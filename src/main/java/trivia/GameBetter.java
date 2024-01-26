package trivia;

import java.util.ArrayList;
import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements IGame {

    ArrayList<Player> players = new ArrayList<>();

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public GameBetter() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName, 0, 0, false));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        Player currentPlayer = players.get(this.currentPlayer);
        System.out.println(currentPlayer.name() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.inPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer.name() + " is getting out of the penalty box");
                currentPlayer.setPlace(currentPlayer.place() + roll);
                if (currentPlayer.place() > 11) currentPlayer.setPlace(currentPlayer.place() - 12);

                System.out.println(currentPlayer.name()
                        + "'s new location is "
                        + currentPlayer.place());
                System.out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                System.out.println(currentPlayer.name() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            currentPlayer.setPlace(currentPlayer.place() + roll);
            if (currentPlayer.place() > 11) currentPlayer.setPlace(currentPlayer.place() - 12);

            System.out.println(currentPlayer.name()
                    + "'s new location is "
                    + currentPlayer.place());
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        Player currentPlayer = players.get(this.currentPlayer);
        if (currentPlayer.place() == 0) return "Pop";
        if (currentPlayer.place() == 4) return "Pop";
        if (currentPlayer.place() == 8) return "Pop";
        if (currentPlayer.place() == 1) return "Science";
        if (currentPlayer.place() == 5) return "Science";
        if (currentPlayer.place() == 9) return "Science";
        if (currentPlayer.place() == 2) return "Sports";
        if (currentPlayer.place() == 6) return "Sports";
        if (currentPlayer.place() == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        Player currentPlayer = players.get(this.currentPlayer);
        if (currentPlayer.inPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                currentPlayer.addCoinToPurse();
                System.out.println(currentPlayer.name()
                        + " now has "
                        + currentPlayer.purse()
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                this.currentPlayer++;
                if (this.currentPlayer == players.size()) this.currentPlayer = 0;

                return winner;
            } else {
                this.currentPlayer++;
                if (this.currentPlayer == players.size()) this.currentPlayer = 0;
                return true;
            }


        } else {

            System.out.println("Answer was corrent!!!!");
            currentPlayer.addCoinToPurse();
            System.out.println(currentPlayer.name()
                    + " now has "
                    + currentPlayer.purse()
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            this.currentPlayer++;
            if (this.currentPlayer == players.size()) this.currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        Player currentPlayer = players.get(this.currentPlayer);
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.name() + " was sent to the penalty box");
        currentPlayer.setInPenaltyBox(true);

        this.currentPlayer++;
        if (this.currentPlayer == players.size()) this.currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        Player currentPlayer = players.get(this.currentPlayer);
        return !(currentPlayer.purse() == 6);
    }
}
