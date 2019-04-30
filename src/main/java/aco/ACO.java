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
    private int iterationNumber = 300; //195 iterazioni e il 76 va a 0

    private double c = 1.0;
    private double alpha = 0.1;
    private double beta = 2.0;
    private double evaporation = 0.5;
    private double Q0 = 0.95; //probabilità di scegliere tra exploration e exploitation
    private double antFactor = 0.8; //bho
    private double exploitation = 0.9; //possibilità di scegliere exploitation
    private double t0; //initial pheromone

    private Random randomGenerator = new Random(10_000_000);

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
        int bestTour = Integer.MAX_VALUE;
        Ant bestAnt = null;
        Random rand = new Random(0);

        int tourSize = oldTour.getCities().size();
        pheromoneMatrix = new double[tourSize][tourSize];
        distanceMatrix = oldTour.getDistanceMatrix();

        //Set the initial pheromone
        double startPheromone = 1.0 / (oldTour.getTourCost() * tourSize);
        //t0 = startPheromone;
        for (int r = 0; r < tourSize; r++)
            for (int c = 0; c < tourSize; c++)
                pheromoneMatrix[r][c] = startPheromone;

        for(int iteration = 0; iteration < iterationNumber; iteration++)
        {
            this.ants = new ArrayList<>();
            //int tourSize = oldTour.getTour().size(); //Number of cities

            //Distribute ants to cities

            for (int i = 0; i < ANTS_NUMBER; i++) {
                Ant ant = new Ant(tourSize, rand.nextInt(tourSize));
                ants.add(ant);
            }

            currentIndex++; //number of visited city

            //Muovo le formiche sul percorso
            for (int c =0; c < tourSize - 1; c++) //add all cities to the tour
            {
                //for (int i = 0; i < ANTS_NUMBER; i++) //add cities for all ants
                for (Ant a : ants)
                {
                    int nextCity = nextCity(a);
                    a.visitCity(nextCity);

                    //updateLocalPheromone(pheromoneMatrix, lastInsertedCity, lastCity);
                    // (1 - alpha) * costoArco + alpha * fermoneIniziale
                    int lastVisited = a.lastVisited();
                    int penultimateVisited = a.penultimateVisited();

                    double updatePheromone = (1.0 - alpha) * pheromoneMatrix[lastVisited][penultimateVisited] + (alpha*startPheromone);
                    pheromoneMatrix[lastVisited][penultimateVisited] = updatePheromone;
                    pheromoneMatrix[penultimateVisited][lastVisited] = updatePheromone;

                   // pheromoneMatrix[lastVisited][penultimateVisited] = (1.0-alpha)*distanceMatrix[lastVisited][penultimateVisited] + alpha*startPheromone;
                    //pheromoneMatrix[penultimateVisited][lastVisited] = (1.0-alpha)*distanceMatrix[lastVisited][penultimateVisited] + alpha*startPheromone;


//                    pheromoneMatrix[a.lastVisited()][a.penultimateVisited()] = (1.0-alpha)*distanceMatrix[a.lastVisited()][a.penultimateVisited()] + alpha*startPheromone;
//                    pheromoneMatrix[a.penultimateVisited()][a.lastVisited()] = (1.0-alpha)*distanceMatrix[a.lastVisited()][a.penultimateVisited()] + alpha*startPheromone;
                }
            }


            //2OPT (deve finire con -1 il tour che gli passo nella mia versione)
            for(Ant a : ants)
            {
                a.getAntTour().setBestKnown(oldTour.getBestKnown());
                a.getAntTour().setDistanceMatrix(oldTour.getDistanceMatrix());

                //a.addTourTerminator();
                a.getAntTour().addTourCity(a.getAntTour().getTourCity(0));
                //a.setAntTour(_2opt._2opt(a.getAntTour()));// MIA VERSIONE


                _2opt._2opt(a.getAntTour()); //VERSIONE BRE

                //a.getAntTour().computeTourCost();

                if(a.getAntTour().computeTourCost() < bestTour)
                {
                    bestTour = a.getAntTour().getTourCost();
                    bestAnt = a;
                }
            }


            //aggiorno fermone del percorso migliore

            for(int i = 0; i < bestAnt.getAntTour().getTour().size() - 1; i++)
            {
                pheromoneMatrix[bestAnt.getAntTour().getTourCity(i)][bestAnt.getAntTour().getTourCity(i+1)] = (1.0-alpha) * pheromoneMatrix[bestAnt.getAntTour().getTourCity(i)][bestAnt.getAntTour().getTourCity(i+1)] + (alpha * 1.0/bestAnt.getAntTour().computeTourCost());
                pheromoneMatrix[bestAnt.getAntTour().getTourCity(i+1)][bestAnt.getAntTour().getTourCity(i)] = (1.0-alpha) * pheromoneMatrix[bestAnt.getAntTour().getTourCity(i+1)][bestAnt.getAntTour().getTourCity(i)] + alpha * 1.0/bestAnt.getAntTour().computeTourCost();
            }
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
                   // if(ant.lastVisited() != i)
                    //{
                        //questo si chiama score
                        probability = pheromoneMatrix[ant.lastVisited()][i] * Math.pow(1.0 / distanceMatrix[ant.lastVisited()][i], beta);
                        //probability = pheromoneMatrix[ant.lastVisited()][i] / distanceMatrix[ant.lastVisited()][i]/1.0;

                        if (probability > maxProbability) {
                            maxProbability = probability;
                            designedCity = i; //the next city to visit
                        }
                    //}
                }
            }
        }
        else //Formula: (fermone/costo)/ somma_di_fermone/costo per tutti gli archi
        {
            double totalCostPhermone = 0;
            double sum = 0;
            double threshold = randomGenerator.nextDouble();
            HashMap<Integer, Double> probabilities = new HashMap<>();


            for(int i = 0; i < size; i++) {
                if (!visitedCity[i]) {
                    if(ant.lastVisited() != i)
                    {
                        //probability = pheromoneMatrix[ant.lastVisited()][i] / distanceMatrix[ant.lastVisited()][i]/1.0;
                        probability = pheromoneMatrix[ant.lastVisited()][i] * Math.pow(1.0 / distanceMatrix[ant.lastVisited()][i], beta);
                        //probability = Math.pow(pheromoneMatrix[ant.lastVisited()][i], alpha) * Math.pow(1.0 / distanceMatrix[ant.lastVisited()][i], beta);
                        totalCostPhermone += probability;
                        probabilities.put(i, probability);
                    }
                }
            }


            //MIA VERSIONE
            for(Integer k : probabilities.keySet())
            {
                if(!visitedCity[k])
                {
                    sum += probabilities.get(k)/totalCostPhermone;
                    if( sum >= threshold)
                    {
                        designedCity = k;
                        break;
                    }
                }
            }

        }

        return designedCity;
    }



}
