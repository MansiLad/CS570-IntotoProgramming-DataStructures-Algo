import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
public class IDLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices = new ArrayList<>();

    private static class Node<E> {
        E data;
        private Node<E> next = null;
        private Node<E> prev = null;
        Node(E elem){
            this.data = elem;
        }
        Node(E elem, Node<E> prev, Node<E> next){
            this.data = elem;
            this.next = next;
            this.prev = prev;

        }
    }

    //creates empty double-linked list
    public IDLList(){
        head = null;
        tail = null;
        size = 0;
    }

    //adds given element at given position
    public boolean add(int index, E elem){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = indices.get(index);
        Node<E> newNode = new Node<E>(elem, node.prev, node);
        node.prev.next = newNode;
        node.prev = newNode;
        indices.add(index, newNode);
        size += 1;
        return true;
    }

    //adds the element to the head
    public boolean add(E elem){
        if(head == null){
            Node<E> newNode = new Node<E>(elem);
            newNode.data = elem;
            head = tail = newNode;
            indices.add(head);
            size += 1;
            return true;
        }else{
            Node<E> newNode = new Node<E>(elem);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            indices.add(0, newNode);
            size += 1;
            return true;
        }
    }

    //adds the element to at the tail
    public boolean append(E elem){
        if(head == null){
            Node<E> newNode = new Node<E>(elem);
            newNode.data = elem;
            head = tail = newNode;
            indices.add(head);
            size += 1;
            return true;
        }else{
            Node<E> newNode = new Node<E>(elem);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            indices.add(tail);
            size += 1;
            return true;
        }
    }

    //return object present at the given index
    public E get(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        return this.indices.get(index).data;
    }

    //returns head of the list
    public E getHead(){
        return head.data;
    }

    //returns tail of the list
    public E getLast(){
        return tail.data;
    }

    //returns size of the list
    public int size(){
        return size;
    }

    //removes the head element and returns it
    public E remove(){
        if(size == 0){
            throw new IndexOutOfBoundsException();
        }
        if(head == tail){
            Node<E> node = head;
            head = tail = null;
            size -= 1;
            return node.data;
        }
        head = head.next;
        head.prev = null;
        size -= 1;
        return indices.remove(0).data;
    }

    //removes the tail element and returns it
    public E removeLast(){
        if(size == 0){
            throw new IndexOutOfBoundsException();
        }
        if(head == tail){
            Node<E> node = head;
            head = tail = null;
            size -= 1;
            return node.data;
        }
        tail = tail.prev;
        tail.next = null;
        size -= 1;
        return indices.remove(size).data;
    }

    //removes element at the given index and returns it
    public E removeAt(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        if(index == 0){
            E node = this.remove();
            return node;
        }
        Node<E> node = indices.get(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size -= 1;
        return indices.remove(index).data;
    }

    //removes the given element from the list
    public boolean remove(E elem){
        Node<E> node = head;
        int cnt = 0;
        while(node.next != null){
            if(node.data.equals(elem)){
                if(cnt == 0){
                    head = head.next;
                    size -= 1;
                    indices.remove(0);
                    return true;
                }
                node.next.prev = node.prev;
                node.prev.next = node.next;
                size -= 1;
                indices.remove(cnt);
                return true;
            }
            node = node.next;
            cnt += 1;
        }
        return false;
    }

    //return the elements, in string
    public String toString(){
        Node<E> node = head;
        StringBuilder ResultString = new StringBuilder();
        while(node != null){
            ResultString.append(node.data);
            if(node.next != null){
                ResultString.append("->");
            }
            node = node.next;
        }
        return ResultString.toString();
    }
}