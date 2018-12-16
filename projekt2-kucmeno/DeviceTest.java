import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

  public class TestMp3Player {

    private Mp3Player mp3;
    private ArrayList list = new ArrayList();

    @Before
      public void setUp() {
      mp3 = new MockMp3Player();
      list = new ArrayList();
      list.add("Bill Chase -- Open Up Wide");
      list.add("Jethro Tull -- Locomotive Breath");
      list.add("The Boomtown Rats -- Monday");
      list.add("Carl Orff -- O Fortuna");
    }

    @Test
    public void testPlay() {

      mp3.loadSongs(list);
      // Assercja
      mp3.play();
      // Assercje
      mp3.pause();
      // Assercje
      mp3.stop();
      // Assercje
    }

    @Test
    public void testPlayNoList() {
      // Assercja
      mp3.play();
      // Assercje
      mp3.pause();
      // Assercje
      mp3.stop();
      // Assercje
    
    }

    @Test
    public void testAdvance() {

    mp3.loadSongs(list);
    mp3.play();
    // Assercje
    mp3.prev();
    // Assercje
    mp3.next();
    // Assercja
    mp3.next();
    // Assercja
    mp3.prev();
    // Assercja
    mp3.next();
    // Assercja
    mp3.next();
    // Assercja
    mp3.next();
    // Assercje
    
    }
    
    @After
    public void teardown(){
      mp3=null;
      list=null;
    }
}
