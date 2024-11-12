import components.list.List;
import components.list.List1L;
import components.map.Map;
import components.map.Map1L;
import components.stack.Stack;
import components.stack.Stack1L;

public abstract class TBRListSecondary implements TBRList {
    /*
     * Public instance variables
     */
    public Map<String, Map<String, String>>[][] bookShelf;
    public static final int DEFAULT_ROW_SIZE_OF_SHELF = 50;
    public int numberOfBooksToRead;
    public int numberOfBooksCompleted;
    public int numberOfBooksInProgress;
    public Stack<Map<String, String>> listOfBooksInProgress;
    public int numberOfBooksDNF;
    public String genreOfLastBookRead;
    public boolean goalReached;

    @Override
    public int hashCode() {

    }

    @Override
    public String toString() {

    }

    public boolean equals() {

    }

    public boolean shelfContainsBook(String genre, String title) {
        Map<String, String> booksOfGenre = this
                .removeRowFromShelf(genre.hashCode());
    }

    public void replaceBookSameGenre(String genre, String oldTitle,
            String newTitle) {
        this.removeBookFromShelf(genre, oldTitle);
        this.addBookToShelf(genre, newTitle);
    }

    String recommendedNextBookTitle() {
        String genre = this.removeFromListOfBooksInProgress();
        this.addToListOfBooksInProgress();
        Map<String, String>[] booksOfGenre = this
                .removeRowFromShelf(genre.hashCode());
        Map.Pair<K, V> book = booksOfGenre.removeAnyBook();
        return book.title();
    }

    String recommendedBookGenre() {
        //Returns the most popular genre in the list of books in progress
    }

    String recommendedAuthor() {

    }
}
