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
class MExpensesList extends JFrame implements ActionListener
{
	Connection con;
	Statement stm;
	ResultSet rs;
	JTable tb;
	JLabel l=new JLabel("Enter Date(mm/yy)");
	JComboBox cb1,cb2;
	JButton b1=new JButton("Show");
	JButton b2=new JButton("Exit");
	Label title=new Label("Monthly Expenses List Of All Employees");
	JPanel p=new JPanel();
	JScrollPane jsp;
	Container c;
    Vector col,data,row;
    JButton total;
    JLabel temp;
    JTextField sum=new JTextField(12);
    String ip;
    MExpensesList(){

    }
    MExpensesList(String s)
 	{
		super("MExpenses List");
        ip=s;
        total = new JButton("Total");
        total.addActionListener(this);
		title.setFont(new Font("Arial",Font.BOLD,30));
		try
		{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
          		stm=con.createStatement();
	
        		jsp=new JScrollPane(tb);
            	c=getContentPane();
                c.setLayout(new BorderLayout());
                JPanel pp= new JPanel();
                pp.setLayout(new FlowLayout(FlowLayout.CENTER));
                pp.add(title);
                c.add(pp,"North");
           		c.add(jsp,"South");
                c.add(b2);
                String mm[],yy[];

				mm=new String[12];
				yy=new String[50];
				int i;
				for(i=0;i<12;i++)
				{

						mm[i]=new String(""+(i+1));
				}
				for(i=0;i<=49;i++)
					yy[i]=new String(""+(i+1980));


				cb1=new JComboBox(mm);
				cb2=new JComboBox(yy);
    			p.setLayout(new FlowLayout());
        		p.add(l); p.add(cb1);
            	p.add(cb2); p.add(b1);
                c.add(p,"Center");

        		b1.addActionListener(this);
            	b2.addActionListener(this);
                JPanel p1 = new JPanel();
                p1.setLayout(new FlowLayout(FlowLayout.CENTER));

                temp = new JLabel("           ");
                sum.setEditable(false);
                p1.add(total);p1.add(temp);p1.add(sum);
                c.add(p1,"South");
                setSize(790,570);
                setLocationRelativeTo(null);
		}
		catch(Exception e)
		{
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

                    String mn,yr,q;
                    mn=cb1.getSelectedItem()+"";
                    yr=cb2.getSelectedItem()+"";
                    q="Select * from Expenses where Month="+mn +"  and Year=" +yr ;
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
                else if(obj==b2)
                {
                    setVisible(false);
                }else if(obj== total){
                    try
                    {
                            String mn,yr,q;
                            mn=cb1.getSelectedItem()+"";
                            yr=cb2.getSelectedItem()+"";
                            Class.forName("com.mysql.jdbc.Driver");
                            con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
                            stm=con.createStatement();
                            q="Select sum(amount) from expenses where Month="+mn +"  and Year=" +yr;
                            rs=stm.executeQuery(q);
                            while(rs.next())
                            {
                                sum.setText(rs.getString(1));
                            }


                    }
                    catch(Exception ee){System.out.println(ee);}
                }
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"error "+ex);
		}
	}

}

