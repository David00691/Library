package pl.java.david.library;


    import org.junit.jupiter.api.Assertions;
    import org.junit.jupiter.api.Test;
    import pl.java.dawid.library.database.BookDB;
    import pl.java.dawid.library.model.Book;

    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.List;

    import static org.junit.jupiter.api.Assertions.assertThrows;
    import static org.junit.jupiter.api.Assertions.assertTrue;

public class Booktest {

        BookDB bookDB = BookDB.getInstance();

        @Test
        public void accessAddingBook() {
            Book testBook = new Book("ogry", "pan jan", LocalDate.now(), 1 , "1215", "\n ");
            assertTrue(bookDB.add_book_indb(testBook));

        }
        @Test
        public void failedBorrowBookByID() {
            Boolean actual = bookDB.rentbook("Pan Tadeusz", 2 , "\n" );
            Boolean expectedResult = false;
            Assertions.assertEquals(expectedResult,actual);
        }

        @Test
        public void failedSerchBook() {
            String title =  "is not null" ;
            ArrayList result = bookDB.serch_books(title);
            Assertions.assertTrue(result.isEmpty());

        }


        @Test
        public void ShowAllBooks() {
            List<Book> books = bookDB.getAllBooks();
            Assertions.assertFalse(books.isEmpty());
        }
}

