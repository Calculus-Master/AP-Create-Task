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
        MEGA_VENUSAUR(1, true, false),
		CHARMANDER(1, false, false),
        CHARMELEON(1, false, false),
        CHARIZARD(1, false, false),
        MEGA_CHARIZARD_X(1, true, false),
        MEGA_CHARIZARD_Y(1, true, false),
        SQUIRTLE(1, false, false),
        WARTORTLE(1, false, false),
        BLASTOISE(1, false, false),
        MEGA_BLASTOISE(1, true, false);

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
            case CHARMANDER: return new Charmander();
            case CHARMELEON: return new Charmeleon();
            case CHARIZARD: return new Charizard();
            case MEGA_CHARIZARD_X: return new MegaCharizardX();
            case MEGA_CHARIZARD_Y: return new MegaCharizardY();
            case SQUIRTLE: return new Squirtle();
            case WARTORTLE: return new Wartortle();
            case BLASTOISE: return new Blastoise();
            case MEGA_BLASTOISE: return new MegaBlastoise();
            default: throw new IllegalArgumentException("Invalid Input");
        }
    }

    //Individual Pokemon Declarations

		/** Pokemon Methods
         * this.moves.addMove() - Add moves
         * this.initMoves() - Call after addMoves()
         * this.typeEff.addTypeEff() - Type Effectiveness
         * this.typeEff.copyEffMap(Pokemon p) - If the Pokemon is an evolution and can copy the eff map of the basic evo
         * this.addForm() - Add a Mega or alternate form
		*/

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
            this.typeEff.create();
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
            this.typeEff.create();
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
            this.addMega(EnumPokemon.MEGA_VENUSAUR);
            this.typeEff.create();
        }
    }

    public static class MegaVenusaur extends Pokemon
    {
        public MegaVenusaur()
        {
            super("Mega Venusaur", 3, 1);
            this.setDefaultStats(Type.GRASS, Type.POISON, 80, 100, 123, 122, 120, 80, MEGA_S2_ENERGY);

            this.moves.copyMoves(new Venusaur());

            this.initMoves();
            this.typeEff.create();
        }
    }

    public static class Charmander extends Pokemon
    {
        public Charmander()
        {
            super("Charmander", 4, 1);
            this.setDefaultStats(Type.FIRE, Type.FIRE, 39, 52, 43, 60, 50, 65, BASIC_ENERGY);

            this.moves.addMove(MoveDB.GROWL, 1);
            this.moves.addMove(MoveDB.SCRATCH, 1);
            this.moves.addMove(MoveDB.EMBER, 4);

            this.initMoves();
            this.typeEff.create();
        }
    }

    public static class Charmeleon extends Pokemon
    {
        public Charmeleon()
        {
            super("Charmeleon", 5, 1);
            this.setDefaultStats(Type.FIRE, Type.FIRE, 58, 64, 58, 80, 65, 80, STAGE1_ENERGY);

            this.moves.addMove(MoveDB.GROWL, 1);
            this.moves.addMove(MoveDB.SCRATCH, 1);
            this.moves.addMove(MoveDB.EMBER, 1);

            this.initMoves();
            this.typeEff.create();
        }
    }

    public static class Charizard extends Pokemon
    {
        public Charizard()
        {
            super("Charizard", 6, 1);
            this.setDefaultStats(Type.FIRE, Type.FLYING, 78, 84, 78, 109, 85, 100, STAGE2_ENERGY);

            this.moves.addMove(MoveDB.GROWL, 1);
            this.moves.addMove(MoveDB.SCRATCH, 1);
            this.moves.addMove(MoveDB.EMBER, 1);

            this.initMoves();
            this.addMega(EnumPokemon.MEGA_CHARIZARD_X, EnumPokemon.MEGA_CHARIZARD_Y);
            this.typeEff.create();
        }
    }

    public static class MegaCharizardX extends Pokemon
    {
        public MegaCharizardX()
        {
            super("Mega Charizard X", 6, 1);
            this.setDefaultStats(Type.FIRE, Type.DRAGON, 78, 130, 111, 130, 85, 100, MEGA_S2_ENERGY);

            this.moves.copyMoves(new Charizard());

            this.initMoves();
            this.typeEff.create();
        }
    }

    public static class MegaCharizardY extends Pokemon
    {
        public MegaCharizardY()
        {
            super("Mega Charizard Y", 6, 1);
            this.setDefaultStats(Type.FIRE, Type.FLYING, 78, 104, 78, 159, 115, 100, MEGA_S2_ENERGY);

            this.moves.copyMoves(new Charizard());

            this.initMoves();
            this.typeEff.create();
        }
    }

    public static class Squirtle extends Pokemon
    {
        public Squirtle()
        {
            super("Squirtle", 7, 1);
            this.setDefaultStats(Type.WATER, Type.WATER, 44, 48, 65, 50, 64, 43, BASIC_ENERGY);

            this.initMoves();
            this.typeEff.create();
        }
    }

    public static class Wartortle extends Pokemon
    {
        public Wartortle()
        {
            super("Wartortle", 8, 1);
            this.setDefaultStats(Type.WATER, Type.WATER, 59, 63, 80, 65, 80, 58, STAGE1_ENERGY);

            this.initMoves();
            this.typeEff.create();
        }
    }

    public static class Blastoise extends Pokemon
    {
        public Blastoise()
        {
            super("Blastoise", 9, 1);
            this.setDefaultStats(Type.WATER, Type.WATER, 79, 83, 100, 85, 105, 78, STAGE2_ENERGY);

            this.initMoves();
            this.addMega(EnumPokemon.MEGA_BLASTOISE);
            this.typeEff.create();
        }
    }

    public static class MegaBlastoise extends Pokemon
    {
        public MegaBlastoise()
        {
            super("Mega Blastoise", 9, 1);
            this.setDefaultStats(Type.WATER, Type.WATER, 79, 103, 120, 135, 115, 78, MEGA_S2_ENERGY);

            this.initMoves();
            this.typeEff.create();
        }
    }
}
