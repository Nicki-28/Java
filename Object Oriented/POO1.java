import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import java.awt.Color;

public class POO1 extends GraphicsProgram{

    public double TIMEOUT= 50.0; //50 milisegundos
    public double WIDTH= 150.0;
    public double HEIGHT= 80.0;

    public void run(){

        //Escribir un rectángulo pintado de rojo en el centro de la pantalla
        double x= (getWidth()-WIDTH)/2.0; //canvasWidth-rectWidth
        double y= (getHeight()-HEIGHT)/2.0; // canvasHeight-rectHeight

        GRect rect=new GRect(x,y,WIDTH,HEIGHT);
        rect.setFilled(true);
        rect.setFillColor(Color.RED);
        add(rect);

        //Mover de izq a derecha cuando llegue a extremos rebotar hacia el otro lado.


        double limitwidth= getWidth()-WIDTH;

        double i=5.0;//pixeles que se agregan para que se mueva derecha
        double j=-5.0; //pixeles que se agregan para que se mueva izquierda

        while(true){

            //mueve a la derecha
            while(x+i<=limitwidth) {
                rect.move(i, 0);
                x += i;
                pause(TIMEOUT);
            }

            //mueve a la izquierda
            while(x+j>=0){
                    rect.move(j,0);
                    x+=j;
                    pause(TIMEOUT);
            }
        }


    }
    public static void main(String[] args) {
        new POO1().start(args);
    }
}