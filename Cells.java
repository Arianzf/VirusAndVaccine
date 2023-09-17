import java.awt.*;

public class Cells {
    
    //location
    int x, y;
    
    //velocity
    int vx, vy;
    
    //status
    int status = 0;
    
    
    
    //Constructor for the cells objects
    public Cells() {
        
        //randomize the position of the cell
        x = (int)(Math.random()*790+0);
        y = (int)(Math.random()*590+0);
        
        //code to make the Cell objects infected 
        if(Math.random()<.5) {
            status = 1;
        }
        
 
        // the code below will run to give the objects a non-zero vx and vy(velocity)
        if(Math.random()<1) {
            vx  = (int)(Math.random()*(20+1)+-5);
            vy  = (int)(Math.random()*(20+1)+-5);
        }
        
      
        
    }
    
    /**
     * Collision between two cells objects for "infections"
     * If two cells objects collide they have a possibility of infecting!
     * @param p2
     */
    public void collision(Cells p2) {
        
        //Represent the circle objects asa Rectangles for simple collision detection
        Rectangle per1 = new Rectangle(p2.x,p2.y, 26,26);
        Rectangle per2 = new Rectangle(this.x,this.y, 26,26);
        
        //collision check
        if(per1.intersects(per2)) {
            //infection only happens if one cell is infected and the other has never
            //been infected before
            if(this.status==1 && p2.status==0) { //case cell 1 is infected and cell 1 is not
                p2.status = 1;
            }else if(this.status==0 && p2.status==1) { //case cell 2 is infected and cell 1 is
                this.status = 1;
             }            
        }

    }
        
    public void paint(Graphics g) {
        
        //set the color of cells
        switch(status) {
            case 0: //normal
                g.setColor(Color.LIGHT_GRAY);
                break;
            case 1: //infected
                g.setColor(Color.red);
                break;
            case 2: //recovered/healed
                g.setColor(Color.blue);
                
        }
        
        
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