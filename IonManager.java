package borgh.nacl;

import android.graphics.Canvas;

import java.util.ArrayList;



/**
 * Created by Zondra on 4/18/2018.
 */

public class IonManager {

    public static ArrayList<fallingIon> fallingIons;

    public static ArrayList<risingIon> risingIons;

    private long startTime;
    private long initTime;

    public IonManager() {

        startTime = initTime = System.currentTimeMillis();

        fallingIons = new ArrayList<>();

        risingIons = new ArrayList<>();

        populateIons();
    }

    public boolean playerCollide(RectPlayer player) { //You can iterate through the ions and test if adjacent ions add up to the proper amt.
        for(fallingIon i : fallingIons) {
            if(i.playerCollide(player)) return true;
        }
        return false;
    }

    private void populateIons() { //creates the lads

        for(int i = 0; i <= 5; i++){
            fallingIons.add(new fallingIon(i, (int)(Math.random() * 3)));
        }

        risingIons.add(new risingIon(10, (int)(Math.random() * 3)));
    }

    public void update(){ //moves lads
        int delta = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT/(60000.0f - (float)(500.0*Math.log((startTime - initTime)/1000.0))); //takes 60 seconds for it to move down.
        for(fallingIon i : fallingIons){
            i.incrementY(2); //* delta //////smaller = slower speed
        }
        for(risingIon i : risingIons){
            i.incrementY(50);
        }
        if(fallingIons.get(fallingIons.size() - 1).getRectangle().top >= 0 ) { //(Constants.SCREEN_WIDTH)/6
            //Log.d("STOP", "d" + (fallingIons.get(fallingIons.size() - 1).getRectangle().top));
            //System.out.println("BOOP");
            for(int i = 0; i <= 5; i++){
                fallingIons.add(new fallingIon(i, (int)(Math.random() * 3)));
            }
            //int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            //fallingIons.add(0, new fallingIon(ionHeight, color, xStart,
            //fallingIons.get(0).getRectangle().top - ionHeight - ionGap));
            //fallingIons.remove(fallingIons.size() - 1);
        }
    }

    public void draw(Canvas canvas) {//called by iterator in MainThread
        for(fallingIon i : fallingIons)
            i.draw2(canvas, i.getIonId());
        for(risingIon r : risingIons)
            r.draw3(canvas, r.getIonId());
    }

}
