package trivia;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {

    Map<Integer, String> gameBoard = new HashMap<>();

    public GameBoard() {
        for (int place = 0; place < 12; place++) {
            if (place % 4 == 0) {
                gameBoard.put(place, "Pop");
            } else if (place % 4 == 1) {
                gameBoard.put(place, "Science");
            } else if (place % 4 == 2) {
                gameBoard.put(place, "Sports");
            } else {
                gameBoard.put(place, "Rock");
            }
        }
    }

    public String currentCategory(int place) {
        return gameBoard.get(place);
    }
}
