import java.sql.*;
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
    public static List<Book> getAllBooksFromDatabase(){
        List<Book> bookList = new ArrayList<>();
        Connection conn = null;

        try {
            conn = connectToDatabase();
            String query = "SELECT * FROM books";

            try (PreparedStatement statement = conn.prepareStatement(query); ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    String genre = resultSet.getString("genre");
                    String ISBN = resultSet.getString("ISBN");
                    float price = resultSet.getFloat("price");

                    Book bookObj = new Book(title, author ,genre, ISBN, price);
                    bookList.add(bookObj);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return bookList;
    }


}
