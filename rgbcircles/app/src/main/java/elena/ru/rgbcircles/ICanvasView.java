package elena.ru.rgbcircles;

/**
 * Created by Dmitry on 31.05.2016.
 */
public interface ICanvasView {
    void drawCircle(SimpleCircle circle);

    void redraw();

    void showMessage(String text);
}
