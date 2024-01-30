package trivia;

// REFACTOR ME
public class GameBetter implements IGame {
    public static final int BOARD_SIZE = 12;
    private final Players players = new Players();
    private final GameBoard gameBoard = new GameBoard();
    private final QuestionDeck questionDeck = new QuestionDeck();

    public boolean add(String playerName) {
        players.add(playerName);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public void roll(int roll) {
        Player currentPlayer = players.nextPlayer();

        System.out.println(currentPlayer.name() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.inPenaltyBox()) {
            handlePenaltyBox(currentPlayer, roll);
        } else {
            movePlayer(currentPlayer, roll);
            askQuestion(currentPlayer);
        }
    }

    private void handlePenaltyBox(Player currentPlayer, int roll) {
        if (roll % 2 != 0) {
            System.out.println(currentPlayer.name() + " is getting out of the penalty box");
            currentPlayer.gettingOutOfPenaltyBox();

            movePlayer(currentPlayer, roll);
            askQuestion(currentPlayer);
        } else {
            System.out.println(currentPlayer.name() + " is not getting out of the penalty box");
            currentPlayer.notGettingOutOfPenaltyBox();
        }
    }

    private static void movePlayer(Player currentPlayer, int roll) {
        currentPlayer.move((currentPlayer.place() + roll) % BOARD_SIZE);
        System.out.println(currentPlayer.name()
                + "'s new location is "
                + currentPlayer.place());
    }

    private void askQuestion(Player currentPlayer) {
        Category currentCategory = gameBoard.currentCategory(currentPlayer.place());
        System.out.println("The category is " + currentCategory.getName());
        System.out.println(questionDeck.fetchQuestion(currentCategory));
    }

    /**
     * @return false if game is won by the current player
     */
    public boolean wasCorrectlyAnswered() {
        Player currentPlayer = players.currentPlayer();

        if (currentPlayer.inPenaltyBox() && !currentPlayer.isGettingOutOfPenaltyBox()) {
            return true;
        }

        System.out.println("Answer was correct!!!!");
        addCoin(currentPlayer);
        return currentPlayer.coins() < 6;

    }

    private static void addCoin(Player player) {
        player.addCoin();
        System.out.println(player.name()
                + " now has "
                + player.coins()
                + " Gold Coins.");
    }

    public boolean wrongAnswer() {
        Player currentPlayer = players.currentPlayer();
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.name() + " was sent to the penalty box");

        currentPlayer.moveToPenaltyBox();
        return true;
    }
}
