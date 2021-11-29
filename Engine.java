// Engine.java
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.lang.Math;

/*
 * Engine Class
 */
public class Engine extends JFrame {
	
	// main method
	public static void main(String[] args) {
		new Engine();
	}// end main
	
	// Engine Constructor
	public Engine() {
		// create the canvas
		Scene scene = new Scene();
		
		add(scene, BorderLayout.CENTER);
		setContentPane(scene);
		pack(); // use the preferred size of the jpanel scene instead of the jframe.
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// End Engine Constructor
	
}