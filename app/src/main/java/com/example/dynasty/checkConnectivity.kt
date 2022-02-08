package com.example.dynasty

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.app.ActivityCompat.recreate
import java.util.*


fun checkConnectivity(
    context: Context
): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = manager.activeNetworkInfo

    if (null == activeNetwork) {

        val builder =
            AlertDialog.Builder(Objects.requireNonNull(context))
        var inflater:LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.customdailog, null)
        val btnDone = view.findViewById<Button>(R.id.btnTryAgain)
        val btnFinish = view.findViewById<Button>(R.id.btnCancel)
        builder.setView(view)
        val alertDialog = builder.create()
        alertDialog.show()
        alertDialog.setCancelable(false)
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.getWindow()!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        btnDone.setOnClickListener { recreate(context as Activity) }
        btnFinish.setOnClickListener { finishAffinity(context as Activity) }


        /*val dialogBuilder = AlertDialog.Builder(context)
        // set message of alert dialog

        dialogBuilder.setMessage("Make sure that WI-FI or mobile data is turned on, then try again")
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Retry", DialogInterface.OnClickListener { dialog, id ->
                recreate(context as Activity)
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("No Internet Connection")
        alert.setIcon(R.mipmap.ic_launcher)
        // show alert dialog
        alert.show()*/
        return true
    }
    return false
}