import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class main {
    public static void main(String[] args) {
        // Создаем переменную студента
        ArrayList<Student> students = new ArrayList<>();



        Student student1 = new Student("MalkolmX");
        //Создаем книги
        List<Book> books = new ArrayList<>();

        books.add(new Book("Harry Potter and the Philisipher's ston", 1997, 150));
        books.add(new Book("Harry Potter and the Chambers of Secrets", 1998, 200));
        books.add(new Book("Harry Potter and the Prisoner of Azkaban", 1999, 215));
        books.add(new Book("Harry Potter and the Goblet of Fire", 2000, 300));
        books.add(new Book("Harry Potter and the Orden of the Phoenix", 2003, 600));



        Student student2 = new Student("John");

        List<Book> books2 = new ArrayList<>();

        books2.add(new Book("Harry Potter and the Half-Blood Prince", 2005, 500));
        books2.add(new Book("Harry Potter and the Deathly Hallows", 2007, 550));
        books2.add(new Book("A Daughter of the Snows", 1902, 250));
        books2.add(new Book("The Kempton-Wace Letters", 1903, 400));
        books2.add(new Book("Martin Eden", 1909, 600));
        books2.add(new Book("War and Peace", 2007, 550));

        student1.getBooks().addAll(books);
        student2.getBooks().addAll(books2);

        students.stream()
                // Вывести каждого студента
                .peek(System.out::println)
                // Получить для каждого студента список книг
                .flatMap(s -> s.getBooks().stream())
                // Отсортировать книги по количеству страниц
                .sorted(Comparator.comparingInt(Book::getPages))
                // Оставить только уникальные книги
                .distinct()
                // Отфильтровать книги, выпущенные после 2000 года
                .filter(book -> book.getYear() > 2000)
                // Ограничить на 3 элементах
                .limit(3)
                // Получить годы выпуска
                .map(Book::getYear)
                // Найти первый подходящий элемент
                .findFirst()
                // Вывести результат
                .ifPresentOrElse(
                        year -> System.out.println("Год выпуска найденной книги: " + year),
                        () -> System.out.println("Книга отсутствует")
                );
    }
}