package ElectionProcess13;

/*
*
*   Created By Jensen
*
*/


import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

public class Process13 extends PApplet {

    private float x;
    private float y;
    private float angle;
    private float speed;
    private float radius;
    private Color party;
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private String results[] = loadStrings("ElectionProcess13/2012ElectionResults.txt");
//    private String votes[] = new String[51];
//    private String party[] = new String[51];

    public void setup(){
        size(600,600);

        for (int i = 0; i < results.length-1; i++) {

            String[] state = split(results[i], " ");

            if(state[1].equals("R")) {
                party = new Color(255,0,0);
            }
            else if(state[1].equals("D")) {
                party = new Color(0, 0, 255);
            }

            x = random(1,this.width);
            y = this.height/2;
            angle = random(0,2*PI);
            radius = random(1,51);
            speed= random(1,1);


            circles.add(new Circle(x,y,radius,speed,angle,Integer.valueOf(state[2]),party));
        }



    }
    public void draw() {

//      x+=speed*cos(angle);
//      y+=speed*sin(angle);



        //redraw the background to update the circles
        this.background(200);



        for (int i = 0; i < 50; i++) { //draw the circles

            //draw the circle
            this.ellipse(circles.get(i).getX(),circles.get(i).getY(),circles.get(i).getRadius(),circles.get(i).getRadius());
            this.fill(circles.get(i).getColor().getRGB());

            //move the circles
            circles.get(i).setX(circles.get(i).getX() + (circles.get(i).getSpeedFactor()*cos(circles.get(i).getAngle())));
            circles.get(i).setY(circles.get(i).getY() + (circles.get(i).getSpeedFactor()*sin(circles.get(i).getAngle())));

            //check to see if on edge,
            // then set the circle randomly back to center and randomly changing the direction.
            if(circles.get(i).getX() <= 0 || circles.get(i).getX() >= this.width) {
                circles.get(i).setX(random(1,this.width));
                circles.get(i).setY(this.height/2);
                circles.get(i).setAngle(random(0,2*PI));
            }
            if(circles.get(i).getY() <= 0 || circles.get(i).getY() >= this.height) {
                circles.get(i).setX(random(1,this.width));
                circles.get(i).setY(this.height/2);
                circles.get(i).setAngle(random(0,2*PI));
            }

        }

        //starting line
        this.line(0, this.height/2, this.width, this.height/2);

    }



}
