package Projekt1;
import java.util.ArrayList;
import java.util.Collection;

public class Player implements Comparable<Player>{

    @Override
    public String toString() {
        return
                "Nick: " + nick +
                        ", Wins: " + wins +
                        ", Losts: " + losts +
                        ", Tie: "+ tie
                ;
    }

    public Player(String nick, String password, String wins, String losts,String tie) {
        this.nick = nick;
        this.password = password;
        this.wins = wins;
        this.losts = losts;
        this.tie = tie;
    }

    private String nick;
    private String password;
    private String wins;
    private String losts;
    private String tie;

    public String getTie() {
        return tie;
    }

    public void setTie() {
        this.tie = Integer.toString(Integer.parseInt(tie)+1) ;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWins() {
        return wins;
    }

    public void setWins() {
        this.wins = Integer.toString(Integer.parseInt(wins)+1) ;
    }

    public String getLosts() {
        return losts;
    }

    public void setLosts() {
        this.losts = Integer.toString(Integer.parseInt(losts)+1);
    }

    @Override
    public int compareTo(Player o) {
        // sortowanie graczy po wygranych gdy tyle samo to po przegranyh a na końcu po remisach
        // jeśli zła kolejność to >0 dobra <=0
        if(Integer.parseInt(wins) > Integer.parseInt(o.wins)) return -1;

        if(Integer.parseInt(wins) < Integer.parseInt(o.wins)) return 1;

        if(Integer.parseInt(losts) < Integer.parseInt(o.losts)) return -1;

        if(Integer.parseInt(losts) > Integer.parseInt(o.losts)) return 1;

        if(Integer.parseInt(tie) < Integer.parseInt(o.tie)) return -1;

        if(Integer.parseInt(tie) > Integer.parseInt(o.tie)) return 1;

        return 0;
    }

}
