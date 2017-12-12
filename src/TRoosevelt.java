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
		
		int maxIter = 400;
		
		int conv = 0x00ff00;
		int div  = 0x000000;
		
		for (int pos = 0; pos < buffer.length; pos++) {
			
			
			double xo = pos%size;
			double yo = Math.floor(pos/size);
			
			xo -= size/2;
			yo -= size/2;
			
			xo /= size/2.4;
			yo /= size/2.4;
			
			xo -= 0.5;
			
			double xz = 0.0;
			double yz = 0.0;
			
			int iter = 0;
			while ( (xz*xz) + (yz*yz) < 4 && iter < maxIter ) {
				
				double xztemp = (xz*xz) - (yz*yz) + xo;
				yz = (2*xz*yz) + yo;
				xz = xztemp;
				iter++;
			}
			
//			buffer[pos] = getMandelColor(iter);
			
			if (iter == maxIter) {
				// Inside of the set - converges
				buffer[pos] = conv;
			}
			else {
				// Outside of the set - diverges
				buffer[pos] = div;
			}
			
		}

	}
	
	public static int getMandelColor(int iter) {
		
		if (iter >= 350) return 0xffffff;
		
		if (iter < 5)  return 0x000000;
		if (iter < 10)  return 0x333333;
		if (iter < 125)  return 0x8c611d;
		if (iter < 250)  return 0xffa71f;
		if (iter < 300)  return 0xf9cc88;
		if (iter < 360)  return 0xf2f2f2;
		if (iter < 390)  return 0xffffff;
		
		return 0xffffff;
	}

	public static void main(String[] args) {
		System.out.println("Java fractals by David Mitchell and Felix Hanau.");
		System.out.println("Renders mandelbrot and barnsley fractals.");
		
		int mandelSize = 1000;

		int[] mandelBuff = new int[mandelSize * mandelSize];
		mandelbrot(mandelBuff, mandelSize);
		BufferedImage mandelImage = new BufferedImage(mandelSize, mandelSize, BufferedImage.TYPE_INT_RGB);
		for (int a = 0; a < mandelImage.getWidth(); a++) {
			for (int b = 0; b < mandelImage.getHeight(); b++) {
				mandelImage.setRGB(a, b, mandelBuff[b * mandelImage.getWidth() + a]);
			}
		}
		
		JFrame mandelFrame = new JFrame("Theodore Roosevelt Project");
		mandelFrame.setSize(mandelSize, mandelSize);
		mandelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mandelFrame.add(new JLabel(new ImageIcon(mandelImage)));
		mandelFrame.setVisible(true);
		
		int size = 1000;
		
		int[] buffer = new int[size * size];
		barnsley(buffer, size);
		BufferedImage bufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < bufferedImage.getWidth(); i++) {
		    for (int j = 0; j < bufferedImage.getHeight(); j++) {
		    	bufferedImage.setRGB(i, j, buffer[j * bufferedImage.getWidth() + i]);
		    }
		}

		JFrame barnsleyFrame = new JFrame("Theodore Roosevelt Project");
		barnsleyFrame.setSize(size, size);
		barnsleyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		barnsleyFrame.add(new JLabel(new ImageIcon(bufferedImage)));
		barnsleyFrame.setVisible(true);
	}
}
