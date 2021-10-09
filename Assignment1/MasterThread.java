package Assignment1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MasterThread {

    public static void main (String [] args) throws FileNotFoundException
    {
        File file = new File ("C:/Users/genet/Desktop/vm_1.txt");
        Scanner scan = new Scanner(file); 

        System.out.println(scan.nextLine());

    }
    
}
