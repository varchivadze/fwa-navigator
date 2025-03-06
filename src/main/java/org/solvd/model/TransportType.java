package org.solvd.model;

public enum TransportType {
    CAR,
    TRANSPORT,
    PEDESTRIAN;

    public static TransportType getTransportType(String number) {
        switch (number) {
            case "1":
                return CAR;
            case "2":
                return TRANSPORT;
            case "3":
                return PEDESTRIAN;
        }
        return null;
    }
}


