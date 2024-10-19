package com.example.camera;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {
    Handler handler;
    Camera camera ;
    SurfaceView s;
    Button b ;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s = findViewById(R.id.s);
        b = findViewById(R.id.but);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},1);
        }
        else {
//
            HandlerThread thread = new HandlerThread("handler");
            thread.start();
            handler = new Handler(thread.getLooper());
            b.setOnClickListener(view -> {
                if (camera != null) {
                    camera.takePicture(null, null, null, new Camera.PictureCallback() {
                        @Override
                        public void onPictureTaken(byte[] bytes, Camera camera) {

                            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "image", "none");
                            camera.startPreview();
                        }
                    });
                } else {
                }
            });
        }
    }

    public void startCamera (){

        handler.post(new Runnable() {
            @Override
            public void run() {

                camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                camera.getParameters();
                int rotation = getWindowManager().getDefaultDisplay().getRotation();
                camera.setDisplayOrientation(rotation+90);
                while(!s.getHolder().getSurface().isValid()) {
                }
                try {
                    camera.setPreviewDisplay(s.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                camera.startPreview();
            }
        });
    }

    @Override
    protected void onPause() {
        if( camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        super.onPause();
    }


    @Override
    protected void onResume() {
        startCamera();
        super.onResume();
    }



}