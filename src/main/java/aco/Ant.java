package aco;

import application.Tour;

public class Ant
{
    private boolean[] visitedCity; //tell you if the city is just visited
    //private int[] tour; //ant tour
    private Tour tour;
    private int cityNumber;

    /**
     *
     * @param tourSize
     * @param initialCity first city the ant visit
     */
    public Ant(int tourSize, int initialCity)
    {
        this.cityNumber = 0;
        this.visitedCity = new boolean[tourSize];
        //this.tour = new int[tourSize];
        this.tour = new Tour();

        for(boolean c : visitedCity)
            c = false;

        this.visitedCity[initialCity] = true;
        this.tour.addTourCity(initialCity);
        //this.tour[cityNumber] = initialCity;
        ++this.cityNumber;
    }

    /**
     * The ant visit a new city
     * @param city
     * @return
     */
    public boolean visitCity(int city)
    {
        if(city != -1)
        {
            if (visitedCity[city])
                return false;

            visitedCity[city] = true;
            tour.addTourCity(city);
            //tour[cityNumber] = city;
            ++cityNumber;

            return true;
        }
        else
        {
            //TODO: questa parte non va bene
            //tour.addTourCity(-1);
            return true;
        }


    }

    public void addTourTerminator()
    {
        tour.addTourCity(-1);
    }

    public int lastVisited()
    {
        return tour.getTourCity(cityNumber-1);
    }

    public int penultimateVisited()
    {
        return tour.getTourCity(cityNumber-2);
    }

    public boolean[] getVisitedCity()
    {
        return visitedCity;
    }

    public Tour getAntTour()
    {
        return tour;
    }

    public void setAntTour(Tour tour)
    {
        this.tour = tour;
    }
}
