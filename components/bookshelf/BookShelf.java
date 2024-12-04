package components.bookshelf;

public interface BookShelf extends BookShelfKernel {

    public boolean hasGenre();

    public String recommendedNextBookTitle();

    public String recommendedBookGenre();

    public String recommendedAuthor();

}
