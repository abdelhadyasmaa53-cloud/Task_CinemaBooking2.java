import java.util.*;

class Task_CinemaBooking2 {

    static class Seat {
        final char row;
        final int number;

        Seat(char row, int number) {
            this.row = row;
            this.number = number;
        }

        @Override
        public String toString() {
            return "" + row + number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Seat)) return false;
            Seat s = (Seat) o;
            return row == s.row && number == s.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, number);
        }
    }

    public static void main(String[] args) {
        HashMap<Seat, String> bookings = new HashMap<>();
        HashSet<Seat> broken = new HashSet<>();

        bookings.put(new Seat('A', 1), "Ali");
        bookings.put(new Seat('A', 2), "Sara");
        bookings.put(new Seat('B', 5), "Omar");
        bookings.put(new Seat('C', 3), "Mona");
        System.out.println("Bookings:           " + bookings);

        String mona = bookings.get(new Seat('C', 3));
        System.out.println("Who booked C3:      " + mona);

        boolean isA2 = bookings.containsKey(new Seat('A', 2));
        boolean isE6 = bookings.containsKey(new Seat('E', 6));
        System.out.println("Is A2 taken:        " + isA2);
        System.out.println("Is E6 taken:        " + isE6);

        String res = bookings.putIfAbsent(new Seat('B', 5), "Youssef");
        if (res == null) {
            System.out.println("Youssef -> B5:      booked");
        } else {
            System.out.println("Youssef -> B5:      refused, taken by " + res);
        }

        String client = bookings.remove(new Seat('A', 1));
        bookings.put(new Seat('D', 2), client);
        System.out.println("After Ali moved:    " + bookings);

        broken.add(new Seat('E', 1));
        broken.add(new Seat('E', 2));
        Seat targetSeat = new Seat('E', 1);
        if (broken.contains(targetSeat)) {
            System.out.println("Hana -> E1:         refused, seat is broken");
        } else {
            bookings.put(targetSeat, "Hana");
            System.out.println("Hana -> E1:         booked");
        }

        int total = bookings.size();
        System.out.println("Seats booked:       " + total);

        System.out.println();
        printChart(bookings, broken);
    }

    static void printChart(HashMap<Seat, String> bookings, HashSet<Seat> broken) {
        System.out.println("     1  2  3  4  5  6");
        for (char row = 'A'; row <= 'E'; row++) {
            System.out.print(row + "  ");
            for (int number = 1; number <= 6; number++) {
                Seat seat = new Seat(row, number);
                if (bookings.containsKey(seat)) {
                    System.out.print("  X");
                } else if (broken.contains(seat)) {
                    System.out.print("  #");
                } else {
                    System.out.print("  .");
                }
            }
            System.out.println();
        }
    }
}

/*
 QUESTIONS - write your answers here

 Q1.Why did we have to override hashcode() and equals()?
 Because by default, Java compares objects by their memory address (==). Since we make a new Seat object every time (like new Seat('C', 3)), it has a different address from the one saved in the map. We need hashCode() so the map puts it in the right bucket, and equals() to check if row and number match.

 Q2. Delete your hashCode() but keep equals(), and run again.
     What happens? Why?
 It won't find any seats (get returns null, containsKey returns false, and the chart is empty).
 This happens because HashMap uses hashCode() first to find the bucket. Without overriding it, two seats with the same values get different hash codes, so the map searches the wrong bucket and never even calls equals().

 Q3. Why are row and number final? What could go wrong if the number
     of a booked seat could be changed after it was put in the map?
 So the seat cannot be changed after adding it to the map. If we change the number later, its hash code changes too, but it stays in the old bucket. If we try to find it later, HashMap will look in a different bucket and won't find it.

 Q4. Change equals(Object o) to equals(Seat o). What happens?
     Then also remove @Override and run again. What happens now?
 First, with @Override it gives a compile error because Object's equals takes an Object, not Seat (so this is overloading).
 When we remove @Override, it compiles fine but fails when running because HashMap internally calls equals(Object), so it just uses the default Object equals and ignores our method.

 BONUS. Replace the whole Seat class with this one line:
            record Seat(char row, int number) {}
        Run it. What happens, and why?
 It works normally and gives the exact same output.
 Because a record in Java automatically makes final fields, constructor, and generates equals() and hashCode() for us based on row and number.
*/
