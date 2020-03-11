package pokedungeon.utils.properties;

import pokedungeon.pkmn.Pokemon;

public abstract class PropertyBase
{
    private String propertyName;
    protected Pokemon owner;

    public PropertyBase(String propertyName, Pokemon owner)
    {
        this.propertyName = propertyName;
        this.owner = owner;
    }

    public String getPropertyName()
    {
        return this.propertyName;
    }
}
