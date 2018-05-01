package borgh.nacl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.WHITE;
import static borgh.nacl.IonManager.fallingIons;

/**
 * Created by Zondra on 4/18/2018.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {



    private MainThread thread;

    private boolean moving;

    private risingIon currentRisingIon;
    private Point risingIonPoint;
    private borgh.nacl.IonManager ionManager;

    private boolean movingPlayer = false;
    private boolean gameOver = false;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        currentRisingIon = new risingIon(10, 1); //should be a rand.


        //player = new RectPlayer(new Rect(Constants.SCREEN_WIDTH/2 - (Constants.SCREEN_WIDTH/6)/2,
        //        (Constants.SCREEN_HEIGHT - 150) - ((Constants.SCREEN_WIDTH)/6)/2,
        //        Constants.SCREEN_WIDTH/2 + (Constants.SCREEN_WIDTH/6)/2,
        //        (Constants.SCREEN_HEIGHT - 150) + ((Constants.SCREEN_WIDTH)/6)/2),
        //        Color.rgb(255, 0, 0));
        risingIonPoint = new Point(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT -150);

        ionManager = new borgh.nacl.IonManager(); //init

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch(Exception e) {e.printStackTrace();}
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //if(!gameOver && currentRisingIon.getRectangle().contains((int)event.getX(), (int)event.getY()))
                 //   movingPlayer = true; //allows us to move our player when game is not over.
            case MotionEvent.ACTION_MOVE:
                if((int)event.getY() < Constants.SCREEN_HEIGHT -300) {//yeah this can be a for loop f off jaiv I'm doing this at an ungodly hour bc im friggen dum
                    if ((int) event.getX() > (Constants.SCREEN_WIDTH / 5) * 4) {
                        risingIonPoint.set(Constants.SCREEN_WIDTH / 10 + 4 *(Constants.SCREEN_WIDTH/5),
                                Constants.SCREEN_HEIGHT);
                    }
                    else if ((int) event.getX() > (Constants.SCREEN_WIDTH / 5) * 3) {
                        risingIonPoint.set(Constants.SCREEN_WIDTH / 10 + 3 *(Constants.SCREEN_WIDTH/5),
                                Constants.SCREEN_HEIGHT);
                    }
                    else if ((int) event.getX() > (Constants.SCREEN_WIDTH / 5) * 2) {
                        risingIonPoint.set(Constants.SCREEN_WIDTH / 10 + 2 *(Constants.SCREEN_WIDTH/5),
                                Constants.SCREEN_HEIGHT);
                    }
                    else if ((int) event.getX() > (Constants.SCREEN_WIDTH / 5)) {
                        risingIonPoint.set(Constants.SCREEN_WIDTH / 10 + (Constants.SCREEN_WIDTH/5),
                                Constants.SCREEN_HEIGHT);
                    }
                    else if ((int) event.getX() < Constants.SCREEN_WIDTH / 5) {
                        risingIonPoint.set(Constants.SCREEN_WIDTH / 10, Constants.SCREEN_HEIGHT);
                    }
                    currentRisingIon.activate();
                }
                //playerPoint.set((int)event.getX(), (int)event.getY()); //RETURNS TAP POINT
        }

        return true;
        //return super.onTouchEvent(event);
    }

    public void update() {
        //currentRisingIon.update(risingIonPoint, moving);

        ionManager.update();
        for( fallingIon i : fallingIons ){
            Rect test = currentRisingIon.getRectangle();
            //Rect test2 = i.getRectangle();
            //System.out.println("TESTING");
            //System.out.println(test2);
            if(fallingIons.size() > 5000){
                if (Rect.intersects(i.getRectangle(), currentRisingIon.getRectangle())) {
                    if (i.getIonId() == 0) {
                        fallingIons.remove(i);
                        //System.out.println("TESTING");
                    }
                    //System.out.println("GOTCHA");
                    risingIonPoint.set(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 150);
                    moving = false;
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(WHITE);

        currentRisingIon.draw(canvas);

        ionManager.draw(canvas);

        Rect bottomTab = new Rect(0, Constants.SCREEN_HEIGHT - 300,
                Constants.SCREEN_WIDTH/2 - (Constants.SCREEN_WIDTH/5)/2, Constants.SCREEN_HEIGHT);
        Rect bottomTab2 = new Rect(Constants.SCREEN_WIDTH/2 + (Constants.SCREEN_WIDTH/5)/2,
                Constants.SCREEN_HEIGHT - 300,
                Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        Rect bottomTab3 = new Rect(0,
                Constants.SCREEN_HEIGHT - 300, Constants.SCREEN_WIDTH,
                (Constants.SCREEN_HEIGHT - 150) - ((Constants.SCREEN_WIDTH)/5)/2);
        Rect bottomTab4 = new Rect(0,
                (Constants.SCREEN_HEIGHT - 150) + ((Constants.SCREEN_WIDTH)/5)/2,
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT);


        //left top right bottom
        Paint paint = new Paint();
        paint.setColor(GRAY);
        canvas.drawRect(bottomTab, paint);
        canvas.drawRect(bottomTab2, paint);
        canvas.drawRect(bottomTab3, paint); //BOTTOMTAB3 SHOULD BE GAME OVER WHEN IT COLLIDES WITH AN ION
        canvas.drawRect(bottomTab4, paint);

    }

}
