
package hotelreservationdemo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Rosemol
 */
public class Reservation implements Serializable  {

    
    int reservationNumber;

    private static int ReservationNo=1000;
    enum Status{NOT_CHECKED_IN,VALID,INVALID}; 
    Status status;
    double totalCostForTheStay;
    public enum  RoomType{DOUBLE,KING,DOUBLESUITE,KINGSUITE}; 
    RoomType roomType;
    Guest guest;
    HotelRoom room;
    /**
     *default constructor
     * 
     */
    public Reservation() {
        this.reservationNumber = ReservationNo++;
    }
    /**
     *constructor with arguments
     * 
     * @param status the status of the room
     * @param totalCostForTheStay the total price
     * @param roomType the type of the room
     * @param guest the information of the guest
     * @param room the details of the room booked

     */
    public Reservation(Status status, 
            double totalCostForTheStay, RoomType roomType, 
            Guest guest, HotelRoom room) {
        this.reservationNumber = ReservationNo++;
        this.status = status;
        this.totalCostForTheStay = totalCostForTheStay;
        this.roomType = roomType;
        this.guest = guest;
        this.room = room;
    }
    /**
     *copy constructor
     * 
     * @param obj The object to copy
     */
    public Reservation(Reservation obj) {
        this.reservationNumber =obj.getReservationNumber();
        this.status = obj.getStatus();
        this.totalCostForTheStay = obj.getTotalCostForTheStay();
        this.roomType = obj.getRoomType();
        this.guest = obj.getGuest();
        this.room = obj.getRoom();
    }

    
/**
     * @return the ReservationNo
     */
    public static int getReservationNo() {
        
        return ReservationNo;
    }

    /**
     * @param aReservationNo the ReservationNo to set
     */
    public static void setReservationNo(int aReservationNo) {
        ReservationNo = aReservationNo;
    }
    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getTotalCostForTheStay() {
        return totalCostForTheStay;
    }

    public void setTotalCostForTheStay(double totalCostForTheStay) {
        this.totalCostForTheStay = totalCostForTheStay;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public HotelRoom getRoom() {
        return room;
    }

    public void setRoom(HotelRoom room) {
        this.room = room;
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
        final Reservation other = (Reservation) obj;
        if (this.reservationNumber == other.getReservationNumber()) {
            return false;
        }
        if (this.status != other.getStatus()) {
            return false;
        }
        if (this.totalCostForTheStay != other.getTotalCostForTheStay()) {
            return false;
        }
        if (this.roomType != other.getRoomType()) {
            return false;
        }
        if (!Objects.equals(this.guest, other.getGuest())) {
            return false;
        }
        if (!Objects.equals(this.room, other.getRoom())) {
            return false;
        }
        return true;
    }
    /**
     *
     * string representation of the class
     * @return String
     */
    @Override
    public String toString() {
        return "\n\nReservation{" + 
                "reservationNumber=" + reservationNumber +
                ", status=" + status + 
                ", totalCostForTheStay=" + totalCostForTheStay 
                + ", roomType=" + roomType 
                + ", guest=" + guest
                + ", room=" + room + '}';
    }
    
}
