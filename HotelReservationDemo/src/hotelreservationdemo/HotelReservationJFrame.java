
package hotelreservationdemo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Rosemol
 */
public class HotelReservationJFrame extends JFrame

{
    final int WIDTH=1100;
    final int LENGTH=575;
    
    private JTextField[] textFields;
    JTextField txtNumNights;
    private JLabel[] labels;
    JButton availButton;
    JButton calcButton;
    JButton reserveButton;
    JButton newReserveButton;
    JButton checkInButton;
    JButton checkOutButton;
    JButton nextButton;
    JButton prevButton;
    JRadioButton roomTypes[];
    JPanel roomTypesPanel;
    JPanel guestInfoPanel;
    JPanel checkInOutPanel;
    JPanel currReservationsPanel;
    JPanel panel;
    
    Hotel hotel;
    int currIndex=0;
    int currReservationNo;
    boolean doneReservation=true;
    JLabel numNights;
    
    /**
    *
    * no arg constructor
    */
    public HotelReservationJFrame(){
        super();
        super.setTitle("Hotel Reservation");
        super.setSize(new Dimension(WIDTH,LENGTH));
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
       
        
        hotel=new Hotel();
        readHotelDataFromFile();
        if(hotel.getReservations().size()>0){
            doInitialization();

        }
        
        addReservationPanel();
        addWindowListener(new myWindowAdapter());
        
                
    }
    /**
    *
    * constructor
    * @param title the title of the window
    * @param width the width of the window
    * @param length the length of the window
    */
    public HotelReservationJFrame(String title,int width,int length){
        super();
        super.setTitle(title);
        super.setSize(new Dimension(width,length));
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        hotel=new Hotel();
        readHotelDataFromFile();
        if(hotel.getReservations().size()>0){
            doInitialization();
        }
        
        
        addReservationPanel();
        addWindowListener(new myWindowAdapter());

                
    }
    /**
    *
    * method to do initialization 
    */
    private void doInitialization(){
        Reservation lastReservation=hotel.getReservations().get(hotel.getReservations().size()-1);
        int lastReservationNo=lastReservation.getReservationNumber();
        Reservation.setReservationNo(lastReservationNo+1);
        //JOptionPane.showMessageDialog(null,"ReservationNo : "+Reservation.getReservationNo());


        //checking for current booked rooms
        HotelRoom room=null;
        ArrayList<Reservation> reservations=hotel.getReservations();
        System.out.println("The reservations are :\n"+reservations);
        for(int i=0;i<reservations.size();i++){
            //if(reservations.get(i).getRoom().getIsBooked() == true){
//            if(reservations.get(i).getRoom().getIsVacant() == false){
            if(reservations.get(i).getRoom()!=null){
                if(reservations.get(i).getRoom().getIsVacant() == false){
                    //room=hotel.createRoom(reservations.get(i).getRoomType());
                    reservations.get(i).setRoom(hotel.createRoom(reservations.get(i).getRoomType()));
                }
            }
        }
        
        
        //for(int j=0;j<hotel.getRooms().size();j++){
            System.out.println("The rooms are :\n"+hotel.getRooms());
            
        //}
    }
        
    
    /**
    *
    * creating and adding the travel panel
    * 
    */
    public void addReservationPanel(){
        
        JPanel reservationPanel=new JPanel(new BorderLayout());
        JPanel panel1=new JPanel();
        
        JPanel panel2=new JPanel(new BorderLayout(10,10));
        JPanel panel4=new JPanel();
        panel1.setPreferredSize(new Dimension(300,350));
        
        createButton();
        labels=createLabels();
        textFields=createTextFields();
        
//        calcButton=createButton();
//        calcButton.setSize(100,25);
//        calcButton.addActionListener(new ButtonListener());
//        
        
        reserveButton.setSize(100,25);
        availButton.addActionListener(new ButtonListener());
        
        BorderLayout bL1=new BorderLayout();
        
        panel1.setLayout(bL1);
        panel1.setBorder(
            BorderFactory.createTitledBorder(
                    "The hotel reservation."));
        
        //creating and adding room types panel
        buildRoomTypesPanel();
        roomTypesPanel.setBorder(
            BorderFactory.createTitledBorder(
                    "The room types available : "));
        roomTypesPanel.setPreferredSize(new Dimension(550,100));
        panel1.add(roomTypesPanel,BorderLayout.CENTER);
        
        
        //creating and adding Guest information panel
        buildGuestInfoPanel();
        guestInfoPanel.setBorder(
            BorderFactory.createTitledBorder(
                    "The guest information : "));
        panel1.add(guestInfoPanel,BorderLayout.SOUTH);
        
        enableGuestInfoPanel(false);
        
        //creating and adding checkInOutPanel
        buildCheckInOutPanel();
        checkInOutPanel.setBorder(
            BorderFactory.createTitledBorder(
                    "Check in and check out : "));

        
        //creating and adding CurrReservationsPanel
        buildCurrReservationsPanel();
        currReservationsPanel.setPreferredSize(new Dimension(400,100));
        enableCurrReservationsPanel(false);
        panel2.add(panel1,BorderLayout.CENTER);
        
        panel4.add(newReserveButton);
        panel4.add(reserveButton);
        
        panel2.add(panel4,BorderLayout.SOUTH);
        
              

        reservationPanel.add(panel2,BorderLayout.WEST);
        reservationPanel.add(checkInOutPanel,BorderLayout.CENTER);
        reservationPanel.add(currReservationsPanel,BorderLayout.EAST);
        
        add(reservationPanel);
        enableReservationsPanel(false);
    }
    /**
    *
    * method to build room types panel
    */
    private void buildRoomTypesPanel(){
        ButtonGroup group=new ButtonGroup();
        roomTypes=new JRadioButton[4];
        roomTypes[0]=new JRadioButton("Double");
        roomTypes[1]=new JRadioButton("King");
        roomTypes[2]=new JRadioButton("Double suite");
        roomTypes[3]=new JRadioButton("King suite");
        for(int i=0;i<roomTypes.length;i++){
            group.add(roomTypes[i]);
        }
        panel=new JPanel();
        roomTypesPanel=new JPanel(new FlowLayout());
        
        for(int i=0;i<roomTypes.length;i++){
            roomTypesPanel.add(roomTypes[i]);
            roomTypes[i].setPreferredSize(new Dimension(100,25));
        }
        roomTypesPanel.add(availButton);
        
        numNights=new JLabel("Number of nights to stay : ");
        txtNumNights=new JTextField(8);
        txtNumNights.addKeyListener(new myKeyAdapter());
        txtNumNights.setEditable(false);
        
        roomTypesPanel.add(numNights);
        roomTypesPanel.add(txtNumNights);
        
        
        roomTypesPanel.add(labels[0]);
        roomTypesPanel.add(textFields[0]);
        textFields[0].setEditable(false);
        
        availButton.requestFocusInWindow();
        
    }
    /**
    *
    * method to build guest info panel
    */
    private void buildGuestInfoPanel(){
        guestInfoPanel=new JPanel(new GridLayout(5,2,5,25));
        for(int i=2;i<7;i++){
            guestInfoPanel.add(labels[i]);
            guestInfoPanel.add(textFields[i]);
            
        }
                
    }
    /**
    *
    * method to build check in and out panel
    */
    private void buildCheckInOutPanel(){
        checkInOutPanel=new JPanel();
        checkInOutPanel.setLayout(new BorderLayout());
        
        JPanel panel10=new JPanel(new GridLayout(4,2,5,10));
        JPanel guestPanel=new JPanel(new GridLayout(5,2,5,10));
        JPanel roomPanel=new JPanel(new GridLayout(6,2,5,10));
        
        for(int i=7;i<11;i++){
            panel10.add(labels[i]);
            panel10.add(textFields[i]);
            textFields[i].setEditable(false);
        }
        textFields[7].setEditable(true);
        textFields[7].addKeyListener(new checkInOutKeyAdapter());
        for(int i=12;i<17;i++){
            guestPanel.add(labels[i]);
            guestPanel.add(textFields[i]);
            textFields[i].setEditable(false);
        }
        for(int i=18;i<23;i++){
            roomPanel.add(labels[i]);
            roomPanel.add(textFields[i]);
            textFields[i].setEditable(false);
        }
        roomPanel.add(labels[23]);
        roomPanel.add(textFields[23]);
        textFields[23].setEditable(false);
        
        guestPanel.setBorder(
            BorderFactory.createTitledBorder(
                    "Guest Information : "));
        roomPanel.setBorder(
            BorderFactory.createTitledBorder(
                    "Room Details : "));
        
        JPanel panel12=new JPanel();
        panel12.setLayout(new BorderLayout());
        panel12.add(panel10,BorderLayout.NORTH);
        panel12.add(guestPanel,BorderLayout.CENTER);
        panel12.add(roomPanel,BorderLayout.SOUTH);
        
        JPanel panel3=new JPanel(new FlowLayout());
        panel3.add(checkInButton);
        panel3.add(checkOutButton);
        
        checkInOutPanel.add(panel12,BorderLayout.CENTER);
        checkInOutPanel.add(panel3,BorderLayout.SOUTH);
    }
    /**
    *
    * method to build current reservations panel
    */
    private void buildCurrReservationsPanel(){
        currReservationsPanel=new JPanel();
        JPanel panel10=new JPanel(new GridLayout(4,2,5,10));
        JPanel guestPanel=new JPanel(new GridLayout(5,2,5,10));
        JPanel roomPanel=new JPanel(new GridLayout(6,2,5,10));
        GridLayout  grid4=new GridLayout(10,2,5,10);
        
        currReservationsPanel.setLayout(new BorderLayout());
        for(int i=24;i<28;i++){
            panel10.add(labels[i]);
            panel10.add(textFields[i]);
        }
        for(int i=29;i<34;i++){
            guestPanel.add(labels[i]);
            guestPanel.add(textFields[i]);
        }
        for(int i=35;i<40;i++){
            roomPanel.add(labels[i]);
            roomPanel.add(textFields[i]);
        }
        roomPanel.add(labels[34]);
        roomPanel.add(textFields[34]);

        
        guestPanel.setBorder(
            BorderFactory.createTitledBorder(
                    "Guest Information : "));
        roomPanel.setBorder(
            BorderFactory.createTitledBorder(
                    "Room Details : "));
        currReservationsPanel.setBorder(
            BorderFactory.createTitledBorder(
                    "Registration Details : "));
        JPanel panel12=new JPanel();
        panel12.setLayout(new BorderLayout());
        panel12.add(panel10,BorderLayout.NORTH);
        panel12.add(guestPanel,BorderLayout.CENTER);
        panel12.add(roomPanel,BorderLayout.SOUTH);
        JPanel panel3=new JPanel(new FlowLayout());
        
        panel3.add(prevButton);
        panel3.add(nextButton);
       
        currReservationsPanel.add(panel12,BorderLayout.CENTER);
        currReservationsPanel.add(panel3,BorderLayout.SOUTH);
    }
    /**
    *
    * method to enable or disable guest information panel
    * @param value a boolean value to enable or disable the 
    * guest information panel
    */
    private void enableGuestInfoPanel(boolean value){
        
        guestInfoPanel.setEnabled(value);
        for(int i=2;i<7;i++){
            labels[i].setEnabled(value);
            textFields[i].setEnabled(value);
            textFields[i].setEditable(value);
            //textFields[i].setText("");

        }
        
    }
    /**
    *
    * method to enable or disable current reservations panel
    * @param value a boolean value to enable or disable the 
    * current reservations panel
    */
    private void enableCurrReservationsPanel(boolean value){
        for(int i=24;i<40;i++){
            textFields[i].setEditable(value);
        }
        
    }
    /**
    *
    * method to clear the fields in check in and check out panel
    * 
    */
    private void clearCheckInOutPanel(){
        
        for(int i=7;i<11;i++){
            textFields[i].setText("");
        }
        
        for(int i=12;i<17;i++){
            textFields[i].setText("");
        }
        for(int i=18;i<23;i++){
            textFields[i].setText("");
        }
    }
    /**
    *
    * creates the labels
    * @return labels array
    */
    private JLabel[] createLabels() {
        JLabel labels[]=new JLabel[40];
        labels[0]=new JLabel("Total cost for the stay : ");
        labels[1]=new JLabel("The number of rooms  : ");
        labels[2]=new JLabel("First Name : ");
        labels[3]=new JLabel("Last name : ");
        labels[4]=new JLabel("Address : ");
        labels[5]=new JLabel("Number of children  : ");
        labels[6]=new JLabel("Number of adults : ");
        
        labels[7]=new JLabel("Reservation Number : ");
        labels[8]=new JLabel("Status : ");
        labels[9]=new JLabel("Total cost for the stay : ");
        labels[10]=new JLabel("Room type : ");
        labels[11]=new JLabel("Guest Info : ");
        labels[12]=new JLabel("First Name : ");
        labels[13]=new JLabel("Last name : ");
        labels[14]=new JLabel("Address : ");
        labels[15]=new JLabel("Number of children  : ");
        labels[16]=new JLabel("Number of adults : ");
        labels[17]=new JLabel("Room : ");
        labels[18]=new JLabel("Capacity : ");
        labels[19]=new JLabel("Room type : ");
        labels[20]=new JLabel("Has micro fridge : ");
        labels[21]=new JLabel("Average Nightly price : ");
        labels[22]=new JLabel("Is vacant : ");
        labels[23]=new JLabel("Is Booked : ");
        
        
        labels[24]=new JLabel("Reservation Number : ");
        labels[25]=new JLabel("Status : ");
        labels[26]=new JLabel("Total cost for the stay : ");
        labels[27]=new JLabel("Room type : ");
        labels[28]=new JLabel("Guest Info : ");
        labels[29]=new JLabel("First Name : ");
        labels[30]=new JLabel("Last name : ");
        labels[31]=new JLabel("Address : ");
        labels[32]=new JLabel("Number of children  : ");
        labels[33]=new JLabel("Number of adults : ");
        labels[34]=new JLabel("Is Booked : ");
        labels[35]=new JLabel("Capacity : ");
        labels[36]=new JLabel("Room type : ");
        labels[37]=new JLabel("Has micro fridge : ");
        labels[38]=new JLabel("Average Nightly price : ");
        labels[39]=new JLabel("Is vacant : ");
        return labels;
        }
    /**
    *
    * creates the text fields
    * @return textFields array
    */
    private JTextField[] createTextFields() {
        JTextField textFields[]=new JTextField[40];

        for(int i=0;i<textFields.length;i++){
            textFields[i]=new JTextField(8);
        }
        for(int i=2;i<7;i++){
            textFields[i].setFocusTraversalKeysEnabled(true);
            textFields[i].addKeyListener(new focusKeyAdapter());
            
        }
        
        return textFields;
        }
    
