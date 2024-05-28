/******************************************************************************************
Name: Joshua Ren 
Period: 6
Name of the Lab: ForFunctionCaculator || optional (bonus maybe :) )
Due Date: n/a
Date Submitted: 5/27/24
What I learned:
   a) Parsing double and string to add a value
   b) Utilizing multiple JButtons in a grid layout with different functionality.
   c) Using booleans to determine whether moderators are active like
      when a user presses division using a new value to divide by the old value
   d) Traversing through components to reduce redundancy, specifically traversing container
      which is a panel and the added components to set background to a new color
   e) Switching back and forth from a previous value to a new value then using both
      on caculations, for example, with the moderators a new text must be shown with
      different conditions to change the old value by the new value.
       
*******************************************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calculatorJoshuaRenPD6
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();
      frame.setSize(370,380);
      frame.setLocation(200, 100);
      frame.setContentPane(new Panel() );
      frame.setVisible(true);
   }//main
}//driver

class Panel extends JPanel
{
   private double value;
   private double preValue;
   
   private JButton label; //display value
   private JButton cbCE; //clear entry
   private JButton cbAC; //all clear
   private JButton cbSQRT; 
   private JButton cbDIV;
   private JButton cbSEVEN;
   private JButton cbEIGHT; 
   private JButton cbNINE;
   private JButton cbMULT;
   private JButton cbFOUR;
   private JButton cbFIVE;
   private JButton cbSIX;
   private JButton cbSUB;
   private JButton cbONE;
   private JButton cbTWO;
   private JButton cbTHREE;
   private JButton cbADD;
   private JButton cbOFF;
   private JButton cbZERO;
   private JButton cbPI;
   private JButton cbEQ;
   
   //operators
   boolean modActive = false;
   boolean equals = false;
   boolean divActive = false; 
   boolean multActive = false;
   boolean subActive = false;
   boolean addActive = false;
   
  /*  Pre condition: n/a
   *  Post condition: instantiate the componenets and add them to their respective panels.
   */
   public Panel()
   {
         
      JPanel north = new JPanel(new BorderLayout() );
      JPanel center = new JPanel(new GridLayout(5, 4) );
      
      setBackground(new Color(87, 87, 89)); //entire panel background to GRAY
      center.setBackground(new Color(214, 211, 224)); //center panel background to lighter GRAY
      center.setPreferredSize(new Dimension(300, 250)); //set size of center panel to 300, 250 dimensions
      
      add(north, BorderLayout.NORTH);
      add(center, BorderLayout.CENTER);
      
      label = new JButton("");
      label.setFont(new Font("Ariel", Font.PLAIN, 12));
      north.setPreferredSize(new Dimension(300, 60)); //set size of north button to 300, 60 dimensions
      north.add(label);
      
      //fill the grid layout panel with buttons on each grid index instantiating the attributes and adding actionListener
      cbCE = new JButton("CE"); center.add(cbCE); cbCE.addActionListener(new Listener() );
      cbAC = new JButton("AC"); center.add(cbAC); cbAC.addActionListener(new Listener() );
      cbSQRT = new JButton("SQRT"); center.add(cbSQRT); cbSQRT.addActionListener(new Listener() );
      cbDIV = new JButton("DIV (/)"); center.add(cbDIV); cbDIV.addActionListener(new Listener() );
      cbSEVEN = new JButton("7"); center.add(cbSEVEN); cbSEVEN.addActionListener(new Listener() );
      cbEIGHT = new JButton("8"); center.add(cbEIGHT); cbEIGHT.addActionListener(new Listener() );
      cbNINE = new JButton("9"); center.add(cbNINE); cbNINE.addActionListener(new Listener() );
      cbMULT = new JButton("Mult (*)"); center.add(cbMULT); cbMULT.addActionListener(new Listener() );
      cbFOUR = new JButton("4"); center.add(cbFOUR); cbFOUR.addActionListener(new Listener() );
      cbFIVE = new JButton("5"); center.add(cbFIVE); cbFIVE.addActionListener(new Listener() );
      cbSIX = new JButton("6"); center.add(cbSIX); cbSIX.addActionListener(new Listener() );
      cbSUB = new JButton("Sub (-)"); center.add(cbSUB); cbSUB.addActionListener(new Listener() );
      cbONE = new JButton("1"); center.add(cbONE); cbONE.addActionListener(new Listener() );
      cbTWO = new JButton("2"); center.add(cbTWO); cbTWO.addActionListener(new Listener() );
      cbTHREE = new JButton("3"); center.add(cbTHREE); cbTHREE.addActionListener(new Listener() );
      cbADD = new JButton("Add (+)"); center.add(cbADD); cbADD.addActionListener(new Listener() );
      cbOFF = new JButton("OFF"); center.add(cbOFF); cbOFF.addActionListener(new Listener() );
      cbZERO = new JButton("0"); center.add(cbZERO); cbZERO.addActionListener(new Listener() );
      cbPI = new JButton("PI"); center.add(cbPI); cbPI.addActionListener(new Listener() );
      cbEQ = new JButton("="); center.add(cbEQ); cbEQ.addActionListener(new Listener() );
         
      setButtonColor(center, new Color(78, 86, 125), Color.WHITE);
      //call setButtonColor to change color of all buttons on center panel
      
      Color c1 = new Color(252, 195, 109); //change functional (ac, ce, off) buttons to yellow
      cbCE.setBackground(c1);
      cbAC.setBackground(c1);
      cbEQ.setBackground(c1);
      cbOFF.setBackground(c1);
      
   } //panel
   
   private class Listener implements ActionListener
   {
     /* Pre conditions: @param1 ActionEvent e must link to a defined component
      * Post conditions: set the respective actions for each component:
      *                  number buttons: adding the digit to the end of the current value by parsing
      *                  operators: perform their respective operations (/, *, +, -) with a new value to the old value
      *     
      */
      public void actionPerformed(ActionEvent e)
      {
         /*
            when pressing an operator twice it acts like pressing equals just like a caculator
            when pressing an operator function it turns their booleanActive var to true and moderatoractive to true
               => this enables the different moderator active code with the new value to modify the old value
          */
         
         if (e.getSource() == cbCE) //if CE is selectd clear the current entry of value not preValue
         {
            label.setText("0");
            value = 0;
            
         }
         else if (e.getSource() == cbOFF) { System.exit(0); } //exits
         else if (e.getSource() == cbAC) //clear all entries pre value and value
         {
            value = 0;
            preValue = 0;
            label.setText("0");
         }
         else if (e.getSource() == cbSQRT) //sqrt by Power function of 0.5
         {
            value = Math.pow(value, 0.5);
            label.setText(value + "");
         }
         else if (e.getSource() == cbDIV)
         {
            if (modActive)equals = true;
            
            else  
            {
               label.setText("/");
               Switch();
               modActive = true;
               divActive = true;
            }
         }
         else if (e.getSource() == cbMULT)
         {
            if (modActive) equals = true;
            
            else
            {
               label.setText("*");
               Switch(); 
               modActive = true;
               multActive = true;
            }
         }
         else if (e.getSource() == cbSUB)
         {
            if (modActive) equals = true;
            
            else
            {
               label.setText("-");
               Switch();
               modActive = true;
               subActive = true;
            }
         }
         else if (e.getSource() == cbADD)
         {
            if (modActive) equals = true;
            else
            {
               label.setText("+");
               Switch();
               modActive = true;
               addActive = true;
            }
         }
         
         /*
            on a fourfunction caculator after pressing any moderator a new value appears to modify the previous value
            this is simulated in the code through a boolean modActive where it executes the operators when their conditions
            are met, for instance, when the new value is 0.0 meaning the user has not inputed any value to modify you cannot
            exit the modactive mode. Then when the condition is met when equals var is fufilled with a value greater than 0
            modify the previous value with the new value with the respective operators
         */ 
         if (modActive)
         {  
          
            if ((e.getSource() == cbEQ || equals) && value > 0) //when presing a mod with no new value no change waits for value
            {
               if (divActive)
               {
                  value = preValue / value;
                  divActive = false;
               }
               else if (multActive)
               {
                  value = preValue * value;
                  multActive = false;
               }
               else if (subActive)
               {
                  value = preValue - value;
                  subActive = false;
               }
               else if (addActive)
               {
                  value = preValue + value;
                  addActive = true;
               }
               label.setText(value + "");
               
               modActive = false;
            }//eq
         }//modActive
         
         /*
            inputs for the different add-ons buttons
            the add-on buttons are the digit 0-9 and pi values which are added to the value not literal addition but like a string concat
               this is simulated using the helper method addNum where using strings and parsing adds the value to the last place.
         */
         if (e.getSource() == cbSEVEN){ addNum("7"); }
         else if (e.getSource() == cbEIGHT){ addNum("8"); }
         else if (e.getSource() == cbNINE){ addNum("9"); }
         else if (e.getSource() == cbFOUR) { addNum("4"); }
         else if (e.getSource() == cbFIVE) { addNum("5"); }
         else if (e.getSource() == cbSIX) { addNum("6"); }
         else if (e.getSource() == cbONE) { addNum("1"); }
         else if (e.getSource() == cbTWO) { addNum("2"); }
         else if (e.getSource() == cbTHREE) {addNum("3"); }
         else if (e.getSource() == cbZERO) { addNum("0"); }
         else if (e.getSource() == cbPI) { addNum(Math.PI + ""); }
      } //actionperformed
      
     /* Pre condition: n/a
      * Post condition: set the preValue attribute to value and set value to 0
         this allows for the moderator active code
      */
      public void Switch() 
      {
         preValue = value;
         value = 0; //new value to add onto the pre value 
      }
     
     /* Pre condition: valid String that correlates to a number
      * Post condition: concat value with the String num then parse it to add num to last place like a caculator
      */
      public void addNum(String num)
      {
         int v = (int) value;
         
         if (value != 0.0)
            num = v + num;
            
         value = Double.parseDouble(num);
         label.setText(value + ""); //concat value with "" to change to string to set as labe
           
      }
   } //Listener
   
   /*  Helper Method:
    *  Pre conditions: @param1 must be a valid declared container, @param2 and @param3
    *                   must be a valid color. 
    *  Post conditions: traverse through the container and changing all button components
    *                   to bg background color and fg text color.
    */
   private void setButtonColor(Container c, Color bg, Color fg)
   {
      //traverse through the container obtaining all of its components
      for (Component a : c.getComponents())
      {
         if (a instanceof JButton) //if the component is a JButton
         {
            a.setBackground(bg); //change background color to bg
            a.setForeground(fg); //change textcolor to fg
         }
      }
   } //setButtonColor 
}