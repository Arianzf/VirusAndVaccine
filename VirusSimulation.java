import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.*;


public class VirusSimulation extends JPanel implements ActionListener{
    
    //store multiple cells 
    ArrayList<Cells> CellsGroup = new ArrayList<Cells>(); //the moving cells objects (circles)
    // store multiple vaccine cells
    ArrayList<Vaccine> VaccineGroup = new ArrayList<Vaccine>(); //vaccine objects list
    
    boolean run = true;
    int sec = 30;

    //vaccine collision
    public void colli(Vaccine p2,Cells cl) {
        //Represent the circle objects asa Rectangles for simple collision detection
        Rectangle per1 = new Rectangle(p2.x,p2.y, 26,26);
        Rectangle per2 = new Rectangle(cl.x,cl.y, 26,26);
        
        //collision check
        if(per1.intersects(per2)) {
            //checks between infected cell and vaccine
            if(cl.status==2 && p2.status==1) { //case cell 1 is infected and cell 2 is not
                p2.status = 2;
            }else if(cl.status==1 && p2.status==2) { //case cell 2 is infected and cell 1 is
                cl.status = 2;
             }            
        }

    }
    
    //runner for the simulation 
    public static void main(String[] arg) {
        VirusSimulation c = new VirusSimulation();
    }
    
    /* paint method for drawing the simulation and animation */
    public void paint(Graphics g) {
        
        super.paintComponent(g); 
        
        //paint the cell objects
        for(Cells p: CellsGroup) {
            p.paint(g); 
        }
        
        //paint Vaccine objects
        for (Vaccine vc: VaccineGroup){
            vc.paint(g);
        }
        
        //check for collision by generating unique pairs of cells
        for(int i =0; i < CellsGroup.size();i++) {
            for(int j = i+1 ; j < CellsGroup.size();j++){
                //for each unique pair invoke the collision detection code
                CellsGroup.get(i).collision(CellsGroup.get(j));
            }
        }
        
           //check colision of Virus and vaccine
           for (int l=0; l<20;l++){
           for(int i =0; i < CellsGroup.size();i++) {
                 for(int j =1; j < VaccineGroup.size();j++) {
                colli(VaccineGroup.get(j),CellsGroup.get(i));
            }
           }
           }
    }
     /* constructor will setup our main Graphic User Interface - a simple Frame! */
     public VirusSimulation() {   
         
        //Setup the GUI
        JFrame frame = new JFrame("VirusSimulation");
        frame.setSize(800,600); //set the size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //setup the cell objects in the list
        for(int i = 0; i < 20; i++) {
            //instantiate an cell object and add it to the ArrayList
            //this is the part that actually CREATES objects we can use
            CellsGroup.add(new Cells());
        }
        
        
        
        //Timer for animation
         Timer t = new Timer(sec, this); // is the period in ms. 
                           //second argument (parameter) can be any class that implements
                        // ActionListener
                        
        t.restart(); //restart or start
        
        
       
        //Buttons
        JMenuBar mb = new JMenuBar();;
        JMenu m1 = new JMenu("SPEED");
        JMenu m2 = new JMenu("CURE / ADD VIRUSE");
        JMenu m3 = new JMenu("STOP/RESUME");
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        
        JMenuItem m11 = new JMenuItem("High");
        JMenuItem m00 = new JMenuItem("Medium");
        JMenuItem m22 = new JMenuItem("Low");
        m1.add(m11);
        m1.add(m00);
        m1.add(m22);
        
        JMenuItem vaccine = new JMenuItem("INJECT  VACCINE");
        m2.add(vaccine);
        JMenuItem addvirus = new JMenuItem("ADD  VIRUS");
        m2.add(addvirus);
        
        JMenuItem stopb = new JMenuItem("STOP");
        JMenuItem resumeb = new JMenuItem("RESUME");
        m3.add(stopb);
        m3.add(resumeb);
        
        frame.add(mb);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        
        //stop animation when stop button is pressed
        stopb.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
         run = false;
         
        }
        });
        //resume animation when resume button is pressed
        resumeb.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
         run = true;
         
        }
        });
        
        //low button
        m22.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
         
          for(Cells p: CellsGroup) {
              p.vy = p.vx - 2;
              p.vy = p.vy - 2;
            }
        }
        });
        
        // medium button
        m00.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
         
          for(Cells p: CellsGroup) {
              p.vy = p.vx + 5;
              p.vy = p.vy + 5;
            }
        }
        });
        
        //High button
        m11.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
         
          for(Cells p: CellsGroup) {
              p.vy = p.vx + 20;
              p.vy = p.vy + 20;
            }
        }
        });
        
        //inject vaccine button
        vaccine.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
         for(int i = 0; i < 2; i++) {
            //instantiate an cell object and add it to the ArrayList
            //this is the part that actually CREATES objects we can use
            VaccineGroup.add(new Vaccine());
        }
         
        }
        });
        
        //add more viruses
        addvirus.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
         for(int i = 0; i < 5; i++) {
            CellsGroup.add(new Cells());
        }
         
        }
        });
        
        
        
        
        
        
        //make it visible
        frame.add(this); 
        frame.setVisible(true);
        
     }

    
    /* This runs every 30ms based on the timer */
     @Override
     public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (run){
         repaint();
     }
    
    }
}