package kz.edu.nu.cs.se.hw.States;

import kz.edu.nu.cs.se.hw.Rummy;
import kz.edu.nu.cs.se.hw.RummyException;
import kz.edu.nu.cs.se.hw.Step;
import kz.edu.nu.cs.se.hw.Steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MeldState extends Step {
    Rummy game;

    public MeldState(Rummy game) {
        this.game = game;
    }

    @Override
    public void meld(String... cards) {
        this.game.players.checkCardsForValidMeld(cards);

        for(String card: cards){
            this.game.players.drawFromCurrentPlayer(card);
        }

        this.game.melds.add(new ArrayList<>(Arrays.asList(cards)));

        if(this.game.players.getHandOfCurrentPlayer().length == 0)
            this.game.setState(Steps.FINISHED);
    }

    @Override
    public void addToMeld(int meldIndex, String... cards) {
        for(String card: cards) {
            this.game.players.drawFromCurrentPlayer(card);
        }
        this.game.melds.get(meldIndex).addAll(new ArrayList<>(Arrays.asList(cards)));

        if(this.game.players.getHandOfCurrentPlayer().length == 0)
            this.game.setState(Steps.FINISHED);
    }

    @Override
    public void declareRummy() {
        this.game.setState(Steps.RUMMY);
    }

    @Override
    public void finishMeld() {
        this.game.setState(Steps.DISCARD);
    }

    @Override
    public Steps getCurrentStep() {
        return Steps.MELD;
    }
}
