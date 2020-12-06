package com.example.colorapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    //code below is adapted from https://developer.android.com/training/appbar/actions
   override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
       R.id.action_settings -> {
           // User chose the "Settings" item, show the app settings UI...
           true
       }

       R.id.action_menu -> {
           val toast = Toast.makeText(applicationContext, "Test",Toast.LENGTH_SHORT)
           toast.show()
           true
       }

       R.id.action_test -> {
           val toast = Toast.makeText(applicationContext, "Test",Toast.LENGTH_SHORT)
           toast.show()
           true
       }


       else -> {
           // If we got here, the user's action was not recognized.
           // Invoke the superclass to handle it.
           super.onOptionsItemSelected(item)
       }
   }


}