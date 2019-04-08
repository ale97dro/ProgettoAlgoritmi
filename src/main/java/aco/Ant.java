package aco;

public class Ant
{
    private boolean[] visitedCity; //tell you if the city is just visited
    private int[] tour; //ant tour
    private int cityNumber;

    public Ant(int tourSize, int initialCity)
    {
        this.cityNumber = 0;
        this.visitedCity = new boolean[tourSize];
        this.tour = new int[tourSize];

        for(boolean c : visitedCity)
            c = false;

        this.visitedCity[initialCity] = true;
        this.tour[cityNumber] = initialCity;
        ++this.cityNumber;
    }

    /**
     * The ant visit a new city
     * @param city
     * @return
     */
    public boolean visitCity(int city)
    {
        if(visitedCity[city])
            return false;

        visitedCity[city] = true;
        tour[cityNumber] = city;
        ++cityNumber;

        return true;
    }
}
