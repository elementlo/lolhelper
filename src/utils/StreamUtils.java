package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;

public final class StreamUtils {

	private StreamUtils() {}

	public static String toString(InputStream inputStream, String charset) {
		char[] buffer = new char[1024*1024];
		StringBuffer string = new StringBuffer();
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
			int bufferLength = inputStreamReader.read(buffer);
			while(bufferLength != -1) {
				string.append(new String(buffer));
				bufferLength = inputStreamReader.read(buffer);
			}
			inputStreamReader.close();
			inputStream.close();
			return string.toString();
		} catch(Exception exception) {
			return null;
		}
	}

	public static String toString(InputStream inputStream, String charset, char[] buffer) {
		StringBuffer string = new StringBuffer();
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
			int bufferLength = inputStreamReader.read(buffer);
			while(bufferLength != -1) {
				string.append(new String(buffer));
				bufferLength = inputStreamReader.read(buffer);
			}
			inputStreamReader.close();
			inputStream.close();
			return string.toString();
		} catch(Exception exception) {
			return null;
		}
	}

	public static String toString(String path, String charset) {
		char[] buffer = new char[1024*1024];
		StringBuffer string = new StringBuffer();
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
			int bufferLength = inputStreamReader.read(buffer);
			while(bufferLength != -1) {
				string.append(new String(buffer));
				bufferLength = inputStreamReader.read(buffer);
			}
			inputStreamReader.close();
			fileInputStream.close();
			return string.toString();
		} catch(Exception exception) {
			return null;
		}
	}

	public static String toString(String path, String charset, char[] buffer) {
		StringBuffer string = new StringBuffer();
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
			int bufferLength = inputStreamReader.read(buffer);
			while(bufferLength != -1) {
				string.append(new String(buffer));
				bufferLength = inputStreamReader.read(buffer);
			}
			inputStreamReader.close();
			fileInputStream.close();
			return string.toString();
		} catch(Exception exception) {
			return null;
		}
	}

	public static Object toObject(String path) {
		try {
			return new ObjectInputStream(new FileInputStream(path)).readObject();
		} catch(Exception exception) {
			return null;
		}
	}

	public static boolean output(Context context, String path, InputStream inputStream) {
		synchronized (context) {
			byte[] buffer = new byte[1024*1024];
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(path);
				int bufferLength = inputStream.read(buffer);
				while(bufferLength != -1) {
					fileOutputStream.write(buffer, 0, bufferLength);
					fileOutputStream.flush();
					bufferLength = inputStream.read(buffer);
				}
				fileOutputStream.close();
				return true;
			} catch(Exception exception) {
				return false;
			}
		}
	}

	public static boolean output(Context context, String path, InputStream inputStream, byte[] buffer) {
		synchronized (context) {
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(path);
				int bufferLength = inputStream.read(buffer);
				while(bufferLength != -1) {
					fileOutputStream.write(buffer, 0, bufferLength);
					fileOutputStream.flush();
					bufferLength = inputStream.read(buffer);
				}
				fileOutputStream.close();
				return true;
			} catch(Exception exception) {
				return false;
			}
		}
	}

	public static boolean output(Context context, String path, byte[] buffer) {
		synchronized (context) {
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(path);
				fileOutputStream.write(buffer);
				fileOutputStream.close();
				return true;
			} catch(Exception exception) {
				return false;
			}
		}
	}

	public static boolean output(Context context, String path, Object object) {
		synchronized (context) {
			try {
				new ObjectOutputStream(new FileOutputStream(path)).writeObject(object);
				return true;
			} catch(Exception exception) {
				return false;
			}
		}
	}

}