    /**
    *
    * creates the buttons
    * @return the button
    */
    private JButton createButton() {
        //availButton=new JButton("Check availabilty");
        availButton=new JButton("Check total price");
        availButton.setPreferredSize(new Dimension(150,25));
        
        checkInButton = new JButton("Check In");
        checkInButton.addActionListener(new CheckInOutListener());
        checkOutButton=new JButton("Check out");
        checkOutButton.addActionListener(new CheckInOutListener());
        
        nextButton=new JButton("Next");
        nextButton.addActionListener(new nextPrevListener()); 
        prevButton=new JButton("Previous");
        prevButton.addActionListener(new nextPrevListener());
        reserveButton=new JButton("Create Reservation");
        reserveButton.addActionListener(new reservationListener());
//        reserveButton.addKeyListener(new myKeyAdapter());
        newReserveButton=new JButton("New Reservation");
        newReserveButton.addActionListener(new newReservationListener());
//        newReserveButton.addKeyListener(new myKeyAdapter());
        JButton button=new JButton("Calculate expenses");
        
        return button;
        }
    
    /**
    *
    * creates the Button listener
    * 
    */
     private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            enableReservationsPanel(true);

            
            for(int i=0;i<roomTypes.length;i++){
             if(roomTypes[i].isSelected()){
                 if(doneReservation==true){
                     hotel.bookHotelRoom();
                     doneReservation=false;
                 }
//                 JOptionPane.showMessageDialog(null,"The selected room type is : "
//                 +roomTypes[i].getText());
                 
                 
                 try{
                     hotel.createReservation(i+1);

//                    if(hotel.getCurrReservation().getTotalCostForTheStay()==0){
//                        JOptionPane.showMessageDialog(null,"The "+
//                                roomTypes[i].getText()+" is not available." );
//                        doneReservation=false;
//                        return;
//                    }
//                    else{
                        
                        JOptionPane.showMessageDialog(null,
                                "Please select the number of nights to stay.");
                        txtNumNights.setEditable(true);
                        txtNumNights.setEnabled(true);
                        txtNumNights.requestFocusInWindow();
                        numNights.setEnabled(true);
                        labels[0].setEnabled(true);

//                    }
                 
                 }
                 catch(NoVacancyException ex){
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                     doneReservation=false;
                     return;
                 }
                 
                 
             }
         }
         
