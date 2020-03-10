package pokedungeon.utils.interfaces;

public interface IEnergyValues
{
    double BASIC_ENERGY = 100;
    double STAGE1_ENERGY = 150;
    double STAGE2_ENERGY = 200;
    double LEGENDARY_ENERGY = 250;
    double MYTHICAL_ENERGY = 300;

    default double getMega(double energy)
    {
        return energy * 1.5;
    }

    default double getPassiveGen(double energy)
    {
        return energy / 100;
    }
}
