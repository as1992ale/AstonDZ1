import java.util.Objects;

public class Book{
    private String title;
    private int year;
    private int pages;

    public Book(String title, int year, int pages){
        this.title = title;
        this.year = year;
        this.pages = pages;

    }
    public int getPages() {
        return pages;
    }
    public int getYear() {
        return year;
    }
    public String getTitle() {
        return title + "(" + year + ")";
    }
    public String toString(){
        return "Название: " + title + ", Год выпуска: " + year + ", Страницы: " + pages;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && pages == book.pages && title.equals(book.title);
    }

    @Override
    public int hashCode(){
        return Objects.hash(title, year, pages);
    }

}
