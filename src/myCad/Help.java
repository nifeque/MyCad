package myCad;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Help extends JFrame {
	private MyCad  mycad;
	public Help(MyCad mycad) {
		this.mycad = mycad;
	}
	
	public void tipsHelp() {
		JOptionPane.showMessageDialog(this,"a simple progarm","tips",JOptionPane.WARNING_MESSAGE);
	} 
	public void aboutHelp() {
		JOptionPane.showMessageDialog(mycad,"Mycad"+"\n"+"   By Nifeqc","about", JOptionPane.WARNING_MESSAGE);
	}

}
