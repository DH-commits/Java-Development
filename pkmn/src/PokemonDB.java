import java.util.LinkedHashMap;

public class PokemonDB {

    public static LinkedHashMap bulbasaurMoves, onixMoves, squirtleMoves;
    public static int[]  onixLevels, baseStats;
    public PokemonDB(){

    }
        //Initialises Pokemon levels and movesets.
    public void initMoves(){
        bulbasaurMoves = new LinkedHashMap(); //Can actually just use a hasmap. Linkedhashmap is not required.
        baseStats = new int[]{100, 20, 20};
        onixLevels = new int[]{120, 20, 20};
        onixMoves = new LinkedHashMap();
        squirtleMoves = new LinkedHashMap();
        bulbasaurMoves.put(moves.GROWL.name, moves.GROWL.damage);
        bulbasaurMoves.put(moves.TACKLE.name, moves.TACKLE.damage);
        bulbasaurMoves.put(moves.VINE_WHIP.name, moves.VINE_WHIP.damage);
        bulbasaurMoves.put(moves.TAIL_WHIP.name, moves.TACKLE.damage);

        squirtleMoves.put(moves.BITE.name, moves.BITE.damage);
        squirtleMoves.put(moves.HEADBUTT.name, moves.HEADBUTT.damage);
        squirtleMoves.put(moves.WITHDRAW.name, moves.WITHDRAW.damage);
        squirtleMoves.put(moves.WATER_GUN.name, moves.WATER_GUN.damage);

        onixMoves.put(moves.ROCK_THROW.name, moves.ROCK_THROW.damage);
        onixMoves.put(moves.BIND.name, moves.BITE.damage);
        onixMoves.put(moves.SLAM.name, moves.SLAM.damage);
        onixMoves.put(moves.GROWL.name, moves.GROWL.damage);


    }
        //Enum containing data of each move.
        //Contains name, damage and pp. Could be extended easily
        //Benefits the program as different Pokemon can share the same moves,
        //instead of repeating moves, a single declaration can be declared here.
    enum moves{
        GROWL("Growl", 0, 5),
        BITE("Bite", 15, 10),
        WITHDRAW("Withdraw", 0, 5),
        WATER_GUN("Water gun", 20, 5),
        TAIL_WHIP("Tail whip", 0, 10),
        SLAM("Slam", 10, 5),
        ROCK_THROW("Rock throw", 15, 0),
        BIND("Bind", 10, 5),
        HEADBUTT("Headbutt", 10, 5),
        VINE_WHIP("Vine whip", 15, 5),
        TACKLE("Tackle", 10, 5);
        ;


        private int damage;
        private int pp;
        private String name;

        moves(String move, int damage, int pp) {
            this.name = move;
            this.damage = damage;
            this.pp = pp;
        }
    }


}

