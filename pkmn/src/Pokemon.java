import java.util.LinkedHashMap;

public class Pokemon {

    public Pokemon(){

    }

    public Pokemon(String name, int[] levels, LinkedHashMap moves){
        this.name = name;
        this.levels = levels;
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getLevels() {
        return levels;
    }

    public void setLevels(int[] levels) {
        this.levels = levels;
    }

    public LinkedHashMap getMoves() {
        return moves;
    }

    public void setMoves(LinkedHashMap moves) {
        this.moves = moves;
    }

    private String name;
    private int[] levels;
    private LinkedHashMap moves;

}
