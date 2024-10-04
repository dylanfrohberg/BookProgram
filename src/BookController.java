import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.lang.String;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookController implements Initializable {
    @FXML
    public Button btnSubmit;
    @FXML
    public Button btnExit;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayBooks();
    }

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtAuthor;
    @FXML
    private TextField txtGenre;
    @FXML
    private TextField txtISBN;
    @FXML
    private TextField txtPrice;
    @FXML
    private Label lblSuccess;
    @FXML
    private WebView webView;

    /*--METHODS--*/
    //Exit button
    @FXML
    private void ExitButton() {
        Platform.exit();
    }

    //Submit button
    @FXML
    private void SubmitButton() {

        //Get input from textboxes, clear beginning and end spaces
        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String genre = txtGenre.getText().trim();
        String ISBN = txtISBN.getText().trim();
        float Price = 0;
        try {
            Price = Float.parseFloat(txtPrice.getText().trim());
        } catch (NumberFormatException e) {
            // Handle the exception if the input is not a valid float
            lblSuccess.setText("Invalid price entered.");
            return;
        }
        try {
            //Create new URL object
            Book newBook = new Book(title, author, genre, ISBN, Price);

            //Validates URL with testUrl method
            Book.isValidISBN(ISBN);

            //save to database
            boolean isSaved = BookDatabaseManager.saveToDatabase(newBook);

            if (isSaved) {
                //clear fields if saved correctly
                txtTitle.clear();
                txtAuthor.clear();
                txtGenre.clear();
                txtISBN.clear();
                txtPrice.clear();
                System.out.println("Successfully added!");
                lblSuccess.setTextFill(Color.GREEN);
                lblSuccess.setText("Book Added Successfully!");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("OOPS!");
            System.out.println("Whoops! It failed");
            lblSuccess.setText("Book Failed to SAVE! Check your ISBN");
            lblSuccess.setTextFill(Color.RED);
        }
        //Update webView
        displayBooks();
    }
    //FETCH FROM DATABASE
    @FXML
    private void displayBooks() {
        List<Book> bookList = BookDatabaseManager.getAllBooksFromDatabase();

        StringBuilder htmlContent = new StringBuilder();

        htmlContent.append("<!DOCTYPE html><html><head><title>Book List</title></head><body><style>h1 { text-align: center; } body { overflow-y: scroll; }  li { margin-bottom: 10px; border-bottom: 1px solid #222; }</style>");
        htmlContent.append("<h1>Books List</h1><ul>");

        for (Book book : bookList) {
            try {
                Book bookObj = new Book(book.getTitle(), book.getAuthor(), book.getGenre(), book.getISBN(), book.getPrice());
                htmlContent.append("<li>")
                        .append("<b>Title: </b>").append(bookObj.getTitle())
                        .append(" | <b>Author: </b>").append(bookObj.getAuthor())
                        .append(" | <b>Genre: </b>").append(bookObj.getGenre())
                        .append(" | <b>ISBN: </b>").append(bookObj.getISBN())
                        .append(" | <b>Price: </b>").append(bookObj.getPrice())
                        .append("</li>");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid ISBN: " + book.getISBN());
            }
        }
        htmlContent.append("</ul></body></html>");
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(htmlContent.toString());
        System.out.println(bookList);
    }
}
