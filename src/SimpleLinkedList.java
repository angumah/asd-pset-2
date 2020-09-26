import java.util.List;
public class SimpleLinkedList {
    private Node head;
    private Node tail;
    private int size;
    public SimpleLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    public SimpleLinkedList(List<String> list) {
            for(int i=0; i<list.size(); i++){
                add(i, list.get(i));
            }
            size=list.size();
    }

    public void add(int index, String s){
        if(index<0 || index>size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        else if(index == 0){
            addFirst(s);
        }
        else if(index == size){
            addLast(s);
        }
        else {
            Node current = getNode(index);
            Node newNode = new Node(current.prev, s, current);

            size++;
            current.prev.next = newNode;
            current.prev = newNode;
        }

    }
    public void addFirst(String s){
        Node current = head;
        Node newNode = new Node(null, s, current);

        head = newNode;
        if (current == null){
            tail = newNode;
        }
        else{
            current.prev = newNode;
        }
        size++;
    }

    public void addLast(String s){
        Node current = tail;
        Node newNode = new Node(current, s, null);

        tail = newNode;
        if (current == null){
            head = newNode;
        }
        else {
            current.next = newNode;
        }
        size++;
    }

    public void clear(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean contains(String s){
        Node current = head;
        while(current != null){
            if(current.data == s){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public String get(int index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node current = getNode(index);
        if(current.data != null) {
            String s = current.data;
            return s;
        }
        return null;
    }

    public String getFirst(){
        Node current = head;
        if(current != null){
            String s = current.data;
            return s;
        }
        return null;
    }

    public String getLast(){
        Node current = tail;
        if(current != null){
            String s = current.data;
            return s;
        }
        return null;
    }

    public int indexOf(String s){
        int count = 0;
        Node current = head;
        while(current != null){
            if(current.data == s){
                return count;
            }
            current = current.next;
            count++;
        }
        return 0;
    }

    public String remove(int index){
        Node current = getNode(index);
        if(current == head){
            String s = removeFirst();
            return s;
        }
        else if(current == tail){
            String s = removeLast();
            return s;
        }
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size --;
        }
        return current.data;
    }

    public boolean remove(String s){
        Node current = getNode(s);
        if(current != null) {
            if(current == head){
                removeFirst();
            }
            else if(current == tail){
                removeLast();
            }
            else{
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size --;
            }
            return true;
        }
        return false;
    }

    public String removeFirst(){
        Node current = head;
        if (current != null){
            String s = current.data;
            Node nextCurrent = current.next;
            current.next = null;
            nextCurrent.prev = null;
            head = nextCurrent;
            size --;
            return s;
        }
        return null;
    }

    public String removeLast(){
        Node current = tail;
        if (current != null){
            String s = current.data;
            Node nextCurrent = current.next;
            current.next = null;
            nextCurrent.prev = null;
            tail = nextCurrent;
            size--;
            return s;
        }
        return null;
    }

    public String set(int index, String s){
        if(index<0 || index>size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size" + size);
        }
        Node current = getNode(index);
        current.data = s;
        return current.data;
    }

    public int size(){
        Node current = head;
        int count = 0;
        while(current != null && current != tail){
            count++;
        }
        return count;
    }

    public String toString(){
        if(size == 0){
            return "[]";
        }
        String convert = "[";
        Node current = head;
        for(int y=0; y<size; y++){
            String s = current.data;
            if(y == size) {
                convert += current.data + "]";
            }
            else {
                convert += current.data + ", ";
                current = current.next;
            }
        }
        return convert;
    }


    private Node getNode(int index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int count = 0;
        Node current = head;

        while(current != null){
            if(count++ == index){
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private Node getNode(String s){
        int count = 0;
        Node current = head;
        while(current != null){
            if(current.data == s){
                return current;
            }
            current = current.next;
            count++;
        }
        return null;
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
}
