// App/Main class

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        //creating Library object
        Library library = new Library();
        //Loading books and users from saved files
        ArrayList<Book> LibraryBooks = FileHandling.loadBooks("books.txt");
        ArrayList<User> users = FileHandling.loadUsers("users.txt");
        //Updating the arraylist of books and users as per the updated file
        Library.setBooksList(LibraryBooks);
        Library.setUsers(users);
        //Calling the menu method
        library.menu(); 
    }
}