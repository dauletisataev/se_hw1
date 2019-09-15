package kz.edu.nu.cs.se.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card implements Comparable {
    public int rank;
    public char suit;

    List<String> ranks =  new ArrayList<>(Arrays.asList("A","2","3","4","5","6","7","8","9","10","J","Q","K"));
    public Card(String card) {
        this.rank = extractRank(card);
        this.suit = extractSuit(card);
    }

    int extractRank(String card) {
        return ranks.indexOf(card.substring(0, card.length()-1));
    }

    char extractSuit(String card) {
        return card.charAt(card.length()-1);
    }

    @Override
    public int compareTo(Object o) {
        return rank - ((Card) o).rank;
    }


}
