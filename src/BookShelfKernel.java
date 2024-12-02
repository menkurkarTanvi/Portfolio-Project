package src;

import java.util.Map;

public interface BookShelfKernel {
    public Map<String, Map<String, String>>[][] displayShelf();

    public boolean isEmpty();

    public int numBooksInRow(int row);

    public int lengthOfShelf();

    public void updateShelfSize(int newRowSize, int newColSize);

    public Map<String, Map<String, String>> removeAnyBook();

    public boolean shelfContainsBooks(String genre, String title);

    public void addBookToShelf(String genre, String title, String author);

    public Map<String, Map<String, String>> removeBookFromShelf(String genre,
            String title);

    public void removeFromListOfBooksInProgress(
            Map<String, Map<String, Integer>> book);

    public Map<String, Map<String, String>> removeFromListOfBooksInProgress();

    public void addToListOfBooksInProgress(
            Map<String, Map<String, Integer>> book);

    public String genre(Map<String, Map<String, String>> book);

    public Map<String, String> titleAuthor(
            Map<String, Map<String, String>> book);

    public String title(Map<String, String> titleAuthor);

    public String author(Map<String, String> titleAuthor);

    public void createNewGoal(int numBooksToRead);
}
