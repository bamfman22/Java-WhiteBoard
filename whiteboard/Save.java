import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Save implements ActionListener{

	ArrayList<DShapeModel> models;
	
	public Save(ArrayList<DShapeModel> model)
	{
		models = model;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		
		JFrame frame = new JFrame();
		JPanel pane = new JPanel();
		JTextField text = new JTextField(15);
		text.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String fileName = text.getText();
				
				XMLEncoder x;
				try {
					
					x = new XMLEncoder( new BufferedOutputStream(new FileOutputStream(fileName)));
				
					x.writeObject(models);
					x.close();
				
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
		
        
		
		
		
	}

}