        if(hotel.getCurrReservation()==null) {
            JOptionPane.showMessageDialog(null,"Please select the room type you want.");
        }
        else{
//        JOptionPane.showMessageDialog(null,"Current reservation : "+
//                hotel.getCurrReservation());
                     return;
        }
        }
        
     }
     /**
    *
    * creates the Button listener
    * 
    */
     private class reservationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        boolean flag=true;
        for(int i=2;i<5;i++){
            String txt=textFields[i].getText();
            if(txt.isEmpty()){
                flag=false;
                JOptionPane.showMessageDialog(null, "Please enter data in "
                + labels[i].getText());
                return;
            }
            
        }
        int totalPartySize=0,numChild=0,numAdults=0;
        
        try{
           numChild=Integer.parseInt(textFields[5].getText());
           if(numChild<0){
               JOptionPane.showMessageDialog(null,"The number of children cannot be less"
                       + "than zero. Please enter a positive value.");
              return; 
           }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Invalid data in "
                + labels[5].getText());
            JOptionPane.showMessageDialog(null, "Please enter data in "
                + labels[5].getText());
            return;
        }
        try{
           numAdults=Integer.parseInt(textFields[6].getText());
           if(numAdults<0){
               JOptionPane.showMessageDialog(null,"The number of adults cannot be less"
                       + "than zero. Please enter a positive value.");
               
           }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Invalid data in "
                + labels[6].getText());
            JOptionPane.showMessageDialog(null, "Please enter data in "
                + labels[6].getText());
            return;
        }
        try{
            
            hotel.createGuest(        
                    textFields[2].getText(),
                    textFields[3].getText(),
                    textFields[4].getText(),
             numChild,numAdults);
     
            //HotelRoom room=hotel.createRoom(hotel.getCurrReservation().getRoomType());
            //hotel.getCurrReservation().setRoom(room);
        
        }catch(OverCapacityException  ex){
            doneReservation=false;   
            JOptionPane.showMessageDialog(null,ex.getMessage());
            return; 
        }

