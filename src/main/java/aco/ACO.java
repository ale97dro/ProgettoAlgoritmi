package aco;

import application.Tour;
import application._2opt;

import java.util.ArrayList;
import java.util.HashMap;
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
    private int iterationNumber = 10;

    private double c = 1.0;
    private double alpha = 1;
    private double beta = 5.0;
    private double evaporation = 0.5;
    private double Q0 = 0.95; //probabilità di scegliere tra exploration e exploitation
    private double antFactor = 0.8; //bho
    private double exploitation = 0.95; //possibilità di scegliere exploitation

    private Random randomGenerator = new Random(10_000_000);

    private int currentIndex = 0;

    private int[][] distanceMatrix;
    private double[][] pheromoneMatrix;
    private List<Ant> ants;

    public ACO()
    {
        this.ants = new ArrayList<>();


    }


    //Corrisponde per ora ad una sola iterazione
    public Tour antColony(Tour oldTour)
    {
        int bestTour = Integer.MAX_VALUE;
        Ant bestAnt = null;

        for(int iteration = 0; iteration < iterationNumber; iteration++)
        {
            this.ants = new ArrayList<>();
            //int tourSize = oldTour.getTour().size(); //Number of cities
            int tourSize = oldTour.getCities().size();

            distanceMatrix = oldTour.getDistanceMatrix();
            pheromoneMatrix = new double[tourSize][tourSize];

            //Set the initial pheromone
            double startPheromone = 1.0 / (oldTour.getTourCost() * tourSize);
            for (int r = 0; r < tourSize; r++)
                for (int c = 0; c < tourSize; c++)
                    pheromoneMatrix[r][c] = startPheromone;


            //Distribute ants to cities
            Random rand = new Random(0);
            for (int i = 0; i < ANTS_NUMBER; i++) {
                Ant ant = new Ant(tourSize, rand.nextInt(tourSize));
                ants.add(ant);
            }

            currentIndex++; //number of visited city

            //Muovo le formiche sul percorso
            for (int c = 0; c < tourSize; c++) //add all cities to the tour
            {
                //for (int i = 0; i < ANTS_NUMBER; i++) //add cities for all ants
                for (Ant a : ants)
                {
                    int nextCity = nextCity(a);
                    a.visitCity(nextCity);

                    //updateLocalPheromone(pheromoneMatrix, lastInsertedCity, lastCity);
                }
            }

            //2OPT (deve finire con -1 il tour che gli passo)
            for(Ant a : ants)
            {
                a.getAntTour().setBestKnown(oldTour.getBestKnown());
                a.getAntTour().setDistanceMatrix(oldTour.getDistanceMatrix());
                //a.addTourTerminator();

                a.setAntTour(_2opt._2opt(a.getAntTour()));
                a.getAntTour().computeTourCost();

                if(a.getAntTour().getTourCost() < bestTour)
                {
                    bestTour = a.getAntTour().getTourCost();
                    bestAnt = a;
                }
            }





            //SCELGO IL MIGLIORE

            //AGGIORNO FEROMONE GLOBALE PER LA FORMICA MIGLIORE

            //RIESEGUO TUTTO IL PROCESSO
        }

        return bestAnt.getAntTour();
    }

    private int nextCity(Ant ant)
    {
        double rand = randomGenerator.nextDouble();
        boolean[] visitedCity = ant.getVisitedCity();
        int size = visitedCity.length;
        double probability = 0;
        double maxProbability = Double.MIN_VALUE;
        int designedCity = -1;

        if(rand < exploitation)
        {
            //fermone/costo
            for(int i = 0; i < size; i++) //scorro le città
            {
                if(!visitedCity[i]) //se la città non è visitata, posso calcolare i parametri che mi servono
                {
                    if(ant.lastVisited() != i)
                    {
                        probability = pheromoneMatrix[ant.lastVisited()][i] / distanceMatrix[ant.lastVisited()][i];

                        if (probability > maxProbability) {
                            maxProbability = probability;
                            designedCity = i; //the next city to visit
                        }
                    }
                }
            }
        }
        else //Formula: (fermone/costo)/ somma_di_fermone/costo per tutti gli archi
        {
            double totalCostPhermone = 0;
            //List<Double> probabilities = new ArrayList<>(size); //all probabilities NO: PERDO IL NOME DELLA CITTA'
            HashMap<Integer, Double> probabilities = new HashMap<>();


            for(int i = 0; i < size; i++) {
                if (!visitedCity[i]) {
                    if(ant.lastVisited() != i)
                    {
                        probability = pheromoneMatrix[ant.lastVisited()][i] / distanceMatrix[ant.lastVisited()][i];
                        totalCostPhermone += probability;
                        probabilities.put(i, probability);
                        //probabilities.add(probability);
                    }
                }
            }

            //size = probabilities.keySet().size(); //update size

            for(Integer k : probabilities.keySet())
            {
                if(!visitedCity[k])
                {
                    if((probabilities.get(k)/totalCostPhermone) > maxProbability)
                    {
                        maxProbability = probabilities.get(k);
                        designedCity = k;
                    }
                }
            }


//            for(int i = 0; i < size; i++)
//            {
//                if(!visitedCity[i])
//                {
//                    if((probabilities.get(i)./totalCostPhermone) > maxProbability)
//                    {
//                        maxProbability = probabilities.get(i);
//                        designedCity = probabilities.;
//                    }
//                }
//            }

        }


        return designedCity;
    }



}