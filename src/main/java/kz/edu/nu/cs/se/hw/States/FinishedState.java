package kz.edu.nu.cs.se.hw.States;

import kz.edu.nu.cs.se.hw.Rummy;
import kz.edu.nu.cs.se.hw.RummyException;
import kz.edu.nu.cs.se.hw.Step;
import kz.edu.nu.cs.se.hw.Steps;

public class FinishedState extends Step {
    Rummy game;

    public FinishedState(Rummy game) {
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
        return this.game.players.currentPlayer;
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
        throw new RummyException("EXPECTED_MELD_STEP_OR_RUMMY_STEP", RummyException.EXPECTED_MELD_STEP_OR_RUMMY_STEP);
    }

    @Override
    public void addToMeld(int meldIndex, String... cards) {
        throw new RummyException("EXPECTED_MELD_STEP_OR_RUMMY_STEP", RummyException.EXPECTED_MELD_STEP_OR_RUMMY_STEP);
    }

    @Override
    public void declareRummy() {
        throw new RummyException("EXPECTED_MELD_STEP", RummyException.EXPECTED_MELD_STEP);
    }

    @Override
    public void finishMeld() {
        throw new RummyException("EXPECTED_MELD_STEP_OR_RUMMY_STEP", RummyException.EXPECTED_MELD_STEP_OR_RUMMY_STEP);
    }

    @Override
    public void discard(String card) {
        throw new RummyException("EXPECTED discard state", RummyException.EXPECTED_DISCARD_STEP);
    }

    @Override
    public Steps getCurrentStep() {
        return Steps.FINISHED;
    }
}
