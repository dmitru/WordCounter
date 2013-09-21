package ru.ncedu.java.tasks;

import java.io.PrintStream;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: dborody
 * Date: 9/19/13
 * Time: 10:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class WordCounterImpl implements WordCounter {
    private String text = null;
    private Map<String, Long> dict = null;

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public Map<String, Long> getWordCounts() {
        if (this.text == null)
            throw new IllegalStateException();

        if (this.dict == null)
            this.dict = this.countWords(this.text);

        return new HashMap<String, Long>(this.dict);
    }

    private Map<String, Long> countWords(String text) {
        Map<String, Long> dict = new HashMap<String, Long>();

        for (String word : text.split("[ \\t\\n]+")) {
            word = word.toLowerCase();
            if (word.matches("<.*>") || word.length() == 0)
                continue;

            Long counter = dict.get(word);
            if (counter == null)
                counter = new Long(1);
            else
                counter = counter + 1;

            dict.put(word, counter);
        }

        return dict;
    }

    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() {
        if (this.text == null)
            throw new IllegalStateException();

        return this.sortWordCounts(this.getWordCounts());
    }

    @Override
    public List<Map.Entry<String, Long>> sortWordCounts(Map<String, Long> orig) {
        List<Map.Entry<String, Long>> newList = new ArrayList<Map.Entry<String, Long>>();

        for (Map.Entry<String, Long> entry : orig.entrySet()) {
            newList.add(new AbstractMap.SimpleEntry<String, Long>(entry));
        }

        Collections.sort(newList, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                if (o2.getValue() > o1.getValue())
                    return 1;
                else if (o2.getValue() < o1.getValue())
                    return -1;
                else
                    return o1.getKey().compareTo(o2.getKey());
            }
        });

        return newList;
    }

    @Override
    public void printWordCounts(PrintStream ps) {
        if (this.text == null)
            throw new IllegalStateException();

        Map<String, Long> dict = this.getWordCounts();
        for (Map.Entry<String, Long> entry : dict.entrySet()) {
            ps.format("%s %d\n", entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void printWordCountsSorted(PrintStream ps) {
        if (this.text == null)
            throw new IllegalStateException();

        List<Map.Entry<String, Long>> entries = this.sortWordCounts(this.getWordCounts());

        for (Map.Entry<String, Long> entry : entries) {
            ps.format("%s %d\n", entry.getKey(), entry.getValue());
        }
    }
}
