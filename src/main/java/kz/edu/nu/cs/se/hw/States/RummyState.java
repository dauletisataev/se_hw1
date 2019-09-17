package kz.edu.nu.cs.se.hw.States;

import kz.edu.nu.cs.se.hw.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class RummyState extends Step {
    Rummy game;

    public RummyState(Rummy game) {
        this.game = game;
    }

    @Override
    public void meld(String... cards) {
        this.game.players.checkCardsForValidMeld(cards);

        for(String card: cards){
            this.game.players.drawFromCurrentPlayer(card);
        }

        this.game.melds.add(new ArrayList<>(Arrays.asList(cards)));

        if(this.game.players.getHandOfCurrentPlayer().length == 0 || this.game.players.getHandOfCurrentPlayer().length == 1)
            this.game.setState(Steps.FINISHED);

    }

    @Override
    public void addToMeld(int meldIndex, String... cards) {
        for(String card: cards)
            canCardBeAddedToMeld(meldIndex, card);

        for(String card: cards) {
            this.game.players.drawFromCurrentPlayer(card);
        }

        this.game.melds.get(meldIndex).addAll(new ArrayList<>(Arrays.asList(cards)));
        if(this.game.players.getHandOfCurrentPlayer().length == 0 || this.game.players.getHandOfCurrentPlayer().length == 1)
            this.game.setState(Steps.FINISHED);
    }

    void canCardBeAddedToMeld(int meldIndex, String card) {
        Card cardToAdd = new Card(card);
        Card bottomCard = new Card(this.game.melds.get(meldIndex).get(this.game.melds.get(meldIndex).size()-1));
        Card topCard = new Card(this.game.melds.get(meldIndex).get(0));

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

    @Override
    public void finishMeld() {
        this.game.setState(Steps.DISCARD);
        if(this.game.players.getHandOfCurrentPlayer().length > 1)
            throw new RummyException("RUMMY_NOT_DEMONSTRATED", RummyException.RUMMY_NOT_DEMONSTRATED);
    }

    @Override
    public Steps getCurrentStep() {
        return Steps.RUMMY;
    }
}
