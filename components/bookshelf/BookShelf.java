package components.bookshelf;

public interface BookShelf extends BookShelfKernel {

    public boolean hasGenre(String genre);

    public String recommendedNextBookTitle();

    public String recommendedBookGenre();

    public String recommendedAuthor();

}
