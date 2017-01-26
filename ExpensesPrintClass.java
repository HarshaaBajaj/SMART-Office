/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author varun
 */
import java.awt.*;
import java.sql.*;
import java.awt.print.*;
import java.awt.geom.*;

class ExpensesPrintClass implements Printable
{
	Connection con;
	Statement stm;
    ResultSet rs;
	PrinterJob pj;
	PageFormat pf;
    int i=1;
    String ip;
    ExpensesPrintClass(){

    }
	ExpensesPrintClass(String s)
	{
        ip=s;
		try
		{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
           		stm=con.createStatement();
                rs=stm.executeQuery("Select * from Expenses");
                pj=PrinterJob.getPrinterJob();
                pf=pj.defaultPage();
                pj.setPrintable(this,pf);
                pj.print();

        }catch(Exception e)
		{
			System.out.println("Error "+e);
		}
	}

	public int print(Graphics g,PageFormat pf,int page)
	{
            if(i==1)
            {
                i++;
                return Printable.PAGE_EXISTS;
            }
            Graphics2D g2=(Graphics2D)g;
            g2.setPaint(Color.black);
            g2.translate(pf.getImageableX(),pf.getImageableY());
            g2.draw(new Rectangle2D.Double(0,0,pf.getImageableWidth(),pf.getImageableHeight()));
            try
            {
                int x=50;
                int y=65;
                g2.drawString("Expenses Table",160,10);
                g2.drawString("Empid",10,40);
                g2.drawString("Date",60,40);
                g2.drawString("Paid For",130,40);
                g2.drawString("Amount",300,40);
                g2.drawString("Paid To",370,40);
                while(rs.next())
                {
                    g2.drawString(rs.getString(1),10,y);
                    g2.drawString(rs.getString(2)+"/"+rs.getString(3)+"/"+rs.getString(4),60,y);
                    g2.drawString(rs.getString(5)+"",130,y);
                    g2.drawString(rs.getString(6),300,y);
                    g2.drawString(rs.getString(7),370,y);

                    y=y+15;
                    if(y>350)
                        return Printable.PAGE_EXISTS;

                }
            }catch(Exception e)
            {
                System.out.println("Error "+e);
            }

            return Printable.NO_SUCH_PAGE;
    }

}
