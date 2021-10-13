//ASSIGNMENT 1 
// Christopher Khoury 40019672 and Romain de Fazio 40062501
package Assignment1;
import java.io.*;
import java.util.ArrayList;

public class Master extends Thread {
// global variables initialization
    public String filename;
    public static ArrayList<String> logs = new ArrayList<String>(); //Usage of array to avoid constantly using bufferReader
    public int wCount; ////worker counter
    public static int cntVuln; //vulnerability counter
    public int cntLines; 
    public double approxAvg;
    public double prevAvg;
    public static ArrayList<WorkerThread> w = new ArrayList<WorkerThread>();
    public static int currWorkerNum;


    public Master(){ //empty contructor to allow worker to have their own contructor

    }

    public Master(String filename){ // contructor to read the file and input it into the arraylist
        this.filename = filename;
        String line = null;
        String subString = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            while ((line = in.readLine())!= null){
                subString = getLogContent(line);
                logs.add(subString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cntLines = logs.size();
        this.approxAvg = 0;
        this.prevAvg = 0;
        wCount = 0;
        currWorkerNum = 2;
    }


    public double calcAppAvg(){ //calculate an approximate
            return (double) cntVuln/cntLines;
        }
    
    public void incWorkers(){ // increments worker by 2
            currWorkerNum += 2;
        }
    
    public void appWorkers2(){ // adds 2 extra workers to the array and runs them
            incWorkers();
            w.clear();
            for(int i=0; i<currWorkerNum; i++){
                w.add(new WorkerThread(wCount));
                wCount++;
            }
            startWorkers();
        }
    
    public void appWorkers1(){  // create workers and runs them (updates the array)
            w.clear();
            for(int i=0; i<currWorkerNum; i++){
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
            cntVuln = countVul;
            System.out.println("Vulnerabilities found in total: "+countVul);
        }
    
    public String getLogContent(String line) { // splits the logs to get the desired array
            String subString;
            subString = line.substring(line.lastIndexOf("\t")+1);
            subString = subString.substring(subString.lastIndexOf(':')+1);
            return subString;
        }



    
    
}
