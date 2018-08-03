import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;

public class DText extends DShape{

	public DText()
	{
		data = new DTextModel();
	}
	
	public DText(DTextModel i)
	{
		data = i;
		data.addListener(this);
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics g1 = g.create(data.getBounds().x, data.getBounds().y, data.getBounds().width, data.getBounds().height);
		
		DTextModel f = (DTextModel) data;
		
		String s = f.getFont();
		
		Font font1 = Font.decode(s);
		g1.setFont(font1);
		
		Font font = Canvas.computeFont(g1);
		g.setClip(getBounds());
		g.setFont(font);
		g.setColor(data.color);
		g.drawRect(data.getBounds().x, data.getBounds().y, data.getBounds().width, data.getBounds().height);
		Shape clip = g.getClip();	
		g.setClip(clip.getBounds().createIntersection(getBounds()));
		
		
		
		
		g.drawString(f.getText(), data.getBounds().x + 5, data.getBounds().y + data.getBounds().height - 5);
		
		
		g.setClip(clip);
		
	}

	@Override
	public void selectedDraw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics g1 = g.create(data.getBounds().x, data.getBounds().y, data.getBounds().width, data.getBounds().height);
		
		DTextModel f = (DTextModel) data;
		
		String s = f.getFont();
		
		Font font1 = Font.decode(s);
		g1.setFont(font1);
		
		Font font = Canvas.computeFont(g1);
		g.setClip(getBounds());
		g.drawRect(data.getBounds().x, data.getBounds().y, data.getBounds().width, data.getBounds().height);
		g.setColor(data.color);
		g.setFont(font);
		
		Shape clip = g.getClip();
		
		g.setClip(clip.getBounds().createIntersection(data.getBounds()));
		g.drawString(f.getText(), data.getBounds().x +5, data.getBounds().y + data.getBounds().height - 5);
		
		
		g.setClip(data.getBounds().x - 5,data.getBounds().y - 5,data.getBounds().width +20,data.getBounds().height +20);
		
		final int D = 9;
		for(int i = 0; i < getKnobs().size(); i++)
		{
			Point p = getKnobs().get(i);
			g.setColor(Color.BLACK);
			g.fillRect(p.x - 6, p.y-6, D, D);
			
		}
		
		
		
	}

	
}
