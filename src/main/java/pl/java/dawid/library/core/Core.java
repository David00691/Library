package pl.java.dawid.library.core;

import pl.java.dawid.library.database.BookDB;
import pl.java.dawid.library.gui.GUI;
import pl.java.dawid.library.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Core {
    final BookDB bookDB = BookDB.getInstance();
    final Authenticator authenticator = Authenticator.getInstance();
    final GUI gui = GUI.getInstance();
    private static final Core instance = new Core();

    private Core() {

    }
    public void start() {
        boolean isRunning = false;
        int counter = 0;

        while(!isRunning && counter < 3) {
            this.authenticator.authenticate(this.gui.readLoginAndPassword());
            isRunning = this.authenticator.getLoggedUser() != null;
            if (!isRunning) System.out.println("Authentication failed");
            counter++;
        }

        while(isRunning) {
            String choosed = this.gui.showMenu();
            System.out.println("Wybrales: " + choosed);
            switch (choosed) {
                case "1":
                    this.gui.productList();
                    break;
                case "2":
                    System.out.println("prosze podaj autor tytuł isbn");
                    Scanner scanner = new Scanner(System.in);
                    String word = scanner.nextLine();
                    ArrayList findbooks;
                    findbooks = this.bookDB.serch_books(word);
                    System.out.println( "wybierz ksiazke po tytule");
                    System.out.println(findbooks);
                    String title = scanner.nextLine();
                    System.out.println( "wpisz imie i nazwisko");
                    String Name_surname = scanner.nextLine();
                    this.bookDB.rentbook(title,this.authenticator.getLoggedUser().getid(), Name_surname);


                    break;
               case "3":
                   if(this.authenticator.getLoggedUser() != null &&
                           this.authenticator.getLoggedUser().getRole() == User.Role.ADMIN) {
                       this.bookDB.addbook(this.gui.readNewProduct());
                   }
                case "4":
                    if (this.authenticator.getLoggedUser() != null &&
                            this.authenticator.getLoggedUser().getRole() == User.Role.ADMIN) {
                        this.gui.productRentedList();
                }


                    break;
                case "5":
                    if (this.authenticator.getLoggedUser() != null &&
                            this.authenticator.getLoggedUser().getRole() == User.Role.ADMIN) {
                        this.gui.booktimeList();
                    }
                    break;
                case "6":
                    if(this.authenticator.getLoggedUser() != null &&
                            this.authenticator.getLoggedUser().getRole() == User.Role.ADMIN) {
                      User user= this.authenticator.userDB.findByLogin(gui.readName());
                      user.setRole(User.Role.ADMIN);
                      user.showUser();
                    }
                    break;

                case "9":
                     isRunning = false;
                     break;
                default:
                    System.out.println("Wrong choose !!");
                    break;
            }
        }
    }
    public void buyProduct() {
        String name = this.gui.readName();
           // if (this.BookDB.sellProduct(name) == true)
        // {
                System.out.println("Operation successfully");
          //  }
           // else
            //    System.out.println(("operation failed"));
        //System.out.println("StockCounter is:");
          //     this.gui.showProduct(BookDB.getProduct(name));
    }



    public static Core getInstance() {
        return instance;
    }
    }
