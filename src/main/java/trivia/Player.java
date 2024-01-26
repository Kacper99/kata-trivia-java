package trivia;

import java.util.Objects;

final class Player {
    private final String name;
    private int place;
    private int purse;
    private boolean inPenaltyBox;

    Player(String name, int place, int purse, boolean inPenaltyBox) {
        this.name = name;
        this.place = place;
        this.purse = purse;
        this.inPenaltyBox = inPenaltyBox;
    }

    public String name() {
        return name;
    }

    public int place() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int purse() {
        return purse;
    }

    public void addCoinToPurse() {
        purse++;
    }

    public boolean inPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
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
