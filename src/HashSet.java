import java.util.*;

public class HashSet {
    private static final int SIZE = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Node[] table;
    private int size;
    private int capacity;
    private float loadFactor;
    private int threshold;

    private static class Node {
        Object key;
        Node next;
        int hash;

        Node(Object key, Node next, int hash) {
            this.key = key;
            this.next = next;
            this.hash = hash;
        }
    }

    public HashSet(){
        this(SIZE, LOAD_FACTOR);
    }

    public HashSet(int initialCapacity, float loadFactor){
         this.capacity = initialCapacity;
         this.loadFactor = loadFactor;
         this. threshold = (int)(capacity * loadFactor);
         this.table = new Node[capacity];
         this.size = 0;
    }

    private int hash(Object key){
         if(key == null) return 0;
         int h = key.hashCode();
         return (h ^ (h >>> 16));
    }

    public boolean add(Object key){
        int hash = hash(key);
        int index = (capacity-1) & hash;

        Node current = table[index];
        while (current != null){
            if(current.hash == hash && (current.key == key || (key != null && key.equals(current.key))))
            {
                return false;
            }
            current = current.next;
        }

        table[index] = new Node(key, current, hash);
        size++;

        if (size > threshold){
            resize();
        }
        return true;
    }

    public boolean contains (Object key){
        int hash = hash(key);
        int index = (capacity-1) & hash;

        Node current = table[index];
        while (current != null){
            if(current.hash == hash && (current.key == key || (key != null && key.equals(current.key)))){
                return true;
            }
            current = current.next;
        }
        return false;

    }

    public boolean remove(Object key){
        int hash = hash(key);
        int index = (capacity-1) & hash;

        Node current = table[index];
        Node prev = null;

        while (current != null){
            if (current.hash == hash && (current.key == key || (key != null && key.equals(current.key)))){

            }
            prev = current;
            current = current.next;
            if (prev == null){
                table[index] = table[index].next;
            }else{
                prev.next = current.next;
            }
            size--;
            return true;
        }
        return  false;
    }

    public void resize(){
        int newCapacity = capacity *2;
        int newThreshold = (int)(newCapacity * loadFactor);

        Node[] newTable = new Node[newCapacity];


        for(int i = 0; i < capacity; i++){
            Node current = table[i];


            while (current != null){
                Node next = current.next;


                int nextIndex = (newCapacity-1) & current.hash;


                current.next = newTable[nextIndex];
                newTable[nextIndex] = current;

                current = next;
            }
        }
        table = newTable;
        capacity = newCapacity;
        threshold = newThreshold;
    }

}