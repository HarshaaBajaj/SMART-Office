/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author varun
 */
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;
class AllAttendList extends JFrame
{
	Connection con;
 	Statement stm;
    ResultSet rs;
	JTable tb;
    Vector col,data,row;
	Label title=new Label("Attendance List OF All Employees");
    String ip;

    AllAttendList() {
        
    }
    AllAttendList(String s)
 	 {
		super("Attendance List");
        ip=s;
		title.setFont(new Font("Arial",Font.BOLD,30));
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
            stm=con.createStatement();
			rs=stm.executeQuery("Select * from Attendance");

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
            JPanel pp= new JPanel();
            pp.setLayout(new FlowLayout(FlowLayout.CENTER));
            pp.add(title);
			c.add(pp,"North");
            c.add(new JScrollPane(tb),"Center");
			setSize(800,600);

        }
		catch(Exception e)
		{
            JOptionPane.showMessageDialog(this,"Error "+e);
		}
		setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
	 }
}


