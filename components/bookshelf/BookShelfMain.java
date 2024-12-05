package components.bookshelf;

import components.map.Map;

public class BookShelfMain {
    public static void main(String[] args) {
        BookShelf shelf = new BookShelf1L(3, 5, 20);
        shelf.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        shelf.addBookToShelf("Fantasy", "Words of Radiance", "Sanderson");
        Map<String, Map<String, String>> book = shelf.removeAnyBook();
        Map.Pair<String, Map<String, String>> p = book.removeAny();
        Map.Pair<String, String> p2 = p.value().removeAny();
        shelf.displayShelf();

        BookShelf shelfRef = new BookShelf1L(3, 5, 20);
        shelfRef.addBookToShelf("Fantasy", "Oathbringer", "Sanderson");
        shelfRef.addBookToShelf("Fantasy", "Words of Radiance", "Sanderson");
        shelfRef.removeBookFromShelf(p.key(), p2.key());
        shelfRef.removeBookFromShelf("Fantasy", "Words of Radiance");
        System.out.println();
        shelfRef.displayShelf();

    }
}
