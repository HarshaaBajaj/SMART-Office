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

class LetterSendDateForm extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,temp;
	JComboBox cb1,cb2,cb3,cb4,cb5,cb6;
	JPanel p1,p2,p3,p4,p5,p6;
	JButton b1;
	JTable tb;
    Vector col,data,row;
	ResultSet rs;
	Connection con;
	Statement stm;
    String ip;
	void message(String msg)
	{
		JOptionPane.showMessageDialog(this,msg);
	}
    LetterSendDateForm(){

    }
	LetterSendDateForm(String s)
	{
		super("LetterSendDateForm");
        ip=s;
		int i;
		l1=new JLabel("Letters Send Between Two Dates");
		l1.setFont(new Font("Arial",Font.BOLD,20));
		l2=new JLabel("From Date");
		l3=new JLabel("To Date");
		l4=new JLabel("Month");
		l5=new JLabel("Day");
		l6=new JLabel("Year");

		l7=new JLabel("Month");
		l8=new JLabel("Day");
		l9=new JLabel("Year");
		String day[],month[],year[];
		day=new String[31];
		month=new String[12];
		year=new String[50];
		for(i=0;i<=49;i++)
		{
			year[i]=(i+1980)+"";
			if(i<31)
			{
				day[i]=(i+1)+"";

				if(i<12)
					month[i]=(i+1)+"";
			}
		}
		cb1=new JComboBox(month);
		cb2=new JComboBox(day);
		cb3=new JComboBox(year);
		cb4=new JComboBox(month);
		cb5=new JComboBox(day);
		cb6=new JComboBox(year);
		p1=new JPanel(new GridLayout(2,3));
		p2=new JPanel(new GridLayout(2,3));
		p3=new JPanel(new BorderLayout());
		p4=new JPanel(new BorderLayout());
		p5=new JPanel(new FlowLayout());
		p6=new JPanel(new BorderLayout());
		p1.add(l4);
		p1.add(l5);
		p1.add(l6);
		p1.add(cb1);
		p1.add(cb2);
		p1.add(cb3);
		p2.add(l7);
		p2.add(l8);
		p2.add(l9);
		p2.add(cb4);
		p2.add(cb5);
		p2.add(cb6);
		p3.add(l2,"North");
		p3.add(p1);

		p4.add(l3,"North");
		p4.add(p2);
		b1=new JButton("Load");
		b1.addActionListener(this);
		temp=new JLabel("                                        ");
		p5.add(p3);p5.add(temp);p5.add(p4);p5.add(b1);
        JPanel pp = new JPanel();
        pp.setLayout(new FlowLayout(FlowLayout.CENTER));
        pp.add(l1);
		p6.add(pp,"North");p6.add(p5);
		Container c=getContentPane();
		c.setLayout(new BorderLayout());
		c.add(p6,"North");
		setSize(600,500);
		setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");

		}catch(Exception e)
		{
			message("Error "+e);
		}

	}

	public void actionPerformed(ActionEvent ae)
	{
		String s,q;
		int dd1,mm1,yy1,dd2,mm2,yy2;
		s=ae.getActionCommand();
		if(s.equals("Load"))
		{
			mm1=Integer.parseInt(cb1.getSelectedItem()+"");
			dd1=Integer.parseInt(cb2.getSelectedItem()+"");
			yy1=Integer.parseInt(cb3.getSelectedItem()+"");
			mm2=Integer.parseInt(cb4.getSelectedItem()+"");
			dd2=Integer.parseInt(cb5.getSelectedItem()+"");
			yy2=Integer.parseInt(cb6.getSelectedItem()+"");


			try
			{
				stm=con.createStatement();
                rs=stm.executeQuery("select * from letterssend where (letterssend.year,letterssend.month,letterssend.date) >=('"+yy1+"','"+mm1+"','"+dd1+"') and (letterssend.year,letterssend.month,letterssend.date) <=('"+yy2+"','"+mm2+"','"+dd2+"')");

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

				Container c=getContentPane();

				c.add(new JScrollPane(tb),"Center");
				c.doLayout();

			}catch(Exception e)
			{
				message("Error "+e);
			}
		}

	}

}