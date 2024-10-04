import java.util.Scanner;

public class BookDriver {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Book Storing Program!");

        while (true) {
            System.out.print("Enter a title for a Book or type (quit): ");
            String title = scan.nextLine();
            if (title.equalsIgnoreCase("quit"))
                break;
            System.out.print("Enter the author: ");
            String author = scan.nextLine();
            System.out.print("Enter the genre: ");
            String genre = scan.nextLine();
            System.out.print("Enter the ISBN: ");
            String ISBN = scan.nextLine();
            System.out.print("Enter the price: ");
            System.out.print("Enter the price: ");
            String input = scan.nextLine(); // Read the input as a string
            float price = 0;
            try {
                price = Float.parseFloat(input); // Convert the string to float
                System.out.println("The price is: " + price);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            Book newBook = new Book(title, author, genre, ISBN, price);

            if (BookDatabaseManager.saveToDatabase(newBook))
                System.out.println("Successfully saved to database: " + newBook);
            else
                System.out.println("Failed to save to database.");
        }

    }
}