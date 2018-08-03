import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.tools.DocumentationTool.Location;

public class Whiteboard extends JFrame{
	
	
	public static void main(String[] args)
	{
		Canvas canvas = new Canvas();
		
		
		Whiteboard frame = new Whiteboard();
		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		JPanel row3 = new JPanel();
		JPanel textEdit = new JPanel();	
		JPanel save = new JPanel();
		JPanel vertical = new JPanel();
		Table dataTable = new Table();
		
		JTable table = new JTable(dataTable);
		table.setEnabled(false);
		
		
		
		JButton saveB = new JButton("Save");
		saveB.addActionListener(new Save(canvas.getModels()));
		JButton load = new JButton("Load");
		load.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Load l = new Load();
				
				ArrayList<DShapeModel> model = l.getShapes();
				if(model != null)
				{
					for(int i = 0; i < model.size(); i++)
					{
						canvas.addShape(model.get(i));
					}
				}
			}
		});
		
		JButton savePng = new JButton("Save as PNG");
		savePng.addActionListener(new SaveImage(canvas));
		JLabel ed = new JLabel("Edit/Save Content");
		
		save.add(ed);
		save.add(saveB);
		save.add(load);
		save.add(savePng);
		save.setLayout(new BoxLayout(save, BoxLayout.LINE_AXIS));
		
		JPanel network = new JPanel();
		JLabel net = new JLabel("Networking");
		JButton server = new JButton("Start Server");
		JButton client = new JButton("Start CLient");
		network.setLayout(new BoxLayout(network, BoxLayout.LINE_AXIS));
		
		network.add(net);
		network.add(server);
		network.add(client);
		
		JScrollPane scroll = new JScrollPane(table);
		
		canvas.addMouseMotionListener(new MouseMotionListener(){
			
			
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				// TODO Auto-generated method stub
				
				boolean knob = canvas.getKnob();
				
				
				
				if(canvas.selected != null)
				{
					Point anchor;
					
					if(!knob)
					{
						
						canvas.selected.move(e.getX(), e.getY());
						
						
					}
					
					
					else if(knob)
					{
						
						anchor = canvas.getAnchor();
						canvas.selected.reshape(anchor,canvas.getMover(),e);	
						
					}
				
					}
				
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {	
				
			}
			
		});
		
		JButton line = new JButton("Line");
		
		line.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				DLineModel line = new DLineModel();
							
				canvas.addShape(line);
				canvas.paintComponent();
				line.addListener(dataTable);
				dataTable.addRow(line);
				
			}
		});
		
		JButton rect = new JButton("Rect");
		rect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				DRectModel rectangle = new DRectModel();
				canvas.addShape(rectangle);
				canvas.paintComponent();
				rectangle.addListener(dataTable);
				dataTable.addRow(rectangle);
				dataTable.fireTableDataChanged();
			}
		});
		
		JButton oval = new JButton("Oval");
		oval.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				DOvalModel oval = new DOvalModel();
				canvas.addShape(oval);				
				canvas.paintComponent();
				oval.addListener(dataTable);
				dataTable.addRow(oval);
				dataTable.fireTableDataChanged();
			}
		});
		JButton text = new JButton("Text");
		text.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				DTextModel text = new DTextModel();
				canvas.addShape(text);				
				canvas.paintComponent();
				text.addListener(dataTable);
				dataTable.addRow(text);
				dataTable.fireTableDataChanged();
			}
		});
		JLabel add = new JLabel("Add");
		JButton setColor = new JButton("Set Color");
		setColor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JFrame it = new JFrame();
				JColorChooser choose = new JColorChooser();
				JButton ok = new JButton("Ok");
				ok.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						if(canvas.selected != null)
						{
							canvas.selected.data.setColor(choose.getColor());
						}
					}
				});
				JButton cancel = new JButton("Cancel");
				cancel.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						if(canvas.selected != null)
						{
							canvas.selected.data.setColor(Color.GRAY);
						}
						it.dispose();
					}
				});
				ok.setPreferredSize(new Dimension(50,50));
				cancel.setPreferredSize(new Dimension(100,50));
				JPanel pane = new JPanel();
				pane.add(ok);
				pane.add(cancel);
				it.setLayout(new BorderLayout());
				it.add(choose, BorderLayout.CENTER);
				it.add(pane, BorderLayout.SOUTH);
				it.pack();
				it.setVisible(true);
				it.setEnabled(true);
				
				
				
			}
		});
		JButton moveToFront = new JButton("Move to Front");
		moveToFront.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				canvas.moveToFront();
				canvas.paintComponent();
			}
		});
		JButton moveToBack = new JButton("Move to Back");
		moveToBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				canvas.moveToBack();
				canvas.paintComponent();
			}
		});
		JButton remove = new JButton("Remove Shape");
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{		
				dataTable.remove();
				canvas.remove();
				canvas.paintComponent();
				dataTable.fireTableDataChanged();
			}
		});
		
		JTextField field = new JTextField(50);
		
		
		field.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(canvas.selected != null && canvas.selected.data instanceof DTextModel)
				{
					DTextModel model = (DTextModel) canvas.selected.data;
					
					model.setText(field.getText());
					field.setText("");
				}
			}
		});
		
		GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font [] f = g.getAllFonts();
		ArrayList<String>  x = new ArrayList<String>();
		
		for(Font fo: f)
		{
			x.add(fo.getFontName());
		}
		
		
		
		JComboBox box = new JComboBox(x.toArray());
		box.setMaximumSize(new Dimension(150,25));
		field.setMaximumSize(new Dimension(200,30));
		box.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String font =   (String) box.getSelectedItem();
				
				if(canvas.selected != null && canvas.selected.data instanceof DTextModel)
				{
					DTextModel model = (DTextModel) canvas.selected.data;
					
					model.setFont(font.toString());
					
					
					
				}
			}
		});
		
		canvas.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				int x = e.getX();
				int y = e.getY();
				canvas.setSelected(x,y);
				canvas.setKnobTruth(e.getX(), e.getY());
				dataTable.setRow(canvas.getSelectedIndex());
				box.setEnabled(canvas.textInspector());
				field.setEnabled(canvas.textInspector());
				
			}
			
			public void mouseReleased(MouseEvent e)
			{
				canvas.setKnob(false);
				
			}
		});
		
		
		JLabel edit = new JLabel("Edit Text:  ");
		
		JPanel tab = new JPanel();
		
		tab.setLayout(new BoxLayout(tab, BoxLayout.LINE_AXIS));
		tab.add(scroll);
		
		
		box.setEnabled(canvas.textInspector());
		field.setEnabled(canvas.textInspector());
		
		row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));
		row1.add(add);
		row1.add(rect);
		row1.add(oval);
		row1.add(text);
		row1.add(line);
		row1.setVisible(true);
		row1.setEnabled(true);
		
		textEdit.setLayout(new BoxLayout(textEdit,BoxLayout.LINE_AXIS));
		textEdit.add(edit);
		textEdit.add(field);
		textEdit.add(box);
		
		textEdit.setVisible(true);
		textEdit.setEnabled(true);
		
		
		row2.add(setColor);
		row2.setLayout(new BoxLayout(row2, BoxLayout.LINE_AXIS));
		row2.setVisible(true);
		row2.setEnabled(true);
		
		
		row3.add(moveToFront);
		row3.add(moveToBack);
		row3.add(remove);
		row3.setLayout(new BoxLayout(row3, BoxLayout.LINE_AXIS));
		row3.setVisible(true);
		row3.setEnabled(true);
		
		
		vertical.add(row1);
		vertical.add(textEdit);
		vertical.add(row2);
		vertical.add(row3);
		vertical.add(save);
		vertical.add(network);
		vertical.add(tab);
		vertical.setPreferredSize(new Dimension(400,400));
		vertical.setLayout(new BoxLayout(vertical, BoxLayout.PAGE_AXIS));
		
		for(Component comp: vertical.getComponents())
		{
			((JComponent)comp).setAlignmentX(Box.LEFT_ALIGNMENT);
		}
		
		frame.setLayout(new BorderLayout());
		
		
		frame.add(vertical,BorderLayout.WEST);
		frame.setEnabled(true);
		frame.add(canvas, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
		
	}

}
