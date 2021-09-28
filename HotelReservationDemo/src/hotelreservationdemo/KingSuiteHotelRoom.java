
package hotelreservationdemo;

/**
 *
 * @author Rosemol
 */
public class KingSuiteHotelRoom extends KingHotelRoom {
    /**
     *default constructor
     * 
     */    
    public KingSuiteHotelRoom() {
        super.setHasMicroFridge(true);
        super.setRoomType(RoomType.KINGSUITE);
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
        KingSuiteHotelRoom room=(KingSuiteHotelRoom)obj;
        
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
        return "\nKingSuiteHotelRoom"+super.toString();
    }
}
