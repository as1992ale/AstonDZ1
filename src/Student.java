import java.util.List;
import java.util.ArrayList;

public class Student {
    public String name;
    List<Book> books;

    public Student (String name){
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }

    public String getName() {
        return name;
    }
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Имя студента: "+name+"Список книг"+books;
    }
}



