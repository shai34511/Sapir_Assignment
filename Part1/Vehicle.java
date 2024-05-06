package Part1;
import java.io.IOException;


abstract class Vehicle extends Thread {

    public void setWashing(boolean washing) {
        isWashing = washing;
    }

    public void setWaiting(boolean waiting) {
        isWaiting = waiting;
    }

    boolean isDone = false, isWaiting = false, isWashing = false;

    public void setDone(boolean done) {
        isDone = done;
    }

    public static double waitingTime(double i) {
        double d = Math.random();
        while ((d == 0)) {
            d = Math.random();
        }
        return (((-1 * Math.log(d)) / i)) * 1000;
    }

    public void run() {
        while (!isDone) {
            try {
                if (!isWaiting && !isWashing) {
                    Thread.sleep((long) waitingTime(VehicleWasher.arrival));        // the main run method
                    VehicleWasher.vw.add2WaitingList(this);
                }
                else if (isWaiting && !isWashing) {
                        VehicleWasher.vw.add2WashingList(this);
                        Thread.sleep((long) waitingTime(VehicleWasher.beingWashed));
                }
                else if (isWashing && isWaiting) {
                    VehicleWasher.vw.add2FinalList(this);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public abstract String getType();
}
