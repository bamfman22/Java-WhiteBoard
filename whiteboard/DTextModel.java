import java.awt.Color;
import java.awt.Rectangle;

public class DTextModel extends DShapeModel{
	
	String text;
	String font;
	
	
	
	public DTextModel()
	{
		text = "Hello";
		font = "Dialog";
		
		color = Color.black;
		bounds = new Rectangle(10,10,100,25);
	}
	
	
	public void setText(String t)
	{
		text = t;
		
		for(int i = 0; i < listeners.size(); i++)
		{
			listeners.get(i).modelChanged(this);
		}
	}
	
	public void setFont(String f)
	{
		font = f;
		
		
		for(int i = 0; i < listeners.size(); i++)
		{
			listeners.get(i).modelChanged(this);
		}
	}
	
	public String getFont()
	{
		return font;
	}
	
	public String getText()
	{
		return text;
	}
}
