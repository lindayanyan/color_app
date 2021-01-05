package com.example.colorapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton



class photo : AppCompatActivity() {
    private val cameraRequest = 1888
    lateinit var imageView: ImageView
    private val fileRequest = 13
    private val TAG = photo.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        print("TAG + $TAG")
        Log.d(TAG, "my log is here")

        findViewById<FloatingActionButton>(R.id.camera_fab).setOnClickListener() { view ->
            camera_permissions()
            imageView = findViewById(R.id.img_preview)
            //code on camera stuff adapted from
            //https://www.tutorialspoint.com/how-to-work-with-camera-in-an-android-app-using-kotlin
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) //navigates to camera
            startActivityForResult(cameraIntent, cameraRequest)
            Log.d(TAG, "CAMERA")
            //still need to figure out how to save it...
        }
        findViewById<FloatingActionButton>(R.id.file_fab).setOnClickListener() { view ->
            //code for galleries adapted from
            //https://medium.com/developer-student-clubs/android-kotlin-camera-using-gallery-ff8591c26c3e
            file_permissions()
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            gallery.setType("image/*");
            startActivityForResult(gallery, fileRequest)
            Log.d(TAG, "FILE1")

        }
    }

  /* fun colorpick(){
      try {
           val imageUri: Uri = data.getData()
           val imageStream: InputStream? = contentResolver.openInputStream(imageUri)
           val selectedImage = BitmapFactory.decodeStream(imageStream)
           val drawable: Drawable = BitmapDrawable(resources, selectedImage)
           colorPickerView.setPaletteDrawable(drawable)
       } catch (e: FileNotFoundException) {
           e.printStackTrace()
       }
   }*/


    // companion object = static objects
    companion object {
        private val IMAGE_CHOOSE = 1000;
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "FILE2")
        //updates the image view
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequest) { //for camera
            val photo: Bitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(photo)
        }

        if(resultCode == RESULT_OK && requestCode == fileRequest){
           imageView.setImageURI(data?.data)
           // val imageUri: Uri = attr.data.//getData()
            //val imageStream = contentResolver.openInputStream(imageUri)
            //val selectedImage = BitmapFactory.decodeStream(imageStream)
           // val drawable: Drawable = BitmapDrawable(resources, selectedImage)
            //imageView.setPaletteDrawable(drawable)
           // imageView.setImageDrawable(drawable)
           Log.d(TAG, "error did not reach")
        }
    }




    //requesting permissions adapted from https://www.tutorialspoint.com/how-to-request-location-permission-at-runtime-on-kotlin
    //original code requested location
    //complete list of android app requests : https://androidjson.com/android-request-multiple-runtime-permissions/#:~:text=List%20of%20all%20Runtime%20Permission%20in%20Android%20Marshmallow,ACCESS_FINE_LOCATION%20ACCESS_COARSE_LOCAT%20...%20%206%20more%20rows%20
    fun file_permissions(){
        if (ContextCompat.checkSelfPermission(this@photo,
                        Manifest.permission.READ_EXTERNAL_STORAGE) !== //asks to read storage (go through user's camera roll)
            PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this@photo,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }

    }
    fun camera_permissions(){
        if (ContextCompat.checkSelfPermission(this@photo,
                        Manifest.permission.CAMERA) !== //asks to get access to device camera
            PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this@photo,
                        arrayOf(Manifest.permission.CAMERA), 2)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this@photo,
                                    Manifest.permission.READ_EXTERNAL_STORAGE) ===
                                    PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Camera Roll Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Camera Roll Permission Denied", Toast.LENGTH_SHORT).show()
                }

            }
            2 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this@photo,
                                    Manifest.permission.CAMERA) ===
                                    PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Camera Access Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Camera Access Denied", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }

}