
package hotelreservationdemo;

/**
 *
 * @author Rosemol
 */
public class KingHotelRoom extends HotelRoom {
    /**
     *default constructor
     * 
     */
    public KingHotelRoom() {
        super.setCapacity(3);
        super.setRoomType(RoomType.KING);

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
        KingHotelRoom room=(KingHotelRoom)obj;
        
        if(getCapacity()==room.getCapacity()
        && getHasMicroFridge()==room.getHasMicroFridge()
        &&getAverageNightlyPrice()==room.getAverageNightlyPrice()
        &&getIsBooked()== room.getIsBooked()
        &&getIsVacant()== room.getIsVacant()){
            return true;
        }
        else{
            return false;
        }
        
    }
    

    /**
     * string representation of the class
     * @return String
     */
    @Override
    public String toString() {
        return "\nKingHotelRoom"+super.toString();
    }
}
