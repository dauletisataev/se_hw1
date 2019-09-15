package kz.edu.nu.cs.se.hw.States;

import kz.edu.nu.cs.se.hw.Rummy;
import kz.edu.nu.cs.se.hw.RummyException;
import kz.edu.nu.cs.se.hw.Step;
import kz.edu.nu.cs.se.hw.Steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class RummyState extends Step {
    Rummy game;

    public RummyState(Rummy game) {
        this.game = game;
    }

    @Override
    public void rearrange(String card) {
        throw new RummyException("EXPECTED_WAITING_STEP", RummyException.EXPECTED_WAITING_STEP);
    }

    @Override
    public void shuffle(Long l) {
        throw new RummyException("EXPECTED_WAITING_STEP", RummyException.EXPECTED_WAITING_STEP);
    }

    @Override
    public int isFinished() {
        return -1;
    }

    @Override
    public void initialDeal() {
        throw new RummyException("EXPECTED_WAITING_STEP", RummyException.EXPECTED_WAITING_STEP);
    }

    @Override
    public void drawFromDiscard() {
        throw new RummyException("EXPECTED_DRAW_STEP", RummyException.EXPECTED_DRAW_STEP);
    }

    @Override
    public void drawFromDeck() {
        throw new RummyException("EXPECTED_DRAW_STEP", RummyException.EXPECTED_DRAW_STEP);
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
        for(String card: cards) {
            this.game.players.drawFromCurrentPlayer(card);
        }
        this.game.melds.get(meldIndex).addAll(new ArrayList<>(Arrays.asList(cards)));
        if(this.game.players.getHandOfCurrentPlayer().length == 0 || this.game.players.getHandOfCurrentPlayer().length == 1)
            this.game.setState(Steps.FINISHED);
    }

    @Override
    public void declareRummy() {
        throw new RummyException("game must be in rummy step", RummyException.EXPECTED_MELD_STEP);
    }

    @Override
    public void finishMeld() {
        this.game.setState(Steps.DISCARD);
        if(this.game.players.getHandOfCurrentPlayer().length > 1)
            throw new RummyException("RUMMY_NOT_DEMONSTRATED", RummyException.RUMMY_NOT_DEMONSTRATED);
    }

    @Override
    public void discard(String card) {
        throw new RummyException("not in discard step", RummyException.EXPECTED_DISCARD_STEP);
    }

    @Override
    public Steps getCurrentStep() {
        return Steps.RUMMY;
    }
}
