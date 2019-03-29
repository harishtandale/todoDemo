package demo.todo.com.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import demo.todo.com.R
import demo.todo.com.db.entities.ToDoItemEntity
import demo.todo.com.viewmodels.ToDoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_new_to_do.*

class NewToDoFragment: Fragment() {
    var title : String?= ""
    private var content : String?= ""
    private var toDoItemEntity : ToDoItemEntity? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_to_do,container,false)
    }
    private lateinit  var toDoViewModel: ToDoViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toDoItemEntity =  arguments?.getParcelable<ToDoItemEntity>("todo_item")
        title = toDoItemEntity?.title
        content = toDoItemEntity?.content
        title_text.setText(title)
        content_text.setText(content)
        toDoViewModel = (activity as MainActivity).toDoViewModel
        button_save.setOnClickListener {
            if(!title_text.text.toString().isEmpty() or !content_text.text.toString().isEmpty()){
                if (toDoItemEntity == null) {
                    toDoViewModel.insertOrUpdate(
                        ToDoItemEntity(
                            title = title_text.text.toString(),
                            content = content_text.text.toString()
                        )
                    )
                } else {
                    toDoItemEntity!!.title = title_text.text.toString()
                    toDoItemEntity!!.content = content_text.text.toString()
                    toDoViewModel.insertOrUpdate(toDoItemEntity!!)
                }
            }
            (activity as MainActivity).supportFragmentManager.popBackStack()
        }

        button_cancel.setOnClickListener { (activity as MainActivity).supportFragmentManager.popBackStack() }
    }
 }
