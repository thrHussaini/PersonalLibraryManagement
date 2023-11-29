package com.tahera.model;

import javax.persistence.*;

@Entity
@Table(
    name = "books",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "author"})})
public class Book {
    public Book(Long id, String title, int rate, boolean isRead, String author) {
        this.id = id;
        this.title = title;
        this.rate = rate;
        this.isRead = isRead;
        this.author = author;
    }

    public Book() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "rate")
    private int rate;

    @Column(name = "isread")
    private boolean isRead;

    @Column(name = "author")
    private String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rate='" + rate + '\'' +
                ", isRead=" + isRead +
                ", author=" + author +
                '}';
    }
}
