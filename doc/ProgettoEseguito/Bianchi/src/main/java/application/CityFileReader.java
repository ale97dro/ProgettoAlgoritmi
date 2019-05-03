package application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CityFileReader implements CityReader
{
    @Override
    public Tour read(String path)
    {
        List<City> cities = new ArrayList<>();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String row = null;

            for (int i = 0; i < 5; i++)
                reader.readLine();

            String bestKnownRaw = reader.readLine();

            int bestKnown = Integer.parseInt(bestKnownRaw.split(" ")[2]);

            reader.readLine();

            while ((row = reader.readLine()) != null) {
                if (!row.equals("EOF")) {
                   // System.out.println(row);
                    String[] informations = row.split(" ");
                    cities.add(City.create(Integer.parseInt(informations[informations.length - 3]), Double.parseDouble(informations[informations.length -2 ]), Double.parseDouble(informations[informations.length -1])));
                }
            }

            Tour tour = new Tour(cities);

            tour.setBestKnown(bestKnown);

            return tour;
           // return cities;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
