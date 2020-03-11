package pokedungeon.utils.properties;

import pokedungeon.pkmn.Pokemon;
import pokedungeon.utils.enums.EnumStats;
import pokedungeon.utils.enums.StatusCondition;

import java.util.Random;

public class PropertyStatusConditions extends PropertyBase
{
    public boolean isAsleep = false;
    public boolean isBurned = false;
    public boolean isConfused = false;
    public boolean isParalyzed = false;
    public boolean isPoisoned = false;

    public PropertyStatusConditions(Pokemon owner)
    {
        super("Special Conditions", owner);
    }

    public void inflict(StatusCondition sc)
    {
        this.set(sc, true);
    }

    public void heal(StatusCondition sc)
    {
        this.set(sc, false);
    }

    public void logic()
    {
        if(this.isAsleep)
        {
            if((new Random()).nextInt(3) <= 1) this.heal(StatusCondition.ASLEEP);
        }

        if(this.isBurned)
        {
            this.owner.stats().decrHP(1 / 16);
        }

        if(this.isConfused)
        {
            if((new Random()).nextInt(3) == 0)
            {
                this.owner.stats().decrHP(((((2 * this.owner.getLevel() / 5.0) + 2.0) * 40 * ((double)this.owner.stats().getStat(EnumStats.ATTACK) / (double)this.owner.stats().getStat(EnumStats.DEFENSE)) / 50) + 2));
            }
        }

        if(this.isParalyzed)
        {
            if((new Random()).nextInt(3) <= 1) this.heal(StatusCondition.PARALYZED);
        }

        if(this.isPoisoned)
        {
            this.owner.stats().decrHP(1 / 8);
        }
    }

    public boolean get(StatusCondition sc)
    {
        switch(sc)
        {
            case ASLEEP: return this.isAsleep;
            case BURNED: return this.isBurned;
            case CONFUSED: return this.isConfused;
            case PARALYZED: return this.isParalyzed;
            case POISONED: return this.isPoisoned;
            default: throw new IllegalArgumentException("Invalid Input");
        }
    }

    private void set(StatusCondition sc, boolean val)
    {
        switch(sc)
        {
            case ASLEEP: this.isAsleep = val;
            case BURNED: this.isBurned = val;
            case CONFUSED: this.isConfused = val;
            case PARALYZED: this.isParalyzed = val;
            case POISONED: this.isPoisoned = val;
            default: throw new IllegalArgumentException("Invalid Input");
        }
    }
}
