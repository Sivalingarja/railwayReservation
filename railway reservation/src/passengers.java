public class passengers {
    static int id = 1;
    String name;
    int age;
    String berthPreference;
    String alloted;
    int seatNumber;
    int passengerId;
    public passengers(String name,int age,String berthPreference)
    {
        this.name = name;
        this.age = age;
        this.berthPreference = berthPreference;
        this.passengerId = id++;
        alloted = "";
        seatNumber = -1;
    } 
}