package Projekt1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

public class Game {

    int board[][];

    int lastRun; // kto ostani wrzucił żeton
    int rows;
    int columns;
    int win ; // jeśli 0 to nikt nie wygrał 1-gracz1, 2-gracz2, 3-remis
    int countAddedTokens;
    boolean moveOK = false;

    ArrayList<Player> players ;
    Player player1,player2;
    int playersSet = 0; // Ustawione gdy użytkownicy grają jako jawni i są dodawne zmiany do statystyk
    String fileName = null;

    // oznaczenia na planszy 1-Player1, 2-Player2
    public Game(int row,int column,boolean initListPlayers) {

        if(row < 4 || column < 4) {
            throw new IllegalArgumentException("Size of rows and columns must be higher than 4");
        }
        rows = row;
        columns = column;
        board = new int[rows][columns];

        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                board[i][j] = 0;
            }
        }
        if(initListPlayers)
            players = new ArrayList<Player>();
        lastRun = -1;
        win = 0;
        countAddedTokens = 0;
    }

    // Wyszukuje zwycięstwa pionowo podanego gracza
    // row i column to pozycja na planszy, na której wylądował żeton gracza
    // Wygrana przypadek 1: (4 w pionie)
    public boolean checkWinVertical(int player,int row,int column){
        int count = 0; //liczba żetonów przylegających

        for(int i=row;i<rows;i++){
            if( board[i][column] == player ){
                count ++;
            }else {

                i=rows;
            }
            if(count == 4) i=rows;
        }
        if( count == 4 ) { //win = player;
            return true; }

        return false;
    }
    //Wygrana przypadek 2: (4 w poziomie)
    public boolean checkWinHorizontal(int player,int row, int column){

        int count = 0;
        // Sprawdzanie w prawo od dodanego żetonu
        for(int i=column; i<columns; i++){
            //System.out.println(i +" "+ row);
            if(board[row][i] == player){
                count++ ;

            }else
            {
                i = columns;
            }
            if(count == 4) i=columns;
        }
        if(count == 4)
        { //win = player;
            return true; }
        // Sprawdzanie w lewo od dodanego żetonu
        else{
            for(int i = column-1 ;i>=0;i--){
                if(board[row][i] == player){
                    count++;
                }else{
                    i = -1;
                }
                if(count == 4) {i = -1; //win = player;
                    return true; }
            }

        }
        return false;
    }
    //Przypadek 3: po skosie "/"
    public boolean checkWinDiagonallyAsc(int player,int row,int column){

        int count = 0;
        int i ;
        int j=column;
        // Idę w lewo w dół czyli "/"
        for(i = row;i<rows && j>=0;i++,j--){
            if(board[i][j] == player){
                count++;

            }else
            {
                i = rows;
            }
            if(count == 4) i = rows;
        }
        if(count == 4){
            //win = player;
            return true;
        }else
        {
            i = row-1;
            j = column+1;
            for( ;i>=0 && j<columns;i--,j++){
                if(board[i][j] == player){
                    count++;
                }else{
                    i =-1;
                }
                if(count == 4) {i = -1; //win = player;
                    return true;}
            }
        }
        return false;
    }
    // Pzypadek 4: po skosie "\"
    public boolean checkWinDiagonallyDesc(int player,int row,int column) {
        int count = 0;
        int i ;
        int j=column;
        // Idę w lewo w dół czyli "\"
        for(i = row;i<rows && j<columns;i++,j++){
            if(board[i][j] == player){
                count++;

            }else
            {
                i = rows;
            }
            if(count == 4) i = rows;
        }
        if(count == 4){
            //win = player;
            return true;
        }else
        {
            i = row-1;
            j = column-1;
            for(; i>=0 && j>=0; i--,j--){
                if(board[i][j] == player){
                    count++;
                }else{
                    i =-1;
                }
                if(count == 4) {i = -1; //win = player;
                    return  true;}
            }
        }
        return false;
    }

    // Zwraca wolną pozycje w kolumnie./
    // Sprawdzanie czy kolumna nie jest pełna będzie wykonywane w funkcji
    // która bedzię wywoływała tą funkcję
    // Ale jeśli kolumna jest pełna zwróci -1
    public int getPositionInColumn(int col){

        for(int i=rows-1;i>=0;i--){
            if( board[i][col] == 0 ){
                return i;
            }
        }
        return -1;
    }

    public String checkWin(int player, int row,int column){
        if(checkWinVertical(player,row,column)){
            win = player;
            return "Player"+player+" victory !! (vertical)";
        }
        if(checkWinHorizontal(player,row,column)){
            win = player;
            return "Player"+player+" victory !! (horizontal)";
        }
        if(checkWinDiagonallyAsc(player,row,column)){
            win = player;
            return "Player"+player+" victory !! (diagonallyAsc)";
        }
        if(checkWinDiagonallyDesc(player,row,column)){
            win = player;
            return "Player"+player+" victory !! (diagonallyDesc)";
        }
        return "Nobody victory";
    }

    // Budowanie stringa planszy, który ją odzwierciedla
    public String buildStringBoard(){
        String message = "  ";
        for(int z=0;z<columns;z++) message += " "+z+" ";
        message += "\n";
        for(int i=0;i<rows;i++) {

            message += i +" ";
            for (int j = 0; j < columns; j++) {

                if(board[i][j] == 0) message += "| |";
                if(board[i][j] == 1) message += "|R|";
                if(board[i][j] == 2) message += "|G|";
            }
            message += "\n";
        }
        message += "Player1: R, Player2: G"; //Red Green
        //System.out.println(message);
        return message;
    }

    // Będzie wykonowywane gdy ruch będzie możliwy, sprawdzanie będzie w funkcji, która wywołuje ją
    public int addToken(int player,int col){

        int row = getPositionInColumn(col);

        board[row][col] = player; // przypisanie żetonu gracza do pierwszego wolnego miejsca w kolumnie od dołu
        return row;
    }

    public String movePlayer(int player,int col){

        if(col < 0 || col > columns -1) {moveOK = false; return "Wrong Index"; }

        if( win == 0 && lastRun != player && board[0][col] == 0 && countAddedTokens < (rows*columns) ){

            int row = addToken(player,col);

            countAddedTokens++;
            moveOK = true;
            lastRun = player;
            //  Sprawdzanie czy wygrana
            String message = checkWin(player,row,col);

            // Sprawdzenie czy remis
            if(countAddedTokens >= (rows*columns) && win == 0) {
                win = 3;

                if(playersSet == 2) {
                    increasStatistic();
                    writeListPlayers(fileName);
                }
                return "Board is full. Tie.";
            }
            if(playersSet == 2) {
                increasStatistic();
                writeListPlayers(fileName);
            }

            return message; // Zwraca widomość w jaki przypadek wygranej i który gracz
        }else
        {
            moveOK = false;
            if(win != 0) return "The game is over.";
            if(lastRun == player) return  "It's not your turn."; // Taka sytuacja w grze będzie nie możliwa, jedynie w testach funckji
            if(countAddedTokens >= (rows*columns)) { win = 3; return "Board is full. Tie."; }
            if(board[0][col] != 0) return "Column is full";

        }
        // notka dla mnie :: gra zakończona , nie twój ruch , kolumna pełna , remis, wygrane

        return null;
    }

    public Player serachPlayer(String nick,String password){
        for(Player player : players){
            if(player.getNick().equals(nick) && player.getPassword().equals(password) ){
                return player;
            }
        }
        return null;
    }

    public String printListPlayers() {
        String list= "";
        for (Player player : players) {
            System.out.println(player);
            list+=player+"\n";
        }
        return list;
    }

    public void sortedList(){
        Collections.sort(players);
    }

    //jeśli istnieje wyszukje i przypisuje gracza istniejącego, jak nie to tworzy. Potencjalne istnienie podawane jest do funkcji
    public String playersInit(int player,String nick,String pass,boolean exist){
        if( players == null ) { return "List of players is empty."; }

        if(exist == true) {
            Player playerSerach = serachPlayer(nick, pass);

            if (playerSerach != null) {
                if(player == 1) {
                    player1 = playerSerach;
                }
                else {
                    player2 = playerSerach;
                }
                playersSet++; // zwiększam liczbę ustawionych
                return "Player"+player+" found and set";
            }else
            {
                return "Player not found!";
            }
        }else
        {
            if(serachPlayer(nick,pass) != null){
                return "Player exist now!!!!";
            }else {
                Player newPlayer = new Player(nick, pass, "0", "0", "0");
                players.add(newPlayer);
                if (player == 1) {
                    player1 = newPlayer;
                } else {
                    player2 = newPlayer;
                }
                playersSet++;
                return "Player"+player+" create and set";
            }
        }
    }

    public void increasStatistic(){

        if(win != 0){
            if(win == 1) { player1.setWins(); player2.setLosts(); }
            if(win == 2) { player2.setWins(); player1.setLosts(); }
            if(win == 3) { player2.setTie(); player1.setTie(); }

        }
    }

    // Wczytanie listy graczy z pliku csv
    public ArrayList<Player> readListPlayers(String fileNameIns) {
        ArrayList<Player> players = new ArrayList<Player>();
        File file ;
        if(fileNameIns != null) {
            file = new File("src/main/resources/" + fileNameIns);
            fileName = fileNameIns;
        }
        else
            file = new File("src/main/resources/players.csv");
        Scanner read = null;
        try {
            read = new Scanner(file);
        } catch (FileNotFoundException e) {

        }
        //StringTokenizer token;
        while(read.hasNext()){
            String[] elements = read.next().split(",");
            players.add(new Player(elements[0],elements[1],elements[2],elements[3],elements[4]));
        }
        read.close();

        return players;
    }

    public void writeListPlayers(String fileName) {

        File file;
        if(fileName != null)
            file = new File("src/main/resources/"+fileName);
        else
            file = new File("src/main/resources/players.csv");
        BufferedWriter save = null;

        try {
            save = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (Player player : players) {
            try {
                save.write(player.getNick()+","+player.getPassword()+","+player.getWins()+","+player.getLosts()+","+player.getTie()+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            save.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
