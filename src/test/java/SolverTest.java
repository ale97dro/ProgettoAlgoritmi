import aco.ACO;
import application.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {

    @Test(timeout = 181000)
    public void ch130()
    {
        long startTime = System.currentTimeMillis();

        String path = "D:\\alex2\\Desktop\\RepoGit\\ProgettoAlgoritmi\\problems\\ch130.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 150);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile("ch130.opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("CH130");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void d198() {
        long startTime = System.currentTimeMillis();

        String path = "D:\\alex2\\Desktop\\RepoGit\\ProgettoAlgoritmi\\problems\\d198.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 350);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile("ch130.opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("D198");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void eil76() {
        long startTime = System.currentTimeMillis();

        String path = "D:\\alex2\\Desktop\\RepoGit\\ProgettoAlgoritmi\\problems\\eil76.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 150);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile("ch130.opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("EIL76");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void fl1577() {
        long startTime = System.currentTimeMillis();

        String path = "D:\\alex2\\Desktop\\RepoGit\\ProgettoAlgoritmi\\problems\\fl1577.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 15);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile("ch130.opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("FL1577");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void kroA100() {
        long startTime = System.currentTimeMillis();

        String path = "D:\\alex2\\Desktop\\RepoGit\\ProgettoAlgoritmi\\problems\\kroA100.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 150);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile("ch130.opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("KROA100");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void lin318() {
        long startTime = System.currentTimeMillis();

        String path = "D:\\alex2\\Desktop\\RepoGit\\ProgettoAlgoritmi\\problems\\lin318.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 350);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile("ch130.opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("LIN318");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void pcb442() {
        long startTime = System.currentTimeMillis();

        String path = "D:\\alex2\\Desktop\\RepoGit\\ProgettoAlgoritmi\\problems\\PCB442.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 350);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile("ch130.opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("PCB442");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void pr439() {
        long startTime = System.currentTimeMillis();

        String path = "D:\\alex2\\Desktop\\RepoGit\\ProgettoAlgoritmi\\problems\\PR439.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 350);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile("ch130.opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("PR439");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void rat783() {
        long startTime = System.currentTimeMillis();

        String path = "D:\\alex2\\Desktop\\RepoGit\\ProgettoAlgoritmi\\problems\\RAT783.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 350);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile("ch130.opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("RAT783");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }

    @Test(timeout = 181000)
    public void u1060() {
        long startTime = System.currentTimeMillis();

        String path = "D:\\alex2\\Desktop\\RepoGit\\ProgettoAlgoritmi\\problems\\u1060.tsp";

        Tour tour = new CityFileReader().read(path);
        tour.calcuateDistances(new EuclideanDistance());

        tour.setTour(NearestNeighbor.run(tour.getCities(), tour.getDistances()));
        tour.getTour().remove(tour.getTour().size()-1);
        tour.addTourCity(tour.getTour().get(0));
        int nnCost = tour.computeTourCost();

        ACO aco = new ACO(0.1, 2, 0.9, 10_000_000, 0, 350);

        tour.calcuateDistances(new EuclideanDistance());
        tour.setTourCost(nnCost);

        Tour acoTour = aco.antColony(tour);
        acoTour.setCities(tour.getCities());

        new WriteFile().writeFile("ch130.opt.tour", acoTour);

        long endTime = System.currentTimeMillis();

        System.out.println("U1060");
        System.out.println("Tempo: " + (endTime-startTime));
        System.out.println("Costo: " + acoTour.computeTourCost());

        System.out.println("\n\n");
    }
}

