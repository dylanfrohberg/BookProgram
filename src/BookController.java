import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.lang.String;
import java.net.URL;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    private void btnExit() {
        Platform.exit();
    }

    //Submit button
    @FXML
    private void btnSubmit() {

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
                lblSuccess.setText("URL Added Successfully!");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("OOOPS!");
            System.out.println("Whoops! It failed");
            lblSuccess.setText("Book Failed to SAVE!");
            lblSuccess.setTextFill(Color.RED);
        }
    }
}
