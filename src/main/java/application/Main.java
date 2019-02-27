package application;

import java.util.List;

//dist = (int)(dist+0,5) //distanza come intero

public class Main
{
    public static void main(String[] args)
    {
        //String path = "D:\\alex2\\Desktop\\ALGO_cup_2019_problems\\ch130.tsp";
        //String path = "D:\\alex2\\Desktop\\ALGO_cup_2019_problems\\fl1577.tsp";
        String path = "D:\\alex2\\Desktop\\ALGO_cup_2019_problems\\eil76.tsp";

        List<City> cities;

        cities = new CityFileReader().read(path);


        Tour tour = new Tour(cities);
        tour.calcuateDistances(new EuclideanDistance());

        int[][] distances = tour.getDistances();

      //  printMatrix(distances);


        NearestNeighbor.run(cities, distances);
    }

    public static void printMatrix(int[][] matrix)
    {
        for(int r = 0; r<matrix.length; r++)
        {
            for(int c = 0; c <matrix[0].length; c++)
            {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }
}
