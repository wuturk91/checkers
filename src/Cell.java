import java.awt.*;
import javax.swing.*;

public class Cell {
	private JButton cell;
	private ImageIcon cellIcon;
	
	public Cell() {
		//Initiate button and set dimensions and border
		cell = new JButton();
		cell.setPreferredSize(new Dimension(90, 90));
		cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//Icon set to null initiallyx
		cellIcon = null;
	}
	
	public JButton getCell() {
		return cell;
	}
	
	public ImageIcon getCellIcon() {
		return cellIcon;
	}
	
}
