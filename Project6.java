/** *************************************************************
 *
 * Project Number 6 - Comp Sci 182 - Data Structures
 * Start Code - Build your program starting with this code
 *
 * Copyright 2005,2016 Christopher C. Ferguson
 * This code may only be used with the permission of Christopher C. Ferguson
 *
 ************************************************************** */
package project6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Project6 extends JFrame implements ActionListener {

    private static int win_xpos = 0, win_ypos = 0;// place window here
    private static int win_xsize = 700, win_ysize = 500;//  window size

// Private state variables.
    private Font boldfont = new Font("TimesRoman", Font.BOLD, 18);
    private Font plainfont = new Font("TimesRoman", Font.PLAIN, 12);

    private HashTable hash;
    private JButton hashbutton, exitbutton;
    private JPanel northPanel;
    private MyJPanel centerPanel;
    private JTextField hashsizefield;
    private String thetext = "101";
    private String[] names = {"fred", "barney", "tom", "jerry", "larry", "moe", "curly",
        "betty", "wilma", "bart", "homer", "marge", "maggie", "lisa",
        "pebbles", "bambam", "smithers", "burns", "milhouse", "george", "astro",
        "dino", "mickey", "minnie", "pluto", "goofy", "donald", "huey",
        "louie", "dewey", "snowwhite", "happy", "doc", "grumpy", "sneezy",
        "dopey", "sleepy", "bambi", "belle", "gaston", "tarzan", "jane",
        "simba", "scar", "mufasa", "ariel", "flounder", "bugs", "daffy",
        "elmer", "foghorn", "chickenhawk", "roger", "jessica", "hank", "bobby",
        "peggy", "spot", "pongo", "perdy", "buzz", "potatohead", "woody",
        "chuckie", "tommy", "phil", "lil", "angelica", "dill", "spike",
        "pepe", "speedy", "yosemite", "sam", "tweety", "sylvester", "granny",
        "spiderman", "batman", "superman", "supergirl", "robin", "jimmy", "olsen",
        "thing", "flash", "silversurfer", "xmen", "pokemon", "joker", "wonderwoman"
    };

////////////MAIN////////////////////////
    public static void main(String[] args) {
        Project6 tpo = new Project6();

        tpo.addWindowListener(new WindowAdapter() {   // this exits the program when X box clicked
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

////////////CONSTRUCTOR/////////////////////
    public Project6() {
        northPanel = new JPanel();
        northPanel.add(new Label("Enter a hashtable size: "));
        hashsizefield = new JTextField(thetext, 20);
        northPanel.add(hashsizefield);
        hashbutton = new JButton("CreateHash");
        northPanel.add(hashbutton);
        hashbutton.addActionListener(this);
        exitbutton = new JButton("Exit");
        northPanel.add(exitbutton);
        exitbutton.addActionListener(this);
        getContentPane().add("North", northPanel);
        centerPanel = new MyJPanel();
        getContentPane().add("Center", centerPanel);

        // need more init stuff? try here.
        setSize(win_xsize, win_ysize);
        setLocation(win_xpos, win_ypos);
        setVisible(true);
    }

////////////BUTTON CLICKS ///////////////////////////
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitbutton) {
            dispose();
            System.exit(0);
        }

        if (e.getSource() == hashbutton) {
            thetext = hashsizefield.getText();
            try {
                int size = Integer.parseInt(thetext);
                hash = new HashTable(size);// new hash table based on user input
                for(String s : names){
                    // tries to create and fill a new hastable with names
                    DataItem item = new DataItem(s);
                    hash.insert(item);
                }
                
            } catch (Exception E) {
                thetext = "Warning! Invalid hash size.";
            }
            
            repaint();
        }
    } // end actionPerformed

    class MyJPanel extends JPanel {

        ////////////    PAINT   ////////////////////////////////
        public void paintComponent(Graphics g) {
            int count = 0;
            int height = 30;
            int width = 20;
            g.setFont(plainfont);
            g.drawString("I am paint, field contains " + thetext, width, height);
            height+=20;
            for(String s : names){
                    int found= hash.found(s);
                    int expected = hash.hashFunc3(s);
                    if( found == -1){
                        throw new RuntimeException("name not found in hashtable");
                    }else if(found != expected){
                         g.drawString("Hash Crash: " + s,  width, height);
                         g.drawString("should be at         " + expected,  width+150, height);
                         g.drawString("found at         " + found,  width+275, height);
                                    height+=20;
                                    count++;

                    }
                    
                }
            g.drawString("Hash Crash count is " + count,  width, height);
            height+=10;
            

        }
    } // End Of MyJPanel

}     // End Of Project6
