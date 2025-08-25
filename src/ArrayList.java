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
        if (size >= capacity) {
            resize();
        }
        elements[size] = element;
        size++;
    }

    private void add(int index, Object element) {
        if (index > size|| index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size >= capacity) {
            resize();
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        size++;
    }

    public boolean addAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection null");
        }
        if (collection.isEmpty()) {
            return false;
        }

        for (Object element : collection) {
            add(element);
        }
        return true;
    }

    private void resize() {
        int newCapacity =(int)(capacity * GROW_FACTOR);
        Object[] newElements = new Object[newCapacity];

        for(int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
        capacity = newCapacity;
    }

    public Object get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elements[index];
    }

    public Object remove(int index) {
            if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
            Object removedElement = elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null;
        size--;

        return removedElement;
    }

}