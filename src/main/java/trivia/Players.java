package trivia;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> playerList = new ArrayList<>();
    private int currentPlayerIndex = -1;

    public boolean add(String playerName) {
        playerList.add(new Player(playerName));
        return true;
    }

    public Player currentPlayer() {
        return playerList.get(currentPlayerIndex);
    }

    public int size() {
        return playerList.size();
    }

    public Player nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % playerList.size();
        return currentPlayer();
    }
}
