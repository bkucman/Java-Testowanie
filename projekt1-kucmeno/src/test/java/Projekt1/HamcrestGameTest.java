package Projekt1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestGameTest {

    private Game game;
    private int rows,columns;

    @BeforeEach
    void init(){
        rows = 6;
        columns = 7;
        game = new Game(rows,columns,true);
        game.players.add(new Player("Aaaa","pass","1","3","1"));
        game.players.add(new Player("Bbbb","haslo","1","1","1"));
    }

    // Dla każdego rozmiaru
    @Test
    void checkWinVerticalTest(){

        for(int i = rows-1;i>=rows-4;i--){
            game.board[i][columns-1] = 1;
        }

        boolean actual = game.checkWinVertical(1,rows-4,columns-1);
        assertThat(actual,is(equalTo(true)));
    }

    // Dla każdego rozmiaru
    @Test
    void checkWinHorizontalTest(){

        for(int i = 0;i<4;i++){
            game.board[0][i] = 1;
        }

        boolean actual = game.checkWinHorizontal(1,0,columns-4);
        assertThat(actual,is(equalTo(true)));
    }

    // Dla każdego rozmiaru
    @Test
    void checkWinDiagonallyAscTest(){

        int col = columns-1;
        for(int i = 0;i<4;i++,col--){
            game.board[i][col] = 1;
        }

        boolean actual = game.checkWinDiagonallyAsc(1,0,columns-1);

        assertThat(actual,equalTo(true));
    }
    //Dla każdego rozmiaru
    @Test
    void checkWinDiagonallyDescTest(){
        int col = 0;
        for(int i = 0;i<4;i++,col++){
            game.board[i][col] = 1;
        }

        boolean actual = game.checkWinDiagonallyDesc(1,2,2);

        assertThat(actual,is(true));
    }

    @Test
    void checkSizeOfListPlayersTest(){

        game.players.add(new Player("Mati","koko","0","0","0"));

        assertThat(game.players,hasSize(3));
    }

    @Test
    void initTestCheckIfListIncludeNewPlayerTest(){
        game.playersInit(1,"MArek","Pass",false);

        assertThat(game.players,hasSize(3));
    }

    @Test
    void checkWinMethod_WinDiagonallyAsc_Test(){
        int col = 0;
        for(int i = rows-1;i>=rows-4;i--,col++){
            game.board[i][col] = 1;
        }

        String actual = game.checkWin(1,rows-2,1);

        assertThat(actual,anyOf(containsString("Player1")));
    }

    @Test
    void checkWinMethod_WinDiagonallyAscCheckStartAndEndString_Test(){
        int col = 0;
        for(int i = rows-1;i>=rows-4;i--,col++){
            game.board[i][col] = 1;
        }

        String actual = game.checkWin(1,rows-2,1);

        assertThat(actual,allOf(startsWith("Player1"),endsWith("Asc)")));
    }

    @Test
    void checkWinMethod_WinHorizontal_Test(){

        for(int i = columns-1;i>=columns-4;i--){
            game.board[0][i] = 1;
        }
        String actual = game.checkWin(1,0,columns-3); //spradza gdy ostani żeton dodany ma dobre żetony z lewj i prawej

        assertThat(actual,endsWith("(horizontal)"));
    }

    @Test
    void checkWinMethod_WinHorizontalValueWin_Test(){

        for(int i = columns-1;i>=columns-4;i--){
            game.board[0][i] = 1;
        }
        String actual = game.checkWin(1,0,columns-3); //spradza gdy ostani żeton dodany ma dobre żetony z lewj i prawej

        assertThat(actual,startsWith("Player1"));
    }

    @Test
    void searchPlayerCheckReturnVaule(){
        Player actual = game.serachPlayer("Aaaa","pass");

        assertThat(actual,notNullValue());
    }

    @Test
    void searchPlayerChecClassVaule(){
        Player actual = game.serachPlayer("Aaaa","pass");

        assertThat(actual,isA(Player.class));
    }

    @Test
    void searchPlayerCeckReturnVaule1(){
        game.player1 = game.players.get(0);
        game.player2 = game.players.get(1);
        game.fileName = "testList.csv";
        game.playersSet = 2;
        for(int i = rows-1;i>=rows-3;i--){
            game.movePlayer(1,0);
            game.movePlayer(2,2);

        }
        game.movePlayer(1,0);
        int actual = Integer.parseInt(game.player1.getWins());

        assertThat(actual,greaterThan(1));
    }

    @AfterEach
    void tearDownAll(){
        game = null;
    }
}
