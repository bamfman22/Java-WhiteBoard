import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.*;

public class Canvas extends JPanel implements ModelListener{
	
	private ArrayList<DShape> shapes = new ArrayList<DShape>();
	DShape selected = null;
	protected boolean knob = false;
	protected Point anchor;
	private Point move;
	
	
	
	public Canvas()
	{
		
		this.setPreferredSize(new Dimension(400,400));
		this.setBackground(Color.white);
		this.setVisible(true);
		
		
		
		
	}
	
	public void paintComponent()
	{
		
		Graphics2D g = (Graphics2D) getGraphics().create();
		super.paintComponent(g);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		
		
		
		for(int i = 0; i < shapes.size();i++)
		{	
			
			
			
			if(shapes.get(i) == selected)
			{
				
				selected.selectedDraw(g);
				
			}
			
			else
			{
				shapes.get(i).draw(g);
			}
				
		}
		
		
		
		
		
	}
	
	public void addShape(DShapeModel model)
	{
		
		if(model instanceof DRectModel)
		{		
			DRect rect = new DRect((DRectModel) model);
			shapes.add(rect);
		}
		else if(model instanceof DOvalModel)
		{
			shapes.add(new DOval((DOvalModel) model));
		}
		
		else if(model instanceof DLineModel)
		{
			shapes.add(new DLine((DLineModel) model));
		}
		
		else if(model instanceof DTextModel)
		{
			shapes.add(new DText((DTextModel) model));
		}
		
		model.addListener(this);
	}
	
	public void setSelected(int x, int y)
	{
		for(int i = 0;i < shapes.size(); i++)
		{
			DShape shape = shapes.get(i);
			if(shape.getBounds().contains(x,y))
			{
				selected = shape;
				selected.selectedDraw(getGraphics());
				selected.setPre(x, y);
				break;
							
			}
			
			else
			{
				selected = null;
				paintComponent();
			}
		}
		
		
	}

	
	@Override
	public void modelChanged(DShapeModel model) {
		// TODO Auto-generated method stub
		
		paintComponent();
		
		
		
		
	}
	
	public boolean getKnob()
	{
		return knob;
	}
	
	
	public void setKnob(boolean f)
	{
		knob =f;
	}
	public void setKnobTruth(int x, int y)
	{
		Point click = new Point(x,y);
		
		
		if(selected != null)
		{
			for(int i = 0; i < selected.getKnobRect().size(); i++)
			{
				Rectangle rect = selected.getKnobRect().get(i);
			
				
				Point p = new Point(rect.x,rect.y);
								
				if(p.distance(click) <= 5)
				{
					
					knob = true;
					move = click;
					setAnchor(i);
					break;
					
				}
			}
		
		}
		
	}
	
	public void remove()
	{
		shapes.remove(selected);
		paintComponent();
		
	}
	
	public void moveToFront()
	{
		shapes.remove(selected);
		shapes.add(selected);
		paintComponent();
	}
	
	public Point getAnchor()
	{
		return anchor;
	}
	
	public void moveToBack()
	{
		shapes.remove(selected);
		shapes.add(0, selected);
		paintComponent();
		
	}
	
	
	public Point getMover()
	{
		return move;
	}
	
	public void setAnchor(int i)
	{
		
		
		if(selected.getKnobs().size() > 2)
		{
			
		
			if(i == 0)
			{
				anchor = selected.getKnobs().get(2);
			
			}
			else if(i == 1)
			{
				anchor = selected.getKnobs().get(3);
			}
			else if(i == 2)
			{
				anchor = selected.getKnobs().get(0);
			}
		
			else
			{
				anchor = selected.getKnobs().get(1);
			}
		}
		
		
		else
		{
			if(i == 0)
			{
				anchor = selected.getKnobs().get(1);
			}
			
			else
			{
				anchor = selected.getKnobs().get(0);
			}
				
		}
		
		
		
		
	}
	
	public static Font computeFont(Graphics g)
	{
		double size = 1.0;
		double size2 = size;
		
		Font font = new Font(g.getFont().getFontName(), g.getFont().getStyle(),(int) size);
		
		
		FontMetrics metrics = g.getFontMetrics(font);
		
		double height = metrics.getHeight();
		
		if(height < g.getClipBounds().height)
		{
			while(size < g.getClipBounds().height)
			{
				size2 = size;
				
				size = (size*1.10) +1;
			
			}
		}
		
			Font f = new Font(font.getFontName(),font.getStyle(),(int) size2);
			
			
			return f;
			
		
		
		
		
	}
	
	public boolean textInspector()
	{
		if(selected == null)
		{
			return false;
		}
		else if(selected.data instanceof DTextModel)
		{
			return true;
		}
		
		return false;
	}
	
	
	public int getSelectedIndex()
	{
		
		for(int i = 0; i < shapes.size(); i++)
		{
			if(shapes.get(i) == selected)
				{
					return i;
				}				
		}
		
		return 0;
	}

	public ArrayList<DShapeModel> getModels()
	{
		
		
		ArrayList<DShapeModel> m = new ArrayList<DShapeModel>();
		
		for(int i = 0; i < shapes.size(); i++)
		{
			m.add(shapes.get(i).data);
		}
		
		
		return m;
	}
}
