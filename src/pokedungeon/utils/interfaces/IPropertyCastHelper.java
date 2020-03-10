package pokedungeon.utils.interfaces;

import pokedungeon.utils.properties.*;

public interface IPropertyCastHelper
{
    default PropertyID idCast()
    {
        return (PropertyID)this;
    }

    default PropertyType typeCast()
    {
        return (PropertyType)this;
    }

    default PropertyEXP expCast()
    {
        return (PropertyEXP)this;
    }

    default PropertyBattleStats statsCast()
    {
        return (PropertyBattleStats)this;
    }

    default PropertyEnergy energyCast()
    {
        return (PropertyEnergy)this;
    }

    default PropertyMoves movesCast()
    {
        return (PropertyMoves)this;
    }

    default PropertyEffectiveness typeEffCast()
    {
        return (PropertyEffectiveness)this;
    }
}
