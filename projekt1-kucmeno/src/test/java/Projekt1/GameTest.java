package Projekt1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;


public class GameTest {

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
    void checkInitGameValueInBoardTest() {
        assertEquals(0, game.board[0][0]);
    }
    @Test
    void checkInitGameWinTest() {
        assertEquals(0,game.win);
    }
    @Test
    void checkInitGameLastRunTest() {
        assertEquals(-1,game.lastRun);
    }
    @Test
    void checkInitGameRowsTest() {
        assertEquals(rows,game.rows);
    }
    @Test
    void checkInitGameColumnsTest() {
        assertEquals(columns,game.columns);
    }
    @Test
    void checkInitGameCountAddedTokensTest() {
        assertEquals(0,game.countAddedTokens);
    }
    @Test
    void checkInitGameWrongNumbOfRowsTest(){
        assertThrows(IllegalArgumentException.class, () -> {
            game = new Game(3,6,false);
        },"Should be exceptions IllegalArgument for row < 4");
    }
    @Test
    void checkInitGameWrongNumbOfColumnsTest(){
        assertThrows(IllegalArgumentException.class, () -> {
            game = new Game(6,3,false);
        },"Should be exceptions IllegalArgument for row < 4");
    }

    // Testy zrobione na początku dla jendego rozmiaru planszy zostawiłem jako testy szczególnych przypadków ustawień żetonów
    @Test
    void checkWinVerticalPositive6x7Test(){
        game = new Game(6,7,true);
        game.board[5][0] = game.board[4][0] = game.board[4][1] =
                game.board[4][2] = game.board[3][1] = game.board[2][1] = game.board[1][1] = 1;
        game.board[5][1] = game.board[5][2] = game.board[5][3] = game.board[3][0] = game.board[3][2] = 2;

        boolean actual = game.checkWinVertical(1,1,1);

        assertEquals(true,actual);
    }

    @Test
    void checkWinVerticalPositiveCheckReturn6x7Test(){
        game = new Game(6,7,true);
        game.board[5][0] = game.board[4][0] = game.board[4][1] =
                game.board[4][2] = game.board[3][1] = game.board[2][1] = game.board[1][1] = 1;
        game.board[5][1] = game.board[5][2] = game.board[5][3] = game.board[3][0] = game.board[3][2] = 2;

        boolean actual = game.checkWinVertical(1,1,1);

        assertTrue(actual);
    }

    @Test
    void checkWinVerticalPositiveLeftDownCorner6x7Test(){
        game = new Game(6,7,true);
        game.board[5][6] = game.board[4][6] = game.board[3][6] =
                game.board[2][6] = game.board[3][5] = 2;
        game.board[5][3] = game.board[5][4] = game.board[5][5] = game.board[4][5]  = 1;

        boolean actual = game.checkWinVertical(2,2,6);
        assertEquals(true,actual);
    }

    @Test
    //@Disabled("Disabled while function not implemented")
    void checkWinVerticalNonPositive6x7Test(){
        game = new Game(6,7,true);
        game.board[5][0] = game.board[4][0] = game.board[4][1] =
                game.board[4][2] = game.board[3][1] = game.board[2][1] = 1;
        game.board[5][1] = game.board[5][2] = game.board[5][3] = game.board[3][0] = game.board[3][2] = 2;

        boolean actual = game.checkWinVertical(1,2,1);
        assertFalse(actual);
    }

    @Test
    void checkInWinHorizontalPositive6x7Test(){
        game = new Game(6,7,true);
        game.board[5][0] = game.board[4][0] = game.board[4][2]= game.board[4][1] = game.board[4][3] =
                game.board[5][3] = 1;
        game.board[3][0] = game.board[3][1] = game.board[5][1] = game.board[5][2] = game.board[3][3] = 2;

        boolean actual = game.checkWinHorizontal(1,4,2);
        assertEquals(true,actual);
    }

    @Test
    void checkInWinHorizontalNonPositive6x7Test(){
        game = new Game(6,7,true);
        game.board[5][0] = game.board[4][0] = game.board[4][2]= game.board[4][1] = game.board[4][3] =
                game.board[5][3] = game.board[4][4] = game.board[4][5] = game.board[4][6] = 1;
        game.board[3][0] = game.board[3][1] = game.board[5][1] = game.board[5][2] = game.board[3][3] = 2;

        boolean actual = game.checkWinHorizontal(1,4,3);
        assertEquals(true,actual);
    }

