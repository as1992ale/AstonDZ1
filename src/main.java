import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public class main {
    public static void main(String[] args) {
        // Создаем переменную студента
        ArrayList<Student> students = new ArrayList<>();



        Student student1 = new Student("MalkolmX");
        //Создаем книги
        Book book1 = new Book("Harry Potter and the Philisipher's ston", 1997, 150);
        Book book2 = new Book("Harry Potter and the Chambers of Secrets", 1998, 200);
        Book book3 = new Book("Harry Potter and the Prisoner of Azkaban", 1999, 215);
        Book book4 = new Book("Harry Potter and the Goblet of Fire", 2000, 300);
        Book book5 = new Book("Harry Potter and the Orden of the Phoenix", 2003, 600);


        Student student2 = new Student("John");
        Book book6 = new Book("Harry Potter and the Half-Blood Prince", 2005, 500);
        Book book7 = new Book("Harry Potter and the Deathly Hallows", 2007, 550);
        Book book8 = new Book("A Daughter of the Snows", 1902, 250);
        Book book9 = new Book("The Kempton-Wace Letters", 1903, 400);
        Book book10 = new Book("Martin Eden", 1909, 600);
        Book book11 = new Book("War and Peace", 2007, 550);

        students.add(student1);
        students.add(student2);

        student1.addBook(book1);
        student1.addBook(book2);
        student1.addBook(book3);
        student1.addBook(book4);
        student1.addBook(book5);
        student2.addBook(book6);
        student2.addBook(book7);
        student2.addBook(book8);
        student2.addBook(book9);
        student2.addBook(book10);
        student2.addBook(book11);

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