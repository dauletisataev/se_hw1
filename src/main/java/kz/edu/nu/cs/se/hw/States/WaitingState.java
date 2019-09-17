package kz.edu.nu.cs.se.hw.States;

import kz.edu.nu.cs.se.hw.Rummy;
import kz.edu.nu.cs.se.hw.RummyException;
import kz.edu.nu.cs.se.hw.Step;
import kz.edu.nu.cs.se.hw.Steps;

public class WaitingState extends Step {
    Rummy game;

    public WaitingState(Rummy game) {
        this.game = game;
    }

    @Override
    public void rearrange(String card) {
        this.game.deck.rearrange(card);
    }

    @Override
    public void shuffle(Long l) {
        this.game.deck.shuffle(l);
    }

    @Override
    public int isFinished() {
        return -1;
    }

    @Override
    public void initialDeal() {
        int cardsPerPlayer;
        if (this.game.getNumPlayers() == 2)
            cardsPerPlayer = 10;
        else if(this.game.getNumPlayers() == 3 || this.game.getNumPlayers() == 4)
            cardsPerPlayer = 7;
        else
            cardsPerPlayer = 6;

        for(int i=0; i<cardsPerPlayer; i++)
            for(int player_index=0; player_index<this.game.getNumPlayers(); player_index++)
                this.game.players.addToHandOfPlayer(player_index, this.game.deck.draw(), false);

        this.game.discard_pile.push(this.game.deck.draw());
        this.game.players.currentPlayer = 0;
        this.game.setState(Steps.DRAW);
    }

    @Override
    public Steps getCurrentStep() {
        return Steps.WAITING;
    }
}
