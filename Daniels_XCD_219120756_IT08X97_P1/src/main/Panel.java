package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import snakes.*;

public class Panel extends JPanel implements ActionListener {

	static final int SCREEN_WIDTH = 900;
	static final int SCREEN_HEIGHT = 900;
	static final int UNIT_SIZE = 50;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
	static final int DELAY = 500;

	SnakeGrid grid;

	char direction = 'R';

	boolean running = false;
	Timer timer;

	Panel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}

	public void startGame() {
		grid = new SnakeGrid();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
		if (running) {

			for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
			}

			g.setColor(Color.red);
			g.fillOval(grid.getPositionApple().getyHead()*UNIT_SIZE,
					   grid.getPositionApple().getxHead()*UNIT_SIZE,
					   UNIT_SIZE, UNIT_SIZE);

			for (int i = 0; i < grid.getPositionAStar().getBodyParts(); i++) {
				if (i == 0) {
					g.setColor(Color.green);
					g.fillRect(grid.getPositionAStar().getY(i)*UNIT_SIZE,
							   grid.getPositionAStar().getX(i)*UNIT_SIZE,
							   UNIT_SIZE, UNIT_SIZE);
				} else {
					g.setColor(new Color(45, 180, 0));
					g.fillRect(grid.getPositionAStar().getY(i)*UNIT_SIZE,
							   grid.getPositionAStar().getX(i)*UNIT_SIZE,
							   UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + grid.getPositionAStar().getApplesEaten(),
					(SCREEN_WIDTH - metrics.stringWidth("Score: " + grid.getPositionAStar().getApplesEaten())) / 2,
					g.getFont().getSize());
		} else {
			gameOver(g);
		}
	}

	public void move() {
		grid.move(direction);
	}

	public void checkApple() {

		boolean checkAStar = grid.checkApple();

		if (checkAStar) {
			grid.newApple();
		}
	}

	public Boolean checkCollisions() {
		boolean collision = grid.checkCollisions();

		if (collision == false) {
			return false;
		} else {
			return true;
		}
	}

	public void gameOver(Graphics g) {
		
		// Score
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: " + grid.getPositionAStar().getApplesEaten(),
				(SCREEN_WIDTH - metrics1.stringWidth("Score: " + grid.getPositionAStar().getApplesEaten())) / 2,
				 g.getFont().getSize());

		// Game Over text
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkApple();
			running = checkCollisions();
			
		}
		if (!running) {
			timer.stop();
		}
		repaint();
	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if (direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}
}