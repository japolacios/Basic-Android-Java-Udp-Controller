import processing.core.PApplet;

public class Bullets {
	//Atributes
	PApplet app;
	float x,y;
	
	
	public Bullets(PApplet _app, float _x){
		app = _app;
		x = _x;
		y = 100;
	}
	
	public void paint(){
		app.fill(0);
		app.ellipse(x, y, 10, 10);
		move();
	}
	
	public void move(){
		y = y+3;
	}
	
	public float getY(){
		return y;
	}
}
