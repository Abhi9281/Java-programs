import java.util.*;
class Sep_char
{
    public static void main(String[] args)
    {
        String s;
        System.out.println("Enter the string");
        Scanner sc= new Scanner(System.in);
        s=sc.nextLine();
        for(int i=0;i<s.length();i++)
        {
            System.out.println(s.charAt(i)+" ");
        }
        sc.close();
    }
} 