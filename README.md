# 🎬 Cinema Booking System – Java

## 📌 Project Overview

This project is a simple Cinema Seat Booking System developed using **Java**.

It demonstrates how to use Java Collections, especially `HashMap` and `HashSet`, to manage cinema seat reservations, prevent double booking, handle broken seats, and display a visual seating chart.

The project also demonstrates the importance of overriding `equals()` and `hashCode()` when using custom objects as keys in a `HashMap` or elements in a `HashSet`.

## 🚀 Features

- **Book Seats:** Store seat reservations using seat positions as keys and customer names as values.
- **Find Bookings:** Retrieve the customer who booked a specific seat.
- **Check Seat Availability:** Check whether a seat is already booked.
- **Prevent Double Booking:** Use `putIfAbsent()` to avoid overwriting existing reservations.
- **Move Bookings:** Remove an existing reservation and assign it to another seat.
- **Manage Broken Seats:** Use a `HashSet` to store unavailable seats.
- **Count Reservations:** Display the total number of booked seats.
- **Display Seating Chart:** Print a visual representation of the cinema seating arrangement.

## 🛠️ Technologies Used

- Java
- HashMap
- HashSet
- Java Collections Framework
- Object-Oriented Programming (OOP)
