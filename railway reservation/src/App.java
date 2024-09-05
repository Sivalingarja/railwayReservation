import java.util.Scanner;
public class App {

    public static void bookTicket(passengers passenger)
    {
        ticketBooker booker = new ticketBooker();
        if(ticketBooker.availableWaitingList <= 0)
        {
            System.err.println(ticketBooker.availableWaitingList);
            System.out.println("Sorry Ticket Sold Out");
            return;
        }
        if(ticketBooker.availableLowerBerth > 0 && passenger.berthPreference.equals("L"))
        {
            System.out.println("....................................Lower Birth Given");
            booker.bookTicket(passenger,ticketBooker.lowerBerthPosition.get(0),"L");
            ticketBooker.lowerBerthPosition.remove(0);
            ticketBooker.availableLowerBerth--;
        }
        else if(ticketBooker.availableMiddleBerth > 0 && passenger.berthPreference.equals("M"))
        {
            System.out.println("....................................Middle Birth Given");
            booker.bookTicket(passenger,ticketBooker.middleBerthPosition.get(0),"M");
            ticketBooker.middleBerthPosition.remove(0);
            ticketBooker.availableMiddleBerth--;
        }
        else if(ticketBooker.availableUpperBerth > 0 && passenger.berthPreference.equals("U"))
        {
            System.out.println("....................................Upper Birth Given");
            booker.bookTicket(passenger,ticketBooker.upperBerthPosition.get(0),"U");
            ticketBooker.upperBerthPosition.remove(0);
            ticketBooker.availableUpperBerth--;
        }
        else if(ticketBooker.availableLowerBerth > 0)
        {
            System.out.println("....................................Lower Birth Given");
            booker.bookTicket(passenger,ticketBooker.lowerBerthPosition.get(0),"L");
            ticketBooker.lowerBerthPosition.remove(0);
            ticketBooker.availableLowerBerth--;
        }
        else if(ticketBooker.availableMiddleBerth > 0)
        {
            System.out.println("....................................Middle Birth Given");
            booker.bookTicket(passenger,ticketBooker.middleBerthPosition.get(0),"M");
            ticketBooker.middleBerthPosition.remove(0);
            ticketBooker.availableMiddleBerth--;
        }
        else if(ticketBooker.availableUpperBerth > 0)
        {
            System.out.println("....................................Upper Birth Given");
            booker.bookTicket(passenger,ticketBooker.upperBerthPosition.get(0),"U");
            ticketBooker.upperBerthPosition.remove(0);
            ticketBooker.availableUpperBerth--;
        }
        else if(ticketBooker.availableRAC > 0)
        {
            System.out.println("....................................Added to RAC");
            booker.addToRAC(passenger,ticketBooker.RAC_Position.get(0));
            ticketBooker.RAC_Position.remove(0);
            ticketBooker.availableRAC--;
        }
        else if(ticketBooker.availableWaitingList > 0)
        {
            System.out.println("....................................Waiting List");
            booker.waiting(passenger,ticketBooker.waitingListPosition.get(0));
            ticketBooker.waitingListPosition.remove(0);
            ticketBooker.availableWaitingList--;
        }
        System.out.println(passenger.passengerId);
    }
    public static void cancelTicket(int id)
    {
        ticketBooker booker = new ticketBooker();
        if(!ticketBooker.details.containsKey(id))
        {
            System.out.println("........................No Ticket Booked in this ID");
            return;
        }
        booker.cancelTicket(id);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while(loop)
        {
            System.out.println("Welcome to Railway Reservation System");
            System.out.println("Enter the number");
            System.out.println(" 1.Book Ticket \n 2.Cancel Tickets \n 3.Print Passenger Details \n 4.Print Available Tickets \n 5.Exit");
            int num = sc.nextInt();
            switch (num) {
                // book tickets
                case 1 :
                    System.out.println("Enter name, Age and berth preference");
                    String name = sc.next();
                    int age = sc.nextInt();
                    String berthPreference = sc.next(); 
                    passengers passenger = new passengers(name,age,berthPreference);
                    bookTicket(passenger);
                    break;
                // cancel tickets    
                case 2 :
                    System.out.println("Enter the id");
                    int id = sc.nextInt();
                    cancelTicket(id);
                    break;
                case 3 :
                    ticketBooker.passengerdetails();    
                    break;
                default:
                    loop = false;
                    break;
            }
        }
        sc.close();
    }
}
