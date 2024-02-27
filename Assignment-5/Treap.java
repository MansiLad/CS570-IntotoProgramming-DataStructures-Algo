
package Treap;
import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
    private static class Node<E> {
        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;

        public Node(E data, int priority){
            if(data == null){
                throw new IllegalArgumentException();
            }
            else{
                this.data = data;
                this.priority = priority;
                this.right = null;
                this.left = null;
            }
        }
        public Node<E> rotateRight(){
            Node<E> tempnode = this.left;
            Node<E> left = tempnode.right;
            tempnode.right = this;
            this.left = left;
            return tempnode;
        }

        public Node<E> rotateLeft(){
            Node<E> tempnode = this.right;
            Node<E> left = tempnode.left;
            tempnode.left = this;
            this.right = left;
            return tempnode;
        }

        public String toString() {
            return "( key = " + this.data.toString() + ", priority = " + this.priority + " )";
        }
    }

    private Random priorityGenerator;
    private Node<E> root;
    public Treap(){
        priorityGenerator = new Random();
        root = null;
    }
    public Treap(long seed){
        priorityGenerator = new Random(seed);
        root = null;
    }

    boolean add(E key){
        return add(key, priorityGenerator.nextInt());
    }
    boolean add(E key, int priority){
        if(root == null){
            root = new Node<E>(key, priority);
            return true;
        }
        Stack<Node<E>> stack = new Stack<Node<E>>();
        Node<E> node = new Node<E>(key, priority);
        Node<E> temproot = root;
        if(find(node.data)){
            return false;
        }
        while(temproot.left != null || temproot.right != null) {
            if(node.data.compareTo(temproot.data) < 0){
                if(temproot.left == null)
                    break;
                stack.push(temproot);
                temproot = temproot.left;
            }
            else{
                if(temproot.right == null)
                    break;
                stack.push(temproot);
                temproot = temproot.right;
            }
        }
        stack.push(temproot);
        if(node.data.compareTo(temproot.data) < 0){
            temproot.left = node;
        }
        else{
            temproot.right = node;
        }
        while (stack.peek().priority < node.priority) {
            if(stack.peek() == root) {
                temproot = stack.pop();
                if(temproot.left == node){
                    temproot = temproot.rotateRight();
                }
                else{
                    temproot = temproot.rotateLeft();
                }
                root = temproot;
                break;
            }
            else{
                temproot = stack.pop();
                if(temproot.left == node) {
                    temproot = temproot.rotateRight();
                }
                else{
                    temproot = temproot.rotateLeft();
                }
                if(node.data.compareTo(stack.peek().data) < 0){
                    stack.peek().left = temproot;
                }
                else stack.peek().right = temproot;
            }
        }
        return true;
    }

    boolean delete(E key){
        if(!find(key)){
            return false;
        }
        Node<E> temproot = root;
        Node<E> target = null;
        Node<E> parentroot = root;
        Node<E> head = null;
        Node<E> parent = null;

        if(root == null) return false;
        else if (key == temproot.data && temproot.right == null && temproot.left == null){
            root = null;
            return true;
        }
        while (true){
            if(key.compareTo(temproot.data)<0){
                parentroot = temproot;
                temproot = temproot.left;
            } else if (key.compareTo(temproot.data) > 0){
                parentroot = temproot;
                temproot = temproot.right;
            } else if (key.compareTo(temproot.data) == 0){
                target = temproot;
                break;
            }
        }
        if(target.right == null && target.left == null) {
            if(parentroot.left == target) parentroot.left = null;
            else parentroot.right = null;
            return true;
        }
        while (target.right != null || target.left != null){
            if(target.right != null && target.left != null) {
                if(target.right.priority > target.left.priority) {
                    head = target.rotateLeft();
                    target = head.left;
                }
                else {
                    head = target.rotateRight();
                    target = head.right;
                }
            }
            else if (target.right == null){
                head = target.rotateRight();
                target = head.right;
            }
            else{
                head = target.rotateLeft();
                target = head.left;
            }
            if (target.data == parentroot.data) root = head;
            else if (parentroot.left == target) parentroot.left = head;
            else parentroot.right = head;
            parentroot = head;
            parent = head;
        }
        if(parent.left == target) parent.left = null;
        else parent.right = null;
        return true;
    }
    private boolean find(Node <E> root, E key){
        while(true) {
            if (root != null) {
                if (root.data == key) return true;
                else if (root.data.compareTo(key) > 0) root = root.left;
                else if (root.data.compareTo(key) < 0) root = root.right;
            } else
                return false;
        }
    }
    public boolean find(E key){
        return find(root, key);
    }

    private void Preorder(StringBuilder sb, Node<E> node, int depth) {
        for (int i = 1; i < depth; i++) sb.append("  ");
        if (node == null) sb.append("null\n");
        else {
            sb.append(node);
            sb.append("\n");
            Preorder(sb, node.left, depth + 1);
            Preorder(sb, node.right, depth + 1);
        }
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Preorder(sb, root, 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        Treap<Integer> testTree = new Treap<Integer>();
        testTree.add(5,55);
        testTree.add(7,23);
        testTree.add(8,90);
        testTree.add(2,56);
        testTree.add(4,45);
        testTree.add(9,15);
        testTree.add(1,42);
        System.out.println(testTree.delete(9));
        System.out.println(testTree.find(9));
        System.out.println(testTree.toString());
    }

}