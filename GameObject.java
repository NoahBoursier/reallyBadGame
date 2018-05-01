package borgh.nacl;

import android.graphics.Canvas;

/**
 * Created by Zondra on 4/18/2018.
 */

public interface GameObject {
    //what methods we put in here have to also be in the class that implements me
    public void draw(Canvas canvas);
    public void update();
}
