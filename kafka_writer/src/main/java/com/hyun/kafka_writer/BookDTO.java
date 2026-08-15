package com.hyun.kafka_writer;

import lombok.Data;

@Data
public class BookDTO {
    private String title;
    private String author;
    private String category;
    private int pages;
    private int price;
    private String published_date;
    private String description;
}