    @Test
    void checkWinHorizontalReturnTrue6x7Test(){
        game = new Game(6,7,true);
        game.board[5][6] = game.board[4][6] = game.board[4][4] =
                game.board[3][6] = game.board[3][5] = 1;
        game.board[5][3] = game.board[5][4] = game.board[5][5] = game.board[4][5] = game.board[5][2]  = 2;

        boolean actual = game.checkWinHorizontal(2,5,2);
        assertTrue(actual);
    }

    @Test
    void checkWinHorizontalReturnFalse6x7Test(){
        game = new Game(6,7,true);
        game.board[5][6] = game.board[4][6] = game.board[4][4] =
                game.board[2][6] = game.board[3][5] = game.board[4][3] = 2;
        game.board[5][3] = game.board[5][4] = game.board[5][5] = game.board[4][5]  = 1;

        boolean actual = game.checkWinHorizontal(2,5,3);
        assertFalse(actual);
    }

    @Test
    void checkWinDiagonallyAscPositive6x7Test(){
        game = new Game(6,7,true);
        game.board[5][0] = game.board[4][0] = game.board[4][2]= game.board[4][1] = game.board[4][3] =
                game.board[5][3] = game.board[3][2] = game.board[2][3] = 1;
        game.board[3][0] = game.board[3][1] = game.board[5][1] = game.board[5][2] = game.board[3][3] = 2;

        boolean actual = game.checkWinDiagonallyAsc(1,3,2);
        assertEquals(true,actual);
    }

    @Test
    void checkWinDiagonallyAscNonPositive6x7Test(){
        game = new Game(6,7,true);
        game.board[5][0] = game.board[4][0] = game.board[4][2]= game.board[4][1] = game.board[4][3] =
                game.board[5][3] = game.board[3][2] = 1;
        game.board[3][0] = game.board[3][1] = game.board[5][1] = game.board[5][2] = game.board[3][3] = 2;

        boolean actual = game.checkWinDiagonallyAsc(1,3,2);
        assertNotSame(true,actual);
    }

    @Test
    void checkWinDiagonallyAscReturnTrue6x7Test(){
        game = new Game(6,7,true);
        game.board[5][3] = game.board[4][3]= game.board[4][4] = game.board[3][5] =
                game.board[3][6] = game.board[4][6] = game.board[2][6] = 1;
        game.board[5][2] = game.board[5][4] = game.board[5][5] = game.board[5][6] = game.board[4][5] =
                game.board[3][3] = game.board[3][4] = 2;

        boolean actual = game.checkWinDiagonallyAsc(1,3,5);
        assertTrue(actual);
    }

    @Test
    void checkWinDiagonallyAscReturnFalse6x7Test(){
        game = new Game(6,7,true);
        game.board[5][3] = game.board[4][3]= game.board[4][4] = game.board[3][5] =
                game.board[3][6] = game.board[4][6] = 1;
        game.board[5][2] = game.board[5][4] = game.board[5][5] = game.board[5][6] = game.board[4][5] =
                game.board[3][3] = game.board[3][4] = 2;

        boolean actual = game.checkWinDiagonallyAsc(1,3,5);
        assertFalse(actual);
    }

    @Test
    void checkWinDiagonallyDescPositive6x7Test(){
        game = new Game(6,7,true);
        game.board[5][0] = game.board[4][0] = game.board[5][2] = game.board[3][2]=
                game.board[4][3]= game.board[3][3] = game.board[5][4] = game.board[4][4] = 1;
        game.board[3][0] = game.board[5][1] = game.board[4][1] = game.board[3][1] = game.board[2][1] =
                game.board[4][2] = game.board[2][0] = game.board[5][3] = 2;

        boolean actual = game.checkWinDiagonallyDesc(2,2,0);
        assertEquals(true,actual);

    }

