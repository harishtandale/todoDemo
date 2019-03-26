package demo.todo.com.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import demo.todo.com.R
import demo.todo.com.db.entities.ToDoItemEntity
import demo.todo.com.viewmodels.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_new_to_do.*

class NewToDoFragment: Fragment() {
    var title : String?= ""
    var content : String?= ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_to_do,container,false)
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_save,menu)
    }

    private lateinit  var toDoViewModel: ToDoViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        title = arguments!!.getString("title")
//        content = arguments!!.getString("content")
        title_text.setText(title)
        content_text.setText(content)
        toDoViewModel = (activity as MainActivity).toDoViewModel
        button_save.setOnClickListener {
            toDoViewModel.insertOrUpdate(ToDoItemEntity(title = title_text.text.toString(),content = content_text.text.toString(),id=null))
            (activity as MainActivity).supportFragmentManager.popBackStack()
        }
     }
 }
