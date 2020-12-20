package gameClient;
import Server.Game_Server_Ex2;
import api.*;
import gameClient.MyFrame;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Main implements Runnable {

    public static void main(String[] args) {
        game_service game = Game_Server_Ex2.getServer(0);
        System.out.println(game.getGraph());
        System.out.println();
        System.out.println(game.getPokemons());
    }

    @Override
    public void run() {

    }
}


