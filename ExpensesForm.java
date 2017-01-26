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
class ExpensesForm extends JFrame implements ActionListener
{

	int tot,cur,i;
	Connection con;
	Statement stm;
	ResultSet rs;
	JLabel title=new JLabel("Your Expenses");
	JLabel l1=new JLabel("Date(mm/dd/yy)");
	JLabel l2=new JLabel("Paid For");
	JLabel l3=new JLabel("Amount");
	JLabel l4=new JLabel("Paid To");
	JComboBox cb1,cb2,cb3;
	JTextField tf4=new JTextField(20);
	JTextField tf5=new JTextField(20);
	JTextField tf6=new JTextField(20);
	JButton b1=new JButton("Reset");
	JButton b2=new JButton("Save");
	JButton b3=new JButton("Exit");
	JPanel p=new JPanel(),P=new JPanel();
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
    String userid;
    String ip;

    ExpensesForm(){
        
    }
	ExpensesForm(String s,String v)
	{
        
        
		super("Expenses Form");
        userid =s;
        ip=v;
		 try
         {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
                stm=con.createStatement();

                p.setLayout(new GridLayout(4,2));
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

			p1.setLayout(new FlowLayout());
			p1.add(cb1);p1.add(cb2);p1.add(cb3);
			p.add(l1);
			p.add(p1);
			p.add(l2);
			p.add(tf4);
			p.add(l3);
			p.add(tf5);
			p.add(l4);
			p.add(tf6);
			p2.setLayout(new FlowLayout());
			p2.add(b1);
			p2.add(b2);
			p2.add(b3);
			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			title.setFont(new Font("Arial",Font.BOLD,30));
			
			P.setLayout(new BorderLayout());
            JPanel pp = new JPanel();
            pp.setLayout(new FlowLayout(FlowLayout.CENTER));
            pp.add(title);
			P.add(pp,"North");
			P.add(p,"Center");
			P.add(p2,"South");
			Container c=getContentPane();
			c.setLayout(new FlowLayout());
			c.add(P,"Center");
			tot=recCount();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error in Connection");
		}
		setVisible(true);
		setSize(790,570);
        setResizable(false);
        setLocationRelativeTo(null);
        addWindowListener (new WindowAdapter() {

            @Override
                public void windowClosing (WindowEvent e) {
                    dispose();
                }
        } );

	}
	int recCount()
	{

        int count=0;
        try
        {
            rs=stm.executeQuery("Select * from Expenses");
            while(rs.next())
            {
                count++;
            }
        }
		catch(Exception e)
        {
               JOptionPane.showMessageDialog(this,"Error in Connection");
        }
        return(count);

    }
	void showRec(int recno)
	{
		try
		{
			rs=stm.executeQuery("select * from Expenses");
			i=0;
			while(rs.next())
			{
				i++;
				if(i==recno)
				{
					break;
				}
			}
			cb1.setSelectedItem(rs.getString(1));
			cb2.setSelectedItem(rs.getString(2));
			cb3.setSelectedItem(rs.getString(3));
			tf4.setText(rs.getString(4));
			tf5.setText(rs.getString(5));
			tf6.setText(rs.getString(6));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"No Record found");
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		Object obj=e.getSource();
		try
		{
            if(obj==b1)
            {

                tf4.setText("");
                tf5.setText("");
                tf6.setText("");
            }
            else
            if(obj==b2)
            {
				String dt,mn,yr,pf,am,pt,q;
				mn=cb1.getSelectedItem()+"";
				dt=cb2.getSelectedItem()+"";
				yr=cb3.getSelectedItem()+"";
				pf=tf4.getText();
				am=tf5.getText();
				pt=tf6.getText();
				try
				{
					q="Insert into Expenses Values((select Account.empid from account where username='"+userid+"'),"+mn+","+dt+","+yr+",'"+pf+"'," +am+",'"+pt+"')";

					int x=stm.executeUpdate(q);
                    JOptionPane.showMessageDialog(this,x+ "Record Inserted");
				}
				catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(this,"Error"+ex);
          		}
            }
            else
	        if(obj==b3)
            {
				dispose();
            }
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"error "+ex);
		}
	}
        
}