package kz.edu.nu.cs.se.hw.States;

import kz.edu.nu.cs.se.hw.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RummyState extends Step {
    Rummy game;

    public RummyState(Rummy game) {
        this.game = game;
    }

    @Override
    public void meld(String... cards) {
        this.game.players.checkCardsForValidMeld(cards, true);

        for(String card: cards){
            this.game.players.drawFromCurrentPlayer(card);
        }

        this.game.melds.add(new ArrayList<>(Arrays.asList(cards)));

        if(this.game.players.getHandOfCurrentPlayer().length == 0 || this.game.players.getHandOfCurrentPlayer().length == 1)
            this.game.setState(Steps.FINISHED);

    }

    @Override
    public void addToMeld(int meldIndex, String... cards) {
        List<String> currentMeld = new ArrayList<>(this.game.melds.get(meldIndex));
        currentMeld.addAll(new ArrayList<>(Arrays.asList(cards)));

        this.game.players.checkCardsForValidMeld(currentMeld.toArray(new String[currentMeld.size()]), false);

        for(String card: cards) {
            this.game.players.drawFromCurrentPlayer(card);
        }

        this.game.melds.get(meldIndex).addAll(new ArrayList<>(Arrays.asList(cards)));
        if(this.game.players.getHandOfCurrentPlayer().length == 0 || this.game.players.getHandOfCurrentPlayer().length == 1)
            this.game.setState(Steps.FINISHED);
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
