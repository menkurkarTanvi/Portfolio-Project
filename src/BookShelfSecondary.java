package src;

import java.util.Map;

public abstract class BookShelfSecondary implements BookShelf {

    @Override
    public int hashCode() {
        int len = this.length();
        int n = 31;
        int hashCode = 0;
        int i = 0;
        while (len > 0) {
            char c = this.substring(i, i + 1);
            hashCode += c * (Math.pow(n, len - 1));
            n--;
            len--;
            i++;
        }

        return hashCode;
    }

    @Override
    public String toString() {
        this.displayShelf();
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BookShelfSecondary)) {
            return false;
        }
        BookShelf b = (BookShelf) obj;
        boolean areEqual = true;
        while (!b.isEmpty() && areEqual) {
            Map<String, Map<String, String>> book = b.removeAnyBook();
            if (!this.shelfContainsBook(this.genre(book),
                    this.title(this.titleAuthor(book)))) {
                areEqual = false;
            }
        }
        return areEqual;
    }

    public boolean hasGenre(String genre) {
        boolean hasGenre = false;
        if (this.numBooksInRow(genre.hashCode()) > 0) {
            hasGenre = true;
        }
        return hasGenre;
    }

    public void replaceBook(String genre, String oldTitle, String newTitle,
            String newAuthor) {
        this.removeBookFromShelf(genre, oldTitle);
        this.addBookToShelf(genre, newTitle, newAuthor);
    }

    @Override
    public String recommendedNextBookTitle() {
        Map<String, Map<String, String>> book = this
                .removeFromListOfBooksInProgress();
        this.addToListOfBooksInProgress(book);
        Map<String, Map<String, String>> book2 = this
                .removeAnyBook(this.genre(book).hashCode());
        return this.title(this.titleAuthor(book2));
    }

    @Override
    public String recommendedBookGenre() {
        //Returns the most popular genre in the bookShelf
        int max = 0;
        int i = 0;
        String mostPopularGenre = "None";
        while (i < this.lengthOfShelf()) {
            int len = this.numBooksInRow(i);
            if (len > max) {
                max = len;
                Map<String, Map<String, String>> book = this.removeAnyBook(i);
                mostPopularGenre = this.genre(book);
            }
            i++;
        }
        return mostPopularGenre;
    }

    @Override
    public String recommendedAuthor() {
        Map<String, Map<String, String>> book = this.removeAnyBook();
        return this.author(this.titleAuthor(book));
    }
}
