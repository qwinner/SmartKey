package hyzk.smartkeydevice.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;


public class DoughnutProgress extends View {
    private static final int DEFAULT_MIN_WIDTH = 400;
    private static final int RED = 230, GREEN = 85, BLUE = 35;
    private static final int MIN_ALPHA = 30;
    private static final int MAX_ALPHA = 255;
    private static final float doughnutRaduisPercent = 0.65f;
    private static final float doughnutWidthPercent = 0.12f;
    private static final float MIDDLE_WAVE_RADUIS_PERCENT = 0.9f;
    private static final float WAVE_WIDTH = 5f;

    private static int[] doughnutColors = new int[]{
            Color.argb(MAX_ALPHA, RED, GREEN, BLUE),
            Color.argb(MIN_ALPHA, RED, GREEN, BLUE),
            Color.argb(MIN_ALPHA, RED, GREEN, BLUE)};

    private Paint paint = new Paint();
    private float width;
    private float height;
    private float currentAngle = 0f;
    private float raduis;
    private float firstWaveRaduis;
    private float secondWaveRaduis;

    private Thread thread = new Thread(){
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                postInvalidate();
            }
        }
    };

    public DoughnutProgress(Context context) {
        super(context);
        init();
    }

    public DoughnutProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DoughnutProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        thread.start();
    }

    private void resetParams() {
        width = getWidth();
        height = getHeight();
        raduis = Math.min(width, height)/2;
    }

    private void initPaint() {
        paint.reset();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        resetParams();

        canvas.translate(width / 2, height / 2);

        canvas.rotate(-currentAngle, 0, 0);
        if (currentAngle >= 360f){
            currentAngle = currentAngle - 360f;
        } else{
            currentAngle = currentAngle + 2f;
        }

        float doughnutWidth = raduis * doughnutWidthPercent;
        RectF rectF = new RectF(-raduis * doughnutRaduisPercent, -raduis * doughnutRaduisPercent, raduis * doughnutRaduisPercent, raduis * doughnutRaduisPercent);
        initPaint();
        paint.setStrokeWidth(doughnutWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setShader(new SweepGradient(0, 0, doughnutColors, null));
        canvas.drawArc(rectF, 0, 360, false, paint);

        initPaint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(MAX_ALPHA, RED, GREEN, BLUE));
        canvas.drawCircle(raduis * doughnutRaduisPercent, 0, doughnutWidth / 2, paint);

        initPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(WAVE_WIDTH);
        secondWaveRaduis = calculateWaveRaduis(secondWaveRaduis);
        firstWaveRaduis = calculateWaveRaduis(secondWaveRaduis + raduis*(MIDDLE_WAVE_RADUIS_PERCENT - doughnutRaduisPercent) - raduis*doughnutWidthPercent/2);
        paint.setColor(Color.argb(calculateWaveAlpha(secondWaveRaduis), RED, GREEN, BLUE));
        canvas.drawCircle(0, 0, secondWaveRaduis, paint);

        initPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(WAVE_WIDTH);
        paint.setColor(Color.argb(calculateWaveAlpha(firstWaveRaduis), RED, GREEN, BLUE));
        canvas.drawCircle(0, 0, firstWaveRaduis, paint);
    }

    private float calculateWaveRaduis(float waveRaduis){
        if(waveRaduis < raduis*doughnutRaduisPercent + raduis*doughnutWidthPercent/2){
            waveRaduis = raduis*doughnutRaduisPercent + raduis*doughnutWidthPercent/2;
        }
        if(waveRaduis > raduis*MIDDLE_WAVE_RADUIS_PERCENT + raduis*(MIDDLE_WAVE_RADUIS_PERCENT - doughnutRaduisPercent) - raduis*doughnutWidthPercent/2){
            waveRaduis = waveRaduis - (raduis*MIDDLE_WAVE_RADUIS_PERCENT + raduis*(MIDDLE_WAVE_RADUIS_PERCENT - doughnutRaduisPercent) - raduis*doughnutWidthPercent/2) + raduis*doughnutWidthPercent/2 + raduis*doughnutRaduisPercent;
        }
            waveRaduis += 0.6f;
        return waveRaduis;
    }

    private int calculateWaveAlpha(float waveRaduis){
        float percent = (waveRaduis-raduis*doughnutRaduisPercent-raduis*doughnutWidthPercent/2)/(raduis-raduis*doughnutRaduisPercent-raduis*doughnutWidthPercent/2);
        if(percent >= 1f){
            return 0;
        }else{
            return (int) (MIN_ALPHA*(1f-percent));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int origin) {
        int result = DEFAULT_MIN_WIDTH;
        int specMode = MeasureSpec.getMode(origin);
        int specSize = MeasureSpec.getSize(origin);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
