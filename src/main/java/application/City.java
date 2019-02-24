package application;

import java.util.Objects;

public class City
{
    private int name;
    private double x;
    private double y;

    public static City create(int name, double x, double y)
    {
        return new City(name, x, y);
    }

    private City(int name, double x, double y)
    {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public int getName()
    {
        return name;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return name == city.name &&
                Double.compare(city.x, x) == 0 &&
                Double.compare(city.y, y) == 0;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, x, y);
    }
}
