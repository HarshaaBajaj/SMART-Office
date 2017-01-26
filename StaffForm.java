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
class StaffForm extends JFrame implements ActionListener
{

        int tot,cur,i;
        Connection con;
        Statement stm;
       	ResultSet rs;
        JLabel l1=new JLabel("Employee Id");
        JLabel l2=new JLabel("Employee Name");
        JLabel l3=new JLabel("Designation");
        JLabel l4=new JLabel("Address");
      
        JLabel l5=new JLabel("Phone No.");
        JTextField tf1=new JTextField(20);
        JTextField tf2=new JTextField(20);
        JTextField tf3=new JTextField(20);
        JTextField tf4=new JTextField(20);
        JTextField tf5=new JTextField(20);
       	JButton b1=new JButton("Reset");
       	JButton b2=new JButton("Save");
       	JButton b3=new JButton("Next");
       	JButton b4=new JButton("Prev");
       	JButton b5=new JButton("Exit");
        JPanel p=new JPanel();
        String ip;
        Label title=new Label("Staff  Setup");
        StaffForm(){

        }
        StaffForm(String s)
        {
            super("Staff Form");
            ip=s;
            try
            {

                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
                stm=con.createStatement();
                tot=recCount();
      
                p.setLayout(new GridLayout(8,2));
                p.add(l1);p.add(tf1);
                p.add(l2);p.add(tf2);
                p.add(l3);p.add(tf3);
                p.add(l4);p.add(tf4);
                p.add(l5);p.add(tf5);
              	p.add(b1);p.add(b2);
           		p.add(b3);p.add(b4);p.add(b5);
                b1.addActionListener(this);
                b2.addActionListener(this);
                b3.addActionListener(this);
                b4.addActionListener(this);
                b5.addActionListener(this);
                Container c=getContentPane();
                c.setLayout(new FlowLayout());
                JPanel jp=new JPanel();
                jp.setLayout(new BorderLayout());
                title.setFont(new Font("Arial",Font.BOLD,30));
                JPanel pp= new JPanel();
                pp.setLayout(new FlowLayout(FlowLayout.CENTER));
                pp.add(title);
                jp.add(pp,"North");
                jp.add(p,"Center");
                c.add(jp);
                setSize(800,600);
        }catch(Exception e)
        {
            setTitle("Error "+e);
		}
		setVisible(true);
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
                rs=stm.executeQuery("Select * from Employee");
                while(rs.next())
                {
                        count++;
                }
                }catch(Exception e)
                {
                        JOptionPane.showMessageDialog(this,"Error in Connection");
                }
                return(count);


    }

	void showRec(int recno)
	{
		try
		{
			rs=stm.executeQuery("select * from Employee");
			i=0;
			while(rs.next())
			{
				i++;
				if(i==recno)
				{
					break;
				}
			}
			tf1.setText(rs.getString(1));
			tf2.setText(rs.getString(2));
			tf3.setText(rs.getString(3));
			tf4.setText(rs.getString(4));
			tf5.setText(rs.getString(5));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"No Record found");
		}
	}
	public void actionPerformed(ActionEvent e)
	{
                Object obj=e.getSource();
                if(obj==b1)
                {
                        cur=0;
                        tf1.setText("");
                        tf2.setText("");
                        tf3.setText("");
                        tf4.setText("");
                        tf5.setText("");
                        }
                else
                if(obj==b2)
                {
                        String nm,ds,ad,ct,ph,q;
                        nm=tf1.getText();
                        ds=tf2.getText();
                        ad=tf3.getText();
                        ct=tf4.getText();
                        ph=tf5.getText();
                        try
                        {
                                q="Insert into Employee Values('"+nm+"','"+ds+"','"+ad+"','"+ct+"',"+ph+")";
                                int x=stm.executeUpdate(q);
                                tot++;
                                cur=tot;
                                JOptionPane.showMessageDialog(this,x+ "Record Inserted");
                        }
                        catch(Exception ex)
                        {
                                JOptionPane.showMessageDialog(this,"No Record saved");
                        }
                }
                else
                if(obj==b3)
                {
                        if(cur<tot)
                        {
                                cur++;
                                showRec(cur);
                        }

                }
                else
                if(obj==b4)
                {
                        if(cur>1)
                        {
                                cur--;
                                showRec(cur);
                        }
                }
                else
                if(obj==b5)
                {
                        dispose();
                }
	}

}