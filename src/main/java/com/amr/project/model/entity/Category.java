package com.amr.project.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guid")
    private String guid;

    @Column(name = "category_name", unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "category_items",
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private List<Item> items;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "category_advert")
    private List<Advert> adverts;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
