
package li.itcc.hackathon2014.Selfie;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

public class Logic {
    Activity activity;
    Context context;
    Uri outputFileUri;
    String _path = context.getFilesDir().getPath();

    public Logic(Activity ac, Context con) {
        activity = ac;
        context = con;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public Uri TakePictureIntent() {
        
//        outputFileUri = Uri.fromFile(image);

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        activity.startActivityForResult(intent, 0);
        String TAG = "TakePictureIntent";
        Log.v(TAG, "index=" + outputFileUri);
        return outputFileUri;

    }

    public void addWatermark(Bitmap bitmap1, int watermark) {
        // definieren .. pfad zum bitmap
        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), watermark);

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

//        try {
//            resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }

    public Bitmap BitmapSave(Bitmap image) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()+ "/saved_images";
        File myDir = new File(root);
        myDir.mkdirs();
////        String photoName = "image" + diveNum + ".jpg";
////        File file = new File(root + "/" + photoName);
//        
//        if (file.exists()) {
//            file.delete();
//        }
//        try {
//            FileOutputStream out = new FileOutputStream(file);
//            image.compress(Bitmap.CompressFormat.JPEG, 90, out);// compress
//            // output
//            out.flush();
//            out.close(); 
//        } catch(Exception ex) {
//            ex.printStackTrace();
//        }
    return null;
    }
}
