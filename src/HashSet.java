import java.util.*;

public class HashSet {
    private static final int SIZE = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Node[] table;  //Массив корзин
    private int size;  //количество элементов
    private int capacity; //текущая вместимость
    private float loadFactor;  // коэффициент загрузки
    private int threshold; //порог для resize

    //Внутренний класс для элементов
    private static class Node {
        Object key; // хранитель элементов
        Node next; // ссылка на следующий узел
        int hash; // кэширование хэш ключа

        Node(Object key, Node next, int hash) {
            this.key = key;
            this.next = next;
            this.hash = hash;
        }
    }

    //Конструктор по умолчанию
    public HashSet(){
        this(SIZE, LOAD_FACTOR);
    }

    //Основной конструктор
    public HashSet(int initialCapacity, float loadFactor){
         this.capacity = initialCapacity; //вместимость склада
         this.loadFactor = loadFactor; //коэфициент загрузки
         this. threshold = (int)(capacity * loadFactor); //порог срабатывания расширений
         this.table = new Node[capacity]; //массив корзин
         this.size = 0; //счетчик элементов
    }


        //Вспомогательный метод для вычисления хэша
    private int hash(Object key){
         if(key == null) return 0; // null всегда в корзине 0
         int h = key.hashCode(); //получаем стандартный хэш
         return (h ^ (h >>> 16));//улучшаем распределение
    }

    //метод add
    public boolean add(Object key){
        // 1. Вычисляем хэш и индекс полки
        int hash = hash(key);   //вычисляем улучшеный кэш
        int index = (capacity-1) & hash;//находим номер полки

        // 2. Проверяем дубликаты
        Node current = table[index];
        while (current != null){
            if(current.hash == hash && (current.key == key || (key != null && key.equals(current.key))))
            {
                //нашли дубликаи - не добавляем
                return false;
            }
            current = current.next;
        }

        // 3. Добавляем новый элемент
        table[index] = new Node(key, current, hash);
        size++;

        // 4. Проверяем расширение
        if (size > threshold){
            resize();
        }
        return true; // Добавлено
    }

    public boolean contains (Object key){
        int hash = hash(key);//вычисляем улучшаем кэш
        int index = (capacity-1) & hash;//находим номер полки

        //идем по цепочке
        Node current = table[index];
        while (current != null){
            //та же проверка
            if(current.hash == hash && (current.key == key || (key != null && key.equals(current.key)))){
                return true;//нашли элемент
            }
            current = current.next;
        }
        return false;//прошли до конца и не нашли

    }

    //меод remove
    public boolean remove(Object key){
        int hash = hash(key);
        int index = (capacity-1) & hash;

        Node current = table[index];
        Node prev = null;

        while (current != null){
            if (current.hash == hash && (current.key == key || (key != null && key.equals(current.key)))){
                //Нашли элемент для удаления
                //удаляем
            }
            prev = current; // запоминаем текущую как предыдущую
            current = current.next;//переходим к следуюущей
            if (prev == null){
                //удаляем коробку на полке
                table[index] = table[index].next;
            }else{
                //удаляем с середины или с конца
                prev.next = current.next;
            }
            size--;//уменьшаем счетчик
            return true;//удалили
        }
        return  false;//не нашли
    }

    public void resize(){
        int newCapacity = capacity *2;//увеличиываем вместимость в 2 раза
        int newThreshold = (int)(newCapacity * loadFactor);//пересчитываем порог для нового размера

        Node[] newTable = new Node[newCapacity];

        //проходим по полкам
        for(int i = 0; i < capacity; i++){
            Node current = table[i];

            //проходим по каждой коробке на полке
            while (current != null){
                Node next = current.next; //запоминаем слудующую

                //Вычисляем НОВЫЙ индекс для коробки
                int nextIndex = (newCapacity-1) & current.hash;

                //Вставляем коробку в новую таблицу
                current.next = newTable[nextIndex];
                newTable[nextIndex] = current;

                current = next; // переходим к следующей коробке
            }
        }
        //обновляем все перменные
        table = newTable; // исп новый масси
        capacity = newCapacity; //обн вместимость
        threshold = newThreshold; //обн порог
    }

}