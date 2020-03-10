package pokedungeon.utils;

import pokedungeon.pkmn.PKMNDB;
import pokedungeon.utils.enums.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Global
{
    public static final List<Type> typeList = Arrays.asList(Type.NORMAL, Type.FIRE, Type.WATER, Type.ELECTRIC, Type.GRASS, Type.ICE, Type.FIGHTING, Type.POISON, Type.GROUND, Type.FLYING, Type.PSYCHIC, Type.BUG, Type.ROCK, Type.GHOST, Type.DRAGON, Type.DARK, Type.STEEL, Type.FAIRY);

    public static final List<PKMNDB.EnumPokemon> GEN_1 = new ArrayList<>();
    public static final List<PKMNDB.EnumPokemon> GEN_2 = new ArrayList<>();
    public static final List<PKMNDB.EnumPokemon> GEN_3 = new ArrayList<>();
    public static final List<PKMNDB.EnumPokemon> GEN_4 = new ArrayList<>();
    public static final List<PKMNDB.EnumPokemon> GEN_5 = new ArrayList<>();
    public static final List<PKMNDB.EnumPokemon> GEN_6 = new ArrayList<>();
    public static final List<PKMNDB.EnumPokemon> GEN_7 = new ArrayList<>();
    public static final List<PKMNDB.EnumPokemon> GEN_8 = new ArrayList<>();

    public static List<PKMNDB.EnumPokemon> allPKMN()
    {
        List<PKMNDB.EnumPokemon> ALL = new ArrayList<>();
        ALL.addAll(GEN_1);
        ALL.addAll(GEN_2);
        ALL.addAll(GEN_3);
        ALL.addAll(GEN_4);
        ALL.addAll(GEN_5);
        ALL.addAll(GEN_6);
        ALL.addAll(GEN_7);
        ALL.addAll(GEN_8);
        return ALL;
    }
}
