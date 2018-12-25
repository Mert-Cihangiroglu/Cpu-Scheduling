import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CompareThreeAlgorithm {
	private static Integer CheckInt(Scanner sc) throws MyException {
	    try {
	        //int input = sc.nextInt();
	        if (!sc.hasNextInt()) {
	            throw new MyException("Wrong type");
	        } else {
	        	Integer input = sc.nextInt();
	            return input;
	        }
	    } catch (InputMismatchException ime) {
	        sc.nextInt(); // Flush the scanner

	        // Rethrow your own exception
	        throw new MyException("Wrong Int");
	    }
	}

	private static class MyException extends Exception {
	    // You exception details
	    public MyException(String message) {
	        super(message);
	    }
	}
	
	private static void showResult(ArrayList <Integer> bt, ArrayList <Integer> at) {
	    // Process id's
		int size = bt.size();
		int processes[] = new int[size] ;  
	    
	    // Burst time of all processes  
	    int burst_time[] = new int[size];  
	  
	    // Arrival time of all processes  
	    int arrival_time[] = new int[size]; 
		
		for(int i = 1; i <= bt.size(); i++)
		{
			processes[i-1] = i;
			burst_time[i-1] = bt.get(i -1);
			arrival_time[i-1] = at.get(i -1);			
		}
		
	    int n = processes.length;
	    
		// TODO Auto-generated method stub
		System.out.println("=====================================ROUND ROBIN ====================================");
		RoundRobin rr = new RoundRobin();
        // Time quantum 
        int quantum = 2; 
        ResultSchedule resultRR = new  ResultSchedule();
        resultRR = rr.getAverageTime(processes, n, burst_time, quantum);
		
		System.out.println("\n\n==============================FIRST COME FIRST SERVE=================================");
		FirstComeFirstServe fcfs = new FirstComeFirstServe();
		ResultSchedule resultFCFS = new  ResultSchedule();
		resultFCFS = fcfs.getAverageTime(processes, n, burst_time, arrival_time);		
		
		
		System.out.println("\n\n===================================SHORTEST JOB FIRST=================================");
		
		
		ShortestJobFirst sjf = new ShortestJobFirst();
		ShortestJobFirst.Process[] proc = new ShortestJobFirst.Process[size];
		for(int i = 1; i <= size; i ++)
		{
			ShortestJobFirst.Process procItem = new ShortestJobFirst.Process(i, burst_time[i-1]);
			proc[i-1] = procItem;
		}		 
		
	    n = proc.length;	  
	    
	    // Sorting processes by burst time.
	    Arrays.sort(proc, (a,b) -> a.compareTo(b));	     
	  
	    System.out.println("Order in which process gets executed"); 
	    for (int i = 0 ; i < n; i++) 
	        System.out.print(proc[i].pid + "  "); 
	    
	    ResultSchedule resultSJF = new  ResultSchedule();
	    resultSJF = sjf.getAverageTime(proc, n);    
	    
	    System.out.println("\n========================================== COMPARE ====================================");
	    System.out.print("Algorithm                  " +  "Average waiting time " + "   Average turn around time \n" );	    //
	    System.out.printf("%-26s %-23s %s\n", "Round Robin", resultRR.average_waiting_time.toString(), resultRR.average_turn_arround_time.toString());
	    System.out.printf("%-26s %-23s %s\n", "First Come First Serve", resultFCFS.average_waiting_time.toString(), resultFCFS.average_turn_arround_time.toString());
	    System.out.printf("%-26s %-23s %s\n", "Shortest Job First", resultSJF.average_waiting_time.toString(), resultSJF.average_turn_arround_time.toString());	    //
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList <Integer> bt = new ArrayList<Integer>();
		ArrayList <Integer> at = new ArrayList<Integer>();
	    while (true) {
	        try {
	        	System.out.println("\n\n");
	        	System.out.println("*************************************************************************************");
	        	System.out.println("*                                      SHOW RESULT                                   ");
	        	System.out.println("*************************************************************************************");
	            System.out.print("Enter burst time: ");	            
	            bt.add(CheckInt(input));
	            System.out.print("Enter arrival time: ");
	            at.add(CheckInt(input));
	            showResult(bt, at);
	            
	        } catch (MyException me) {
	            System.out.println(me.toString());
	        }
	    }
	    
	}

}
