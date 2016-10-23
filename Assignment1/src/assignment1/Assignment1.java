
package assignment1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author c3165020 - Robert Logan
 */
public class Assignment1 {
    
private static Clustering c;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        c = new Clustering();
        try{
            Scanner src = new Scanner(new File("src/assignment1/"+args[0]));
            //read in the hotspot points and put them into a structure
            while(src.hasNextLine()){
                Hotspot h = new Hotspot();
                h.setName(Integer.toString(src.nextInt()));
                h.setX(src.nextInt());
                h.setY(src.nextInt());
                if(!c.listAdd(h)){
                    System.out.println("Error adding hotspot.");
                }
            }
            c.run();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
}
