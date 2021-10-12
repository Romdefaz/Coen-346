package Assignment1;
import java.io.*;
import java.util.ArrayList;

public class Master extends Thread {
// global variables initialization
    public String filename;
    public static ArrayList<String> logs = new ArrayList<String>(); //Usage of array to avoid constantly using bufferReader
    public int wCount; ////worker counter
    public static int countVulnerabilities; //vulnerability counter
    public int countLines; 
    public double approximateAvg;
    public double previousAvg;
    public static ArrayList<WorkerThread> w = new ArrayList<WorkerThread>();
    public static int currentWorkerNumber;


    public Master(){ //empty contructor to allow worker to have their own contructor

    }

    public Master(String filename){ // contructor to read the file and input it into the arraylist
        this.filename = filename;
        String line = null;
        String subStr = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            while ((line = in.readLine())!= null){
                subStr = getLogContent(line);
                logs.add(subStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        countLines = logs.size();
        this.approximateAvg = 0;
        this.previousAvg = 0;
        wCount = 0;
        currentWorkerNumber = 2;
    }


    public double calculateApproximateAvg(){ //calculate an approximate
            return (double) countVulnerabilities/countLines;
        }
    
    public void incrementWorkers(){ // increments worker by 2
            currentWorkerNumber += 2;
        }
    
    public void appendWorkers2(){ // adds 2 extra workers to the array and runs them
            incrementWorkers();
            w.clear();
            for(int i=0; i<currentWorkerNumber; i++){
                w.add(new WorkerThread(wCount));
                wCount++;
            }
            startWorkers();
        }
    
    public void appendWorkers(){  // create workers and runs them (updates the array)
            w.clear();
            for(int i=0; i<currentWorkerNumber; i++){
                w.add(new WorkerThread(wCount));
                wCount++;
            }
            startWorkers();
        }
    
    public void appendWorkers1stTime(){ // initial creation of the workers
            w.add(new WorkerThread(wCount));
            wCount++;
            w.add(new WorkerThread(wCount));
            wCount++;
            startWorkers();
        }
    
    public void startWorkers(){ // runs the workers
            for(WorkerThread w: w){
                w.start();
            }
        }

    public void setCountVulnerabilities(int countVul){ // count vulnerabilities
            countVulnerabilities = countVul;
            System.out.println("Vulnerabilities found in total: "+countVul);
        }
    
    public String getLogContent(String line) { // splits the logs to get the desired array
            String subStr;
            subStr = line.substring(line.lastIndexOf("\t")+1);
            subStr = subStr.substring(subStr.lastIndexOf(':')+1);
            return subStr;
        }



    
    
}
