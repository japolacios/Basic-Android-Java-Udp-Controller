import processing.core.PApplet;

public class AirMadness extends PApplet {

	// Relations
	Logica logica;

	@Override
	public void settings() {

		// Set Size
		int alto = 600;
		int ancho = 1000;

		size(ancho, alto);

		System.out.println("Canvas Size " + ancho + "x" + alto);	
	}

	@Override
	public void setup() {
		System.out.println("Initializing Logic");
		logica = new Logica(this);

		// Check Logic its Live
		if (logica != null) {
			System.err.println("Logic Runing");
		}

	}

	@Override
	public void draw() {
		// Set Background White
		background(255, 255, 255);
		// Call Logic Paint
		logica.paint();
	}

	// Program Runner
	public static void main(String[] args) {
		PApplet.main("AirMadness");
	}

	// End Class
}
