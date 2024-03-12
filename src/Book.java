import java.io.*;

public class Book implements Serializable {
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;

    public Book(int id, String title, String author, String genre) {
        setBookID(id);
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
        isAvailable = true;
    } 
    
    public int getBookID() { return bookID; }

    public String getTitle() { return title; }

    public String getAuthor() { return author; }

    public String getGenre() { return genre; }

     public boolean getBookAvailability() { return isAvailable; }

    public void setBookID(int bookID) { this.bookID = bookID; }

    public void setTitle(String title) { this.title = title; }

    public void setAuthor(String author) { this.author = author; }

    public void setGenre(String genre) { this.genre = genre; }

    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable;}

    public boolean isAvailable() { return isAvailable; }
    
    public void displayBookInfo() {
        System.out.println("Name: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Genre : " + getGenre());
        System.out
                .println("Availability Status: " + (getBookAvailability() ? "Available" : "Unavailable"));
        System.out.println("\t\t__________________________");
    }
}
