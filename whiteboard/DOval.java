import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class DOval extends DShape{

	
	
	public DOval()
	{
		data.addListener(this);
	}
	
	public DOval(DOvalModel model)
	{
		data = model;
		data.addListener(this);
	}
	@Override
	public void draw(Graphics g) {
		
		g.setColor(data.getColor());
		g.fillOval(data.getBounds().x, data.getBounds().y, data.getBounds().width, data.getBounds().height);
		
	}
	
	
	public void selectedDraw(Graphics g)
	{
		
		g.setColor(data.getColor());
		g.fillOval(data.getBounds().x, data.getBounds().y, data.getBounds().width, data.getBounds().height);
		
		final int D = 9;
		for(int i = 0; i < getKnobs().size(); i++)
		{
			Point p = getKnobs().get(i);
			g.setColor(Color.BLACK);
			g.fillRect(p.x- 4, p.y-4, D, D);
			
		}
		
		
	}
}