    @Test
    void checkWinDiagonallyDescNonPositive6x7Test(){
        game = new Game(6,7,true);
        game.board[5][0] = game.board[4][0] = game.board[5][2] = game.board[3][2] = game.board[4][1] =
                game.board[4][3] = game.board[5][4] = game.board[4][4] = game.board[5][3]= 1;
        game.board[3][0] = game.board[5][1]  = game.board[3][4] = game.board[3][1] = game.board[2][1] =
                game.board[4][2] = game.board[2][0]  = 2;

        boolean actual = game.checkWinDiagonallyDesc(2,2,0);
        assertNotEquals(true,actual);

    }


    @Test
    //@Disabled("Disabled while function not implemented")
    void checkWinDiagonallyDescReturnTrue6x7Test(){
        game = new Game(6,7,true);
        game.board[5][3] = game.board[4][2] = game.board[4][3]= game.board[4][4] = game.board[3][5] =
                game.board[3][6] = game.board[4][6] = 1;
        game.board[5][2] = game.board[5][4] = game.board[5][5] = game.board[5][6] = game.board[4][5] =
                game.board[3][3] = game.board[3][4] =game.board[2][3]=game.board[2][6]= 2;

        boolean actual = game.checkWinDiagonallyDesc(2,2,3);
        assertTrue(actual);
    }

    @Test
    //@Disabled("Disabled while function not implemented")
    void checkWinDiagonallyDescReturnFalse6x7Test(){
        game = new Game(6,7,true);
        game.board[5][3] = game.board[4][2] = game.board[4][3]= game.board[4][4] = game.board[3][5] =
                game.board[3][6] = game.board[4][6] = 1;
        game.board[5][2] = game.board[5][4] = game.board[5][5] = game.board[5][6] = game.board[4][5] =
                game.board[3][3] = game.board[3][4] =game.board[2][6]= 2;

        boolean actual = game.checkWinDiagonallyAsc(1,3,4);
        assertFalse(actual);
    }

    // Testy dla getPositonInColumn działają dla dowolnego rozmiaru

    @Test
    void checkgetPositioInColumn_EmptyColumntTest(){

        int actual = game.getPositionInColumn(0);

        assertEquals(rows-1,actual);
    }

    @Test
    void checkgetPositioInColumn_NotEmptyColumntTest(){
        game.board[rows-1][0] = 1; game.board[rows-2][0] = 2 ;

        int actual = game.getPositionInColumn(0);

        assertEquals(rows-3,actual);
    }

    @Test
    void checkgetPositioInColumn_NotEmptyColumnt1Test(){
        game.board[rows-1][0] = 1;

        int actual = game.getPositionInColumn(0);

        assertNotEquals(rows-1,actual);
    }

    @Test
    void checkgetPositioInColumn_FullColumntTest(){
        for(int i = rows-1;i>=0;i--){
            game.board[i][0] = 1;
            game.board[i][0] = 2;
        }
        int actual = game.getPositionInColumn(0);
        assertEquals(-1,actual);
    }

