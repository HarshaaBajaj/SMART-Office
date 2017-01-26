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

class LettersReceivedPrintClass implements Printable
{
	Connection con;
	Statement stm;
    ResultSet rs;
	PrinterJob pj;
	PageFormat pf;
	int i=1;
    String ip;
    LettersReceivedPrintClass(){

    }
	LettersReceivedPrintClass(String s)
	{
        ip=s;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
  			stm=con.createStatement();
			rs=stm.executeQuery("Select * from LettersReceived");
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
            int y=60;
            g2.drawString("Letters Received Table",160,10);
            g2.drawString("Letter No",10,40);
            g2.drawString("FromAddress",100,40);
            g2.drawString("Subject",220,40);
            g2.drawString("Date",335,40);
            
            while(rs.next())
            {
                g2.drawString(rs.getString(1)+"",10,y);
                g2.drawString(rs.getString(2)+"",100,y);
                g2.drawString(rs.getString(3)+"",220,y);
                g2.drawString(rs.getString(4)+"/"+rs.getString(5)+"/"+rs.getString(6),335,y);

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
