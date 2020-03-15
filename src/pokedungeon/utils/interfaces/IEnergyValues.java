package pokedungeon.utils.interfaces;

public interface IEnergyValues
{
    double BASIC_ENERGY = 100;
    double STAGE1_ENERGY = 150;
    double STAGE2_ENERGY = 200;
    double LEGENDARY_ENERGY = 250;
    double MYTHICAL_ENERGY = 300;

    double MEGA_S1_ENERGY = STAGE1_ENERGY * 1.75;
    double MEGA_S2_ENERGY = STAGE2_ENERGY * 1.75;
    double MEGA_LEGEND_ENERGY = LEGENDARY_ENERGY * 1.75;

    default double getPassiveGen(double energy)
    {
        return energy / 100;
    }
}
