package fUNcgrp;


import java.util.Scanner;

import fUNcgrp.FunctionGrapher.GraphPanel;

public class Trapezoidal {
	
	public static double totalArea;
	
	double   area;      
    double   a, b;      
    int      n;         
    double   h; 

   Trapezoidal() {
           

     // Scanner sc = new Scanner(System.in);
   

     // System.out.println("Enter a, b, and n");
      
      
      
      
     // a = sc.nextDouble();
     // b = sc.nextDouble();
     // n = sc.nextInt();

      
  

      
   }  

   
   
   
   public static double trap1(double a, double b, int n, double h) {
       double area;   
       double x;
       int i;

       for(int ss=1;ss<=10;ss++)
		{
			double ka;
    	   ka = GraphPanel.func.value(ss);
			System.out.println(ka);
		}
       area = (GraphPanel.func.value(a) + GraphPanel.func.value(b))/2.0;
       for (i = 1; i <= n-1; i++) {
           x = a + i*h;
           area = area + GraphPanel.func.value(x);
       }
       area = area*h;
       System.out.println(area);
       return area;
   } 


   

}  