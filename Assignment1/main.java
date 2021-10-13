
package Assignment1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class  main extends Thread {
    int Worker;
     

    public static void main (String [] args){
        Master master = new Master("C:\\Users\\chris\\OneDrive\\Desktop"); //Creates master thread
        master.start(); //run masterthread
    }
    
}
