package pokedungeon.utils.properties;

import pokedungeon.utils.enums.Type;

public class PropertyType extends PropertyBase
{
    private Type typeA;
    private Type typeB;

    public PropertyType()
    {
        super("Type");
    }

    public void set(Type typeA, Type typeB)
    {
        this.typeA = typeA;
        this.typeB = typeB;
    }

    public Type getA()
    {
        return this.typeA;
    }

    public Type getB()
    {
        return this.typeB;
    }

    public Type[] get()
    {
        return new Type[]{this.typeA, this.typeB};
    }
}
