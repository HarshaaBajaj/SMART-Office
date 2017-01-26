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
class LetterReceivedSenderForm extends JFrame implements ActionListener
{
	Connection con;
	Statement stm;
    ResultSet rs;
	JComboBox cb1;
	JButton b1;
	JTable tb;
	JLabel l1,l2;
	JPanel p1,p2;
    Vector col,data,row;
    JLabel temp;
    String ip;
    LetterReceivedSenderForm(){

    }
	LetterReceivedSenderForm(String s)
	{
		super("LetterReceivedSenderForm");
        ip=s;
		p1=new JPanel();
		p2=new JPanel();
		l1=new JLabel("Sender");
		l2=new JLabel("Letters Received From Specified Sender");
		l2.setFont(new Font("Arial",Font.BOLD,20));
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
        stm=con.createStatement();
		rs=stm.executeQuery("Select distinct FromAdd from LettersReceived");

        Container c=getContentPane();

        c.setLayout(new BorderLayout());
		cb1=new JComboBox();
		b1=new JButton("Load");
        temp = new JLabel("                                                 ");
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
        p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.add(l1);p2.add(temp);p2.add(cb1);p2.add(temp);p2.add(b1);p1.add(l2);

		while(rs.next())
		{

			cb1.addItem(rs.getString(1)+"");
		}


		JPanel jp=new JPanel();
		jp.setLayout(new BorderLayout());

		jp.add(p1,"North");
		jp.add(p2);
		c.add(jp,"North");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		b1.addActionListener(this);
		setSize(500,500);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

		
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
		String s=ae.getActionCommand();
		if(s.equals("Load"))
		{
			String q,f;
			f=cb1.getSelectedItem()+"";
			q="Select * from LettersReceived where FromAdd='"+f+"'";
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
                Container c=getContentPane();
                c.add(new JScrollPane(tb),"Center");
                c.doLayout();
			
		}
		}catch(Exception e)
		{
            System.out.println(e);
		}
	}
}

