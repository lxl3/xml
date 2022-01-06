package com.lixinlin.xml.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String id;
    private String category;
    private String ISBN;
    private String title;
    private String author;
    private String press;
    private Double price;
}
