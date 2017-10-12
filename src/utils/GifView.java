package utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

public class GifView extends View {

	private long mMovieStart;
	private String mWidth,mHeight;
	private Movie mMovie;
	private Bitmap mBitmap;
	private Matrix mMatrix = new Matrix();

	public GifView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		mMovie = Movie.decodeStream(context.getResources().openRawResource(attributeSet.getAttributeResourceValue(null, "scr", 0)));
		mBitmap = BitmapFactory.decodeStream(context.getResources().openRawResource(attributeSet.getAttributeResourceValue(null, "scr", 0)));
		mWidth = attributeSet.getAttributeValue(null, "width");
		mHeight =attributeSet.getAttributeValue(null, "height");
		if(!mWidth.equals("match_parent"))
			mWidth = utils.DensityUtil.dip2px(context, Float.valueOf(mWidth)) + "";
		if(!mHeight.equals("match_parent"))
			mHeight = utils.DensityUtil.dip2px(context, Float.valueOf(mHeight)) + "";
	}

	@SuppressLint("DrawAllocation")
	protected void onDraw(Canvas canvas) {
		long currentTime = System.currentTimeMillis();
		if (mMovieStart == 0) {
			float w = mBitmap.getWidth();
			float h = mBitmap.getHeight();
			if(mWidth.equals("match_parent"))
				mWidth = canvas.getWidth() + "";
			if(mHeight.equals("match_parent"))
				mHeight = canvas.getHeight() + "";
			mMatrix.postScale(Float.valueOf(mWidth)/w, Float.valueOf(mHeight)/h);
			mMovieStart = currentTime;
		}
		if (mMovie != null) {
			canvas.setMatrix(mMatrix);
			int duration = mMovie.duration();
			int relTime = (int) ((currentTime - mMovieStart) % duration);
			mMovie.setTime(relTime);
			mMovie.draw(canvas, 0, 0);
			invalidate();
		}
	}

}
