package pl.java.dawid.library.model;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private LocalDate date_rent;
    private Integer status;
    private String isbn;
    private int iduser;
    private String Name_Surname;


    public Book(String title, String author,
                LocalDate date_rent, int status, String isbn,String Name_Surname) {

        this.title = title;
        this.author = author;
        this.date_rent = date_rent;
        this.status = status;
        this.isbn = isbn;
        this.Name_Surname=Name_Surname;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate_rent() {
        return date_rent;
    }

    public void setDate_rent(LocalDate date_rent) {
        this.date_rent = date_rent;
    }
    public int getstatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public String getisbn() {
        return isbn;
    }

    public void setisbn(String isbn) {
        this.isbn = isbn;
    }
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    public String getName_Surname() {
        return Name_Surname;
    }

    public void setName_Surname(String Name_Surname) {
        this.Name_Surname = Name_Surname;
    }
    @Override
    public String toString() {
        if (status != 0) {
            return new StringBuilder()
                    .append("  ")
                    .append(this.getTitle())
                    .append(" autor : ")
                    .append(this.getAuthor())
                    .append(" \nosoba wypozyczajaca:")
                    .append(this.Name_Surname)
                    .append("\n id user:")
                    .append(this.getIduser())
                    .append(" \n data")
                    .append(this.date_rent)
                    .toString();
        }
        else
            return new StringBuilder()
                    .append("  ")
                    .append(this.getTitle())
                    .append(" autor : ")
                    .append(this.getAuthor())
                    .toString();
    }
}
