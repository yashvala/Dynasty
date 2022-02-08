package com.example.dynasty.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.dynasty.R
import com.example.dynasty.checkConnectivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (!checkConnectivity(this)) {
            Handler().postDelayed({
                val intent = Intent(this, SelectorActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }


    }

   /* fun checkConnectivity(): Boolean {
        val manager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = manager.activeNetworkInfo

        if (null == activeNetwork) {
            val dialogBuilder = AlertDialog.Builder(this)
            val intent = Intent(this, SelectorActivity::class.java)
            // set message of alert dialog
            dialogBuilder.setMessage("Make sure that WI-FI or mobile data is turned on, then try again")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Retry", DialogInterface.OnClickListener { dialog, id ->
                    recreate()
                })
                // negative button text and action
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                    finish()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("No Internet Connection")
            alert.setIcon(R.mipmap.ic_launcher)
            // show alert dialog
            alert.show()
            return true
        }
        return false
    }*/

}