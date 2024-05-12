package com.example.todolistfinal
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistfinal.databinding.ActivityMainBinding
import androidx.appcompat.app.AlertDialog
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), OnItemClick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ToDoListViewModel
    private val list = mutableListOf<ToDoListData>()
    private val listAdapter = ListAdapter(list, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout and set up data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(ToDoListViewModel::class.java)
        binding.viewModel = viewModel // Set the ViewModel in the binding

        // Set up RecyclerView
        binding.rvTodoList.layoutManager = LinearLayoutManager(this)
        binding.rvTodoList.adapter = listAdapter

        // Other initialization code...
    }

    // Implement other methods as needed...

    override fun onItemClick(v: View, position: Int) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.apply {
            setMessage(list[position].title)
            setPositiveButton("Edit") { dialog, _ ->
                viewModel.title.set(list[position].title)
                viewModel.date.set(list[position].date)
                viewModel.time.set(list[position].time)
                viewModel.position = position
                viewModel.index = list[position].indexDb
            }
            setNegativeButton("Delete") { dialog, _ ->
                viewModel.delete(list[position].indexDb)
            }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    override fun onStop() {
        super.onStop()
    }

}


