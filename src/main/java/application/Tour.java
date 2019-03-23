package application;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.ArrayList;
import java.util.List;

public class Tour
{
    private List<City> cities; //elenco di tutte le città del problema
    private int[][] distanceMatrix;

    private List<Integer> tour; //città ordinate da un qualche algoritmo
    private int tourCost;

    private int bestKnown;


    public Tour(List<City> cities)
    {
        this.cities = cities;
        distanceMatrix = new int[cities.size()][cities.size()];
        tourCost = 0;
    }

    public Tour(Tour tour)
    {

    }

    public Tour()
    {
        cities = new ArrayList<>();
        distanceMatrix = new int[10][10];
        tour = new ArrayList<>();
        tourCost = 0;
    }

    public Tour(int[][] distanceMatrix)
    {
        cities = new ArrayList<>();
        this.distanceMatrix = distanceMatrix;
        tour = new ArrayList<>();
        tourCost = 0;
    }

    /**
     * Calcola distanza tra le città
     * @param calculator
     */
    public void calcuateDistances(DistanceCalculator calculator)
    {
        for(int r = 0; r < distanceMatrix.length; r++)
        {
            for(int c = 0; c < distanceMatrix[0].length; c++)
            {
                distanceMatrix[r][c] = calculator.calculateDistance(cities.get(r), cities.get(c));
            }
        }
    }

    public int[][] getDistances()
    {
        return distanceMatrix;
    }

    //TOUR: serie di città ordinate

    public void setTour(List<Integer> tour)
    {
        this.tour = tour;
    }

    public List<Integer> getTour() { return this.tour; }

    public void setTourCost(int cost)
    {
        this.tourCost = cost;
    }

    public int getTourCost()
    {
        return tourCost;
    }

    public int getTourCity(int pos)
    {
        return tour.get(pos);
    }

    public void setTourCity(int pos, int city)
    {
        try {
            tour.set(pos, city);
        }
        catch (Exception ex)
        {
            tour.add(city);
        }
    }

    public void addTourCity(int city)
    {
        tour.add(city);
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     * Calcola il costo del tour
     * @return
     */
    public int computeTourCost()
    {
        tourCost = 0;

        try {
           // System.out.println("Tour size : " + tour.size());
            for (int i = 0; i < tour.size() - 2; i++)
                tourCost += distanceMatrix[tour.get(i)][tour.get(i + 1)];

            tourCost += distanceMatrix[tour.get(0)][tour.get(tour.size() - 2)];
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //TODO: errore
        }
        return tourCost;
    }

    public int computeTourCostWithout1()
    {
        tourCost = 0;

        try {
            // System.out.println("Tour size : " + tour.size());
            for (int i = 0; i < tour.size() - 1; i++)
                tourCost += distanceMatrix[tour.get(i)][tour.get(i + 1)];

            tourCost += distanceMatrix[tour.get(0)][tour.get(tour.size() - 1)];
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //TODO: errore
        }
        return tourCost;
    }

    public int getBestKnown() {
        return bestKnown;
    }

    public void setBestKnown(int bestKnown) {
        this.bestKnown = bestKnown;
    }

    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public void setDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public int distanceBetweenCities(int a, int b)
    {
        return distanceMatrix[a][b];

//        try {
//            return distanceMatrix[a - 1][b - 1];
//        }
//        catch (Exception ex)
//        {
//            return 0;
//        }
    }

    //Per disegnare
    private Path getPath()
    {
        Path path = new Path();
        path.getElements().add(new MoveTo(cities.get(tour.get(0)).getX(), cities.get(tour.get(0)).getY()));
        for(int i = 1; i< tour.size()-1; i++){ //se tolgo il -1 dalla lista, devo fare i<tour.size()
            path.getElements().add(new LineTo(cities.get(tour.get(i)).getX(), cities.get(tour.get(i)).getY()));
        }

        path.getElements().add(new LineTo(cities.get(tour.get(0)).getX(), cities.get(tour.get(0)).getY()));

        return path;
    }

    public Node getVisualTour()
    {
        Group tourVisual = new Group();
        tourVisual.getChildren().add(getPath());

        //add points
        tourVisual.getChildren().add(new Circle((float) cities.get(tour.get(0)).getX(), (float) cities.get(tour.get(0)).getY(), 5));
        for (int i = 1; i < tour.size()-1; i++) { //se tolgo il -1 dalla lista, devo fare i<tour.size()
            //se voglio cambiare il colore, faccio new Circle qui dentro e lo aggiungo
            tourVisual.getChildren().add(new Circle((float) cities.get(tour.get(i)).getX(), (float) cities.get(tour.get(i)).getY(), 3));
        }
        return tourVisual;
    }

}
