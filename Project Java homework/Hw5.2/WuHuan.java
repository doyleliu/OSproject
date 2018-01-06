/**
 * Created by 栋 on 2017/3/23.
 */
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class WuHuan extends Applet{
    private int numbers = 5;
    private Color colors[]={Color.blue,Color.black,Color.red,Color.yellow, Color.green};         //restore the colors
    private int[] x={100,136,172,118,154};   //restore x lable
    private int[] y={60,60,60,91,91};    //resore y label
    private int[][] xy={{100,60},{136,60},{172,60},{118,91},{154,91}};              //restore the circule
    private int d=40; //diameter
    public void paint(Graphics g)     //painting functions
    {
        //Font font = new Font("Times New Roman",Font.ITALIC,10);
        Font font = new Font("Times New Roman",Font.BOLD,30); //The font and its size
        g.setFont(font);
        for(int i=0;i<5;i++)      //draw five circles
        {
            g.setColor(colors[i]);
            g.drawOval(x[i], y[i], d, d);
        }
        g.setColor(Color.red);
        //g.setFont(Font.BOLD);
        g.setFont(font);
        g.drawString("U S A",120,45);
    }
}