    @Test
    void checkgetPositioInColumn_FullColumn1Test(){
        for(int i = rows-1;i>=0;i--){
            game.board[i][0] = 1;
            game.board[i][0] = 2;
        }

        int actual = game.getPositionInColumn(0);
        assertNotEquals(0,actual);
    }
    // Dla dowolnego rozmiaru
    @Test
    void checkWinMethod_WinVertical_Test(){

        for(int i = rows-1;i>=rows-4;i--){
            game.board[i][0] = 1;
        }
        String actual = game.checkWin(1,rows-4,0);
        String expected = "Player1 victory !! (vertical)";
        assertEquals(expected,actual);
    }
    // Dla dowolnego rozmiaru
    @Test
    void checkWinMethod_WinVerticalValueWin_Test(){

        for(int i = rows-1;i>=rows-4;i--){
            game.board[i][0] = 1;
        }

        game.checkWin(1,rows-4,0);

        assertEquals(1,game.win);
    }
    // Dla dowolnego rozmiaru
    @Test
    void checkWinMethod_WinHorizontal_Test(){

        for(int i = columns-1;i>=columns-4;i--){
            game.board[0][i] = 1;
        }
        String actual = game.checkWin(1,0,columns-3); //spradza gdy ostani żeton dodany ma dobre żetony z lewj i prawej
        String expected = "Player1 victory !! (horizontal)";

        assertEquals(expected,actual);
    }
    // Dla dowolnego rozmiaru
    @Test
    void checkWinMethod_WinHorizontalValueWin_Test(){

        for(int i = columns-1;i>=columns-4;i--){
            game.board[0][i] = 1;
        }
        game.checkWin(1,0,columns-3); //spradza gdy ostani żeton dodany ma dobre żetony z lewj i prawej

        assertEquals(1,game.win);
    }
    // Dla dowolnego rozmiaru
    @Test
    void checkWinMethod_WinDiagonallyAsc_Test(){
        int col = 0;
        for(int i = rows-1;i>=rows-4;i--,col++){
            game.board[i][col] = 1;
        }

        String actual = game.checkWin(1,rows-2,1);
        String expected = "Player1 victory !! (diagonallyAsc)";

        assertEquals(expected,actual);
    }
    // Dla dowolnego rozmiaru
    @Test
    void checkWinMethod_WinDiagonallyAscValueWin_Test(){
        int col = 0;
        for(int i = rows-1;i>=rows-4;i--,col++){
            game.board[i][col] = 1;
        }

        game.checkWin(1,rows-2,1);

        assertEquals(1,game.win);
    }
    // Dla dowolnego rozmiaru
    @Test
    void checkWinMethod_WinDiagonallyDesc_Test(){

        int col = columns-1;
        for(int i = rows-1;i>=rows-4;i--,col--){
            game.board[i][col] = 2;
        }

        game.checkWin(2,rows-3,columns-3);

        assertEquals(2,game.win);
    }
    // Dla dowolnego rozmiaru
    @Test
    void checkWinMethod_WinDiagonallyDescValueWin_Test(){

        int col = columns-1;
        for(int i = rows-1;i>=rows-4;i--,col--){
            game.board[i][col] = 2;
        }

        game.checkWin(2,rows-3,columns-3);

        assertEquals(2,game.win);
    }
    // Dla dowolnego rozmiaru
    @Test
    void checkWinMethod_NobodyWin_Test(){
        int col = columns-1;
        for(int i = rows-1;i>=rows-3;i--,col--){
            game.board[i][col] = 2;
        }

        String actual = game.checkWin(2,rows-1,columns-1); //tylko 3 żetony
        String expected = "Nobody victory";

        assertEquals(expected,actual);
    }
    // Dla dowolnego rozmiaru
    @Test
    void checkWinMethod_NobodyWinValueWin_Test(){
        int col = columns-1;
        for(int i = rows-1;i>=rows-3;i--,col--){
            game.board[i][col] = 2;
        }

        game.checkWin(2,rows-1,columns-1); //tylko 3 żetony

        assertNotEquals(2,game.win);
    }

    @Test
    void BuildStringOfBoard6x7Test(){
        game = new Game(6,7,false);

        game.board[5][0] = game.board[4][0] = game.board[4][1] =
                game.board[4][2] = game.board[3][1] = game.board[2][1] = game.board[1][1] = 1;
        game.board[5][1] = game.board[5][2] = game.board[5][3] = game.board[3][0] = game.board[3][2] = 2;
        String expected = "   0  1  2  3  4  5  6 \n" + "0 | || || || || || || |\n"+ "1 | ||R|| || || || || |\n"+
                "2 | ||R|| || || || || |\n"+ "3 |G||R||G|| || || || |\n"+ "4 |R||R||R|| || || || |\n"+
                "5 |R||G||G||G|| || || |\n"+ "Player1: R, Player2: G";

        assertEquals(expected,game.buildStringBoard());

    }
    @Test
    void BuildStringOfBoard6x7Empty(){
        game = new Game(6,7,false);
        String expected = "   0  1  2  3  4  5  6 \n" + "0 | || || || || || || |\n"+ "1 | || || || || || || |\n"+
                "2 | || || || || || || |\n"+ "3 | || || || || || || |\n"+ "4 | || || || || || || |\n"+
                "5 | || || || || || || |\n"+ "Player1: R, Player2: G";

        assertEquals(expected,game.buildStringBoard());

    }

