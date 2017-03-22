package dev.delve.game.worlds.generation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WorldGenerator {

	private static int WIDTH = 512;
	private static int HEIGHT = 512;
	private static double FEATURE_SIZE = 24;
	public static int getWIDTH() {
		return WIDTH;
	}


	public void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}


	public static int getHEIGHT() {
		return HEIGHT;
	}


	public void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}


	public static double getFEATURE_SIZE() {
		return FEATURE_SIZE;
	}


	public void setFEATURE_SIZE(double fEATURE_SIZE) {
		FEATURE_SIZE = fEATURE_SIZE;
	}


	private static int[][] map_array = new int[512][512];
	
	
	public int[][] GenerateRandomWorld()  
		throws IOException {
			
		
		OpenSimplexNoise noise = new OpenSimplexNoise();
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				double value = noise.eval(x / FEATURE_SIZE, y / FEATURE_SIZE, 0.0, 0.0);
				int rgb = 0x010101 * (int)((value + 1) * 127.5);
				image.setRGB(x, y, rgb);
				map_array[x][y] = Math.abs((int)(value * 10));
				System.out.println("X: " + x + " || Y: " + y + " || Value: " + Math.abs((int)(value * 10)));
			}
		}
		
		return map_array;
		
	}
}

