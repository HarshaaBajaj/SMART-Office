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
class MLSendList extends JFrame implements ActionListener
{
        Connection con;
        Statement stm;
        ResultSet rs;

        JTable tb;
        Vector col,data,row;
        JLabel l=new JLabel("Enter Date(mm/yy)");
        JComboBox cb1,cb2;
        JButton b1=new JButton("Show");
        JButton b2=new JButton("Exit");
        Label title=new Label("Monthly Letters Sent List");
        JPanel p=new JPanel();
        JScrollPane jsp;
        Container c;
        String ip;
        MLSendList(){

        }
        MLSendList(String s)
        {
            super("MLSend List");
            ip=s;
            try
            {
                    Class.forName("com.mysql.jdbc.Driver");
                	con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
                	stm=con.createStatement();

                    jsp=new JScrollPane(tb);
                    c=getContentPane();
                    c.setLayout(new BorderLayout());
                    JPanel p1 = new JPanel();
                    p1.add(title);
                    c.add(p1,"North");
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
                    title.setBackground(Color.pink);
                    b1.addActionListener(this);
                    b2.addActionListener(this);
                    setSize(750,550);
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
                q="Select * from LettersSend where Month='"+mn+"'  and Year= '"+yr+"'" ;
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
				c.remove(jsp);

				jsp=new JScrollPane(tb);
				c.add(jsp,"South");
				c.doLayout();

			}
		 	if(obj==b2)
			{
				setVisible(false);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"error "+ex);
		}
	}

}

