package pokedungeon.pkmn;

import pokedungeon.attacks.Move;
import pokedungeon.utils.Global;
import pokedungeon.pkmn.*;
import pokedungeon.utils.enums.EnumStats;
import pokedungeon.utils.enums.Type;
import pokedungeon.utils.properties.*;

import java.util.*;

public abstract class Pokemon
{
    //Identification Properties
    private String name;
    private int pokedexNum;
    private int gen;
    private Set<Type> type;
    private boolean isFainted = false;

    //Complex Properties
    private PropertyEXP exp;
    private PropertyBattleStats battleStats;
    private PropertyEnergy energy;
    protected PropertyMoves moves;
    protected PropertyEffectiveness typeEff;
    private PropertyStatusConditions statusConditions;

    //Mega and Form Transformation
    private PKMNDB.EnumPokemon megaX = null;
    private PKMNDB.EnumPokemon megaY = null;
    private List<PKMNDB.EnumPokemon> altForms = new ArrayList<>();

    public Pokemon(String name, int pokedex, int gen)
    {
        exp = new PropertyEXP(this);
        battleStats = new PropertyBattleStats(this);
        energy = new PropertyEnergy(this);
        moves = new PropertyMoves(this);
        typeEff = new PropertyEffectiveness(this);
        statusConditions = new PropertyStatusConditions(this);

        this.name = name;
        this.pokedexNum = pokedex;
        this.gen = gen;
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
     */
    public void useAttack(Pokemon opponent)
    {
        assert !this.isFainted() && !opponent.isFainted() : "One of the Battling Pokemon is Fainted";
        assert !this.statusConditions.isAsleep && !this.statusConditions.isParalyzed;

        System.out.println(this.getName() + " can use any of these moves: " + Global.asString(this.getMoveSet()));
        int index = (new Scanner(System.in)).nextInt() - 1;
        Move chosenMove = this.moves.getCurrentMoveSet().get(index);

        //User must be alive & the move must exist in their moveset
        assert this.moves.getCurrentMoveSet().size() > index : this.getName() + " does not know " + chosenMove.getName();

        //User must have enough energy to use the move
        if(this.energy.get() < chosenMove.getEnergyDrain())
        {
            System.out.println("Cannot use move due to low energy");
            return;
        }

        boolean acc = chosenMove.isAccurate();

        this.moves.updateAvailableMoves(this.getLevel());
        if(acc) chosenMove.use(this, opponent);
        this.energy.decr(chosenMove.getEnergyDrain());

        if(!acc) System.out.println(this.getName() + " missed using " + chosenMove.getName() + "!");
        else System.out.println(this.getName() + Global.wrapHP(this) + "used " + chosenMove.getName() + " on " + opponent.getName() + Global.wrapHP(opponent) + "!");
    }

    /**
     * Method that gets called after the moves are added to the pokemon, in order to create an initial moveset
     */
    public void initMoves()
    {
        for(Move m : this.moves.getAllMoves().keySet()) if(this.moves.getAllMoves().get(m) == 1) this.moves.learnMove(m);
    }

    public void setDefaultStats(Type typeA, Type typeB, int baseHP, int baseATK, int baseDEF, int baseSPATK, int baseSPDEF, int baseSPD, double maxEnergy)
    {
        this.type = new HashSet<>(Arrays.asList(typeA, typeB));
        this.battleStats.setInitialStats(baseHP, baseATK, baseDEF, baseSPATK, baseSPDEF, baseSPD);
        this.energy.setMax(maxEnergy);
    }

    //Below Methods involve transforming a pokemon into a Mega or Form

    public void addMega(PKMNDB.EnumPokemon megaX, PKMNDB.EnumPokemon megaY)
    {
        this.megaX = megaX;
        this.megaY = megaY;
    }

    public void addMega(PKMNDB.EnumPokemon mega)
    {
        this.megaX = mega;
        this.megaY = mega;
    }

    public void addForm(PKMNDB.EnumPokemon form)
    {
        this.altForms.add(form);
    }

    private Map<String, Object> persistentData()
    {
        Map<String, Object> pData = new HashMap<>();

        pData.put("Level", this.getLevel());
        pData.put("EXP", this.experience().getEXP());

        return pData;
    }

    public void applyData(Map<String, Object> pData)
    {
        for(int i = 1; i <= (int)pData.get("Level"); i++) this.experience().addEXP(this.experience().getRequiredEXPLevel(i));

        this.experience().addEXP((int)pData.get("EXP"));
    }

    /**
     * Not Yet Called
     * Transforms a pokemon, if eligible, into its mega evolved form
     */
    public Pokemon transformEvent()
    {
        Pokemon form;
        if(this.canTransform())
        {
            if(this.megaX != null && this.megaY != null) form = PKMNDB.create((new Random().nextInt(1)) == 1 ? this.megaX : this.megaY);
            else form = PKMNDB.create(this.altForms.get(new Random().nextInt(this.altForms.size())));

            form.applyData(this.persistentData());
            return form;
        }
        return null;
    }

    private boolean canTransform()
    {
        return (new Random().nextInt(5000) <= 5) && ((this.megaX != null && this.megaY != null) || !this.altForms.isEmpty());
    }

    //Properties
    public PropertyEXP experience()
    {
        return this.exp;
    }

    public PropertyBattleStats stats()
    {
        return this.battleStats;
    }

    public PropertyEnergy energy()
    {
        return this.energy;
    }

    public PropertyMoves moves()
    {
        return this.moves;
    }

    public PropertyEffectiveness typeEff()
    {
        return this.typeEff;
    }

    public PropertyStatusConditions statusConditions()
    {
        return this.statusConditions;
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
        return this.name;
    }

    public int getPokedexNumber()
    {
        return this.pokedexNum;
    }

    public int getGeneration()
    {
        return this.gen;
    }

    public Set<Type> getType()
    {
        return this.type;
    }

    public boolean isFainted()
    {
        return this.isFainted;
    }

    public void faint()
    {
        this.isFainted = true;
    }

    public int getLevel()
    {
        return this.exp.getLevel();
    }

    public List<Move> getMoveSet()
    {
        return this.moves.getCurrentMoveSet();
    }
}
