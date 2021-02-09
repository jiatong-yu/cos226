/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   jiatongy
 *  Precept: P04
 *
 *  Partner Name:    NA
 *  Partner NetID:   NA
 *  Partner Precept: NA
 *
 *  Description:  Immutable data type with underlying digraph being a rooted DAG
 * so that each vertex v is an integer that represents a synset,
 * and each directed edge v→w represents that w is a hypernym of v
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;

public class WordNet {
    // HashMap<id, synset>
    private final HashMap<Integer, String> idToSyn;


    // HashMap<word, synset>
    private final HashMap<String, Bag<Integer>> wordToID;

    // sca data type
    private final ShortestCommonAncestor sca;


    // construct WordNet data type from file names of synsets and hypernyms
    /* @citation Adapted from: https://algs4.cs.princeton.edu/54regexp/
    Digraph.java.html
     */
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException();

        idToSyn = new HashMap<>();
        wordToID = new HashMap<>();

        In inSyn = new In(synsets);
        In inHyp = new In(hypernyms);

        // store synset into hash map
        while (inSyn.hasNextLine()) {
            String line = inSyn.readLine();
            String[] list = line.split("\\,");
            int id = Integer.parseInt(list[0]);
            String synset = list[1];
            idToSyn.put(id, synset);
            String[] words = synset.split("\\ ");
            for (int i = 0; i < words.length; i++) {
                if (wordToID.containsKey(words[i])) {
                    Bag<Integer> bag = wordToID.get(words[i]);
                    bag.add(id);
                    wordToID.put(words[i], bag);
                }
                else {
                    Bag<Integer> bag = new Bag<>();
                    bag.add(id);
                    wordToID.put(words[i], bag);
                }

            }
        }

        // create empty graph
        Digraph G = new Digraph(idToSyn.size());


        // add links to graph
        while (inHyp.hasNextLine()) {
            String[] list = inHyp.readLine().split("\\,");
            int v = Integer.parseInt(list[0]);
            for (int i = 1; i < list.length; i++) {
                int w = Integer.parseInt(list[i]);
                G.addEdge(v, w);
            }
        }

        sca = new ShortestCommonAncestor(G);
    }

    // all WordNet nones
    public Iterable<String> nouns() {
        return wordToID.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        return wordToID.containsKey(word);
    }

    // a synset (second field of synsets.txt) that is a shortest common ancestor
    // of noun1 and noun2 (defined below)
    public String sca(String noun1, String noun2) {
        if (noun1 == null || noun2 == null) throw new IllegalArgumentException();
        Bag<Integer> syn1 = wordToID.get(noun1);
        Bag<Integer> syn2 = wordToID.get(noun2);

        int ancestor = sca.ancestorSubset(syn1, syn2);
        String res = idToSyn.get(ancestor);
        return res;
    }

    // distance between noun1 and noun2 (defined below)
    // TODO
    public int distance(String noun1, String noun2) {
        if (noun1 == null || noun2 == null) throw new IllegalArgumentException();
        if (!isNoun(noun1) || !isNoun(noun2))
            throw new IllegalArgumentException("query not in synset");

        Bag<Integer> syn1 = wordToID.get(noun1);
        Bag<Integer> syn2 = wordToID.get(noun2);

        int distance = sca.lengthSubset(syn1, syn2);
        return distance;
    }


    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        int i = 0;
        for (String s : wordnet.nouns()) StdOut.println("#" + i++ + ": " + s);
        StdOut.println("should be false: " + wordnet.isNoun("中文"));
        StdOut.println("ancestor: " + wordnet.sca("cat", "dog"));
        StdOut.println("ancestor length: " + wordnet.distance("cat", "dog"));

    }
}
