package application;

import java.util.List;

public class _2opt
{
//    public static void run(List<Integer> cities, int[][] distances)
//    {
//        int bestGain = 1;
//
//        int bestI, bestC;
//        bestI = bestC = -1;
//
//        boolean firstImprove =
//
//
//        while(bestGain != 0)
//        {
//            bestGain = 0;
//
//            for(int i = 1; i < cities.size(); i++)
//            {
//                for( int c = 1; c < cities.size(); c++)
//                {
//                    int gain = computeGain(i, j);
//
//                    if(gain < bestGain)
//                    {
//                        bestGain = gain;
//                        bestI = i;
//                        bestC = c;
//                    }
//                }
//            }
//        }
//    }

    //QUESTA VERSIONE DA ERRORE SULLA MATRICE DELLE DISTANZE
//    public static List<Integer> runWiki(Tour tour, int[][] distances)
//    {
//        //List<Integer> cities = tour.getTour();
//        int size = tour.getTour().size();
//        int bestDistance = 1;
//        boolean _break = false;
//
//        while(bestDistance != 0)
//        {
//            _break = false;
//            bestDistance = tour.computeTourCost();
//
//            for(int i = 0; i < size -1; i++)
//            {
//                for(int c = i + 1; c < size; c++)
//                {
//                    Tour newTour = _2optSwap(tour, i, c);
//
//                   // Tour newTour = new Tour();
//                    //newTour.setTour(newRoute);
//
//                    int newDistance = newTour.computeTourCost();
//
//                    if(newDistance < bestDistance)
//                    {
//                        //cities = newRoute;
//                        tour = newTour;
//                        bestDistance = newDistance;
//                        _break = true;
//                        break;
//                    }
//                }
//
//                if(_break)
//                    break;
//            }
//        }
//
//        return tour.getTour();
//    }
//
//    private static Tour _2optSwap(Tour tour, int i, int k)
//    {
//        //List<Integer> newTour = new ArrayList<>();
//
//        //TourMigliore tour = new TourMigliore(citites);
//       // TourMigliore newTour = new TourMigliore();
//
//        Tour newTour = new Tour(tour.getDistances());
//
//        int size = tour.getTour().size();
//
//        for(int c = 0; c <= i - 1; c++)
//            newTour.setTourCity(c, tour.getTourCity(c));
//
//        int dec = 0;
//        System.out.println(dec);
//        for(int c = i; c<= k; c++)
//        {
//            newTour.setTourCity(c, tour.getTourCity(k-dec));
//            dec++;
//        }
//
//        for(int c = k+1; c < size; c++)
//            newTour.setTourCity(c, tour.getTourCity(c));
//
//
//        return newTour;
//    }

//    public static Tour run(Tour tour)
//    {
//        int size = tour.getTour().size() - 1;
//
//        Tour newTour = new Tour();
//
//        newTour.setDistanceMatrix(tour.getDistanceMatrix());
//
//        for(int i = 0; i < size; i++)
//            newTour.setTourCity(i, tour.getTourCity(i));
//
//        int improve = 0;
//        int iteration = 0;
//
//        while(improve < 800)
//        {
//            int bestDistance = tour.computeTourCost();
//
//            for(int i = 1; i < size - 1; i++)
//            {
//                for(int k = i + 1; k < size; k++)
//                {
//                    _2optSwap(i, k, tour, newTour);
//                    iteration++;
//                    int newDistance = newTour.computeTourCost();
//
//                    if(newDistance < bestDistance)
//                    {
//                        improve = 0;
//
//                        for(int j = 0; j < size; j++)
//                        {
//                            tour.setTourCity(j, newTour.getTourCity(j));
//                        }
//
//                        bestDistance = newDistance;
//                    }
//                }
//            }
//
//            improve++;
//        }
//
//        return tour;
//    }
//
//    private static void _2optSwap(int i, int k, Tour tour, Tour newTour)
//    {
//        int size = tour.getTour().size() - 1;
//
//        for(int c = 0; c <= i - 1; c++)
//        {
//            newTour.setTourCity(c, tour.getTourCity(c));
//        }
//
//        int dec = 0;
//
//        for(int c = i; c <= k; c++)
//        {
//            newTour.setTourCity(c, tour.getTourCity( k - dec));
//            dec++;
//        }
//
//        for(int c = k + 1; c < size; c++)
//        {
//            newTour.setTourCity(c, tour.getTourCity(c));
//        }
//    }


    public static Tour _2opt(Tour oldTour)
    {
        Tour tour = new Tour();
        //tour.setCities(oldTour.getCities());
        //tour.setBestKnown(oldTour.getBestKnown());
        tour.setDistanceMatrix(oldTour.getDistanceMatrix());


        for(int i : oldTour.getTour())
            tour.addTourCity(i);
        tour.getTour().remove(tour.getTour().size()-1);

        //tour.getTour()

        Tour newTour = new Tour();

        newTour.setDistanceMatrix(tour.getDistanceMatrix());

        int bestDistance = tour.computeTourCost();
        int newDistance;
        int swaps = 1;
        int improve = 0;
        int iterations = 0;
        long comparisions = 0;

        int size = tour.getTour().size();

        while(swaps != 0)
        {
            swaps = 0;

            for(int i = 1; i < size - 2; i++)
            {
                for(int j = i + 1; j < size - 1; j++)
                {
                    comparisions++;

                    if((tour.distanceBetweenCities(i, i-1) + tour.distanceBetweenCities(j+1, j)) >= (tour.distanceBetweenCities(i, j+1) + tour.distanceBetweenCities(i-1, j)))
                    {
                        newTour = swap(tour, i, j);

                        newDistance = newTour.computeTourCost();

                        if(newDistance < bestDistance)
                        {
                            tour = newTour;
                            bestDistance = newDistance;
                            swaps++;
                            improve++;
                        }
                    }
                }
            }

            iterations++;

        }

        tour.addTourCity(-1);

        tour.setCities(oldTour.getCities());
        tour.setBestKnown(oldTour.getBestKnown());
        return tour;
    }

    private static Tour swap(Tour tour, int i, int j)
    {
        Tour newTour = new Tour();
        newTour.setDistanceMatrix(tour.getDistanceMatrix());


        int size = tour.getTour().size();

        for(int c = 0; c <= i - 1; c++)
        {
            newTour.addTourCity(tour.getTourCity(c));
        }

        int dec = 0;
        for(int c = i; c <= j; c++)
        {
            newTour.addTourCity(tour.getTourCity(j - dec));
            dec++;
        }

        for(int c = j + 1; c < size; c++)
        {
            newTour.addTourCity(tour.getTourCity(c));
        }

        return newTour;
    }




}
