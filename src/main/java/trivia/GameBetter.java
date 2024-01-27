package trivia;

import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements IGame {
    final Players players = new Players();

    final LinkedList<String> popQuestions = new LinkedList<>();
    final LinkedList<String> scienceQuestions = new LinkedList<>();
    final LinkedList<String> sportsQuestions = new LinkedList<>();
    final LinkedList<String> rockQuestions = new LinkedList<>();

    public GameBetter() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public boolean add(String playerName) {
        players.add(playerName);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public void roll(int roll) {
        Player currentPlayer = players.currentPlayer();
        System.out.println(currentPlayer.name() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.inPenaltyBox()) {
            if (roll % 2 != 0) {
                System.out.println(currentPlayer.name() + " is getting out of the penalty box");
                currentPlayer.gettingOutOfPenaltyBox();
            } else {
                System.out.println(currentPlayer.name() + " is not getting out of the penalty box");
                currentPlayer.notGettingOutOfPenaltyBox();
                return;
            }
        }
        movePlayerAndAskQuestion(roll, currentPlayer);
    }

    private void movePlayerAndAskQuestion(int roll, Player currentPlayer) {
        currentPlayer.move(currentPlayer.place() + roll);
        if (currentPlayer.place() > 11) currentPlayer.move(currentPlayer.place() - 12);

        System.out.println(currentPlayer.name()
                + "'s new location is "
                + currentPlayer.place());

        String currentCategory = currentCategory(currentPlayer.place());
        System.out.println("The category is " + currentCategory);

        if (currentCategory.equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (currentCategory.equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory.equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory.equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }


    private static String currentCategory(int place) {
        if (place % 4 == 0) {
            return "Pop";
        } else if (place % 4 == 1) {
            return "Science";
        } else if (place % 4 == 2) {
            return "Sports";
        }
        return "Rock";
    }

    /**
     * @return false if game is won
     */
    public boolean wasCorrectlyAnswered() {
        Player currentPlayer = players.currentPlayer();

        boolean isGameStillInPlay = true;
        if (currentPlayer.isNotInPenaltyBox() || currentPlayer.isGettingOutOfPenaltyBox()) {
            System.out.println("Answer was correct!!!!");
            isGameStillInPlay = addCoinToPurseAndCheckIfGameIsStillInPlay(currentPlayer);
        }
        players.nextPlayer();
        return isGameStillInPlay;
    }

    private boolean addCoinToPurseAndCheckIfGameIsStillInPlay(Player currentPlayer) {
        currentPlayer.addCoinToPurse();
        System.out.println(currentPlayer.name()
                + " now has "
                + currentPlayer.purse()
                + " Gold Coins.");

        return !currentPlayer.didPlayerWin();
    }

    public boolean wrongAnswer() {
        Player currentPlayer = players.currentPlayer();
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.name() + " was sent to the penalty box");
        currentPlayer.setInPenaltyBox(true);

        players.nextPlayer();
        return true;
    }
}
