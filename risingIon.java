package borgh.nacl;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import static android.graphics.Color.WHITE;
/**
 * Created by Zondra on 4/25/2018.
 */

public class risingIon extends fallingIon implements GameObject{

    static int[][] ionRGBs = {{255, 50, 100},{100,200,150},{200,200,50},{255, 255, 0},{255, 0, 255},{0, 0, 0},{255, 255, 255},{0, 255, 255}};
    String[] ionNames = {"H", "NH4", "Ca"};
    int[] charge = {1, 1, 2};

    private Rect rectangle;
    private int color;
    private int ionId;
    private boolean isActive;

    public risingIon(int spot, int ionId) {
        //System.out.println(test2);
        super(spot, ionId);

        isActive = false;
    }

    public void activate() {
        isActive = true;
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public int getIonId() { return ionId; }

    public void incrementY(float y) {
        if (isActive) {
            rectangle.top -= y;
            rectangle.bottom -= y;
        }
    }

    public void draw(Canvas canvas) {

    }

    public void draw3(Canvas canvas, int ionId) {
        Paint paint = new Paint();
        paint.setARGB( 255, ionRGBs[ionId][0], ionRGBs[ionId][1], ionRGBs[ionId][2]);

        canvas.drawRect(rectangle, paint);
        super.drawDigit(canvas, 120,  rectangle.left + (Constants.SCREEN_WIDTH/6)/2 , rectangle.top + (Constants.SCREEN_WIDTH/6)/2, WHITE, ionNames[ionId]);
    }



}
