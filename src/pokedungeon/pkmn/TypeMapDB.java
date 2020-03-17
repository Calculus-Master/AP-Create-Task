package pokedungeon.pkmn;

import pokedungeon.utils.enums.Type;

import java.util.HashMap;
import java.util.Map;

public class TypeMapDB
{
    public static Map<Type, Double> getMap(Type tA, Type tB)
    {
        Map<Type, Double> hm = new HashMap<>();

        if(tA.equals(Type.GRASS) && tB.equals(Type.POISON))
        {
            hm.put(Type.FIRE, 2D);
            hm.put(Type.WATER, 0.5);
            hm.put(Type.ELECTRIC, 0.5);
            hm.put(Type.GRASS, 0.25);
            hm.put(Type.ICE, 2D);
            hm.put(Type.FIGHTING, 0.5);
            hm.put(Type.FLYING, 2D);
            hm.put(Type.PSYCHIC, 2D);
            hm.put(Type.FAIRY, 0.5);
        }
        else if(tA.equals(Type.FIRE) && tB.equals(Type.FIRE))
        {
            hm.put(Type.FIRE, 0.5);
            hm.put(Type.WATER, 2D);
            hm.put(Type.GRASS, 0.5);
            hm.put(Type.ICE, 0.5);
            hm.put(Type.GROUND, 2D);
            hm.put(Type.BUG, 0.5);
            hm.put(Type.ROCK, 2D);
            hm.put(Type.STEEL, 0.5);
            hm.put(Type.FAIRY, 0.5);
        }
        else if(tA.equals(Type.FIRE) && tB.equals(Type.FLYING))
        {
            hm.put(Type.FIRE, 0.5);
            hm.put(Type.WATER, 2D);
            hm.put(Type.ELECTRIC, 2D);
            hm.put(Type.GRASS, 0.25);
            hm.put(Type.FIGHTING, 0.5);
            hm.put(Type.GROUND, 0.0);
            hm.put(Type.BUG, 0.25);
            hm.put(Type.ROCK, 4D);
            hm.put(Type.STEEL, 0.5);
            hm.put(Type.FAIRY, 0.5);
        }
        else if(tA.equals(Type.FIRE) && tB.equals(Type.DRAGON))
        {
            hm.put(Type.FIRE, 0.25);
            hm.put(Type.ELECTRIC, 0.5);
            hm.put(Type.GRASS, 0.25);
            hm.put(Type.GROUND, 2D);
            hm.put(Type.BUG, 0.5);
            hm.put(Type.ROCK, 2D);
            hm.put(Type.DRAGON, 2D);
            hm.put(Type.STEEL, 0.5);
        }
        else if(tA.equals(Type.WATER) && tB.equals(Type.WATER))
        {
            hm.put(Type.FIRE, 0.5);
            hm.put(Type.WATER, 0.5);
            hm.put(Type.ELECTRIC, 2D);
            hm.put(Type.GRASS, 2D);
            hm.put(Type.ICE, 0.5);
            hm.put(Type.STEEL, 0.5);
        }

        return hm;
    }
}
