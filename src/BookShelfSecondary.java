import java.util.Map;

public abstract class BookShelfSecondary implements BookShelf {

    @Override
    public int hashCode() {
        int len = this.length();
        int n = 31;
        int hashCode = 0;
        while (len > 0) {
            char c = this.substring(i, i + 1);
            hashCode += c * (Math.pow(n, len - 1));
            n--;
            len--;
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
        BookShelf<?> b = (BookShelf<?>) obj;
        boolean areEqual = true;
        while (!b.isEmpty() && areEqual) {
            Map.Pair<String, Map.Pair<String, String>> book = b.removeAnyBook();
            if (!this.shelfContainsBook(book.genre(),
                    book.titleAndAuthor().title)) {
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

    String recommendedNextBookTitle() {
        Map.Pair<String, Map.Pair<String, String>> book = this
                .removeFromListOfBooksInProgress();
        this.addToListOfBooksInProgress(book);
        Map.Pair<String, Map.Pair<String, String>> book2 = booksOfGenre
                .removeAnyBook(book.genre.hashCode());
        return book2.titleAuthor().title();
    }

    String recommendedBookGenre() {
        //Returns the most popular genre in the bookShelf
        int max = 0;
        int i = 0;
        String mostPopularGenre = "None";
        while (i < this.lengthOfShelf()) {
            int len = this.numBooksInRow(i);
            if (len > max) {
                max = len;
                Map.Pair<String, Map.Pair<String, String>> book = this
                        .removeAnyBook(i);
                mostPopular = book.genre();
            }
            i++;
        }
        return mostPopularGenre;
    }

    String recommendedAuthor() {
        Map.Pair<String, Map.Pair<String, String>> book = b.removeAnyBook();
        return book.titleAuthor().author();
    }
}
