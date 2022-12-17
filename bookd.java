package com.example.myapplication;

public class bookd {
    String author;
    String book_id;
    String book_name;
    String copies;
    String dates;
    String usernames;
    bookd(){}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getCopies() {
        return copies;
    }

    public void setCopies(String copies) {
        this.copies = copies;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getUsernames() {
        return usernames;
    }

    public void setUsernames(String usernames) {
        this.usernames = usernames;
    }

    public bookd(String author, String book_id, String book_name, String copies, String dates, String usernames) {
        this.author = author;
        this.book_id = book_id;
        this.book_name = book_name;
        this.copies = copies;
        this.dates = dates;
        this.usernames = usernames;
    }
}
