public class week01and02 {

    public static void main(String[] args) {

        ParkingLot lot = new ParkingLot(10);

        System.out.println(lot.parkVehicle("ABC123"));
        lot.exitVehicle("ABC123");
    }
}

class ParkingLot {

    String[] spots;

    ParkingLot(int size) {
        spots = new String[size];
    }

    int hash(String plate) {
        return Math.abs(plate.hashCode()) % spots.length;
    }

    int parkVehicle(String plate) {

        int index = hash(plate);

        while (spots[index] != null)
            index = (index + 1) % spots.length;

        spots[index] = plate;

        return index;
    }

    void exitVehicle(String plate) {

        int index = hash(plate);

        while (spots[index] != null) {

            if (spots[index].equals(plate)) {
                spots[index] = null;
                return;
            }

            index = (index + 1) % spots.length;
        }
    }
}