package application;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import java.util.List;

public class Tour
{
    private List<City> cities;
    private int[][] distanceMatrix;

    private List<Integer> orderedCity;
    private int tourCost;


    public Tour(List<City> cities)
    {
        this.cities = cities;
        distanceMatrix = new int[cities.size()][cities.size()];
        tourCost = 0;
    }

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

    public void setOrderedCity(List<Integer> orderedCity)
    {
        this.orderedCity = orderedCity;
    }

    public List<Integer> getOrderedCity() { return this.orderedCity; }

    public void setTourCost(int cost)
    {
        this.tourCost = cost;
    }

    public int getTourCost()
    {
        return tourCost;
    }

    public int computeTourCost()
    {
        tourCost = 0;

        for(int i = 0; i < orderedCity.size()-2;i++)
            tourCost += distanceMatrix[orderedCity.get(i)][orderedCity.get(i+1)];

        tourCost += distanceMatrix[orderedCity.get(0)][orderedCity.get(orderedCity.size()-2)];

        return tourCost;
    }

    private Path getPath()
    {
        Path path = new Path();
        path.getElements().add(new MoveTo(cities.get(orderedCity.get(0)).getX(), cities.get(orderedCity.get(0)).getY()));
        for(int i = 1;i<orderedCity.size()-1;i++){ //se tolgo il -1 dalla lista, devo fare i<orderedCity.size()
            path.getElements().add(new LineTo(cities.get(orderedCity.get(i)).getX(), cities.get(orderedCity.get(i)).getY()));
        }

        path.getElements().add(new LineTo(cities.get(orderedCity.get(0)).getX(), cities.get(orderedCity.get(0)).getY()));

        return path;
    }

    public Node getVisualTour()
    {
        Group tourVisual = new Group();
        tourVisual.getChildren().add(getPath());

        //add points
        tourVisual.getChildren().add(new Circle((float) cities.get(orderedCity.get(0)).getX(), (float) cities.get(orderedCity.get(0)).getY(), 5));
        for (int i = 1; i < orderedCity.size()-1; i++) { //se tolgo il -1 dalla lista, devo fare i<orderedCity.size()
            //se voglio cambiare il colore, faccio new Circle qui dentro e lo aggiungo
            tourVisual.getChildren().add(new Circle((float) cities.get(orderedCity.get(i)).getX(), (float) cities.get(orderedCity.get(i)).getY(), 3));
        }
        return tourVisual;
    }

}
