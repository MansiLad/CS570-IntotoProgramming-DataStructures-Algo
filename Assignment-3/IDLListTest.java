public class IDLListTest {
    public static void main(String[] args) {
        IDLList<Integer> DoubleLList= new IDLList<Integer>();
        DoubleLList.add(5);
        DoubleLList.add(88);
        DoubleLList.add(332);
        DoubleLList.add(54);
        DoubleLList.add(100);
        DoubleLList.add(32);
        DoubleLList.add(9);
        DoubleLList.add(5, 45);
        DoubleLList.append(11);
        DoubleLList.add(2, 48);
        System.out.println();
        System.out.println("The Resultant List: " + DoubleLList.toString());
        System.out.println("Value at position 3: " + DoubleLList.get(3));
        System.out.println("Value of Head: " + DoubleLList.getHead());
        System.out.println("Value of Tail: " + DoubleLList.getLast());
        System.out.println("Size of the List: " + DoubleLList.size());
        System.out.println(DoubleLList.remove() + ": Head is removed");
        System.out.println(DoubleLList.removeLast() + ": Tail is removed");
        System.out.println(DoubleLList.removeAt(2) + ": element at position 2 is removed");
        System.out.println(DoubleLList.remove(332) + ": status of the element searched is deleted or not");
        System.out.println("The resultant List " + DoubleLList.toString());
        System.out.println("Size of the List: " + DoubleLList.size());
    }
}
