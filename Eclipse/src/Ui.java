import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

//Class in charge of all the UI 
public class Ui {

	PApplet app;
	PImage[] city;
	PImage bullseye;
	int cityNumber, stage;
	float bullsEye_x, bullsEye_y;

	// Constructor
	public Ui(PApplet _app) {

		// Set Initial Atributes
		app = _app;
		cityNumber = 0;
		stage = 0;
		city = new PImage[5];
		bullsEye_x = app.width / 2;
		bullsEye_y = app.height / 2;
		// Call Load Assets

		loadAssets();

		System.out.println("UI initialized");
	}

	private void loadAssets() {

		System.out.println("Loading Assets");

		// Load City
		city[0] = app.loadImage("assets/city1.png");
		city[1] = app.loadImage("assets/city2.png");
		city[2] = app.loadImage("assets/city3.png");
		city[3] = app.loadImage("assets/city4.png");
		city[4] = app.loadImage("assets/city5.png");

		// Load BullsEye
		bullseye = app.loadImage("assets/bulleye.png");

		System.out.println("Assets Loaded!");
	}

	// Runs all the other paints in the class
	public void paintAll() {
		app.imageMode(app.CENTER);
		paintBuildings();
		paintBullsEye();
	}

	private void paintBuildings() {
		if (city[cityNumber] != null) {
			app.image(city[cityNumber], app.width / 2, app.height / 2);
		} else {
			System.out.println("Paint element is empty");
		}
	}

	private void paintBullsEye() {
		app.image(bullseye, bullsEye_x, bullsEye_y);
	}

	public void setBullsEye_right() {
		if(bullsEye_x + 5<= 950){
		bullsEye_x = bullsEye_x + 5;
		}
	}

	public void setBullsEye_left() {
		if (bullsEye_x - 5>= 50){
		bullsEye_x = bullsEye_x - 5;
		}
	}

	public void setBullsEye_down() {
		if(bullsEye_y + 5 <= 550){
		bullsEye_y = bullsEye_y + 5;
		}
	}

	public void setBullsEye_up() {
		if(bullsEye_y - 5>= 50){
		bullsEye_y = bullsEye_y - 5;
		}
	}
	
	public void setCity(int _city){
		cityNumber = _city;
	}
	// End Class
}
