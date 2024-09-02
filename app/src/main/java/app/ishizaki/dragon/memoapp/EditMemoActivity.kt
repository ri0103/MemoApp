package app.ishizaki.dragon.memoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import app.ishizaki.dragon.memoapp.databinding.ActivityEditMemoBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EditMemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditMemoBinding;
    private lateinit var adapter: RecyclerViewAdapter

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this.applicationContext)

        adapter = RecyclerViewAdapter(this)

        binding.saveMemoButton.setOnClickListener {

            val titleText = binding.titleEditText.text.toString()
            val memoText = binding.memoEditText.text.toString()
//            val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
//            val newMemoId = formatter.format(LocalDateTime.now()).toLong()
            val newMemoData: MemoData = MemoData(title = titleText, memo = memoText)

            db.memoDataDao().insert(newMemoData)
            adapter.submitList(db.memoDataDao().getAll())

//            finish()
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }

    }
}