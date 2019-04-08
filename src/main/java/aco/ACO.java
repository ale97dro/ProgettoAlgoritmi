package aco;

import application.Tour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ACO
{
    private static final int ANTS_NUMBER = 10;
    private double c = 1.0;
    private double alpha = 1;
    private double beta = 5.0;
    private double evaporation = 0.5;
    private double Q0 = 0.95;
    private double antFactor = 0.8; //bho


    private int[][] distanceMatrix;
    private double[][] pheromoneMatrix;
    private List<Ant> ants;

    public ACO()
    {
        this.ants = new ArrayList<>();


    }


    public Tour antColony(Tour oldTour)
    {
        int tourSize = oldTour.getTour().size(); //Number of cities

        distanceMatrix = oldTour.getDistanceMatrix();
        pheromoneMatrix = new double[oldTour.getTour().size()][tourSize];

        //Set the initial pheromone
        double startPheromone = 1.0/(oldTour.computeTourCost() * tourSize);
        for(int r = 0; r < tourSize; r++)
            for(int c = 0; c < tourSize; c++)
                pheromoneMatrix[r][c] = startPheromone;


        //Distribute ants to cities
        Random rand = new Random(0);
        for(int i = 0; i < ANTS_NUMBER; i++)
        {
            Ant ant = new Ant(tourSize, rand.nextInt(tourSize));
            ants.add(ant);
        }


        //Muovo le formiche
        //Calcolo percorso migliore
        //Aggiorno il feromone


        return null;
    }
}
