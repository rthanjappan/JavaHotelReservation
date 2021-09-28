
package hotelreservationdemo;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Rosemol
 */
public class Hotel implements BookHotelRoom,
        ReservationStatus{
    ArrayList<HotelRoom> rooms;
    ArrayList<Reservation> reservations=new ArrayList();
    public enum  RoomType{DOUBLE,KING,DOUBLESUITE,KINGSUITE};
    int numEachRooms=2;
    static Scanner input=new Scanner(System.in);
    Reservation currReservation;
    /**
     *default constructor
     * 
     */    
    public Hotel() {
    rooms=new ArrayList();
    
    for(int i=0;i<numEachRooms;i++){
        DoubleHotelRoom room = new DoubleHotelRoom();
        room.setAverageNightlyPrice(95.50);
        rooms.add(room);
        
 
    }
    for(int i=0;i<numEachRooms;i++){
        KingHotelRoom room =  new KingHotelRoom();
        room.setAverageNightlyPrice(96.50);
        rooms.add(room);
        
    }
    for(int i=0;i<numEachRooms;i++){
        DoubleSuiteHotelRoom room =  new DoubleSuiteHotelRoom();
        room.setAverageNightlyPrice(105.36);
        rooms.add(room);
        
    }
    for(int i=0;i<numEachRooms;i++){
        KingSuiteHotelRoom room =  new KingSuiteHotelRoom();
        room.setAverageNightlyPrice(104.52);
        rooms.add(room);
        
    }
        
    }
    /**
     *method to create a reservation
     * 
     * @return currReservation the reservation made.
     */
    @Override
    public Reservation bookHotelRoom() {
        currReservation=new Reservation();
        
        return currReservation;

    }
    /**
     *method to create a reservation
     * 
     * 
     */
    public void createReservation(int iChoice) throws NoVacancyException{
        double price=0.0;    
        Reservation.RoomType roomType=null;

            switch(iChoice){
                case 1:
                    price=bookDoubleHotelRoom();
                    roomType=Reservation.RoomType.DOUBLE;
                    break;
                case 2:
                    price=bookKingHotelRoom();
                    roomType=Reservation.RoomType.KING;
                    break;
                case 3:
                    price=bookDoubleSuiteHotelRoom();
                    roomType=Reservation.RoomType.DOUBLESUITE;
                    break;
                case 4:
                    price=bookKingSuiteHotelRoom();
                    roomType=Reservation.RoomType.KINGSUITE;
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Invalid choice.");

   
            }

        
        currReservation.setTotalCostForTheStay(price);
        currReservation.setRoomType(roomType);
    }
    /**
     *method to create a room
     * 
     * 
     * @param rmType
     * @return room the available hotel room
     */
    public HotelRoom createRoom(Reservation.RoomType rmType){
     
        HotelRoom room=null;
        
        switch(rmType){
            case DOUBLE:    

                for(int i=0;i<numEachRooms;i++){
                    if(rooms.get(i).getIsVacant()==true){
                        //rooms.get(i).setIsBooked(true);
//                    if(rooms.get(i).getIsBooked()==false){
                        rooms.get(i).setIsVacant(false);
                        room=rooms.get(i);
                        break;
                    }
                }
            break;
            case KING: 
                for(int i=numEachRooms;i<numEachRooms*2;i++){
                    if(rooms.get(i).getIsVacant()==true){
                        rooms.get(i).setIsVacant(false);
                        room=rooms.get(i);
                        break;
                    }
//                    if(rooms.get(i).getIsBooked()==false){
//                        rooms.get(i).setIsBooked(true);
//                        room=rooms.get(i);
//                        break;
//                    }
            
                }
            break;
            case DOUBLESUITE:
                for(int i=numEachRooms*2;i<numEachRooms*3;i++){
                    
                    if(rooms.get(i).getIsVacant()==true){
                        rooms.get(i).setIsVacant(false);
                        room=rooms.get(i);
                        break;
                    }
//                    if(rooms.get(i).getIsBooked()==false){
//                        rooms.get(i).setIsBooked(true);
//                        room=rooms.get(i);
//                        break;
//                    }
            
                }
            break;
            case KINGSUITE:
                for(int i=numEachRooms*3;i<numEachRooms*4;i++){
                    
                    if(rooms.get(i).getIsVacant()==true){
                        rooms.get(i).setIsVacant(false);
                        room=rooms.get(i);
                        break;
                    }
//                    if(rooms.get(i).getIsBooked()==false){
//                        rooms.get(i).setIsBooked(true);
//                        room=rooms.get(i);
//                        break;
//                    }
            
                }
            break;
                
        }
        
        return room;
    }
    /**
     *method to create guest
     * 
     * @return guest the details of the guest
     */
    public Guest createGuest(String fName,
            String lName,String address,
            int numChild,int numAdults) throws OverCapacityException{

        boolean flag=false;
        Reservation.RoomType rmType = currReservation.getRoomType();
        
        int totalPartySize=numChild+numAdults;
        if(rmType==Reservation.RoomType.DOUBLE ||
                rmType==Reservation.RoomType.DOUBLESUITE){
            if(totalPartySize<=0 || totalPartySize>4){
                throw new OverCapacityException("Too many guests in the party."
                +"The capacity of Double room is 4");
            }
            else{
             flag=true;   
            }
        }else if(rmType==Reservation.RoomType.KING ||
                rmType==Reservation.RoomType.KINGSUITE){
            if(totalPartySize<=0 || totalPartySize>3){
                throw new OverCapacityException("Too many guests in the party."
                +"The capacity of King room is 3");
            }
            else{
             flag=true;   
            }
        }
            

        Guest guest=new Guest(fName,lName,address,numChild,numAdults);
        currReservation.setGuest(guest);
        
        return guest;
}
    /**
     *method to do the check-in procedure
     * 
     * @param reservationNum
     * @return integer value showing the results of the process
     */
    @Override
    public int checkIn(int reservationNum) {
        Reservation reservation;
        //will cause the room to be occupied and   

        for(int i=0;i<reservations.size();i++){
            if(reservations.get(i).getReservationNumber()==reservationNum){
                reservation=reservations.get(i);
                //set the status of the reservation to valid.
                //if(reservation.getStatus()==Reservation.Status.VALID){
                if(reservation.getStatus()==Reservation.Status.VALID){
                    return 1;//guest is already checked in
                }
                if(reservation.getStatus()==Reservation.Status.NOT_CHECKED_IN){
                    
                    HotelRoom room=createRoom(reservation.getRoomType());
                    
                    if(room==null){
                    //if(reservations.get(i).getRoom()==null)
                        return -3;
                    }
                    reservation.setRoom(room);
                    reservation.getRoom().setIsVacant(false);
                    reservation.getRoom().setIsBooked(true);
                    
                    reservation.setStatus(Reservation.Status.VALID);
                    
                    //reservation.getRoom().setIsVacant(false);
                    //reservation.setRoom(room);
                    return 0;//the guest is successfully checked in
                }
                if(reservations.get(i).getStatus()==Reservation.Status.INVALID){
                    return -2;//the guest already checked in and checked out
                    //no more checking in.
                }
                //Also the room isVacant field should be false.
               
                
            }
        }
        return -1;//the reservation number does not exists
    }
    /**
     *method to do the check-out procedure
     * 
     * @param reservationNum
     * @return integer value showing the results of the process
     */
    @Override
    public int checkOut(int reservationNum) {
        Reservation reservation;
        for(int i=0;i<reservations.size();i++){
            if(reservations.get(i).getReservationNumber()==reservationNum){
                reservation=reservations.get(i);
                if(reservation.getStatus()==Reservation.Status.NOT_CHECKED_IN){
                    return -2;//guest is not checked in yet.
                }
                if(reservation.getStatus()==Reservation.Status.INVALID){
                    return -3;
                }
                if(reservation.getStatus()==Reservation.Status.VALID){
                reservation.setStatus(Reservation.Status.INVALID);//checks-out the guest
                //reservation.getRoom().setIsVacant(true);//sets the room vacant
                reservation.getRoom().setIsVacant(true);
                reservation.getRoom().setIsBooked(false);
                
                HotelRoom rm=new DoubleHotelRoom(reservation.getRoom());
                reservation.setRoom(rm);
                //rm.setCapacity(i);
                
                return 0;//the guest is successfully checked out
                }
            }
        }
        return -1;//the reservation number does not exists
        
        
    }
    /**
     *method to book a double hotel room
     * 
     * @return price double the average nightly price
     * @throws hotelreservationdemo.NoVacancyException
     */
    public double bookDoubleHotelRoom() throws NoVacancyException{
        
        for(int i=0;i<numEachRooms;i++){
            //if(rooms.get(i).getIsBooked()==false){
            //if(rooms.get(i).getIsVacant()==true){
                //rooms.get(i).setIsVacant(false);
                
                return rooms.get(i).getAverageNightlyPrice();
            //}
            
        }
        throw new NoVacancyException("Double hotel rooms are not available.");
        
    }
    /**
     *method to book a king  hotel room
     * 
     * @return price double the average nightly price
     * @throws hotelreservationdemo.NoVacancyException
     */
    public double bookKingHotelRoom() throws NoVacancyException{
        
        for(int i=numEachRooms;i<numEachRooms*2;i++){
            //if(rooms.get(i).getIsVacant()==true){
            //if(rooms.get(i).getIsBooked()==false){
                //rooms.get(i).setIsVacant(false);
                return rooms.get(i).getAverageNightlyPrice();
           // }
            
        }
        throw new NoVacancyException("King hotel rooms are not available.");
        
    }
    /**
     *method to book a double suite hotel room
     * 
     * @return price double the average nightly price
     * @throws hotelreservationdemo.NoVacancyException
     */
    public double bookDoubleSuiteHotelRoom() throws NoVacancyException{
        
        for(int i=numEachRooms*2;i<numEachRooms*3;i++){
            //if(rooms.get(i).getIsVacant()==true){
           // if(rooms.get(i).getIsBooked()==false){
                //rooms.get(i).setIsVacant(false);
                return rooms.get(i).getAverageNightlyPrice();
            //}
            
        }
        throw new NoVacancyException("Double Suite hotel rooms are not available.");
        
    }
    /**
     *method to book a king suite hotel room
     * 
     * @return price double the average nightly price
     * @throws hotelreservationdemo.NoVacancyException
     */
    public double bookKingSuiteHotelRoom() throws NoVacancyException{
        
        for(int i=numEachRooms*3;i<numEachRooms*4;i++){
            //if(rooms.get(i).getIsVacant()==true){
            //if(rooms.get(i).getIsBooked()==false){
                //rooms.get(i).setIsVacant(false);
                return rooms.get(i).getAverageNightlyPrice();
            //}
            
        }
        throw new NoVacancyException("King Suite hotel rooms are not available.");
        
    }
    /**
     *method to check for vacancy in the hotel
     * 
     * @return boolean true if vacancy or false if no vacancy
     * @throws hotelreservationdemo.NoVacancyException
     */
    public boolean hasVacancy() throws NoVacancyException{
        for(int i=0;i<rooms.size()-1;i++){
            if(rooms.get(i).getIsVacant()==true){
                return true;
            }

        }
        throw new NoVacancyException();
    }
    //Accessors and mutators
    public ArrayList<HotelRoom> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<HotelRoom> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }


    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Reservation getCurrReservation() {
        return currReservation;
    }

    public void setCurrReservation(Reservation currReservation) {
        this.currReservation = currReservation;
    }
    
    /**
     *
     * checks whether two objects are equal
     * @return boolean
     */

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Hotel objHotel=(Hotel)obj;
        if(rooms.equals(objHotel.getRooms())&&
                reservations.equals(objHotel.getReservations())){
            return true;
        }
           return false; 
    }
    /**
     *
     * string representation of the class
     * @return String
     */
    @Override
    public String toString() {
        return "Hotel{" + "rooms=" + rooms + 
                ", reservations=" + reservations 
                +'}';
    }

    
    
}
