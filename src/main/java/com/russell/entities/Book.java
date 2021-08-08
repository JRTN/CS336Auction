package com.russell.entities;

import java.util.Date;

public class Book {

    enum SubCategory {
        FICTION("FICTION"),
        NONFICTION("NONFICTION"),
        MAGAZINE("MAGAZINE");

        public final String category;

        SubCategory(String category) {
            this.category = category;
        }
    }

    private String isbn;
    private String title;
    private SubCategory subcategory;
    private int pages;
    private String author;
    private String publisher;
    private String genre;
    private Date publicationDate;


    public Book(String isbn, String title, SubCategory subcategory, int pages, String author,
                String publisher, String genre, Date publicationDate) {
        this.isbn = isbn;
        this.title = title;
        this.subcategory = subcategory;
        this.pages = pages;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SubCategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubCategory subcategory) {
        this.subcategory = subcategory;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
