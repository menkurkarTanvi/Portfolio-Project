package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.bookshelf.BookShelf;
import components.bookshelf.BookShelf1L;
import components.map.Map;
import components.map.Map1L;

public class BookShelfTestKernel {
    @Test
    public void testIsEmpty() {
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        boolean empty = shelf.isEmpty();
        assertEquals(empty, true);
    }

    @Test
    public void testIsNotEmpty() {
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        shelf.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        BookShelf shelfRef = new BookShelf1L(3, 5, 20);
        shelfRef.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        boolean empty = shelf.isEmpty();
        assertEquals(shelf, shelfRef);
        assertEquals(empty, false);
    }

    @Test
    public void testRemoveBookFromShelf() {
        BookShelf shelfRef = new BookShelf1L(3, 5, 20);
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        shelf.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        Map<String, Map<String, String>> book = shelf
                .removeBookFromShelf("Fantasy", "Oathbringer");
        Map.Pair<String, Map<String, String>> p = book.removeAny();
        String title = p.value().removeAny().key();
        String genre = p.key();
        assertEquals("Fantasy", genre);
        assertEquals(title, "Oathbringer");
        assertEquals(shelf, shelfRef);
    }

    @Test
    public void testnumBooksInRow1() {
        BookShelf shelfRef = new BookShelf1L(3, 5, 20);
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        int bookInRow = shelf.numBooksInRow(0);
        assertEquals(0, bookInRow);
        assertEquals(shelf, shelfRef);
    }

    @Test
    public void testnumBooksInRow3() {
        BookShelf shelfRef = new BookShelf1L(3, 5, 20);
        shelfRef.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        shelfRef.addBookToShelf("Fantasy", "Way of Kings", "Sanderson");
        shelfRef.addBookToShelf("Fantasy", "Words of Radiance", "Sanderson");
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        Map<String, String> titleAuthor = new Map1L<>();
        titleAuthor.add("OathBringer", "Sanderson");
        Map<String, Map<String, String>> book = new Map1L<>();
        book.add("Fantasy", titleAuthor);
        shelf.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        shelf.addBookToShelf("Fantasy", "Way of Kings", "Sanderson");
        shelf.addBookToShelf("Fantasy", "Words of Radiance", "Sanderson");
        int row = shelf.mod(shelf.genre(book).hashCode(), shelf.sizeOfShelf());
        int bookInRow = shelf.numBooksInRow(row);
        assertEquals(bookInRow, 3);
        assertEquals(shelf, shelfRef);
    }

    @Test
    public void testLengthOfShelf() {
        BookShelf shelfRef = new BookShelf1L(3, 5, 20);
        shelfRef.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        shelfRef.addBookToShelf("Fantasy", "Way of Kings", "Sanderson");
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        shelf.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        shelf.addBookToShelf("Fantasy", "Way of Kings", "Sanderson");
        int len = shelf.lengthOfShelf();
        assertEquals(shelf, shelfRef);
        assertEquals(len, 5);
    }

    @Test
    public void sizeOfShelf() {
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        BookShelf shelfRef = new BookShelf1L(3, 5, 20);
        int len = shelf.sizeOfShelf();
        assertEquals(shelf, shelfRef);
        assertEquals(len, 3);
    }

    @Test
    public void updateShelfSize() {
        BookShelf shelfRef = new BookShelf1L(3, 5, 20);
        shelfRef.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        shelf.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        shelf.updateShelfSize(4, 6);
        int shelfLen = shelf.lengthOfShelf();
        int shelfSize = shelf.sizeOfShelf();
        assertEquals(6, shelfLen);
        assertEquals(4, shelfSize);
        shelf.displayShelf();
        shelfRef.displayShelf();
    }

    @Test
    public void removeAnyBook() {
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        shelf.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        shelf.addBookToShelf("Fantasy", "Words of Radiance", "Sanderson");
        Map<String, Map<String, String>> book = shelf.removeAnyBook();
        Map.Pair<String, Map<String, String>> p = book.removeAny();
        Map.Pair<String, String> p2 = p.value().removeAny();
        assertEquals("Fantasy", p.key());
        if (!p2.key().equals("Oathbringer")) {
            assertEquals(p2.key(), "Words of Radiance");
        } else {
            assertEquals(p2.key(), "Oathbringer");
        }
        BookShelf shelfRef = new BookShelf1L(3, 5, 20);
        shelfRef.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        shelfRef.addBookToShelf("Fantasy", "Words of Radiance", "Sanderson");
        shelfRef.removeBookFromShelf(p.key(), p2.key());
        assertEquals(shelf, shelfRef);
    }

    @Test
    public void removeBookFromRow() {
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        shelf.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        Map<String, Map<String, String>> book = shelf
                .removeBookFromShelf("Fantasy", "Oathbringer");
        Map.Pair<String, Map<String, String>> p = book.removeAny();
        String title = p.value().removeAny().key();
        String genre = p.key();
        assertEquals("Fantasy", genre);
        assertEquals(title, "Oathbringer");
    }

    @Test
    public void shelfContainsBooks() {
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        shelf.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        Map<String, Map<String, String>> book = shelf
                .removeBookFromShelf("Fantasy", "Oathbringer");
        Map.Pair<String, Map<String, String>> p = book.removeAny();
        String title = p.value().removeAny().key();
        String genre = p.key();
        assertEquals("Fantasy", genre);
        assertEquals(title, "Oathbringer");
    }

    @Test
    public void testRemoveFromListOfBooksInProgress() {
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        shelf.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        Map<String, Map<String, String>> book = shelf
                .removeBookFromShelf("Fantasy", "Oathbringer");
        Map.Pair<String, Map<String, String>> p = book.removeAny();
        String title = p.value().removeAny().key();
        String genre = p.key();
        assertEquals("Fantasy", genre);
        assertEquals(title, "Oathbringer");
    }

    @Test
    public void addToListOfBooksInProgress() {
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        shelf.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        Map<String, Map<String, String>> book = shelf
                .removeBookFromShelf("Fantasy", "Oathbringer");
        Map.Pair<String, Map<String, String>> p = book.removeAny();
        String title = p.value().removeAny().key();
        String genre = p.key();
        assertEquals("Fantasy", genre);
        assertEquals(title, "Oathbringer");
    }
}
