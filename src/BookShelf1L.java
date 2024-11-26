import java.util.Map;
import java.util.Stack;

public class BookShelf1L extends BookShelfSecondary {

    /**
     * Default size of hash table.
     */
    private static final int DEFAULT_ROW_SIZE = 60;

    /**
     * Default size of hash table.
     */
    private static final int DEFAULT_COLUMN_SIZE = 100;
    /**
     * Users bookShelf.
     */
    private Map.Pair<String, Map.Pair<String, String>>[][] bookShelf;
    /*
     * List of Books Currently In Progress
     */
    private Stack<Map.Pair<String, Map.Pair<String, String>>> listOfBooksInProgress;

    private int numberOfBooksToRead;

    private in numberOfBooksRead;

    private int numberOfBooksInProgress;

    private int numberOfBooksDNF;
    private boolean goalReached;

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public BookShelf1L() {

        this.createNewRep();

    }

    /**
     * Constructor resulting in a hash table of size {@code hashTableSize}.
     *
     * @param hashTableSize
     *            size of hash table
     * @requires hashTableSize > 0
     * @ensures this = {}
     */
    public BookShelf1L(int rowSize, int columnSize) {

        this.createNewRep();

    }

    public void displayShelf() {

    }

    public boolean isEmpty() {

    }

    public int lengthOfShelf() {

    }

    public void updateShelfSize() {

    }

    public Map.Pair<String, Map.Pair<String, String>> removeAnyBook() {

    }

    public int numBooksInRow(int row) {

    }

    public void addBookToShelf(String genre, String, title, String author){

    }

    public Map.Pair<String, Map.Pair<String, String>> removeBookFromShelf(
            String genre, String title) {

    }

    public Map.Pair<String, Map.Pair<String, String>> removeFromListOfBooksInProgress() {
    }

    public void addToListOfBooksInProgress(
            Map.Pair<String, Map.Pair<String, Integer>> book) {

    }

    public String genre() {

    }

    public String title() {
    }

    public Map.Pair<String, String> titleAuthor() {
        return this.value();
    }

    public String author() {
    }

}
