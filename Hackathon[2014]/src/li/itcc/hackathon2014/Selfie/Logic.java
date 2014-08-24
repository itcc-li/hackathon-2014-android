
package li.itcc.hackathon2014.Selfie;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import li.itcc.hackathon2014.R;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class Logic {
    Activity activity;
    Context context;
    Uri outputFileUri;
    String _path = "data/data/li.itcc.hackathon2014.Selfie/";

    public Logic(Activity ac, Context con) {
        activity = ac;
        context = con;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public Uri TakePictureIntent() {
        File file = new File(_path);
        outputFileUri = Uri.fromFile(file);

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        activity.startActivityForResult(intent, 0);
        String TAG = "TakePictureIntent";
        Log.v(TAG, "index=" + outputFileUri);
        return outputFileUri;

    }

    public void addWatermark(String pic, int watermark) {
        // definieren .. pfad zum bitmap
        Bitmap bitmap1 = null;
        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), watermark);

        InputStream is = null;
        BufferedInputStream bis = null;

        try {
            URLConnection conn = new URL(pic).openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is, 80000);
            bitmap1 = BitmapFactory.decodeStream(bis);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // create white Image
        Bitmap resultBitmap = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(),
                Bitmap.Config.ARGB_8888);

        // create Canvas with white Image
        Canvas c = new Canvas(resultBitmap);

        // draw pic
        c.drawBitmap(bitmap1, 0, 0, null);
        Paint p = new Paint();
        p.setAlpha(127);

        // draw watermark
        c.drawBitmap(bitmap2, 0, 0, p);
        //TODO: Save Canvas: resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File(Activity.)));

    } // TO DO: bmp 2 jpeg und speichern
}
