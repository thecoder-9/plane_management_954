//References
//Researching about multidimensional arrays - https://www.geeksforgeeks.org/multidimensional-arrays-in-java/
//Breaking out of 2 nested for loops - https://www.w3docs.com/snippets/java/how-do-i-break-out-of-nested-loops-in-java.html
//Removing an element from an array - https://www.w3resource.com/java-exercises/array/java-array-exercise-7.php
//Linear Search to find index - https://www.tutorialspoint.com/find-the-index-of-an-array-element-in-java
//To validate the name and the surname of the user - https://www.geeksforgeeks.org/how-to-validate-a-username-using-regular-expressions-in-java/
//To validate email address - https://www.tutorialspoint.com/checking-for-valid-email-address-using-regular-expressions-in-java
//Lectures and notes



import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaneManagement {
    //Main method
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Using multidimensional arrays to create rows and columns of the plane
        int [] [] seats = new int [4] [];
        seats[0] = new int [14];
        seats[1] = new int [12];
        seats[2] = new int [12];
        seats[3] = new int [14];

        //Array of tickets sold created using Ticket class
        Ticket[] soldTickets = new Ticket[100];
        int ticketIndex = 0;
        System.out.println("\n     Welcome to Plane Management application");

        //Creating an outer loop to break out of the loops within and the while loop
        outerLoop:
        while (true) {
            try{
                System.out.println("\n\n*************************************************");
                System.out.println("*                 MENU  OPTIONS                 *");
                System.out.println("*************************************************");
                System.out.println("\n     1) Buy a seat");
                System.out.println("     2) Cancel a seat");
                System.out.println("     3) Find first available seat");
                System.out.println("     4) Show seating plan");
                System.out.println("     5) Print tickets information and total sales");
                System.out.println("     6) Search ticket");
                System.out.println("     0) Quit");
                System.out.println("\n*************************************************");
                System.out.print("\nPlease select an option: ");
                int userOp = scan.nextInt();

                //calling methods according to the option selected
                switch (userOp) {
                    case 0:
                        //quit option
                        System.out.println("\nThank you for using our Plane Management application. We hope that you will continue to use our service.");
                        break outerLoop;
                    case 1:
                        buy_seat(seats, soldTickets, ticketIndex);
                        ticketIndex++;
                        break;
                    case 2:
                        cancel_seat(seats, soldTickets);
                        ticketIndex--;
                        break;
                    case 3:
                        find_first_available(seats);
                        break;
                    case 4:
                        show_seating_plan(seats);
                        break;
                    case 5:
                        print_tickets_info(soldTickets);
                        break;
                    case 6:
                        search_ticket(seats, soldTickets);
                        break;
                    default:
                        System.out.println("\nInvalid option. Please read the options given and try again.");
                }
            }catch (InputMismatchException e){
                System.out.println("\nInvalid input. Please read the options given and enter one of the numbers(0,1,2,3,4,5,6). Thank you.");
                scan.nextLine();  //consume the invalid input
            }

        }
    }

    //Method to return the corresponding row letter for the index
    public static String numToLetter(int row) {
        switch (row){
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
        }
        return null;
    }

    //Method to check if the seat number entered is within the specific row length and if the seat is available or unavailable
    public static String validSeat(int[] []seats,int row, int colVal) {
        if (colVal>0 && colVal<=seats[row].length) {
            if (seats[row][colVal-1]==0){
                return "Valid and Available";
            }else if(seats[row][colVal-1]==1){
                return "Valid but Unavailable";
            }
        }
        return "Invalid";

    }

    //Method to check if the user's input is an alphabetic letter and if it is returning the index
    public static int rowSelection(String rowVal) {
        if(rowVal.matches("[A-Z]")){
            switch (rowVal) {
                case "A":
                    return 0;
                case "B":
                    return 1;
                case "C":
                    return 2;
                case "D":
                    return 3;
                default:
                    return -1;
            }
        }else {
            throw new InputMismatchException();
        }
    }

    //Method to calculate the seat price according to the column values
    public static int seatPriceCal(int colVal) {
        if (colVal>0 && colVal<=14){
            if (colVal<=5){
                return 200;
            } else if (colVal<=9) {
                return 150;
            }
            return 180;
        }
        return 0;
    }

    //To find the index of a specific ticket
    public static int findTicketIndex(Ticket[] tickets, String rowLetter, int seatNum) {
        //searching for the index
        for (int index = 0; index< tickets.length; index++){
            Ticket checkTicket = tickets[index];
            if (checkTicket!=null){
                if (checkTicket.getRow().equals(rowLetter) && checkTicket.getSeat()==seatNum){
                    return index;
                }
            }
        }
        return -1;
    }


    //called when user selects 1
    public static void buy_seat(int [] [] seats, Ticket[] tickets, int ticketIndex){
        Scanner scan = new Scanner(System.in);
        String name = "";
        String sName = "";
        String email = "";
        boolean flag = false;

        //Collecting information of the user
        System.out.println("\n---------------------------------------------------");
        System.out.println("\n Please enter the passenger details below.");
        while (true){
            System.out.print("\nEnter your name: ");
            name = scan.next();
            if (name.matches("[a-zA-Z]+")) {
                System.out.print("\nEnter your surname: ");
                sName = scan.next();
                if (sName.matches("[a-zA-Z]+")) {
                    System.out.print("\nEnter your email address: ");
                    email = scan.next();
                    if (email.matches("[a-z0-9+_.-]+@[a-z0-9.-]+\\.[a-z]{2,}")) {
                        break;
                    } else {
                        System.out.println("\nEnter a valid email. Thank you.");
                    }
                } else {
                    System.out.println("\nEnter a valid surname. Thank you.");
                }
            } else {
                System.out.println("\nEnter a valid name. Thank you.");
            }

        }

        //Creating the object person
        Person person = new Person(name, sName, email);

        //Looping until a valid seat which is available is entered creating a ticket object using the Ticket class
        while(!flag) {
            try{
                System.out.print("\n\nEnter the row letter(A, B, C, D): ");
                int rowVal = rowSelection(scan.next().toUpperCase());
                if(rowVal >=0 && rowVal<4){
                    String rowLetter = numToLetter(rowVal);
                    System.out.print("Enter the seat number: ");
                    int seatNum = scan.nextInt();
                    String validity = validSeat(seats, rowVal, seatNum);
                    switch (validity){
                        case "Valid and Available":
                            System.out.println("\nThe seat "+ rowLetter + seatNum + " is valid and has been booked successfully. Thank you.");
                            seats[rowVal][seatNum-1] = 1; //recording the seat as sold
                            int seatPrice = seatPriceCal(seatNum);
                            Ticket soldTicket = new Ticket(rowLetter, seatNum, seatPrice, person);
                            tickets[ticketIndex] = soldTicket;
                            soldTicket.save(); //saving the ticket to a file
                            flag=true;
                            break;
                        case "Valid but Unavailable":
                            System.out.println("\nThe seat is valid but has been booked already. Please try a different seat. Thank you.");
                            break;
                        case "Invalid":
                            System.out.println("\nThe seat you have entered is invalid. Please try again. Thank you.");
                            break;
                    }
                } else if (rowVal==-1) {
                    System.out.println("\nThe seat letter should be either A, B, C or D. Please try again.");
                }
            }catch (InputMismatchException e){
                System.out.println("\nPlease enter an alphabet letter for the row letter and an integer for the seat number. Thank you.");
                scan.nextLine(); //removing the line of the invalid input
            }
        }

    }


    //called when user selects 2
    public static void cancel_seat(int[] [] seats, Ticket[] tickets) {
        Scanner scan = new Scanner(System.in);
        boolean flag = false;
        while(!flag) {
            try{
                System.out.print("\nEnter the row letter(A, B, C, D): ");
                int rowVal = rowSelection(scan.next().toUpperCase());
                if(rowVal >=0 && rowVal<4){
                    String rowLetter = numToLetter(rowVal);
                    System.out.print("Enter the seat number: ");
                    int seatNum = scan.nextInt();
                    String validity = validSeat(seats, rowVal, seatNum);
                    switch (validity){
                        case "Valid and Available":
                            System.out.println("\nThe seat is valid and is vacant. Please enter the booked seat you want to cancel. Thank you.");
                            break;
                        case "Valid but Unavailable":
                            System.out.println("\nThe seat " + rowLetter + seatNum + " is valid and was booked earlier. We have cancelled the seat booking successfully. Thank you.");
                            seats[rowVal][seatNum-1] = 0;  //recording the seat as available after cancelling
                            //Removing the ticket by assigning the immediately following ticket to its index
                            int cancelSeatIndex = findTicketIndex(tickets, rowLetter, seatNum);
                            for (int index= cancelSeatIndex; index<tickets.length-1; index++){
                                tickets[index] = tickets[index+1];
                            }
                            flag=true;
                            break;
                        case "Invalid":
                            System.out.println("\nThe seat you have entered is invalid. Please try again. Thank you.");
                            break;
                    }
                } else if (rowVal==-1) {
                    System.out.println("\nThe seat letter should be either A, B, C or D. Please try again.");
                }
            }catch (InputMismatchException e){
                System.out.println("\nPlease enter an alphabet letter for the row letter and an integer for the seat number. Thank you.");
                scan.nextLine(); //removing the line of the invalid input
            }
        }
    }


    //called when user selects 3
    public static void find_first_available(int[] [] seats) {
        String rowLetter= "";
        //looping through rows and columns to find the first available seat
        outerLoop:
        for (int rowVal=0; rowVal<seats.length; rowVal++){
            for (int colVal=0; colVal<seats[rowVal].length; colVal++){
                rowLetter = numToLetter(rowVal);
                String validity = validSeat(seats, rowVal, colVal+1);
                if (validity.equals("Valid and Available")){
                    System.out.println("The seat " + rowLetter + (colVal+1) + " is the first available seat in the plane.");
                    break outerLoop; //breaking out of all loops
                }
            }
        }
    }


    //called when user selects 4
    public static void show_seating_plan(int[] [] seats) {
        String rowLetter = "";
        System.out.println("\n----------------------------------");
        System.out.println("            SEATING PLAN");
        System.out.println("----------------------------------");
        System.out.println("\n'O' - indicates the available seats");
        System.out.println("'X' - indicates the seats that have been already booked.");

        System.out.println("\n\n      1    2    3    4    5    6    7    8    9   10   11   12   13   14");
        //Iterating through the rows and columns to display the seating plan of the plane
        for (int rowVal=0; rowVal<seats.length; rowVal++){
            System.out.println();
            rowLetter = numToLetter(rowVal);
            System.out.print(rowLetter + "     ");
            for (int colVal=0; colVal<seats[rowVal].length; colVal++){
                String validity = validSeat(seats, rowVal, colVal+1);
                if (validity.equals("Valid and Available")){
                    System.out.print("O    ");
                } else if (validity.equals("Valid but Unavailable")) {
                    System.out.print("X    ");
                }
            }
        }
        System.out.println();
    }


    //called when user selects 5
    public static void print_tickets_info(Ticket[] tickets) {
        int totSeatPrice = 0;
        System.out.println("\n-----------------------------");
        System.out.println("\n     TICKETS PURCHASED");
        System.out.println("\n-----------------------------");

        //Iterating through the ticket objects in the sold tickets array to print the ticket information and add the prices of all the bookings
        for (Ticket ticket : tickets) {
            if (ticket != null) {
                ticket.printTicketInfo();
                totSeatPrice += ticket.getPrice();
            } else {
                break;
            }
        }
        System.out.println("\n\n----------------------------------");
        System.out.println("----------------------------------");
        System.out.println("\nThe Total price of the purchased tickets:   " + totSeatPrice);
        System.out.println("\nThank you.");
        System.out.println("----------------------------------");
    }


    //called when user selects 6
    public static void search_ticket(int[] [] seats, Ticket[] tickets) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n------------------------------------");
        System.out.println("           SEARCH TICKETS");
        System.out.println("\n------------------------------------");
        boolean flag = false;
        while (!flag) {
            try {
                System.out.print("\nEnter the row letter(A, B, C, D): ");
                int rowVal = rowSelection(scan.next().toUpperCase());
                if (rowVal >= 0 && rowVal < 4) {
                    String rowLetter = numToLetter(rowVal);
                    System.out.print("Enter the seat number: ");
                    int seatNum = scan.nextInt();
                    String validity = validSeat(seats, rowVal, seatNum);
                    switch (validity) {
                        case "Valid and Available":
                            System.out.println("\nThe seat is valid and is available.");
                            flag = true;
                            break;
                        case "Valid but Unavailable":
                            System.out.println("\nThe seat has been booked!!");
                            //finding the index of the sold ticket and printing the ticket information
                            int ticketIndex = findTicketIndex(tickets, rowLetter, seatNum);
                            Ticket ticket = tickets[ticketIndex];
                            ticket.printTicketInfo();
                            flag = true;
                            break;
                        case "Invalid":
                            System.out.println("\nThe seat you have entered is invalid. Please try again. Thank you.");
                            break;
                    }
                } else if (rowVal == -1) {
                    System.out.println("\nThe seat letter should be either A, B, C or D. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter an alphabet letter for the row letter and an integer for the seat number. Thank you.");
                scan.nextLine();
            }

        }
    }
}