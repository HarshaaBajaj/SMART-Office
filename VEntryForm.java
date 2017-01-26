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

class VEntryForm extends JFrame implements ActionListener
{
	 int tot, cur,i;
	Connection con;
	Statement stm;
	ResultSet rs;
	JPanel p1,p2,P,p3;
	JLabel l1=new JLabel("Visitor Name");
	JLabel l2=new JLabel("Sex");
	JLabel l3=new JLabel("Came From");
	JLabel l4=new JLabel("Purpose of Visit");
	JLabel l6=new JLabel("In-Time");
	JLabel l7=new JLabel("Out-Time");
	JLabel l8=new JLabel("Enter Date(mm/dd/yy)");
	JComboBox cb1,cb2,cb3;
	JLabel l9=new JLabel("Remarks");
	JTextField tf1=new JTextField(20);
	JRadioButton m=new JRadioButton("Male");
	JRadioButton fm=new JRadioButton("Female");

    ButtonGroup bg = new ButtonGroup();

	JTextField tf2=new JTextField(20);
	JTextField tf3=new JTextField(20);
	JTextField tf5=new JTextField(20);
	JTextField tf6=new JTextField(20);
	JTextField tf9=new JTextField(4);
	JTextField tf10=new JTextField(20);
	JButton b1=new JButton("Reset");
	JButton b2=new JButton("Save");
	JButton b3=new JButton("Next");
	JButton b4=new JButton("Prev");
	JButton b5=new JButton("Exit");
	JPanel p=new JPanel();
	Label title=new Label("Visitors Entry");
    String ip;
    VEntryForm(){

    }
	VEntryForm(String s)
	{
		super("VisitorEntry Form");
        ip=s;
		 try
         {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
		        stm=con.createStatement();

                p1=new JPanel();
                p1.setLayout(new FlowLayout());
                p.setLayout(new GridLayout(8,2));
                p.add(l1);p.add(tf1);
                p.add(l2);p1.add(m); p1.add(fm);
                bg.add(m);bg.add(fm);
                m.setSelected(false);
                fm.setSelected(false);
                p.add(p1);
                p.add(l3);p.add(tf2);
                p.add(l4);p.add(tf3);
                
                p.add(l6);p.add(tf5);
                p.add(l7);p.add(tf6);
                p.add(l9);p.add(tf10);
                p2=new JPanel();
                p2.setLayout(new FlowLayout());
                p2.add(b1);p2.add(b2);
                p2.add(b3);p2.add(b4);p2.add(b5);
                p3=new JPanel();
                p3.setLayout(new FlowLayout());
                p3.add(l8);


                String dd[],mm[],yy[];
                dd=new String[31];
                mm=new String[12];
                yy=new String[50];
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

                p3.add(cb1);p3.add(cb2);p3.add(cb3);
                P=new JPanel();
                P.setLayout(new BorderLayout());
                JPanel pp=new JPanel();
                JPanel pan=new JPanel();
                pan.setLayout(new FlowLayout(FlowLayout.CENTER));
                pp.setLayout(new GridLayout(2,1));
                pan.add(title);
                pp.add(pan);
                pp.add(p3);
                P.add(pp,"North");
                P.add(p,"Center");
                P.add(p2,"South");

				b1.addActionListener(this);
                b2.addActionListener(this);
                b3.addActionListener(this);
                b4.addActionListener(this);
                b5.addActionListener(this);
                Container c=getContentPane();
                c.setLayout(new FlowLayout(FlowLayout.CENTER));


                title.setFont(new Font("Arial",Font.BOLD,30));
                c.add(P);
                setSize(700,600);
                tot=recCount();
                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this,"Error"+e);
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
              rs=stm.executeQuery("Select * from VisitorsEntry");
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
			rs=stm.executeQuery("select * from VisitorsEntry");
			i=0;
			while(rs.next())
			{
				i++;
				if(i==recno)
				{
					break;
				}
			if(rs.getString(2).equals("Male"))
			{
				m.setSelected(true);
				fm.setSelected(false);
			}
			else
			{
				fm.setSelected(true);
				m.setSelected(false);
			}
            }

			tf1.setText(rs.getString(1));
			tf2.setText(rs.getString(3));
			tf3.setText(rs.getString(4));
			tf5.setText(rs.getString(5));
			tf6.setText(rs.getString(6));

			cb1.setSelectedItem(rs.getString(7));
			cb2.setSelectedItem(rs.getString(8));
			cb3.setSelectedItem(rs.getString(9));
			tf10.setText(rs.getString(10));


		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error"+e);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
        Object obj=e.getSource();
		if(obj==b1)
		{
            cur =1;
	 		tf1.setText( "");
			tf2.setText("");
			tf3.setText("");			
			tf5.setText("");
			tf6.setText("");
			tf10.setText("");
			m.setSelected(false);
			fm.setSelected(false);
		}
		else
		if(obj==b2)
		{
			String vn,sex,cf,pov,in,ot,d,mn,y,rem,q;
            vn=tf1.getText();

			if(m.isSelected()){
				sex="Male";
            }
            else 
				sex="Female";
            
			cf=tf2.getText();
			pov=tf3.getText();
			in=tf5.getText();
			ot=tf6.getText();
			d=cb1.getSelectedItem()+"";
			mn=cb2.getSelectedItem()+"";
			y=cb3.getSelectedItem()+"";
			rem=tf10.getText();
            try
            {
                q="Insert into VisitorsEntry Values('"+vn+"','"+sex+"','"+cf+"','"+pov+"','"+in+"','"+ot+"',"+mn+","+d+","+y+",'"+rem+"',0)";

     			int x=stm.executeUpdate(q);
				tot++;
				cur=tot;
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