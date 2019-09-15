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

    int compareToCards(String c1, String c2){
        List<String> ranks =  new ArrayList<>(Arrays.asList("A","2","3","4","5","6","7","8","9","10","J","Q","K"));
        int rank1 = ranks.indexOf(c1.substring(0, c1.length()-1));
        int rank2 = ranks.indexOf(c2.substring(0, c2.length()-1));

        return rank1 - rank2;
    }


}
