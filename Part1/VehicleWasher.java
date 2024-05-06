package Part1;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class VehicleWasher {


    private Part1.VehicleArray<Part1.Car> cars = new Part1.VehicleArray<Part1.Car>();
    private Part1.VehicleArray<Part1.SUV> SUVs = new Part1.VehicleArray<Part1.SUV>();
    private Part1.VehicleArray<Part1.Truck> trucks = new Part1.VehicleArray<Part1.Truck>();
    private Part1.VehicleArray<Part1.MiniBus> miniBus = new Part1.VehicleArray<Part1.MiniBus>();
    private Part1.VehicleArray<Part1.Vehicle> waitingList = new Part1.VehicleArray<Part1.Vehicle>();
    private Part1.VehicleArray<Part1.Vehicle> washingList = new Part1.VehicleArray<Part1.Vehicle>();  // field initialization

    @Override
    public String toString() {
        return "waiting " + waitingList + "\n";
    }

    public synchronized void add2WaitingList(Part1.Vehicle v) throws InterruptedException, IOException {   // the method that adds the vehicles into the wating list
        s = "";
        while (!availableWait) {
            wait();
        }
        availableWait = false;
        waitingList.add2End(v);
        aid = TimeCalculate.time.castDouble() - aid2;
        System.out.println(aid + "sec");
        System.out.println(v.getType() + " " + v.getName() + " Has entered the waiting list ");
        s += v.getType() + " " + v.getName() + " Has entered the waiting list " + aid + "sec" + "\n";
        Part1.VehicleLogger.write2File(s);

        v.setWaiting(true);
        availableWait = true;
        notifyAll();
    }


    public synchronized void add2WashingList(Part1.Vehicle v) throws InterruptedException, IOException {  // as long as one of the lists arent being used, moving the next vehicle form the wating list into the washing list
        s = "";
        if (m > 0) {
            while (!availableWash) {
                wait();
            }
            synchronized (v) {                  // avilability booleans
                availableWash = false;
            }
            washingList.add2End(waitingList.removeFirst(v));
            aid = TimeCalculate.time.castDouble() - aid2;
            System.out.println(aid +"sec");
            System.out.println(v.getType() + " " + v.getName() + " entered the wash list ");
            s += v.getType() + " " + v.getName() + " " + v.getName() + " " + aid + "sec" +"\n";
            Part1.VehicleLogger.write2File(s);
            m--;
            v.setWashing(true);         // changes when a thread is finished its proccess of moving betweent he arrays
            availableWash = true;
            notifyAll();             // notifying those that are waiting
        }
    }


    public synchronized void add2FinalList(Part1.Vehicle v) throws InterruptedException, IOException {  // same as the last function, but this time only moving each unique vehicle into his own type array
        s = "";
        while (!availableFinal) {
            wait();
        }
        synchronized (v) {
            availableFinal = false;    // avilability boolean
        }
        if (v instanceof Part1.Car) {
            cars.add2End((Part1.Car) v);
            if (cars.getSize() == countCar) {
                carTime = TimeCalculate.time.castDouble()-aid2;
            }
        } else if (v instanceof Part1.SUV) {
            SUVs.add2End((Part1.SUV) v);
            if (SUVs.getSize() == countSUV) {
                SUVTime = TimeCalculate.time.castDouble()-aid2;      // adding each instance to an array of his own type and calculating his operating time
            }
        } else if (v instanceof Part1.Truck) {
            trucks.add2End((Part1.Truck) v);
            if (trucks.getSize() == countTruck) {
                truckTime = TimeCalculate.time.castDouble()-aid2;
            }
        } else if (v instanceof Part1.MiniBus) {
            miniBus.add2End((Part1.MiniBus) v);
            if (miniBus.getSize() == countMiniBus) {
                minBusTime = TimeCalculate.time.castDouble()-aid2;
            }
        }
        washingList.deleteByParameter(v);
        m++;
        aid = TimeCalculate.time.castDouble() - aid2;
        System.out.println(aid + "sec");
        System.out.println(v.getType() + " " + v.getName() + " entered the final list ");
        s += v.getType() + " " + v.getName() + " entered the final list " + "sec"+aid + "\n";
        Part1.VehicleLogger.write2File(s);
        v.setDone(true);
        availableFinal = true;   // the avilability boolean is flipped once the thread is finished its proccess
        notifyAll();
        if ((cars.getSize()+SUVs.getSize()+trucks.getSize()+miniBus.getSize()) == n) {  // a condition to print the average time when all of the vehicles are finished
            timeAvg(carTime, SUVTime, truckTime, minBusTime);
        }
    }
    public void timeAvg(double c, double s, double t, double m) {   // a function that calculate the average time of each vehicle type
        if (countCar != 0) {
            System.out.println("Cars washed: " +countCar+ " Cars Avg is: " + c / countCar);
        }else{
            System.out.println("there are no Cars");
        }
        if (countSUV != 0) {
            System.out.println("SUVs washed: " +countSUV+ " SUVs Avg is: " +s/countSUV);
        }else{
            System.out.println("there are no SUVs");
        }
        if (countTruck != 0) {
            System.out.println("Trucks washed: " +countTruck+ " Trucks Avg is: " + t/countTruck);
        }else{
            System.out.println("there are no Trucks");
        }
        if (countMiniBus != 0) {
            System.out.println("MiniBus washed: " +countMiniBus+ " miniBus Avg is: " + m/countMiniBus);
        }else{
            System.out.println("there are no miniBus");
        }




    }

    static VehicleWasher vw = new VehicleWasher();
    static int n = 0, m = 0, countCar = 0, countSUV = 0, countTruck = 0, countMiniBus = 0;
    static double aid2 = 0, aid = 0, minBusTime = 0, carTime = 0, truckTime = 0, SUVTime = 0;   // variable initialization
    static String s = "";

    static double arrival = 0, beingWashed = 0;
    static boolean availableWash = true, availableWait = true, availableFinal = true;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of cars");
        n = sc.nextInt();
        System.out.println("Enter number of car wash stations");
        m = sc.nextInt();
        System.out.println("enter arrival time");
        arrival = sc.nextDouble();
        System.out.println("enter washing time");
        beingWashed = sc.nextDouble();
        Vehicle[] arr = new Vehicle[n];

        for (int i = 0; i < n; i++) {     // instance creation base of a random number
            Random rnd = new Random();
            int option = rnd.nextInt(4) + 1;
            switch (option) {
                case 1:
                    arr[i] = new Car("Car" + i);
                    countCar++;
                    break;

                case 2:
                    arr[i] = new MiniBus("MiniBus" + i);
                    countMiniBus++;
                    break;

                case 3:
                    arr[i] = new Truck("Truck" + i);
                    countTruck++;
                    break;

                case 4:
                    arr[i] = new SUV("SUV" + i);
                    countSUV++;
                    break;
            }
        }
        for (int i = 0; i < n; i++) {
            arr[i].start();
        }
        aid2 = TimeCalculate.time.castDouble();
    }
}








