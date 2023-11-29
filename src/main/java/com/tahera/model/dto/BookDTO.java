package com.tahera.model.dto;

public class BookDTO {

    private Long id;
    private String title;
    private int rate;
    private boolean isRead;
    private String author;

    public BookDTO(Long id, String title, int rate, boolean isRead, String author) {
        this.id = id;
        this.title = title;
        this.rate = rate;
        this.isRead = isRead;
        this.author = author;
    }

    public BookDTO(Long id) {
        this.id = id;
    }

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

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