    @Test
    void BuildStringOfBoard8x10Empty(){
        game = new Game(8,10,false);
        String expected = "   0  1  2  3  4  5  6  7  8  9 \n" + "0 | || || || || || || || || || |\n"+ "1 | || || || || || || || || || |\n"+
                "2 | || || || || || || || || || |\n"+ "3 | || || || || || || || || || |\n"+ "4 | || || || || || || || || || |\n"+
                "5 | || || || || || || || || || |\n"+ "6 | || || || || || || || || || |\n"+  "7 | || || || || || || || || || |\n"+"Player1: R, Player2: G";

        assertEquals(expected,game.buildStringBoard());

    }

    @Test
    void addToken6x7CheckBoradTest(){
        game = new Game(6,7,false);
        int[] p1 = { 0, 1, 0 ,1, 1 ,1};
        int[] p2 = { 0, 1, 0, 0, 0, 2};
        for(int i = 0;i<6;i++){
                game.addToken(1,p1[i]);
                game.addToken(2,p2[i]);
        }

        int[][] expected = {{2,0,0,0,0,0,0},{2,1,0,0,0,0,0},{2,1,0,0,0,0,0},{1,1,0,0,0,0,0},{2,2,0,0,0,0,0},{1,1,2,0,0,0,0}};

        assertArrayEquals(expected,game.board);
    }
    // Dla każdego rozmiaru
    @Test
    void addTokenAnySizeTest(){

        for(int i = 0;i<rows/2;i++){
            game.addToken(1,0);
            game.addToken(2,0);

        }
        // tablica powinna wyglądać tak
        int[][] expected = new int[rows][columns];
        int count = 1;
        for(int i = rows-1;i>=0;i--){
            for(int j = columns-1;j>=0;j--){
                if( j == 0 ){
                    if(count%2 != 0) expected[i][j] = 1;
                    else expected[i][j] = 2;
                    count++;
                }else {
                    expected[i][j] = 0;
                }
            }
        }
        if(rows%2 == 1) expected[0][0] = 0;

        assertArrayEquals(expected,game.board);
    }
    // Dla każdego rozmiaru
    @Test
    void AddTokenCheckReturnValueTest(){
        for(int i = 0;i<2;i++){
            game.addToken(1,0);
        }

        int actual = game.addToken(1,0);

        assertEquals(rows-3,actual);
    }

    @Test
    void movePlayerGiveWrongColumnToLowerTest(){
        String actual = game.movePlayer(1,-1);
        String expected = "Wrong Index";

        assertEquals(expected,actual);
    }

    @Test
    void movePlayerGiveWrongColumnToHighTest(){
        String actual = game.movePlayer(1,columns);
        String expected = "Wrong Index";

        assertEquals(expected,actual);
    }

    @Test
    void movePlayerSetWinTest(){
        game.win = 1;
        String actual = game.movePlayer(2,0);
        String expected = "The game is over.";

        assertEquals(expected,actual);
    }

    @Test
    void movePlayerWrongPlayerTest(){
        game.movePlayer(1,0);
        String actual = game.movePlayer(1,0);
        String expected = "It's not your turn.";

        assertEquals(expected,actual);
    }

    @Test
    void movePlayerTieTest(){
        // sztucznie zainicjuje licznik dodanych żetonów. bez grania
        game.countAddedTokens = rows*columns;
        String actual = game.movePlayer(1,0);
        String expected = "Board is full. Tie.";

        assertEquals(expected,actual);
    }

    // Dla każdego rozmiaru
    @Test
    void movePlayerColumnFullTest(){
        // Nie sprawdzam tu wygranej. aby tylko wykrcie pełnej kolumny
        for(int i = rows-1;i>=0;i--){
            game.board[i][0] = 1;
        }

        String actual = game.movePlayer(1,0);
        String expected = "Column is full";

        assertEquals(expected,actual);
    }

    @Test
    void movePlayerAddTokenCheckFieldsTest(){

        game.movePlayer(1,0);
        // ze względu użuycia więcej niż jednej asercji dodaje message
        assertEquals(1,game.countAddedTokens,"CountAddedTokens should be 1");
        assertEquals(true,game.moveOK,"moveOK should be true");
        assertEquals(1,game.lastRun,"lastRun should be 1");
        assertEquals(0,game.win,"win should be 0");
    }

