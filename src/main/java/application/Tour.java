package application;

import java.util.List;

public class Tour
{
    private List<City> cities;
    private int[][] distanceMatrix;

    public Tour(List<City> cities)
    {
        this.cities = cities;
        distanceMatrix = new int[cities.size()][cities.size()];
    }

    public void calcuateDistances(DistanceCalculator calculator)
    {
        for(int r = 0; r < distanceMatrix.length; r++)
        {
            for(int c = 0; c < distanceMatrix[0].length; c++)
            {
                distanceMatrix[r][c] = calculator.calculateDistance(cities.get(r), cities.get(c));
            }
        }
    }

    public int[][] getDistances()
    {
        return distanceMatrix;
    }

}
