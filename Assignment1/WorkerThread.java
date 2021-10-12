package Assignment1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WorkerThread extends Master{

    public String patt = "V04K4B63CL5BK0B"; //patern
    public int vunPatSize = patt.length(); //size of pattern
    public int wID;
    public LevenshteinDistance leveinshteinDist = new LevenshteinDistance();
    public static int vunCount = 0;

            public WorkerThread (int wID){ //inherit pattern from Master
                this.wID =wID;
            }

            @Override
            public void run(){ //Run method to see if the two strings are the same or 5% similar
                synchronized(System.out) { //2 concurrent threads that share data but won't override eachother
                        compare();
                        if (leveinshteinDist.isAcceptable_change()) {
                            System.out.println("Vunerability pattern has been found at line: " + this.wID + " " + Thread.currentThread().getName());
                            System.out.println();
                            vunCount++;
                            setCountVulnerabilities(vunCount);
                    }
                }
            }

            public void compare(){
                /**for loop that runs the logs from i=0 to i < logs.size - vunPatSize with i++
                for loop that runs from j=i to j< to j < i + vunPatSize with j++
                puts the char into an array that puts into a string**/
            }
    }