    @Test
    void movePlayerChceckInMessageNotNull(){
        String actual = game.movePlayer(1,0);

        assertNotNull(actual);
    }
    // Dla każdego rozmiaru
    @Test
    void movePlayerSimulateWinPlayer1Test(){
        for(int i = rows-1;i>=rows-3;i--){
            game.movePlayer(1,0);
            game.movePlayer(2,2);

        }
        String actual = game.movePlayer(1,0);

        String expected = "Player1 victory !! (vertical)";
        assertEquals(expected,actual);

    }

    @Test
    void movePlayerSimulateTie4x4Test(){
        game = new Game(4,4,false);

        int[] p1 = { 2, 3, 0 ,1, 2 ,3, 0, 1};
        int[] p2 = { 0, 1, 2, 3, 0, 1, 2, 1};
        for(int i = 0;i<8;i++){
            game.movePlayer(1,p1[i]);
            game.movePlayer(2,p2[i]);
        }

        String actual = game.movePlayer(2,3);
        String expected = "Board is full. Tie.";

        assertEquals(expected,actual);

    }

    @ParameterizedTest(name = "Player({0},{1}), exist {2}")
    @CsvSource({"Aaaa, pass, true", "Aaa, paa, false","Ccc, paaa, false", "Bbbb, haslo, true","Aaa, pass, false"})
    void searchPlayerTest(String nick,String password,boolean exist){

        Player p = game.serachPlayer(nick,password);

        if(exist)
            assertNotNull(p);
        else
            assertNull(p);
    }

    @ParameterizedTest(name = "Player({0},{1}), exist {2}")
    @CsvFileSource(resources = "/dane.csv")
    void searchPlayerFileTest(String nick,String password,boolean exist){

        Player p = game.serachPlayer(nick,password);

        if(exist)
            assertNotNull(p);
        else
            assertNull(p);
    }

    @Test
    void printListPlayerTest(){

    String expected = "Nick: Aaaa, Wins: 1, Losts: 3, Tie: 1\n" +
            "Nick: Bbbb, Wins: 1, Losts: 1, Tie: 1\n";

    String actual = game.printListPlayers();

    assertEquals(expected,actual);

    }

    @Test
    void printListPlayerAfterAddNewPlayerTest(){
        game.players.add(new Player("Adam","1234","2","1","0"));
        String expected = "Nick: Aaaa, Wins: 1, Losts: 3, Tie: 1\n" +
                "Nick: Bbbb, Wins: 1, Losts: 1, Tie: 1\n"+
                "Nick: Adam, Wins: 2, Losts: 1, Tie: 0\n";

        String actual = game.printListPlayers();

        assertEquals(expected,actual);

    }

    @Test
    void sortedListTest(){
        game.sortedList();

        String expected = "Nick: Bbbb, Wins: 1, Losts: 1, Tie: 1\n"+
                "Nick: Aaaa, Wins: 1, Losts: 3, Tie: 1\n";

        String actual = game.printListPlayers();

        assertEquals(expected,actual);
    }

    @Test
    void sortedListAfterAddPlayerTest(){
        game.players.add(new Player("Adam","1234","2","1","0"));
        game.sortedList();

        String expected = "Nick: Adam, Wins: 2, Losts: 1, Tie: 0\n"+
                "Nick: Bbbb, Wins: 1, Losts: 1, Tie: 1\n"+
                "Nick: Aaaa, Wins: 1, Losts: 3, Tie: 1\n";

        String actual = game.printListPlayers();

        assertEquals(expected,actual);
    }

    @Test
     void playersInitListIsEmptyNotInicializeTest(){
        game = new Game(7,6,false);
        String actual = game.playersInit(1,"Mati","pass",true);

        String expected = "List of players is empty.";

        assertEquals(expected,actual);
    }

    @Test
    void playersInitPlayerExistTest(){
        String actual = game.playersInit(1,"Aaaa","pass",true);

        String expected = "Player1 found and set";

        assertEquals(expected,actual);
    }

    @Test
    void playersInitPlayerExistButNotFoundTest(){
        String actual = game.playersInit(1,"Aaa","pass",true);

        String expected = "Player not found!";

        assertEquals(expected,actual);
    }

    @Test
    void playersInitPlayerExistCheckPlayerOnGameTest(){
        game.playersInit(1,"Aaaa","pass",true);

        assertNotNull(game.player1);
    }

