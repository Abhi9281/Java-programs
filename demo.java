public class demo {
    public static void main(String[] args)
    {
        try{
            int result= divide(10,0);
            System.out.println("Result" +result);
        }
        catch(Custom Exception )
        {
            System.out.println("Custom Exception caught"+e.getMessage);
        }
    } 
    public static int divide(int numerator, int denominator) throws Custom Exception
    {
        if(denominator==0)
        {
            throw new Custom Exception("division by zero is not allowed");
        }
        return numerator/denominator;

    }
}
