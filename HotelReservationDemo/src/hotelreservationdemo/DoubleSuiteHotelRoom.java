/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelreservationdemo;

/**
 *
 * @author Rosemol
 */
public class DoubleSuiteHotelRoom extends DoubleHotelRoom {
    /**
     *default constructor
     * 
     */
    public DoubleSuiteHotelRoom() {
        super.setHasMicroFridge(true);
        super.setRoomType(RoomType.DOUBLESUITE);
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
        DoubleSuiteHotelRoom room=(DoubleSuiteHotelRoom)obj;
        
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
     * @return String
     */
    @Override

    public String toString() {
        return "\nDoubleSuiteHotelRoom"+super.toString();
    }
}
