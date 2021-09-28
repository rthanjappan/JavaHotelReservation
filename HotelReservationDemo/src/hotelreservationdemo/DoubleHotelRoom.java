
package hotelreservationdemo;

/**
 *
 * @author Rosemol
 */
public class DoubleHotelRoom extends HotelRoom {
    /**
     *default constructor
     * 
     */
    public DoubleHotelRoom() {
        super.setCapacity(4);
        super.setRoomType(RoomType.DOUBLE);
    }
    /**
     *copy constructor
     * 
     */
    public DoubleHotelRoom(HotelRoom obj) {
        super.setCapacity(obj.getCapacity());
        super.setRoomType(obj.getRoomType());
        super.setAverageNightlyPrice(obj.getAverageNightlyPrice());
        super.setHasMicroFridge(obj.getHasMicroFridge());
        super.setIsBooked(obj.getIsBooked());
        super.setIsVacant(obj.getIsVacant());
        
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
        DoubleHotelRoom room=(DoubleHotelRoom)obj;
        
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
     *
     * string representation of the class
     */
    @Override
    public String toString() {
        return "\nDoubleHotelRoom"+super.toString();
    }
    
    
}
