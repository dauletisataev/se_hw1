package kz.edu.nu.cs.se.hw.States;

import kz.edu.nu.cs.se.hw.Rummy;
import kz.edu.nu.cs.se.hw.RummyException;
import kz.edu.nu.cs.se.hw.Step;
import kz.edu.nu.cs.se.hw.Steps;

public class DrawState extends Step {
    Rummy game;

    public DrawState(Rummy game) {
        this.game = game;
    }

    @Override
    public void drawFromDiscard() {
        this.game.players.addToHandOfCurrentPlayer(this.game.discard_pile.pop(), true);
        this.game.setState(Steps.MELD);
    }

    @Override
    public void drawFromDeck() {
        if(this.game.deck.size() == 0) { //Deck is empty
            while(!this.game.discard_pile.empty())
                this.game.deck.push(this.game.discard_pile.pop());

            this.game.discard_pile.push(this.game.deck.draw());

        }

        String drawnCardFromDeck = this.game.deck.draw();
        this.game.players.addToHandOfCurrentPlayer(drawnCardFromDeck, false);

        this.game.setState(Steps.MELD);
    }

    @Override
    public Steps getCurrentStep() {
        return Steps.DRAW;
    }
}
