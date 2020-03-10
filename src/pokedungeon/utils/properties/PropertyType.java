package pokedungeon.utils.properties;

import pokedungeon.pkmn.Pokemon;
import pokedungeon.utils.enums.Type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PropertyType extends PropertyBase
{
    private Type typeA;
    private Type typeB;

    public PropertyType(Pokemon owner)
    {
        super("Type", owner);
    }

    public void set(Type typeA, Type typeB)
    {
        this.typeA = typeA;
        this.typeB = typeB;
    }

    public Set<Type> get()
    {
        return new HashSet<>(Arrays.asList(this.typeA, this.typeB));
    }
}
