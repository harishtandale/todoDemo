package demo.todo.com.views

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import demo.todo.com.R
import demo.todo.com.viewmodels.ToDoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var toDoViewModel : ToDoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toDoViewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)
        supportFragmentManager.beginTransaction().replace(R.id.main_container, ToDoMainFragment())
            .addToBackStack("main_view").commit()
    }

    /*override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0)
            super.onBackPressed()
        else
            supportFragmentManager.popBackStackImmediate()

    }*/
}
