package engine;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Gui extends JPanel{
	
	public int width = gameUtil.Constants.ScrennBase*22;
	public int height = gameUtil.Constants.ScrennBase*20;
	
	/**
	 * Here, frame works for sync, up to 12 will be reset 0.
	 */
	public int frame = 0;
	
	public Gui() {
		setPreferredSize(new Dimension(width,height));
		setBounds(0, 0, width, height);
		setLayout(null);
	}
	

}
