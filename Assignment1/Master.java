package Assignment1;
import java.io.*;
import java.util.ArrayList;

public class Master extends Thread {

    public String filename;
    public static ArrayList<String> logs = new ArrayList<String>(); //Usage of an ArrayList avoids writing logic for counting the number of lines when reading the .txt file for the first time: logs.size();
    public static int currentWorkerNumber;
    public int wCount;
    public static int countVulnerabilities;
    public int countLines;
    public double approximateAvg;
    public double previousAvg;
    public static ArrayList<WorkerThread> w = new ArrayList<WorkerThread>();


    public Master(){

    }

    public Master(String filename){
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


    public double calculateApproximateAvg(){
            return (double) countVulnerabilities/countLines;
        }
    
    public void incrementWorkers(){
            currentWorkerNumber += 2;
        }
    
    public void appendWorkers2(){
            incrementWorkers();
            w.clear();
            for(int i=0; i<currentWorkerNumber; i++){
                w.add(new WorkerThread(wCount));
                wCount++;
            }
            startWorkers();
        }
    
    public void appendWorkers(){
            w.clear();
            for(int i=0; i<currentWorkerNumber; i++){
                w.add(new WorkerThread(wCount));
                wCount++;
            }
            startWorkers();
        }
    
    public void appendWorkers1stTime(){
            w.add(new WorkerThread(wCount));
            wCount++;
            w.add(new WorkerThread(wCount));
            wCount++;
            startWorkers();
        }
    
    public void startWorkers(){
            for(WorkerThread w: w){
                w.start();
            }
        }

    public void setCountVulnerabilities(int countVul){
            countVulnerabilities = countVul;
            System.out.println("Vulnerabilities found in total: "+countVul);
        }
    
    public String getLogContent(String line) {
            String subStr;
            subStr = line.substring(line.lastIndexOf("\t")+1);
            subStr = subStr.substring(subStr.lastIndexOf(':')+1);
            return subStr;
        }



    
    
}
