package demo.swipe.com.accessibilitydemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by USer on 26-10-2016.
 */

public class NewView extends View {

    private static final String QUOTE = "This is a curved text";
    private Path circle;
    private Paint cPaint;
    private Paint tPaint;

    public NewView(Context context) {
        super(context);

        int color = Color.argb(127, 255, 0, 255);

        circle = new Path();
        circle.addCircle(230, 350, 150, Path.Direction.CW);
        cPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        cPaint.setStyle(Paint.Style.STROKE);
        cPaint.setColor(Color.LTGRAY);
        cPaint.setStrokeWidth(3);

        setBackgroundResource(R.drawable.icon);

        tPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        tPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        tPaint.setColor(Color.BLACK);
        tPaint.setTextSize(50);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawTextOnPath(QUOTE, circle, 20, 20, tPaint);
    }

}
