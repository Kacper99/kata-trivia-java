package trivia;

import java.util.Objects;

final class Player {
    private final String name;
    private int place;
    private int purse;
    private boolean inPenaltyBox;
    private boolean isGettingOutOfPenaltyBox;

    Player(String name) {
        this.name = name;
        this.place = 0;
        this.purse = 0;
        this.inPenaltyBox = false;
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

    public void addCoinToPurse() {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Player) obj;
        return Objects.equals(this.name, that.name) &&
                this.place == that.place &&
                this.purse == that.purse &&
                this.inPenaltyBox == that.inPenaltyBox;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place, purse, inPenaltyBox);
    }

    @Override
    public String toString() {
        return "Player[" +
                "name=" + name + ", " +
                "place=" + place + ", " +
                "purse=" + purse + ", " +
                "inPenaltyBox=" + inPenaltyBox + ']';
    }
}
