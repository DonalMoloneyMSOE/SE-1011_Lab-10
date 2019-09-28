/*
 * SE1011 - 011
 * Winter 2015
 * Lab 8 - Parking Lot
 * Name: Donal Moloney
 * Created: 2/7/2017
 */
package Moloneyda;

import java.text.DecimalFormat;
import java.text.NumberFormat;

//this public class creates a parking lot
public class ParkingLot {
    public static double CLOSED_THRESHOLD = 80.0;
    private int capacity;
    private double capacityBeforeClosed;
    private String color;
    public boolean isClosed;
    private int lastVehicleEntryTime;
    private int lastVehicleExitTime;
    private int vehiclesInLot;
    private int timeClosed;

    //this is the main method that takes strings as arguments
    public static void main(String[] args) {
    }

    /*
    this public constructor method uses this to talk about members o the object parking lot, string and capacity
    are parameters
     */

    public ParkingLot(String color, int capacity) {
        this.color = color;
        this.capacity = capacity;
        this.capacityBeforeClosed = (CLOSED_THRESHOLD / 100) * capacity;
    }

    //this public constructor method uses this to talk about members of  object parking lot, int capacity is parameter
    public ParkingLot(int capacity) {
        this("test", capacity);
    }

    //this public method obtains the string color
    public String getColor() {
        return this.color;
    }

    /*
    this public method records when a vehicle enters the lot and calculates how long the lot was closed
    int time is a parameter
     */
    public void markVehicleEntry(int time) {
        if (time >= lastVehicleEntryTime) {
            if (isClosed()) {
                timeClosed = timeClosed + (time - lastVehicleEntryTime);
                lastVehicleEntryTime = time;
            } else {
                lastVehicleEntryTime = time;
            }
            vehiclesInLot++;
        }
    }

    //this public method records when a vehicle exits and calculates how long the lot was closed
    public void markVehicleExit(int time) {
        if ((lastVehicleExitTime <= time) && (time >= lastVehicleEntryTime)) {
            if (isClosed()) {
                if (lastVehicleExitTime > lastVehicleEntryTime) {
                    timeClosed = timeClosed + (time - lastVehicleExitTime);
                    lastVehicleExitTime = time;
                } else {
                    lastVehicleExitTime = time;
                    timeClosed = timeClosed + (lastVehicleExitTime - lastVehicleEntryTime);
                }
            } else {
                lastVehicleExitTime = time;
            }
            vehiclesInLot--;
        }
    }


    //  Method returns the number of vehicles in the lot
    public int vehiclesInLot() {
        return this.vehiclesInLot;
    }

    /*
    this public method checks if the lot checks to see if the current vehicles in lot cause the lot to be close
    it returns a true or false
     */

    public boolean isClosed() {
        //if ((((double) vehiclesInLot) / ((double) capacity) * 100) >= (CLOSED_THRESHOLD)) {
        //  this.isClosed = true;
        //} else
        //  this.isClosed = false;
        //return isClosed;
        //}
        if (vehiclesInLot >= (capacity * (CLOSED_THRESHOLD / 100))) {
            this.isClosed = true;
        } else {
            this.isClosed = false;

        }
        return isClosed;
    }

    //this public method returns the time the lot was closed
    public int closedMinutes() {
        return timeClosed;
    }

    //this public method display if the lot is closed or what capacity until closed the lot is at
    public void displayStatus() {
        double cars = ((double) vehiclesInLot / (double) capacity) * 100;
        if (vehiclesInLot < capacityBeforeClosed) {
            System.out.printf("\nParking lot %s: %.2f percent", color, cars);
        } else {
            System.out.println("\nParking lot " + color + ": CLOSED");
        }
    }

    public String toString() {
        NumberFormat percentFormat = new DecimalFormat("#.#");
        if (vehiclesInLot >= (capacity * (CLOSED_THRESHOLD / 100))) {
            return (("Status for " + color + " " + "parking lot:" + " "
                    + vehiclesInLot + " " + "vehicles (CLOSED)"));
        } else {
            return (("Status for " + color + " " + "parking lot: " +
                    + vehiclesInLot + " " + "vehicles " + "(" +
                    percentFormat.format((((double) vehiclesInLot) / ((double) capacity))*100)+"%)" ));
        }
    }
}
