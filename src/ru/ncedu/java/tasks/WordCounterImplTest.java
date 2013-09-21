package ru.ncedu.java.tasks;

import junit.framework.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: dborody
 * Date: 9/19/13
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class WordCounterImplTest {

    @org.junit.Test
    public void testSetGetText() throws Exception {
        final String s = "a a b c d e";
        WordCounter counter = new WordCounterImpl();

        counter.setText("a a b c d e");
        Assert.assertEquals(s, counter.getText());
    }

    @org.junit.Test
    public void testGetWordCounts() throws Exception {
        WordCounter counter = new WordCounterImpl();

        try {
            counter.getWordCounts();
            Assert.fail();
        } catch (Exception e) {

        }

        final String s = "<> a    <a>  A <not-a-word>  b\t<not-really>> \t  d c  d a";
        counter.setText(s);

        Map<String, Long> expected = new HashMap<String, Long>();
        expected.put("a", new Long(3));
        expected.put("b", new Long(1));
        expected.put("c", new Long(1));
        expected.put("d", new Long(2));

        Assert.assertEquals(expected, counter.getWordCounts());
    }

    @org.junit.Test
    public void testGetWordCountsSorted() throws Exception {
        WordCounter counter = new WordCounterImpl();

        try {
            counter.getWordCounts();
            Assert.fail();
        } catch (Exception e) {

        }

        final String s = "a     A  aB\t\t  d Ac  d a";
        counter.setText(s);

        List<Map.Entry<String, Long>> expected = new LinkedList<Map.Entry<String, Long>>();
        expected.add(new AbstractMap.SimpleEntry<String, Long>("a", new Long(3)));
        expected.add(new AbstractMap.SimpleEntry<String, Long>("d", new Long(2)));
        expected.add(new AbstractMap.SimpleEntry<String, Long>("ab", new Long(1)));
        expected.add(new AbstractMap.SimpleEntry<String, Long>("ac", new Long(1)));

        Assert.assertEquals(expected, counter.getWordCountsSorted());
    }

    @org.junit.Test
    public void testPrintWordCounts() throws Exception {
        WordCounter counter = new WordCounterImpl();

        final String s = "\t\n a  d D  A  d aB\t\t  d Ac  d a";
        counter.setText(s);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(buffer);

        counter.printWordCounts(ps);
        Assert.assertEquals("d 5\n" +
                "a 3\n" +
                "ac 1\n" +
                "ab 1\n", buffer.toString());
    }

    @org.junit.Test
    public void testPrintWordCountsSorted() throws Exception {
        WordCounter counter = new WordCounterImpl();

        final String s = " a  d D  A  d aB\t\t  d Ac  d a\n\n\n  ";
        counter.setText(s);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(buffer);

        counter.printWordCountsSorted(ps);
        Assert.assertEquals("d 5\n" +
                "a 3\n" +
                "ab 1\n" +
                "ac 1\n", buffer.toString());
    }
}
