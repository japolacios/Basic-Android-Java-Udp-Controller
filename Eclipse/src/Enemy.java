import processing.core.PApplet;
import processing.core.PImage;

public class Enemy {
	// Atributes
	float x, y;
	int side;
	public static int LEFT = 1;
	public static int RIGHT = 2;
	PApplet app;
	PImage ufo;
	
	public Enemy(int _side, int _x, PApplet _app) {
		side = _side;
		x = _x;
		y = 100;
		app = _app;
		
		loadAssets();
	}

	public void move() {
		//System.out.println("Start Move");
		if (side == 1) {
			//System.out.println("Move Left");
			x = x+2;
			//System.out.println(x);
			if (x >= 1050) {
				x = -50;
				//System.out.println("Position Reset");
			}

		} else if (side == RIGHT) {
			x = x--;

			if (x <= -50) {
				x = 1050;
			}
		}
	}
	
	private void loadAssets()
	{
		ufo = app.loadImage("assets/ufo.png");
	}
	public void paint(){
		app.image(ufo, x, y);
	}
	
	public boolean dropBomb(){
		boolean out = false;
		int random = (int) app.random(0,10000);
		
		if(random >=9999){
			out = true;
		} else {
			out = false;
		}
		return out;
	}
	
}
