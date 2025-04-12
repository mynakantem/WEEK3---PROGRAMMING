import java.util.*;

class MyHashMap<K, V> {

    // Node class for each key-value pair
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 1000;
    private Node<K, V>[] buckets;

    public MyHashMap() {
        buckets = new Node[SIZE];
    }

    // Hash function to find index
    private int getIndex(K key) {
        return Math.abs(key.hashCode() % SIZE);
    }

    // Insert or update key-value pair
    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> head = buckets[index];

        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value; // Update if key exists
                return;
            }
            current = current.next;
        }

        // Insert new node at the head
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        buckets[index] = newNode;
    }

    // Retrieve value for a key
    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null; // Not found
    }

    // Remove key-value pair
    public void remove(K key) {
        int index = getIndex(key);
        Node<K, V> current = buckets[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }
}

class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("apple", 10);
        map.put("banana", 20);
        map.put("orange", 30);

        System.out.println("Get apple: " + map.get("apple"));   // 10
        System.out.println("Get mango: " + map.get("mango"));   // null

        map.put("apple", 15); // update
        System.out.println("Updated apple: " + map.get("apple")); // 15

        map.remove("banana");
        System.out.println("After removing banana: " + map.get("banana")); // null
    }
}
