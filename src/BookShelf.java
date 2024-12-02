package src;

public interface BookShelf extends BookShelfKernel {
    public boolean hasGenre();

    public void replaceBook();

    public String recommendedNextBookTitle();

    public String recommendedBookGenre();

    public String recommendedAuthor();

}
