package demo.todo.com.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import demo.todo.com.R
import demo.todo.com.viewmodels.ToDoViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {
    lateinit var toDoViewModel: ToDoViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toDoViewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)
        toDoViewModel.allTodoToDoItemEntity.observe(this, Observer {

        })
    }
}
