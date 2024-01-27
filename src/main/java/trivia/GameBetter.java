package trivia;

// REFACTOR ME
public class GameBetter implements IGame {
    private final Players players = new Players();
    private final GameBoard gameBoard = new GameBoard();
    private final QuestionDeck questionDeck = new QuestionDeck();

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
            handlePenaltyBox(roll, currentPlayer);
        } else {
            movePlayer(roll, currentPlayer);
            askQuestion(currentPlayer);
        }
    }

    private void handlePenaltyBox(int roll, Player currentPlayer) {
        if (roll % 2 != 0) {
            System.out.println(currentPlayer.name() + " is getting out of the penalty box");
            currentPlayer.gettingOutOfPenaltyBox();
            movePlayer(roll, currentPlayer);
            askQuestion(currentPlayer);
        } else {
            System.out.println(currentPlayer.name() + " is not getting out of the penalty box");
            currentPlayer.notGettingOutOfPenaltyBox();
        }
    }

    private void askQuestion(Player currentPlayer) {
        Category currentCategory = gameBoard.currentCategory(currentPlayer.place());
        System.out.println("The category is " + currentCategory.getName());
        System.out.println(questionDeck.fetchQuestion(currentCategory));
    }

    private static void movePlayer(int roll, Player currentPlayer) {
        currentPlayer.move(currentPlayer.place() + roll);
        if (currentPlayer.place() > 11) currentPlayer.move(currentPlayer.place() - 12);

        System.out.println(currentPlayer.name()
                + "'s new location is "
                + currentPlayer.place());
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
