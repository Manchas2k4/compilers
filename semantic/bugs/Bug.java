package bugs;

import java.awt.Color;

public class Bug {
	private double x, y;
	private double angle;
	private boolean pen;
	private Color color;

	private Bug() {
		this.x = 0;
		this.y = 0;
		this.angle = 0;
		this.pen = true;
		this.color = Color.black;
	}

	private static class SingletonHelper {
		private static final Bug INSTANCE = new Bug();
	}

	public static Bug getInstance() {
		return SingletonHelper.INSTANCE;
	}

	public double getAngle() {
		return angle;
	}

	public void turnRight(double a) {
		int aux;

		angle = angle + a;
		aux = (((int) angle) % 360) / 10;
		System.out.println(this);
	}

	public void turnLeft(double a) {
		int aux;

		a = a % 360;
		angle = angle - angle;
		while (angle < 0) {
			angle = 360 - angle;
		}

		aux = (((int) angle) % 360) / 10;
		System.out.println(this);
	}

	public boolean getPen() {
		return pen;
	}

	public void penUp() {
		pen = false;
	}

	public void penDown() {
		pen = true;
	}

	public Color getColor() {
		return color;
	}

	public void changeColor(Color c) {
		color = c;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void forward(double n) {
		double auxx, auxy;

		auxx = Math.sin(Math.toRadians(angle)) * n;
		auxy = Math.cos(Math.toRadians(angle - 180)) * n;
		if (Math.abs(auxx) < 0.5) {
			auxx = 0;
		}
		if (Math.abs(auxy) < 0.5) {
			auxy = 0;
		}

		x += auxx;
		y += auxy;
		System.out.println(this);
	}

	public void backward(double n) {
		double auxx, auxy;

		auxx = Math.sin(Math.toRadians(angle)) * n;
		auxy = Math.cos(Math.toRadians(angle - 180)) * n;
		if (Math.abs(auxx) < 0.5) {
			auxx = 0;
		}
		if (Math.abs(auxy) < 0.5) {
			auxy = 0;
		}

		x -= auxx;
		y -= auxy;
		System.out.println(this);
	}

	public void gotoXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void goHome() {
		x = 0;
		y = 0;
		angle = 0;
	}

	public String toString() {
		return "x = " + x + " y = " + y + " angle = " + angle + " pen = " + pen;
	}
}
