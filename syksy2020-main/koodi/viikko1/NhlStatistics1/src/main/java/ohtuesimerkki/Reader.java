package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;

public interface Reader {


    default List<Player> getPlayers(){
        ArrayList<Player> players = new ArrayList<Player>();

        return players;

     }

}