    @Test
    void playersInitPlayer2ExistTest(){
        String actual = game.playersInit(2,"Aaaa","pass",true);

        String expected = "Player2 found and set";

        assertEquals(expected,actual);
    }

    @Test
    void playersInitNewPlayerTest(){
        String actual = game.playersInit(1,"Mati","haselko",false);

        String expected = "Player1 create and set";

        assertEquals(expected,actual);
    }

    @Test
    void playersInitNewPlayer2Test(){
        String actual = game.playersInit(2,"Mati","haselko",false);

        String expected = "Player2 create and set";

        assertEquals(expected,actual);
    }

    @Test
    void playersInitNewPlayerButExistYetTest(){
        String actual = game.playersInit(2,"Aaaa","pass",false);

        String expected = "Player exist now!!!!";

        assertEquals(expected,actual);
    }

    @Test
    void playersInitTwoPlayerAndCheckFieldPlayerSetTest(){
        game.playersInit(2,"Aaaa","pass",true);
        game.playersInit(1,"Mati","haselko",false);

        assertEquals(2,game.playersSet);
    }

    @Test
    void playersInitTwoPlayerAndCompareObjectPlayerTest(){
        game.playersInit(2,"Aaaa","pass",true);
        game.playersInit(1,"Mati","haselko",false);

        assertNotSame(game.player1,game.player2);
    }

    @Test
    void increasStatisticWinPlayer1Test(){
        game.player1 = game.players.get(0);
        game.player2 = game.players.get(1);

        game.win = 1;

        game.increasStatistic();

        assertEquals("2",game.player1.getWins());
    }

    @Test
    void increasStatisticWinPlayer1CheckWinsPlayer2Test(){
        game.player1 = game.players.get(0);
        game.player2 = game.players.get(1);

        game.win = 1;

        game.increasStatistic();

        assertNotEquals("2",game.player2.getWins());
    }

    @Test
    void increasStatisticWinPlayer1CheckLostsPlayer2Test(){
        game.player1 = game.players.get(0);
        game.player2 = game.players.get(1);

        game.win = 1;

        game.increasStatistic();

        assertEquals("2",game.player2.getLosts());
    }

    @Test
    void increasStatisticWinPlayer2OneTest(){
        game.player1 = game.players.get(0);
        game.player2 = game.players.get(1);

        game.win = 2;

        game.increasStatistic();

        assertEquals("2",game.player2.getWins());
    }

    @Test
    void increasStatisticTieTest(){
        game.player1 = game.players.get(0);
        game.player2 = game.players.get(1);

        game.win = 3;

        game.increasStatistic();

        assertEquals("2",game.player2.getTie());
    }

    @Test
    void increasStatisticTie1Test(){
        game.player1 = game.players.get(0);
        game.player2 = game.players.get(1);

        game.win = 3;

        game.increasStatistic();

        assertEquals("2",game.player1.getTie());
    }

    @Test
    void readListUserFileExitstTest(){
        game = new Game(7,6,false);
        game.players = game.readListPlayers(null);

        assertNotNull(game.players);
    }

    @Test
    void readListUserFileNotExitstTest(){
        game = new Game(7,6,false);

        assertThrows(NullPointerException.class, () -> {
            game.players = game.readListPlayers("playersy.csv");
        },"File not exist");
    }

    @Test
    void writeListUserTest(){
        game.writeListPlayers("testList.csv");
        game.players = game.readListPlayers("testList.csv");

        String actual = game.players.get(0).toString();
        String expected = "Nick: Aaaa, Wins: 1, Losts: 3, Tie: 1";

        assertEquals(expected,actual);
    }

    @Test
    void movePlayerCheckIncreaseStatistic(){
        game.player1 = game.players.get(0);
        game.player2 = game.players.get(1);
        game.fileName = "testList.csv";
        game.playersSet = 2;
        for(int i = rows-1;i>=rows-3;i--){
            game.movePlayer(1,0);
            game.movePlayer(2,2);

        }
        String actual = game.movePlayer(1,0);
        assertEquals("2",game.player1.getWins(),"Wins shuld be 2");
        assertEquals("2",game.player2.getLosts(),"Losts shuld be 2");

    }

    @AfterEach
    void tearDownAll(){
        game = null;
    }
}
