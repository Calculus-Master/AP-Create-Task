package pokedungeon;

import pokedungeon.pkmn.PKMNDB;
import pokedungeon.pkmn.Pokemon;
import pokedungeon.utils.enums.EnumProperty;

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

        System.out.println(b.property(EnumProperty.MOVES).movesCast().getMoveSet());
    }
}
