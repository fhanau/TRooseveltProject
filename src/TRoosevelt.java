import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.Arrays;

public class TRoosevelt {
	static void mandelbrot(int[] buffer) {
		Arrays.fill(buffer, 0x5FFFF);
	}

	public static void main(String[] args) {
		System.out.println("TRoosevelt project by David Mitchell and Felix Hanau.");
		System.out.println("Renders mandelbrot fractals.");

        //Zen.create(320, 300, "stretch");
		System.out.println("Zen ran (or not)");
		int height = 450;
		int width = 800;

		int[] buffer = new int[height * width];
		mandelbrot(buffer);
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < bufferedImage.getWidth(); i++) {
		    for (int j = 0; j < bufferedImage.getHeight(); j++) {
		    	bufferedImage.setRGB(i, j, buffer[i * bufferedImage.getHeight() + j]);
		    }
		}

		JFrame frame = new JFrame("Theodore Roosevelt Project");
		frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JLabel(new ImageIcon(bufferedImage)));
        frame.setVisible(true);
	}
}
