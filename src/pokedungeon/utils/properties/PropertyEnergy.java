package pokedungeon.utils.properties;

/**
 * The Total Energy of the Pokemon, for Attack Purposes (Each attack will use a different amount of Energy Points (EP)
 * and each turn the pokemon will regain a certain amount of points.
 */
public class PropertyEnergy extends PropertyBase
{
    private int maxEnergy, currentEnergy;

    public PropertyEnergy()
    {
        super("Energy");
    }

    public int get()
    {
        return this.currentEnergy;
    }

    public int getMax()
    {
        return this.maxEnergy;
    }

    public void setMax(int max)
    {
        this.maxEnergy = max;
        this.currentEnergy = this.maxEnergy;
    }

    public void decr(int amount)
    {
        this.currentEnergy = Math.max(this.currentEnergy - amount, 0);
    }

    public void incr(int amount)
    {
        this.currentEnergy = Math.min(this.currentEnergy + amount, this.maxEnergy);
    }
}
