import java.util.regex.*;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private String ISBN;
    private Float price;

    public Book(int id, String title, String author, String genre, String ISBN, Float price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        this.price = price;
    }

    private boolean isValidISBN(String isbn) {
        //ISBN-10 Format:
        //Matches 1 to 5 digits followed by hyphens and digits, allowing an 'X' as the last character.
        //Alternatively, matches a straight sequence of 10 digits without hyphens.
        //ISBN-13 Format:
        //Matches 1 to 5 digits followed by hyphens and groups of digits, ensuring a single digit for the check digit.
        //Both formats are anchored to the start and end of the string.
        String isbn10Pattern = "^(?:\\d{1,5}-\\d{1,7}-\\d{1,7}-[\\dX]|\\d{10})$";
        String isbn13Pattern = "^(?:\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d{1}-\\d{1,7})$";

        return Pattern.matches(isbn10Pattern, isbn) || Pattern.matches(isbn13Pattern, isbn);
    }

    public int getId () {
        return id;
    }

    public void setId() {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(){
        this.title = title;
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor(){
        this.author = author;
    }
    public String getGenre(){
        return genre;
    }
    public void setGenre(){
        this.genre = genre;
    }
    public String getISBN(){
        return ISBN;
    }
    public void setISBN() {
    if (!isValidISBN(ISBN)) {
        throw new IllegalArgumentException("Invalid ISBN format");
    }
        this.ISBN = ISBN;
    }
    public Float getPrice(){
        return price;
    }
    public void setPrice(){
        this.price = price;
    }

    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", price=" + price +
                '}';
    }














}