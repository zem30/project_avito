package com.amr.project.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String email;
    private String phone;
    private String description;
    private int count;
    private double rating;

    @OneToOne(fetch = FetchType.LAZY)
    private Country location;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Item> items;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Order> orders;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> logo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User user;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Collection<Discount> discounts;

    @JsonIgnore
    private boolean isModerated;

    @JsonIgnore
    private boolean isModerateAccept;

    @JsonIgnore
    private String moderatedRejectReason;

    //поле для подтверждения модератором
    @JsonIgnore
    @Column
    private int activity;
    @JsonIgnore
    @Transient
    private MultipartFile file;
    @JsonIgnore
    private boolean isPretendentToBeDeleted = false;

}