//        JOptionPane.showMessageDialog(null, "currReservation :"+
//                hotel.getCurrReservation());
        
        hotel.getCurrReservation().setStatus(Reservation.Status.NOT_CHECKED_IN);
                
        hotel.getReservations().add(hotel.getCurrReservation());
        doneReservation=true;
        JOptionPane.showMessageDialog(null,"The room is booked successfully.");
        populateReservationsView(hotel.getCurrReservation());
        
        for(int i=0;i<roomTypes.length;i++){
            roomTypes[i].setEnabled(false);
        }

        enableReservationsPanel(false);
        
     }
     }
     /**
    *
    * method to write the reservations data to the "hotelRooms.txt" file
    * 
    */
     private void writeHotelDataToFile(){
         String fileName="hotelRooms.txt";
         ObjectOutputStream outFile=null;
         try{

             outFile=new ObjectOutputStream(
             new FileOutputStream(new File(fileName)));
             
             
             try{
                for(int i=0;i<hotel.getReservations().size();i++){
                    outFile.writeObject(hotel.getReservations().get(i));
                }
                outFile.close();
            }
            catch(IOException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());

            }
         }catch(IOException ex){
             JOptionPane.showMessageDialog(null,fileName+" not found."
                     +ex.getMessage());
             
         }
         
         
     }
     /**
    *
    * method to read the reservations data from the "hotelRooms.txt" file
    * 
    */
      private void readHotelDataFromFile(){
         String fileName="hotelRooms.txt";
         ObjectInputStream inputFile=null;
         try{

             inputFile=new ObjectInputStream(
             new FileInputStream(new File(fileName)));
         
         }catch(IOException ex){
             JOptionPane.showMessageDialog(null,fileName+" not found."+
                     ex.getMessage());
             return;
             
         }
         boolean flag=true;
         
             
         
         while(flag){
             
         
         try{
         
             hotel.getReservations().add((Reservation)inputFile.readObject());
//             JOptionPane.showMessageDialog(null,hotel.getReservations().get(
//                     hotel.getReservations().size()-1));
         
         
         }catch(EOFException ex){
             flag=false;
             
         }
         catch(ClassNotFoundException ex){
             JOptionPane.showMessageDialog(null,ex.getMessage());
             flag=false;
         }
         catch(IOException ex){
             JOptionPane.showMessageDialog(null,ex.getMessage());
             flag=false;
         }
         
         
         }
         try{
             if(inputFile!= null){
             inputFile.close();
             }
         }
         catch(IOException ex){
             JOptionPane.showMessageDialog(null,ex.getMessage());
         }
         
     }
    /**
    *
    * method to populate the check in and check out panel with reservation data
    * @param reservation the reservation data which is used to populate the 
    * check in and check out panel.
    * 
    */
      private void populateCheckInOutPanel(Reservation reservation){
        textFields[7].setText(""+reservation.getReservationNumber());
        textFields[8].setText(""+reservation.getStatus());
        textFields[9].setText("$"+reservation.getTotalCostForTheStay());
        textFields[10].setText(""+reservation.getRoomType());
        
        //guest Information
        textFields[12].setText(""+reservation.getGuest().getFirstName());
        textFields[13].setText(""+reservation.getGuest().getLastName());
        textFields[14].setText(""+reservation.getGuest().getAddress());
        textFields[15].setText(""+reservation.getGuest().getNumberChildrenInParty());
        textFields[16].setText(""+reservation.getGuest().getNumberAdultsInParty());
        
        //Room Information
        HotelRoom r=reservation.getRoom();
        if(r==null){
            for(int i=18;i<24;i++){
                textFields[i].setText("");
            }
            return;
        }
        textFields[18].setText(""+reservation.getRoom().getCapacity());
        textFields[19].setText(""+reservation.getRoomType());
        textFields[20].setText(""+reservation.getRoom().getHasMicroFridge());
        textFields[21].setText("$"+reservation.getRoom().getAverageNightlyPrice());
        textFields[22].setText(""+reservation.getRoom().getIsVacant());
        textFields[23].setText(""+reservation.getRoom().getIsBooked());
     }
    /**
    *
    * method to populate the reservation panel with reservation data
    * @param reservation the reservation data which is used to populate the 
    * reservation panel.
    * 
    */
     private void populateReservationsView(Reservation reservation){
        textFields[24].setText(""+reservation.getReservationNumber());
        textFields[25].setText(""+reservation.getStatus());
        textFields[26].setText("$"+reservation.getTotalCostForTheStay());
        textFields[27].setText(""+reservation.getRoomType());
        
        //guest Information
        textFields[29].setText(""+reservation.getGuest().getFirstName());
        textFields[30].setText(""+reservation.getGuest().getLastName());
        textFields[31].setText(""+reservation.getGuest().getAddress());
        textFields[32].setText(""+reservation.getGuest().getNumberChildrenInParty());
        textFields[33].setText(""+reservation.getGuest().getNumberAdultsInParty());
        
        //Room Information
        HotelRoom r=reservation.getRoom();
        if(r==null){
            for(int i=34;i<40;i++){
                textFields[i].setText("");
            }
            return;
        }
        textFields[35].setText(""+reservation.getRoom().getCapacity());
        textFields[36].setText(""+reservation.getRoomType());
        textFields[37].setText(""+reservation.getRoom().getHasMicroFridge());
        textFields[38].setText("$"+reservation.getRoom().getAverageNightlyPrice());
        textFields[39].setText(""+reservation.getRoom().getIsVacant());
        textFields[34].setText(""+reservation.getRoom().getIsBooked());
     }
     /**
    *
    * method to enable or disable reservations panel
    * @param value a boolean value to enable or disable the 
    * reservations panel
    */
     private void enableReservationsPanel(boolean value){
         
         for(int i=0;i<roomTypes.length;i++){
             roomTypes[i].setEnabled(value);
             

            }
            availButton.setEnabled(value);
            
            numNights.setEnabled(false);
            txtNumNights.setEditable(false);
            txtNumNights.setEnabled(false);
            txtNumNights.setText("");
            
            labels[0].setEnabled(false);
            textFields[0].setText("");
            textFields[0].setEditable(false);

            reserveButton.setEnabled(false);
            reserveButton.setEnabled(false);
            enableGuestInfoPanel(false);
     }
     /**
    *
    * creates the Button listener
    * 
    */
     private class newReservationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            enableReservationsPanel(true);

        
        }
        
     }
            
    /**
     *
    * creates the Button listener
    * 
    */
     private class nextPrevListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        ArrayList<Reservation> reservations=hotel.getReservations();
        if(reservations.isEmpty()){
            return;
        }
        int reservationNum;
        try{
         reservationNum=Integer.parseInt(textFields[24].getText());
         for(int i=0;i<reservations.size();i++){
             if(reservations.get(i).getReservationNumber()==reservationNum){
                 currIndex=i;
             }
         }
         
        }catch(Exception ex){
          JOptionPane.showMessageDialog(null,ex.getMessage());
          return;
        }
        if(e.getSource()==nextButton){
            
            
            if(currIndex>=0 && currIndex < reservations.size()){
                if(currIndex<reservations.size()-1){
                    currIndex++;
                }
                populateReservationsView(reservations.get(currIndex));
                
            }
            
        }
        if(e.getSource()==prevButton){
            
            
            if(currIndex>=0 && currIndex < reservations.size()){
                if(currIndex>=1){
                    currIndex--;
                }
                populateReservationsView(reservations.get(currIndex));
                
            }
            
        }
        }
     }
     /**
     *
    * creates the Button listener
    * 
    */
     private class CheckInOutListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        //JOptionPane.showMessageDialog(null, "Key Released");
            int iValue;
            if(e.getSource()==checkInButton){
                //if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    String inputTxt=textFields[7].getText();
                    try{
                        int num=Integer.parseInt(inputTxt);
                        iValue=hotel.checkIn(num);
                        if(iValue==-1){
                        JOptionPane.showMessageDialog(null, "The reservation with reservation number "+
                                num+" doesn't exists.");
                        
                        }else if(iValue==0){
                            ArrayList<Reservation> reservations=hotel.getReservations();
                            Reservation reservation=null;
                            for(int i=0;i<reservations.size();i++){
                                if(reservations.get(i).getReservationNumber()==num){
                                    reservation=reservations.get(i);
                                    break;
                                }                            
                            }
                            if(reservation!= null){
                                populateCheckInOutPanel(reservation);
                                populateReservationsView(reservation);
                            }
                            JOptionPane.showMessageDialog(null, "The guest with registration number "+
                                    num+" is successfully checked in.");
                            
                        
                        }else if(iValue==1){
                            JOptionPane.showMessageDialog(null, "The guest with registration number "+
                                    num+" is already checked in.");
                        }
                        else if(iValue==-2){
                            JOptionPane.showMessageDialog(null, "The guest with registration number "+
                                    num+" is already checked in and checked out."+
                                    " No more checking in or checking out.");
                        }else if(iValue==-3){
                            JOptionPane.showMessageDialog(null, "The room "+
//                                roomTypes[i].getText()
                                    " is not available yet." );
                        }
                    
                     //JOptionPane.showMessageDialog(null, "Please enter guest information.");
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null,
                                "Invalid data for reservation number."+
                                        "\""+inputTxt+ "\" is given.");
                    }

