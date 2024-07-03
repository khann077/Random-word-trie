/**
 * CSci 1913: Project 3
 * Author: Devajya Khanna
 */


/**
 * Represents a Trie Data Structure, which associates short strings with specific nodes on a Tree
 */
public class Trie<T> {
    private final TrieNode<T> root;


    /**
     * Constructs an instance of the Trie Class, setting the root node to the desired value.
     */
    public Trie(){
        this.root = new TrieNode<T>();
    }


    /**
     * Returns the node in the Trie associated with the inputted parameter string. Returns null if no such string exists.
     * @param searchVal - String. String value for which we are finding the node.
     * @return TrieNode instance which is associated with the last letter of the string and null if the string not in the Trie.
     */
    private TrieNode<T> getNode(String searchVal){
        TrieNode<T> currentNode = this.root;
        String val = "";
        if(searchVal.equals(val)){
            return currentNode;
        }
        for(int i =0; i<searchVal.length(); i++){
            val += searchVal.charAt(i);
            currentNode = currentNode.getChild(searchVal.charAt(i));

        }
        return currentNode;
    }


    /**
     * Returns the data on the end node of the path of the trie that is associated with the search string.
     * @param searchVal - String whose associated data we are finding in the Trie.
     * @return T. Data on the last node of the Trie.
     */
    public T get(String searchVal){
        TrieNode<T> endNode = getNode(searchVal);
        if(endNode == null){
            return null;
        }
        return endNode.getData();
    }


    /**
     * Sets the data of the node that terminates the searchVal string in the Trie.
     * @param searchVal - String whose data is being set by the function
     * @param newData - T. Data to be assigned to the last node of the path through the Trie that represents searchVal.
     */
    public void put(String searchVal, T newData){
        // if key doesn't exist
        if(this.getNode(searchVal)==null){
            if(searchVal.equals("")){
                TrieNode<T> currentNode = root;
                currentNode.setData(newData);
            }
            else{
                TrieNode<T> currentNode = this.root.getChild(searchVal.charAt(0));
                for(int i=1;i<searchVal.length(); i++){
                    currentNode.setData(newData);
                    if(i != searchVal.length()-1){
                        currentNode = currentNode.getChild(searchVal.charAt(i));
                    }
                }
            }
        }
        // if key exists
        else{
            TrieNode endNode = this.getNode(searchVal);
            endNode.setData(newData);
        }

    }
}
