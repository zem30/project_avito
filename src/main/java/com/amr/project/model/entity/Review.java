package com.amr.project.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Введите плюсы для товара")
    private String dignity; //плюсы

    @NotBlank(message = "Введите минусы для товара")
    private String flaw; //минусы

    @NotBlank(message = "Введите отзыв для товара")
    private String text;

    private Date date;

    @Positive()
    @Length(min = 1, max = 5, message = "Поставьте оценку от 1 до 5")
    private Integer rating;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "shop_review", joinColumns = @JoinColumn(name = "review_id"), inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private Shop shop;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> logo;

    @Column
    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;

    @JsonIgnore
    @Transient
    private MultipartFile file;
}
