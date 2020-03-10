package pokedungeon.attacks;

import pokedungeon.pkmn.Pokemon;
import pokedungeon.utils.enums.EnumProperty;
import pokedungeon.utils.enums.EnumStats;
import pokedungeon.utils.enums.MoveCategory;
import pokedungeon.utils.enums.Type;

import java.util.Random;

public abstract class Move
{
    private String name;
    private Type type;
    private MoveCategory cat;
    private int baseDMG;
    private int accuracy;

    public Move(String name, Type type, MoveCategory cat, int baseDMG, int accuracy)
    {
        this.name = name;
        this.type = type;
        this.cat = cat;
        this.baseDMG = baseDMG;
        this.accuracy = accuracy;
    }

    public int getEnergyDrain()
    {
        int catBase = cat.equals(MoveCategory.STATUS) ? 0 : (cat.equals(MoveCategory.SPECIAL) ? 1 : 2);

        return catBase + (baseDMG / 50) * 2;
    }

    public String getName()
    {
        return this.name;
    }

    public Type getType()
    {
        return this.type;
    }

    public MoveCategory getMoveCategory()
    {
        return this.cat;
    }

    public int getBaseDMG()
    {
        return this.baseDMG;
    }

    public int getAccuracy()
    {
        return this.accuracy;
    }

    public boolean isAccurate()
    {
        return (new Random()).nextInt(101) <= this.getAccuracy();
    }

    public void dealDamage(Pokemon user, Pokemon opponent)
    {
        EnumStats attackType = this.getMoveCategory().equals(MoveCategory.PHYSICAL) ? EnumStats.ATTACK : EnumStats.SPECIAL_ATTACK;
        EnumStats defenseType = this.getMoveCategory().equals(MoveCategory.PHYSICAL) ? EnumStats.DEFENSE : EnumStats.SPECIAL_DEFENSE;
        double atkStat = user.property(EnumProperty.BATTLE_STATS).statsCast().getStat(attackType);
        double defStat = opponent.property(EnumProperty.BATTLE_STATS).statsCast().getStat(defenseType);

        double modifier = opponent.property(EnumProperty.TYPE_EFFECTIVENESS).typeEffCast().get().get(this.type);
        double fullDamage = ((((2 * user.getLevel() / 5.0) + 2.0) * this.getBaseDMG() * (atkStat / defStat) / 50) + 2);
        opponent.property(EnumProperty.BATTLE_STATS).statsCast().decrHP((int)(fullDamage * modifier));
    }

    public abstract void use(Pokemon user, Pokemon opponent);
}