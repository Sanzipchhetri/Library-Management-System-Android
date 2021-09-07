package com.kunja.librarymanagementsystem.model;

public class Book {
    private  int bookId;
    private  String bookName;
    private  String bookAuthor;
    private  String bookAvailability;



    public Book(int bookId, String bookName, String bookAuthor, String bookAvailability) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookAvailability = bookAvailability;
    }
//    public Book(String bookName, String bookAuthor, String bookAvailability) {
//        this.bookName = bookName;
//        this.bookAuthor = bookAuthor;
//        this.bookAvailability = bookAvailability;
//    }

    public Book() {

    }

    public Book(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookAvailability() {
        return bookAvailability;
    }

    public void setBookAvailability(String bookAvailability) {
        this.bookAvailability = bookAvailability;
    }

    @Override
    public String toString() {
        return bookId +". " + bookName ;

    }
}

