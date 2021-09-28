
package hotelreservationdemo;

import java.io.Serializable;


/**
 *
 * @author Rosemol
 */


public abstract class HotelRoom implements Serializable{
    private int capacity; 
    public enum  RoomType{DOUBLE,KING,DOUBLESUITE,KINGSUITE};
    private RoomType roomType;
    private boolean hasMicroFridge=false;
    private double averageNightlyPrice;
    private boolean isVacant=true;
    private boolean isBooked=false;
    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
    
    /**
     * @return the hasMicroFridge
     */
    public boolean getHasMicroFridge() {
        return hasMicroFridge;
    }

    /**
     * @param hasMicroFridge the hasMicroFridge to set
     */
    public void setHasMicroFridge(boolean hasMicroFridge) {
        this.hasMicroFridge = hasMicroFridge;
    }

    /**
     * @return the averageNightlyPrice
     */
    public double getAverageNightlyPrice() {
        return averageNightlyPrice;
    }

    /**
     * @param averageNightlyPrice the averageNightlyPrice to set
     */
    public void setAverageNightlyPrice(double averageNightlyPrice) {
        this.averageNightlyPrice = averageNightlyPrice;
    }

    /**
     * @return the isVacant
     */
    public boolean getIsVacant() {
        return isVacant;
    }

    /**
     * @param isVacant the isVacant to set
     */
    public void setIsVacant(boolean isVacant) {
        this.isVacant = isVacant;
    }
    /**
     * @return the isBooked
     */
    public boolean getIsBooked() {
        return isBooked;
    }
    /**
     * @param isBooked the isBooked to set
     */
    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }
   
    /**
     *
     * checks whether two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        HotelRoom room=(HotelRoom)obj;
        
        if(getCapacity()==room.getCapacity()
        && getRoomType()==room.getRoomType()
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
     *@return string
     */

    @Override
    public String toString() {
        return "{" + "capacity=" + capacity +
                "room type=" + roomType +
                ", hasMicroFridge=" + hasMicroFridge +
                ", averageNightlyPrice=" + averageNightlyPrice +
                ", isBooked=" + isBooked +
                ", isVacant=" + isVacant + '}'
                +"\n";
    }
    
}
