import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public class main {
    public static void main(String[] args) {
        // Создаем переменную студента
        ArrayList<Student> students = new ArrayList<>();



        Student student1 = new Student("MalkolmX");
        //Создаем книги


        Student student2 = new Student("John");

        students.add(student2);


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