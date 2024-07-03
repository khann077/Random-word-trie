/**
 * CSci 1913
 * Author: Devajya Khanna
 */


/**
 * Represents the Gibberisher random word generation algorithm
 */
public class Gibberisher {
    private Trie<CharBag> model;
    private int segmentLength;
    private int samplesProcessed;


    /**
     * Instantiates a Gibberisher
     *
     * @param segmentLength - Length of the segments to be used while training the algorithm
     */
    public Gibberisher(int segmentLength) {
        this.segmentLength = segmentLength;
        model = new Trie<CharBag>();
        samplesProcessed = 0;
    }


    /**
     * Adds/updates data to/in the model, to represent the new information about "English strings" passed through it via trainingSample.
     * Essentially instantiates the model we use to generate random words. Must be used before calling generate.
     * @param trainingSample String[]. Used to obtain information about what "real" english words may look like.
     */
    public void train(String[] trainingSample) {
        for (int i = 0; i < trainingSample.length; i++) {
            String preSample = trainingSample[i];
            LetterSample[] ithSamples = LetterSample.toSamples(preSample, segmentLength);
            for (int j = 0; j < ithSamples.length; j++) {
                samplesProcessed += 1;
                String segment = ithSamples[j].getSegment();
                if (this.model.get(segment) == null) {
                    CharBag tempCharBag = new CharBag();
                    tempCharBag.add(ithSamples[j].getNextLetter());
                    model.put(segment, tempCharBag);
                } else {
                    CharBag tempCharBag = model.get(segment);
                    tempCharBag.add(ithSamples[j].getNextLetter());
                    model.put(segment, tempCharBag);
                }
            }
        }
    }


    /**
     * @return int. samplesProcessed. number of distinct samples that have been used to "train" the model.
     */
    public int getSampleCount() {
        return samplesProcessed;
    }


    /**
     * Generates a random "word" based on the model it is trained upon. Uses a combination of frequency and random generation to create real-looking fake words.
     * Can be called on any gibberisher object and word generated will depend on the properties of that gibberisher such as model and segment length.
     * @return String. Random fake but real-looking word. Built for english characters.
     */
    public String generate() {
        String generatedWord = "";
        int k = 0;
        while (generatedWord.equals("") || (generatedWord.charAt(generatedWord.length() - 1) != LetterSample.STOP)) {
            String sample = "";
            if (generatedWord.equals("")) {
                generatedWord += model.get("").getRandomChar();
                sample = generatedWord;
            } else if (generatedWord.length() < segmentLength) {
                sample = generatedWord;
            } else {
                for (int i = 0; i < segmentLength; i++) {
                    sample += generatedWord.charAt(generatedWord.length() - segmentLength + i);
                }
            }
            CharBag letterCounts = model.get(sample);
            generatedWord += letterCounts.getRandomChar();

        }
        return generatedWord.substring(0, generatedWord.length() - 1);
    }
}
