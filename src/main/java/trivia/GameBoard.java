package trivia;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {

    Map<Integer, Category> gameBoard = new HashMap<>();

    public GameBoard() {
        for (int place = 0; place < 12; place++) {
            if (place % 4 == 0) {
                gameBoard.put(place, Category.POP);
            } else if (place % 4 == 1) {
                gameBoard.put(place, Category.SCIENCE);
            } else if (place % 4 == 2) {
                gameBoard.put(place, Category.SPORTS);
            } else {
                gameBoard.put(place, Category.ROCK);
            }
        }
    }

    public Category currentCategory(int place) {
        return gameBoard.get(place);
    }
}
