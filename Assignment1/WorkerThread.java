package Assignment1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WorkerThread extends Master{

    public String patt = "V04K4B63CL5BK0B"; //patern
    public int patternsize = patt.length(); //size of pattern
    public int workerID;
    public LevenshteinDistance leveinshteinDist = new LevenshteinDistance();
    public static int vunCount = 0;

            public WorkerThread (int workerID){ //inherit pattern from Master
                this.workerID =workerID;
            }

            @Override
            public void run(){ //Run method to see if the two strings are the same or 5% similar
                synchronized(System.out) { //2 concurrent threads that share data but won't override eachother
                        compare();
                        if (leveinshteinDist.isAcceptable_change()) {
                            System.out.println("Vulnerability pattern found at line: " + this.workerID + " " + Thread.currentThread().getName());
                            System.out.println();
                            vunCount++;
                            setCountVulnerabilities(vunCount);
                    }
                }
            }

            public void compare(){
                /** We are not quite sure how to figure the comparing, we have done 2 for loops with i and j but it is not working and figured we did not know how to implement this section **/
            }
    }
