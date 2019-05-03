package application;

public class EuclideanDistance implements DistanceCalculator
{
    @Override
    public int calculateDistance(City a, City b)
    {
        //dist = (int)(dist+0,5) //distanza come intero
        double x_square_distance = Math.pow(a.getX() - b.getX(), 2);
        double y_square_distance = Math.pow(a.getY() - b.getY(), 2);
        double distance = Math.sqrt(x_square_distance + y_square_distance);

        return ((int)(distance + 0.5));
    }
}
