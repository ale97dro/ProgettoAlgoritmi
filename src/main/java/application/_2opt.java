package application;

import java.util.List;

public class _2opt
{

    public static void _2opt(Tour tour)
    {
        while(true)
        {
            if(!trySwap(tour))
                break;
        }
    }

    private static boolean trySwap(Tour tour) {
        int[][] distanceMatrix = tour.getDistanceMatrix();
        int size = tour.getTour().size();
        int gain;
        int best_gain = 0;
        int index_i = 0;
        int index_k = 0;
        for (int i = 1; i < size - 1; i++) {
            for (int k = i; k < size - 1; k++) {
                //gain = (a,d) + (b,c) - (c,d) - (a,b)
                // a = i, b = k, c = k+1, d = i+1;
                int a = tour.getTour().get(i);
                int b = tour.getTour().get(k);
                int c = tour.getTour().get(k + 1);
                int d = tour.getTour().get(i + 1);

                if (Math.abs(i - k) < 2) {
                    continue;
                }
                gain = distanceMatrix[a][d] + distanceMatrix[b][c] - distanceMatrix[d][c] - distanceMatrix[a][b];
                if (gain > best_gain) {
                    best_gain = gain;
                    index_i = i;
                    index_k = k;
                }
            }
        }

        if (best_gain > 0) {
            int tempX = tour.getTour().get(index_i + 1);
            tour.getTour().set(index_i + 1, tour.getTour().get(index_k));
            tour.getTour().set(index_k, tempX);

            for (int ind = 0; ind < Math.abs(index_i - index_k) - 2; ind++) {
                if (index_i + ind + 2 > index_k - ind - 1)
                    break;
                int temp = tour.getTour().get(index_i + ind + 2);
                tour.getTour().set(index_i + ind + 2, tour.getTour().get(index_k - ind - 1));
                tour.getTour().set(index_k - ind - 1, temp);
            }
            return true;
        }

        return false;
    }

//    public static Tour _2opt(Tour oldTour)
//    {
//        Tour tour = new Tour();
//        //tour.setCities(oldTour.getCities());
//        //tour.setBestKnown(oldTour.getBestKnown());
//
//
//        //oldTour.addTourCity(oldTour.getTourCity(0));
//        tour.setTour(oldTour.getTour());
//        tour.setDistanceMatrix(oldTour.getDistanceMatrix());
//        //tour.getTour().remove(tour.getTour().size()-2);
//
//        //tour.getTour()
//
//        //Tour newTour = new Tour();
//
//
//
//        //newTour.setDistanceMatrix(tour.getDistanceMatrix());
//
//        int bestDistance = tour.computeTourCost();
//       // int bestDistance = tour.computeTourCostWithout1();
//        int newDistance;
//        boolean swaps = true;
//
//
//        int oldBestDistance = bestDistance;
//        int old_i, old_j;
//        old_i = old_j = -1;
//
//        int size = tour.getTour().size();
//
//        while(swaps)
//        {
//            swaps = false;
//
//           // System.out.println(tour.getTour().size());
//            for(int i = 1; i < tour.getTour().size() -2; i++) //-2
//            {
//                for(int j = i + 1 ; j < tour.getTour().size() - 1; j++) //-1
//                {
//                    if((tour.distanceBetweenCities(tour.getTourCity(i), tour.getTourCity(i-1)) + tour.distanceBetweenCities(tour.getTourCity(j+1), tour.getTourCity(j))) >=  //>=
//                            (tour.distanceBetweenCities(tour.getTourCity(i), tour.getTourCity(j+1)) + tour.distanceBetweenCities(tour.getTourCity(i-1), tour.getTourCity(j))))
//                    {
//                        Tour newTour = swap(tour, i, j);
//                        //newDistance = newTour.computeTourCostWithout1();
//                        newDistance = newTour.computeTourCost();
//
//                        if(newDistance < bestDistance)
//                        {
//                            old_i = i;
//                            old_j = j;
//                            bestDistance = newDistance;
//                        }
//                    }
//                }
//            }
//
//            if(bestDistance < oldBestDistance)
//            {
//                tour = swap(tour, old_i, old_j);
//                tour.setBestKnown(oldTour.getBestKnown());
//                //System.out.println(calcoloErrore(tour.computeTourCostWithout1(), tour.getBestKnown()));
//                //System.out.println(calcoloErrore(tour.computeTourCost(), tour.getBestKnown()));
//                swaps = true;
//                oldBestDistance = bestDistance; //Todo: controllare questo
//                //System.out.println(old_i + " "+ old_j);
//            }
//        }
//
//        //tour.addTourCity(-1);
//        //tour.computeTourCostWithout1();
//        tour.computeTourCost();
//        tour.setCities(oldTour.getCities());
//        return tour;
//    }
//
//
//    public static double calcoloErrore(int tourCost, int bestKnown)
//    {
//        return ((tourCost - bestKnown) / (double)bestKnown) * 100;
//    }
//
//    private static Tour swap(Tour tour, int i, int j)
//    {
//        Tour newTour = new Tour();
//        newTour.setDistanceMatrix(tour.getDistanceMatrix());
//
//
//        int size = tour.getTour().size();
//
//        for(int c = 0; c <= i - 1; c++)
//        {
//            newTour.addTourCity(tour.getTourCity(c));
//        }
//
//        int dec = 0;
//        for(int c = i; c <= j; c++)
//        {
//            newTour.addTourCity(tour.getTourCity(j - dec));
//            dec++;
//        }
//
//        for(int c = j + 1; c < size; c++)
//        {
//            newTour.addTourCity(tour.getTourCity(c));
//        }
//
//        return newTour;
//    }
}
