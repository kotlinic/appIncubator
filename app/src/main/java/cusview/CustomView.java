package cusview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class CustomView extends View {
	private Paint mPaint;

	public CustomView(Context context) {
		this(context, null);
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// 初始化画笔
		initPaint();
	}

	/**
	 * 初始化画笔
	 */
	private void initPaint() {
		// 实例化画笔并打开抗锯齿
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

		/*
		 * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
		 *
		 * 画笔样式分三种：
		 * 1.Paint.Style.STROKE：描边
		 * 2.Paint.Style.FILL_AND_STROKE：描边并填充
		 * 3.Paint.Style.FILL：填充
		 */
		mPaint.setStyle(Paint.Style.STROKE);

		// 设置画笔颜色为浅灰色
		mPaint.setColor(Color.LTGRAY);

		/*
		 * 设置描边的粗细，单位：像素px
		 * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
		 */
		mPaint.setStrokeWidth(10);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 绘制圆环
		canvas.drawCircle(600, 600, 200, mPaint);

	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}