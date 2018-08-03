import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class DShape implements ModelListener{
	
	DShapeModel data = new DShapeModel();
	int preX, preY = 0;
	
	public DShape()
	{
		data.addListener(this);
	}
	
	public DShape(DShapeModel model)
	{
		this.data.setBounds(model.getBounds().x, model.getBounds().y, model.getBounds().height, model.getBounds().width);
		model.addListener(this);
	}
	
	
	public abstract void draw(Graphics g);
	
	public Rectangle getBounds()
	{
		return data.getBounds();
	}
	
	public abstract void selectedDraw(Graphics g);
	
	public void move(int x, int y)
	{
		data.setBounds(preX +x,preY+ y, data.bounds.width, data.bounds.height);
	}

	public void modelChanged(DShapeModel model)
	{
		data = model;
		
		
	}
	
	public ArrayList<Point> getKnobs()
	{
		ArrayList<Point> knobs = new ArrayList<Point>();
		
		Point topRight = new Point();
		Point topLeft = new Point();
		Point bottomLeft = new Point();
		Point bottomRight = new Point();
		
		int x = data.bounds.x;
		int y = data.bounds.y;
		int width = data.bounds.width;
		int height = data.bounds.height;
		
		topRight.setLocation(width + x ,y );
		topLeft.setLocation(x , y );
		bottomLeft.setLocation(x , y + height );
		bottomRight.setLocation(x + width , y + height );
		
		knobs.add(bottomLeft);
		knobs.add(topLeft);
		knobs.add(topRight);
		knobs.add(bottomRight);
		
		return knobs;
		
	}
	
	public ArrayList<Rectangle> getKnobRect()
	{
		ArrayList<Rectangle> ret = new ArrayList<Rectangle>();
		
		ArrayList<Point> knobs = getKnobs();
		
		for(int i = 0; i < knobs.size();i++)
		{
			Rectangle rect = new Rectangle();
			rect.setBounds(knobs.get(i).x, knobs.get(i).y, 9, 9);
			
			ret.add(rect);
		}
		
		return ret;
	}
	
	public void setPre(int x, int y)
	{
		preX = data.bounds.x - x;
		preY = data.bounds.y - y;
	}
	
	
	public void reshape(Point anchor, Point mover, MouseEvent e)
	{
		mover.x = 2 * mover.x - e.getX();
		mover.y = 2 * mover.y - e.getY();

		
		int distX = -anchor.x + mover.x;
		int distY = -anchor.y + mover.y ;
		
		data.setBounds(anchor.x, anchor.y, distX, distY);
		mover.x = e.getX();
		mover.y = e.getY();
		
		
		
		
	}
}
