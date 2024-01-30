package trivia;

final class Player {
    private final String name;
    private int place = 0;
    private int purse = 0;
    private boolean inPenaltyBox = false;
    private boolean isGettingOutOfPenaltyBox = true;

    Player(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public int place() {
        return place;
    }

    public void move(int to) {
        this.place = to;
    }

    public int coins() {
        return purse;
    }

    public void addCoin() {
        purse++;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }

    public void gettingOutOfPenaltyBox() {
        isGettingOutOfPenaltyBox = true;
    }

    public void notGettingOutOfPenaltyBox() {
        isGettingOutOfPenaltyBox = false;
    }

    public boolean inPenaltyBox() {
        return inPenaltyBox;
    }

    public void moveToPenaltyBox() {
        this.inPenaltyBox = true;
    }
}
