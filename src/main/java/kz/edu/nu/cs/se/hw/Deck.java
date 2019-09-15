package kz.edu.nu.cs.se.hw;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Deck {
    public Stack<String> cards;

    final String[] suits = new String[] { "C", "D", "H", "S", "M" };
    final String[] ranks = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

    public Deck() {
        this.cards = new Stack<>();
        for (String suit : suits)
            for (String rank : ranks) {
                cards.push(rank + suit);
            }
    }

    public void rearrange(String card) {
        cards.remove(card);
        cards.push(card);
    }

    public void shuffle(Long rand) {
        Collections.shuffle(cards, new Random(rand));
    }

    public int size(){
        return cards.size();
    }

    public String draw(){
        return cards.pop();
    }

    public void push(String card) {
        this.cards.push(card);
    }

}
