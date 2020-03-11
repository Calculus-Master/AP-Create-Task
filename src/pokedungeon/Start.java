package pokedungeon;

import pokedungeon.pkmn.PKMNDB;
import pokedungeon.pkmn.Pokemon;

/**
 * All Pokemon names, Pokemon move names, and Pokemon textures are property of the Pokemon Company International.
 * © 2020 Pokémon. TM, ® Nintendo.
 */
public class Start
{
    public static void main(String[] args)
    {
        //DO NOT REMOVE
        PKMNDB.init();
        Pokemon b = PKMNDB.create(PKMNDB.EnumPokemon.BULBASAUR);
        Pokemon i = PKMNDB.create(PKMNDB.EnumPokemon.IVYSAUR);
        Pokemon v = PKMNDB.create(PKMNDB.EnumPokemon.VENUSAUR);

        v.experience().addEXP(2000);

        v.useAttack(i);

        i.useAttack(v);
    }
}
