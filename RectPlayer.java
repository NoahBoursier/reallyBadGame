package borgh.nacl;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Zondra on 4/18/2018.
 */

public class RectPlayer implements borgh.nacl.GameObject {

    private static Rect rectangle;
    private int color;
    private int incY;

    public static Rect getRectangle() {
        return rectangle;
    }

    public RectPlayer(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {

    }


    public void update(Point point, boolean moving){
        //left, top, right, bottom

        if(moving){
            incY += 75;
            rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2 - incY,
                    point.x + rectangle.width()/2, point.y + rectangle.height()/2 - incY);
        }
        else{
            incY = 0;
            rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
        }
    }
}
