/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author varun
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;
class DVEntryList extends JFrame implements ActionListener
{
	Connection con;
	Statement stm;
	ResultSet rs;
	JTable tb;
	JLabel l=new JLabel("Enter Date(mm/dd/yy)");
	JComboBox cb1,cb2,cb3;
	JButton b1=new JButton("Show");
	JButton b2=new JButton("Exit");
	Label title=new Label("Daily Visitors Entry List");
	JPanel p=new JPanel();
	JScrollPane jsp;
	Container c;
    Vector col,row,data;
    String ip;
    DVEntryList(){

    }
    DVEntryList(String s)
 	{
		super("VisitorsEntry List");
        ip=s;
		title.setFont(new Font("Arial",Font.BOLD,30));
		try
		{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
                stm=con.createStatement();
                c=getContentPane();
                c.setLayout(new BorderLayout());
                JPanel pp = new JPanel();
                pp.setLayout(new FlowLayout(FlowLayout.CENTER));
                pp.add(title);
                c.add(pp,"North");
                c.add(b2);
                String dd[],mm[],yy[];
				dd=new String[31];
				mm=new String[12];
				yy=new String[50];
				int i;
				for(i=0;i<=30;i++)
				{
					dd[i]=new String(""+(i+1));
					if(i<12)
						mm[i]=new String(""+(i+1));
				}
				for(i=0;i<=49;i++)
					yy[i]=new String(""+(i+1980));


				cb1=new JComboBox(mm);
				cb2=new JComboBox(dd);
				cb3=new JComboBox(yy);

                p.setLayout(new FlowLayout());
                p.add(l); p.add(cb1);
                p.add(cb2); p.add(cb3); p.add(b1);
                c.add(p,"Center");
                b1.addActionListener(this);
                b2.addActionListener(this);
                setSize(790,570);
                setResizable(false);
                setLocationRelativeTo(null);
        }
        catch(Exception e)
		{
            System.out.println(e);
		}
		setVisible(true);
	 }
	public void actionPerformed(ActionEvent e)
	{
		try
		{
            Object obj=e.getSource();
			if(obj==b1)
			{

				String dt,mn,yr,q;
				mn=cb1.getSelectedItem()+"";
				dt=cb2.getSelectedItem()+"";
				yr=cb3.getSelectedItem()+"";
				q="Select * from VisitorsEntry where Month="+mn +" and Date="+ dt + " and Year=" +yr ;
				rs=stm.executeQuery(q);

                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();

                col=new Vector(columnCount);
                for(int i=0;i<columnCount;i++)
                {
                    col.add(rsmd.getColumnName(i+1));

                }
                data=new Vector();
                while(rs.next())
                {
                    row=new Vector();
                    for(int k=0;k<columnCount;k++)
                    {
                        row.addElement(rs.getObject(k+1));

                    }
                    data.addElement(row);

                }

                tb=new JTable(data,col){
                    @Override
                    public boolean isCellEditable(int row,int col){
                        return false;
                    }
                };
                tb.getTableHeader().setReorderingAllowed(false);

				jsp=new JScrollPane(tb);
				c.add(jsp,"South");
				c.doLayout();

			}
		 	if(obj==b2)
			{
				setVisible(false);
			}
		}
		catch(Exception ex)
		{
		}
	}

}
