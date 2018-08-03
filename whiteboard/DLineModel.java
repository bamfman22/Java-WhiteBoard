import java.awt.Color;
import java.awt.Point;

public class DLineModel extends DShapeModel{
	
	
	public DLineModel()
	{
		p1 = new Point(10,10);
		p2 = new Point(50,50);
		color = Color.GRAY;
		bounds.setBounds(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
		
	}
	
	public void setPoints(Point p1, Point p2)
	{
		this.p1 = p1;
		this.p2 = p2;
		bounds.setBounds(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
		
		for(int i = 0; i < listeners.size(); i++)
		{
			listeners.get(i).modelChanged(this);
		}
		
	}

	@Override
	public void setBounds(int x, int y, int width, int height)
	{
		
		int x2 = (p2.x-p1.x) + x;
		int y2 = p2.y -p1.y +y;
		
		
		
		setPoints(new Point(x,y), new Point(x2,y2));
		
		
		
	}
}
