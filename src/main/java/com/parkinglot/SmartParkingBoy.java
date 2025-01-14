package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends StandardParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }
    @Override
    public Ticket park(Car car) {
        List<ParkingLot> parkingLots = super.getParkingLots();

        if (parkingLots == null) {
            return null;
        }
        return parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getAvailablePositionCount))
                .get()
                .park(car);
    }

    @Override
    public Car getCar(Ticket ticket) {
        List<ParkingLot> parkingLots = super.getParkingLots();

        if (parkingLots == null) {
            return null;
        }
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.isContainCar(ticket))
                .findFirst()
                .orElseThrow(() -> new UnrecognizedParkingTicketException("Unrecognized Parking Ticket"))
                .getCar(ticket);
    }


}
