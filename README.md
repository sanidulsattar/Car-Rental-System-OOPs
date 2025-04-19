# 🚗 Car Rental System (Java OOP Implementation)

A console-based car rental application demonstrating core Object-Oriented Programming principles in Java.

## 🌟 Features
- **Full OOP Implementation**: Encapsulation, Inheritance (expandable), Polymorphism, Abstraction
- **CRUD Operations**: Rent, return, and view available cars
- **Interactive Menu**: User-friendly console interface
- **Price Calculation**: Dynamic pricing based on rental duration

## 🛠️ Tech Stack
- **Core Java** (JDK 17+)
- **OOP Concepts**:
  - Classes & Objects (`Car`, `Customer`, `Rental`)
  - Encapsulation (Private fields + Getters)
  - Composition (`CarRentalSystem` aggregates other classes)
- **Collections Framework**: `ArrayList` for data storage

## 📦 Project Structure
```plaintext
src/
├── Car.java            # Car entity with rental logic
├── Customer.java       # Customer information
├── Rental.java         # Rental transaction record
├── CarRentalSystem.java # Main system logic
└── Main.java           # Entry point with sample data
