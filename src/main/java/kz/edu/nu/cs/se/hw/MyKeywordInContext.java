package kz.edu.nu.cs.se.hw;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyKeywordInContext implements KeywordInContext {
    private static final String newLine = "\n";
    private static final String html_start = "<!DOCTYPE html>\n" +
            "<html><head><meta charset=\"UTF-8\"></head><body>\n" +
            "<div>\n";
    private static final String html_start_kwic = "<!DOCTYPE html>\n" +
            "<html><head><meta charset=\"UTF-8\"></head><body>\n" +
            "<div style=\"text-align:center;line-height:1.6\">\n";
    private static final String html_end = "</div></body></html>";

    private String name;
    private String pathstring;
    private ArrayList<Indexable> indexes;
    private ArrayList<String> lines;
    private String[] wordsToIgnore;

    public MyKeywordInContext(String name, String pathstring) {
        this.name = name;
        this.pathstring = pathstring;
        this.indexes = new ArrayList<>();
        this.lines = new ArrayList<>();

        this.wordsToIgnore = new String[] {"ourselves", "out", "over", "own", "same", "shan't", "she", "she'd", "she'll", "she's", "should", "shouldn't", "so", "some", "such", "than", "that", "that's", "the", "their", "theirs", "them", "themselves", "then", "there", "there's", "these", "they", "they'd", "they'll", "they're", "they've", "this", "those", "through", "to", "too", "under", "until", "up", "very", "was", "wasn't", "we", "we'd", "we'll", "we're", "we've", "were", "weren't", "what", "what's", "when", "when's", "where", "where's", "which", "while", "who", "who's", "whom", "why", "why's", "with", "won't", "would", "wouldn't", "you", "you'd", "you'll", "you're", "you've", "your", "yours", "yourself", "yourselves", "a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any", "are", "aren't", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both", "but", "by", "can't", "cannot", "could", "couldn't", "did", "didn't", "do", "does", "doesn't", "doing", "don't", "down", "during", "each", "few", "for", "from", "further", "had", "hadn't", "has", "hasn't", "have", "haven't", "having", "he", "he'd", "he'll", "he's", "her", "here", "here's", "hers", "herself", "him", "himself", "his", "how", "how's", "i", "i'd", "i'll", "i'm", "i've", "if", "in", "into", "is", "isn't", "it", "it's", "its", "itself", "let's", "me", "more", "most", "mustn't", "my", "myself", "no", "nor", "not", "of", "off", "on", "once", "only", "or", "other", "ought", "our", "ours"};
    }

    @Override
    public int find(String word) {
        for(Indexable indexable: indexes) {
            if(indexable.getEntry().toLowerCase().compareToIgnoreCase(word) == 0) {
                return indexable.getLineNumber();
            }
        }
        return -1;
    }

    @Override
    public Indexable get(int i) {
        for(Indexable indexable: indexes) {
            if(indexable.getLineNumber() == i) {
                return indexable;
            }
        }
        return null;
    }

    @Override
    public void txt2html() {

        try{
            FileInputStream fstream = new FileInputStream(this.pathstring);

            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;

            FileOutputStream out = new FileOutputStream(this.name+".html");
            out.write(html_start.getBytes());
            int line_counter = 0;
            while ((strLine = br.readLine()) != null) {
                line_counter++;
                out.write(strLine.getBytes());
                String line_ender = "<span id=\"line_"+line_counter+"\">&nbsp&nbsp["+line_counter+"]</span><br>";
                out.write(line_ender.getBytes());
                out.write(newLine.getBytes());
            }
            out.write(html_end.getBytes());

            fstream.close();
            out.close();
        } catch (Exception exception){
            System.out.println(exception);
        }
    }

    @Override
    public void indexLines() {
        try{
            FileInputStream fstream = new FileInputStream(this.pathstring);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            int lineCounter = 0;
            while ((strLine = br.readLine()) != null) {
                lineCounter++;
                lines.add(strLine);

                Pattern word_pattern = Pattern.compile("\\b[a-zA-Z]+\\b");
                Matcher matcher = word_pattern.matcher(strLine);

                while(matcher.find()) {
                    String word = strLine.substring(matcher.start(), matcher.end());
                    if(Arrays.asList(this.wordsToIgnore).contains(word.toLowerCase()))
                        continue;
                    Indexable indexedWord = new IndexableClass(word, lineCounter);
                    if(indexes.contains(indexedWord)) continue;
                    indexes.add(indexedWord);
                }
            }

            indexes.sort(new Comparator<Indexable>() {
                @Override
                public int compare(Indexable o1, Indexable o2) {
                    return o1.getEntry().toLowerCase().compareToIgnoreCase(o2.getEntry());
                }
            });
            fstream.close();
        } catch (Exception exception){
            System.out.println(exception);
        }
    }

    @Override
    public void writeIndexToFile() {
        try {
            FileOutputStream out = new FileOutputStream("kwic-"+this.name + ".html");
            out.write(html_start_kwic.getBytes());

            for (Indexable indexable : indexes) {
                String lineToWrite = this.lines.get(indexable.getLineNumber()-1);

                Pattern pattern = Pattern.compile(indexable.getEntry());
                Matcher matcher = pattern.matcher(lineToWrite);

                System.out.println("printing for = "+indexable.getEntry());
                System.out.println(lineToWrite);
                while(matcher.find()){
                    System.out.println("printing");
                    out.write(
                            (lineToWrite.substring(0, matcher.start())
                                    +"<a href=\""+this.name+".html#line_"+indexable.getLineNumber()+"\">"+indexable.getEntry().toUpperCase()+"</a> "
                                    +lineToWrite.substring(matcher.end())
                            ).getBytes());
                    out.write(("<br>").getBytes());
                    out.write(newLine.getBytes());
                }

            }
            out.write(html_end.getBytes());
            out.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
