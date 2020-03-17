package pokedungeon.attacks;

import pokedungeon.pkmn.Pokemon;
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

    public boolean isAccurate()
    {
        return (new Random()).nextInt(101) <= this.accuracy;
    }

    public void dealDamage(Pokemon user, Pokemon opponent)
    {
        EnumStats attackType = this.cat.equals(MoveCategory.PHYSICAL) ? EnumStats.ATTACK : EnumStats.SPECIAL_ATTACK;
        EnumStats defenseType = this.cat.equals(MoveCategory.PHYSICAL) ? EnumStats.DEFENSE : EnumStats.SPECIAL_DEFENSE;
        double atkStat = user.stats().getStat(attackType);
        double defStat = opponent.stats().getStat(defenseType);

        double modifier = opponent.typeEff().get().get(this.type);
        double fullDamage = ((((2 * user.getLevel() / 5.0) + 2.0) * this.baseDMG * (atkStat / defStat) / 50) + 2);
        opponent.stats().decrHP((int)(fullDamage * modifier));
    }

    public abstract void use(Pokemon user, Pokemon opponent);
}
