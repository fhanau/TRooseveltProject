/* Fractals in Java (2017) by David Mitchell and Felix Hanau
 * */

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

public class TRoosevelt {
	static void barnsley(int[] buffer, int size) {
		int scale_factor = size * 9 / 100;
		Arrays.fill(buffer, 0x101010/*xFFFFFF*/);
		//Arrays.fill(buffer, 0xFFFFFF);//500k 500m no visible difference
		int iterations = 100000000;
		int color = 0x10F010;
		double x = 0, y = 0;
		buffer[((int)(scale_factor * x))+((int)(scale_factor * y))* size + size / 2] = color;

		Random rnd = new Random();
		for(int i=0; i < iterations; i++) {
			int r = rnd.nextInt(100);
			if(r == 0) {
				x = 0;
				y = y * 0.16;
			}
			else if(r <= 85) {
				double nextx = x * .85 + y * .04;
				double nexty = -x * .04 + y * .85 + 1.6;
				x = nextx;
				y = nexty;
			}
			else if(r <= 92) {
				double nextx = x * .20 - y * .26;
				double nexty = x * .23 + y * .22 + 1.6;
				x = nextx;
				y = nexty;
			}
			else {
				double nextx = -x * .15 + y * .28;
				double nexty = x * .26 + y * .24 + 0.44;
				x = nextx;
				y = nexty;
			}
			
			buffer[((int)(scale_factor * x))+((int)(scale_factor * y))* size + size / 2] = color;
		}
	}

	static void mandelbrot(int[] buffer, int size) {
	}

	public static void main(String[] args) {
		System.out.println("Java fractals by David Mitchell and Felix Hanau.");
		System.out.println("Renders mandelbrot and barnsley fractals.");

		int size = 1000;

		int[] buffer = new int[size * size];
		mandelbrot(buffer, size);
		barnsley(buffer, size);
		BufferedImage bufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < bufferedImage.getWidth(); i++) {
		    for (int j = 0; j < bufferedImage.getHeight(); j++) {
		    	bufferedImage.setRGB(i, j, buffer[j * bufferedImage.getWidth() + i]);
		    }
		}

		JFrame frame = new JFrame("Theodore Roosevelt Project");
		frame.setSize(size, size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JLabel(new ImageIcon(bufferedImage)));
        frame.setVisible(true);
	}
}
