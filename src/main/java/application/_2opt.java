package application;

import java.util.ArrayList;
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

    public static List<Integer> runWiki(Tour tour, int[][] distances)
    {
        List<Integer> cities = tour.getOrderedCity();

        int bestDistance = 1;
        boolean _break = false;

        while(bestDistance != 0)
        {
            _break = false;
            bestDistance = tour.computeTourCost();

            for(int i = 0; i < cities.size() -1; i++)
            {
                for(int c = i + 1; c < cities.size(); c++)
                {
                    List<Integer> newRoute = _2optSwap(cities, i, c);

                    Tour newTour = new Tour(null);
                    newTour.setOrderedCity(newRoute);

                    int newDistance = newTour.computeTourCost();

                    if(newDistance < bestDistance)
                    {
                        cities = newRoute;
                        tour = newTour;
                        bestDistance = newDistance;
                        _break = true;
                        break;
                    }
                }

                if(_break)
                    break;
            }
        }

        return tour.getOrderedCity();
    }

    private static List<Integer> _2optSwap(List<Integer> citites, int i, int k)
    {
        //List<Integer> newTour = new ArrayList<>();

        TourMigliore tour = new TourMigliore(citites);
        TourMigliore newTour = new TourMigliore();

        int size = citites.size();

        for(int c = 0; c <= i - 1; c++)
            newTour.setCity(c, tour.getCity(c));

        int dec = 0;
        System.out.println(dec);
        for(int c = i; c<= k; c++)
        {
            newTour.setCity(c, tour.getCity(k-dec));
            dec++;
        }

        for(int c = k+1; c < size; c++)
            newTour.setCity(c, tour.getCity(c));


        return newTour.getAllCities();
    }


    public static void runGitHub(Tour tour)
    {
        Tour
    }
}
