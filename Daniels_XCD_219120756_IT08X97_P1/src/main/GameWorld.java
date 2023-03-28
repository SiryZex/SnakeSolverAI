package main;
import javax.swing.JFrame;

/**
 * @author Daniels XCD 219120756
 *
 */
public class GameWorld extends JFrame{

	GameWorld(){
		this.add(new Panel());
		this.setTitle("BFS Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
