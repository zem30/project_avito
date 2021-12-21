package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.CouponMapper;
import com.amr.project.converter.ShopMapper;
import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.CouponDto;
import com.amr.project.model.entity.Coupon;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.CouponStatus;
import com.amr.project.service.abstracts.CouponService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/coupon")
@Api(tags = "API для работы с купонами")
@RequiredArgsConstructor
public class CouponRestController {

    private final CouponService couponService;
    private final CouponMapper couponMapper;
    private final UserMapper userMapper;
    private final ShopMapper shopMapper;
    private final ShopService shopService;
    private final UserService userService;

    @ApiOperation(value = "Добавление купона")
    @PostMapping("/addCoupon")
    public ResponseEntity<?> addCoupon(@RequestBody CouponDto couponDto) {
        if (couponDto.getSum() == null && couponDto.getShop() == null) {
            return ResponseEntity.badRequest().build();
        }
        Coupon coupon = couponMapper.couponDtoToCoupon(couponDto);
        coupon.setStatus(CouponStatus.ACTUAL);

        coupon = couponService.saveD(coupon);
        coupon.setShop(shopMapper.dtoToShop(shopService.getShopId(couponDto.getShopId())));

        User user = userService.findByUsername(couponDto.getUsername());
        List<Coupon> coupons = user.getCoupons();
        coupons.add(coupon);
        user.setCoupons(coupons);
        userService.update(user);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "для обновления статуса купона на USED")
    @PutMapping("/update/used/{id}")
    public ResponseEntity<?> updateToUsed(@PathVariable("id") long id) {
        if (id == 0) {
            return ResponseEntity.badRequest().build();
        }
        Coupon coupon = couponService.getByKey(id);
        coupon.setStatus(CouponStatus.USED);
        couponService.update(coupon);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "для обновления статуса купона на OVERDUE")
    @PutMapping("/update/overdue/{id}")
    public ResponseEntity<?> updateToOverdue(@PathVariable("id") long id) {
        if (id == 0) {
            return ResponseEntity.badRequest().build();
        }
        Coupon coupon = couponService.getByKey(id);
        coupon.setStatus(CouponStatus.OVERDUE);
        couponService.update(coupon);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{name}")
    @ApiOperation(value = "для получения купона BY SHOP NAME")
    public ResponseEntity<List<CouponDto>> getAllCoupons(@PathVariable("name") String nameShop) {
        if (shopService.getShop(nameShop) == null) {
            return ResponseEntity.badRequest().build();
        }
        List<CouponDto> coupons = couponService.findByShop(shopService.getShop(nameShop));
        coupons.forEach(c -> c.setUsername(Objects.requireNonNull(
                        userService.findByCouponId(c.getId())
                        .orElse(null))
                        .getUsername()));

        return ResponseEntity.ok().body(coupons);
    }
}
