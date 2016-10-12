package elena.ru.rgbcircles;

import android.graphics.Color;

/**
 * Created by Dmitry on 31.05.2016.
 */
public class MainCircle extends SimpleCircle{
    public static final int INIT_RADIUS = 50;
    public static final int MAIN_SPEED = 30;
    public static final int OUR_COLOR = Color.BLUE;

    public MainCircle(int x, int y) {
        super(x, y, INIT_RADIUS);
        setColor(OUR_COLOR);//вызвали сетколор для закрашивания главногокруга
    }


    public void moveMainCircleWhenTouchAt(int x1, int y1) {
        int dx = (x1-x)* MAIN_SPEED /GameManager.getWidth();//скорость передвижения главного груга
        int dy = (y1-y)* MAIN_SPEED /GameManager.getHeight();//скорость передвижения главного груга
        x += dx;
        y += dy;

    }

    public void initRadius() {
        radius = INIT_RADIUS;
    }

    public void growRadius(SimpleCircle circle) {
        //pi *newr^2==pi*r^2+pi*eatr^2
        //newr==sqrt(r^2+eatr^2)
        radius=(int) Math.sqrt(Math.pow(radius,2)+Math.pow(circle.radius,2));
    }
}
