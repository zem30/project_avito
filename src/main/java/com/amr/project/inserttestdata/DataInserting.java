package com.amr.project.inserttestdata;

import com.amr.project.inserttestdata.repository.*;
import com.amr.project.model.entity.*;
import com.amr.project.model.enums.CouponStatus;
import com.amr.project.model.enums.Gender;
import com.amr.project.model.enums.Status;
import com.amr.project.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static java.time.LocalTime.now;

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
    @Autowired
    private final CartItemRepository cartItemRepository;
    @Autowired
    private final FavoriteRepository favoriteRepository;
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final ReviewRepository reviewRepository;
    @Autowired
    private final ChatRepository chatRepository;
    @Autowired
    private final DiscountRepository discountRepository;
    @Autowired
    private final FeedbackRepository feedbackRepository;
    @Autowired
    private final AnswerFromModeratorRepository answerFromModeratorRepository;
    @Autowired
    private final CouponRepository couponRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AdvertRepository advertRepository;

    @PostConstruct
    public void init() throws IOException, ParseException {
//---------------------------------------------------------------Roles
        Role roleAdmin = Role.builder().name("ADMIN").build();
        Role roleModerator = Role.builder().name("MODERATOR").build();
        Role roleUser = Role.builder().name("USER").build();
        roleRepository.save(roleAdmin);
        roleRepository.save(roleModerator);
        roleRepository.save(roleUser);
//---------------------------------------------------------------Cities
        City city = City.builder().name("City").build();

        City moscow = City.builder().name("Moscow").build();
        City petersburg = City.builder().name("Saint-Petersburg").build();
        City novosibirsk = City.builder().name("Novosibirsk").build();

        City kyiv = City.builder().name("Kyiv").build();
        City kharkiv = City.builder().name("Kharkiv").build();
        City odessa = City.builder().name("Odessa").build();

        City minsk = City.builder().name("Minsk").build();
        City gomel = City.builder().name("Gomel").build();
        City vitebsk = City.builder().name("Vitebsk").build();

        cityRepository.save(city);

        cityRepository.save(moscow);
        cityRepository.save(petersburg);
        cityRepository.save(novosibirsk);

        cityRepository.save(kyiv);
        cityRepository.save(kharkiv);
        cityRepository.save(odessa);

        cityRepository.save(minsk);
        cityRepository.save(gomel);
        cityRepository.save(vitebsk);
//---------------------------------------------------------------Countries
        Country country = Country.builder().name("Country").build();
        Country russia = Country.builder().name("Russia").build();
        Country ukraine = Country.builder().name("Ukraine").build();
        Country belarus = Country.builder().name("Belarus").build();

        countryRepository.save(country);
        countryRepository.save(russia);
        countryRepository.save(ukraine);
        countryRepository.save(belarus);
//---------------------------------------------------------------Addresses
        Address address1 = Address.builder()
                .cityIndex("123456")
                .country(countryRepository.findByName("Russia"))
                .city(cityRepository.findByName("Moscow"))
                .street("user1_street")
                .house("user1_house")
                .build();
        Address address2 = Address.builder()
                .cityIndex("123456")
                .country(countryRepository.findByName("Russia"))
                .city(cityRepository.findByName("Saint-petersburg"))
                .street("user2_street")
                .house("user2_house")
                .build();
        Address address3 = Address.builder()
                .cityIndex("123456")
                .country(countryRepository.findByName("Russia"))
                .city(cityRepository.findByName("Novosibirsk"))
                .street("user3_street")
                .house("user3_house")
                .build();
        Address address4 = Address.builder()
                .cityIndex("123456")
                .country(countryRepository.findByName("Ukraine"))
                .city(cityRepository.findByName("Kyiv"))
                .street("user4_street")
                .house("user4_house")
                .build();
        Address address5 = Address.builder()
                .cityIndex("123456")
                .country(countryRepository.findByName("Ukraine"))
                .city(cityRepository.findByName("Kharkiv"))
                .street("user5_street")
                .house("user5_house")
                .build();
        Address address6 = Address.builder()
                .cityIndex("123456")
                .country(countryRepository.findByName("Ukraine"))
                .city(cityRepository.findByName("Odessa"))
                .street("user6_street")
                .house("user6_house")
                .build();
//---------------------------------------------------------------Images
        File admin_image = ResourceUtils.getFile("classpath:static/images/users/admin_image.jpg");
        File moderator_image = ResourceUtils.getFile("classpath:static/images/users/moderator_image.jpg");

        File user1_image = ResourceUtils.getFile("classpath:static/images/users/user1_image.jpg");
        File user2_image = ResourceUtils.getFile("classpath:static/images/users/user2_image.jpg");
        File user3_image = ResourceUtils.getFile("classpath:static/images/users/user3_image.jpg");
        File user4_image = ResourceUtils.getFile("classpath:static/images/users/user4_image.jpg");
        File user5_image = ResourceUtils.getFile("classpath:static/images/users/user5_image.jpg");
        File user6_image = ResourceUtils.getFile("classpath:static/images/users/user6_image.jpg");

        File mi_image =  ResourceUtils.getFile("classpath:static/images/shops/mi.jpg");
        File predator_image =  ResourceUtils.getFile("classpath:static/images/shops/predator.png");
        File samsung_image =  ResourceUtils.getFile("classpath:static/images/shops/samsung.png");

        File headphonesPart1 =  ResourceUtils.getFile("classpath:static/images/items/headphonesPart1.jpg");
        File headphonesPart2 =  ResourceUtils.getFile("classpath:static/images/items/headphonesPart2.jpg");
        File headphonesPart3 =  ResourceUtils.getFile("classpath:static/images/items/headphonesPart3.jpg");

        File tablet1 =  ResourceUtils.getFile("classpath:static/images/items/tablet1.jpg");
        File tablet2 =  ResourceUtils.getFile("classpath:static/images/items/tablet2.jpg");
        File tablet3 =  ResourceUtils.getFile("classpath:static/images/items/tablet3.jpg");

        File laptop1 =  ResourceUtils.getFile("classpath:static/images/items/laptop1.jpg");
        File laptop2 =  ResourceUtils.getFile("classpath:static/images/items/laptop2.jpg");
        File laptop3 =  ResourceUtils.getFile("classpath:static/images/items/laptop3.jpg");

        File cleaner1 =  ResourceUtils.getFile("classpath:static/images/items/cleaner1.jpg");
        File cleaner2 =  ResourceUtils.getFile("classpath:static/images/items/cleaner2.jpg");
        File cleaner3 =  ResourceUtils.getFile("classpath:static/images/items/cleaner3.jpg");

        File TV1 =  ResourceUtils.getFile("classpath:static/images/items/TV1.jpg");
        File TV2 =  ResourceUtils.getFile("classpath:static/images/items/TV2.jpg");
        File TV3 =  ResourceUtils.getFile("classpath:static/images/items/TV3.jpg");

        File mouse1 =  ResourceUtils.getFile("classpath:static/images/items/mouse1.jpg");
        File mouse2 =  ResourceUtils.getFile("classpath:static/images/items/mouse2.jpg");
        File mouse3 =  ResourceUtils.getFile("classpath:static/images/items/mouse3.jpg");

        File hp1 =  ResourceUtils.getFile("classpath:static/images/items/hp1.jpg");
        File hp2 =  ResourceUtils.getFile("classpath:static/images/items/hp2.jpg");
        File hp3 =  ResourceUtils.getFile("classpath:static/images/items/hp3.jpg");

        byte[] array_admin_image = Files.readAllBytes(admin_image.toPath());
        byte[] array_moderator_image = Files.readAllBytes(moderator_image.toPath());
        byte[] array_user1_image = Files.readAllBytes(user1_image.toPath());
        byte[] array_user2_image = Files.readAllBytes(user2_image.toPath());
        byte[] array_user3_image = Files.readAllBytes(user3_image.toPath());
        byte[] array_user4_image = Files.readAllBytes(user4_image.toPath());
        byte[] array_user5_image = Files.readAllBytes(user5_image.toPath());
        byte[] array_user6_image = Files.readAllBytes(user6_image.toPath());

        byte[] array_mi_image = Files.readAllBytes(mi_image.toPath());
        byte[] array_predator_image = Files.readAllBytes(predator_image.toPath());
        byte[] array_samsung_image = Files.readAllBytes(samsung_image.toPath());

        byte[] array_headphonesPart1 = Files.readAllBytes(headphonesPart1.toPath());
        byte[] array_headphonesPart2 = Files.readAllBytes(headphonesPart2.toPath());
        byte[] array_headphonesPart3 = Files.readAllBytes(headphonesPart3.toPath());

        byte[] array_tablet1 = Files.readAllBytes(tablet1.toPath());
        byte[] array_tablet2 = Files.readAllBytes(tablet2.toPath());
        byte[] array_tablet3 = Files.readAllBytes(tablet3.toPath());

        byte[] array_laptop1 = Files.readAllBytes(laptop1.toPath());
        byte[] array_laptop2 = Files.readAllBytes(laptop2.toPath());
        byte[] array_laptop3 = Files.readAllBytes(laptop3.toPath());

        byte[] array_cleaner1 = Files.readAllBytes(cleaner1.toPath());
        byte[] array_cleaner2 = Files.readAllBytes(cleaner2.toPath());
        byte[] array_cleaner3 = Files.readAllBytes(cleaner3.toPath());

        byte[] array_TV1 = Files.readAllBytes(TV1.toPath());
        byte[] array_TV2 = Files.readAllBytes(TV2.toPath());
        byte[] array_TV3 = Files.readAllBytes(TV3.toPath());

        byte[] array_mouse1 = Files.readAllBytes(mouse1.toPath());
        byte[] array_mouse2 = Files.readAllBytes(mouse2.toPath());
        byte[] array_mouse3 = Files.readAllBytes(mouse3.toPath());

        byte[] array_hp1 = Files.readAllBytes(hp1.toPath());
        byte[] array_hp2 = Files.readAllBytes(hp2.toPath());
        byte[] array_hp3 = Files.readAllBytes(hp3.toPath());

        Image adminImage = Image.builder().picture(array_admin_image).isMain(true).build();
        Image moderatorImage = Image.builder().picture(array_moderator_image).isMain(true).build();
        Image user1Image = Image.builder().picture(array_user1_image).isMain(true).build();
        Image user2Image = Image.builder().picture(array_user2_image).isMain(true).build();
        Image user3Image = Image.builder().picture(array_user3_image).isMain(true).build();
        Image user4Image = Image.builder().picture(array_user4_image).isMain(true).build();
        Image user5Image = Image.builder().picture(array_user5_image).isMain(true).build();
        Image user6Image = Image.builder().picture(array_user6_image).isMain(true).build();

        Image miLogo = Image.builder().picture(array_mi_image).isMain(true).build();
        Image predatorLogo = Image.builder().picture(array_predator_image).isMain(true).build();
        Image samsungLogo = Image.builder().picture(array_samsung_image).isMain(true).build();

        Image item1Image1 = Image.builder().picture(array_headphonesPart1).isMain(true).build();
        Image item1Image2 = Image.builder().picture(array_headphonesPart2).isMain(false).build();
        Image item1Image3 = Image.builder().picture(array_headphonesPart3).isMain(false).build();

        Image item2Image1 = Image.builder().picture(array_cleaner1).isMain(true).build();
        Image item2Image2 = Image.builder().picture(array_cleaner2).isMain(false).build();
        Image item2Image3 = Image.builder().picture(array_cleaner3).isMain(false).build();

        Image item3Image1 = Image.builder().picture(array_tablet1).isMain(true).build();
        Image item3Image2 = Image.builder().picture(array_tablet2).isMain(false).build();
        Image item3Image3 = Image.builder().picture(array_tablet3).isMain(false).build();

        Image item4Image1 = Image.builder().picture(array_TV1).isMain(true).build();
        Image item4Image2 = Image.builder().picture(array_TV2).isMain(false).build();
        Image item4Image3 = Image.builder().picture(array_TV3).isMain(false).build();

        Image item5Image1 = Image.builder().picture(array_mouse1).isMain(true).build();
        Image item5Image2 = Image.builder().picture(array_mouse2).isMain(false).build();
        Image item5Image3 = Image.builder().picture(array_mouse3).isMain(false).build();

        Image item6Image1 = Image.builder().picture(array_laptop1).isMain(true).build();
        Image item6Image2 = Image.builder().picture(array_laptop2).isMain(false).build();
        Image item6Image3 = Image.builder().picture(array_laptop3).isMain(false).build();

        Image item7Image1 = Image.builder().picture(array_hp1).isMain(true).build();
        Image item7Image2 = Image.builder().picture(array_hp2).isMain(false).build();
        Image item7Image3 = Image.builder().picture(array_hp3).isMain(false).build();
//---------------------------------------------------------------Admin + Moderator
        User admin1 = User.builder()
                .email("admin1@mail")
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .activate(true)
                .activationCode("admin1_activation_code")
                .phone("admin1_phone")
                .firstName("admin1_firstname")
                .lastName("admin1_lastname")
                .address(null)
                .age(0)
                .roles(Set.of(roleRepository.findByName("ADMIN")))
                .gender(Gender.UNKNOWN)
                .birthday(null)
                .images(adminImage)
                .coupons(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .favorite(null)
                .discounts(null)
                .build();
        User moderator1 = User.builder()
                .email("moderator1@mail")
                .username("moderator1_username")
                .password(passwordEncoder.encode("password"))
                .activate(true)
                .activationCode("moderator1_activation_code")
                .phone("moderator1_phone")
                .firstName("moderator1_firstname")
                .lastName("moderator1_lastname")
                .address(null)
                .age(0)
                .roles(Set.of(roleRepository.findByName("MODERATOR")))
                .gender(Gender.UNKNOWN)
                .birthday(null)
                .images(moderatorImage)
                .coupons(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .favorite(null)
                .discounts(null)
                .build();
        userRepository.save(admin1);
        userRepository.save(moderator1);
//---------------------------------------------------------------Users
        User user1 = User.builder()
                .email("user1@mail")
                .username("user1_username")
                .password(passwordEncoder.encode("password"))
                .activate(true)
                .activationCode("user1_activation_code")
                .phone("user1_phone")
                .firstName("user1_firstname")
                .lastName("user1_lastname")
                .address(address1)
                .age(30)
                .roles(Set.of(roleRepository.findByName("USER")))
                .gender(Gender.MALE)
                .birthday(new GregorianCalendar(1991, 1, 1))
                .images(user1Image)
                .coupons(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .favorite(null)
                .discounts(null)
                .build();
        User user2 = User.builder()
                .email("user2@mail")
                .username("user2_username")
                .password(passwordEncoder.encode("password"))
                .activate(true)
                .activationCode("user2_activation_code")
                .phone("user2_phone")
                .firstName("user2_firstname")
                .lastName("user2_lastname")
                .address(address2)
                .age(29)
                .roles(Set.of(roleRepository.findByName("USER")))
                .gender(Gender.MALE)
                .birthday(new GregorianCalendar(1992, 2, 2))
                .images(user2Image)
                .coupons(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .favorite(null)
                .discounts(null)
                .build();
        User user3 = User.builder()
                .email("user3@mail")
                .username("user3_username")
                .password(passwordEncoder.encode("password"))
                .activate(true)
                .activationCode("user3_activation_code")
                .phone("user3_phone")
                .firstName("user3_firstname")
                .lastName("user3_lastname")
                .address(address3)
                .age(28)
                .roles(Set.of(roleRepository.findByName("USER")))
                .gender(Gender.MALE)
                .birthday(new GregorianCalendar(1993, 3, 3))
                .images(user3Image)
                .coupons(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .favorite(null)
                .discounts(null)
                .build();
        User user4 = User.builder()
                .email("user4@mail")
                .username("user4_username")
                .password(passwordEncoder.encode("password"))
                .activate(true)
                .activationCode("user4_activation_code")
                .phone("user4_phone")
                .firstName("user4_firstname")
                .lastName("user4_lastname")
                .address(address4)
                .age(27)
                .roles(Set.of(roleRepository.findByName("USER")))
                .gender(Gender.FEMALE)
                .birthday(new GregorianCalendar(1994, 4, 4))
                .images(user4Image)
                .coupons(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .favorite(null)
                .discounts(null)
                .build();
        User user5 = User.builder()
                .email("user5@mail")
                .username("user5_username")
                .password(passwordEncoder.encode("password"))
                .activate(true)
                .activationCode("user5_activation_code")
                .phone("user5_phone")
                .firstName("user5_firstname")
                .lastName("user5_lastname")
                .address(address5)
                .age(26)
                .roles(Set.of(roleRepository.findByName("USER")))
                .gender(Gender.FEMALE)
                .birthday(new GregorianCalendar(1995, 5, 5))
                .images(user5Image)
                .coupons(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .favorite(null)
                .discounts(null)
                .build();
        User user6 = User.builder()
                .email("user6@mail")
                .username("user6_username")
                .password(passwordEncoder.encode("password"))
                .activate(true)
                .activationCode("user6_activation_code")
                .phone("user6_phone")
                .firstName("user6_firstname")
                .lastName("user6_lastname")
                .address(address6)
                .age(25)
                .roles(Set.of(roleRepository.findByName("USER")))
                .gender(Gender.FEMALE)
                .birthday(new GregorianCalendar(1996, 6, 6))
                .images(user6Image)
                .coupons(null)
                .orders(null)
                .reviews(null)
                .shops(null)
                .favorite(null)
                .discounts(null)
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
//---------------------------------------------------------------Shops
        Shop mi = Shop.builder()
                .name("mi")
                .email("mi@mail")
                .phone("magazinPhone")
                .description("©2016-2021")
                .location(russia)
                .items(null)
                .reviews(null)
                .logo(List.of(miLogo))
                .count(0)
                .coupons(null)
                .rating(1)
                .user(userRepository.findByEmail("user1@mail"))
                .discounts(null)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason(null)
                .activity(0)
                .file(null)
                .isPretendentToBeDeleted(false)
                .build();

        Shop samsung = Shop.builder()
                .name("samsung")
                .email("samsung@mail")
                .phone("magazinPhone")
                .description("©1995-2021")
                .location(ukraine)
                .items(null)
                .reviews(null)
                .logo(List.of(samsungLogo))
                .count(0)
                .coupons(null)
                .rating(2)
                .user(userRepository.findByEmail("user2@mail"))
                .discounts(null)
                .isModerated(true)
                .isModerateAccept(false)
                .moderatedRejectReason("moderation failed")
                .activity(0)
                .file(null)
                .isPretendentToBeDeleted(false)
                .build();

        Shop predator = Shop.builder()
                .name("predator")
                .email("predator@mail")
                .phone("magazinPhone")
                .description("©2010-2021")
                .location(belarus)
                .items(null)
                .reviews(null)
                .logo(List.of(predatorLogo))
                .count(0)
                .coupons(null)
                .rating(3)
                .user(userRepository.findByEmail("user3@mail"))
                .discounts(null)
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .activity(0)
                .file(null)
                .isPretendentToBeDeleted(false)
                .build();
        shopRepository.save(mi);
        shopRepository.save(samsung);
        shopRepository.save(predator);

        user1.setShops(List.of(mi));
        user3.setShops(List.of(predator, samsung));
        userRepository.save(user3); // создалась связь в таблицу user_shop
        userRepository.save(user1);

//---------------------------------------------------------------Categories
        Category category1 = Category.builder().name("headphones").guid(Util.getGuid()).build();
        Category hardware = Category.builder().name("hardware").guid(Util.getGuid()).build();
        Category tablets = Category.builder().name("tablets").guid(Util.getGuid()).build();
        Category vacuumCleaners = Category.builder().name("vacuumCleaners").guid(Util.getGuid()).build();
        Category TV = Category.builder().name("TV").guid(Util.getGuid()).build();
        Category laptop = Category.builder().name("laptop").guid(Util.getGuid()).build();

        categoryRepository.save(category1);
        categoryRepository.save(hardware);
        categoryRepository.save(tablets);
        categoryRepository.save(vacuumCleaners);
        categoryRepository.save(TV);
        categoryRepository.save(laptop);
//---------------------------------------------------------------Items
        Item item1 = Item.builder()
                .name("True Wireless Beats Studio")
                .price(new BigDecimal("13990"))
                .categories(List.of(categoryRepository.findByName("headphones")))
                .images(List.of(item1Image1, item1Image2, item1Image3))
                .reviews(null)
                .count(2)
                .rating(1.0)
                .description("Система активного подавления шума")
                .discount(0)
                .shop(mi)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason(null)
                .isPretendentToBeDeleted(false)
                .build();
        Item item2 = Item.builder()
                .name("Mi Robot Vacuum-Mop")
                .price(new BigDecimal("16999"))
                .categories(List.of(categoryRepository.findByName("vacuumCleaners")))
                .images(List.of(item2Image1, item2Image2, item2Image3))
                .reviews(null)
                .count(200)
                .rating(2.0)
                .description("Количество режимов работы - 4")
                .discount(0)
                .shop(mi)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason(null)
                .isPretendentToBeDeleted(false)
                .build();
        Item item3 = Item.builder()
                .name("Samsung Galaxy Tab A 8.0")
                .price(new BigDecimal("11999"))
                .categories(List.of(categoryRepository.findByName("tablets")))
                .images(List.of(item3Image1, item3Image2, item3Image3))
                .reviews(null)
                .count(300)
                .rating(3.0)
                .description("RAM - 2гб, ROM - 32гб")
                .discount(0)
                .shop(samsung)
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .isPretendentToBeDeleted(false)
                .build();
        Item item4 = Item.builder()
                .name("Samsung TV QE50Q67AAU")
                .price(new BigDecimal("66999"))
                .categories(List.of(categoryRepository.findByName("TV")))
                .images(List.of(item4Image1, item4Image2, item4Image3))
                .reviews(null)
                .count(400)
                .rating(4.0)
                .description("Экран - 50/3840x2160 Пикс")
                .discount(0)
                .shop(samsung)
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .isPretendentToBeDeleted(false)
                .build();
        Item item5 = Item.builder()
                .name("Mouse Logitech M190")
                .price(new BigDecimal("999"))
                .categories(List.of(categoryRepository.findByName("hardware")))
                .images(List.of(item5Image1, item5Image2, item5Image3))
                .reviews(null)
                .count(500)
                .rating(5.0)
                .description("Тип мыши - оптическая")
                .discount(0)
                .shop(predator)
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .isPretendentToBeDeleted(false)
                .build();
        Item item6 = Item.builder()
                .name("Acer Nitro 5 AN515-55-50LX")
                .price(new BigDecimal("98999"))
                .categories(List.of(categoryRepository.findByName("laptop")))
                .images(List.of(item6Image1, item6Image2, item6Image3))
                .reviews(null)
                .count(600)
                .rating(5.0)
                .description("Процессор Intel Core i510300H")
                .discount(0)
                .shop(predator)
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .isPretendentToBeDeleted(false)
                .build();
        Item item7 = Item.builder()
                .name("HP 15s-eq2013ur 3B4T1EA")
                .price(new BigDecimal("54999"))
                .categories(List.of(categoryRepository.findByName("laptop")))
                .images(List.of(item7Image1, item7Image2, item7Image3))
                .reviews(null)
                .count(0)
                .rating(2.0)
                .description("Процессор AMD Ryzen 5 5500U 2.1")
                .discount(0)
                .shop(predator)
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .isPretendentToBeDeleted(false)
                .build();

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);
        itemRepository.save(item6);
        itemRepository.save(item7);
//---------------------------------------------------------------CartItem
        User user4_cartItem = userRepository.findByEmail("user4@mail");
        User user5_cartItem = userRepository.findByEmail("user5@mail");

        Item item1_cart_item1 = itemRepository.findByName("True Wireless Beats Studio");
        Item item2_cart_item2 = itemRepository.findByName("Mi Robot Vacuum-Mop");
        Item item3_cart_item3 = itemRepository.findByName("Samsung Galaxy Tab A 8.0");
        Item item4_cart_item4 = itemRepository.findByName("Samsung TV QE50Q67AAU");
        Item item5_cart_item5 = itemRepository.findByName("Mouse Logitech M190");
        Item item6_cart_item6 = itemRepository.findByName("Acer Nitro 5 AN515-55-50LX");

        CartItem cartItem1 = CartItem.builder()
                .item(item1_cart_item1)
                .shop(item1_cart_item1.getShop())
                .user(user4_cartItem)
                .quantity(2)
                .build();
        CartItem cartItem2 = CartItem.builder()
                .item(item2_cart_item2)
                .shop(item2_cart_item2.getShop())
                .user(user4_cartItem)
                .quantity(2)
                .build();
        CartItem cartItem3 = CartItem.builder()
                .item(item3_cart_item3)
                .shop(item3_cart_item3.getShop())
                .user(user4_cartItem)
                .quantity(2)
                .build();

        CartItem cartItem4 = CartItem.builder()
                .item(item4_cart_item4)
                .shop(item4_cart_item4.getShop())
                .user(user5_cartItem)
                .quantity(2)
                .build();
        CartItem cartItem5 = CartItem.builder()
                .item(item5_cart_item5)
                .shop(item5_cart_item5.getShop())
                .user(user5_cartItem)
                .quantity(2)
                .build();
        CartItem cartItem6 = CartItem.builder()
                .item(item6_cart_item6)
                .shop(item6_cart_item6.getShop())
                .user(user5_cartItem)
                .quantity(2)
                .build();

        cartItemRepository.save(cartItem1);
        cartItemRepository.save(cartItem2);
        cartItemRepository.save(cartItem3);

        cartItemRepository.save(cartItem4);
        cartItemRepository.save(cartItem5);
        cartItemRepository.save(cartItem6);

        user4_cartItem.setCartItems(List.of(cartItem1, cartItem2, cartItem3));
        user5_cartItem.setCartItems(List.of(cartItem4, cartItem5, cartItem6));

        userRepository.save(user4_cartItem);
        userRepository.save(user5_cartItem);

        cartItem1 = cartItemRepository.findById(1L).orElse(null);
        cartItem2 = cartItemRepository.findById(2L).orElse(null);
        cartItem3 = cartItemRepository.findById(3L).orElse(null);
        cartItem4 = cartItemRepository.findById(4L).orElse(null);
        cartItem5 = cartItemRepository.findById(5L).orElse(null);
        cartItem6 = cartItemRepository.findById(6L).orElse(null);

        item1_cart_item1.setCartItems(List.of(cartItem1));
        item2_cart_item2.setCartItems(List.of(cartItem2));
        item3_cart_item3.setCartItems(List.of(cartItem3));
        item4_cart_item4.setCartItems(List.of(cartItem4));
        item5_cart_item5.setCartItems(List.of(cartItem5));
        item6_cart_item6.setCartItems(List.of(cartItem6));

        itemRepository.save(item1_cart_item1);
        itemRepository.save(item2_cart_item2);
        itemRepository.save(item3_cart_item3);
        itemRepository.save(item4_cart_item4);
        itemRepository.save(item5_cart_item5);
        itemRepository.save(item6_cart_item6);

//---------------------------------------------------------------Favourite
        User user4_favorite = userRepository.findByEmail("user4@mail");
        User user5_favorite = userRepository.findByEmail("user5@mail");
        User user6_favorite = userRepository.findByEmail("user6@mail");

        Favorite favorite_user4 = Favorite.builder()
                .items(List.of(item1))
                .shops(List.of(mi))
                .user(user4_favorite)
                .build();
        Favorite favorite_user5 = Favorite.builder()
                .items(List.of(item1, item4))
                .shops(List.of(mi, samsung))
                .user(user5_favorite)
                .build();
        Favorite favorite_user6 = Favorite.builder()
                .items(List.of(item1, item2, item6))
                .shops(List.of(mi, samsung))
                .user(user6_favorite)
                .build();

        favoriteRepository.save(favorite_user4);
        favoriteRepository.save(favorite_user5);
        favoriteRepository.save(favorite_user6);

        user4_favorite.setFavorite(favoriteRepository.findByUserId(user4_favorite.getId()));
        userRepository.save(user4_favorite);

        user5_favorite.setFavorite(favoriteRepository.findByUserId(user5_favorite.getId()));
        userRepository.save(user5_favorite);

        user6_favorite.setFavorite(favoriteRepository.findByUserId(user6_favorite.getId()));
        userRepository.save(user6_favorite);
//---------------------------------------------------------------Orders
        User user4_order = userRepository.findByEmail("user4@mail");
        Item user4_order_item1 = itemRepository.findByName("True Wireless Beats Studio");
        Item user4_order_item2 = itemRepository.findByName("Mi Robot Vacuum-Mop");

        User user5_order = userRepository.findByEmail("user5@mail");
        Item user5_order_item1 = itemRepository.findByName("Samsung Galaxy Tab A 8.0");
        Item user5_order_item2 = itemRepository.findByName("Samsung TV QE50Q67AAU");

        User user6_order = userRepository.findByEmail("user6@mail");
        Item user6_order_item1 = itemRepository.findByName("Mouse Logitech M190");
        Item user6_order_item2 = itemRepository.findByName("Acer Nitro 5 AN515-55-50LX");

        Shop shop_insert_order_user6 = shopRepository.findByName("predator");
        Shop shop_insert_order_user4 = shopRepository.findByName("mi");
        Shop shop_insert_order_user5 = shopRepository.findByName("samsung");

        Order order_user4 = Order.builder()
                .items(List.of(user4_order_item1, user4_order_item2))
                .date(GregorianCalendar.getInstance())
                .status(Status.START)
                .address(user4_order.getAddress())
                .total(itemRepository.findByName("True Wireless Beats Studio").getPrice().add(itemRepository.findByName("Mi Robot Vacuum-Mop").getPrice()))
                .user(user4_order)
                .buyerName(user4_order.getUsername())
                .buyerPhone(user4_order.getPhone())
                .build();

        Order order_user5 = Order.builder()
                .items(List.of(user5_order_item1, user5_order_item2))
                .date(GregorianCalendar.getInstance())
                .status(Status.START)
                .address(user5_order.getAddress())
                .total(user5_order_item1.getPrice().add(user5_order_item2.getPrice()))
                .user(user5_order)
                .buyerName(user5_order.getUsername())
                .buyerPhone(user5_order.getPhone())
                .build();

        Order order_user6 = Order.builder()
                .items(List.of(user6_order_item1, user6_order_item2))
                .date(GregorianCalendar.getInstance())
                .status(Status.START)
                .address(user6_order.getAddress())
                .total(user6_order_item1.getPrice().add(user6_order_item2.getPrice()))
                .user(user6_order)
                .buyerName(user6_order.getUsername())
                .buyerPhone(user6_order.getPhone())
                .build();

        Order order2_user6 = Order.builder()
                .items(List.of(user6_order_item1))
                .date(GregorianCalendar.getInstance())
                .status(Status.COMPLETE)
                .address(user6_order.getAddress())
                .total(user6_order_item1.getPrice())
                .user(user6_order)
                .buyerName(user6_order.getUsername())
                .buyerPhone(user6_order.getPhone())
                .build();

        user4_order.setOrders(List.of(order_user4));
        user5_order.setOrders(List.of(order_user5));
        user6_order.setOrders(List.of(order_user6, order2_user6));

        shop_insert_order_user6.setOrders(List.of(order_user6, order2_user6));
        shop_insert_order_user4.setOrders(List.of(order_user4));
        shop_insert_order_user5.setOrders(List.of(order_user5));

        userRepository.save(user4_order);
        userRepository.save(user5_order);
        userRepository.save(user6_order);

        shopRepository.save(shop_insert_order_user6);
        shopRepository.save(shop_insert_order_user5);
        shopRepository.save(shop_insert_order_user4);
//---------------------------------------------------------------Reviews
        //Ревью user4 на товар item1
        User user4_review = userRepository.findByEmail("user4@mail");
        Item item_review1_user4 = itemRepository.findByName("Samsung TV QE50Q67AAU");
        Shop shop_review1_user4 = shopRepository.findByName("samsung");
        Review review1_user4_item = Review.builder()
                .dignity("dignity_review_user4")
                .flaw("flaw_review_user4")
                .text("text_review_user4")
                .date(Date.from(LocalDate.now().atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant()))
                .rating(4)
                .user(null) //detached entity passed to persist: com.amr.project.model.entity.User
                .item(null)
                .shop(null)
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .logo(null)
                .file(null)
                .build();
        reviewRepository.save(review1_user4_item);

        review1_user4_item.setItem(item_review1_user4);
        review1_user4_item.setShop(shop_review1_user4);
        review1_user4_item.setUser(user4_review);
        reviewRepository.save(review1_user4_item); //создалась связь в колонке user_id (таблица review) и item_review

        Review review1_user4_item_managed = reviewRepository.findByUserId(user4_review.getId());
        user4_review.setReviews(List.of(review1_user4_item_managed));
        userRepository.save(user4_review); // создалась связь в таблицу user_review

        //Ревью user5 на магазин shop1
        User user5_review = userRepository.findByEmail("user5@mail");
        Item item_review1_user5 = itemRepository.findByName("Mi Robot Vacuum-Mop");
        Shop shop_review1_user5 = shopRepository.findByName("mi");
        Review review1_user5_shop = Review.builder()
                .dignity("dignity_review_user5")
                .flaw("flaw_review_user5")
                .text("text_review_user5")
                .date(Date.from(LocalDate.now().atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant()))
                .rating(5)
                .user(null) //detached entity passed to persist: com.amr.project.model.entity.User
                .item(null)
                .shop(null)
                .logo(null)
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .file(null)
                .build();
        reviewRepository.save(review1_user5_shop);
        review1_user5_shop.setItem(item_review1_user5);
        review1_user5_shop.setShop(shop_review1_user5);
        review1_user5_shop.setUser(user5_review);
        reviewRepository.save(review1_user5_shop); //создалась связь в колонке user_id (таблица review) и shop_review

        Review review1_user5_shop_managed = reviewRepository.findByUserId(user5_review.getId());
        user5_review.setReviews(List.of(review1_user5_shop_managed));
        userRepository.save(user5_review); // создалась связь в таблицу user_review

        shop_review1_user5.setReviews(List.of(review1_user5_shop_managed));
        shopRepository.save(shop_review1_user5); //создалась связь в таблицу shop_review
//---------------------------------------------------------------Chats
        //Chat1
        User chat1_member1 = userRepository.findByEmail("moderator1@mail");
        User chat1_member2 = userRepository.findByEmail("user1@mail");

        Chat chat1 = Chat.builder().members(List.of(chat1_member1, chat1_member2)).build();

        Message message1_chat1 = Message.builder()
                .chat(chat1)
                .from(chat1_member1)
                .to(chat1_member2)
                .textMessage("message1")
                .viewed(false)
                .build();
        Message message2_chat1 = Message.builder()
                .chat(chat1)
                .from(chat1_member2)
                .to(chat1_member1)
                .textMessage("message2")
                .viewed(false)
                .build();
        Message message3_chat1 = Message.builder()
                .chat(chat1)
                .from(chat1_member2)
                .to(chat1_member1)
                .textMessage("message3")
                .viewed(false)
                .build();

        chat1.setMessages(List.of(message1_chat1, message2_chat1, message3_chat1));
        chatRepository.save(chat1);

        //Chat2
        User chat2_member1 = userRepository.findByEmail("user1@mail");
        User chat2_member2 = userRepository.findByEmail("user4@mail");

        Chat chat2 = Chat.builder().members(List.of(chat2_member1, chat2_member2)).build();

        Message message1_chat2 = Message.builder()
                .chat(chat2)
                .from(chat2_member1)
                .to(chat2_member2)
                .textMessage("message1")
                .viewed(false)
                .build();
        Message message2_chat2 = Message.builder()
                .chat(chat2)
                .from(chat2_member2)
                .to(chat2_member1)
                .textMessage("message2")
                .viewed(false)
                .build();
        Message message3_chat2 = Message.builder()
                .chat(chat2)
                .from(chat2_member2)
                .to(chat2_member1)
                .textMessage("message3")
                .viewed(false)
                .build();

        chat2.setMessages(List.of(message1_chat2, message2_chat2, message3_chat2));
        chatRepository.save(chat2);
//---------------------------------------------------------------Discounts
        //Discount2
        Shop shop1_discount1 = shopRepository.findByName("mi");
        User user4_discount1 = userRepository.findByEmail("user4@mail");

        Discount discount1 = Discount.builder()
                .minOrder(1000)
                .percentage(10)
                .fixedDiscount(100)
                .shop(shop1_discount1)
//                .user(user4_discount1)
                .build();
        discountRepository.save(discount1);

        user4_discount1.setDiscounts(List.of(discount1));
        userRepository.save(user4_discount1);

        shop1_discount1.setDiscounts(List.of(discount1));
        shopRepository.save(shop1_discount1);

        //Discount2
        Shop shop2_discount1 = shopRepository.findByName("samsung");
        User user5_discount1 = userRepository.findByEmail("user5@mail");

        Discount discount2 = Discount.builder()
                .minOrder(2000)
                .percentage(20)
                .fixedDiscount(200)
                .shop(shop2_discount1)
//                .user(user5_discount1)
                .build();
        discountRepository.save(discount2);

        user5_discount1.setDiscounts(List.of(discount2));
        userRepository.save(user5_discount1);

        shop2_discount1.setDiscounts(List.of(discount2));
        shopRepository.save(shop2_discount1);
//---------------------------------------------------------------Coupons
        User user4_coupons = userRepository.findByEmail("user4@mail");
        User user5_coupons = userRepository.findByEmail("user5@mail");
        User user6_coupons = userRepository.findByEmail("user6@mail");

        Shop samsung_coupons = shopRepository.findByName("samsung");
        Shop mi_coupons = shopRepository.findByName("mi");
        Shop predator_coupons = shopRepository.findByName("predator");

        Coupon coupon1 = Coupon.builder()
                .shop(samsung_coupons)
                .sum(75)
                .start(new GregorianCalendar(2021, 10, 26)) // в БД заносится на месяц вперед ?
                .end(new GregorianCalendar(2021, 11, 26))
                .status(CouponStatus.ACTUAL)
                .build();

        Coupon coupon2 = Coupon.builder()
                .shop(mi_coupons)
                .sum(80)
                .start(new GregorianCalendar(2021, 10, 24))
                .end(new GregorianCalendar(2021, 11, 24))
                .status(CouponStatus.USED)
                .build();

        Coupon coupon3 = Coupon.builder()
                .shop(predator_coupons)
                .sum(70)
                .start(new GregorianCalendar(2021, 10, 20))
                .end(new GregorianCalendar(2021, 11, 20))
                .status(CouponStatus.ACTUAL)
                .build();

        Coupon coupon4 = Coupon.builder()
                .shop(predator_coupons)
                .sum(100)
                .start(new GregorianCalendar(2021, 11, 4))
                .end(new GregorianCalendar(2021, 12, 4))
                .status(CouponStatus.ACTUAL)
                .build();

        couponRepository.save(coupon1);
        couponRepository.save(coupon2);
        couponRepository.save(coupon3);

        user4_coupons.setCoupons(List.of(coupon1));
        user5_coupons.setCoupons(List.of(coupon2));
        user6_coupons.setCoupons(List.of(coupon3));

        userRepository.save(user4_coupons);
        userRepository.save(user5_coupons);
        userRepository.save(user6_coupons);

        samsung_coupons.setCoupons(List.of(coupon1));
        mi_coupons.setCoupons(List.of(coupon2));
        predator_coupons.setCoupons(List.of(coupon3, coupon4));

        shopRepository.save(samsung_coupons);
        shopRepository.save(mi_coupons);
        shopRepository.save(predator_coupons);

//---------------------------------------------------------------Feedback
        Feedback feedback1 = Feedback.builder()
                .full_text("full_text_feedback1")
                .reason("reason_feedback1")
                .build();
        Feedback feedback2 = Feedback.builder()
                .full_text("full_text_feedback1")
                .reason("reason_feedback2")
                .build();
        feedbackRepository.save(feedback1);
        feedbackRepository.save(feedback2);
//---------------------------------------------------------------Answer from moderator
        AnswerFromModerator answerFromModerator1 = AnswerFromModerator.builder()
                .answer_from_moderator("answer_from_moderator_1")
                .build();
        AnswerFromModerator answerFromModerator2 = AnswerFromModerator
                .builder().answer_from_moderator("answer_from_moderator_2")
                .build();

        answerFromModeratorRepository.save(answerFromModerator1);
        answerFromModeratorRepository.save(answerFromModerator2);

//---------------------------------------------------------------Advert
        User user4_advert = userRepository.findByEmail("user4@mail");
        User user5_advert = userRepository.findByEmail("user5@mail");
        User user6_advert = userRepository.findByEmail("user6@mail");

    Advert advert1 = Advert.builder()
            .name("Advert #1")
            .description("Description #1")
            .price(new BigDecimal("1000"))
            .categories(List.of(categoryRepository.findByName("headphones")))
            .images(List.of(item1Image1, item1Image2, item1Image3))
            .isModerated(true)
            .isModerateAccept(true)
            .moderatedRejectReason(null)
            .isPretendentToBeDeleted(false)
            .build();

    Advert advert2 = Advert.builder()
            .name("Advert #2")
            .description("Description #2")
            .price(new BigDecimal("2000"))
            .categories(List.of(categoryRepository.findByName("headphones")))
            .images(List.of(item2Image1, item2Image2, item2Image3))
            .isModerated(true)
            .isModerateAccept(true)
            .moderatedRejectReason(null)
            .isPretendentToBeDeleted(false)
            .build();

    Advert advert3 = Advert.builder()
            .name("Advert #3")
            .description("Description #3")
            .price(new BigDecimal("3000"))
            .categories(List.of(categoryRepository.findByName("headphones")))
            .images(List.of(item3Image1, item3Image2, item3Image3))
            .isModerated(true)
            .isModerateAccept(false)
            .moderatedRejectReason("moderatedRejectReason")
            .isPretendentToBeDeleted(false)
            .build();

//    advert1.setUser(user4_advert);
//    advert2.setUser(user5_advert);
//    advert3.setUser(user6_advert);
//
//    advertRepository.save(advert1);
//    advertRepository.save(advert2);
//    advertRepository.save(advert3);

    }
}
//-------------------------------------------------------------------------------------
