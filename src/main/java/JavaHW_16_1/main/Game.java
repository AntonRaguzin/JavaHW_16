package JavaHW_16_1.main;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Player> registered = new ArrayList<>();

    public void register(Player player) {

        registered.add(player);
    }

    public List allRegistered() {
        return registered;
    }

    public int round(String playerName1, String playerName2) {

        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);

        if(player1 == null) {
            throw new NotRegisteredException(
                    "Пользователь c именем - " + playerName1 + " не зарегистрирован"
            );
        }
        if(player2 == null) {
            throw new NotRegisteredException(
                    "Пользователь c именем - " + playerName2 + " не зарегистрирован"
            );
        }
        if (player1.getStrength() > player2.getStrength()) {
            return 1;
        } else if (player1.getStrength() < player2.getStrength()) {
            return 2;
        }
        return 0;
    }

    public Player findByName(String name) {
        for (Player player : registered) {
            if (player.getName() == name) {
                return player;
            }
        }
        return null;
    }
}
