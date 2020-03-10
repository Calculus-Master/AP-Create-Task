package pokedungeon.utils.properties;

import pokedungeon.utils.interfaces.IPropertyCastHelper;

public abstract class PropertyBase implements IPropertyCastHelper
{
    String propertyName;

    public PropertyBase(String propertyName)
    {
        this.propertyName = propertyName;
    }

    public String getPropertyName()
    {
        return this.propertyName;
    }
}
