

/**
 * Created by Jensen on 11/13/16.
 */



import java.awt.Color;

class Circle{


    private float x;
    private float y;
    private float radius;
    private float speedFactor;
    private float angle;
    private int votes;
    private Color c;

    public Circle(float xValue, float yValue, float radiusValue, float speedFactorValue, float angleValue, int votesValue, Color cValue) {
        x = xValue;
        y = yValue;
        radius = radiusValue;
        speedFactor = speedFactorValue;
        angle = angleValue;
        votes = votesValue;
        c = cValue;
    }

    public float getX() {
        return x;
    }

    public void setX(float xValue) {
        x = xValue;
    }

    public float getY() {
        return y;
    }

    public void setY(float yValue) {
        y = yValue;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radiusValue) {
        radius = radiusValue;
    }

    public float getSpeedFactor() {
        return speedFactor;
    }

    public void setSpeedFactor(float speedFactorValue) {
        speedFactor = speedFactorValue;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angleValue) {
        angle = angleValue;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Color getColor() {
        return c;
    }

    public void setColor(Color col) {
        this.c = col;
    }

    public float distanceFromCenterToCenterOf(Circle other) {
        return (float) Math.sqrt( (Math.pow(other.getX() - this.getX(),2)) + (Math.pow(other.getY() - this.getY(),2)) );
    }

    public boolean isIntersecting(Circle other) {

        if(this.distanceFromCenterToCenterOf(other) <= (this.getRadius() + other.getRadius())) {
            return true;
        }
        else return false;
    }

    public void changeAngleToMoveAwayFrom(Circle other) {
        if(this.isIntersecting(other)) {
          this.setAngle((float) this.getAngle() + (float) Math.PI/4);
          other.setAngle((float) other.getAngle() - (float) Math.PI/4);
        }
    }

    public Color getColorOfLine(Circle other) { //TODO: FIX THIS
        if(this.getColor().getRed() == 255 && other.getColor().getRed() == 255) {
            return new Color(255,0,0); //red thingy
        }
        else if (this.getColor().getBlue() == 255 && other.getColor().getBlue() == 255) {
            return new Color(0,0,255); //blue

        }
        else {
            int red = 0;
            int blue = 0;
            int votes = this.getVotes();
            int otherVotes = other.getVotes();

            if(votes > otherVotes) {
                if(this.getColor().getRed()==255) {
                    red=255;
                    blue= (int) ((float) (otherVotes*255)/votes);
                }
                else if (this.getColor().getBlue()==255) {
                    red= (int) ((float) (otherVotes*255)/votes);
                    blue=255;
                }
            }
            else if(otherVotes > votes) {
                if(other.getColor().getRed()==255) {
                    red=255;
                    blue=(int) ((float) (votes*255)/otherVotes);
                }
                else if(other.getColor().getBlue()==255) {
                    red =(int) ((float) (votes*255)/otherVotes);
                    blue = 255;
                }
            }
            else {
                red = 255;
                blue = 255;
            }
//            System.out.println("votes: " + votes);
//            System.out.println("otherVotes: " + otherVotes);
//            System.out.println("Red: " + red);
//            System.out.println("Blue: " + blue);

            return new Color(red,0, blue); //purple
        }
    }
}