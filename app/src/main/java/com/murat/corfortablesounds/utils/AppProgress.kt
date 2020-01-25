package com.murat.corfortablesounds.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.murat.corfortablesounds.R
import io.reactivex.disposables.CompositeDisposable

class AppProgress {
    companion object {
        fun progressDialog(context: Context): Dialog {
            val composite = CompositeDisposable()
            val dialog = Dialog(context)
            val inflate = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)
            dialog.setContentView(inflate)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT)
            )
            return dialog
        }
    }
}