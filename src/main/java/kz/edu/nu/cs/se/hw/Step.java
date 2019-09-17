package kz.edu.nu.cs.se.hw;

public abstract class Step {
    public void rearrange(String card) {
        throw new RummyException("EXPECTED_WAITING_STEP", RummyException.EXPECTED_WAITING_STEP);
    }

    public void shuffle(Long l){
        throw new RummyException("EXPECTED_WAITING_STEP", RummyException.EXPECTED_WAITING_STEP);
    }

    public int isFinished() {
        return -1;
    }

    public void initialDeal(){
        throw new RummyException("EXPECTED_WAITING_STEP", RummyException.EXPECTED_WAITING_STEP);

    }

    public void drawFromDiscard(){
        throw new RummyException("EXPECTED_DRAW_STEP", RummyException.EXPECTED_DRAW_STEP);
    }

    public void drawFromDeck(){
        throw new RummyException("EXPECTED_DRAW_STEP", RummyException.EXPECTED_DRAW_STEP);
    }

    public void meld(String... cards){
        throw new RummyException("EXPECTED_MELD_STEP_OR_RUMMY_STEP", RummyException.EXPECTED_MELD_STEP_OR_RUMMY_STEP);
    }

    public void addToMeld(int meldIndex, String... cards){

    }

    public void declareRummy(){
        throw new RummyException("EXPECTED_MELD_TEP", RummyException.EXPECTED_MELD_STEP);
    }

    public void finishMeld(){
        throw new RummyException("EXPECTED_MELD_STEP_OR_RUMMY_STEP", RummyException.EXPECTED_MELD_STEP_OR_RUMMY_STEP);
    }

    public void discard(String card){
        throw new RummyException("EXPECTED discard state", RummyException.EXPECTED_DISCARD_STEP);
    }

    public abstract Steps getCurrentStep();

}
