import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static boolean playerAttack = false;
    private static boolean pokemonFainted = false;
    private static Pokemon[] pokemonList;

    public static void main(String[] args) throws InterruptedException {
        //Initialise movesets
        PokemonDB pokemonDB = new PokemonDB();
        pokemonDB.initMoves();

        //Initialise pokemon
        Pokemon bulbasaur = new Pokemon("Bulbasaur", PokemonDB.baseStats, PokemonDB.bulbasaurMoves);
        Pokemon onix = new Pokemon("Onix", PokemonDB.onixLevels, PokemonDB.onixMoves);
        Pokemon squirtle = new Pokemon("Squirtle", PokemonDB.baseStats, PokemonDB.squirtleMoves);

        //Initialise and populate list of available pokemon
        pokemonList = new Pokemon[]{bulbasaur, onix, squirtle};

        //Get players selected NPC
        Pokemon playersPokemon = selectAPokemon();


        //Access current objects hp level
        int playerPokemonLevels[] = playersPokemon.getLevels();
        int playerPokemonHp = playerPokemonLevels[0];
        int[] npcPokemonLevels = onix.getLevels();
        int npcPokemonHP = npcPokemonLevels[0];

        //Evalutate if NPC is still alive and battle can continue
        while (playerPokemonHp > 0 || npcPokemonHP > 0) {
            battle(playersPokemon, onix);
        }
    }


    private static void battle(Pokemon a, Pokemon b) throws InterruptedException {
        if (!pokemonFainted) {
            if (playerAttack) {
                attack(a, b);
            } else {
                Thread.sleep(1000);
                enemyAttack(b, a);
            }
        }
    }

    //Handles selecting an attack, displaying the keyset of the Pokemon object
    //Takes a user input, checks if the input is valid and if so, grabs the damage associated to the key.
    //Gets the opponent NPC hp and deducts the damage dealt to it.
    //Checks if the NPC fainted
    private static void attack(Pokemon a, Pokemon b) {
        if (!pokemonFainted) {
            System.out.println("Select an attack: ");
            System.out.println(a.getMoves().keySet());
            Scanner scanner = new Scanner(System.in);
            String moveSelected = scanner.nextLine();
            if (a.getMoves().containsKey(moveSelected)) {
                Object damage = a.getMoves().get(moveSelected);
                int damageInt = (Integer) damage;
                System.out.println("Your " + a.getName() + " uses: " + moveSelected + " and deals " + damage + " damage");
                int[] enemyLevels = b.getLevels();
                int enemyHp = enemyLevels[0];
                int updatedHp = enemyHp - damageInt;
                enemyLevels[0] = updatedHp;
                if (enemyLevels[0] > 0) {
                    System.out.println("Enemy " + b.getName() + " has " + enemyLevels[0] + " hp remaining!\n");
                    playerAttack = false;
                } else {
                    System.out.println("Enemy " + b.getName() + " fainted!");
                    pokemonFainted = true;
                }

            }
        }else{
            cleanupBattle();
        }
    }

    //Ai player gets a random move from the moveset, an easy way to achieve this was to
    //Use a hashset and just get its first index as the set of items is unordered,
    //this means the move can be different each selection. And solves a data problem
    //Where we want to pair a string name with a int damage.
    private static void enemyAttack(Pokemon b, Pokemon a) {
        if (!pokemonFainted) {
            LinkedHashMap moves = b.getMoves();
            Object firstKey = moves.keySet().toArray()[0];
            Object moveDamage = moves.get(firstKey);
            int damageInt = (Integer) moveDamage;
            System.out.println("Enemy's " + b.getName() + " uses " + firstKey.toString() + " and deals " + damageInt + " damage");
            int[] opponentLevels = a.getLevels();
            int opponentHp = opponentLevels[0];
            int newHp = opponentHp - damageInt;
            opponentLevels[0] = newHp;
            if (opponentLevels[0] > 0) {
                System.out.println("Your " +a.getName() + " has " + opponentLevels[0] + " hp remaining!\n");
                playerAttack = true;
            } else {
                System.out.println("Your " +a.getName() + "has fainted!");
                pokemonFainted = true;
            }
        }else {
            cleanupBattle();
        }
    }

    //Handles the selection of the players pokemon,
    //checks if the choice is a valid one.
    //Returns the chosen pokemon when a valid entry is provided.
    private static Pokemon selectAPokemon(){
        Pokemon validPokemon = null;
        System.out.println("Select your pokemon!\n");
        for(int i = 0; i< pokemonList.length; i++){
            System.out.println(pokemonList[i].getName());
        }

        boolean validChoice = false;

        while(!validChoice){
            Scanner scanner = new Scanner(System.in);
            String pokemonChosen = scanner.nextLine();
            for(int i = 0; i < pokemonList.length; i++){
                if(pokemonChosen.equals(pokemonList[i].getName())){
                    validChoice = true;
                    validPokemon = pokemonList[i];
                    break;
                }else{

                }
            } if(!validChoice) {
                System.out.println("invalid selection, please pick again");
            }
        }
        System.out.println("You have chosen : " + validPokemon.getName());
        System.out.println("Enemy has chosen Onix!");
        return validPokemon;
    }


    private Pokemon getEnemysRandomPokemon(Pokemon[] pokemonList){
        Random random = new Random();
        Pokemon randomPokemon = null;
        int randomIndex = random.nextInt(pokemonList.length);
        for(int i = 0; i < pokemonList.length; i++){
            if(i == randomIndex){
                randomPokemon = pokemonList[i];
            }
        }
        return randomPokemon;
    }


    private static void cleanupBattle() {
        System.out.println("The battle has ended");
    }
}


