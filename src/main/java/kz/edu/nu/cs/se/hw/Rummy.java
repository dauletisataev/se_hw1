package kz.edu.nu.cs.se.hw;

import kz.edu.nu.cs.se.hw.States.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Starter code for a class that implements the <code>PlayableRummy</code>
 * interface. A constructor signature has been added, and method stubs have been
 * generated automatically in eclipse.
 *
 * Before coding you should verify that you are able to run the accompanying
 * JUnit test suite <code>TestRummyCode</code>. Most of the unit tests will fail
 * initially.
 *
 * @see PlayableRummy
 * @see TestRummyCode
 *
 */
public class Rummy implements PlayableRummy {
    String[] player_names;
    public Deck deck;
    public Players players;
    public Stack<String> discard_pile;
    public ArrayList<ArrayList<String>> melds;
    public Step state;

    public Rummy(String... players) {
        if(players.length > 6)
            throw new RummyException("Players should be between 2 and 6", RummyException.EXPECTED_FEWER_PLAYERS);
        else if(players.length <2)
            throw new RummyException("Players should be between 2 and 6", RummyException.NOT_ENOUGH_PLAYERS);
        this.player_names = players;
        this.deck = new Deck();
        this.players = new Players(players);
        this.discard_pile = new Stack<>();
        this.melds = new ArrayList<>();

        this.state = new WaitingState(this);
    }

    @Override
    public String[] getPlayers() {
        return player_names;
    }

    @Override
    public int getNumPlayers() {
        return player_names.length;
    }

    @Override
    public int getCurrentPlayer() {
        return this.players.currentPlayer;
    }

    @Override
    public int getNumCardsInDeck() {
        return deck.size();
    }

    @Override
    public int getNumCardsInDiscardPile() {
        return discard_pile.size();
    }

    @Override
    public String getTopCardOfDiscardPile() {
        if(discard_pile.size() > 0)
            return discard_pile.peek();
        throw new RummyException("discard pile is empty", RummyException.NOT_VALID_DISCARD);
    }

    @Override
    public String[] getHandOfPlayer(int player) {
        return this.players.getHandOfPlayer(player);
    }

    @Override
    public int getNumMelds() {
        return melds.size();
    }

    @Override
    public String[] getMeld(int i) {
        if(i >= melds.size())
            throw new RummyException("NOT_VALID_INDEX_OF_MELD", RummyException.NOT_VALID_INDEX_OF_MELD);
        List<String> meld = melds.get(i);
        return meld.toArray(new String[meld.size()]);
    }

    @Override
    public void rearrange(String card) {
        this.state.rearrange(card);
    }

    @Override
    public void shuffle(Long l) {
        this.state.shuffle(l);

    }

    @Override
    public Steps getCurrentStep() {
        return this.state.getCurrentStep();
    }

    @Override
    public int isFinished() {
        return this.state.isFinished();
    }

    @Override
    public void initialDeal() {
        this.state.initialDeal();
    }

    @Override
    public void drawFromDiscard() {
        this.state.drawFromDiscard();
    }

    @Override
    public void drawFromDeck() {
        this.state.drawFromDeck();
    }

    @Override
    public void meld(String... cards) {
        this.state.meld(cards);
    }

    @Override
    public void addToMeld(int meldIndex, String... cards) {
        this.state.addToMeld(meldIndex, cards);
    }

    @Override
    public void declareRummy() {
        this.state.declareRummy();
    }

    @Override
    public void finishMeld() {
        this.state.finishMeld();
    }

    @Override
    public void discard(String card) {
        this.state.discard(card);
    }


    public void setState(Steps step) {
        switch (step){
            case WAITING:
                this.state = new WaitingState(this);
                break;
            case DRAW:
                this.state = new DrawState(this);
                break;
            case MELD:
                this.state = new MeldState(this);
                break;
            case RUMMY:
                this.state = new RummyState(this);
                break;
            case DISCARD:
                this.state = new DiscardState(this);
                break;
            case FINISHED:
                this.state = new FinishedState(this);
                break;
        }

    }



}
