package app.ishizaki.dragon.memoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.ishizaki.dragon.memoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter

//    private val memoList: MutableList<MemoData> = mutableListOf()

    private lateinit var db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this.applicationContext)

        adapter = RecyclerViewAdapter(this)
        binding.memoRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.memoRecyclerView.adapter = adapter

        adapter.submitList(db.memoDataDao().getAll())

        binding.addMemoFab.setOnClickListener {
            val addIntent = Intent(this, EditMemoActivity::class.java)
            startActivity(addIntent)
            finish()
        }


    }
}