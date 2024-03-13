import java.util.Scanner;
import java.util.ArrayList;


public class Library {
    //ArrayLists to store books and users
    private static ArrayList<Book> LibraryBooks = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    // Constructor
    public Library() {
        // Load books and users from file only if the lists are empty
        if (LibraryBooks.isEmpty()) {
            LibraryBooks = FileHandling.loadBooks("books.txt");
        }

        if (users.isEmpty()) {
            users = FileHandling.loadUsers("users.txt");
        }
    }

    public static ArrayList<Book> getLibraryBooks() {
        return LibraryBooks;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setBooksList(ArrayList<Book> books) {
        Library.LibraryBooks = books;
    }

    public static void setUsers(ArrayList<User> users) {
        Library.users = users;
    }
    //Method for adding new user
    public User addUser() {

        System.out.println("ADDING USER: \n");
        int userID;
        //Checking whether the new user is unique or not
        boolean isUnique;
        do {
            isUnique = true;
            userID = Helper.getIntInput("Enter the user ID: ");
            //checking repetition of user ID
            for (User user : users) {
                if (user.getUserID() == userID) {
                    System.out.println("User Already Exists!");
                    isUnique = false;
                    break;
                }
            }
        }
        //prompting user for input as long as a unique ID is not given 
        while (!isUnique);
        String username = Helper.getStringInput("Enter the user name: ");
        String contact = Helper.getContactInfo("Enter the contact number: ");
        //assigning user input values to attributes using class constructor
        User user = new User(userID, username, contact);
        //adding user to the list of users
        users.add(user);
        FileHandling.saveUsers(users, "users.txt");
        System.out.println("User " + user.getUserName() + " added successfully!");
        System.out.println("\t\t----------------------------------------------------------------");

        return user;
    }

    //Method for adding new book
    public Book addBook() {
        System.out.println("ADDING BOOK: \n");
        int bookID;
        boolean isUnique;
        //check for duplicate book ID
        do {
            isUnique = true;
            bookID = Helper.getIntInput("Enter the book ID: ");
            for (Book book : LibraryBooks) {
                if (book.getBookID() == bookID) {
                    System.out.println("Book ID already exists!");
                    isUnique = false;
                }
            }
        } 
        while (!isUnique);
        String bookTitle = Helper.getTitleInput("Enter the title of the book: ");
        String bookAuthor = Helper.getStringInput("Enter the author of the book: ");
        String bookGenre = Helper.getStringInput("Enter the genre of the book: ");
        
        //passing values to object constructor
        Book book = new Book(bookID, bookTitle, bookAuthor, bookGenre);
        LibraryBooks.add(book);
        FileHandling.saveBooks(LibraryBooks, "books.txt");
        System.out.println("\"" + book.getTitle() + "\" added successfully!");
        System.out.println("\t\t----------------------------------------------------------------");
        
        return book;
    }
    
    //Method for borrowing a book
    public void borrowBook() {
        FileHandling.loadBooks("books.txt");
        FileHandling.loadUsers("users.txt");
        System.out.println("BORROWING BOOK: ");
        int bookID = Helper.getIntInput("Enter the book ID:");

        // Search for the book
        Book booktoBorrow = null;
        for (Book book : LibraryBooks) {
            if (book.getBookID() == bookID) {
                booktoBorrow = book;
                break;
            }
        }
        if (booktoBorrow == null) {
            System.out.println("Book not found!");
            System.out.println("\t\t================================================================");
            return;
        }
        // Check if the book was available
        if (!booktoBorrow.getBookAvailability()) {
            System.out.println("Book is not available");
            System.out.println("\t\t================================================================");
            return;
        }

        // prompting user for user ID
        int userID = Helper.getIntInput("Enter the user ID:");

        // Search for the user
        User borrower = null;
        for (User user : users) {
            if (user.getUserID() == userID) {
                borrower = user;
                break;
            }
        }
        // Check if the user was found
        if (borrower == null) {
            System.out.println("User not found!");
            System.out.println("\t\t================================================================");
            return;
        }

         // Changing book availability to false and adding the book to the user's borrowed list //
        borrower.getBorrowedBooks().add(booktoBorrow);
        booktoBorrow.setAvailable(false);

        FileHandling.saveBooks(LibraryBooks, "books.txt");
        FileHandling.saveUsers(users, "users.txt");

        System.out.println("Book \"" + booktoBorrow.getTitle() + "\" successfully borrowed by \""
                + borrower.getUserName() + "\"");
        System.out.println("\t\t----------------------------------");
    }
    //Method for returning a book
    public void returnBook() {
        //Loads books and users from file
        FileHandling.loadBooks("books.txt");
        FileHandling.loadUsers("users.txt");
        boolean userFound = false, bookFound = false;
        System.out.println("RETURNING BOOK: ");
        int returningUserID = Helper.getIntInput("Enter the user ID: ");
        // check for empty users list
        if (users.isEmpty()) {
            System.out.println("No users found!");
            System.out.println("\t\t================================================================");
            return;
        }

        for (User user : users) {
            // checking user ID inputted with available users
            if (user.getUserID() == returningUserID) {
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            System.out.println("User not found!");
            return;
        }
        // now prompting user for book id
        int booktoreturnID = Helper.getIntInput("Enter the book ID: ");
        for (User user : users) {

            if (user.getUserID() == returningUserID) {
                
                for (Book book : user.getBorrowedBooks()) {
                    if (book.getBookID() == booktoreturnID) {
                        bookFound = true;
                        // removing book from user's borrowed books' list
                        user.getBorrowedBooks().remove(book);
                        System.out.println("Book \"" + book.getTitle() + "\" returned successfully by \""
                                + user.getUserName() + "\"");
                        System.out.println("\t\t----------------------------------------------------------------");
                        break;
                    }
                }
                if (!bookFound) {
                    System.out.println("Requested book is not borrowed by this user");
                }
                break;
            }
        }
        for (Book book : LibraryBooks) {
            if (book.getBookID() == booktoreturnID) {
                //changing book availability to true
                book.setAvailable(true);
                break;
            }
        }
                FileHandling.saveBooks(LibraryBooks, "books.txt");
                FileHandling.saveUsers(users, "users.txt");
                return;
}

    //Method for searching a book
    public void searchBook() {
        int choice;
        do {
            //Giving options for searching book
            choice = Helper.getIntInput(
                    "How do you want to search the book?\n 1. By Title \n2. By Author Name \n3. By User ID");
            switch (choice) {
                case 1 -> {
                    String titleToSearch = Helper.getTitleInput("Enter the title you want to search: ");
                    //Method call
                    searchBookByTitle(titleToSearch);
                }
                case 2 -> {
                    String authortoSearch = Helper.getStringInput("Enter the author name you want to search: ");
                    //Method call
                    searchBookByAuthor(authortoSearch);
                }
                case 3 -> {
                    int userIDtoSearch = Helper.getIntInput("Enter the user ID you want to search: ");
                    //Method call
                    searchBookByUserID(userIDtoSearch);
                }
                default -> {
                    //error handling
                    System.out.println("Invalid input. Please try again!");
                }
            }
        }
        //continuously propmting user in case of invalid input
        while (choice < 1 || choice > 3);
    }
    
    //Method for searching book by Author name
    public Book searchBookByAuthor(String authorToSearch) {
        boolean found = false;
        for (Book book : LibraryBooks) {
            //Checking user inputted author name with those available in library(case insensitive) 
            if (book.getAuthor().equalsIgnoreCase(authorToSearch)) {
                System.out.println("Book found!");
                book.displayBookInfo();
                found = true;
                return book;
            }
        }
        //error handling
        if (!found) {
            System.out.println("Book not found!");
        }
        return null;
    }
    //Method for searching book by User ID
    public void searchBookByUserID(int userIDtoSearch) {
        boolean userFound = false;
        for (User user : users) {
            //Checking user inputted user ID with those available in library
            if (user.getUserID() == userIDtoSearch) {
                userFound = true;
            }
        }
        if (!userFound) {
            System.out.println("User not found!");
            return;
        }
        //Check for a user having no book borrowed
        for (User user : users) {
            if (user.getUserID() == userIDtoSearch && user.getBorrowedBooks().isEmpty()) {
                System.out.println("No books borrowed by " + user.getUserName());
                return;
            }
            else if(user.getUserID() == userIDtoSearch && !user.getBorrowedBooks().isEmpty()){
                System.out.println("Books borrowed by " + user.getUserName() + " are:");
                for (Book book : user.getBorrowedBooks()) {
                    FileHandling.loadBooks("books.txt");
                    FileHandling.loadUsers("users.txt");
                    //Method call for displaying book information
                    book.displayBookInfo();
                }
            }
        }
    }

    //Method for searching book by Title
    public Book searchBookByTitle(String titleToSearch) {
        boolean found = false;
        Book foundBook = null;
        for (Book book : LibraryBooks) {
            if (book.getTitle().equalsIgnoreCase(titleToSearch)) {
                System.out.println("Book found!");
                book.displayBookInfo();
                foundBook = book;
                found = true;
                break;
            }
        }
        //error message for invalid book title
        if (!found) {
            System.out.println("Book not found!");
        }
        return foundBook;
    }

    //Method for displaying all books of library
    public void displayBooks() {
        if (LibraryBooks.isEmpty()) {
            System.out.println("No books found!");
            return;
        }
        else {
            System.out.println("Available Books Are: ");
            for (Book book : LibraryBooks) {
                book.displayBookInfo();
            }
        }
    }

    //Method for main menu
    public void menu() {
        int choice;
        do{
            System.out.println("1. Add Book");
            System.out.println("2. Borrow or Check Out Book");
            System.out.println("3. Return Book");
            System.out.println("4. Search Book");
            System.out.println("5. Display Books");
            System.out.println("6. Add User");
            System.out.println("7. Exit");
            choice = Helper.getIntInput("Enter your choice: ");
            switch (choice) {
                //respective method calls
                case 1 -> addBook();
                case 2 -> borrowBook();
                case 3 -> returnBook();
                case 4 -> searchBook();
                case 5 -> displayBooks();
                case 6 -> addUser();
                case 7 -> {
                            FileHandling.saveBooks(LibraryBooks, "books.txt");
                            FileHandling.saveUsers(users, "users.txt");
                            System.exit(0);
                        }
                default -> {
                    //error handling
                    System.out.println("Invalid choice!\n");
                    continue;
                }
            }
        }
        while (true);
}

    public void setBook(int bookID, String newTitle, String newAuthor, String newGenre) {
        for (Book book : LibraryBooks) {
            if (book.getBookID() == bookID) {
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                book.setGenre(newGenre);
                System.out.println("Book with ID " + bookID + " has been updated.");
                return;
            }
        }
        System.out.println("Book with ID " + bookID + " not found.");
    }
}