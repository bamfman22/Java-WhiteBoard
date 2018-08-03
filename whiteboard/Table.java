import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class Table extends AbstractTableModel implements ModelListener{

	ArrayList<int[]> rows = new ArrayList<int[]>();
	String [] columns = {"x", "Y", "Width", "Height"};
	
	int row = 0;
	
	public Table()
	{
			
		
		
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows.size();
	}
	
	@Override
	public String getColumnName(int index)
	{
		return columns[index];
	}
	
	public void remove()
	{
		rows.remove(row);
		
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
		
		
	}
	
	public void addRow(DShapeModel model)
	{
		int [] x = new int[4]; 
		x[0] = model.bounds.x;
		x[1] = model.bounds.y;
		x[2] = Math.abs(model.bounds.width);
		x[3] = Math.abs(model.bounds.height);
		rows.add(x);
	}
	


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return rows.get(rowIndex)[columnIndex];
	}
	@Override
	public void modelChanged(DShapeModel model) {
		// TODO Auto-generated method stub
		int [] x = new int[4]; 
		x[0] = model.bounds.x;
		x[1] = model.bounds.y;
		x[2] = Math.abs(model.bounds.width);
		x[3] = Math.abs(model.bounds.height);
		
		
		rows.set(row, x);
		
		this.fireTableRowsUpdated(row, row);
		
		
	}

	public void setRow(int i)
	{
		row = i;
	}
}
