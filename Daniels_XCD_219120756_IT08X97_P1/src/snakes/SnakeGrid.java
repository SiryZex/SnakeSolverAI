package snakes;

import java.util.ArrayList;
import java.util.Random;

import helpers.ReadFile;
import helpers.Spot;

public class SnakeGrid {
	
	private static final int Rows = 18; //Y Value
	private static final int Cols = 18; //X Value

	Random random;
	private Spot[][] grid;
	private A_Star_snake PositionAStar;
	private Spot PositionApple;

	public SnakeGrid() {
		grid = ReadSpots();
		random = new Random();
	}

	private Spot[][] ReadSpots() {
		int[][] GridStart = ReadFile.ReadFileData();
		Spot[][] SpotData = new Spot[Rows][Cols];
		
		for (int r=0; r < Rows; r++) {    
            for (int c=0; c < Cols; c++) {
            	if(GridStart[r][c] == 4) {
            		PositionAStar = new A_Star_snake(r, c);
            		PositionAStar.setX(0,0);
            		PositionAStar.setY(0,0);
            		SpotData[r][c] = PositionAStar;
            	}else if(GridStart[r][c] == 10) {
            		PositionApple = new Spot(r, c);
            		SpotData[r][c] = PositionApple;
            	}else {
            		SpotData[r][c] = new Spot(r, c);
            	}
            }
         }
		
		return SpotData;
	}
	
	public boolean checkApple() {
		
		if ((PositionAStar.getyHead() == PositionApple.getyHead()) &&
			(PositionAStar.getxHead() == PositionApple.getxHead())) {
			
			PositionAStar.plusBodyParts();
			PositionAStar.plusApplesEaten();
			grid[PositionApple.getyHead()][PositionApple.getxHead()] = new Spot(PositionApple.getyHead(),
																				PositionApple.getxHead());
			
			return true;
		} else {
			return false;
		}
	}
	
	public void newApple() {
		int appleY = random.nextInt(Cols - 1);
		int appleX = random.nextInt(Rows - 1);

		PositionApple = new Spot(appleY, appleX);
		grid[appleY][appleX] = PositionApple;
	}
	
	public void move(char direction) {
		for (int i = PositionAStar.getBodyParts(); i > 0; i--) {
			PositionAStar.setY(i, PositionAStar.getY(i - 1));
			PositionAStar.setX(i, PositionAStar.getX(i - 1));
		}

		switch (direction) {
		case 'U':
			int yU = PositionAStar.getxHead();
			int xU = PositionAStar.getyHead();
			if (yU - 1 >= 0) {
				PositionAStar.setX(0, PositionAStar.getX(0) - 1);
				PositionAStar.setxHead(yU - 1);
				setCoordinate(yU - 1, PositionAStar.getyHead(), PositionAStar);
				setCoordinate(yU, xU, new Spot(yU, xU));
			}
			break;
		case 'D':
			int yD = PositionAStar.getxHead();
			int xD = PositionAStar.getyHead();
			if (yD + 1 < 18) {
				PositionAStar.setX(0, PositionAStar.getX(0) + 1);
				PositionAStar.setxHead(yD + 1);
				setCoordinate(yD + 1, PositionAStar.getyHead(), PositionAStar);
				setCoordinate(yD, xD, new Spot(yD, xD));
			}
			break;
		case 'L':
			int xL = PositionAStar.getyHead();
			int yL = PositionAStar.getxHead();
			if (xL - 1 >= 0) {
				PositionAStar.setY(0, PositionAStar.getY(0) - 1);
				PositionAStar.setyHead(xL - 1);
				setCoordinate(PositionAStar.getxHead(), xL - 1, PositionAStar);
				setCoordinate(yL, xL, new Spot(yL, xL));
			}
			break;
		case 'R':
			int xR = PositionAStar.getyHead();
			int yR = PositionAStar.getxHead();
			if (xR + 1 < 18) {
				PositionAStar.setY(0, PositionAStar.getY(0) + 1);
				PositionAStar.setyHead(xR + 1);
				setCoordinate(PositionAStar.getxHead(), xR + 1, PositionAStar);
				setCoordinate(yR, xR, new Spot(yR, xR));
			}
			break;
		}
	}
	
	public Boolean checkCollisions() {
		// checks if head collides with body
		for (int i = PositionAStar.getBodyParts(); i > 0; i--) {
			if ((PositionAStar.getY(0) == PositionAStar.getY(i)) &&
				(PositionAStar.getX(0) == PositionAStar.getX(i))) {
				return false;
			}
		}
		// check if head touches top border
		if (PositionAStar.getY(0) < 0) {
			return false;
		}
		// check if head touches bottom border
		if (PositionAStar.getY(0) > Rows) {
			return false;
		}
		// check if head touches left border
		if (PositionAStar.getX(0) < 0) {
			return false;
		}
		// check if head touches right border
		if (PositionAStar.getX(0) > Cols) {
			return false;
		}
		return true;
	}
	
	public ArrayList<Spot> getNeighbours() {
		if (PositionAStar.getNeighbours().size() == 0) {
			populateNeighbors();
		}
		return PositionAStar.getNeighbours();
	}

	public Spot getNode(int r, int c) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
			return null;
		}
		return grid[r][c];
	}

	// populate neighbor move and neighbor wall arrays
	public void populateNeighbors() {

		// Add Left/Up/Right/Down Moves
		for (var i = 0; i < 4; i++) {
			Spot node = getNode(PositionAStar.getI() + PositionAStar.getLurdmoves(i, 0),
								PositionAStar.getJ() + PositionAStar.getLurdmoves(i, 1));
			if (node != null) {

				// check if head touches left border
				if (PositionAStar.getyHead() > 0 &&
					PositionAStar.getyHead() < 18 &&
					PositionAStar.getxHead() > 0 &&
					PositionAStar.getxHead() < 30)
					 {
					
					PositionAStar.getNeighbours().add(node);
				}
			}
		}
	}

	public Spot getCoordinate(int gridY, int gridX) {
		return grid[gridY][gridX];
	}

	public void setCoordinate(int gridY, int gridX, Spot Value) {
		grid[gridY][gridX] = Value;
	}
	
	public A_Star_snake getPositionAStar() {
		return PositionAStar;
	}

	public void setPositionAStar(int y, int x) {
		PositionAStar.setyHead(y);
		PositionAStar.setxHead(x);
	}

	public Spot getPositionApple() {
		return PositionApple;
	}

	public void setPositionApple(int y, int x) {
		PositionApple.setyHead(y);
		PositionApple.setxHead(x);
	}

	public static int getCols() {
		return Cols;
	}

	public static int getRows() {
		return Rows;
	}

	
	public Spot[][] getGrid() {
		return grid;
	}
	
}
