package application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CityFileReader implements CityReader
{
    @Override
    public List<City> read(String path)
    {
        List<City> cities = new ArrayList<>();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String row = null;

            for (int i = 0; i < 7; i++)
                reader.readLine();

            while ((row = reader.readLine()) != null) {
                if (!row.equals("EOF")) {
                    System.out.println(row);
                    String[] informations = row.split(" ");
                    cities.add(City.create(Integer.parseInt(informations[0]), Double.parseDouble(informations[1]), Double.parseDouble(informations[2])));
                }
            }

            return cities;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
