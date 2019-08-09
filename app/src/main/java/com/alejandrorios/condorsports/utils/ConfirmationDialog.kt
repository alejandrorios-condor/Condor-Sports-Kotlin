package com.alejandrorios.condorsports.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.alejandrorios.condorsports.R

class ConfirmationDialog(private val context: Context) : DialogInterface {

    private lateinit var dialog: AlertDialog

    override fun dismiss() {
        dialog.apply {
            cancel()
        }
    }

    override fun cancel() {
        dialog.apply {
            dismiss()
        }
    }

    fun showSimple(messageId: Int, isCancelable: Boolean) {
        val resources = context.resources
        val builder = AlertDialog.Builder(context)
        val message: String = resources.getString(messageId)
        val confirmButton = resources.getString(R.string.button_ok)

        builder.apply {
            setMessage(message)
            setCancelable(isCancelable)
            setPositiveButton(confirmButton) { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
        }

        dialog = builder.show()
    }
}