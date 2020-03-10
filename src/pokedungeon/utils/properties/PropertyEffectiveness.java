package pokedungeon.utils.properties;

import pokedungeon.pkmn.Pokemon;
import pokedungeon.utils.Global;
import pokedungeon.utils.enums.EnumProperty;
import pokedungeon.utils.enums.Type;

import java.util.HashMap;

public class PropertyEffectiveness extends PropertyBase
{
    private HashMap<Type, Double> typeEff = new HashMap<>();

    public PropertyEffectiveness()
    {
        super("Type Effectiveness");
        this.createBaseMap();
    }

    private void createBaseMap()
    {
        for(Type t : Global.typeList) this.typeEff.put(t, 1.0);
    }

    public void addTypeEff(Type type, double effectiveness)
    {
        this.typeEff.put(type, effectiveness);
    }

    public void copyEffMap(Pokemon copyFrom)
    {
        this.typeEff = copyFrom.property(EnumProperty.TYPE_EFFECTIVENESS).typeEffCast().get();
    }

    public HashMap<Type, Double> get()
    {
        return this.typeEff;
    }

}
