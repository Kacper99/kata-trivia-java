package trivia;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {

    private final Map<Integer, Category> gameBoard = new HashMap<>();

    public GameBoard() {
        gameBoard.put(0, Category.POP);
        gameBoard.put(1, Category.SCIENCE);
        gameBoard.put(2, Category.SPORTS);
        gameBoard.put(3, Category.ROCK);
        gameBoard.put(4, Category.POP);
        gameBoard.put(5, Category.SCIENCE);
        gameBoard.put(6, Category.SPORTS);
        gameBoard.put(7, Category.ROCK);
        gameBoard.put(8, Category.POP);
        gameBoard.put(9, Category.SCIENCE);
        gameBoard.put(10, Category.SPORTS);
        gameBoard.put(11, Category.ROCK);
    }

    public Category currentCategory(int place) {
        return gameBoard.get(place);
    }
}
