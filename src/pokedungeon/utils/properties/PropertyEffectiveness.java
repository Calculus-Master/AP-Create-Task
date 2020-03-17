package pokedungeon.utils.properties;

import pokedungeon.pkmn.Pokemon;
import pokedungeon.pkmn.TypeMapDB;
import pokedungeon.utils.Global;
import pokedungeon.utils.enums.Type;

import java.util.HashMap;
import java.util.Map;

public class PropertyEffectiveness extends PropertyBase
{
    private HashMap<Type, Double> typeEff = new HashMap<>();

    public PropertyEffectiveness(Pokemon owner)
    {
        super("Type Effectiveness", owner);
        this.createBaseMap();
    }

    private void createBaseMap()
    {
        for(Type t : Global.typeList) this.typeEff.put(t, 1.0);
    }

    public void create()
    {
        Map<Type, Double> dataMap = TypeMapDB.getMap(this.owner.getType().get(0), this.owner.getType().get(1));
        for(Type t : dataMap.keySet()) this.addTypeEff(t, dataMap.get(t));
    }

    public void addTypeEff(Type type, double effectiveness)
    {
        this.typeEff.put(type, effectiveness);
    }

    public void copyEffMap(Pokemon copyFrom)
    {
        this.typeEff = copyFrom.typeEff().get();
    }

    public HashMap<Type, Double> get()
    {
        return this.typeEff;
    }

}
