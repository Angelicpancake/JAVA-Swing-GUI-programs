//*********************************************************************************************************************************
// Name: Joshua Ren
// Period: 6                                      
// Date: 5/29/2024
// What I learned: I learned multiple things from this lab.
//    1. I learned how to disable and re-enable buttons 
//    2. I learned utilizing multiple 2d arrays to create a gui game
//    3. Event handling in swing java
//    4. Handling differet conditions: win, loss, reset
//    5. Panel layouts 
//    6. User feedback in terms of jlabel displaying game status and torpedoes left
//    ...
// How I feel about this lab: I feel very accomplished to create a real legitimate game of battleship that can reset with Java
//   
// What I wonder: I wonder how would I add multiplayer game functionality

//***********************************************************************************************************************************


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BattleShipProgramJoshuaRenPD6 extends JPanel
{
   private JButton[][] board;  // front-end grid
   private int[][]matrix;      // back-end grid
   private int hits;           // keep track of # of times the ship got hit
   private int torpedoes;      // 
   private JLabel label;       // display number of torpedoes left (on north panel)
   private JButton reset;      // the reset button on the south panel
   
   public BattleShipProgramJoshuaRenPD6()
   {      
      setLayout(new BorderLayout());
      hits = 0;
      torpedoes = 20;
      
      // set up the north panel
      JPanel north = new JPanel();
      north.setLayout(new FlowLayout());
      add(north, BorderLayout.NORTH);
      label = new JLabel("You have 20 torpedoes");
      north.add(label);
      
      // set up the center panel and components on it
      JPanel center = new JPanel();
      center.setLayout(new GridLayout(10,10));
      add(center, BorderLayout.CENTER);
      board = new JButton[10][10];
      matrix = new int[10][10];
      
      for(int r=0;r<10;r++)
      {
         for(int c=0;c<10;c++)
         {
            matrix[r][c]=0;
            board[r][c]=new JButton();
            board[r][c].setBackground(Color.blue);
            board[r][c].addActionListener(new Handler1(r,c));
            center.add(board[r][c]);
         } // inner for
      } // outer for
      
      // set up the south panel
      reset = new JButton("Reset");
      reset.addActionListener(new Handler2());
      reset.setEnabled(true);
      add(reset,BorderLayout.SOUTH);
      
      // place battleship in a random location
      placeShip();
   }
   
   /*  Pre condition: n/a
    *  Post condition: placeShip method will have a 50% chance of placing a ship horizontally or vertically
    *                  then generates a valid inbound vertical or horizontal ship
    *                  placement storing the indices in the matrix attribute labeled as 1's and printing the 
    *                  initial starting index for testing purposes.
    */
   private void placeShip()
   {
      int coin = (int)(Math.random()*2 + 1); //initializes coin with the int value 1 or 2 both being an equal chance of obtaining
   
      int x; //col
      int y; //row
                  
      if (coin == 1) //if coin flips 1 the ship is vertically placed
      {
         x = (int)(Math.random()*matrix[0].length);
         y = (int)(Math.random()*(matrix.length-3));
         System.out.print("top pos [vertical]: " + "[" + y + "," + x + "]\n\n"); //print index for testing
         for (int i = 0; i < 4; i++)
         {
            matrix[y+i][x] = 1; //store the ships position in attribute matrix with value 1
         }         
      }
         
      else if (coin == 2) //if coin flips 2 the ship is horizontally placed
      {
         x = (int)(Math.random()*(matrix[0].length-3));
         y = (int)(Math.random()*matrix.length);
         System.out.print("top pos [horizontal]: " + "[" + y + "," + x + "]\n\n"); //print index for testing
         for (int i = 0; i < 4; i++)
         {
            matrix[y][x+i] = 1; //store the ships position in attribute matrix with value 1
         }   
      }       
   } //placeShip
   
   /*  Helper Method: disableButtons()
    *  Pre conditions: n/a
    *  Post conditoins: traverses through the attribute board setting enabled to false to
    *                   disable all buttons of board
    */
   public void disableButtons()
   {
      for (int r = 0; r < 10; r++)
         for (int c = 0; c < 10; c++)
            board[r][c].setEnabled(false);
            
   }//disableButtons

   private class Handler1 implements ActionListener
   {
      private int myRow,myCol; //keep track of which row and col user is pressing
    
      /*  Pre Condition: @param1 r and @param2 c must be valid indices ranging from [0-9]
       *  Post condition: initializes attributes myRow and myCol to their respective @params
       */
      public Handler1(int r, int c)
      {
         myRow=r;
         myCol=c;
      }
      
      /*  Pre condition: @param1 ActionEvent e must link to a defined component
       *  Post condition: Handles all the user interactions of the battleship game with subtracing, adding
       *                  torpedoes, clicking potential ship containing button indices that change color
       *                  depending on whether the row and col aligns with the matrix row and col, and 
       *                  updating the amount of hit spots of the ship containing indices to lead to either
       *                  a changed label of winning or losing then calling the helper method disableButtons to
       *                  disable the buttons of the board to prevent additional torpedo actions. Also changing
       *                  the button color to red of the ship indices when player loses.
       */
      public void actionPerformed(ActionEvent e)
      {  
         if (torpedoes > 0) //if the attribute torpedoes is more than 0
         {
            torpedoes -= 1; //subtract a torpedo when used
            label.setText("You have " + torpedoes + " torpedoes"); //change label of total torpedoes after using
            board[myRow][myCol].setEnabled(false); //disable the button after clicked
            
            if (matrix[myRow][myCol] == 1) //if the board row and col aligns with the matrix row and col containing 1 => ship hit
            {
               board[myRow][myCol].setBackground(Color.GREEN); //change background to signify a hit with green background
               hits++; //update the ships hit attribute
            }
            else //board row and col aligns with matrix row and col containing 0 => ship miss
            {
               board[myRow][myCol].setBackground(Color.YELLOW); //change background to signify a hit with yellow background
            }
            
         }
         
         if (hits == 4) //if user has hit all of the ship : hits attribute is equal to 4
         {
            label.setText("User Wins!!"); //change label to display user winning
            disableButtons();
         } 
         else if (torpedoes == 0) //if user has used all of their torpedoes without hits equal to 4: lose
         {
            label.setText("User Loses!!"); //change label to display user losing
            for (int r = 0; r < matrix.length; r++)
            {
               for (int c = 0; c < matrix[0].length; c++)
               {
                  if (matrix[r][c] == 1)
                     board[r][c].setBackground(Color.RED); //when user loses show the ship indices with buttons displayed as red
               }
            }
            disableButtons();
         }
         
        //call disableButtons helper method to prevent user from interacting with board after winning
        
      } //actionPerformed
   } //Handler1
   
   //handles the reset button event on the south panel
   private class Handler2 implements ActionListener
   {
      /*  Pre condition: @param1 ActionEvent e must link to a defined component : reset button
       *  Post condition: Traverse through board settingEnabled to true and background of all buttons
       *                  to color blue and setting the matrix value of each row, col to 0, attributes to
       *                  initial starting values, in order to transition to a reset of game then calling 
       *                  placeShip to get a new ship placement.
       */
      public void actionPerformed(ActionEvent e)
      {
         for (int r = 0; r < 10; r++) //traverse through board array
         {
            for (int c = 0; c < 10; c++)
            {
               board[r][c].setEnabled(true); //changing interactions with button to be true
               board[r][c].setBackground(Color.BLUE); //changing background of button to blue
               matrix[r][c] = 0; //setting all indices of matrix to 0
            }
         }
      
         torpedoes = 20; //reset torpedo amount to 20
         hits = 0; //reset hits to 0
         label.setText("You have " + torpedoes + " torpedoes"); //change label to represent the new total reset value
         placeShip(); //call placeShip to obtain a new ship position 
         
      }
   }   // Handler2
   
   
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Battleship!");
      frame.setSize(400,400);
      frame.setLocation(200,100);
      
      // place the Battleship_shell JPanel in the content pane area of a JFrame
      frame.setContentPane(new BattleShipProgramJoshuaRenPD6());
      frame.setVisible(true);
         
   }  // main
}  // BattleShipProgramJoshuaRenPD6