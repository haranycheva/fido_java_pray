package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quotes") 
public class Quote{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "text", nullable = false)
    private String text;

    public Quote() {}

    public Quote(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}