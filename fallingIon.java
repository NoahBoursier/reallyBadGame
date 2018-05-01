package borgh.nacl;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import static android.graphics.Color.WHITE;
import static borgh.nacl.Constants.SCREEN_WIDTH;

/**
 * Created by Zondra on 4/18/2018.
 */



public class fallingIon implements GameObject {

    static int[][] ionRGBs = {{0, 255, 150},{100,200,150},{200,200,50},{255, 255, 0},{255, 0, 255},{0, 0, 0},{255, 255, 255},{0, 255, 255}};
    String[] ionNames = {"Cl", "Br", "S"};
    int[] charge = {1, 1, 2};

    private Rect rectangle;
    private int color;
    private int ionId;

    public Rect getRectangle() {
        return rectangle;
    }

    public int getIonId() { return ionId; }

    public void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;
    }

    public fallingIon( int spot, int ionId) {
        this.ionId = ionId;
        if (spot == 10) {
            rectangle = new Rect(Constants.SCREEN_WIDTH/2 - (Constants.SCREEN_WIDTH/6)/2,
                            (Constants.SCREEN_HEIGHT - 150) - ((Constants.SCREEN_WIDTH)/6)/2,
                            Constants.SCREEN_WIDTH/2 + (Constants.SCREEN_WIDTH/6)/2,
                            (Constants.SCREEN_HEIGHT - 150) + ((Constants.SCREEN_WIDTH)/6)/2);
            System.out.println("oop");
        }
        else {
            rectangle = new Rect((SCREEN_WIDTH/5) * (spot -1), -(Constants.SCREEN_WIDTH)/5, (SCREEN_WIDTH/5) * spot, -(Constants.SCREEN_WIDTH)/5 + SCREEN_WIDTH/5);
        }
    }

    /*public fallingIon() {
        this.ionId = 1;
        rectangle = new Rect(Constants.SCREEN_WIDTH/2 - (Constants.SCREEN_WIDTH/6)/2,
                (Constants.SCREEN_HEIGHT - 150) - ((Constants.SCREEN_WIDTH)/6)/2,
                Constants.SCREEN_WIDTH/2 + (Constants.SCREEN_WIDTH/6)/2,
                (Constants.SCREEN_HEIGHT - 150) + ((Constants.SCREEN_WIDTH)/6)/2);
    }*/

    public boolean playerCollide(RectPlayer player) {
        return Rect.intersects(rectangle, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) { //called by iterator in MainThread
    //    Paint paint = new Paint();
    //    paint.setARGB( 255, (int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255));
    //    canvas.drawRect(rectangle, paint);
    }

    public void draw2(Canvas canvas, int ionId) {
        Paint paint = new Paint();
        paint.setARGB( 255, ionRGBs[ionId][0], ionRGBs[ionId][1], ionRGBs[ionId][2]);

        canvas.drawRect(rectangle, paint);
        drawDigit(canvas, 120,  rectangle.left + (Constants.SCREEN_WIDTH/5)/2 , rectangle.top + (Constants.SCREEN_WIDTH/5)/2, WHITE, ionNames[ionId]);
    }

    public void drawDigit(Canvas canvas, int textSize,  float cX, float cY, int color, String text) { //does text
        Paint tempTextPaint = new Paint();
        tempTextPaint.setAntiAlias(true);
        tempTextPaint.setStyle(Paint.Style.FILL);
        tempTextPaint.setColor(color);
        tempTextPaint.setTextSize(textSize);
        float textWidth = tempTextPaint.measureText(text);
        canvas.drawText(text, cX-(textWidth/2), cY+(textSize/2), tempTextPaint);
    }

    @Override
    public void update() {

    }

}
