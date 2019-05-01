package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteFile
{
    public void writeFile(String fileName, Tour tour)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            writer.write("NAME : " + fileName + "\n");
            writer.write("COMMENT : Optimun tour for " + fileName + " (" + tour.computeTourCost() + ")\n");
            writer.write("TYPE : TOUR\n");
            writer.write("DIMENSION : " + tour.getCities().size() + "\n");
            writer.write("TOUR_SECTION\n");

            for(int i = 0; i < tour.getTour().size() - 1; i++)
                writer.write((tour.getTourCity(i) +1) + "\n");

            writer.write(-(tour.getTourCity(tour.getTour().size() - 1) + 1) + "\n");
            writer.write("EOF\n");

            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
