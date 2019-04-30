package application;

import aco.ACO;
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
        //String path = "D:\\alex2\\Desktop\\Algortmi\\ALGO_cup_2019_problems\\ch130.tsp";
        //String path = "C:\\Users\\alex2\\Desktop\\Repo Git\\ProgettoAlgoritmi\\problems\\ch130.tsp";
        //String path = "D:\\alex2\\Desktop\\RepoGit\\ProgettoAlgoritmi\\problems\\ch130.tsp";
       String path = "D:\\alex2\\Desktop\\RepoGit\\ProgettoAlgoritmi\\problems\\pcb442.tsp";
        //String path = "C:\\Users\\alex2\\Desktop\\Repo Git\\ProgettoAlgoritmi\\problems\\eil76.tsp";
        //String path = "D:\\alex2\\Desktop\\Algortmi\\ALGO_cup_2019_problems\\eil76.tsp";

        //String path = "D:\\alex2\\Desktop\\Algortmi\\ALGO_cup_2019_problems\\fl1577.tsp";
       // String path = "D:\\alex2\\Desktop\\Algortmi\\ALGO_cup_2019_problems\\lin318.tsp";
       // List<City> cities;

        Tour tour = new CityFileReader().read(path);


        //= new Tour(cities);
        tour.calcuateDistances(new EuclideanDistance());

        int[][] distances = tour.getDistances();

        //  printMatrix(distances);


        tour.setTour(NearestNeighbor.run(tour.getCities(), distances));



        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        System.out.println("\nNN error: " + calcoloErrore(tour.computeTourCost(), tour.getBestKnown()));
        int nnCost = tour.getTourCost();

        Tour old = tour;
        //tour = _2opt._2opt(tour);
//        System.out.println("2opt error: " + calcoloErrore(tour.computeTourCostWithout1(), tour.getBestKnown()));
        //System.out.println("2opt error: " + calcoloErrore(tour.computeTourCost(), tour.getBestKnown()));
        //System.out.println("2opt");

//        for(int i: tour.getTour())
//            System.out.print(i + " -> ");


        //ANT COLONY TEST

        ACO aco = new ACO();

        long startTime = System.currentTimeMillis();
        tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        long endTime = System.currentTimeMillis();

        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        Group root = new Group();
        primaryStage.setTitle("OK");
        primaryStage.setScene(new Scene(root, 1000, 800));

//        root.getChildren().add(tour.getVisualTour());
        root.getChildren().add(acoTour.betterDraw());
        //root.getChildren().add(tour.betterDraw());


        //System.out.println("\nCosto tour calcolato: " + tour.computeTourCost());

        primaryStage.show();
    }
}
