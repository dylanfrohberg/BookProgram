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

    //Add ISBN validation with regex


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
        this.ISBN = ISBN;
    }
    public Float getPrice(){
        return price;
    }
    public void setPrice(){
        this.price = price;
    }

    //Add toString() for outputting a string representation of the object if necessary














}