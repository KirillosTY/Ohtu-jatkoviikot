import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }


    @Test
    public void nameFailTest(){

        assertEquals(stats.search("oakdwpakdw"),null);

    }

    @Test
    public void nameSuccessTest(){

        Player a =new Player("Semenko", "EDM", 4, 12);
        Player b = stats.search("Semenko");
        assertEquals(b.compareTo(a),0);

    }

    @Test
    public void teamFailTest(){
        ArrayList<Player> players = new ArrayList<>();

        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Kurri",   "EDM", 37, 53));
        players.add(new Player("Gretzky", "EDM", 35, 89));

        List<Player> test = stats.team("EDM");
        for(Player p:players){

            if(!test.contains(p)){

                return;

            }

        }

        fail();
    }


    @Test
    public void teamSuccessTest(){

        for(Player s:stats.team("EDM")){
            if(!s.getTeam().equals("EDM")){
                fail();
            }
        }

    }

    @Test
    public void topScorersTooMany(){

        assertThrows(NoSuchElementException.class, () ->  stats.topScorers(100));

    }

    @Test
    public void topScorersRightAmount(){
        System.out.println(stats.topScorers(2));
        assertEquals(2,stats.topScorers(2).size());

    }

    @Test
    public void topScorersRightName(){

        List<Player> players = stats.topScorers(2);
        Player a = new Player("Gretzky", "EDM", 35, 89);
        Player b = new Player("Lemieux", "PIT", 45, 54);

        for(Player p: players){
          if(p.compareTo(b) != 0 && p.compareTo(a) != 0 ){
              fail();
          }
        }
    }

}