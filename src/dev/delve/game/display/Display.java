package dev.delve.game.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame frame; // needs title, width, height
	private Canvas canvas; // what we draw graphics to

	private String title;
	private int width, height, scale; // in terms of pixel

	public Display(String title, int width, int height, int scale) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.scale = scale;

		createDisplay();
	}

	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width*scale, height*scale);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // appears in center, instead of side
		frame.setVisible(true);

		canvas = new Canvas();
		Dimension size = new Dimension(width * scale, height * scale);
		canvas.setPreferredSize(size);
		canvas.setMaximumSize(size);
		canvas.setMinimumSize(size);
		canvas.setFocusable(false);

		frame.add(canvas);
		frame.pack(); // makes sure we can see all the canvas

	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
