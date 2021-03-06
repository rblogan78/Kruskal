package assignment1;

/**
 *
 * @author c3165020 - Robert Logan
 */
public class Hotspot {
    private double xCoord;
    private double yCoord;
    private String name;
    private static int index = 0;
    
    public Hotspot(){
        xCoord = 0;
        yCoord = 0;
        name = "";
        index++;
    }
    
    public Hotspot(double x, double y, String n){
        xCoord = x;
        yCoord = y;
        name = n;
    }
    
    public double getX(){ return xCoord; }
    
    public double getY(){ return yCoord; }
    
    public String getName(){ return name; }
    
    public void setX(int x){ xCoord = x; }
    
    public void setY(int y){ yCoord = y; }
    
    public void setName(String n) { name = n; }
    
}
