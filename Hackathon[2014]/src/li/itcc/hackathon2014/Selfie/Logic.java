package li.itcc.hackathon2014.Selfie;

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

public class Logic extends SelfieFragment {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int STATIC_RESULT=2; //positive > 0 integer.    
    static final int RESULT_OK = 2;
    Bitmap photo = null;

    private SelfieFragment activity;
    
    public Logic(SelfieFragment selfieFragment){
        activity = selfieFragment;
    }

    
    public String TakePictureIntent() {
        String pfad = "data/data/li.itcc.hackathon2014.Selfie/";
        File file = new File( pfad );
        Uri outputFileUri = Uri.fromFile( file );
        
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
        intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );
        activity.startActivityForResult( intent, STATIC_RESULT );
    return pfad;
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
          if (requestCode == STATIC_RESULT) //check if the request code is the one you've sent
          {
                 if (resultCode == RESULT_OK) 
                 {
                 photo = (Bitmap) data.getExtras().get("data");     
                 }
                 else {
                     }
         }
    }     

}
