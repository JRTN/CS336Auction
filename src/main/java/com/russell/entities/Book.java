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

    enum Genre {
        ActionAndAdventure("Action and adventure"),
        ArtArchitecture("Art/architecture"),
        AlternateHistory("Alternate history"),
        AutoBiography("Autobiography"),
        Anthology("Anthology"),
        Biography("Biography"),
        ChickLit("Chick lit"),
        BusinessEconomics("Business/economics"),
        Childrens("Children's"),
        CraftsHobbies("Crafts/hobbies"),
        Classic("Classic"),
        Cookbook("Cookbook"),
        ComicBook("Comic book"),
        Diary("Diary"),
        ComingOfAge("Coming-of-age"),
        Dictionary("Dictionary"),
        Crime("Crime"),
        Encyclopedia("Encyclopedia"),
        Drama("Drama"),
        Guide("Guide"),
        FairyTale("Fairytale"),
        HealthFitness("Health/fitness"),
        Fantasy("Fantasy"),
        History("History"),
        GraphicNovel("Graphic novel"),
        HomeAndGarden("Home and garden"),
        HistoricalFiction("Historical fiction"),
        Humor("Humor"),
        Horror("Horror"),
        Journal("Journal"),
        Mystery("Mystery"),
        Math("Math"),
        ParanormalRomance("Paranormal romance"),
        Memoir("Memoir"),
        PictureBook("Picture book"),
        Philosophy("Philosophy"),
        Poetry("Poetry"),
        Prayer("Prayer"),
        PoliticalThriller("Political thriller"),
        Religion("Religion"),
        Spirituality("spirituality"),
        Romance("Romance"),
        Textbook("Textbook"),
        Satire("Satire"),
        ScienceFiction("Science fiction"),
        Review("Review"),
        ShortStory("Short story"),
        Science("Science"),
        Suspense("Suspense"),
        SelfHelp("Self help"),
        Thriller("Thriller"),
        SportsAndLeisure("Sports and leisure"),
        Western("Western"),
        Travel("Travel"),
        YoungAdult("Young adult"),
        TrueCrime("True crime");

        public final String genre;

        Genre(String genre) {
            this.genre = genre;
        }
    }

    private String isbn;
    private SubCategory subcategory;
    private int pages;
    private String author;
    private String publisher;
    private Genre genre;
    private Date publicationDate;


    public Book(String isbn, SubCategory subcategory, int pages, String author,
                String publisher, Genre genre, Date publicationDate) {
        this.isbn = isbn;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
