package pokedungeon.pkmn;

import pokedungeon.attacks.MoveDB;
import pokedungeon.utils.Global;
import pokedungeon.utils.enums.Type;
import pokedungeon.utils.interfaces.IEnergyValues;

public class PKMNDB implements IEnergyValues
{
    public static void init()
    {
        EnumPokemon.INITIALIZE.toString();
    }

    public enum EnumPokemon
    {
        INITIALIZE(0, false, false),
        BULBASAUR(1, false, false),
        IVYSAUR(1, false, false),
        VENUSAUR(1, false, false),
        MEGA_VENUSAUR(1, true, false);

        EnumPokemon(int gen, boolean isMega, boolean isLegend)
        {
            if(isMega) Global.MEGA.add(this);

            if(isLegend) Global.LEGENDARIES.add(this);

            switch(gen)
            {
                case 0: System.out.println("Pokemon Enum has been initialized"); break;
                case 1: Global.GEN_1.add(this); break;
                case 2: Global.GEN_2.add(this); break;
                case 3: Global.GEN_3.add(this); break;
                case 4: Global.GEN_4.add(this); break;
                case 5: Global.GEN_5.add(this); break;
                case 6: Global.GEN_6.add(this); break;
                case 7: Global.GEN_7.add(this); break;
                case 8: Global.GEN_8.add(this); break;
                default: throw new IllegalArgumentException("Invalid Input");
            }
        }
    }

    public static Pokemon create(EnumPokemon p)
    {
        switch(p)
        {
            case BULBASAUR: return new Bulbasaur();
            case IVYSAUR: return new Ivysaur();
            case VENUSAUR: return new Venusaur();
            case MEGA_VENUSAUR: return new MegaVenusaur();
            default: throw new IllegalArgumentException("Invalid Input");
        }
    }

    //Individual Pokemon Declarations

    public static class Bulbasaur extends Pokemon
    {
        public Bulbasaur()
        {
            super("Bulbasaur", 1, 1);
            this.setDefaultStats(Type.GRASS, Type.POISON, 45, 49, 49, 65, 65, 45, BASIC_ENERGY);

            this.moves.addMove(MoveDB.GROWL, 1);
            this.moves.addMove(MoveDB.TACKLE, 1);
            this.moves.addMove(MoveDB.VINE_WHIP, 3);
            this.moves.addMove(MoveDB.GROWTH, 6);
            this.moves.addMove(MoveDB.LEECH_SEED, 9);
            this.initMoves();

            this.typeEff.addTypeEff(Type.FIRE, 2);
            this.typeEff.addTypeEff(Type.WATER, 0.5);
            this.typeEff.addTypeEff(Type.ELECTRIC, 0.5);
            this.typeEff.addTypeEff(Type.GRASS, 0.25);
            this.typeEff.addTypeEff(Type.ICE, 2);
            this.typeEff.addTypeEff(Type.FIGHTING, 0.5);
            this.typeEff.addTypeEff(Type.FLYING, 2);
            this.typeEff.addTypeEff(Type.PSYCHIC, 2);
            this.typeEff.addTypeEff(Type.FAIRY, 0.5);
        }
    }

    public static class Ivysaur extends Pokemon
    {
        public Ivysaur()
        {
            super("Ivysaur", 2, 1);
            this.setDefaultStats(Type.GRASS, Type.POISON, 60, 62, 63, 80, 80, 60, STAGE1_ENERGY);

            this.moves.addMove(MoveDB.GROWL, 1);
            this.moves.addMove(MoveDB.TACKLE, 1);
            this.moves.addMove(MoveDB.VINE_WHIP, 1);
            this.moves.addMove(MoveDB.GROWTH, 1);
            this.moves.addMove(MoveDB.LEECH_SEED, 9);
            this.initMoves();

            this.typeEff.copyEffMap(new Bulbasaur());
        }
    }

    public static class Venusaur extends Pokemon
    {
        public Venusaur()
        {
            super("Venusaur", 3, 1);
            this.setDefaultStats(Type.GRASS, Type.POISON, 80, 82, 83, 100, 100, 80, STAGE2_ENERGY);

            this.moves.addMove(MoveDB.GROWL, 1);
            this.moves.addMove(MoveDB.TACKLE, 1);
            this.moves.addMove(MoveDB.VINE_WHIP, 1);
            this.moves.addMove(MoveDB.GROWTH, 1);
            this.moves.addMove(MoveDB.LEECH_SEED, 9);
            this.initMoves();

            this.typeEff.copyEffMap(new Bulbasaur());
        }
    }

    public static class MegaVenusaur extends Pokemon
    {
        public MegaVenusaur()
        {
            super("Mega Venusaur", 3, 1);
            this.setDefaultStats(Type.GRASS, Type.POISON, 80, 100, 123, 122, 120, 80, STAGE2_ENERGY);

            this.moves.addMove(MoveDB.GROWL, 1);
            this.moves.addMove(MoveDB.TACKLE, 1);
            this.moves.addMove(MoveDB.VINE_WHIP, 1);
            this.moves.addMove(MoveDB.GROWTH, 1);
            this.moves.addMove(MoveDB.LEECH_SEED, 9);
            this.initMoves();

            this.typeEff.copyEffMap(new Bulbasaur());
        }
    }
}
