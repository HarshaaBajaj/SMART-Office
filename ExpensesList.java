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
class ExpensesList extends JFrame implements ActionListener
{
	Connection con;
	Statement stm;
    ResultSet rs;
	JTable tb;
    Vector col,data,row;
    JButton total ;
    JTextField sum = new JTextField(12);
    JLabel temp;
	Label title=new Label("Expenses List");
    String userid;
    String ip;
    
    ExpensesList(){
        
    }
	ExpensesList(String s,String v)
 	{
       
		super("Expenses List");
        userid =s;
        ip=v;
		title.setFont(new Font("Arial",Font.BOLD,30));
        total= new JButton("TOTAL");
        total.addActionListener(this);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
            stm=con.createStatement();
			rs=stm.executeQuery("Select * from Expenses where empid=(select Account.empid from account where username='"+userid+"')");

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
			c.setLayout(new BorderLayout());
            JPanel pp = new JPanel();
            pp.setLayout(new FlowLayout(FlowLayout.CENTER));
            pp.add(title);
			c.add(pp,"North");
            c.add(new JScrollPane(tb),"Center");
            temp = new JLabel("           ");
            sum.setEditable(false);
            JPanel p1 = new JPanel();
            p1.setLayout(new FlowLayout(FlowLayout.CENTER));

            p1.add(total);p1.add(temp);p1.add(sum);
            c.add(p1,"South");
			setSize(790,570);
            setLocationRelativeTo(null);

        }
		catch(Exception e)
		{
            JOptionPane.showMessageDialog(this,"Error "+e);
		}
		setVisible(true);
	}
        public void actionPerformed(ActionEvent e)
	{
        int j=0;
        Object obj=e.getSource();
        if(obj==total){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
                stm=con.createStatement();
                rs=stm.executeQuery("Select sum(amount) from expenses where empid=(select Account.empid from account where username='"+userid+"')");
                while(rs.next())
                {
                    sum.setText(rs.getString(1));
                }

            }
            catch(Exception ee){System.out.println(ee);}
        }
    }

}

