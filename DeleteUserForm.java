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

class DeleteUserForm extends JFrame implements ActionListener
{
        int tot, cur,i;
        Connection con;
        Statement stm;
        ResultSet rs;
        JPanel p1,p2,P,p3,p4;
        JLabel l1=new JLabel("User Id");
        JLabel l2=new JLabel("Designation");
        JLabel l3=new JLabel("Employee Id");    
        
        JTextField tf1=new JTextField(20);
        ButtonGroup bg = new ButtonGroup();
        JRadioButton Adm=new JRadioButton("Employee");
        JRadioButton Acc=new JRadioButton("Accountant");
        JRadioButton Re=new JRadioButton("Receptionist");
        JTextField tf2=new JTextField(20);

        JButton b1=new JButton("Reset");
        JButton b2=new JButton("Delete");
        JButton b3=new JButton("Exit");
        
        JPanel p=new JPanel();
        Label title=new Label("Add User Entry Form");
        String ip;
        DeleteUserForm(){

        }
        DeleteUserForm(String s)
        {
            super("Delete User Form");
            ip=s;
		    p1 = new JPanel();
			p1.setLayout(new FlowLayout());
			p.setLayout(new GridLayout(5,2));
            bg.add(Adm);bg.add(Acc);bg.add(Re);
			p.add(l1);p.add(tf1);
			p1.add(Adm);p1.add(Acc);p1.add(Re);
			p.add(l2);p.add(p1);
			p.add(l3);p.add(tf2);

			p2=new JPanel();
			p2.setLayout(new FlowLayout());
			p2.add(b1);p2.add(b2);
			p2.add(b3);
            p3 = new JPanel();
            p3.setLayout(new FlowLayout(FlowLayout.CENTER));

            p4=new JPanel();
            p4.setLayout(new GridLayout(2,1));
            p4.add(p2);p4.add(p3);
			P=new JPanel();
			P.setLayout(new BorderLayout());
			JPanel pp=new JPanel();
			pp.setLayout(new FlowLayout(FlowLayout.CENTER));
			pp.add(title);

			P.add(pp,"North");
			P.add(p,"Center");
			P.add(p4,"South");

			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);

			Container c=getContentPane();
			c.setLayout(new FlowLayout());

			title.setFont(new Font("Arial",Font.BOLD,30));
			c.add(P);
			setSize(800,600);
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


	public void actionPerformed(ActionEvent e)
	{
                Object obj=e.getSource();

                String vn,des,cf,pov,vt,q;

                Acc.setSelected(false);
	            Re.setSelected(false);
                Adm.setSelected(false);
         		vn=tf1.getText();
                if(Adm.isSelected()==true)
                {
                    des="Employee";

                }
                else if(Acc.isSelected()==true)
                {
                    des="Accountant";
                }
                else if(Re.isSelected()==true)
                {
                    des = "Receptionist";

                }
                cf=tf2.getText();


                if(obj==b1)
                {
                    tf1.setText("");
                    tf2.setText("");

                    Adm.setSelected(false);
                    Acc.setSelected(false);
                    Re.setSelected(false);
                }
                else
                if(obj==b2)
                {
                    JOptionPane.showMessageDialog(this,"All the records correspoding to the user will be deleted");
                    deleterec();
                }
                else
                if(obj==b3)
                {
                    dispose();
               	}

    }

    public void deleterec()
    {
            String vn,des,cf,pov,vt,q,q1,q2,q3;
            vn=tf1.getText();
			if(Adm.isSelected()==true)
			{
			     des="Employee";
            }
			else if(Acc.isSelected()==true)
			{
				des="Accountant";

			}
            else
			{
                des = "Receptionist";

			}
			cf=tf2.getText();

            try{

                Class.forName("com.mysql.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
                stm = cn.createStatement();
                q="Delete from Account where username='"+vn+"'and Designation='"+des+"'and Empid="+cf+"";
                q1="Delete from Attendance where Empid="+cf+"";
                q2="Delete from Employee where Empid="+cf+"";
                q3="Delete from Expenses where Empid="+cf+"";
                stm.executeUpdate(q1);
                stm.executeUpdate(q2);
                stm.executeUpdate(q3);
                stm.executeUpdate(q);
                tf1.setText("");
                tf2.setText("");

                Adm.setSelected(false);
                Acc.setSelected(false);
                Re.setSelected(false);
            }

            catch(Exception ae)
            {
                     JOptionPane.showMessageDialog(this,"Error"+ae);
            }
          
    }

}
	
