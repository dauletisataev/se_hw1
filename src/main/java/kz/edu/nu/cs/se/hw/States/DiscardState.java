package kz.edu.nu.cs.se.hw.States;

import kz.edu.nu.cs.se.hw.Rummy;
import kz.edu.nu.cs.se.hw.RummyException;
import kz.edu.nu.cs.se.hw.Step;
import kz.edu.nu.cs.se.hw.Steps;

import java.util.Arrays;

public class DiscardState extends Step {
    Rummy game;

    public DiscardState(Rummy game) {
        this.game = game;
    }

    @Override
    public void discard(String card) {
        if(!this.game.players.currentPlayerHasCard(card))
            throw new RummyException("The player does not have such card", RummyException.EXPECTED_CARDS);

        String lastDrawnCard = this.game.players.lastCardOfCurrentPlayer();
        if(this.game.players.lastCardDrawnFromDiscard && card.compareTo(lastDrawnCard) == 0)
            throw new RummyException("The player may not discard a card drawn from the discard pile on the same", RummyException.NOT_VALID_DISCARD);

        this.game.discard_pile.push(this.game.players.drawFromCurrentPlayer(card));

        if(this.game.players.getHandOfCurrentPlayer().length == 0) {
            this.game.setState(Steps.FINISHED);
        } else {this.game.players.moveToNextPlayer();
            this.game.setState(Steps.DRAW);
        }
    }

    @Override
    public Steps getCurrentStep() {
        return Steps.DISCARD;
    }
}
