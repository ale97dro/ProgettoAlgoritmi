package application;

import java.util.ArrayList;
import java.util.List;

public class NearestNeighbor {

    public static void run(List<City> cities, int[][] distances)
    {
        List<Integer> orderedCity = new ArrayList<>();

        int min = Integer.MAX_VALUE;
        int column = 0;
        int neigh = -1; //numero del nodo più vicino
        int start = 0;
        int cost = 0;

        orderedCity.add(0);

        for(int k = 0; k < distances.length; k++)
        {
            for (int i = 0; i < distances.length; i++)
            {
                if (min > distances[start][column] && distances[start][column] != 0 && !orderedCity.contains(column))
                {
                    min = distances[start][column];
                    neigh = column;
                }
                column++;
            }

            if(min != Integer.MAX_VALUE)
                cost+=min;
            start = neigh;
            orderedCity.add(neigh);
           // System.out.println(min);
            column = 0;
            neigh = -1;
            min = Integer.MAX_VALUE;
        }
        System.out.println("Cost "+cost);
    }
}
