package pl.java.dawid.library.database;

import pl.java.dawid.library.model.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDB {
    private static final Provider conn = Provider.getInstance();
    private static final Connection connection = conn.connect();
    private static final BookDB instance = new BookDB();
    private  List<Book> Books=null;


    private BookDB() {

        Books=getAllBooks();

    }
    public List<Book> getAllBooks(){
        List<Book> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bookdb.books";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String dateStr = rs.getString("date_rent");
                LocalDate date = (dateStr.isEmpty()) ? LocalDate.now() : LocalDate.parse(dateStr);
                result.add(new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        date,
                        rs.getInt("status"),
                        rs.getString("isbn"),
                        rs.getString("Name_Surname")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public List <Book> getProducts() {
        return Books;
    }

    public boolean rentbook(String name, int iduser, String Name_surname) {
        name = name.trim();
        for (Book book : this.Books) {
            if (book.getTitle().equals(name) && book.getstatus() == 0 ) {
                book.setStatus(1);
                book.setIduser(iduser);
                book.setName_Surname(Name_surname);
                LocalDate now = LocalDate.now();
                book.setDate_rent(now.plusDays(14));
                update_book_indb(book);
                return true;
            }
        }
        return false;
    }
    public boolean update_book_indb (Book book){
        try {

            String sql = "UPDATE books SET date_rent = ?, status = 1, Name_Surname=?, iduser=? WHERE isbn=?;";


            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now().plusDays(14)));
            ps.setString(2, book.getName_Surname());
            ps.setInt(3, book.getIduser());
            ps.setString(4,book.getisbn());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }

    }

    public void addbook(Book book) {
        Books.add(book);
        add_book_indb(book);
    }

    public boolean add_book_indb (Book book){
        try {

            String sql = "INSERT INTO books (title, status, author, isbn, date_rent, iduser,Name_Surname) VALUES(?,0,?,?,?,\"\",\"\")";


            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3,book.getisbn());
            ps.setDate(4, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }

    }
    public static BookDB getInstance() {
        return instance;
    }


    public ArrayList serch_books(String word) {

        ArrayList books = new ArrayList();
        for (int i = 0; i < this.Books.size(); i++) {
            if (this.Books.get(i).getTitle().indexOf(word) >= 0 || (this.Books.get(i).getAuthor().indexOf(word) >= 0) || (this.Books.get(i).getisbn().indexOf(word) >= 0)) {
                books.add(this.Books.get(i));
            }

        }
        return books;
    }
}