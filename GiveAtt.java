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
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class GiveAtt extends JFrame implements Runnable,ActionListener{
    Connection con;
    Statement stm;
    ResultSet rs,rs1;
    String check;
    Date todaysDate = new java.util.Date();
    SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd-MMM-yyyy");
    String formattedDate = formatter.format(todaysDate);

    JLabel enter = new JLabel("CLICK IN TO GIVE ATTENDANCE");
    JLabel exit = new JLabel("CLICK OUT TO LEAVE OFFICE   ");

    String userid;
    JButton in;
    JButton out;

    Label title = new Label ("Enter attendance");

	private Thread myThread;

	JLabel clock = new JLabel();

	public void start()
	{
		if (myThread == null)
		{
			myThread = new Thread(this, "clock");
			myThread.start();
		}
	}

    @SuppressWarnings("static-access")
	public void run()
	{
		while (myThread == Thread.currentThread())
		{ // gets data for the clock

		Date dtime = new Date();
		clock.setText(DateFormat.getTimeInstance(DateFormat.MEDIUM).format(dtime));
		try
		{ 
			myThread.sleep(1000);
		}
		catch (InterruptedException e) {}
		}
	}


    JLabel time = new JLabel("CURRENT TIME : ");
    JLabel tdate = new JLabel("TODAY'S DATE :  " + formattedDate + "              ");
    String ip;

    GiveAtt(){
        
    }

    GiveAtt(String s,String v)
    {
        userid = s;
        ip=v;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
            stm = con.createStatement();

            in = new JButton ("    IN     ");
            out = new JButton ("    OUT    ");

            in.addActionListener(this);
            out.addActionListener(this);

            setSize(500,500);

            JPanel p1 = new JPanel();
            JPanel p2 = new JPanel();
            JPanel p3 = new JPanel();
            JPanel p4 = new JPanel();
            JPanel p5 = new JPanel();

            title.setFont(new Font("Arial",Font.BOLD,30));
            enter.setFont(new Font("Arial",Font.BOLD,15));
            exit.setFont(new Font("Arial",Font.BOLD,15));

            p1.setLayout(new GridLayout(4,1));
            p2.setLayout(new FlowLayout(FlowLayout.CENTER,100,1));
            p3.setLayout(new FlowLayout(FlowLayout.CENTER,100,1));
            p4.setLayout(new FlowLayout(FlowLayout.CENTER,1,1));
            p5.setLayout(new FlowLayout(FlowLayout.CENTER,100,1));

            p2.add(enter);
            p2.add(in);
            p3.add(exit);
            p3.add(out);
            p4.add(tdate);
            p4.add(time);
			p4.add(clock);
            p5.add(title);
            p1.add(p5);
            p1.add(p2);
            p1.add(p3);
            p1.add(p4);

            start();

            this.add(p1);
            int count=0;

            rs = stm.executeQuery("select Empid from attendance where (date=curdate()) and (attendance.empid = (select account.empid from account where Username='"+userid+"')) ");
            while(rs.next()){
                count++;
            }
            System.out.println(count);
            if(count !=0){
                rs1 = stm.executeQuery("select outtime from attendance where (date=curdate()) and (attendance.empid = (select account.empid from account where Username='"+userid+"')) ");
                while(rs1.next()){
                    check = rs1.getString("OutTime");
                }
                System.out.println(check);
                if(check != null){
                    in.setEnabled(false);
                    out.setEnabled(false);
                    JOptionPane.showMessageDialog(this,"You have already given your Attendance for today");

                }else{
                    in.setEnabled(false);
                    out.setEnabled(true);
                    JOptionPane.showMessageDialog(this,"You have to enter ur leaving time");
                }
            }else{
                in.setEnabled(true);
                out.setEnabled(false);
                JOptionPane.showMessageDialog(this,"You have to give ur attendance for today");
            }
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this,"error:"+e);
        }

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        setTitle("Office Automation System");
        addWindowListener (new WindowAdapter() {

            @Override
                public void windowClosing (WindowEvent e) {
                    dispose();
                }
        } );

    }
    public void actionPerformed(ActionEvent ex){
        Object ob = ex.getSource();

        if (ob == out){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
                stm = con.createStatement();
                stm.executeUpdate("update attendance set OutTime=(curtime()) where (Empid = (select Account.Empid from Account where Username='"+userid+"')) and Date=(curdate())");
                stm.close();
            }
            catch(Exception ee){System.out.println(ee);}
            JOptionPane.showMessageDialog(this,"You can now leave the Office");
            out.setEnabled(false);
        }else

        if (ob == in){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","root","varun");
                stm = con.createStatement();
                stm.executeUpdate("insert into attendance(Empid,Date,InTime) values((select account.empid from account where username='"+userid+"'),curdate(),curtime())");
                stm.close();
            }
            catch(Exception e){System.out.println (e);}
            JOptionPane.showMessageDialog(this,"You have entered the Attendance");
            in.setEnabled(false);
            out.setEnabled(true);
  
        }
        
    }

}
