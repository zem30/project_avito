package com.amr.project.inserttestdata;

import com.amr.project.inserttestdata.repository.*;
import com.amr.project.model.entity.*;
import com.amr.project.model.enums.Gender;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.*;

@Component
@AllArgsConstructor
public class DataInserting {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ShopRepository shopRepository;
    @Autowired
    private final CountryRepository countryRepository;
    @Autowired
    private final CityRepository cityRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final ItemRepository itemRepository;
    @Autowired
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void init() throws IOException {
//---------------------------------------------------------------Roles
        Role roleAdmin = Role.builder()
                .name("ROLE_Admin")
                .build();
        Role roleUser = Role.builder()
                .name("ROLE_User")
                .build();
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
//---------------------------------------------------------------Cities
        City moscow = City.builder()
                .name("Moscow")
                .build();
        City petersburg = City.builder()
                .name("Saint-Petersburg")
                .build();
        City kyiv = City.builder()
                .name("Kyiv")
                .build();
        City kharkiv = City.builder()
                .name("Kharkiv")
                .build();
        cityRepository.save(moscow);
        cityRepository.save(petersburg);
        cityRepository.save(kyiv);
        cityRepository.save(kharkiv);
//---------------------------------------------------------------Countries
        Country russia = Country.builder()
                .name("Russia")
                .cities(List.of(moscow, petersburg))
                .build();
        Country ukraine = Country.builder()
                .name("Ukraine")
                .cities(List.of(kyiv, kharkiv))
                .build();
        countryRepository.save(russia);
        countryRepository.save(ukraine);
//---------------------------------------------------------------Addresses
        Address address1 = Address.builder()
                .cityIndex("moscow_index")
                .country(countryRepository.findByName("Russia"))
                .city(cityRepository.findByName("Moscow"))
                .street("user1_street")
                .house("user1_house")
                .build();

        Address address2 = Address.builder()
                .cityIndex("kyiv_index")
                .country(countryRepository.findByName("Ukraine"))
                .city(cityRepository.findByName("kyiv"))
                .street("user2_street")
                .house("user2_house")
                .build();
//---------------------------------------------------------------Images
        File admin_image =  ResourceUtils.getFile("classpath:static/images/admin_image.jpg");
        File user1_image =  ResourceUtils.getFile("classpath:static/images/user1_image.jpg");
        File user2_image =  ResourceUtils.getFile("classpath:static/images/user2_image.jpg");

        File item1_image1 =  ResourceUtils.getFile("classpath:static/images/item1_image1.jpg");
        File item1_image2 =  ResourceUtils.getFile("classpath:static/images/item1_image2.jpg");
        File item1_image3 =  ResourceUtils.getFile("classpath:static/images/item1_image3.jpg");

        File item2_image1 =  ResourceUtils.getFile("classpath:static/images/item2_image1.jpg");
        File item2_image2 =  ResourceUtils.getFile("classpath:static/images/item2_image2.jpg");
        File item2_image3 =  ResourceUtils.getFile("classpath:static/images/item2_image3.jpg");

        File item3_image1 =  ResourceUtils.getFile("classpath:static/images/item3_image1.jpg");
        File item3_image2 =  ResourceUtils.getFile("classpath:static/images/item3_image2.jpg");
        File item3_image3 =  ResourceUtils.getFile("classpath:static/images/item3_image3.jpg");

        File shop1_image =  ResourceUtils.getFile("classpath:static/images/shop1_image.jpg");
        File shop2_image =  ResourceUtils.getFile("classpath:static/images/shop2_image.jpg");

        byte[] array_admin_image = Files.readAllBytes(admin_image.toPath());
        byte[] array_user1_image = Files.readAllBytes(user1_image.toPath());
        byte[] array_user2_image = Files.readAllBytes(user2_image.toPath());

        byte[] array_item1_image1 = Files.readAllBytes(item1_image1.toPath());
        byte[] array_item1_image2 = Files.readAllBytes(item1_image2.toPath());
        byte[] array_item1_image3 = Files.readAllBytes(item1_image3.toPath());

        byte[] array_item2_image1 = Files.readAllBytes(item2_image1.toPath());
        byte[] array_item2_image2 = Files.readAllBytes(item2_image2.toPath());
        byte[] array_item2_image3 = Files.readAllBytes(item2_image3.toPath());

        byte[] array_item3_image1 = Files.readAllBytes(item3_image1.toPath());
        byte[] array_item3_image2 = Files.readAllBytes(item3_image2.toPath());
        byte[] array_item3_image3 = Files.readAllBytes(item3_image3.toPath());

        byte[] array_shop1_image = Files.readAllBytes(shop1_image.toPath());
        byte[] array_shop2_image = Files.readAllBytes(shop2_image.toPath());

        Image adminImage = Image.builder()
                .url("adminImageUrl")
                .picture(array_admin_image)
                .isMain(true)
                .build();

        Image user1Image = Image.builder()
                .url("user1ImageUrl")
                .picture(array_user1_image)
                .isMain(true)
                .build();

        Image user2Image = Image.builder()
                .url("user2ImageUrl")
                .picture(array_user2_image)
                .isMain(true)
                .build();

        Image shop1Image = Image.builder()
                .url("shop1ImageUrl")
                .picture(array_shop1_image)
                .isMain(true)
                .build();
        Image shop2Image = Image.builder()
                .url("shop2ImageUrl")
                .picture(array_shop2_image)
                .isMain(true)
                .build();

        Image item1Image1 = Image.builder()
                .url("item1Image1Url")
                .picture(array_item1_image1)
                .isMain(true)
                .build();
        Image item1Image2 = Image.builder()
                .url("item1Image2Url")
                .picture(array_item1_image2)
                .isMain(false)
                .build();
        Image item1Image3 = Image.builder()
                .url("item1Image3Url")
                .picture(array_item1_image3)
                .isMain(false)
                .build();

        Image item2Image1 = Image.builder()
                .url("item2Image1Url")
                .picture(array_item2_image1)
                .isMain(true)
                .build();
        Image item2Image2 = Image.builder()
                .url("item2Image2Url")
                .picture(array_item2_image2)
                .isMain(false)
                .build();
        Image item2Image3 = Image.builder()
                .url("item2Image3Url")
                .picture(array_item2_image3)
                .isMain(false)
                .build();

        Image item3Image1 = Image.builder()
                .url("item3Image1Url")
                .picture(array_item3_image1)
                .isMain(true)
                .build();
        Image item3Image2 = Image.builder()
                .url("item3Image2Url")
                .picture(array_item3_image2)
                .isMain(false)
                .build();
        Image item3Image3 = Image.builder()
                .url("item3Image3Url")
                .picture(array_item3_image3)
                .isMain(false)
                .build();
//---------------------------------------------------------------Shops
        Shop shop1 = Shop.builder()
                .name("shop1")
                .email("shop1@mail")
                .phone("shop1_phone")
                .description("shop1_description")
                .location(russia)
                .items(null)
                .reviews(null)
                .logo(shop1Image)
                .count(0)
                .rating(0)
                .user(null)
                .discounts(null)
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .activity(0)
                .file(null)
                .isPretendentToBeDeleted(false)
                .build();
        Shop shop2 = Shop.builder()
                .name("shop2")
                .email("shop2@mail")
                .phone("shop2_phone")
                .description("shop2_description")
                .location(ukraine)
                .items(null)
                .reviews(null)
                .logo(shop2Image)
                .count(0)
                .rating(0)
                .user(null)
                .discounts(null)
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .activity(0)
                .file(null)
                .isPretendentToBeDeleted(false)
                .build();
        shopRepository.save(shop1);
        shopRepository.save(shop2);
//---------------------------------------------------------------Admin
        User admin1 = User.builder()
                .email("admin1@mail")
                .username("admin1_username")
                .password("password")
                .activate(true)
                .activationCode("activation_code")
                .phone("admin1_phone")
                .firstName("admin1_firstname")
                .lastName("admin1_lastname")
                .address(null)
                .age(0)
                .roles(Set.of(roleRepository.findByName("ROLE_Admin")))
                .gender(Gender.UNKNOWN)
                .birthday(null)
                .images(adminImage)
                .coupons(null)
                .cart(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .favorite(null)
                .discounts(null)
                .build();
        userRepository.save(admin1);

//---------------------------------------------------------------Users
        User user1 = User.builder()
                .email("user1@mail")
                .username("user1_username")
                .password("password")
                .activate(true)
                .activationCode("user1_activation_code")
                .phone("user1_phone")
                .firstName("user1_firstname")
                .lastName("user1_lastname")
                .address(address1)
                .age(31)
                .roles(Set.of(roleRepository.findByName("ROLE_User")))
                .gender(Gender.MALE)
                .birthday(new GregorianCalendar(1990, 1, 1))
                .images(user1Image)
                .coupons(null)
                .cart(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .favorite(null)
                .discounts(null)
                .build();
        User user2 = User.builder()
                .email("user2@mail")
                .username("user2_username")
                .password("password")
                .activate(true)
                .activationCode("user2_activation_code")
                .phone("user2_phone")
                .firstName("user2_firstname")
                .lastName("user2_lastname")
                .address(address2)
                .age(29)
                .roles(Set.of(roleRepository.findByName("ROLE_User")))
                .gender(Gender.FEMALE)
                .birthday(new GregorianCalendar(1992, 2, 2))
                .images(user2Image)
                .coupons(null)
                .cart(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .favorite(null)
                .discounts(null)
                .build();
        userRepository.save(user1);
        userRepository.save(user2);
//---------------------------------------------------------------Categories
        Category category1 = Category.builder()
                .name("category1")
                .build();
        Category category2 = Category.builder()
                .name("category2")
                .build();
        Category category3 = Category.builder()
                .name("category3")
                .build();
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

//---------------------------------------------------------------Items
        Item item1 = Item.builder()
                .name("Item1")
                .price(new BigDecimal("111111"))
                .categories(List.of(categoryRepository.findByName("category1")))
                .images(List.of(item1Image1, item1Image2, item1Image3))
                .reviews(null)
                .count(100)
                .rating(1.0)
                .description("item1_description")
                .discount(100)
                .shop(shop1)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("moderated_reject_reason")
                .isPretendentToBeDeleted(false)
                .build();
        Item item2 = Item.builder()
                .name("Item2")
                .price(new BigDecimal("222222"))
                .categories(List.of(categoryRepository.findByName("category1"), categoryRepository.findByName("category2")))
                .images(List.of(item2Image1, item2Image2, item2Image3))
                .reviews(null)
                .count(200)
                .rating(2.0)
                .description("item2_description")
                .discount(200)
                .shop(shop1)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("moderated_reject_reason")
                .isPretendentToBeDeleted(false)
                .build();
        Item item3 = Item.builder()
                .name("Item3")
                .price(new BigDecimal("333333"))
                .categories(List.of(categoryRepository.findByName("category1"), categoryRepository.findByName("category2"), categoryRepository.findByName("category3")))
                .images(List.of(item3Image1, item3Image2, item3Image3))
                .reviews(null)
                .count(300)
                .rating(3.0)
                .description("item3_description")
                .discount(300)
                .shop(shop2)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("moderated_reject_reason")
                .isPretendentToBeDeleted(false)
                .build();
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);



    }
}
