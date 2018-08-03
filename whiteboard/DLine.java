import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DLine extends DShape{

	
	
	public DLine()
	{
		data.addListener(this);
	}
	
	public DLine(DLineModel i)
	{
		data = i;
		data.addListener(this);
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		
		g.setColor(data.color);
		g.drawLine(data.p1.x, data.p1.y, data.p2.x, data.p2.y);
		
		
	}

	@Override
	public void selectedDraw(Graphics g) {
		// TODO Auto-generated method stub
		
		final int D = 9;
		
		
		
		
		g.setColor(data.color);
		g.drawLine(data.p1.x, data.p1.y, data.p2.x, data.p2.y);
		
		
		g.setColor(Color.BLACK);
		g.fillRect(data.p1.x- 4, data.p1.y-4, D, D);
		g.fillRect(data.p2.x- 4, data.p2.y-4, D, D);
		
		
	}
	
	@Override
	public ArrayList<Rectangle> getKnobRect()
	{
		ArrayList<Rectangle> rect = new ArrayList<Rectangle>();
		
		rect.add(new Rectangle(data.p1.x - 4,data.p1.y-4,9,9));
		rect.add(new Rectangle(data.p2.x -4, data.p2.y - 4, 9,9));
		
		return rect;
		
	}
	
	@Override
	public ArrayList<Point> getKnobs()
	{
		ArrayList<Point> knobs = new ArrayList<Point>();
		
		Point bottomRight = data.p2;
		Point topLeft = data.p1;
		knobs.add(topLeft);
		knobs.add(bottomRight);
		
		return knobs;
	}
	
	@Override
	public void reshape(Point anchor, Point mover, MouseEvent e)
	{

		
		DLineModel d = (DLineModel) data;
		
		d.setPoints(anchor, new Point(e.getX(),e.getY()));
		
	}

}
