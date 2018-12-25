import java.lang.reflect.Array;
import java.util.Arrays;

public class ShortestJobFirst {
	static class Process 
	{ 
	   int pid; // Process ID 
	   int bt;  // Burst Time 
	   
	   Process(int nPid, int nBt) {
		   pid = nPid;
		   bt = nBt ;
	   }
	   	   
	   int compareTo(Process other)
	   {		   
		   if (other.bt > bt)  return -1;
		   if (other.bt == bt) return 0;
		   return 1;
	   }
	}	  
  
	// Function to find the waiting time for all 
	// processes 
	static void GetWaitingTime(Process proc[], int n, int wt[]) 
	{ 
	    // waiting time for first process is 0 
	    wt[0] = 0; 
	  
	    // calculating waiting time 
	    for (int i = 1; i < n ; i++ ) 
	        wt[i] = proc[i-1].bt + wt[i-1] ; 
	} 
	  
	// Function to calculate turn around time 
	static void GetTurnAroundTime(Process proc[], int n, 
	                        int wt[], int tat[]) 
	{ 
	    // calculating turnaround time by adding 
	    // bt[i] + wt[i] 
	    for (int i = 0; i < n ; i++) 
	        tat[i] = proc[i].bt + wt[i]; 
	} 
	  
	//Function to calculate average time 
	static ResultSchedule getAverageTime(Process proc[], int n) 
	{ 
	    int wt[], tat[], total_wt = 0, total_tat = 0; 
	    
	    wt = new int[n];
	    tat = new int[n];
	  
	    // Function to find waiting time of all processes 
	    GetWaitingTime(proc, n, wt); 
	  
	    // Function to find turn around time for all processes 
	    GetTurnAroundTime(proc, n, wt, tat); 
	  
	    // Display processes along with all details	    
	    System.out.println("\nProcesses " + " Burst time " + " Waiting time "  + " Turn around time");
	  
	    // Calculate total waiting time and total turn 
	    // around time 
	    for (int i = 0; i < n; i++) 
	    { 
	        total_wt = total_wt + wt[i]; 
	        total_tat = total_tat + tat[i]; 
	        System.out.println(" "  + proc[i].pid + "\t\t"  + proc[i].bt + "\t " +  wt[i] + "\t\t " + tat[i]);	        
	    } 
	    
	    ResultSchedule result = new ResultSchedule();                
        result.average_waiting_time = (float)total_wt / (float)n;
        result.average_turn_arround_time = (float)total_tat / (float)n;
	  
	    System.out.println("\nAverage waiting time = " + (float)total_wt / (float)n);
	    System.out.println("Average turn around time = " + (float)total_tat / (float)n);
	    
	    return result;
	}   
	

}
