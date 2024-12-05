package components.bookshelf;

import components.map.Map;

public abstract class BookShelfSecondary implements BookShelf {

    @Override
    public int hashCode() {
        int len = this.toString().length();
        int n = 31;
        int hashCode = 0;
        int i = 0;
        while (len > 0) {
            char c = this.toString().charAt(i);
            hashCode += c * (Math.pow(n, len - 1));
            n--;
            len--;
            i++;
        }

        return hashCode;
    }

    @Override
    public String toString() {
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
        if (!(obj instanceof BookShelf)) {
            return false;
        }
        BookShelf1L b = (BookShelf1L) obj;
        if ((this.isEmpty() && !b.isEmpty())
                || (!this.isEmpty() && b.isEmpty())) {
            return false;
        }
        if (b.sizeOfShelf() != this.sizeOfShelf()) {
            return false;
        }
        if (b.lengthOfShelf() != this.lengthOfShelf()) {
            return false;
        }
        if (this.isEmpty() && b.isEmpty()) {
            return true;
        }
        boolean areEqual = true;
        while (!b.isEmpty() && areEqual) {
            Map<String, Map<String, String>> book = b.removeAnyBook();
            while (book.size() > 0 && areEqual) {
                Map.Pair<String, Map<String, String>> p = book.removeAny();
                Map.Pair<String, String> p2 = p.value().removeAny();
                if (!this.shelfContainsBooks(p.key(), p2.key())) {
                    areEqual = false;
                }
                b.addBookToShelf(p.key(), p2.key(), p2.value());
            }
        }
        return areEqual;
    }

    @Override
    public boolean hasGenre(String genre) {
        boolean hasGenre = false;
        if (this.numBooksInRow((genre.hashCode())) > 0) {
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
                .removeBookFromRow(this.genre(book).hashCode());
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
                Map<String, Map<String, String>> book = this
                        .removeBookFromRow(i);
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
