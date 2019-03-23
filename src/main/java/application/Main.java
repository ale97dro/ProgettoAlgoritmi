package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

//dist = (int)(dist+0,5) //distanza come intero

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
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

    public static double calcoloErrore(int tourCost, int bestKnown)
    {
        return ((tourCost - bestKnown) / (double)bestKnown) * 100;
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //String path = "D:\\alex2\\Desktop\\ALGO_cup_2019_problems\\fl1577.tsp";
       // String path = "D:\\alex2\\Desktop\\Algortmi\\ALGO_cup_2019_problems\\ch130.tsp";

        String path = "D:\\alex2\\Desktop\\Algortmi\\ALGO_cup_2019_problems\\lin318.tsp";
       // List<City> cities;

        Tour tour = new CityFileReader().read(path);


        //= new Tour(cities);
        tour.calcuateDistances(new EuclideanDistance());

        int[][] distances = tour.getDistances();

        //  printMatrix(distances);


        tour.setTour(NearestNeighbor.run(tour.getCities(), distances));

        System.out.println("\nNN error: " + calcoloErrore(tour.computeTourCost(), tour.getBestKnown()));

        Tour old = tour;
        tour = _2opt._2opt(tour);
        System.out.println("2opt error: " + calcoloErrore(tour.computeTourCost(), tour.getBestKnown()));

        System.out.println("2opt");

        for(int i: tour.getTour())
            System.out.print(i + " -> ");

       // tour = _2opt.run(tour);




        Group root = new Group();
        primaryStage.setTitle("OK");
        primaryStage.setScene(new Scene(root, 1000, 800));

        root.getChildren().add(tour.getVisualTour());


        System.out.println("\nCosto tour calcolato: " + tour.computeTourCost());

        primaryStage.show();
    }
}
