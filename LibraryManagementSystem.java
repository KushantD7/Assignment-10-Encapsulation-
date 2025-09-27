import java.util.ArrayList;
abstract class LibraryItem {
    private int itemId;      
    private String title;
    private String author;
    private String borrower;  

    public LibraryItem(int itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
        this.borrower = null; 
    }

    
    public int getItemId() { return itemId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    
    protected void setBorrower(String borrower) { this.borrower = borrower; }
    public String getBorrower() { return borrower; }

 
    public void getItemDetails() {
        System.out.println("Item ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        if (borrower != null) {
            System.out.println("Currently borrowed by: " + borrower);
        } else {
            System.out.println("Currently available.");
        }
    }

    
    public abstract int getLoanDuration();
}


interface Reservable {
    void reserveItem(String borrowerName);
    boolean checkAvailability();
}


class Book extends LibraryItem implements Reservable {
    private boolean isReserved;

    public Book(int itemId, String title, String author) {
        super(itemId, title, author);
        this.isReserved = false;
    }

    @Override
    public int getLoanDuration() {
        return 14; // Books can be borrowed for 14 days
    }

    @Override
    public void reserveItem(String borrowerName) {
        if (!isReserved) {
            isReserved = true;
            setBorrower(borrowerName);
            System.out.println("Book reserved by " + borrowerName);
        } else {
            System.out.println("Book is already reserved.");
        }
    }

    @Override
    public boolean checkAvailability() {
        return !isReserved;
    }
}


class Magazine extends LibraryItem implements Reservable {
    private boolean isReserved;

    public Magazine(int itemId, String title, String author) {
        super(itemId, title, author);
        this.isReserved = false;
    }

    @Override
    public int getLoanDuration() {
        return 7; // Magazines for 7 days
    }

    @Override
    public void reserveItem(String borrowerName) {
        if (!isReserved) {
            isReserved = true;
            setBorrower(borrowerName);
            System.out.println("Magazine reserved by " + borrowerName);
        } else {
            System.out.println("Magazine is already reserved.");
        }
    }

    @Override
    public boolean checkAvailability() {
        return !isReserved;
    }
}


class DVD extends LibraryItem implements Reservable {
    private boolean isReserved;

    public DVD(int itemId, String title, String author) {
        super(itemId, title, author);
        this.isReserved = false;
    }

    @Override
    public int getLoanDuration() {
        return 3; // DVDs for 3 days
    }

    @Override
    public void reserveItem(String borrowerName) {
        if (!isReserved) {
            isReserved = true;
            setBorrower(borrowerName);
            System.out.println("DVD reserved by " + borrowerName);
        } else {
            System.out.println("DVD is already reserved.");
        }
    }

    @Override
    public boolean checkAvailability() {
        return !isReserved;
    }
}


public class LibraryManagementSystem {
    public static void main(String[] args) {
        ArrayList<LibraryItem> libraryItems = new ArrayList<>();

        LibraryItem item1 = new Book(101, "Java Programming", "James Gosling");
        LibraryItem item2 = new Magazine(102, "Tech Monthly", "John Doe");
        LibraryItem item3 = new DVD(103, "Inception", "Christopher Nolan");

        libraryItems.add(item1);
        libraryItems.add(item2);
        libraryItems.add(item3);

        
        for (LibraryItem item : libraryItems) {
            System.out.println("\nItem Details:");
            item.getItemDetails();
            System.out.println("Loan Duration: " + item.getLoanDuration() + " days");

            if (item instanceof Reservable) {
                Reservable r = (Reservable) item;
                if (r.checkAvailability()) {
                    r.reserveItem("Aman");
                } else {
                    System.out.println("Item not available for reservation.");
                }
            }
            System.out.println("----------------------------");
        }
    }
}
