package trivia;

import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements IGame {
    final Players players = new Players();
    final GameBoard gameBoard = new GameBoard();
    final QuestionDeck questionDeck = new QuestionDeck();

    public GameBetter() {
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

        Category currentCategory = gameBoard.currentCategory(currentPlayer.place());
        System.out.println("The category is " + currentCategory.getName());
        System.out.println(questionDeck.fetchQuestion(currentCategory));
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
