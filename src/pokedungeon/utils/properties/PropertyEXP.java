package pokedungeon.utils.properties;

import pokedungeon.pkmn.Pokemon;
import pokedungeon.utils.enums.EnumStats;

public class PropertyEXP extends PropertyBase
{
    private int currentLevel = 1;
    private int maxLevel = 100;

    private int currentEXP = 0;

    public PropertyEXP(Pokemon owner)
    {
        super("Experience", owner);
    }

    public void addEXP(int amount)
    {
        if(this.currentLevel == 100) return;

        this.currentEXP += amount;
        this.levelUp();
    }

    public void levelUp()
    {
        if(this.canLevel())
        {
            this.currentEXP -= this.getRequiredEXPLevel(this.currentLevel + 1);
            this.currentLevel++;

            this.outVals();

            this.owner.stats().updateStat(EnumStats.HP);
            this.owner.stats().updateAllStats();

            if(this.canLevel()) this.levelUp();
        }
    }

    private boolean canLevel()
    {
        return this.currentEXP >= this.getRequiredEXPLevel(this.currentLevel + 1);
    }

    private int getRequiredEXPLevel(int level)
    {
        return (level * 2) + 1;
    }

    public int getLevel()
    {
        return this.currentLevel;
    }

    public int getEXP()
    {
        return this.currentEXP;
    }

    //DEBUG ONLY
    private void outVals()
    {
        System.out.println("Level: " + this.currentLevel + ", EXP: " + this.currentEXP + ", EXP for Next Level: " + this.getRequiredEXPLevel(1 + this.currentLevel));
    }

    public void setLevel(int level)
    {
        this.currentLevel = level;
    }
}
