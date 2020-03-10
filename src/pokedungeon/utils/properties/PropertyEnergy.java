package pokedungeon.utils.properties;

import pokedungeon.pkmn.Pokemon;
import pokedungeon.utils.interfaces.IEnergyValues;

/**
 * The Total Energy of the Pokemon, for Attack Purposes (Each attack will use a different amount of Energy Points (EP)
 * and each turn the pokemon will regain a certain amount of points.
 */
public class PropertyEnergy extends PropertyBase implements IEnergyValues
{
    private double maxEnergy, currentEnergy;

    public PropertyEnergy(Pokemon owner)
    {
        super("Energy", owner);
    }

    public double get()
    {
        return this.currentEnergy;
    }

    public double getMax()
    {
        return this.maxEnergy;
    }

    public void setMax(double max)
    {
        this.maxEnergy = max;
        this.currentEnergy = this.maxEnergy;
    }

    public void passiveRegen()
    {
        this.incr(this.getPassiveGen(this.maxEnergy));
    }

    public void decr(double amount)
    {
        this.currentEnergy = Math.max(this.currentEnergy - amount, 0);
    }

    public void incr(double amount)
    {
        this.currentEnergy = Math.min(this.currentEnergy + amount, this.maxEnergy);
    }
}
