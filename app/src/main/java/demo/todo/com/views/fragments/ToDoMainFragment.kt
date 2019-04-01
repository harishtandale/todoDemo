package demo.todo.com.views.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.*
import demo.todo.com.R
import demo.todo.com.viewmodels.ToDoViewModel
import demo.todo.com.views.dialogs.DeleteDialogFragment
import demo.todo.com.views.activities.MainActivity
import demo.todo.com.views.adapters.ToDoRecyclerViewAdapter
import demo.todo.com.views.itemtouchlisteners.RecyclerTouchListener
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
        setHasOptionsMenu(true)
        return itemView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.title = getString(R.string.app_name)
        fab.setOnClickListener { view ->
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.main_container,
                NewToDoFragment()
            )
                .addToBackStack("new_todo").commit()
        }
        initializeRecyclerView()
        setUpTouchAndSwipeListener()
    }

    /**
     *  A function to setup actions(Swipe or Touch) on the recycler view
     */
    private fun setUpTouchAndSwipeListener() {
        val movFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, movFlag) {
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
                val position = viewHolder.adapterPosition
                toDoViewModel.delete(toDoRecyclerViewAdapter.todoItemsList[position])
            }
        }

        // attaching the touch helper to recycler view
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(toDoRecyclerView)
        toDoRecyclerView.addOnItemTouchListener(
            RecyclerTouchListener(
                activity!!.applicationContext,
                toDoRecyclerView,
                object : RecyclerTouchListener.ClickListener {
                    override fun onLongClick(view: View?, position: Int) {


                    }

                    override fun onClick(view: View, position: Int) {
                        val todoItem = toDoRecyclerViewAdapter.todoItemsList[position]
                        var bundle = Bundle()
                        bundle.putParcelable("todo_item", todoItem)
                        val newToDoFragment = NewToDoFragment()
                        newToDoFragment.arguments = bundle
                        activity!!.supportFragmentManager.beginTransaction()
                            .replace(R.id.main_container, newToDoFragment).addToBackStack("new_todo").commit()
                    }
                })
        )
    }

    private fun initializeRecyclerView(grid: Boolean = true) {
        toDoRecyclerViewAdapter = ToDoRecyclerViewAdapter()
        val mLayoutManager: RecyclerView.LayoutManager = if (grid) {
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        } else {
            LinearLayoutManager(activity)
        }
        toDoRecyclerView.layoutManager = mLayoutManager
        toDoRecyclerView.itemAnimator = DefaultItemAnimator()
        toDoRecyclerView.adapter = toDoRecyclerViewAdapter
        toDoViewModel = (activity as MainActivity).toDoViewModel
        toDoViewModel.allTodoToDoItemEntity.observe(this, Observer {
            toDoRecyclerViewAdapter.todoItemsList = it!!
            if (it.isEmpty()) {
                showEmptyView()
            } else {
                toDoRecyclerView.visibility = View.VISIBLE
                add_to_do.visibility = View.GONE
                toDoRecyclerViewAdapter.notifyDataSetChanged()
            }
            (activity as MainActivity).invalidateOptionsMenu()
        })
    }
    private fun showEmptyView() {
        toDoRecyclerView.visibility = View.GONE
        add_to_do.visibility = View.VISIBLE
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        val mi = menu?.findItem(R.id.action_delete_all)
        mi?.isVisible = !toDoRecyclerViewAdapter.todoItemsList.isEmpty()
        super.onPrepareOptionsMenu(menu)
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.menu_main, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_delete_all -> showConfirmation()
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
    /**
     *  A function to ask user confirmation using dialog
     */
    private fun showConfirmation() {
        val deleteDialogFragment = DeleteDialogFragment()
        deleteDialogFragment.show(activity?.supportFragmentManager, "delete_confirmation")
    }
}
