package trivia;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> playerList = new ArrayList<Player>();
    private int currentPlayerIndex = 0;

    public boolean add(String playerName) {
        playerList.add(new Player(playerName, 0, 0, false));
        return true;
    }

    public Player currentPlayer() {
        return playerList.get(currentPlayerIndex);
    }

    public int size() {
        return playerList.size();
    }

    public void nextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex == playerList.size()) {
            currentPlayerIndex = 0;
        }
    }
}
