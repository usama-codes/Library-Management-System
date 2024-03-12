import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        Library library = new Library();
        ArrayList<Book> books = FileHandling.loadBooks("books.txt");
        ArrayList<User> users = FileHandling.loadUsers("users.txt");
        Library.setBooks(books); // Access the static method in a static way
        Library.setUsers(users);
        library.menu();
    }
}