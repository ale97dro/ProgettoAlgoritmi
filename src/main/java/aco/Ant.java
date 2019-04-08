package aco;

public class Ant
{
    private int[] visitedCity;

    public Ant(int tourSize, int initialCity)
    {
        this.visitedCity = new int[tourSize];

        this.visitedCity[0] = initialCity;
    }
}
