import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ticketBooker { 
    static int availableLowerBerth = 1;
    static int availableUpperBerth = 1;
    static int availableMiddleBerth = 1;
    static int availableRAC = 1;
    static int availableWaitingList = 1;

    static Queue<Integer> RAC = new LinkedList<>();
    static Queue<Integer> WaitingList = new LinkedList<>();
    static List<Integer> bookedTicketList = new ArrayList<>();

    static List<Integer> lowerBerthPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer> upperBerthPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer> middleBerthPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer> RAC_Position = new ArrayList<>(Arrays.asList(1));
    static List<Integer> waitingListPosition = new ArrayList<>(Arrays.asList(1));

    static HashMap<Integer,passengers> details = new HashMap<>();

    public void bookTicket(passengers passenger,int pos,String berth)
    {
        passenger.alloted = berth;
        passenger.seatNumber = pos;
        bookedTicketList.add(passenger.passengerId);
        details.put(passenger.passengerId,passenger);
    }
    public void addToRAC(passengers passenger,int pos)
    {
        passenger.alloted = "RAC";
        passenger.seatNumber = pos;
        RAC.add(passenger.passengerId);
        details.put(passenger.passengerId,passenger);
    }
    public void waiting(passengers passenger,int pos)
    {
        passenger.alloted = "WL";
        passenger.seatNumber = pos;
        RAC.add(passenger.passengerId);
        details.put(passenger.passengerId,passenger);
    }
    public void cancelTicket(int passengerId)
    {
        //remove the passenger from the map
        passengers p = details.get(passengerId);
        details.remove(Integer.valueOf(passengerId));
        //remove the booked ticket from the list
        bookedTicketList.remove(Integer.valueOf(passengerId));

        //take the booked position which is now free
        int positionBooked = p.seatNumber;

        System.out.println("---------------cancelled Successfully");

        //add the free position to the correspoding type of list (either L,M,U)
        if(p.alloted.equals("L")) 
        { 
          availableLowerBerth++;
          lowerBerthPosition.add(positionBooked);
        }
        else if(p.alloted.equals("M"))
        { 
          availableMiddleBerth++;
          middleBerthPosition.add(positionBooked);
        }
        else if(p.alloted.equals("U"))
        { 
          availableUpperBerth++;
          upperBerthPosition.add(positionBooked);
        }

        //check if any RAC is there
        if(RAC.size() > 0)
        {
            passengers passengerFromRAC = details.get(RAC.poll());
            int positionRac = passengerFromRAC.seatNumber;
            RAC.add(positionRac);
            RAC.remove(Integer.valueOf(passengerFromRAC.passengerId));
            availableRAC++;

            if(WaitingList.size()>0)
            {
                //take the passenger from WL and add them to RAC , increase the free space in waiting list and 
                //increase available WL and decrease available RAC by 1
                passengers passengerFromWaitingList = details.get(WaitingList.poll());
                int positionWL = passengerFromWaitingList.seatNumber;
                waitingListPosition.add(positionWL);
                WaitingList.remove(Integer.valueOf(passengerFromWaitingList.passengerId));

                passengerFromWaitingList.seatNumber = RAC_Position.get(0);
                passengerFromWaitingList.alloted = "RAC";
                RAC_Position.remove(0);
                RAC.add(passengerFromWaitingList.passengerId);
                
                availableWaitingList++;
                availableRAC--;
            }
            // now we have a passenger from RAc to whom we can book a ticket, 
            //so book the cancelled ticket to the RAC passenger
            App.bookTicket(passengerFromRAC);
        }
    }
    public static void passengerdetails()
    {
        if(details.size() == 0)
        {
            System.out.println("No one Booked");
        }
        else
        {
            for(passengers passenger : details.values())
            {
                System.out.println("Name : "+ passenger.name);
                System.out.println("Id : "+ passenger.passengerId);
                System.out.println("Age : "+ passenger.age);
                System.out.println("Status : "+passenger.alloted +" "+passenger.seatNumber);  
                System.out.println("****************************");              
            }
        }
        return;
    }
}
