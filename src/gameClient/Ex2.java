package gameClient;
import Server.Game_Server_Ex2;
import api.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Ex2 implements Runnable{
    private static GameFrame Gf;
    private static Arena Ar;


    public static void main(String[] a) {
        Thread tr = new Thread(new Ex2());
        tr.start();
    }

    @Override
    public void run() {
        LoginFrame frame = new LoginFrame();
        //int level = frame.level_number;
        int level = 0;
        game_service game = Game_Server_Ex2.getServer(level); // you have [0,23] games
        //int id = frame.id;
        //game.login(id);
        String g = game.getGraph();
        DWGraph_Algo gr = new DWGraph_Algo();
        gr.loadGame(game.getGraph());
        init(game);
        game.startGame();
        Gf.setTitle("Pokemon's challenge   level : " + level);
        int ind=0;
        long dt=1000;
        while(game.isRunning()) {
            directed_weighted_graph p = (directed_weighted_graph) gr;
            moveAgants(game,p);
            try {
                if(ind%1==0) {
                    Gf.repaint();}
                    Thread.sleep(dt);
                    ind++;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }


    private static void moveAgants(game_service game, directed_weighted_graph gg) {
        String lg = game.move();
        List<CL_Agent> log = Arena.getAgents(lg, gg);
        Ar.setAgents(log);
        String fs =  game.getPokemons();
        List<CL_Pokemon> ffs = Arena.json2Pokemons(fs);
        Ar.setPokemons(ffs);
        for(int i=0;i<log.size();i++) {
            CL_Agent ag = log.get(i);
            int id = ag.getID();
            int dest = ag.getNextNode();
            int src = ag.getSrcNode();
            double v = ag.getValue();
            if(dest==-1) {
                dest = nextNode(gg, src);
                game.chooseNextEdge(ag.getID(), dest);
                System.out.println("Agent: "+id+", val: "+v+"   turned to node: "+dest);
            }
        }
    }


    private static int nextNode(directed_weighted_graph g, int src) {
        int ans = -1;
        Collection<edge_data> ee = g.getE(src);
        Iterator<edge_data> itr = ee.iterator();
        int s = ee.size();
        int r = (int)(Math.random()*s);
        int i=0;
        while(i<r) {itr.next();i++;}
        ans = itr.next().getDest();
        return ans;
    }

    private void init(game_service game) {
        String g = game.getGraph();
        String fs = game.getPokemons();
        directed_weighted_graph gg = game.getJava_Graph_Not_to_be_used();
        //gg.init(g);
        Ar = new Arena();
        Ar.setGraph(gg);
        Ar.setPokemons(Arena.json2Pokemons(fs));
        Gf = new GameFrame("test Ex2");
        Gf.setSize(1000, 700);
        Gf.update(Ar);
        Gf.show();
        String info = game.toString();
        JSONObject line;
        try {
            line = new JSONObject(info);
            JSONObject ttt = line.getJSONObject("GameServer");
            int rs = ttt.getInt("agents");
            System.out.println(info);
            System.out.println(game.getPokemons());
            int src_node = 0;  // arbitrary node, you should start at one of the pokemon
            ArrayList<CL_Pokemon> cl_fs = Arena.json2Pokemons(game.getPokemons());
            for(int a = 0;a<cl_fs.size();a++) { Arena.updateEdge(cl_fs.get(a),gg);}
            for(int a = 0;a<rs;a++) {
                int ind = a%cl_fs.size();
                CL_Pokemon c = cl_fs.get(ind);
                int nn = c.get_edge().getDest();
                if(c.getType()<0 ) {nn = c.get_edge().getSrc();}

                game.addAgent(nn);
            }
        }
        catch (JSONException e) {e.printStackTrace();}
    }
}
