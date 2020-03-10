package pokedungeon.utils.properties;

import pokedungeon.pkmn.Pokemon;

public class PropertyID extends PropertyBase
{
    private String name;
    private int pokedex;
    private int gen;

    public PropertyID(Pokemon owner)
    {
        super("Identification Info", owner);
    }

    public void set(String name, int pokedex, int gen)
    {
        this.name = name;
        this.pokedex = pokedex;
        this.gen = gen;
    }

    public String getName()
    {
        return this.name;
    }

    public int getPokedex()
    {
        return this.pokedex;
    }

    public int getGen()
    {
        return this.gen;
    }
}
