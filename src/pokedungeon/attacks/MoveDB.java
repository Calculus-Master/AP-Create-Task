package pokedungeon.attacks;

import pokedungeon.pkmn.Pokemon;
import pokedungeon.utils.enums.EnumStats;
import pokedungeon.utils.enums.MoveCategory;
import pokedungeon.utils.enums.Type;

public class MoveDB
{
    private static final int stage = 2;
    
    //Variables to Access the Moves
    public static final Move GROWL = new Growl();
    public static final Move TACKLE = new Tackle();
    public static final Move VINE_WHIP = new VineWhip();
    public static final Move GROWTH = new Growth();
    public static final Move LEECH_SEED = new LeechSeed();

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
            for(int i = 0; i < stage; i++) opponent.stats().lowerIV(EnumStats.ATTACK);
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

    private static class Growth extends Move
    {
        public Growth()
        {
            super("Growth", Type.NORMAL, MoveCategory.STATUS, 0, 100);
        }

        /**
         * Growth raises the user's Attack and Special Attack by one stage each.
         */

        @Override
        public void use(Pokemon user, Pokemon opponent)
        {
            for(int i = 0; i < stage; i++) 
            {
              user.stats().boostIV(EnumStats.ATTACK);
              user.stats().boostIV(EnumStats.SPECIAL_ATTACK);
            }
        }
    }

    private static class LeechSeed extends Move
    {
        public LeechSeed()
        {
            super("Leech Seed", Type.GRASS, MoveCategory.STATUS, 0, 90);
        }

        /**
         * Leech Seed plants a seed on the target that drains 1⁄8 of its maximum HP at the end of * each turn and restores it to the user, or any Pokémon that takes its place. It does not * work on Grass-type Pokémon.
         */
				
        @Override
        public void use(Pokemon user, Pokemon opponent)
        {
            if(!opponent.getType().contains(Type.GRASS))
            {
                opponent.stats().decrHP(1 / 8);
                user.stats().incrHP((1 / 8) * opponent.stats().getMaxHealth());
            }
        }
    }
}
