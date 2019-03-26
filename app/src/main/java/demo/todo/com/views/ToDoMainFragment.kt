package demo.todo.com.views

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import demo.todo.com.R
import demo.todo.com.viewmodels.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * A fragment containing recycler view to load list of To Dos
 */
class ToDoMainFragment : Fragment() {
    lateinit var toDoViewModel: ToDoViewModel
    lateinit var toDoRecyclerViewAdapter: ToDoRecyclerViewAdapter
    lateinit var toDoRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView = inflater.inflate(R.layout.fragment_main, container, false)
        toDoRecyclerView = itemView.findViewById(R.id.recycler_view)
        return itemView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fab.setOnClickListener { view ->
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.main_container,NewToDoFragment()).addToBackStack("new_todo").commit()
        }
        toDoRecyclerViewAdapter = ToDoRecyclerViewAdapter()
        val movFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val mLayoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0,  movFlag) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Row is swiped from recycler view
                // remove it from adapter
                //toDoViewModel.delete()
            }
        }

        // attaching the touch helper to recycler view
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(toDoRecyclerView)

        toDoRecyclerView.layoutManager = mLayoutManager
        toDoRecyclerView.itemAnimator = DefaultItemAnimator()
        toDoRecyclerView.adapter = toDoRecyclerViewAdapter
        toDoViewModel = (activity as MainActivity).toDoViewModel
        toDoViewModel.allTodoToDoItemEntity.observe(this, Observer {
            toDoRecyclerViewAdapter.todoItemsList = it!!
            toDoRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

}
