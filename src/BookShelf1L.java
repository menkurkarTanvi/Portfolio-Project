package src;

import components.map.Map;
import components.map.Map1L;
import components.stack.Stack;

/**
 * {@code Bookshelf} represented as as a two dimensional Map array where the
 * value of the map is also a map. Each map object store the genre, title, and
 * author of the book
 *
 * @convention this.bookShelf is an array of Map objects that contain the genre
 *             of the book and another map object that contains the title and
 *             author of the book.
 * @correspondence this = this.bookShelf
 *
 */
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
    private Map<String, Map<String, String>>[][] bookShelf;
    /*
     * List of Books Currently In Progress
     */
    /*
     * Keeps track of the number of books in the shelf
     */
    private int numberOfBooksInShelf = 0;
    /*
     * Stores the books that the user is currently reading
     */
    private Stack<Map<String, Map<String, String>>> listOfBooksInProgress;
    /*
     * Number of Books the User Wants to Read
     */
    private int numberOfBooksToRead;
    /*
     * Number of Books the user has read
     */
    private int numberOfBooksRead;
    /*
     * Number of books the user is currently reading
     */
    private int numberOfBooksInProgress;
    /*
     * Whether the user has reached their goal
     */
    private boolean goalReached;

    /**
     * Creator of initial representation.
     */
    @SuppressWarnings("unchecked")
    private void createNewRep(int rowSize, int colSize, int numBooksToRead) {

        this.bookShelf = new Map1L[rowSize][colSize];
        for (int i = 0; i < this.bookShelf.length; i++) {
            for (int j = 0; j < this.bookShelf[0].length; j++) {
                this.bookShelf[i][j] = new Map1L<>();
            }
        }
        this.numberOfBooksRead = numBooksToRead;

    }
    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public BookShelf1L() {

        this.createNewRep(DEFAULT_ROW_SIZE, DEFAULT_COLUMN_SIZE, 0);

    }

    /**
     * Constructor creates a array based on specified rows and columns and
     * initializes the number of books the user wants to read
     *
     * @param rowSize
     *            length of the rows of bookShelf
     * @param colSize
     *            length of columns of bookShelf
     * @ensures this = {}
     */
    public BookShelf1L(int rowSize, int colSize, int numBooksToRead) {

        this.createNewRep(rowSize, colSize, numBooksToRead);

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final BookShelf1L newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep(DEFAULT_ROW_SIZE, DEFAULT_COLUMN_SIZE, 0);
    }

    @Override
    public final void transferFrom(BookShelf source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof BookShelf1L : ""
                + "Violation of: source is of dynamic type BookShelf1L<?,?>";

        BookShelf1L localSource = (BookShelf1L) source;
        this.bookShelf = localSource.bookShelf;
        this.numberOfBooksToRead = localSource.numberOfBooksToRead;
        localSource.createNewRep(DEFAULT_ROW_SIZE, DEFAULT_COLUMN_SIZE, 0);
    }

    @Override
    public Map<String, Map<String, String>>[][] displayShelf() {
        return this.bookShelf;
    }

    @Override
    public boolean isEmpty() {
        boolean shelfEmpty = true;
        for (int i = 0; i < this.bookShelf.length; i++) {
            for (int j = 0; j < this.bookShelf[0].length; j++) {
                if (this.bookShelf[i][j].size() > 0) {
                    shelfEmpty = false;
                }
            }
        }
        return shelfEmpty;
    }

    @Override
    public int numBooksInRow(int row) {
        int numBooksInRow = 0;
        boolean emptyBook = false;
        for (int i = 0; i < this.bookShelf[row].length && !emptyBook; i++) {
            Map<String, Map<String, String>> book = this.bookShelf[row][i];
            if (book.size() == 0) {
                emptyBook = true;
            } else {
                numBooksInRow++;
            }
        }
        return numBooksInRow;
    }

    @Override
    public int lengthOfShelf() {
        return this.bookShelf[0].length;
    }

    @Override
    public void updateShelfSize(int newRowSize, int newColSize) {
        BookShelf b = new BookShelf1L(newRowSize, newColSize,
                this.numberOfBooksToRead);
        for (int i = 0; i < this.bookShelf.length; i++) {
            for (int j = 0; i < this.bookShelf[0].length; j++) {
                b.bookShelf[i][j] = this.bookShelf[i][j];
            }
        }
        this.transferFrom(b);

    }

    @Override
    public Map<String, Map<String, String>> removeAnyBook() {
        Map<String, Map<String, String>> book = new Map1L<>();
        while (book.size() == 0) {
            int row = (int) (Math.random() * this.bookShelf.length);
            int col = (int) (Math.random() * this.bookShelf[0].length);
            book.transferFrom(this.bookShelf[row][col]);
        }
        return book;
    }

    @Override
    public Map<String, Map<String, String>> removeBookFromRow(int row) {
        Map<String, Map<String, String>> book = new Map1L<>();
        while (book.size() == 0) {
            int col = (int) (Math.random() * this.bookShelf[0].length);
            book.transferFrom(this.bookShelf[row][col]);
        }
        return book;
    }

    @Override
    public boolean shelfContainsBooks(String genre, String title) {
        boolean containsBook = false;
        int row = genre.hashCode();
        int col = title.hashCode();
        Map<String, Map<String, String>> book = this.bookShelf[row][col];
        if (book.size() > 0 && book.removeAny().key().equals(title)) {
            containsBook = true;
        }
        return containsBook;
    }

    @Override
    public void addBookToShelf(String genre, String title, String author) {
        int row = genre.hashCode();
        int col = title.hashCode();
        Map<String, String> titleAuthor = new Map1L<>();
        titleAuthor.add(title, author);
        Map<String, Map<String, String>> book = this.bookShelf[row][col];
        book.add(genre, titleAuthor);
        this.numberOfBooksInShelf++;

    }

    @Override
    public Map<String, Map<String, String>> removeBookFromShelf(String genre,
            String title) {
        int row = genre.hashCode();
        int col = title.hashCode();
        Map<String, Map<String, String>> book = this.bookShelf[row][col];
        //Add empty spot
        this.bookShelf[row][col] = new Map1L<String, Map<String, String>>();
        this.numberOfBooksInShelf--;
        return book;
    }

    @Override
    public void removeFromListOfBooksInProgress(
            Map<String, Map<String, Integer>> book) {
        assert this.listOfBooksInProgress
                .length() > 0 : "Violation of: There are books in listOfBooksInProgress";
        boolean removed = false;
        Stack<Map<String, Map<String, String>>> s = this.listOfBooksInProgress
                .newInstance();
        while (this.listOfBooksInProgress.length() > 0 && !removed) {
            Map<String, Map<String, String>> b = this.listOfBooksInProgress
                    .pop();
            if (b.equals(book)) {
                removed = true;
            } else {
                s.push(b);
            }
        }
        while (s.length() > 0) {
            Map<String, Map<String, String>> b2 = s.pop();
            this.listOfBooksInProgress.push(b2);
        }
        this.numberOfBooksInProgress--;
        this.numberOfBooksRead++;
        this.numberOfBooksToRead--;
        if (this.numberOfBooksRead == this.numberOfBooksToRead) {
            this.goalReached = true;
        }
    }

    /*
     * Removes the first book in list of books in progress
     */
    @Override
    public Map<String, Map<String, Integer>> removeFromListOfBooksInProgress() {
        assert this.listOfBooksInProgress
                .length() > 0 : "Violation of: There are books in listOfBooksInProgress";
        this.numberOfBooksInProgress--;
        this.numberOfBooksRead++;
        this.numberOfBooksToRead--;
        if (this.numberOfBooksRead == this.numberOfBooksToRead) {
            this.goalReached = true;
        }
    }

    @Override
    public void addToListOfBooksInProgress(
            Map<String, Map<String, String>> book) {
        this.listOfBooksInProgress.push(book);
        this.numberOfBooksInProgress++;
    }

    @Override
    public String genre(Map<String, Map<String, String>> book) {
        return book.removeAny().key();
    }

    @Override
    public Map<String, String> titleAuthor(
            Map<String, Map<String, String>> book) {
        return book.removeAny().value();
    }

    @Override
    public String title(Map<String, String> titleAuthor) {
        return titleAuthor.removeAny().key();
    }

    @Override
    public String author(Map<String, String> titleAuthor) {
        return titleAuthor.removeAny().value();
    }

    @Override
    public void createNewGoal(int numBooksToRead) {
        this.numberOfBooksToRead = numBooksToRead;
        this.goalReached = false;
    }

    public int numberOfBooksInShelf() {
        return this.numberOfBooksInShelf;
    }

}
