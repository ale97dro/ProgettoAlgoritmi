package aco;

import application.Tour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/*
    PSEUDOCODE

    LOOP
        posiziona casualmente le formiche sulle città

        FOR ALL città DO
            FOR ALL formica do
                IF exploitation THEN
                    vai nella città megliore non ancora visitata
                ELSE
                    scegli la prossima città in modo probabilistico
                END IF
                aggiungi la città al tour
                aggiorna localmente il feromone
            END FOR
            calcola la lunghezza del tour generato
        END FOR
        aggiorna globalmente il feromone
    END LOOP




 */
public class ACO
{
    private static final int ANTS_NUMBER = 10;
    private double c = 1.0;
    private double alpha = 1;
    private double beta = 5.0;
    private double evaporation = 0.5;
    private double Q0 = 0.95; //probabilità di scegliere tra exploration e exploitation
    private double antFactor = 0.8; //bho

    private int currentIndex = 0;

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
        pheromoneMatrix = new double[tourSize][tourSize];

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


        //Muovo le formiche sul percorso

        for(int c = currentIndex; c < tourSize; c++) //add all cities to the tour
        {
            for (int i = 0; i < ANTS_NUMBER; i++) //add cities for all ants
            {
                String scelta = exploitationOrExploration();


                if (scelta == "Exploitation")
                {

                }
                else //Exploration
                {

                }


                updateLocalPheromone(pheromoneMatrix, lastInsertedCity, lastCity);

            }


        }


        //Muovo le formiche
        //Calcolo percorso migliore
        //Aggiorno il feromone


        Tour newTour = new Tour();
        return newTour;
    }


}
