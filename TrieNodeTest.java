public class TrieNodeTest {
    public static void main(String[] args) {
        TrieNode<Double> tn = new TrieNode<>();
        System.out.println(tn.getData() == null); // true

        tn.setData(4.5);
        System.out.println(tn.getData() == 4.5); // true


        tn.setData(4.54e5);
        System.out.println(tn.getData() == 4.54e5); // true

        TrieNode<Double> an = tn.getChild('a');
        TrieNode<Double> bn = tn.getChild('b');

        System.out.println(tn.getChild('a') == an); // true
        System.out.println(tn.getChild('b') == bn); // true
        System.out.println(tn.getChild('a') == bn); // false
        System.out.println(tn.getChild('b') == an); // false
    }
}
/*
true
true
true
true
true
false
false
 */
