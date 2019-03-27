package demo.todo.com.views

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.content.DialogInterface
import android.app.AlertDialog
import android.content.ContextWrapper
import demo.todo.com.R


class DeleteDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(ContextWrapper(activity))
            .setTitle(R.string.alert)
            .setMessage(R.string.are_you_sure)
            .setPositiveButton(
                android.R.string.yes
            ) { dialog, which ->
                run {
                    val toDoViewModel = (activity as MainActivity).toDoViewModel
                    toDoViewModel.deleteAllTodoItems()
                }
            }
            .setNegativeButton(
                android.R.string.no
            ) { dialog, which -> dialog.dismiss() }
            .create()
    }

}
