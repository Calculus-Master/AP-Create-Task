package pokedungeon.utils.properties;

import pokedungeon.pkmn.Pokemon;
import pokedungeon.utils.enums.EnumStats;

import java.util.HashMap;
import java.util.Random;

public class PropertyBattleStats extends PropertyBase
{
    private final HashMap<EnumStats, Integer> baseStats = new HashMap<>();
    private final HashMap<EnumStats, Integer> statIVs = new HashMap<>();

    private int health, attack, defense, special_attack, special_defense, speed;

    public PropertyBattleStats(Pokemon owner)
    {
        super("Battle Stats", owner);
        this.assignIVs();
    }

    public void decrHP(int amount)
    {
        this.health = Math.max(this.health - amount, 0);
        if(this.health == 0) this.owner.faint();
    }

    public void incrHP(int amount)
    {
        this.health = Math.min(this.health + amount, this.statFormula(EnumStats.HP));
    }

    public void updateStat(EnumStats s)
    {
        int other = this.statFormula(s);
        switch(s)
        {
            case HP: this.health = other; break;
            case ATTACK: this.attack = other; break;
            case DEFENSE: this.defense = other; break;
            case SPECIAL_ATTACK: this.special_attack = other; break;
            case SPECIAL_DEFENSE: this.special_defense = other; break;
            case SPEED: this.speed = other; break;
            default: throw new IllegalArgumentException("Invalid Input");
        }
    }

    public int getStat(EnumStats s)
    {
        this.updateAllStats();
        switch(s)
        {
            case HP: return this.health;
            case ATTACK: return this.attack;
            case DEFENSE: return this.defense;
            case SPECIAL_ATTACK: return this.special_attack;
            case SPECIAL_DEFENSE: return this.special_defense;
            case SPEED: return this.speed;
            default: throw new IllegalArgumentException("Invalid Input");
        }
    }

    private int statFormula(EnumStats s)
    {
        //Equation for calculating HP: ((2 * Base + IV) * Level) / 100 + Level + 10
        int newHP = (((2 * this.baseStats.get(s) + this.getIV(s)) * this.owner.getLevel()) / 100) + this.owner.getLevel() + 10;
        //Equation for other stats: [ ((2 * Base + IV) * Level) / 100 + 5 ] * nature (NYI)
        int newStat = (((2 * this.baseStats.get(s) + this.getIV(s)) * this.owner.getLevel()) / 100 + 5);

        return s.equals(EnumStats.HP) ? newHP : newStat;
    }

    public int getIV(EnumStats s)
    {
        return this.statIVs.get(s);
    }

    public double getTotalIV()
    {
        return Math.round((this.statIVs.values().stream().mapToDouble(x -> x / 31D).sum() / this.statIVs.values().size()) * 10000) / 100D;
    }

    public void boostIV(EnumStats s)
    {
        this.statIVs.put(s, Math.min(this.getStat(s) + 1, 31));
        this.updateStat(s);
    }

    public void lowerIV(EnumStats s)
    {
        this.statIVs.put(s, Math.max(this.statIVs.get(s) - 1, 0));
        this.updateStat(s);
    }

    public void assignIVs()
    {
        Random r = new Random();
        this.statIVs.put(EnumStats.HP, r.nextInt(31) + 1);
        this.statIVs.put(EnumStats.ATTACK, r.nextInt(31) + 1);
        this.statIVs.put(EnumStats.DEFENSE, r.nextInt(31) + 1);
        this.statIVs.put(EnumStats.SPECIAL_ATTACK, r.nextInt(31) + 1);
        this.statIVs.put(EnumStats.SPECIAL_DEFENSE, r.nextInt(31) + 1);
        this.statIVs.put(EnumStats.SPEED, r.nextInt(31) + 1);
    }

    public void updateAllStats()
    {
        this.updateStat(EnumStats.ATTACK);
        this.updateStat(EnumStats.DEFENSE);
        this.updateStat(EnumStats.SPECIAL_ATTACK);
        this.updateStat(EnumStats.SPECIAL_DEFENSE);
        this.updateStat(EnumStats.SPEED);
    }

    public void setInitialStats(int HP, int ATK, int DEF, int SPATK, int SPDEF, int SPD)
    {
        this.baseStats.put(EnumStats.HP, HP);
        this.baseStats.put(EnumStats.ATTACK, ATK);
        this.baseStats.put(EnumStats.DEFENSE, DEF);
        this.baseStats.put(EnumStats.SPECIAL_ATTACK, SPATK);
        this.baseStats.put(EnumStats.SPECIAL_DEFENSE, SPDEF);
        this.baseStats.put(EnumStats.SPEED, SPD);

        this.health = HP;
        this.attack = ATK;
        this.defense = DEF;
        this.special_attack = SPATK;
        this.special_defense = SPDEF;
        this.speed = SPD;

        this.updateStat(EnumStats.HP);
        this.updateAllStats();
    }
}
