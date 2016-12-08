package ElectionProcess13;

import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jensen on 12/7/16.
 */
public class Draw32016 extends PApplet {


    private float x;
    private float y;
    private float angle;
    private float speed;
    private float radius;
    private Color party;
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private String results[] = loadStrings("ElectionProcess13/2016ElectionResults.txt");
    private PImage img = loadImage("ElectionProcess13/usa.png");



    public void setup(){

        size(img.width,img.height);





        for (int i = 0; i < results.length-1; i++) {

            String[] state = split(results[i], " ");

            //set the color based on the state
            if(state[1].equals("R")) {
                party = new Color(255,0,0);
            }
            else if(state[1].equals("D")) {
                party = new Color(0, 0, 255);
            }

            //initilize the varibles for each circle
            x = random(1,this.width);
            y = this.height/2;
            angle = random(0,2*PI);
            radius = random(1,51);
            speed = random(1,3);

            //add the circle to the circles list
            circles.add(new Circle(x,y,radius,speed,angle,Integer.valueOf(state[2]),party));
        }



    }
    public void draw() {

        image(img, 0, 0);

        for (int i = 0; i < circles.size(); i++) { //draw the circles

            //move the circles
            circles.get(i).setX(circles.get(i).getX() + (circles.get(i).getSpeedFactor()*cos(circles.get(i).getAngle())));
            circles.get(i).setY(circles.get(i).getY() + (circles.get(i).getSpeedFactor()*sin(circles.get(i).getAngle())));


            //check to see if on edge,
            // then set the circle randomly back to center and randomly changing the direction.
            if(circles.get(i).getX() <= 0 || circles.get(i).getX() >= this.width || circles.get(i).getY() <= 0 || circles.get(i).getY() >= this.height) {
                circles.get(i).setX(random(1,this.width));
                circles.get(i).setY(this.height/2);
                circles.get(i).setAngle(random(0,2*PI));
            }


            //check to see if intersecting then draw a line between then change the direction
            for (int r = 0; r < circles.size(); r++) {
                if(circles.get(i).isIntersecting(circles.get(r)) && circles.get(i) != circles.get(r)) {
                    this.line(circles.get(i).getX(),circles.get(i).getY(),circles.get(r).getX(),circles.get(r).getY());
                    this.stroke(circles.get(i).getColorOfLine(circles.get(r)).getRGB());
                    this.strokeWeight(1);
                    circles.get(i).changeAngleToMoveAwayFrom(circles.get(r));
                }

            }

        }




    } //End of Draw



}



