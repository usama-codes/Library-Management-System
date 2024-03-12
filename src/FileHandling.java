import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileHandling {
    public static void saveBooks(List<Book> books, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(books);
            out.close();
            fileOut.close();
            //System.out.printf("Serialized data is saved in " + filename);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void saveUsers(List<User> users, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
            //System.out.printf("Serialized data is saved in " + filename);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static ArrayList<Book> loadBooks(String filename) {
        ArrayList<Book> books = new ArrayList<>(); // Initialize to a new ArrayList
        try {
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Object obj = in.readObject();
                if (obj instanceof ArrayList) {
                    books = (ArrayList<Book>) obj; 
                }
                in.close();
                fileIn.close();
            } else {
                System.out.println("File does not exist");
            }
        } 
        catch (IOException i) {
            System.out.println("Error reading file");
            i.printStackTrace();
        } 
        catch (ClassNotFoundException c) {
        System.out.println("Book class not found");
        c.printStackTrace();
}
return books;
    }

    public static ArrayList<User> loadUsers(String filename) {
        ArrayList<User> users = new ArrayList<>(); // Initialize to a new ArrayList
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            users = (ArrayList<User>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("User class not found");
            c.printStackTrace();
        }
        return users;
    }
}
