package elena.ru.rgbcircles;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by Dmitry on 31.05.2016.
 */
public class GameManager {
    public static final int MAX_CIRCLES = 10;
    private MainCircle mainCircle;
    private ArrayList<EnemyCircle> circles;//коллекция вражеских кругов
    private CanvasView canvasView;
    private static int width;
    private static int height;


    public GameManager(CanvasView canvasView, int w, int h){
        this.canvasView = canvasView;
        width = w;
        height = h;
        initMainCircle();
        initEnemyCircles();
    }

    private void initEnemyCircles() {
        SimpleCircle mainCircleArea = mainCircle.getCircleArea();//выделим область вокруг главного круга к воторой не будет изначатьно создаваться враг и еда
        circles = new ArrayList<EnemyCircle>();//выделили памят под коллекцию
        for (int i = 1; i< MAX_CIRCLES; i++){
            EnemyCircle circle;//создали временный круг
            do {
                circle = EnemyCircle.getRandomCircle();
            } while (circle.isIntersect(mainCircleArea));//создаем круги пока не пересекается с главным
            circles.add(circle);//добавили его в коллекцию
        }
        calculateAndSetCirclesColor();
    }

    private void calculateAndSetCirclesColor() {//метод рассчитать и установить цвет для круга
        for (EnemyCircle  circle: circles) {
            circle.setEnemyOrFoodColorDependsOn(mainCircle);//метод установить враг или еда
        }
    }

    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }


    private void initMainCircle() {
        mainCircle = new MainCircle(width /2, height / 2);
    }

    public void onDraw() {
        canvasView.drawCircle(mainCircle);
        for (EnemyCircle circle : circles ){
            canvasView.drawCircle(circle);
        }
    }

    public void onTouchEvent(int x, int y) {
        mainCircle.moveMainCircleWhenTouchAt(x,y);//если палец двигается,передвигаем туда главный круг
        checkCollision();//проверка на соприкосновение кругов
        moveCircles();
    }

    private void checkCollision() {
        SimpleCircle circleForDel = null;
        for (EnemyCircle circle  : circles) {
            if (mainCircle.isIntersect(circle)){//если главный круг пересек другой,закончить игру
                if(circle.issmallerThan(mainCircle)) {//если круг меньше главного
                    mainCircle.growRadius(circle);//вызываем метод увеличь радиус на объем съеденного круга
                    circleForDel = circle;//съеденный круг надо удалить
                    calculateAndSetCirclesColor();//переоценим все цвета, тк главный круг уеличился
                    break;
                }else {
                    gameEnd("YOU LOSE!!!");
                    return;
                }
            }
        }
        if(circleForDel !=null){//если круг для удаления не ноль, удалим его из коллекции
            circles.remove(circleForDel);
        }
        if(circles.isEmpty()){//если коллекция кругов пустая, закончи игру
            gameEnd("YOU WIN!!!");
        }

    }

    private void gameEnd(String text) {
        canvasView.showMessage(text);//игровое поле покажи сообщение
        mainCircle.initRadius();//сбросить радиус главного кругадо начального размера
        initEnemyCircles();//заново создаем вражеские круги
        canvasView.redraw();//перерисуем игровое поле
    }

    private void moveCircles() {//двигаем вражеские круги посл прикосновения
        for (EnemyCircle circle : circles) {
            circle.moveOneStep();

        }

    }
}
