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

class VisitorEntryPrintClass implements Printable
{
	Connection con;
	Statement stm;
    ResultSet rs;
	PrinterJob pj;
	PageFormat pf;
	int i=1;
    String ip;
    VisitorEntryPrintClass(){

    }
	VisitorEntryPrintClass(String s)
	{
		try
		{
            ip =s;
			Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
            stm=con.createStatement();
			rs=stm.executeQuery("Select * from VisitorsEntry");
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
                g2.drawString("Visitors List",180,10);
                g2.drawString("Visitor Name",10,40);
                g2.drawString("Sex",110,40);
                g2.drawString("Came From",170,40);
                g2.drawString("Purpose Of Visit",280,40);
                
                g2.drawString("InTime",400,40);
                g2.drawString("OutTime",490,40);
                g2.drawString("Date",580,40);
                g2.drawString("Remarks",640,40);
                while(rs.next())
                {
                    g2.drawString(rs.getString(1)+"",10,y);
                    g2.drawString(rs.getString(2)+"",110,y);
                    g2.drawString(rs.getString(3)+"",170,y);
                    g2.drawString(rs.getString(4)+"",280,y);
                    g2.drawString(rs.getString(5)+"",400,y);
                    g2.drawString(rs.getString(6)+"",490,y);
                
                    g2.drawString(rs.getString(7)+"/"+rs.getString(8)+"/"+rs.getString(9),580,y);
                    g2.drawString(rs.getString(10),640,y);

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