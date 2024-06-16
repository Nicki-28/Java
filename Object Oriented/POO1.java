//DESCRIPTION 

//Create a program using the graphics libraries from the ACM package that does the following:

//Draw a red filled rectangle in the center of the screen.
//Move this rectangle from left to right so that when it reaches one of the screen's edges, it "bounces" and starts moving in the opposite direction.

//This version changes the position of the rectangle using the "move" method.
//Use constants for the values of the square size, the duration of the pause for smooth movement, the number of pixels you move at each step, etc.

import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import java.awt.Color;

public class POO1 extends GraphicsProgram {

    public double TIMEOUT = 50.0; // 50 milliseconds
    public double WIDTH = 150.0;
    public double HEIGHT = 80.0;

    public void run() {

        // Draw a red filled rectangle in the center of the screen
        double x = (getWidth() - WIDTH) / 2.0; // canvasWidth - rectWidth
        double y = (getHeight() - HEIGHT) / 2.0; // canvasHeight - rectHeight

        GRect rect = new GRect(x, y, WIDTH, HEIGHT);
        rect.setFilled(true);
        rect.setFillColor(Color.RED);
        add(rect);

        // Move from left to right, bounce to the other side when reaching the edges

        double limitWidth = getWidth() - WIDTH;
        double moveRight = 5.0; // pixels to move right
        double moveLeft = -5.0; // pixels to move left

        while (true) {

            // move to the right
            while (x + moveRight <= limitWidth) {
                rect.move(moveRight, 0);
                x += moveRight;
                pause(TIMEOUT);
            }

            // move to the left
            while (x + moveLeft >= 0) {
                rect.move(moveLeft, 0);
                x += moveLeft;
                pause(TIMEOUT);
            }
        }
    }

    public static void main(String[] args) {
        new POO1().start(args);
    }
}

