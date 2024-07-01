import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String carId;
    private String Brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        Brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return Brand;
    }

    public String getModel() {
        return model;
    }

    public double getBasePricePerDay() {
        return basePricePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;

    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }
}

class Customer {
    private String Name;
    private String Id;

    public Customer(String name, String id) {
        Name = name;
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public String getCustomerId() {
        return Id;
    }
}

class Rental {
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;

    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("Car is not available for rent..");
        }
    }

    public void returnCar(Car car) {
        Rental rentalToRemove = null;
        for (Rental rental : rentals
        ) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
            if (rentalToRemove != null) {
                car.rent();
                rentals.remove(rentalToRemove);
                System.out.println("Car returned successfully");
            } else {
                System.out.println("Car was not rented");
            }

        }

    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.hasNextLine();  // for consume one line
            if (choice == 1) {
                System.out.println("\n== Rent a Car ==\n");
                System.out.print("Enter your name: ");

                String customerName = sc.next();


                System.out.println("\n Available Cars:");
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
                    }

                }

                System.out.println("Enter the car Id you want to rent: ");
                String carId = sc.next();
                System.out.println("Enter the number of days for rental ");
                int rentalDays = sc.nextInt();
//                sc.nextLine();

                Customer newCustomer = new Customer(customerName, "CUS" + (customers.size() + 1));
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Car car : cars
                ) {
                    if (car.getCarId().equals(carId)) {
                        selectedCar = car;
                        break;
                    }
                }
                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n == Rental Information == \n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price : $%.2f%n", totalPrice);

                    System.out.println("\nConfirm rental (Y/N): ");
                    String confirm = sc.next();
                    if (confirm.equalsIgnoreCase("y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar rented successfully");
                    } else {
                        System.out.println("\n Rental cancelled");
                    }

                } else {
                    System.out.println("\n Invalid car selected or car is not available for rent...");
                }
            } else if (choice == 2) {
                System.out.println("\n== Return a Car ==\n");
                System.out.print("Enter the car ID you want to return: ");
                String carId = sc.next();

                Car carToReturn = null;
                for (Car car : cars
                ) {
                    if (car.getCarId().equals(carId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }
                if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals
                    ) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car return successfully by " + customer.getName());
                    } else {
                        System.out.println("Car was rented of rental information is missing");
                    }
                } else {
                    System.out.println("invalid car Id or car is not rented...");
                }

            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option...");
            }
        }
        System.out.println("\nThank you for using the Car Rental System!");
    }
}

public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("C001", "Toyota", "Camry", 60.0); // Different base price per day for each car
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);

        rentalSystem.menu();
    }
}