//                JOptionPane.showMessageDialog(null,"Key Adapter :Current reservation : "+
//                currReservation);
                     return;
                }
                
        if(e.getSource()==checkOutButton){
                
            String inputTxt=textFields[7].getText();
            try{
                int num=Integer.parseInt(inputTxt);
                iValue=hotel.checkOut(num);
                if(iValue==-1){
                        JOptionPane.showMessageDialog(null, "The reservation with reservation number "+
                                num+" doesn't exists.");
                        
                    }else if(iValue==0){
                        
                        ArrayList<Reservation> reservations=hotel.getReservations();
                        Reservation reservation=null;
                        for(int i=0;i<reservations.size();i++){
                            if(reservations.get(i).getReservationNumber()==num){
                                reservation=reservations.get(i);
                                break;
                            }                            
                        }
                        if(reservation!= null){
                            populateCheckInOutPanel(reservation);
                            populateReservationsView(reservation);
                        }
                        JOptionPane.showMessageDialog(null, "The guest with registration number "+
                            num+" is successfully checked out.");
                        

                        
                    }else if(iValue==-2){
                        JOptionPane.showMessageDialog(null,"The guest with registration number "+
                                num+" is not checked in yet.");
                        JOptionPane.showMessageDialog(null,"Please do check in first.");
                    }else if(iValue==-3){
                        JOptionPane.showMessageDialog(null, "The guest with registration number "+
                                    num+" is already checked in and checked out."+
                                    " No more checking in or checking out.");
                    }

             
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null,
                        "Invalid data for reservation number."+
                                "\""+inputTxt+ "\" is given.");
            }


             return;
        }
            
    }
        
}
     /**
    *
    * creates the key adapter
    * 
    */
    private class myKeyAdapter extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e)
        {   
//            if(e.getKeyCode()==KeyEvent.VK_ENTER){
//                if(e.getSource()== reserveButton){
//                    newReserveButton.requestFocusInWindow();
//                    reserveButton.doClick();
//                    reserveButton.setEnabled(false);
//                    
//                    return;
//                }
//                
//            }
//            if(e.getKeyCode()==KeyEvent.VK_ENTER){
//                if(e.getSource()== newReserveButton){
//                    availButton.requestFocusInWindow();
//                    newReserveButton.doClick();
//                    
//                    return;
//                }
//            }
            if(e.getKeyCode()==KeyEvent.VK_ENTER){
             if(e.getSource()== txtNumNights){
              if(txtNumNights.isEnabled()){
                     
                 
                String inputTxt=txtNumNights.getText();
                try{
                 int numNights=Integer.parseInt(inputTxt);
                 if(numNights<=0){
                     JOptionPane.showMessageDialog(null,"The number of nights must be greater than 0."
                             + "Please re enter the number of nights.");
                     txtNumNights.setText("");
                     textFields[0].setText("");
                     return;
                 }
                 double price =hotel.getCurrReservation().getTotalCostForTheStay()*numNights;
                 textFields[0].setText(""+price);
                 

                 hotel.getCurrReservation().setTotalCostForTheStay(price);
                 
                 enableGuestInfoPanel(true);
                 reserveButton.setEnabled(true);
                 
                 JOptionPane.showMessageDialog(null, "Please enter guest information.");
                 //textFields[2].requestFocusInWindow(); 
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,
                            "Invalid data for number of nights."+
                                    "\""+inputTxt+ "\" is given.");
                }
             
//            JOptionPane.showMessageDialog(null,
//                    "Key Adapter :Current reservation : "+
//            hotel.getCurrReservation());
            textFields[2].requestFocusInWindow();
                 return;
            }
            }   
            }
            
        }
        
    }
    /**
    *
    * creates the key adapter
    * 
    */
    private class focusKeyAdapter extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e)
        {   
            if(e.getSource()== textFields[6]){
               reserveButton.requestFocusInWindow(); 
               return;
            }
            for(int i=2;i<6;i++){
                if(e.getSource()== textFields[i]){
                   if(e.getKeyCode()==KeyEvent.VK_ENTER){
                        textFields[i+1].requestFocusInWindow(); 
                   }


                }
            }
        }
            
    }
    /**
    *
    * creates the key adapter
    * 
    */
    private class checkInOutKeyAdapter extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e)
        {   
            
            int iValue;
            
            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                String inputTxt=textFields[7].getText();
                try{
                    int num=Integer.parseInt(inputTxt);
                    ArrayList<Reservation> reservations=hotel.getReservations();
                    Reservation reservation=null;
                    for(int i=0;i<reservations.size();i++){
                        if(reservations.get(i).getReservationNumber()==num){
                            reservation=reservations.get(i);
                            break;
                        }                            
                    }
                    if(reservation!= null){
                        populateCheckInOutPanel(reservation);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "The reservation with reservation number "+
                            num+" doesn't exists.");
                        clearCheckInOutPanel();
                    }

                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,
                            "Invalid data for reservation number."+
                                    "\""+inputTxt+ "\" is given.");
                }

                return;
            }
         
        }
        
    }
    /**
    *
    * creates the window adapter
    * 
    */
    private class myWindowAdapter extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent windowEvent)
        {
            writeHotelDataToFile();
        //JOptionPane.showMessageDialog(null,"myWindowAdapter : window closing");
        }
        @Override
        public void windowOpened(WindowEvent windowEvent)
        {   if(hotel.getReservations().size()>0){
                 populateReservationsView(hotel.getReservations().get(0));
            }

        }
        
    }
}
