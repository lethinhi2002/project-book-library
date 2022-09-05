package project;

import java.io.Serializable;

public class Book implements Serializable {
    public int bookID;
    public String bookName;
    public String about;
    public String author;
    public String price;

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAbout() {
        return about;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public Book(int  id, String bookName, String about, String author, String price){
        this.bookID=id;
        this.bookName = bookName;
        this.about = about;
        this.author = author;
        this.price = price;
    }

    public static void show(Book book){
        System.out.println("Id: "+book.bookID + "  Name: "+book.bookName + "  About: "+book.about
                + "  Author: "+book.author+ "  Price: "+book.price);
    }

}
