import java.util.Collection;

public class ArrayList {
    private static final  int DEFAULT_CAPACITY = 100;
    private static final  float GROW_FACTOR = 1.5f;


    private Object[] elements; // внутренний массив для хранения элементов
    private int size; // текущее кол-во элементов в списке
    private int capacity; // текущая вместимость массива

    public ArrayList() {
        this.capacity = DEFAULT_CAPACITY; //устанавливаем вместимость = 10
        this.elements = new Object[capacity];//создаем массив на 10 элементов
        this.size = 0; // пустой список
    }

    public ArrayList(int initialCapacity) {
        this.capacity = initialCapacity; // используем переданную вместимость
        this.elements = new Object[capacity]; //создаем массив нужного размера
        this.size = 0; //пустой список
    }

    public void add(Object element) {
        //проверяем, не заполнен ли массив полностью
        if (size >= capacity) {
            resize();
        }
        //добавляем элемент в свободную ячейку
        elements[size] = element;

        //>счетчик элементов
        size++;
    }

    private void add(int index, Object element) {
        //проверяем индекс
        if (index > size|| index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        //Расширяем массив?
        if (size >= capacity) {
            resize();
        }
        //сдвигаем элементы справа от index
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        //всатвляем новый элемент
        elements[index] = element;

        //увеличиваем счетчик
        size++;
    }

    public boolean addAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection null");
        }
        if (collection.isEmpty()) {
            return false;
        }

        // Проходим по коллекции и добавляем элементы через существующий add()
        for (Object element : collection) {
            add(element); // Используем уже готовый метод add()
        }

        return true;
    }

    private void resize() {
        //вычисляем новый размер
        int newCapacity =(int)(capacity * GROW_FACTOR);

        //новый массив
        Object[] newElements = new Object[newCapacity];

        for(int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        //обновление ссылки на новый массив и новую вместимость
        elements = newElements;
        capacity = newCapacity;
    }

    public Object get(int index) {
        //проверка наличие элемента
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        //возвращаем элемент
        return elements[index];
    }

    public Object remove(int index) {
        //Проверка валидности
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        //сохраняем и удаляем элементы для возврата
        Object removedElement = elements[index];

        //сдвиг всех элементов вправо от удаляемого на 1 позицию влево
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        //очищаем последнюю ячейку
        elements[size - 1] = null;
        //уменьшаем счетчик элементов
        size--;
        //возвращаем удаленный элемент
        return removedElement;
    }

}