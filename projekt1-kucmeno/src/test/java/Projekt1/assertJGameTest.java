package Projekt1;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

import static org.assertj.core.api.Assertions.*;

@DisplayName("AssertJ Tests.")
public class assertJGameTest {

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

    @Test
    public void checkInitBoardVaulesTest() {

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(game.win).as("Who win").isEqualTo(0);
        softly.assertThat(game.lastRun).as("Who the last one has moved").isEqualTo(-1);
        softly.assertThat(game.rows).as("Rows").isEqualTo(rows);
        softly.assertThat(game.columns).as("Columns").isEqualTo(columns);

        softly.assertAll();
    }

    @Test
    @Disabled("Make only for check how SofAssertion show message about failed.")
    public void checkInitBoardVaulesWrongTest() {

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(game.win).as("Who win").isEqualTo(1);
        softly.assertThat(game.lastRun).as("Who the last one has moved").isEqualTo(1);
        softly.assertThat(game.rows).as("Rows").isEqualTo(rows-1);
        softly.assertThat(game.columns).as("Columns").isEqualTo(columns-1);

        softly.assertAll();
    }

    @Test
    void checkWinOnInitBoardTest(){
        assertThat(game.win).isEqualTo(0);
    }

    @Test
    void checkInitGameWrongNumbOfRowsTest(){

        assertThatIllegalArgumentException().isThrownBy(() ->{
                game = new Game(3,6,false);});

    }

    @Test
    void checkInitGameWrongNumbOfColumnTest(){

        Throwable thrown = catchThrowable(()-> {
            game = new Game(6,3,false);
        });
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void testException() {
        assertThatIllegalArgumentException().isThrownBy(() -> { game = new Game(3,6,false); })
                .withMessage("%s", "Size of rows and columns must be higher than 4")
                .withMessageContaining("column");
    }

    // Dla każdego rozmiaru
    @Test
    void checkWinVerticalTest(){

        for(int i = rows-1;i>=rows-3;i--){
            game.board[i][columns-1] = 1;
        }

        boolean actual = game.checkWinVertical(1,rows-3,columns-1);
        assertThat(actual).isFalse();
    }
    // Dla każdego rozmiaru
    @Test
    void checkWinHorizontalTest(){

        for(int i = 0;i<4;i++){
            game.board[0][i] = 1;
        }

        boolean actual = game.checkWinHorizontal(1,0,columns-4);
        assertThat(actual).isTrue();
    }
    // Dla każdego rozmiaru
    @Test
    void checkWinDiagonallyAscTest(){

        int col = columns-1;
        for(int i = 0;i<4;i++,col--){
            game.board[i][col] = 1;
        }

        boolean actual = game.checkWinDiagonallyAsc(1,0,columns-1);

        assertThat(actual).isEqualTo(true);
    }

    //Dla każdego rozmiaru
    @Test
    void checkWinDiagonallyDescTest(){
        int col = 0;
        for(int i = 0;i<4;i++,col++){
            game.board[i][col] = 1;
        }

        boolean actual = game.checkWinDiagonallyDesc(1,2,2);

        assertThat(actual).isTrue();
    }
    @Test
    void checkAddedPlayersTest(){
        assertThat(game.players).extracting("nick", "password")
                .contains(tuple("Aaaa","pass"),
                        tuple("Bbbb","haslo"));
    }

    @Test
    void checkAddNewPlayerAndCheckTest(){
        game.playersInit(1,"Marko","silne",false);

        assertThat(game.players).extracting("nick", "password")
                .contains(tuple("Aaaa","pass"),
                        tuple("Bbbb","haslo"),
                        tuple("Marko","silne"));
    }

    @Test
    void checkSizeOfListPlayersTest(){

        game.players.add(new Player("Mati","koko","0","0","0"));

        assertThat(game.players).hasSize(3);
    }

    @Test
    void checkWinMethod_WinDiagonallyAsc_Test(){
        int col = 0;
        for(int i = rows-1;i>=rows-4;i--,col++){
            game.board[i][col] = 1;
        }

        String actual = game.checkWin(1,rows-2,1);

        assertThat(actual).startsWith("Player1")
                .endsWith("Asc)");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addToken.csv")
    void movePlayer6x7Test(int player,int col,String end){
        game = new Game(6,7,false);
        int[] p1 = { 0, 0, 1 ,0, 2 ,3};
        int[] p2 = { 1, 2, 2, 3, 3, 6};
        for(int i = 0;i<6;i++){
            game.movePlayer(1,p1[i]);
            game.movePlayer(2,p2[i]);
        }
        // na potrzeby testu zmieniam wartość lastRun sprawdzam tylko wygrane a nie koljeność
        game.lastRun = -1;
        String actual = game.movePlayer(player,col);

        assertThat(actual).startsWith("Player"+player)
                .endsWith(end);
    }

    @Test
    void movePlayerSimulateTie4x4Test(){
        game = new Game(4,4,true);
        game.players.add(new Player("Aaaa","pass","1","3","1"));
        game.players.add(new Player("Bbbb","haslo","1","1","1"));

        game.player1 = game.players.get(0);
        game.player2 = game.players.get(1);
        game.playersSet = 2;
        game.fileName = "testList.csv";

        int[] p1 = { 2, 3, 0 ,1, 2 ,3, 0, 1};
        int[] p2 = { 0, 1, 2, 3, 0, 1, 2, 1};
        for(int i = 0;i<8;i++){
            game.movePlayer(1,p1[i]);
            game.movePlayer(2,p2[i]);
        }

        String actual = game.movePlayer(2,3);
        String expected = "Board is full. Tie.";

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Player {0}.")
    @ValueSource(strings = { ("Aaaa"),("Bbbb")})
    void test(String player){

        assertThat(game.players).extracting("nick")
                .contains(player);



    }











}
