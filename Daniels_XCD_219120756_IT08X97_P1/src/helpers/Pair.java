package helpers;

/**
 * @author Daniels XCD 219120756
 *
 */
public class Pair {
    private int X_Coordinate;
    private int Y_Coordinate;    

    public Pair(int r, int c) {
    	Y_Coordinate = r;
    	X_Coordinate = c;
    }

 // getters + setters
	public int getX_Coordinate() {
		return X_Coordinate;
	}

	public void setX_Coordinate(int c) {
		X_Coordinate = c;
	}

	public int getY_Coordinate() {
		return Y_Coordinate;
	}

	public void setY_Coordinate(int r) {
		Y_Coordinate = r;
	}
}
