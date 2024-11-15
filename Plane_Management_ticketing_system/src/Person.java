public class Person {

    //attributes
    private String name;
    private String surname;
    private String email;

    //constructor
    public Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    //getters and setters for the attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //method to save the passenger information in a text file created for a sold ticket
    public String personInfo(){
        return "\n\n      Name: "+name+"\n      Surname: "+surname+"\n      Email: "+email;
    }

    //method to print the passenger information when displaying the tickets
    public void printInfo() {
        System.out.println("      Name: " + name);
        System.out.println("      Surname: " + surname);
        System.out.println("      Email: " + email);
    }
}
