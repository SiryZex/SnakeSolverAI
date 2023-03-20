package snakes;

import helpers.Spot;

/**
 * @author Daniels XCD 219120756
 *
 */
public class A_Star_snake extends Spot{
	private int x[];
	private int y[];

	private int bodyParts = 1;
	private int applesEaten = 0;

	public A_Star_snake(int r, int c) {
		super(r, c);
		this.y = new int[540];
		this.x = new int[540];
	}

	// getters + setters
	public int getBodyParts() {
		return bodyParts;
	}
	
	public int plusBodyParts() {
		return bodyParts++;
	}

	public void setBodyParts(int bodyParts) {
		this.bodyParts = bodyParts;
	}

	public int getApplesEaten() {
		return applesEaten;
	}
	
	public int plusApplesEaten() {
		return applesEaten++;
	}

	public void setApplesEaten(int applesEaten) {
		this.applesEaten = applesEaten;
	}

	public int getX(int increment) {
		return x[increment];
	}

	public int getY(int increment) {
		return y[increment];
	}

	public void setX(int increment, int newValue) {
		x[increment] = newValue;
	}

	public void setY(int increment, int newValue) {
		y[increment] = newValue;
	}

	public int[] getXCoordinates() {
		return x;
	}

	public int[] getYCoordinates() {
		return y;
	}
}
