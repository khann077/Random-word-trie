/**
 * CSci 1913: Project 3
 * Author: Devajya Khanna
 */
import java.io.PrintStream;
import java.util.Random;

/**
 * Represents a CharBag, which is a custom version of the Bag ADT. Stores, characters, duplicates allowed.
 * Uses an int array to store counts of each letter and the STOP Character. Index 0 is "a", Index 1 is "b" and so on till Index 26 which is "."
 */
public class CharBag {
    private int[] counts;
    //Ascii a: 97 so Index = 97-ascii(char)


    /**
     * Creates an instance of the CharBag ADT.
     */
    public CharBag(){
        this.counts = new int[27];
    }


    /**
     * Adds a single occurrence of the input character to the CharBag instance. This is represented as increasing the appropriate count in the array.
     * Not case-sensitive (converts to lowercase). Any non english letter character is changed to the STOP value.
     * @param c - Char to be added to the CharBag
     */
    public void add(char c){
        if(Character.isLetter(c)){
            if(Character.isUpperCase(c)){
                c = Character.toLowerCase(c);
            }
            counts[(int)c-97] += 1;
        }
        else{
            c = LetterSample.STOP;
            counts[26] += 1;
        }


    }


    /**
     * Removes a single occurrence of the input character from the CharBag instance. Does nothing if the char doesn't exist in the bag.
     * Not case-sensitive (converts to lowercase). Any non english letter character is changed to the STOP value.
     * @param c - Char to be removed from the CharBag
     */
    public void remove(char c){
        if(Character.isLetter(c)){
            if(Character.isUpperCase(c)){
                c = Character.toLowerCase(c);
            }
            if(counts[(int)c-97] != 0) {
                counts[(int)c - 97] -= 1;
            }
        }
        else{
            c = LetterSample.STOP;
            if(counts[26] != 0) {
                counts[26] -= 1;
            }
        }

    }


    /**
     * Returns the number of occurrences of the character in the CharBag
     * @param c - Character to be "counted"
     * @return - Number of times param c exists in the CharBag.
     */
    public int getCount(char c){
        char cToCount = c;
        if((int)c>=65 && !((int)c >= 91 && (int)c<=96) && (int)c < 97) {
            cToCount = (char)((int)c+32) ;
        }
        else if((int)c < 65 || ((int)c >= 91 && (int)c<=96) ||  (int)c > 122){
            return counts[26];
        }
        return counts[(int)cToCount-97];
    }


    /**
     * Returns the total number of characters in the CharBag, including duplicates.
     * @return size - int. Total number of characters in the CharBag instance.
     */
    public int getSize(){
        int size=0;
        for (int count : counts) {
            size += count;
        }
        return size;
    }


    /**
     * Returns the contents of the CharBag as letter-count pairs  with appropriate formatting.
     * @return String. The contents of CharBag.
     */
    @Override
    public String toString(){
        String lcPair = "";
        for(int i = 0; i < counts.length; i++){
            if(i==26){
                lcPair += ".:"+counts[i];
            }
            else {
                lcPair += (char) (97 + i) + ":" + counts[i] + ", ";
            }
        }
        return "CharBag{"+lcPair+"}";
    }


    /**
     * Randomly generates letters out of those available in the CharBag instance. Generates letter in proportion to the occurrences of the letters in the CharBag.
     * Return '.' if the bag is empty.
     * @return char c - generated "randomly".
     */
    public char getRandomChar(){
        double chance = (new Random()).nextDouble();
        double[] ratios = new double[27];
        double posOnNumberLine = 0;
        for(int j = 0; j<counts.length; j++){
            if(this.getSize() != 0){
                ratios[j] = (double)counts[j]/this.getSize();
            }
        }
        for(int i =0; i<counts.length; i++){
            posOnNumberLine += ratios[i];
            if(posOnNumberLine>=chance){
                if (i == 26) {
                    return LetterSample.STOP;
                }
                else{
                    return (char)(i+97);
                }
            }
        }
        return LetterSample.STOP;
    }
}
