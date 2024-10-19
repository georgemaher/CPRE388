package com.example.camerax2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Size;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity  {

    private PreviewView previewView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    ImageAnalysis imageAnalysis;
    TextView textView;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previewView = findViewById(R.id.preview);
        textView = findViewById(R.id.text);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},1);
        }

             cameraProviderFuture = ProcessCameraProvider.getInstance(this);
            cameraProviderFuture.addListener(() -> {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindImageAnalysis(cameraProvider);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }, ContextCompat.getMainExecutor(this));

    }
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void bindImageAnalysis(@NonNull ProcessCameraProvider cameraProvider) {
        cameraProvider.unbindAll();
        //camera selector use case
        CameraSelector cameraSelector = new CameraSelector.Builder()
                                          .requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
        //preview use case
        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        //image analysis
         imageAnalysis =
                new ImageAnalysis.Builder().setTargetResolution(new Size(1280, 720))
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build();
         imageAnalysis.setAnalyzer(getMainExecutor(), imageProxy -> {
             @SuppressLint("UnsafeOptInUsageError")
             Image mediaImage = imageProxy.getImage();
             if (mediaImage != null) {
                 InputImage image =
                         InputImage.fromMediaImage(mediaImage, imageProxy.getImageInfo().getRotationDegrees());

                 ImageLabeler labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS);
                 labeler.process(image)
                         .addOnSuccessListener(labels -> {
                             StringBuilder temp = new StringBuilder();
                             for (ImageLabel label : labels) {
                                 String text = label.getText();
                                 float confidence = label.getConfidence();
                                 int index = label.getIndex();
                                 temp.append(text).append(", ").append(confidence).append(", ").append(index).append("\n");
                             }
                             textView.setText(temp.toString());
                             imageProxy.close();

                         })
                         .addOnFailureListener(e -> {
                             // Task failed with an exception
                             // ...
                             imageProxy.close();
                         });
             }
         });
        cameraProvider.bindToLifecycle(this,cameraSelector,preview,imageAnalysis);
    }
}
