package pokedungeon.pkmn;

import pokedungeon.attacks.Move;
import pokedungeon.utils.enums.EnumProperty;
import pokedungeon.utils.enums.EnumStats;
import pokedungeon.utils.enums.Type;
import pokedungeon.utils.properties.*;

import java.util.List;

public abstract class Pokemon
{
    private PropertyID ID = new PropertyID();
    private PropertyType type = new PropertyType();
    private PropertyEXP exp = new PropertyEXP();
    private PropertyBattleStats battleStats = new PropertyBattleStats(this);
    private PropertyEnergy energy = new PropertyEnergy();
    protected PropertyMoves moves = new PropertyMoves();
    protected PropertyEffectiveness typeEff = new PropertyEffectiveness();

    public Pokemon(String name, int pokedex, int gen)
    {
        this.ID.set(name, pokedex, gen);
    }

    /**
     * Use a move
     *
     * What has to occur during this event:
     * 1. User must be alive
     * 2. User must have the move in their MoveSet
     * 3. User must have enough energy
     * 4. Use the attack
     * 5. Drain energy
     * 6. Output results
     *
     * @param opponent
     * @param index
     */
    public void useAttack(Pokemon opponent, int index)
    {
        Move chosenMove = this.moves.getMoveSet().get(index);
        this.moves.updateAvailableMoves(this.getLevel());

        if(this.energy.get() < chosenMove.getEnergyDrain())
        {
            System.out.println("Cannot use move due to low energy");
            return;
        }

        chosenMove.use(this, opponent);
        this.energy.decr(chosenMove.getEnergyDrain());
        System.out.println(this.getName() + " used " + chosenMove.getName() + " on " + opponent.getName() + "!");
    }

    /**
     * Method that gets called after the moves are added to the pokemon, in order to create an initial moveset
     */
    public void initMoves()
    {
        for(Move m : this.moves.getAllMoves().keySet()) if(this.moves.getAllMoves().get(m) == 1) this.moves.learnMove(this, m);
    }

    public void setDefaultStats(Type typeA, Type typeB, int baseHP, int baseATK, int baseDEF, int baseSPATK, int baseSPDEF, int baseSPD, int maxEnergy)
    {
        this.type.set(typeA, typeB);
        this.battleStats.setInitialStats(baseHP, baseATK, baseDEF, baseSPATK, baseSPDEF, baseSPD);
        this.energy.setMax(maxEnergy);
    }

    public <PROPERTY extends PropertyBase> PROPERTY property(EnumProperty p)
    {
        switch(p)
        {
            case ID: return (PROPERTY)this.ID;
            case TYPE: return (PROPERTY)this.type;
            case EXP: return (PROPERTY)this.exp;
            case BATTLE_STATS: return (PROPERTY)this.battleStats;
            case ENERGY: return (PROPERTY)this.energy;
            case MOVES: return (PROPERTY)this.moves;
            case TYPE_EFFECTIVENESS: return (PROPERTY)this.typeEff;
            default: return null;
        }
    }

    //Misc Utility Methods
    public void printIVs()
    {
        String hpIV = "HP: " + this.battleStats.getIV(EnumStats.HP) + " / 31";
        String atkIV = "ATK: " + this.battleStats.getIV(EnumStats.ATTACK) + " / 31";
        String defIV = "DEF: " + this.battleStats.getIV(EnumStats.DEFENSE) + " / 31";
        String spatkIV = "SPATK: " + this.battleStats.getIV(EnumStats.SPECIAL_ATTACK) + " / 31";
        String spdefIV = "SPDEF: " + this.battleStats.getIV(EnumStats.SPECIAL_DEFENSE) + " / 31";
        String spdIV = "SPD: " + this.battleStats.getIV(EnumStats.SPEED) + " / 31";
        String totalIV = "Total IV: " + this.battleStats.getTotalIV() + "%";
        String nL = "\n";

        String output = this.getName() + ":" + nL + hpIV + nL + atkIV + nL + defIV + nL + spatkIV + nL + spdefIV + nL + spdIV + nL + totalIV;
        System.out.println(output);
    }

    //Read-Only Getter Methods
    public String getName()
    {
        return this.ID.getName();
    }

    public int getPokedexNumber()
    {
        return this.ID.getPokedex();
    }

    public int getGeneration()
    {
        return this.ID.getGen();
    }

    public int getLevel()
    {
        return this.exp.getLevel();
    }

    public List<Move> getMoveSet()
    {
        return this.moves.getMoveSet();
    }
}