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
class LSendForm extends JFrame implements ActionListener
{
	int tot,cur,i;
 	Connection con;
  	Statement stm;
   	ResultSet rs;
	JLabel l1=new JLabel("Letter No.");
	JLabel l2=new JLabel("To");
	JLabel l3=new JLabel("Subject");
	JLabel l4=new JLabel("Enter Date(mm/dd/yy)");
	JTextField tf1=new JTextField(20);
	JTextField tf2=new JTextField(20);
	JTextField tf3=new JTextField(20);
	JComboBox cb1,cb2,cb3;

	JButton b1=new JButton("Reset");
	JButton b2=new JButton("Save");
	JButton b3=new JButton("Next");
	JButton b4=new JButton("Prev");
	JButton b5=new JButton("Exit");
	JPanel p=new JPanel();
	JPanel P=new JPanel();
	JPanel pan=new JPanel();
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
    JPanel p3 = new JPanel();
	Label title=new Label("Letters Sent");
    String ip;
    LSendForm(){

    }
	LSendForm(String s)
	{
		super("Letter Send Form");
        ip=s;
    	try
       	{
       		Class.forName("com.mysql.jdbc.Driver");
      		con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
         	stm=con.createStatement();

            title.setFont(new Font("Arial",Font.BOLD,30));

            p3.add(title);
    		P.setLayout(new BorderLayout());
        	p1=new JPanel();
            p1.setLayout(new FlowLayout());
    		p.setLayout(new GridLayout(4,2));
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

            p3.setLayout(new FlowLayout(FlowLayout.CENTER));

			cb1=new JComboBox(mm);
			cb2=new JComboBox(dd);
			cb3=new JComboBox(yy);
    		p1.add(l4);p1.add(cb1);p1.add(cb2);p1.add(cb3);
        	P.add(p1,"South");
            P.add(p3,"North");
            p.add(l1);p.add(tf1);
            p.add(l2);p.add(tf2);
        	p.add(l3);p.add(tf3);
  
    		p2.setLayout(new FlowLayout());
    		p2.add(b1);p2.add(b2);
    		p2.add(b3);p2.add(b4);p2.add(b5);
    		pan.setLayout(new BorderLayout());
    		pan.add(P,"North");
    		pan.add(p,"Center");
    		pan.add(p2,"South");
    		b1.addActionListener(this);
    		b2.addActionListener(this);
    		b3.addActionListener(this);
        	b4.addActionListener(this);
            b5.addActionListener(this);
            Container c=getContentPane();
    		c.setLayout(new FlowLayout());
        	c.add(pan);
            setSize(600,500);
            tot=recCount();

            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(this,"Error in Connection");
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
                rs=stm.executeQuery("Select * from LettersSend");
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
			rs=stm.executeQuery("select * from LettersSend");
			i=0;
			while(rs.next())
			{
				i++;
                System.out.println(i);
				if(i==recno)
				{
					break;
				}
			}
			tf1.setText(rs.getString(1));
			tf2.setText(rs.getString(2));
			tf3.setText(rs.getString(3));
			cb1.setSelectedItem(rs.getString(4));
			cb2.setSelectedItem(rs.getString(5));
			cb3.setSelectedItem(rs.getString(6));

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
            cur = 0;
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
        }
        else
        if(obj==b2)
        {
            String ln,to,sb,dt,mn,yr,rm,q;
        	ln=tf1.getText();
        	to=tf2.getText();
         	sb=tf3.getText();
        	dt=cb1.getSelectedItem()+"";
            mn=cb2.getSelectedItem()+"";
        	yr=cb3.getSelectedItem()+"";
            
            try
            {
                q="Insert into LettersSend Values("+ln+",'"+to+"','"+sb+"',"+mn+","+dt+","+yr+")";
                int x=stm.executeUpdate(q);
                JOptionPane.showMessageDialog(this,x+ "Record Inserted");
                tot++;
                cur=tot;
            }catch(Exception ex)
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
		