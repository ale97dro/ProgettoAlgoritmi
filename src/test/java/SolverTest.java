import aco.ACO;
import application.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {

    @Test(timeout = 181000)
    public void ch130()
    {
        long startTime = System.currentTimeMillis();

        String fileName = "ch130";
        String path = "problems/ch130.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 150, 10);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile(fileName + ".opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("CH130");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void d198() {
        long startTime = System.currentTimeMillis();

        String fileName = "d198";
        String path = "problems/d198.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 350, 10);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile(fileName + ".opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("D198");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void eil76() {
        long startTime = System.currentTimeMillis();

        String fileName = "eil76";
        String path = "problems/eil76.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 150, 10);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile(fileName + ".opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("EIL76");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void fl1577() {
        long startTime = System.currentTimeMillis();

        String fileName = "fl1577";
        String path = "problems/fl1577.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 44, 3);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile(fileName + ".opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("FL1577");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void kroA100() {
        long startTime = System.currentTimeMillis();

        String fileName = "kroA100";
        String path = "problems/kroA100.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 150, 10);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile(fileName + ".opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("KROA100");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void lin318() {
        long startTime = System.currentTimeMillis();

        String fileName = "lin318";
        String path = "problems/lin318.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 350, 10);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile(fileName + ".opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("LIN318");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void pcb442() {
        long startTime = System.currentTimeMillis();

        String fileName = "pcb442";
        String path = "problems/pcb442.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 350, 10);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile(fileName + ".opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("PCB442");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void pr439() {
        long startTime = System.currentTimeMillis();

        String fileName = "pr439";
        String path = "problems/pr439.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 350, 10);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile(fileName + ".opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("PR439");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void rat783() {
        long startTime = System.currentTimeMillis();

        String fileName = "rat783";
        String path = "problems/rat783.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 67, 10);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile(fileName + ".opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("RAT783");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void u1060() {
        long startTime = System.currentTimeMillis();

        String fileName = "u1060";
        String path = "problems/u1060.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 23, 10);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile(fileName + ".opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("U1060");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }
}

