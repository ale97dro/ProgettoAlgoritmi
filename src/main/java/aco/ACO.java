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
    private int iterationNumber = 1000; //195 iterazioni e il 76 va a 0

    private double alpha = 0.1;
    private double beta = 2.0;
    private double exploitation = 0.9; //probability to choose exploration or exploitation

    //Seeds for random generators
    private int seed1 = 10_000_000;
    private int seed2 = 0;

    private Random randomGenerator = new Random(seed1);
    private Random rand = new Random(seed2);

    //private int currentIndex = 0;

    private int[][] distanceMatrix;
    private double[][] pheromoneMatrix;

    public ACO()
    {
        this.randomGenerator = new Random(seed1);
        this.rand = new Random(seed2);
    }

    public ACO(double alpha, double beta, double exploitation, int seed1, int seed2, int iterationNumber)
    {
        this.randomGenerator = new Random(seed1);
        this.rand = new Random(seed2);

        this.alpha = alpha;
        this.beta = beta;
        this.exploitation = exploitation;
        this.seed1 = seed1;
        this.seed2 = seed2;
        this.iterationNumber = iterationNumber;
    }

    public Tour antColony(Tour oldTour)
    {
        int bestTour = Integer.MAX_VALUE;
        Ant bestAnt = null;


        int tourSize = oldTour.getCities().size(); //Number cities on tour
        pheromoneMatrix = new double[tourSize][tourSize];
        distanceMatrix = oldTour.getDistanceMatrix();

        //Set the initial pheromone
        double startPheromone = 1.0 / (oldTour.getTourCost() * tourSize);
        //t0 = startPheromone;
        for (int r = 0; r < tourSize; r++)
            for (int c = 0; c < tourSize; c++)
                pheromoneMatrix[r][c] = startPheromone;


        long startTime = System.currentTimeMillis();

        for(int iteration = 0; iteration < iterationNumber && (System.currentTimeMillis() - startTime) / 1000 < 175; iteration++)
        {
            List<Ant> ants = new ArrayList<>();

            //Distribute ants to cities
            for (int i = 0; i < ANTS_NUMBER; i++) {
                Ant ant = new Ant(tourSize, rand.nextInt(tourSize));
                ants.add(ant);
            }

            //Move ants on tour
            for (int c =0; c < tourSize - 1; c++) //add all cities to the tour for alla ants
            {
                //for (int i = 0; i < ANTS_NUMBER; i++) //add cities for all ants
                for (Ant a : ants)
                {
                    int nextCity = nextCity(a);
                    a.visitCity(nextCity);

                    int lastVisited = a.lastVisited();
                    int penultimateVisited = a.penultimateVisited();

                    //update = (1.0 - alpha) * pheromoneBetweenLast2Cities + (alpha * startPheromone)
                    double updatePheromone = (1.0 - alpha) * pheromoneMatrix[lastVisited][penultimateVisited] + (alpha*startPheromone);
                    pheromoneMatrix[lastVisited][penultimateVisited] = updatePheromone;
                    pheromoneMatrix[penultimateVisited][lastVisited] = updatePheromone;

                    //Update phermone with edges cost. Apparently it's not a good idea :(
                   // pheromoneMatrix[lastVisited][penultimateVisited] = (1.0-alpha)*distanceMatrix[lastVisited][penultimateVisited] + alpha*startPheromone;
                    //pheromoneMatrix[penultimateVisited][lastVisited] = (1.0-alpha)*distanceMatrix[lastVisited][penultimateVisited] + alpha*startPheromone;
                }
            }


            //2OPT
            for(Ant a : ants)
            {
                a.getAntTour().setBestKnown(oldTour.getBestKnown());
                a.getAntTour().setDistanceMatrix(oldTour.getDistanceMatrix());

                a.getAntTour().addTourCity(a.getAntTour().getTourCity(0));

                //a.setAntTour(_2opt._2opt(a.getAntTour()));// COST VERSION
                _2opt._2opt(a.getAntTour(), startTime); // GAIN VERSION

                if(a.getAntTour().computeTourCost() < bestTour)
                {
                    bestTour = a.getAntTour().getTourCost();
                    bestAnt = a;
                }
            }

            //update phermone on best tour
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
        boolean[] visitedCity = ant.getVisitedCity();
        int size = visitedCity.length;

        double probability = 0;
        double maxProbability = Double.MIN_VALUE;
        int designedCity = -1; //next city to visit

        double rand = randomGenerator.nextDouble();

        if(rand < exploitation)
        {
            //fermone/costo
            for(int i = 0; i < size; i++) //scorro le città
            {
                if(!visitedCity[i]) //se la città non è visitata, posso calcolare i parametri che mi servono
                {
                    //questo si chiama score
                    probability = pheromoneMatrix[ant.lastVisited()][i] * Math.pow(1.0 / distanceMatrix[ant.lastVisited()][i], beta);

                    if (probability > maxProbability) {
                        maxProbability = probability;
                        designedCity = i; //the next city to visit
                    }
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
