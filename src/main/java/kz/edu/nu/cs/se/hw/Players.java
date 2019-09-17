package kz.edu.nu.cs.se.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Players {
    public ArrayList<ArrayList<String>> cards;
    public int currentPlayer;
    public boolean lastCardDrawnFromDiscard = false;
    public boolean currentPlayerCanDeclareRummy = true;
    public String[] names;

    public Players(String... players) {
        this.names = players;
        this.cards = new ArrayList<>();
        this.currentPlayer = 0;
        for(int i=0; i<players.length; i++)
            this.cards.add(new ArrayList<>());
    }

    public String[] getHandOfPlayer(int playerIndex) {
        if(playerIndex >= cards.size())
            throw new RummyException("NOT_VALID_INDEX_OF_PLAYER", RummyException.NOT_VALID_INDEX_OF_PLAYER);

        List<String> player_cards = cards.get(playerIndex);
        return player_cards.toArray(new String[player_cards.size()]);
    }

    public String[] getHandOfCurrentPlayer() {
        List<String> player_cards = cards.get(currentPlayer);
        return player_cards.toArray(new String[player_cards.size()]);
    }

    public String lastCardOfCurrentPlayer() {
        List<String> player_cards = cards.get(currentPlayer);
        return player_cards.get(player_cards.size()-1);
    }

    public String drawFromCurrentPlayer(String card) {
        cards.get(currentPlayer).remove(card);
        return card;
    }

    public void addToHandOfPlayer(int playerIndex, String card, boolean isFromDiscard) {
        if(playerIndex >= cards.size())
            throw new RummyException("NOT_VALID_INDEX_OF_PLAYER", RummyException.NOT_VALID_INDEX_OF_PLAYER);

        lastCardDrawnFromDiscard = isFromDiscard;
        cards.get(playerIndex).add(card);
    }

    public void addToHandOfCurrentPlayer(String card, boolean isFromDiscard) {
        this.lastCardDrawnFromDiscard = isFromDiscard;
        cards.get(currentPlayer).add(card);
    }

    public boolean currentPlayerHasCard(String card) {
        return cards.get(currentPlayer).contains(card);
    }

    public void moveToNextPlayer() {
        currentPlayer = currentPlayer == names.length-1 ? 0 : currentPlayer+1;
    }

    public boolean checkCardsForValidMeld(String[] cards, Boolean checkHandOfPlayer) {
        if(cards.length < 3)
            throw new RummyException("not enough cards to form MELD", RummyException.EXPECTED_CARDS);

        boolean sameSuit = true;
        boolean sameRank = true;

        if(checkHandOfPlayer)
            if(!currentPlayerHasCard(cards[0]))
                   throw new RummyException("cards can not form MELD", RummyException.EXPECTED_CARDS);

        Arrays.sort(cards, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (new Card(o1)).compareTo(new Card(o2));
            }
        });

        for(int i=1; i<cards.length; i++){
            if(checkHandOfPlayer)
                if(!currentPlayerHasCard(cards[i]))
                    throw new RummyException("cards can not form MELD", RummyException.EXPECTED_CARDS);

            Card firstCard = new Card(cards[i-1]);
            Card secondCard = new Card(cards[i]);

            if(sameRank)
                sameRank = firstCard.rank == secondCard.rank;

            if(sameSuit)
                if(firstCard.suit != secondCard.suit)
                    sameSuit = false;
                else if (secondCard.rank - firstCard.rank != 1)
                        sameSuit = false;



            if(!sameRank && !sameSuit){
                if(i<=3)
                    throw new RummyException("not enough cards to form MELD", RummyException.NOT_VALID_MELD);
                else
                    throw new RummyException("not enough cards to form MELD", RummyException.EXPECTED_CARDS);
            }
        }

        return true;
    }

    public void checkCardCanBeAddedToMeld(List<String> meld, String card) {
        Card cardToAdd = new Card(card);
        Card bottomCard = new Card(meld.get(meld.size()-1));
        Card topCard = new Card(meld.get(0));

        if( bottomCard.suit == topCard.suit) { // this means the current sequence is kind of same suit
            if(cardToAdd.suit != bottomCard.suit)
                throw new RummyException("the given card can not be added to a given meld", RummyException.NOT_VALID_MELD);
            if(cardToAdd.rank-topCard.rank != 1 && bottomCard.rank-cardToAdd.rank != 1)
                throw new RummyException("the given card can not be added to a given meld", RummyException.NOT_VALID_MELD);
        }
        else if(bottomCard.rank == topCard.rank) {
            if(bottomCard.rank  != topCard.rank)
                throw new RummyException("the given card can not be added to a given meld", RummyException.NOT_VALID_MELD);
        }

        throw new RummyException("meld is already invalid", RummyException.NOT_VALID_MELD);
    }
}
