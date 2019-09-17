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
    public int isFinished() {
        return this.game.players.currentPlayer;
    }

    @Override
    public Steps getCurrentStep() {
        return Steps.FINISHED;
    }
}
