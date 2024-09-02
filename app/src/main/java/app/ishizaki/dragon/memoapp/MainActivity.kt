package app.ishizaki.dragon.memoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import app.ishizaki.dragon.memoapp.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter

    private val memoList: MutableList<MemoData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecyclerViewAdapter(this)
        binding.memoRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.memoRecyclerView.adapter = adapter

        binding.saveMemoButton.setOnClickListener {

            val titleText = binding.titleEditText.text.toString()
            val memoText = binding.memoEditText.text.toString()
            val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
            val newMemoId = formatter.format(LocalDateTime.now()).toLong()

            Log.d("debugging", memoList.toString())
            memoList.add(MemoData(newMemoId, titleText, memoText))
            adapter.submitList(memoList.toMutableList())
        }

    }
}