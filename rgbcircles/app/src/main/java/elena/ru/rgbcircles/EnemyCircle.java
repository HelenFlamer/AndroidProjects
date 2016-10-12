package elena.ru.rgbcircles;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Dmitry on 31.05.2016.
 */
public class EnemyCircle extends SimpleCircle {

    public static final int FROM_RADIUS = 10;
    public static final int TO_RADIUS = 110;
    public static final int ENEMY_COLOR = Color.RED;
    public static final int FOOD_COLOR = Color.rgb(0, 200, 0);
    public static final int RANDOM_SPEED = 10;
    private int dx;
    private int dy;


    public EnemyCircle(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;

    }

    public static EnemyCircle getRandomCircle() {
        Random random = new Random();
        int x = random.nextInt(GameManager.getWidth());
        int y = random.nextInt(GameManager.getHeight());
        int dx = 1 + random.nextInt(RANDOM_SPEED);
        int dy = 1 + random.nextInt(RANDOM_SPEED);
        int radius = FROM_RADIUS + random.nextInt(TO_RADIUS - FROM_RADIUS);
        EnemyCircle enemyCircle = new EnemyCircle(x ,y, radius, dx, dy);
        

        return  enemyCircle;
    }

    public void setEnemyOrFoodColorDependsOn(MainCircle mainCircle) {//установи враг или еда в зависимости от размера главного круга
        if (issmallerThan(mainCircle)){//если меньше главного круга
            setColor(FOOD_COLOR);
        }else{
            setColor(ENEMY_COLOR);
        }
    }

    protected boolean issmallerThan(SimpleCircle circle) {
        if(radius < circle.radius)
        {
            return true;
        }
        return false;
    }

    public void moveOneStep() {//передвинься на 1 шаг

        x+=dx;
        y+=dy;
        checkBounds();//прроверим, выходит ли круг заграницы экрана
    }

    private void checkBounds() {
        if (x>GameManager.getWidth() || x<0){//если координата большеразмера экрана или меньше 0
            dx=-dx;//меняем направление на противоположное
        }
        if (y>GameManager.getHeight() || y<0){
            dy=-dy;
        }
    }
}
