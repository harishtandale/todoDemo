package demo.todo.com.views

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.content.DialogInterface
import android.app.AlertDialog
import android.content.ContextWrapper
import android.support.design.button.MaterialButton
import android.widget.Button
import demo.todo.com.R


class DeleteDialogFragment : DialogFragment() {
    lateinit var alertDialog : AlertDialog
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var contentView = activity?.layoutInflater?.inflate(R.layout.delete_dialog, null, false)
        contentView?.findViewById<Button>(R.id.cancel_button)?.setOnClickListener { alertDialog.dismiss() }
        contentView?.findViewById<Button>(R.id.delete_button)?.setOnClickListener {
            run {
                val toDoViewModel = (activity as MainActivity).toDoViewModel
                toDoViewModel.deleteAllTodoItems()
            }
        }
        alertDialog = AlertDialog.Builder(ContextWrapper(activity))
            .setView(contentView)
            .create()
        return alertDialog
    }
}
