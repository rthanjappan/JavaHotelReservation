/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelreservationdemo;

import java.io.Serializable;

/**
 *
 * @author Rosemol
 */
public class Guest implements Serializable{
    String firstName;
    String lastName;
    String address;
    int numberChildrenInParty;
    int numberAdultsInParty;

    public Guest(String firstName, String lastName, String address, int numberChildrenInParty, int numberAdultsInParty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.numberChildrenInParty = numberChildrenInParty;
        this.numberAdultsInParty = numberAdultsInParty;
    }
    public Guest(Guest obj){
        this.firstName = obj.getFirstName();
        this.lastName = obj.getLastName();
        this.address = obj.getAddress();
        this.numberChildrenInParty = obj.getNumberChildrenInParty();
        this.numberAdultsInParty = obj.getNumberAdultsInParty();
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
        Guest guest=(Guest)obj;
        
        if(getFirstName().equals(guest.getFirstName())
        && getLastName().equals(guest.getLastName())
        &&getAddress().equals(guest.getAddress())
        &&getNumberChildrenInParty()== guest.getNumberChildrenInParty()
        &&getNumberAdultsInParty()== guest.getNumberAdultsInParty()){
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
        return "Guest{" + "firstName=" + firstName 
                + ", lastName=" + lastName +
                ", address=" + address + 
                ", numberChildrenInParty=" + numberChildrenInParty + 
                ", numberAdultsInParty=" + numberAdultsInParty + '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberChildrenInParty() {
        return numberChildrenInParty;
    }

    public void setNumberChildrenInParty(int numberChildrenInParty) {
        this.numberChildrenInParty = numberChildrenInParty;
    }

    public int getNumberAdultsInParty() {
        return numberAdultsInParty;
    }

    public void setNumberAdultsInParty(int numberAdultsInParty) {
        this.numberAdultsInParty = numberAdultsInParty;
    }
    
}
