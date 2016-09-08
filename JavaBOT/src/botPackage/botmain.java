package botPackage;
import java.awt.*;
import java.awt.event.*;


public class botmain extends Frame implements ItemListener, ActionListener{
	
	private static final long serialVersionUID = 1L;
	private static MenuBar mnb; 
	private static Menu mnFile, mnHelp;
	private static MenuItem mnitExit, mnitHelp;
	private static Label lb1, lb2, lb3, lb4, lb5, lb6;
	private static TextField tf1, tf2, tf3, tf4, tf5, tf6;
	private static Button bt1, bt2, bt3;
	static TextArea ta1; //isto e uma ta
	
	private static Insets inset;
	
	public botmain(){
		
		setVisible(true);
		setSize(640, 480);
		setResizable(false);
		setLayout(null);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){System.exit(0);}});
		
		mnb = new MenuBar();
		setMenuBar(mnb);
		
		mnFile = new Menu("File");
		mnHelp = new Menu("Help");
		mnb.add(mnFile);
		mnb.add(mnHelp);
		
		mnitExit = new MenuItem("Exit");
		mnFile.add(mnitExit);
		mnitHelp = new MenuItem("Help");
		mnHelp.add(mnitHelp);
		
		inset = getInsets();
		
		lb1 = new Label("Tempo:");
		lb1.setBounds(20 + inset.left, 50 + inset.top, 100, 20);
		add(lb1);
		
		tf1 = new TextField();
		tf1.setBounds(120 + inset.left, 50 + inset.top, 100, 20);
		add(tf1);
		
		lb2 = new Label("Cloro Minimo:");
		lb2.setBounds(20 + inset.left, 100 + inset.top, 100, 20);
		add(lb2);
		
		tf2 = new TextField();
		tf2.setBounds(120 + inset.left, 100 + inset.top, 100, 20);
		add(tf2);
		
		lb3 = new Label("Cloro Máximo:");
		lb3.setBounds(20 + inset.left, 150 + inset.top, 100, 20);
		add(lb3);
		
		tf3 = new TextField();
		tf3.setBounds(120 + inset.left, 150 + inset.top, 100, 20);
		add(tf3);
		
		lb4 = new Label("PH Minímo:");
		lb4.setBounds(20 + inset.left, 200 + inset.top, 100, 20);
		add(lb4);
		
		tf4 = new TextField();
		tf4.setBounds(120 + inset.left, 200 + inset.top, 100, 20);
		add(tf4);
		
		lb5 = new Label("PH Máximo:");
		lb5.setBounds(20 + inset.left, 250 + inset.top, 100, 20);
		add(lb5);
		
		tf5 = new TextField();
		tf5.setBounds(120 + inset.left, 250 + inset.top, 100, 20);
		add(tf5);
		
		lb6 = new Label("ID:");
		lb6.setBounds(20 + inset.left, 300 + inset.top, 100, 20);
		add(lb6);
		
		tf6 = new TextField();
		tf6.setBounds(120 + inset.left, 300 + inset.top, 100, 20);
		add(tf6);
		
		bt1 = new Button("Start");
		bt1.setBounds(55 + inset.left, 350 + inset.top, 100, 30);
		bt1.addActionListener(this);
		add(bt1);
		
		bt2 = new Button("Stop");
		bt2.setBounds(265 + inset.left, 350 + inset.top, 100, 30);
		bt2.addActionListener(this);
		add(bt2);
		
		bt3 = new Button("Exit");
		bt3.setBounds(475 + inset.left, 350 + inset.top, 100, 30);
		bt3.addActionListener(this);
		add(bt3);
		
		ta1 = new TextArea();
		ta1.setEditable(false);
		ta1.setBounds(250 + inset.left, 50 + inset.top, 325, 270);
		add(ta1);
		
	}
	
	public static void main(String[] args) {
		
		botmain app = new botmain();
		app.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
			System.exit(0);	}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getSource()==bt1){
			try {
				botactions.sendPost(Double.parseDouble(tf1.getText()), Double.parseDouble(tf2.getText()), Double.parseDouble(tf3.getText()), Double.parseDouble(tf4.getText()), Double.parseDouble(tf5.getText()), Integer.parseInt(tf6.getText()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//aaa
		
		if (arg0.getSource()==bt2){
			botPackage.botactions.t1.stop();
			
		}
		
		if (arg0.getSource()==bt3){
			System.exit(0);
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
