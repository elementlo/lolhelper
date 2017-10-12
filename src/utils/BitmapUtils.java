package utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public final class BitmapUtils {

	private BitmapUtils() {}

	public static Bitmap toBitmap(String path) {
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(path));
			return bitmap;
		} catch(Exception exception) {
			return null;
		}
	}

	public static Bitmap createBitmap(Bitmap bitmap, int width, int height) {
		if(bitmap != null) {
			int w = bitmap.getWidth();
			int h = bitmap.getHeight();
			Matrix matrix = new Matrix();
			matrix.postScale((float)width/w, (float)height/h);
			return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
		} else
			return null;
	}

	public static boolean zoom(Context context, Bitmap.CompressFormat compressFormat, String path, String toPath, int width, int height) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Bitmap bitmap = createBitmap(toBitmap(path),width,height);	
		if(bitmap != null) {
			bitmap.compress(compressFormat, 100, byteArrayOutputStream);
			return StreamUtils.output(context, toPath, byteArrayOutputStream.toByteArray());
		}
		else
			return false;
	}

}
