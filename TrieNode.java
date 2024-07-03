/**
 * CSci 1913: Project 3
 * Author: Devajya Khanna
 */


/**
 * Represents an instance of the TrieNode class. Used as a component in Trie.
 */
public class TrieNode<T> {
    private T data;
    private TrieNode[] children;


    /**
     * Creates an instance of the TrieNode Class.
     */
    public TrieNode(){
        data = null;
        children = new TrieNode[26];
    }


    /**
     * Returns the data field of the instance it is called on
     * @return data. type is generic T.
     */
    public T getData(){
        return data;
    }


    /**
     * Void method that sets the data field of the TrieNode instance it is called on.
     * @param data - Value the data field is to be set to. Type is Generic T.
     */
    public void setData(T data){
        this.data = data;
    }


    /**
     * Gets the child node associated with the input 'letter', from the TrieNode instance it is called on.
     * Creates a node for the letter if one does not already exist.
     * Returns null if input parameter is a not a lowercase English letter.
     * @param letter - char. letter whose associated node is to be called.
     * @return TrieNode associated with the input parameter letter. Null if 'letter' is not an english lowercase character.
     */
    public TrieNode<T> getChild(char letter){
        if (Character.isLetter(letter) && Character.isLowerCase(letter)){
            if(children[(letter-97)] == null){
                TrieNode<T> newNode = new TrieNode<>();
                children[letter-97] = newNode;
                return newNode;
            }
            else{
                return children[(letter-97)];
            }
        }
        return null;
    }
}


































