package myCad;

import java.awt.Color;
import java.io.*;

import javax.swing.*;

public class MyFile {
	private MyCad mycad;
	private MyCanvas canvas;
    public MyFile(MyCad mycad,MyCanvas canvas) {
		this.mycad = mycad;
		this.canvas = canvas;
	}
    
	public void newFile() {
		canvas.setIndex(0);
		canvas.setCurrentShape(0); 
		canvas.setColor(Color.black); 
		canvas.createNewitem();
		canvas.repaint();
	}

	public void openFile() {
		JFileChooser filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		 
	    int returnVal = filechooser.showOpenDialog(mycad);
	    if(returnVal == JFileChooser.CANCEL_OPTION) { return;}
	    
	    File fileName = filechooser.getSelectedFile(); 
	    fileName.canRead();
	    if(fileName == null || fileName.getName().equals("")) {
	    	JOptionPane.showMessageDialog(filechooser,"filename","Please enter the filename",JOptionPane.ERROR_MESSAGE);
	    }
	    
	    else {
			try {
				FileInputStream ifs = new FileInputStream(fileName);
				ObjectInputStream input = new ObjectInputStream(ifs);
				
				int countNumber = 0;
				Shape inputRecord;
				countNumber = input.readInt();
				for(int i =0;i<countNumber;i++)
				{
					canvas.setIndex(i);
					inputRecord = (Shape)input.readObject();
					canvas.itemList[i] = inputRecord;
				}
				canvas.createNewitem();
				input.close();
				canvas.repaint();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(mycad,"not found file","eerofile",JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(mycad,"read erro","read erro",JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	    }
	}
    
	public void saveFile() {
		JFileChooser filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int result = filechooser.showSaveDialog(mycad);
		if(result == JFileChooser.CANCEL_OPTION){
        	return ;
        }
        
        File fileName = filechooser.getSelectedFile(); 
	    fileName.canWrite(); 
	    if(fileName == null || fileName.getName().equals("")) {
	    	JOptionPane.showMessageDialog(filechooser,"fileName","Enter the filename",JOptionPane.ERROR_MESSAGE);
	    }
	    else {
	    	try {
				fileName.delete(); 
				FileOutputStream fos = new FileOutputStream(fileName+".nife"); 
				 
				ObjectOutputStream output = new ObjectOutputStream(fos);
				
				output.writeInt(canvas.getIndex());
				
				for(int i = 0;i<canvas.getIndex() ;i++)
				{
					Shape p = canvas.itemList[i];
					output.writeObject(p);
					output.flush(); 
					                
				}
				output.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}
}
