package kz.edu.nu.cs.se.hw.States;

import kz.edu.nu.cs.se.hw.Rummy;
import kz.edu.nu.cs.se.hw.RummyException;
import kz.edu.nu.cs.se.hw.Step;
import kz.edu.nu.cs.se.hw.Steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MeldState extends Step {
    Rummy game;

    public MeldState(Rummy game) {
        this.game = game;
    }

    @Override
    public void meld(String... cards) {
        this.game.players.checkCardsForValidMeld(cards, true);

        for(String card: cards){
            this.game.players.drawFromCurrentPlayer(card);
        }

        this.game.melds.add(new ArrayList<>(Arrays.asList(cards)));

        if(this.game.players.getHandOfCurrentPlayer().length == 0)
            this.game.setState(Steps.FINISHED);

        this.game.players.currentPlayerCanDeclareRummy = false;
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

        if(this.game.players.getHandOfCurrentPlayer().length == 0)
            this.game.setState(Steps.FINISHED);

        this.game.players.currentPlayerCanDeclareRummy = false;
    }

    @Override
    public void declareRummy() {
        if(!this.game.players.currentPlayerCanDeclareRummy)
            throw new RummyException("can not declare rummy", RummyException.EXPECTED_MELD_STEP);
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
