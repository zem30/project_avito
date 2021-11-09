package com.amr.project.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
@Builder
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_index")
    private String cityIndex;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "country_id",referencedColumnName = "id")
    private Country country;

  //  @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @Column
    private String street;

    @Column
    private String house;

    @JsonIgnore
    @OneToOne(mappedBy = "address", cascade = {CascadeType.PERSIST})
    private User user;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", cityIndex='" + cityIndex + '\'' +
                ", country=" + country +
                ", city=" + city +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                '}';
    }
}
