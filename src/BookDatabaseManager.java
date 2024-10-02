import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookDatabaseManager {

    /*--METHOD TO CONNECT TO DB--*/
    public static Connection connectToDatabase() throws SQLException {
        String books = "jdbc:mysql://192.168.0.21:3306/books";
        String username = "necc";
        String password = "northeast";

        return DriverManager.getConnection(books,username,password);
    }

    /*--METHOD TO SAVE TO DB--*/
    public static boolean saveToDatabase(Book book) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = connectToDatabase();

            String sql = "INSERT INTO books (title, author, genre, ISBN, price) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getGenre());
            pstmt.setString(4, book.getISBN());
            pstmt.setFloat(5, book.getPrice());

            pstmt.executeUpdate();

            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /*--METHOD TO GET DATA FROM DATABASE--*/
    public static List<Book> getAllUrlsFromDatabase(){
        List<Book> urlsList = new ArrayList<>();
        Connection conn = null;

        try {
            conn = connectToDatabase();
            String query = "SELECT * FROM books";

            try (PreparedStatement statement = conn.prepareStatement(query); ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    String genre = resultSet.getString("genre");
                    String isbn = resultSet.getString("ISBN");
                    float price = resultSet.getFloat("price");
// If you want to create a Book object
                    Book book = new Book(id, title, author, genre, isbn, price);
                    urlsList.add(book);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return urlsList;
    }


}
