package com.fackstoreintract.fackstoreinteract.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@JsonSerialize
public class Product extends BaseModel implements Serializable {

    @ManyToOne
    private Category category;
    private String description;
    private String image;
    private int price;



}
