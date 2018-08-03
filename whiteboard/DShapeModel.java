import java.awt.*;
import java.util.ArrayList;

import javax.xml.bind.Marshaller.Listener;

public class DShapeModel {
	
	
	Rectangle bounds;
	Color color;
	ArrayList<ModelListener> listeners = new ArrayList<ModelListener>();
	Point p1;
	Point p2;
	
	public DShapeModel()
	{
		bounds  = new Rectangle(0,0,0,0);
		color = Color.GRAY;
	}
	
	public void setBounds(int x,int y,int width,int height)
	{
		bounds.setBounds(x, y, width, height);
		
		for(int i = 0; i < listeners.size(); i++)
		{
			listeners.get(i).modelChanged(this);
		}
		
		
	}
	
	public void setBounds(Rectangle rec)
	{
		bounds.setBounds(rec);
		
		for(int i = 0; i < listeners.size(); i++)
		{
			listeners.get(i).modelChanged(this);
		}
		
	}
	
	public void setColor(Color color)
	{
		this.color = color;
		
		for(int i = 0; i < listeners.size(); i++)
		{
			listeners.get(i).modelChanged(this);
		}
	}
	
	public Color getColor()
	{
		return color;
	}

	public Rectangle getBounds()
	{
		return bounds;
	}
	
	public void addListener(ModelListener l)
	{
		listeners.add(l);
	}
	
	public void removeListener(ModelListener l)
	{
		listeners.remove(l);
	}
}
