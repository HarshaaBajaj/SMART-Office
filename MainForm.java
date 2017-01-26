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
class MyThread implements Runnable
{
        JLabel l ;
        Thread t;
        String msg="       Office Management System         ";
        MyThread(JLabel lb)
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



class MainForm extends JFrame implements ActionListener
{
	Icon ic=new ImageIcon("4.jpg");
	JPanel toolbar=new JPanel();
 	JMenuBar mb;
	JLabel l;
    JLabel ico = new JLabel(ic);
    JMenu staffSetup, visitors, letters, expenses, print,view,logt;
    JMenuItem stf,alexp,aduse,ve, lr, ls, ex, tb,sm,sls,vels,dvels,lrls,mlrls,lsls,mlsls,lsdl,lrdl,lrsl,exls,mexls,dexls,eat,oet,lrt,lst,sst,vet,alatt,log;
	JButton but[],logout;
    String userid;
    JLabel temp;
    StaffList sl=null;
    AddUserForm auf=null;
    AllAttendList aal=null;
    DVEntryList dvl=null;
    DeleteUserForm duf=null;
    ExpensesDateForm edf=null;
    ExpensesForm ef=null;
    ExpensesList el= null;
    LReceivedForm lrf=null;
    LReceivedList lrl=null;
    LSendForm lsf=null;
    LSendList lsl=null;
    LetterReceivedDateForm lrdf=null;
    LetterReceivedSenderForm lrsf=null;
    LetterSendDateForm lsdf=null;
    MExpensesList mel=null;
    MLReceivedList mlrl = null;
    MLSendList mlsl = null;
    StaffForm sf=null;
    VEntryForm vef=null;
    VEntryList vel=null;
    AllExpensesList ael=null;
    String ip;
    
