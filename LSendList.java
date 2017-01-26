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
import java.util.Vector;
import java.sql.*;

class LSendList extends JFrame
{

         Connection con;
         Statement stm;
       	 ResultSet rs;
         Vector col,data,row;
         JTable tb;
         Label title=new Label("Letters Sent List");
         String ip;
         LSendList(){
             
         }
         LSendList(String s)
        {
            super("Letters Send List");
            ip=s;
            title.setFont(new Font("Arial",Font.BOLD,30));
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
                stm=con.createStatement();
                rs=stm.executeQuery("Select * from LettersSend");

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
                JPanel p1 = new JPanel();
                p1.add(title);
                c.add(p1,"North");
                c.add(new JScrollPane(tb),"Center");
                setSize(700,500);
                setLocationRelativeTo(null);
            }
            catch(Exception e)
            {
            }
            setResizable(false);
            setVisible(true);

        }
}
