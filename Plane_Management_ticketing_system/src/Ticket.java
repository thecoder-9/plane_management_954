import java.io.IOException;
import java.io.FileWriter;

public class Ticket {

    //attributes
    private String row;
    private int seat;
    private int price;
    private Person person;

    //constructor
    public Ticket(String row, int seat, int price, Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    //getters and setters for the attributes
    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    //method to print the ticket information
    public void printTicketInfo(){
        System.out.println("\n\n\n------------------------------");
        System.out.println("  Ticket Booking Information");
        System.out.println("------------------------------");
        System.out.println("      Row: "+ row);
        System.out.println("      Seat: "+ seat);
        System.out.println("      Price: "+ price);
        System.out.println("\nPassenger Information:");
        person.printInfo();
        System.out.println("------------------------------");
    }

    //method to save the ticket information in a text file
    public void save(){
       try{
           FileWriter file = new FileWriter(row+seat+".txt");
           file.write("------------------------------");
           file.write("\n  Ticket Booking Information");
           file.write("\n------------------------------");
           file.write("\n      Row: "+ row);
           file.write("\n      Seat: "+ seat);
           file.write("\n      Price: "+ price);
           file.write("\n\n Passenger Information:");
           file.write(person.personInfo());
           file.write("\n------------------------------");
           file.close();
       }catch (IOException e){
           System.out.println("Error while writing in the file.");
           e.printStackTrace();
       }

    }
}
