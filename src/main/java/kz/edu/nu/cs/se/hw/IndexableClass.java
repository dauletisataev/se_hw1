package kz.edu.nu.cs.se.hw;

public class IndexableClass implements Indexable
{
    String entry;
    int line;

    public IndexableClass(String entry, int line) {
        this.entry = entry;
        this.line = line;
    }

    @Override
    public String getEntry() {
        return entry;
    }

    @Override
    public int getLineNumber() {
        return line;
    }

    @Override
    public String toString() {
        return "{ "+entry+": "+line+" }";
    }
}
