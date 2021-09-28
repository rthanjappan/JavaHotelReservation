/*
Program : Hotel Reservation Demo 
Programmer : Rosemol Thanjappan
Date : 3/31/2019
 */
package hotelreservationdemo;

import javax.swing.JOptionPane;


/**
 *
 * @author Rosemol
 */
public class HotelReservationDemo {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        
        
        HotelReservationJFrame frame= new HotelReservationJFrame();
        
        frame.setVisible(true);
        JOptionPane.showMessageDialog(null,"1)To make a new reservation"
                + " click the button \"New Reservation\".\n"
                + "2)To check-in and check-out enter the reservation number.\n"
                + "3)To view current reservations click \"Next\""
                + " and \"Previous\" buttons.");
       
    }
  
}
