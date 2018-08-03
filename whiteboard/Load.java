import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Load{

	ArrayList<DShapeModel> models;
	
	public ArrayList<DShapeModel> getShapes(){
		
		JFrame frame = new JFrame();
		JPanel pane = new JPanel();
		JTextField text = new JTextField(15);
		text.addActionListener(new ActionListener(){
			
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			{
				String fileName = text.getText();
				
				XMLEncoder x;
				try {
					
					XMLDecoder d = new XMLDecoder( new BufferedInputStream( new FileInputStream(fileName)));
					models = (ArrayList<DShapeModel>) d.readObject();
	       			d.close();
				
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				frame.dispose();
			}
		});
		frame.setTitle("Please Enter FileName");
		
		frame.setSize(350, 100);
		pane.add(text);
		pane.setVisible(true);
		frame.add(pane);
		frame.setVisible(true);
		frame.setEnabled(true);
		
        
		return models;
		
		
	}

}
