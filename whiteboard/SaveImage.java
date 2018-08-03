import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SaveImage implements ActionListener{

	Canvas canvas;
	
	public SaveImage(Canvas canvas)
	{
		this.canvas = canvas;
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
				BufferedImage bi = new BufferedImage(canvas.getSize().width, canvas.getSize().height, BufferedImage.TYPE_INT_ARGB); 
				Graphics g = bi.createGraphics();
				canvas.paint(g);  //this == JComponent
				
				try{ImageIO.write(bi,"png",new File(fileName));}catch (Exception x) {}
				
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
