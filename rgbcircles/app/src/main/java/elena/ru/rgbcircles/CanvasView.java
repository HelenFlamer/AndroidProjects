package elena.ru.rgbcircles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Dmitry on 31.05.2016.
 */
public class CanvasView extends View implements ICanvasView{
    private static int width;
    private static int height;
    private GameManager gameManager;
    private Paint paint;
    private Canvas canvas;
    private Toast toast;



    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidthAndHeight(context);//метод для определения размеров экрана
        initPaint();
        gameManager = new GameManager(this,width, height);

    }

    private void initPaint() {
        paint =new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }


    private void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);//узнаем размер экрана
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);//возвращает координаты точки правого нижнегоугла
        width = point.x;
        height = point.y;//ширине и высоте задаем координаты угла

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        gameManager.onDraw();
    }

    @Override
    public void drawCircle(SimpleCircle circle) {

        paint.setColor(circle.getColor());//присвоим пэйнту какой то цвет
        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(),paint);

    }

    @Override
    public void redraw() {
        invalidate();//перерисовывает картинку

    }

    @Override
    public void showMessage(String text) {
        if (toast !=null) {
            toast.cancel();//если на экране что то отображалось, удалить это
        }
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);//передается ссылка на контекст, текстсообщения и длина
        toast.setGravity(Gravity.CENTER,0,0);//отображать сообщение в центре экрана
        toast.show();//отображаем на экран
    }

    //метод для определения прикосновения к экрану
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();//узнали координаты прикосновения
        if (event.getAction()==MotionEvent.ACTION_MOVE){//если палец двигается по экрану
            gameManager.onTouchEvent(x,y);
        }
        invalidate();
        return true;
    }
}
