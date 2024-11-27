package com.example.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor              //klassi annotatsioonid
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
@ToString

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private int quantity;

    public double calculatetotalPrice() {
        return this.price * this.quantity;
    }
}