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
    private final List<Move> moveSet = new ArrayList<>();
    private final List<Move> availableMoves = new ArrayList<>();

    public PropertyMoves()
    {
        super("Moves");
    }

    public void addMove(Move m, int level)
    {
        this.allMoves.put(m, level);
    }

    public void learnMove(Pokemon p, Move m)
    {
        Scanner sc = new Scanner(System.in);
        if(!this.allMoves.containsKey(m)) throw new IllegalArgumentException(p.getName() + " can not learn the move " + m.getName());
        else if(this.moveSet.contains(m))
        {
            System.out.println(p.getName() + " already knows the move " + m.getName());
            return;
        }

        if(p.getLevel() >= this.allMoves.get(m))
        {
            if(this.moveSet.size() == 4)
            {
                System.out.println("Which attack would you like to replace with " + m.getName() + "?");
                System.out.println("1: " + this.moveSet.get(0).getName() + ", 2: " + this.moveSet.get(1).getName() + ", 3: " + this.moveSet.get(2).getName() + ", 4: " + this.moveSet.get(3).getName());
                this.moveSet.remove(this.moveSet.get(sc.nextInt() - 1));
                this.moveSet.add(m);
            }
            else this.moveSet.add(m);
        }
    }

    public void updateAvailableMoves(int level)
    {
        this.availableMoves.clear();
        for(Move m : this.allMoves.keySet())
        {
            if(!this.moveSet.contains(m) && level >= this.allMoves.get(m)) this.availableMoves.add(m);
        }
    }

    public HashMap<Move, Integer> getAllMoves()
    {
        return this.allMoves;
    }

    public List<Move> getMoveSet()
    {
        return this.moveSet;
    }

    public List<Move> getAvailableMoves()
    {
        return this.availableMoves;
    }
}
