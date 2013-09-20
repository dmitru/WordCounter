package ru.ncedu.java.tasks;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dborody
 * Date: 9/19/13
 * Time: 10:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class WordCounterImpl implements WordCounter {
    private String text = null;

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

        return null;
    }

    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Map.Entry<String, Long>> sortWordCounts(Map<String, Long> orig) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void printWordCounts(PrintStream ps) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void printWordCountsSorted(PrintStream ps) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
