package helpers;

import java.util.ArrayList;

public class Spot {

	private int i;
	private int j;
	private int yHead;
	private int xHead;
	private Boolean Visited = false;

	// Neighbours
	private ArrayList<Spot> Neighbours;

	private int F = 0;
	private int G = 0;
	private int H = 0;

	private final int LURDMoves[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	public Spot(int yHead, int xHead) {
		Neighbours = new ArrayList<>();
		this.yHead = yHead;
		this.xHead = xHead;
	}

	public int getxHead() {
		return xHead;
	}

	public void setxHead(int xHead) {
		this.xHead = xHead;
	}

	public int getyHead() {
		return yHead;
	}

	public void setyHead(int yHead) {
		this.yHead = yHead;
	}

	public Boolean getVisited() {
		return Visited;
	}

	public void setVisited(Boolean visited) {
		Visited = visited;
	}

	public ArrayList<Spot> getNeighbours() {
		return Neighbours;
	}

	public void setNeighbours(ArrayList<Spot> neighbours) {
		Neighbours = neighbours;
	}

	public int getF() {
		return F;
	}

	public void setF(int f) {
		F = f;
	}

	public int getG() {
		return G;
	}

	public void setG(int g) {
		G = g;
	}

	public int getH() {
		return H;
	}

	public void setH(int h) {
		H = h;
	}

	public int getLurdmoves(int r, int c) {
		return LURDMoves[r][c];
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
}
