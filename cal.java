import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class cal
{
    public static void main(String [] args)
    {
        double num1,num2,result;
        JFrame f=new JFrame("calculater");
        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);

        JTextField t1=new JTextField(20);
        f.add(t1);
        JPanel p =new JPanel();

        JTextField T=new JTextField();

        JButton Add=new JButton("+");
        JButton Sub=new JButton("-");
        JButton Mul=new JButton("*");
        JButton Div=new JButton("/");
        JButton Eql=new JButton("=");
        JButton Del=new JButton("Del");
        
        JButton numbers[]=new JButton[10];
        for(int i=0;i<10;i++)
        {
            numbers[i]=new JButton(String.valueOf(i));
           // numbers[i].addActionListener(new NumberButtonListener());
        }


        p.setLayout(new GridLayout(4,5));
        for(int i=0;i<10;i++)
        {
            p.add(numbers[i]);
        }
        public Double operation()
        {
            switch(op)
            case "+":
                return num1+num2;
            case "-":
                return num1-num2;
            case "*":
                return num1*num2;
            case "/":
                if(num2==0)
                {
                    System.out.println("error");
                }
                else
                {
                    return num1/num2;
                }
            default:
                return 0;            
        }
    }
}
