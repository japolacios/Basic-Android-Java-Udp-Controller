import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica {
	// Atributes
	PApplet app;
	int health, totalEnemies, enemiesKilled;
	boolean win, loose;
	
	//Relations
	Ui ui;
	ArrayList<Enemy> enemies;
	ArrayList<Bullets> bullets;
	Comunicacion com;

	// Constructor
	public Logica(PApplet _app) {
		app = _app;
		health = 100;
		totalEnemies = 1;
		enemiesKilled = 0;
		
		win = false;
		loose = false;
		ui = new Ui(app);
		com = new Comunicacion(app);
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullets>();
		
		//Start comuincation
		com.start();
		
		addEnemy(1, 500);
		System.out.println("Default Enemy Added");
	}
	
	//Creates a new Enemy and adds it to the enemy arrayList
	public void addEnemy(int _side, int _x){
		Enemy tempEnemy = new Enemy (_side,_x, app);
		enemies.add(tempEnemy);
	}
	
	private void checkForNewEnemies(){
		int numEnemies = com.getNumEnemy();
		int diference, x;
		if (numEnemies > totalEnemies){
			diference = numEnemies - totalEnemies;
			for (int i = 0; i < diference; i++) {
				int side = (int)app.random(0,3);
				if(side == 1){
					x = -50;
				}
				if (side == 2){
					x = 1050;
				} else {
					x = -50;
					side = 1;
				}
				addEnemy(side,x);
				totalEnemies++;
			}
		}
	}

	//Move the enemies & Paint
	public void moveAndPaintEnemies(){
		checkForNewEnemies();
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).move();
			if(enemies.get(i).dropBomb() == true){
				addBullet(enemies.get(i).x);
			}
			enemies.get(i).paint();
		}
	}
	// Paint Method
	public void paint() {
		if (win == false && loose == false){
		ui.paintAll();
		moveAndPaintEnemies();
		paintBullets();
		paintHealth();
		accion();
		winOrLoose();
		}
		if(win == true){
			app.text("You Have Won", app.width/2, app.height/2);
			app.text("Enemies Killed: "+ enemiesKilled, app.width/2, (app.height/2)+20);
			app.text("Health Remaining: "+ health, app.width/2, (app.height/2)+40);
		}
		else if(loose == true){
			app.text("You Have Lost", app.width/2, app.height/2);
		}
	}
	
	public void paintBullets(){
		for (int i = 0; i < bullets.size(); i++) {
			if(bullets.get(i).getY() <= 300){
			bullets.get(i).paint();
			} else{
				health = health -1;
				bullets.remove(i);
			}
		}
	}
	
	public void addBullet(float _x){
		Bullets tempBullet = new Bullets(app, _x);
		bullets.add(tempBullet);
	}
	
	public void shoot(){
		for (int i = 0; i < enemies.size(); i++) {
			if(PApplet.dist(enemies.get(i).x, enemies.get(i).y, ui.bullsEye_x, ui.bullsEye_y)<= 50){
				enemies.remove(i);
				totalEnemies--;
				System.err.println("Killed that bastard!");
			} else{
				//System.out.println("Missed");
			}
		}
	}
	
	public void paintHealth(){
		app.fill(0);
		app.rect(700, 20, health*2, 15);
		app.text("Health " + health, 700, 10);
		app.text("Enemies "+ totalEnemies, 100, 10);
		if( health>= 60 && health < 80 ){
			ui.setCity(1);
		}
		if(health>= 40 && health < 60 ){
			ui.setCity(2);
		}
		if( health>= 20 && health < 40 ){
			ui.setCity(3);
		}
		if(health>= 0 && health < 20 ){
			ui.setCity(4);
		}
	}
	
	public void restart(){
		enemiesKilled = 0;
		health = 100;
		totalEnemies = 1;
		win = false;
		loose = false;
		ui = new Ui(app);
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullets>();
		addEnemy(1, 500);
		System.out.println("Default Enemy Added");
	}
	
	//Checks on the message recived in order to move the bullseye
	public void accion(){
		
		if(com.getComando().equals("up")){
			ui.setBullsEye_up();
		}
		
		if(com.getComando().equals("down")){
			
			ui.setBullsEye_down();
		}
		
		if(com.getComando().equals("left")){
			
			ui.setBullsEye_left();
		}
		
		if(com.getComando().equals("right")){
			ui.setBullsEye_right();
		}
		
		if(com.getComando().equals("shoot") ) {
			shoot();
		}
		
		if(com.getComando().equals("reset")){
			restart();
			com.setComando("");
		}

	}
	
	public void winOrLoose(){
		if (health <= 0){
			loose = true;
		}
		if (totalEnemies < 0){
			win = true;
		}
	}
	// End Class
}
