package application;

import java.util.List;

public class _2opt
{
    public static Tour _2opt(Tour oldTour)
    {
        Tour tour = new Tour();
        //tour.setCities(oldTour.getCities());
        //tour.setBestKnown(oldTour.getBestKnown());


        oldTour.addTourCity(oldTour.getTourCity(0));
        tour.setTour(oldTour.getTour());
//        for(int i : oldTour.getTour())
//            tour.addTourCity(i);
        tour.setDistanceMatrix(oldTour.getDistanceMatrix());
        tour.getTour().remove(tour.getTour().size()-2);

        //tour.getTour()

        //Tour newTour = new Tour();



        //newTour.setDistanceMatrix(tour.getDistanceMatrix());

        int bestDistance = tour.computeTourCost();
       // int bestDistance = tour.computeTourCostWithout1();
        int newDistance;
        boolean swaps = true;


        int oldBestDistance = bestDistance;
        int old_i, old_j;
        old_i = old_j = -1;

        int size = tour.getTour().size();

        while(swaps)
        {
            swaps = false;

            System.out.println(tour.getTour().size());
            for(int i = 1; i < tour.getTour().size() -2; i++) //-2
            {
                for(int j = i + 1 ; j < tour.getTour().size() - 1; j++) //-1
                {
                    if((tour.distanceBetweenCities(tour.getTourCity(i), tour.getTourCity(i-1)) + tour.distanceBetweenCities(tour.getTourCity(j+1), tour.getTourCity(j))) >=  //>=
                            (tour.distanceBetweenCities(tour.getTourCity(i), tour.getTourCity(j+1)) + tour.distanceBetweenCities(tour.getTourCity(i-1), tour.getTourCity(j))))
                    {
                        Tour newTour = swap(tour, i, j);
                        //newDistance = newTour.computeTourCostWithout1();
                        newDistance = newTour.computeTourCost();

                        if(newDistance < bestDistance)
                        {
                            old_i = i;
                            old_j = j;
                            bestDistance = newDistance;
                        }
                    }
                }
            }

            if(bestDistance < oldBestDistance)
            {
                tour = swap(tour, old_i, old_j);
                tour.setBestKnown(oldTour.getBestKnown());
                //System.out.println(calcoloErrore(tour.computeTourCostWithout1(), tour.getBestKnown()));
                //System.out.println(calcoloErrore(tour.computeTourCost(), tour.getBestKnown()));
                swaps = true;
                oldBestDistance = bestDistance; //Todo: controllare questo
                //System.out.println(old_i + " "+ old_j);
            }
        }

        //tour.addTourCity(-1);
        //tour.computeTourCostWithout1();
        tour.computeTourCost();
        tour.setCities(oldTour.getCities());
        return tour;
    }


    public static double calcoloErrore(int tourCost, int bestKnown)
    {
        return ((tourCost - bestKnown) / (double)bestKnown) * 100;
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
