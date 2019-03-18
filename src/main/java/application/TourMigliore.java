package application;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TourMigliore {
    private Integer[] cities;

    public TourMigliore(List<Integer> cities)
    {
        this.cities = new Integer[cities.size()];

        for(int i=0; i < cities.size(); i++)
            this.cities[i] = cities.get(i);
    }

    public TourMigliore() { }

    public TourMigliore(int size)
    {
        cities = new Integer[size];
    }

    public void setCity(int pos, int city)
    {
        cities[pos] = city;
    }

    public int getCity(int pos)
    {
        return cities[pos];
    }

    public int getTourSize()
    {
        return cities.length;
    }

    public List<Integer> getAllCities()
    {
        return Arrays.asList(cities);
    }
}
