import java.util.regex.*;

public class Book {
    private String title;
    private String author;
    private String genre;
    private String ISBN;
    private Float price;

    public Book(String title, String author, String genre, String ISBN, Float price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        setISBN(ISBN);
        this.price = price;
    }

    public static boolean isValidISBN(String isbn) {

        // Check if ISBN is null
        if (isbn == null) {
            return false;
        }

        // Remove hyphens and any spaces for validation
        String cleanIsbn = isbn.replace("-", "").replaceAll("\\s+", "");

        // Check if cleaned ISBN has a valid length
        if (cleanIsbn.length() != 10 && cleanIsbn.length() != 13) {
            return false;
        }
        // Check if cleaned ISBN contains only digits
        return cleanIsbn.matches("\\d+"); // Return true if only digits
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public String getISBN(){
        return ISBN;
    }
    public void setISBN(String ISBN) {
    if (!isValidISBN(ISBN)) {
        throw new IllegalArgumentException("Invalid ISBN format");
    }
        this.ISBN = ISBN;
    }
    public Float getPrice(){
        return price;
    }
    public void setPrice(Float price){
        this.price = price;
    }

    public String toString() {
        return "Book{" +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", price=" + price +
                '}';
    }














}