    MainForm(){
        System.out.println("Nothing");
    }
  	MainForm(String s,String v)
  	{
        userid = s;
        ip=v;
		but=new JButton[7];
        logout= new JButton("Logout");
		int i;
		toolbar.setLayout(new FlowLayout());
        
		String caption[]={"Staff","VisitorsEntry", "LettersReceived","LettersSent","Expenses","AddUser","DeleteUser"};
		for(i=0;i<=6;i++)
		{
			but[i]=new JButton(caption[i]);
			toolbar.add(but[i]);
			but[i].addActionListener(this);
		}
        temp = new JLabel("            ");
        toolbar.add(temp);
        toolbar.add(logout);
        logout.addActionListener(this);

            l=new JLabel(" ");
            MyThread mt=new MyThread(l);
    		mb= new JMenuBar();
     		staffSetup= new JMenu("Staff Setup");
     		visitors= new JMenu("Visitors");
     		letters= new JMenu("Letters");
     		expenses= new JMenu("Expenses");
            print=new JMenu("Print");
     		view= new JMenu("View");
            logt= new JMenu("Logout");
            stf= new JMenuItem("Staff");
            sls= new JMenuItem("Staff List");
     		ve= new JMenuItem("Visitors Entry");
            vels= new JMenuItem("Visitors Entry List");
            dvels= new JMenuItem("Daily Visitors Entry List");
     		lr= new JMenuItem("Letters Received");
     		ls= new JMenuItem("Letters Sent");
            lrls= new JMenuItem("Letters Received List");
            lsls= new JMenuItem("Letters Sent List");
            mlrls= new JMenuItem("Monthly Letters Received List");
            mlsls= new JMenuItem("Monthly Letters Sent List");
            lsdl= new JMenuItem("letters Sent Between Two Dates");
            lrdl= new JMenuItem("Letters Received Between Two Dates");
            lrsl= new JMenuItem("Letters Received From Specified Sender");
            ex= new JMenuItem("Add Expenses");
            exls= new JMenuItem("Expenses List");
            mexls= new JMenuItem("Monthly Expenses List of all Employees");
            dexls= new JMenuItem("Expenses of all Between Two Dates");
            eat= new JMenuItem("Employee Attendance Table");
            oet= new JMenuItem("Office Expenses Table");
            lrt= new JMenuItem("Letters Received Table");
            lst= new JMenuItem("Letters Sent Table");
            sst= new JMenuItem("Staff List Table");
            vet= new JMenuItem("Visitor Entry Table");
            tb= new JMenuItem("Tool Bar");
            sm= new JMenuItem("Status Message");
            aduse = new JMenuItem("Add User");
            alatt = new JMenuItem("Attendance list of all");
            alexp = new JMenuItem("Expenses of all");
            log= new JMenuItem("Logout");
            staffSetup.add(stf);

            staffSetup.add(sls);
            staffSetup.add(alatt);
            visitors.add(ve);
            visitors.add(vels);
            visitors.add(dvels);
            letters.add(lr);
            letters.add(ls);
            letters.add(lrls);
            letters.add(mlrls);
            letters.add(lsls);
            letters.add(mlsls);
            letters.add(lsdl);
            letters.add(lrdl);
            letters.add(lrsl);
            expenses.add(ex);
            expenses.add(exls);
            expenses.add(alexp);
            expenses.add(mexls);
            expenses.add(dexls);
            print.add(eat);
            print.add(oet);
            print.add(lrt);
            print.add(lst);
            print.add(sst);
            print.add(vet);
            view.add(tb);
            view.add(sm);
            logt.add(log);
            mb.add(staffSetup);
            mb.add(visitors);
            mb.add(letters);
            mb.add(expenses);
            mb.add(print);
            mb.add(view);
            mb.add(logt);

            setJMenuBar(mb);
            staffSetup.addActionListener(this);
            visitors.addActionListener(this);
            letters.addActionListener(this);
            expenses.addActionListener(this);
            view.addActionListener(this);
            stf.addActionListener(this);
            logt.addActionListener(this);

            ve.addActionListener(this);
            lr.addActionListener(this);
            ls.addActionListener(this);
            ex.addActionListener(this);
            tb.addActionListener(this);
            sm.addActionListener(this);
            sls.addActionListener(this);
            vels.addActionListener(this);
            dvels.addActionListener(this);
            lrls.addActionListener(this);
            mlrls.addActionListener(this);
            lsls.addActionListener(this);
            mlsls.addActionListener(this);
            lsdl.addActionListener(this);
            lrdl.addActionListener(this);
            lrsl.addActionListener(this);
            exls.addActionListener(this);
            mexls.addActionListener(this);
            dexls.addActionListener(this);
            eat.addActionListener(this);
            oet.addActionListener(this);
            lrt.addActionListener(this);
            lst.addActionListener(this);
            sst.addActionListener(this);
            vet.addActionListener(this);
            alatt.addActionListener(this);
            alexp.addActionListener(this);
            log.addActionListener(this);

            Container c=getContentPane();
            c.setLayout(new BorderLayout());
            c.add(toolbar,"North");
            l.setFont(new Font("Arial",Font.BOLD,20));
            c.add(l,"South");
            c.add(ico);
            setSize(850,590);
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
		if(obj==stf || obj==but[0])
		{
			if(sf == null){
                sf = new StaffForm(ip);
            }else if(!sf.isShowing())
            {
                sf.setVisible(true) ;
            }
		}
		else if(obj==ve || obj==but[1])
		{
			if(vef == null){
                vef = new VEntryForm(ip);
            }else if(!vef.isShowing())
            {
                vef.setVisible(true) ;
            }
		}
		else if(obj==lr || obj==but[2] )
		{
			if(lrf == null){
                lrf = new LReceivedForm(ip);
            }else if(!lrf.isShowing())
            {
                lrf.setVisible(true) ;
            }
		}
		else if(obj==ls || obj==but[3])
		{
			if(lsf == null){
                lsf = new LSendForm(ip);
            }else if(!lsf.isShowing())
            {
                lsf.setVisible(true) ;
            }
		}
		else if(obj==ex || obj==but[4])
		{
			if(ef == null){
                ef = new ExpensesForm(userid,ip);
            }else if(!ef.isShowing())
            {
                ef.setVisible(true) ;
            }
		}

		else if(obj==but[5])
        {
            if(auf == null){
                auf = new AddUserForm(ip);
            }else if(!auf.isShowing())
            {
                auf.setVisible(true) ;
            }
        }
        else if(obj==but[6])
        {
            if(duf == null){
                duf = new DeleteUserForm(ip);
            }else if(!duf.isShowing())
            {
                duf.setVisible(true) ;
            }
        }
        else if(obj==tb)
		{
			if(toolbar.isVisible())
				toolbar.setVisible(false);
			else
				toolbar.setVisible(true);

		}
		else if(obj==sm)
		{
			if(l.isVisible())
				l.setVisible(false);
			else
				l.setVisible(true);

		}
		else if(obj==sls)
		{
            if(sl == null){
                sl = new StaffList(ip);
            }else if(!sl.isShowing())
            {
                sl.setVisible(true) ;                
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
		else if(obj==lrls)
		{
            if(lrl == null){
                lrl = new LReceivedList(ip);
            }else if(!lrl.isShowing())
            {
                lrl.setVisible(true) ;
            }
		}
		else if(obj==mlrls)
		{
            if(mlrl == null){
                mlrl = new MLReceivedList(ip);
            }else if(!mlrl.isShowing())
            {
                mlrl.setVisible(true) ;
            }
		}
		else if(obj==lsls)
		{			
            if(lsl == null){
                lsl = new LSendList(ip);
            }else if(!lsl.isShowing())
            {
                lsl.setVisible(true) ;
            }
		}
		else if(obj==mlsls)
		{
            if(mlsl == null){
                mlsl = new MLSendList(ip);
            }else if(!mlsl.isShowing())
            {
                mlsl.setVisible(true) ;
            }
		}
		else if(obj==lsdl)
		{			
            if(lsdf == null){
                lsdf = new LetterSendDateForm(ip);
            }else if(!lsdf.isShowing())
            {
                lsdf.setVisible(true) ;
            }
		}
		else if(obj==lrdl)
		{			
            if(lrdf == null){
                lrdf = new LetterReceivedDateForm(ip);
            }else if(!lrdf.isShowing())
            {
                lrdf.setVisible(true) ;
            }
		}
		else if(obj==lrsl)
		{			
            if(lrsf == null){
                lrsf = new LetterReceivedSenderForm(ip);
            }else if(!lrsf.isShowing())
            {
                lrsf.setVisible(true) ;
            }
		}
		else if(obj==vels)
		{			
            if(vel == null){
                vel = new VEntryList(ip);
            }else if(!vel.isShowing())
            {
                vel.setVisible(true) ;
            }
		}
		else if(obj==dvels)
		{
            if(dvl == null){
                dvl = new DVEntryList(ip);
            }else if(!dvl.isShowing())
            {
                dvl.setVisible(true) ;
            }
		}
		else if(obj==exls)
		{			
            if(el == null){
                el = new ExpensesList(userid,ip);
            }else if(!el.isShowing())
            {
                el.setVisible(true) ;
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
		else if(obj==eat)
		{
			new AllAttendPrintClass(ip);
		}
        else if(obj==oet)
		{
			new ExpensesPrintClass(ip);
		}
		else if(obj==lrt)
		{
			new LettersReceivedPrintClass(ip);
		}
		else if(obj==lst)
		{
			new LettersSendPrintClass(ip);
		}
		else if(obj==sst)
		{
			new StaffListPrintClass(ip);
		}
		else if(obj==vet)
		{
			new VisitorEntryPrintClass(ip);
        }
        else if(obj==logout || obj==log){
            if(sl != null){
                sl.dispose();
            }
            if(auf != null){
                auf.dispose();
            }
            if(aal != null){
                aal.dispose();
            }
            if(dvl != null){
                dvl.dispose();
            }
            if(duf != null){
                duf.dispose();
            }
            if(edf != null){
                edf.dispose();
            }
            if(el != null){
                el.dispose();
            }
            if(ef != null){
                ef.dispose();
            }
            if(lrl != null){
                lrl.dispose();
            }
            if(lrf != null){
                lrf.dispose();
            }
            if(lsf != null){
                lsf.dispose();
            }
            if(lsl != null){
                lsl.dispose();
            }
            if(lrdf != null){
                lrdf.dispose();
            }
            if(lrsf != null){
                lrsf.dispose();
            }
            if(lsdf != null){
                lsdf.dispose();
            }
            if(mel != null){
                mel.dispose();
            }
            if(mlrl != null){
                mlrl.dispose();
            }
            if(mlsl != null){
                mlsl.dispose();
            }
            if(sf != null){
                sf.dispose();
            }
            if(vef != null){
                vef.dispose();
            }
            if(vel != null){
                vel.dispose();
            }
            if(ael != null){
                ael.dispose();
            }

            dispose();
            new Login();
            
        }

	}

}