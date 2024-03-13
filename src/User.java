//User Class

import java.util.ArrayList;
import java.io.Serializable;

public class User implements Serializable {
    private int userID;
    private String userName; 
    private String contactInfo;
    private ArrayList<Book> borrowedBooks = new ArrayList<Book>();

    public User(int userID, String userName, String contactInfo) {
        setUserID(userID);
        setUserName(userName);
        setContactInfo(contactInfo);
    }

    public int getUserID() { return userID; }

    public String getUserName() { return userName; }

    public String getContactInfo() { return contactInfo; }

    public ArrayList<Book> getBorrowedBooks() { return borrowedBooks; }

    public void setUserID(int userID) { this.userID = userID; }

    public void setUserName(String userName) { this.userName = userName; }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) { this.borrowedBooks = borrowedBooks; }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}

