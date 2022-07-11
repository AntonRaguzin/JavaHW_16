package JavaHW_16_1.main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class GameTests {

    public Player p1 = new Player(1, "User1", 10);
    public Player p2 = new Player(2, "User2", 7);
    public Player p3 = new Player(3, "User3", 5);
    public Player p4 = new Player(4, "User4", 3);
    public Player p5 = new Player(5, "User5", 10);

    public Game game = new Game();

    @Test
    public void shouldAddPlayersToRegisteredList() {
        game.register(p1);
        game.register(p2);
        game.register(p3);
        game.register(p4);
        game.register(p5);

        List<Player> expected = Arrays.asList(p1, p2, p3, p4, p5);
        List<Player> actual = game.allRegistered();

        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldFindByName() {

        game.register(p1);
        game.register(p2);
        game.register(p3);
        game.register(p4);

        Player expected = p1;
        Player actual = game.findByName("User1");

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldNotFindByName() {

        game.register(p1);
        game.register(p2);
        game.register(p3);
        game.register(p4);

        Player expected = null;
        Player actual = game.findByName("User5");

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldNotPlayRoundIfFirstNotRegistered() {
        game.register(p1);
        game.register(p3);

        assertThrows(NotRegisteredException.class, () -> {
            game.round("User2", "User3");
        });
    }
    @Test
    public void shouldNotPlayRoundIfSecondNotRegistered() {
        game.register(p1);
        game.register(p3);

        assertThrows(NotRegisteredException.class, () -> {
            game.round("User1", "User2");
        });
    }

    @Test
    public void firstShouldWin() {
        game.register(p1);
        game.register(p2);

        assertEquals(1, game.round("User1", "User2"));
    }

    @Test
    public void secondShouldWin() {
        game.register(p2);
        game.register(p3);

        assertEquals(2, game.round("User3", "User2"));
    }

    @Test
    public void noOneWon() {
        game.register(p1);
        game.register(p5);

        assertEquals(0, game.round("User5", "User1"));
    }
}

