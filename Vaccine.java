import java.awt.*;

public class Vaccine {
    
    //location
    int x, y;
    
    //velocity
    int vx, vy;
    
    //status
    int status = 2;
    
    //Constructor for the cells objects
    public Vaccine() {
        
        //randomize the position of the cell
        x = (int)(Math.random()*790+0);
        y = (int)(Math.random()*590+0);
        
 
        // the code below will run to give the objects a non-zero vx and vy(velocity)
        if(Math.random()<1) {
            vx  = (int)(Math.random()*(20+1)+-5);
            vy  = (int)(Math.random()*(20+1)+-5);
        }

    }
    
    /**
     * Collision between two cells objects for "curing infection"
     * If two cells objects collide they have a possibility of curing!
     * @param p2
     */
        
    public void paint(Graphics g) {
        
        //x and y components are updated based on their velocities
        x += vx;
        y += vy;
        
        //code to have the cell objects bounce off the borders
        if(x < 0 || x >= 790) {
            vx *= -1;
        }
        
        //bounce off the top and bottom
        if(y < 0 || y >= 590) {
            vy *= -1;
        }
        
        //draw the oval representign the cell object
        g.fillOval(x, y, 25, 25);
        
    }
    
    
    
    
    
    

    
    
    
    
}