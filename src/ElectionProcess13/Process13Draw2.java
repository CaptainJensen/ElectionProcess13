package ElectionProcess13;

/**
 * Created by Jensen on 11/28/16.
 */

import processing.core.PApplet;
import java.awt.*;
import java.util.ArrayList;

/*
*
*   Created By Jensen
*
*/


import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

public class Process13Draw2 extends PApplet {

    private float x;
    private float y;
    private float angle;
    private float speed;
    private float radius;
    private Color party;
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private String results[] = loadStrings("ElectionProcess13/2012ElectionResults.txt");



    public void setup(){
        size(1600,1000);

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
            speed= random(1,51);


            circles.add(new Circle(x,y,radius,speed,angle,Integer.valueOf(state[2]),party));
        }



    }
    public void draw() {




        //redraw the background to update the circles
        this.background(200);



        for (int i = 0; i < circles.size(); i++) { //draw the circles
            //draw the circle
            this.ellipse(circles.get(i).getX(),circles.get(i).getY(),circles.get(i).getRadius(),circles.get(i).getRadius());
            this.fill(circles.get(i).getColor().getRGB());

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

            //check to see if intersecting then draw a line between them.
            for (int r = 0; r < circles.size(); r++) {
                if(circles.get(i).isIntersecting(circles.get(r))) {
                    this.line(circles.get(i).getX(),circles.get(i).getY(),circles.get(r).getX(),circles.get(r).getY());
                    this.strokeWeight(1);
                }
            }

        }

        //starting line
        this.line(0, this.height/2, this.width, this.height/2);

    }



}
