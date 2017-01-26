/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author karthik
 */
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
public class Login extends JFrame implements ActionListener{
    Connection con;
    Statement stm;
    ResultSet rs;
    String pass,user,des;
    String in_user,in_pass;
    String Ad = new String("Administrator");
    String Ac = new String("Accountant");
    String Re = new String("Receptionist");
    String Em = new String("Employee");
    int i,user_length,pass_length;
	JLabel luid=new JLabel("User Id");
	JLabel lpsw=new JLabel("Password");
    JLabel ipdb=new JLabel("Enter IP of the database server");

	JTextField tfuid=new JTextField(10);
	JPasswordField tfpsw=new JPasswordField(10);
    JTextField tfip=new JTextField(15);

    JButton bsi=new JButton("Sign In");
    JButton bq=new JButton("Quit");

    String ip;
    JPanel p=new JPanel();
	Label title=new Label("Office Management Login Form");

    Login()
	{
        super("Login Form");

		p.setLayout(new GridLayout(8,2));
		p.add(luid);p.add(tfuid);
		p.add(lpsw);p.add(tfpsw);
        p.add(ipdb);p.add(tfip);

        p.add(bsi);p.add(bq);

		bsi.addActionListener(this);
		bq.addActionListener(this);

 		Container c=getContentPane();
		c.setLayout(new FlowLayout());
		JPanel jp=new JPanel();
		jp.setLayout(new BorderLayout());
		title.setFont(new Font("Arial",Font.BOLD,20));
        JPanel pp = new JPanel();
        pp.setLayout(new FlowLayout(FlowLayout.CENTER));
        pp.add(title);
		jp.add(pp,"North");
		jp.add(p,"Center");
		c.add(jp);

        getRootPane().setDefaultButton(bsi);

        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch(Exception ee){
        }

        addWindowListener (new WindowAdapter() {

            @Override
             public void windowClosing (WindowEvent e) {
                System.exit(0);
            }
        } );
        setSize(500,500);
		setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);


	}
    public void actionPerformed(ActionEvent e)
	{

                Object obj = e.getSource();

                in_pass = new String(tfpsw.getPassword());
                in_user = tfuid.getText();
                ip = tfip.getText();
                user_length = in_user.length();
                pass_length = in_pass.length();

                if(obj==bsi)
                {
                  
                   try{
                       Class.forName("com.mysql.jdbc.Driver");
                       Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/office","system","1234");
                       stm = cn.createStatement();
                       rs = stm.executeQuery("select Password,Designation from Account where Username ='"+in_user+"'");
                       
                       i=0;
                       while(rs.next()){
                                   i++;
                                   pass = rs.getString("Password");
                                   des = rs.getString("Designation");

                                    if(in_pass.equals(pass.toString()) && des.equals(Ad.toString())){
                                                        logedInA();
                                    }
                                    else if(in_pass.equals(pass.toString()) && des.equals(Ac.toString())){
                                                      logedIn();
                                    }
                                    else if(in_pass.equals(pass.toString()) && des.equals(Re.toString())){
                                                      loginRe();
                                    }
                                    else if(in_pass.equals(pass.toString()) && des.equals(Em.toString())){
                                                        logedInEm();
                                    }
                                    else {
                                        loginFail();
                                    }
                       }
                       if(user_length == 0){
                           JOptionPane.showMessageDialog(this,"Username cannot be left blank");
                       }else if(pass_length == 0){
                           JOptionPane.showMessageDialog(this,"Password field cannot be left blank");
                       }else if(i==0){
                           JOptionPane.showMessageDialog(this,"Username doesnot exist");
                       }
                     stm.close();
                     rs.close();
                  }

                  catch(Exception ae){
                        JOptionPane.showMessageDialog(this,"Cannot connect to main server ");
                  }

                }

                else if(obj==bq)
                {
                    System.exit(0);
                }
	}

    public void logedIn(){

        try
        {
            dispose();
            new MainFormAc(in_user,ip);

        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this,"Failed loading");
        }
    }

    public void logedInA(){

        try
        {
             dispose();
             new MainForm(in_user,ip);

        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this,"Failed loading");
         
        }
}

public void loginRe(){
    try
    {
        dispose();
        new MainFormRe(in_user,ip);

    }
    catch (Exception ex)
    {
        JOptionPane.showMessageDialog(this,"Failed loading");
    }
}
    public void logedInEm(){

        try
        {
            dispose();
            new MainFormEmp(in_user,ip);

        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this,"Failed loading");
        }
    }

    public void loginFail()
    {
        JOptionPane.showMessageDialog(this,"Password Incorrect Plz Try again");
    }

    public static void main (String a[]){
        Login test= new Login();
    }


}