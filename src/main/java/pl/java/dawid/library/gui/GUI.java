package pl.java.dawid.library.gui;

import pl.java.dawid.library.core.Authenticator;
import pl.java.dawid.library.database.BookDB;
import pl.java.dawid.library.model.*;

import java.time.LocalDate;
import java.util.Scanner;

public class GUI {
    private final Scanner scanner = new Scanner(System.in);
    final Authenticator authenticator = Authenticator.getInstance();
    final BookDB bookDB = BookDB.getInstance();
    private static final GUI instance = new GUI();

    private GUI() {
    }

    public String showMenu() {
        System.out.println("1. Book list");
        System.out.println("2. rent Book");
        System.out.println("3. add books");

        if(this.authenticator.getLoggedUser() != null &&
                this.authenticator.getLoggedUser().getRole() == User.Role.ADMIN) {
            System.out.println("4. rented books");
            System.out.println("5. time out books ");
            System.out.println("6. new status admin");
        }

        System.out.println("9. Exit");
        return scanner.nextLine();
    }

    public void productList() {
        for(Book book : this.bookDB.getProducts()) {
            System.out.println(book);
        }
    }
    public void productRentedList() {
        for (Book book : this.bookDB.getProducts()) {
            if (book.getstatus() != 0)
                System.out.println(book);
        }
    }
        public void booktimeList() {
            for(Book book : this.bookDB.getProducts()) {
                if(book.getDate_rent().isBefore(LocalDate.now()) && book.getstatus()!=0)
                    System.out.println(book);
            }
    }
    public String readName() {
        System.out.println("Name:");
        return this.scanner.nextLine();
    }
    public int readNumber(){
        System.out.println("stockCounter:");
        int n = this.scanner.nextInt();
        this.scanner.nextLine();
        return n;
    }

    public User readLoginAndPassword() {
        User user = new User();
        System.out.println("Login:");
        user.setLogin(this.scanner.nextLine());
        System.out.println("Password:");
        user.setPassword(this.scanner.nextLine());
        return user;
    }


    public Book readNewProduct() {
        System.out.println("title:");
        String title = this.scanner.nextLine();
        System.out.println("author:");
        String author = this.scanner.nextLine();
        System.out.println("isbn:");
        String isbn = this.scanner.nextLine();

                return new Book(  title,  author, LocalDate.now(),  0, isbn,"");

    }
    public void showProduct(Book book){
        if(book != null) {
            System.out.println("Title:");
            System.out.println(book.getTitle());
            System.out.println("Author:");
            System.out.println(book.getAuthor());

        }
    }

    public static GUI getInstance() {
        return instance;
    }
}
