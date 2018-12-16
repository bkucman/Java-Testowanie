package Projekt1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    Player player;

    @BeforeEach
    void init(){
        player = new Player("Maks","blabla","2","1","1");
    }

    @Test
    void playerToStringTest(){

        String actual = player.toString();

        String expected = "Nick: Maks, Wins: 2, Losts: 1, Tie: 1";

        assertEquals(expected,actual);
    }

    @Test
    void increaseWinsPlayer(){

        player.setWins();

        assertEquals("3",player.getWins());
    }

    @Test
    void increaseLostsPlayer(){

        player.setLosts();

        assertEquals("2",player.getLosts());
    }

    @Test
    void increaseTiesPlayer(){

        player.setTie();

        assertEquals("2",player.getTie());
    }

    @Test
    void setAndGetNick(){
        player.setNick("Maksio");

        assertEquals("Maksio",player.getNick());
    }

    @Test
    void setAndGetPasswrod(){
        player.setPassword("nowe");

        assertEquals("nowe",player.getPassword());
    }

    @Test
    void compareToSecondPlayerMoreWinsTest(){
        Player p1 = new Player("A","B","2","1","0");
        Player p2 = new Player("B","A","4","1","0");

        int actual = p1.compareTo(p2);

        assertEquals(1,actual);
    }
    @Test
    void compareToFirstPlayerMoreWinsTest(){
        Player p1 = new Player("A","B","4","1","0");
        Player p2 = new Player("B","A","2","1","0");

        int actual = p1.compareTo(p2);

        assertEquals(-1,actual);
    }

    @Test
    void compareToSameWinsFirstPlayerLessLostTest(){
        Player p1 = new Player("A","B","2","1","0");
        Player p2 = new Player("B","A","2","2","0");

        int actual = p1.compareTo(p2);

        assertEquals(-1,actual);
    }

    @Test
    void compareToSameWinsSecondPlayerLessLostTest(){
        Player p1 = new Player("A","B","2","3","0");
        Player p2 = new Player("B","A","2","2","0");

        int actual = p1.compareTo(p2);

        assertEquals(1,actual);
    }

    @Test
    void compareToSameWinsAndLostsFirstPlayerLessTieTest(){
        Player p1 = new Player("A","B","2","2","0");
        Player p2 = new Player("B","A","2","2","3");

        int actual = p1.compareTo(p2);

        assertEquals(-1,actual);
    }

    @Test
    void compareToSameWinsAndLostsSecondPlayerLessTieTest(){
        Player p1 = new Player("A","B","2","2","5");
        Player p2 = new Player("B","A","2","2","3");

        int actual = p1.compareTo(p2);

        assertEquals(1,actual);
    }

    @Test
    void compareToAllSameTest(){
        Player p1 = new Player("A","B","2","2","5");
        Player p2 = new Player("B","A","2","2","5");

        int actual = p1.compareTo(p2);

        assertEquals(0,actual);
    }


}
