package pokedungeon.attacks;

import pokedungeon.pkmn.Pokemon;
import pokedungeon.utils.enums.EnumStats;
import pokedungeon.utils.enums.MoveCategory;
import pokedungeon.utils.enums.Type;

public class MoveDB
{
    //Variables to Access the Moves
    public static final Move GROWL = new Growl();
    public static final Move TACKLE = new Tackle();
    public static final Move VINE_WHIP = new VineWhip();

    //Move Classes
    private static class Growl extends Move
    {
        public Growl()
        {
            super("Growl", Type.NORMAL, MoveCategory.STATUS, 0, 100);
        }

        /**
         * Growl lowers the target's Attack by one stage.
         */

        @Override
        public void use(Pokemon user, Pokemon opponent)
        {
            for(int i = 0; i < 3; i++) opponent.stats().lowerIV(EnumStats.ATTACK);
        }
    }

    private static class Tackle extends Move
    {
        public Tackle()
        {
            super("Tackle", Type.NORMAL, MoveCategory.PHYSICAL, 40, 100);
        }

        /**
         * Tackle deals damage with no additional effects.
         */
        @Override
        public void use(Pokemon user, Pokemon opponent)
        {
            this.dealDamage(user, opponent);
        }
    }

    private static class VineWhip extends Move
    {
        public VineWhip()
        {
            super("Vine Whip", Type.GRASS, MoveCategory.PHYSICAL, 45, 100);
        }

        /**
         * Vine Whip deals damage with no additional effect.
         */
        @Override
        public void use(Pokemon user, Pokemon opponent)
        {
            this.dealDamage(user, opponent);
        }
    }
}
