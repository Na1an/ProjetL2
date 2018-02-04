package engine;

import java.awt.Point;

public class View {
	
	//location of the camera
	Point Location = new Point(0, 0);
	
	//This is the max distance from the edge of the map
	final int expanded = 3;
	final int speed = 1;
	
	public final int width = 20;
	public final int height = 12;
	
	
	public void MoveView(MainFrame m) {
		int y = 0;
		int x = 0;
		if (m.GameState == MainFrame.State.PLAYING) {
			y = m.players.get(m.battle.currentPlayer).selecty;
			x = m.players.get(m.battle.currentPlayer).selectx;
		}
		else {
			//y = m.edit.selecty;
			//x = m.edit.selectx;
		}
		
		MoveNorth(y);
		MoveSouth(y);
		MoveEast(x);
		MoveWest(x);
	}
	
	public boolean Viewable(int x, int y) {
		if (y>=Location.y && y<Location.y+height) {
			if (x>=Location.x && x<Location.x+width) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Simple lame getter.
	 * @return X location of view-port to offset anything being drawn on the map.
	 */
	public int ViewX() {return Location.x;}
	/**
	 * Simple lame getter.
	 * @return Y location of view-port to offset anything being drawn on the map.
	 */
	public int ViewY() {return Location.y;}
	
	//These move the viewpoint in the correct direction at a set speed, used internally through MoveView();
	private void MoveNorth(int y) {
		if (y<Location.y+expanded) {
			Location.y-=speed;
		}
	}
	private void MoveSouth(int y) {
		if (y>Location.y+height-expanded) {
			Location.y+=speed;
		}
	}
	private void MoveEast(int x) {
		if (x<Location.x+width-expanded) {
			Location.x-=speed;
		}
	}
	private void MoveWest(int x) {
		if (x>Location.x+expanded) {
			Location.x+=speed;
		}
	}

}
