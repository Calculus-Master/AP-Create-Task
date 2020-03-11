package pokedungeon.utils.properties;

import pokedungeon.attacks.Move;
import pokedungeon.pkmn.Pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PropertyMoves extends PropertyBase
{
    private final HashMap<Move, Integer> allMoves = new HashMap<>();
    private final List<Move> currentMoveSet = new ArrayList<>();
    private final List<Move> availableMoves = new ArrayList<>();

    public PropertyMoves(Pokemon owner)
    {
        super("Moves", owner);
    }

    public void learnMove(Move m)
    {
        Scanner sc = new Scanner(System.in);
        if(!this.allMoves.containsKey(m)) throw new IllegalArgumentException(this.owner.getName() + " can not learn the move " + m.getName());
        else if(this.currentMoveSet.contains(m))
        {
            System.out.println(this.owner.getName() + " already knows the move " + m.getName());
            return;
        }

        if(this.owner.getLevel() >= this.allMoves.get(m))
        {
            if(this.currentMoveSet.size() == 4)
            {
                System.out.println("Which attack would you like to replace with " + m.getName() + "?");
                System.out.println("1: " + this.currentMoveSet.get(0).getName() + ", 2: " + this.currentMoveSet.get(1).getName() + ", 3: " + this.currentMoveSet.get(2).getName() + ", 4: " + this.currentMoveSet.get(3).getName());
                this.currentMoveSet.remove(this.currentMoveSet.get(sc.nextInt() - 1));
                this.currentMoveSet.add(m);
            }
            else this.currentMoveSet.add(m);
        }
    }

    public void updateAvailableMoves(int level)
    {
        this.availableMoves.clear();
        for(Move m : this.allMoves.keySet())
        {
            if(!this.currentMoveSet.contains(m) && level >= this.allMoves.get(m)) this.availableMoves.add(m);
        }
    }

    public Move getMoveAtLevel(int level)
    {
        for(Move m : this.allMoves.keySet())
        {
            if(this.allMoves.get(m) == level) return m;
        }
        return null;
    }

    public void addMove(Move m, int level)
    {
        this.allMoves.put(m, level);
    }

    public HashMap<Move, Integer> getAllMoves()
    {
        return this.allMoves;
    }

    public List<Move> getCurrentMoveSet()
    {
        return this.currentMoveSet;
    }

    public List<Move> getAvailableMoves()
    {
        this.updateAvailableMoves(this.owner.getLevel());
        return this.availableMoves;
    }
}
