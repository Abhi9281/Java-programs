import java.util.*;
import java.lang.*;
public class Time
{
    public static void main(String[] args) 
    {
        int h,m,angle;
        System.out.println("Enter the time in hours and minutes");
        Scanner sc=new Scanner(System.in);
        h=sc.nextInt();
        m=sc.nextInt();
        if(h==12)
        {
            angle=(m*6);
            System.out.println("Angle will be "+angle);
        }
        else
        {
            angle=Math.abs(h*30-m*6);
            System.out.println("Angle will be "+angle);
        }
        sc.close();
    }  
}
