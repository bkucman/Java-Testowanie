import java.util.ArrayList;
    
public interface Mp3Player {
    /** 
    * Rozpocznij odtwarzanie 
    * od utworu znajdujacego sie na szczycie listy
    * utworow lub nie rob nic gdy lista jest pusta. 
    */
    public void play();
    
    /** 
    * Wstrzymaj odtwarzanie. Odtwarzanie bedzie 
    * pozniej wznowione od tego miejsca.
    */
    public void pause();
    
    /** 
    * Zatrzymaj odtwarzanie. Biezacy utwor pozostaje
    * na szczycie listy, ale odtwarzanie zostanie
    * wznowione od jego poczatku.
    */
    public void stop();
    
    /** Zwraca liczbe sekund od poczatku 
    * biezacego utworu.
    */
    public double currentPosition();
    
    /**
    * Zwraca nazwe odtwarzanego pliku.
    */
    public String currentSong();
    
    /** 
    * Przechodzi do nastepnego utworu na liscie 
    * i rozpoczyna jego odtwarzanie.
    */
    public void next();
    
    /**
    * Wraca do poprzedniego utworu na liscie 
    * i rozpoczyna jego odtwarzanie.
    */
    public void prev();
    
    /** 
    * Zwraca true gdy odtwarzany jest utwor. 
    */
    public boolean isPlaying();
    
    /**
    * Laduje pliki na liste utworow.
    */
    public void loadSongs(ArrayList names);
    
}
