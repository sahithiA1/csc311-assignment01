package edu.farmingdale.csc311.fleet;

public class Main {

public static void main(String[] args) {
Fleet fleet = buildFleet();

System.out.println("=== " + fleet.getName() + " ===");
System.out.println();

printInventory(fleet);
printSoundCheck(fleet);
printReport(fleet);
printGuardRails(fleet);
}

private static Fleet buildFleet() {
Fleet fleet = new Fleet("Farmingdale Motor Pool");

fleet.add(new Car(
        "1HGCM82633A004352", "Honda", "Accord",
                2023, "Blue", 4, 2.0,
                FuelType.GASOLINE, 15.8, 4));

fleet.add(new Car(
               "5YJ3E1EA7PF123456", "Tesla", "Model 3",
               2024, "Red", 4, 0.0,
                FuelType.ELECTRIC, 75.0, 4));

fleet.add(new Car(
               "JTDKARFU2J3061234", "Toyota", "Prius",
              2020, "Silver", 4, 1.8,
              FuelType.HYBRID, 11.3, 5));

fleet.add(new Truck(
             "1FT8W3BT5MEC12345", "Ford", "F-350",
             2021, "White", 6, 6.7,
                FuelType.DIESEL, 40.0, 3500.0));

fleet.add(new Truck(
             "3C6UR5DL9JG123456", "Ram", "2500",
               2019, "Black", 4, 6.4,
              FuelType.GASOLINE, 31.0, 1800.0));

return fleet;
}

private static void printInventory(Fleet fleet) {
System.out.println("Inventory:");

for (Vehicle vehicle : fleet.sortedByYear()) {
System.out.println(vehicle);
}

System.out.println();
}

private static void printSoundCheck(Fleet fleet) {
System.out.println("Sound check:");

for (Honkable honkable : fleet.sortedByYear()) {
honkable.honk();
}

Vehicle accord = fleet.findByVin("1HGCM82633A004352");
accord.honk(3);

System.out.println();
}

private static void printReport(Fleet fleet) {
System.out.println("Report:");

System.out.printf("%-20s: %d%n", "Vehicle count", fleet.size());

System.out.printf("%-20s: %.1f L%n", "Average engine size", fleet.averageEngineSize());

Vehicle longest = fleet.longestRange();

System.out.printf("%-20s: %d %s %s (%.1f mi)%n", "Longest range", longest.getYear(), longest.getMake(), longest.getModel(), longest.rangeInMiles());

for (FuelType fuel : FuelType.values()) {
System.out.printf("%-9s: %d%n", fuel.getLabel(), fleet.countWithFuelType(fuel));
}

System.out.println();
}

private static void printGuardRails(Fleet fleet) {
System.out.println("Guard rails:");

Vehicle accord = fleet.findByVin("1HGCM82633A004352");

System.out.printf("%-23s: %s%n",
            "Add Accord again", fleet.add(accord));

System.out.printf("%-23s: %s%n",
         "Remove Prius",
                fleet.removeByVin("JTDKARFU2J3061234"));

System.out.printf("%-23s: %s%n",
             "Size afterwards", fleet.size());

try {
new Car(
                    "1ABCDEF2345678901",
                   "Test",
                 "Electric",
                  2024,
                   "Blue",
                  4,
                    2.0,
FuelType.ELECTRIC,
75.0, 4);
} catch (IllegalArgumentException e) {
System.out.println("Caught: " + e.getMessage());
}

try {
FuelType.fromLabel("Steam");
} catch (IllegalArgumentException e) {
System.out.println("Caught: " + e.getMessage());
}

try {
accord.honk(0);
} catch (IllegalArgumentException e) {
System.out.println("Caught: " + e.getMessage());
}
}
}
