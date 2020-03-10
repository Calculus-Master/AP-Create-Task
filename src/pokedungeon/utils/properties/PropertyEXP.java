package pokedungeon.utils.properties;

public class PropertyEXP extends PropertyBase
{
    private int currentLevel = 1;
    private int maxLevel = 100;

    private int currentEXP = 0;

    public PropertyEXP()
    {
        super("Experience");
    }

    public int getEXP()
    {
        return this.currentEXP;
    }

    public int getLevel()
    {
        return this.currentLevel;
    }

    public void addEXP(int amount)
    {
        this.currentEXP += amount;

        int xp = this.currentEXP;
        for(int i = 2; i <= maxLevel; i++)
        {
            if(xp >= this.reqXP(i))
            {
                levelUp();
                xp -= this.reqXP(i);
            }
            else return;
        }
    }

    private int reqXP(int lvl)
    {
        return (lvl - 1) * 2;
    }

    private void levelUp()
    {
        if(this.currentLevel < this.maxLevel) this.currentLevel++;
    }

    //DEBUG ONLY
    public void setLevel(int level)
    {
        this.currentLevel = level;
    }
}
