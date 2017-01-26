/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author varun
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class MyThread1 implements Runnable
{
        JLabel l ;
        Thread t;
        String msg="       Office Management System         ";
        MyThread1(JLabel lb)
        {
                l=lb;
                t=new Thread(this);
                t.start();
        }
        public void run()
        {
                String s="";
                while(true)
                {
                        try
                        {
                                s=msg.substring(0,1);
                                msg=msg.substring(1)+s;
                                l.setText(msg);
                                Thread.sleep(200);
                        }catch(InterruptedException e)
                        {
                        }
                }
        }
}



class MainFormAc extends JFrame implements ActionListener
{
	Icon ic=new ImageIcon("4.jpg");
 	JMenuBar mb;
	JLabel l;
    JLabel ico = new JLabel(ic);
    JMenu stafflist, visitors, letters, expenses, print,view,logt;
    JMenuItem als,exls,ex,mexls,dexls,sm,att,tb,alatt,alexp,pex,palex,patt,log;
    String userid;
    JPanel toolbar=new JPanel();
    JButton but[],logout;
    JLabel temp;
    
    AllAttendList aal=null;
    AttendList al=null;
    GiveAtt gat=null;
    ExpensesDateForm edf=null;
    ExpensesForm ef=null;
    ExpensesList el= null;
    MExpensesList mel=null;
    AllExpensesList ael=null;
    String ip;

    MainFormAc(){

    }
    MainFormAc(String s,String v)
  	{
        userid = s;
        ip=v;
		int i;
	    l=new JLabel(" ");
		MyThread1 mt=new MyThread1(l);
        mb= new JMenuBar();
        but=new JButton[2];
        logout = new JButton("Logout");
		int j;
		toolbar.setLayout(new FlowLayout());
        
		String caption[]={"Attendance","Expenses"};
		for(j=0;j<=1;j++)
		{
			but[j]=new JButton(caption[j]);
			toolbar.add(but[j]);
			but[j].addActionListener(this);
		}
        temp = new JLabel("                        ");
        toolbar.add(temp);
        toolbar.add(logout);
        logout.addActionListener(this);
        
     	stafflist= new JMenu("Staff Setup");
     	expenses= new JMenu("Expenses");
        print= new JMenu("Print");
        view= new JMenu("View");
        logt=new JMenu("Logout");
   		als= new JMenuItem("Attendance List");
        ex=new JMenuItem("Add Expenses");
        exls= new JMenuItem("Your Expenses List");
		mexls= new JMenuItem("Monthly Expenses List of all");
		dexls= new JMenuItem("Expenses of all Between Two Dates");
		sm= new JMenuItem("Status Message");
        att=new JMenuItem("Attendance");
        tb= new JMenuItem("Tool Bar");
        alatt = new JMenuItem("Attendance list of all");
        alexp= new JMenuItem("Expenses of all");
        pex= new JMenuItem("Print Your Expenses List");
        palex=new JMenuItem("Print Expenses List of All");
        patt=new JMenuItem("Print Your Attendance List");
        log=new JMenuItem("Logout");


        stafflist.add(att);
		stafflist.add(als);
        stafflist.add(alatt);
        expenses.add(ex);
		expenses.add(exls);
        expenses.add(alexp);
		expenses.add(mexls);
		expenses.add(dexls);
        print.add(patt);
        print.add(pex);
        print.add(palex);
		view.add(sm);
        view.add(tb);
        logt.add(log);
		mb.add(stafflist);
		mb.add(expenses);
        mb.add(print);
		mb.add(view);
        mb.add(logt);
		setJMenuBar(mb);
		stafflist.addActionListener(this);
		expenses.addActionListener(this);
		view.addActionListener(this);
        tb.addActionListener(this);
        alexp.addActionListener(this);
		att.addActionListener(this);
        alatt.addActionListener(this);
        als.addActionListener(this);
        ex.addActionListener(this);
		exls.addActionListener(this);
		mexls.addActionListener(this);
		dexls.addActionListener(this);
		sm.addActionListener(this);
        patt.addActionListener(this);
        pex.addActionListener(this);
        palex.addActionListener(this);
        logt.addActionListener(this);
        log.addActionListener(this);

		Container c=getContentPane();
		c.setLayout(new BorderLayout());
		l.setFont(new Font("Arial",Font.BOLD,20));
        c.add(toolbar,"North");
		
		c.add(l,"South");
		c.add(ico);
		setSize(790,570);
		setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
		setTitle("Office Automation System");

        addWindowListener (new WindowAdapter() {

            @Override
             public void windowClosing (WindowEvent e) {
                System.exit(0);
             }
        } );
	}

	public void actionPerformed(ActionEvent e)
	{
		Object obj=e.getSource();

		if(obj==sm)
		{
			if(l.isVisible())
				l.setVisible(false);
			else
				l.setVisible(true);

		}
        else if(obj == att || obj == but[0])
        {            
            if(gat == null){
                gat =new GiveAtt(userid,ip);
            }else if(!gat.isShowing())
            {
                gat.setVisible(true) ;
            }
        }
		else if(obj==als)
		{			
            if(al == null){
                al = new AttendList(userid,ip);
            }else if(!al.isShowing())
            {
                al.setVisible(true) ;
            }
		}
        else if(obj==alatt){         
            if(aal == null){
                aal = new AllAttendList(ip);
            }else if(!aal.isShowing())
            {
                aal.setVisible(true) ;
            }
        }

		else if(obj==ex || obj == but[1])
		{		
            if(ef == null){
                ef = new ExpensesForm(userid,ip);
            }else if(!ef.isShowing())
            {
                ef.setVisible(true) ;
            }
		}
        else if(obj==exls){
            if(el == null){
                el = new ExpensesList(userid,ip);
            }else if(!el.isShowing())
            {
                el.setVisible(true) ;
            }
        }
		else if(obj==mexls)
		{
            if(mel == null){
                mel = new MExpensesList(ip);
            }else if(!mel.isShowing())
            {
                mel.setVisible(true) ;
            }
		}
		else if(obj==dexls)
		{
            if(edf == null){
                edf = new ExpensesDateForm(ip);
            }else if(!edf.isShowing())
            {
                edf.setVisible(true) ;
            }
		}
        else if(obj==alexp){
            if(ael == null){
                ael = new AllExpensesList(ip);
            }else if(!ael.isShowing())
            {
                ael.setVisible(true) ;
            }
        }
        else if(obj==patt){
            new AttendPrintClass(userid,ip);

        }
        else if(obj == pex) {
            new InExpensesPrintClass(userid,ip);
        }
        else if(obj == palex) {
            new ExpensesPrintClass();
        }
        else if(obj==tb){
            if(toolbar.isVisible())
				toolbar.setVisible(false);
			else
				toolbar.setVisible(true);
        }
        else if(obj==logout || obj==log){
            if(al != null){
                al.dispose();
            }
            if(aal != null){
                aal.dispose();
            }
            if(gat != null){
                gat.dispose();
            }
            if(edf != null){
                edf.dispose();
            }
            if(ef != null){
                ef.dispose();
            }
            if(el != null){
                el.dispose();
            }
            if(mel != null){
                mel.dispose();
            }
            if(ael != null){
                ael.dispose();
            }
            dispose();
            new Login();
        }

	}
	
}