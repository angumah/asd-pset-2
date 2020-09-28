import java.util.List;
import java.util.NoSuchElementException;

public class SimpleLinkedList {
    private  Node head;
    private  Node tail;
    private int size;

    public SimpleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public SimpleLinkedList(List<String> list) {
        head = new Node (null, null, null);
        tail = new Node (null, null, null);

        Node current = head;
        head = current;
        size = 0;
        for(int i = 0; i < list.size(); i++){
            current.data = list.get(i);
            Node pastCurrent = current;
            current = new Node (pastCurrent, null, null);
            pastCurrent.next = current;
            tail = current;
            size++;
        }

    }

    public void add(int index, String s) {
        checkIndex(index);
        if (index == 0) {
            addFirst(s);
        } else if (index == size) {
            addLast(s);
        } else {
            Node current = getNode(index);
            Node newNode = new Node(current.prev, s, current);
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    public void addFirst(String s) {
        Node current = head;
        Node newNode = new Node(null, s, current);

        head = newNode;
        if (current == null) {
            tail = newNode;
        } else {
            current.prev = newNode;
        }
        size++;
    }

    public void addLast(String s) {
        Node current = tail;
        Node newNode = new Node(current, s, null);

        tail = newNode;
        if (current == null) {
            head = newNode;
        } else {
            current.next = newNode;
        }
        size++;
    }

    public void clear() {
        head = new Node(null, null, null);
        tail = new Node(null, null, null);
        size = 0;
    }

    public boolean contains(String s) {
        return hasNode(s);
    }

    public String get(int index) {
        checkIndex(index);
        return getNode(index).data;
    }

    public String getFirst() {
        checkSize();
        Node current = head;
        return current.data;
    }

    public String getLast() {
        checkSize();
        Node current = tail;
        return current.data;
    }

    public int indexOf(String s) {
        int index = 0;
        while(index < size) {
            if (getNode(index).data.equals(s)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public String remove(int index) {
        checkIndex(index);
        if (index == 0) {
            return removeFirst();
        } else if (index == size-1) {
            return removeLast();
        } else {
            Node current = getNode(index);
            current.prev.next = current.next;
            current.next.prev = current.prev;
            String s = current.data;
            size--;
            return s;
        }
    }

    public boolean remove(String s) {
        if (contains(s)) {
            int index = indexOf(s);
            if (index == 0) {
                removeFirst();
            }
            if (index == size) {
                removeLast();
            } else {
                Node current = getNode(index);
                current.next.prev = current.prev;
                current.prev.next = current.next;
                size--;
            }
        }
      return contains(s);
    }

    public String removeFirst() {
        checkSize();
        Node current = head;
        String removed = head.data;
        head = current.next;
        head.prev = null;
        size--;
        return removed;
    }

    public String removeLast() {
        checkSize();
        Node current = tail;
        String removed = tail.data;
        tail = current.prev;
        tail.next = null;
        size--;
        return removed;
    }

    public String set(int index, String s) {
        checkIndex(index);
        Node current = getNode(index);
        String element = current.data;
        if (s == null) s = "null";
        current.data = s;
        return element;
    }

    public int size() {
        return size;
    }

    public String toString() {
        String convert = "[";
        Node current = head;
        if (size != 0) {
            for (int i = 0; i < size -1; i++) {
                convert += current.data + ", ";
                current = current.next;
            }
            convert += current.data;
        }
        return convert + "]";
    }


    private Node getNode(int index) {
        checkIndex(index);
        int count = 0;
        Node current = head;
        while (current != null) {
            if (count++ == index) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private boolean hasNode(String s){
        for(int i = 0; i < size; i++){
            if(getNode(i).data.equals(s)){
                return true;
            }
        }
        return false;
    }

    private static class Node {
         Node prev;
         String data;
         Node next;

         public Node(Node prev, String data, Node next){
             this.prev = prev;
             this.data = data;
             this.next = next;
         }
    }
    private  void checkIndex(int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    private void checkSize(){
        if (size==0){
            throw new NoSuchElementException();
        }
    }
}
