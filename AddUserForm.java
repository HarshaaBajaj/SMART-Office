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

class AddUserForm extends JFrame implements ActionListener
{
        int tot, cur,i;
        Connection con;
        Statement stm;
        ResultSet rs;
        JPanel p1,p2,P,p3,p4;
        JLabel l1=new JLabel("User Id");
        JLabel l2=new JLabel("Designation");
        JLabel l3=new JLabel("Employee Id");
        JLabel l4=new JLabel("Click on Next to enter personal details      ");

        JLabel l9=new JLabel("Password");
        JLabel l10=new JLabel("Re-Type Password");
        JTextField tf1=new JTextField(20);
        ButtonGroup bg = new ButtonGroup();
        JRadioButton Adm=new JRadioButton("Employee");
        JRadioButton Acc=new JRadioButton("Accountant");
        JRadioButton Re=new JRadioButton("Receptionist");
        JTextField tf2=new JTextField(20);

        JPasswordField tf3=new JPasswordField(20);
        JPasswordField tf4=new JPasswordField(20);
        JButton b1=new JButton("Reset");
        JButton b2=new JButton("Save");
        JButton b3=new JButton("Exit");
        JButton b4=new JButton("Next");
        JPanel p=new JPanel();
        Label title=new Label("Add User Entry Form");
        String ip;
        AddUserForm(){

        }
        AddUserForm(String s)
        {
            super("Add User Form");
            ip=s;
		    p1 = new JPanel();
			p1.setLayout(new FlowLayout());
			p.setLayout(new GridLayout(8,2));
            bg.add(Adm);bg.add(Acc);bg.add(Re);
			p.add(l1);p.add(tf1);
			p.add(l2);p1.add(Adm);p1.add(Acc);p1.add(Re);
			p.add(p1);
			p.add(l3);p.add(tf2);
			p.add(l9);p.add(tf3);
            p.add(l10);p.add(tf4);
			p2=new JPanel();
			p2.setLayout(new FlowLayout());
			p2.add(b1);p2.add(b2);
			p2.add(b3);p2.add(b4);
            p3 = new JPanel();
            p3.setLayout(new FlowLayout(FlowLayout.CENTER));
            p3.add(l4);
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
            b4.addActionListener(this);

            b4.setEnabled(false);

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
                pov=new String(tf3.getPassword());
            	vt=new String(tf4.getPassword());

                if(obj==b1)
                {
                    tf1.setText("");
                    tf2.setText("");
                    tf3.setText("");

                    tf4.setText("");

                    Adm.setSelected(false);
                    Acc.setSelected(false);
                    Re.setSelected(false);
                }
                else
                if(obj==b2)
                {
                    if(pov.equals(vt.toString()))
                        passwdeql();
                    else
                        retypepass();
                }
                else
                if(obj==b3)
                {
                    dispose();
               	}
                else
                if(obj==b4)
                {
                    dispose();
                    new StaffForm();
                }

    }

    public void passwdeql()
    {
            String vn,des,cf,pov,vt,q;
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
			pov=new String(tf3.getPassword());
			vt=new String(tf4.getPassword());
            try{

                Class.forName("com.mysql.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
                stm = cn.createStatement();
                q="Insert into Account Values('"+vn+"','"+pov+"','"+des+"','"+cf+"')";
                int x=stm.executeUpdate(q);
                JOptionPane.showMessageDialog(this,x+ "Record Inserted");
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");

                Adm.setSelected(false);
                Acc.setSelected(false);
                Re.setSelected(false);
                b4.setEnabled(true);
            }

            catch(Exception ae)
            {
                     JOptionPane.showMessageDialog(this,"Error"+ae);
            }
        
    }

    public void retypepass(){

        try{
            tf4.setText("");
            JOptionPane.showMessageDialog(this,"Password Do Not Match with the First");
        }
        catch(Exception ae)
        {
            JOptionPane.showMessageDialog(this,"Error"+ae);
        }
    }

}
		