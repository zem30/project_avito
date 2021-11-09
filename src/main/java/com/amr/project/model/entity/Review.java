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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

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
    private String dignity; //плюсы
    private String flaw; //минусы
    private String text;
    private Date date;
    private Integer rating;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
}
