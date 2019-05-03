package application;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class CityTest
{
    @Test
    public void cityCreationTest()
    {
        City a = City.create(1, 0.0, 0.0);
        City b = City.create(2, 0.0, 0.0);
        City c = City.create(1, 0.0, 0.0);

        assertNotNull(a);
        assertNotNull(b);
        assertNotNull(c);

        assertNotEquals(a, b);
        assertEquals(a, c);
    }

    @Test
    public void nameTest()
    {
        City a = City.create(1, 0.0, 0.0);

        assertEquals(1, a.getName());
    }

    @Test
    public void xCoordinateTest()
    {
        City a = City.create(1, 0.0, 0.0);
        City b = City.create(2, 1.0, 7.0);

        assertEquals(0.0, a.getX(), 0.001);
        assertEquals(1.0, b.getX(), 0.001);
    }

    @Test
    public void yCoordinateTest()
    {
        City a = City.create(1, 0.0, 0.0);
        City b = City.create(2, 1.0, 7.0);

        assertEquals(0.0, a.getY(), 0.001);
        assertEquals(7.0, b.getY(), 0.001);
    }
}
