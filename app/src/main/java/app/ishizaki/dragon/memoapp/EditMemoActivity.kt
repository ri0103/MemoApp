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

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this.applicationContext)

        val memoId = intent.getLongExtra("memo_id", -1L)

        val memoData = db.memoDataDao().getMemoById(memoId)
        val memoTitleOriginal = memoData?.title
        val memoTextOriginal = memoData?.memo

        binding.titleEditText.setText(memoTitleOriginal)
        binding.memoEditText.setText(memoTextOriginal)


        binding.saveMemoButton.setOnClickListener {

            val titleText = binding.titleEditText.text.toString()
            val memoText = binding.memoEditText.text.toString()

            if (titleText!="" || memoText!=""){
                if (memoId == -1L){
                    val newMemoData: MemoData = MemoData(title = titleText, memo = memoText)
                    db.memoDataDao().insert(newMemoData)
                }else{
                    val updatedMemoData: MemoData = MemoData(id = memoId, title = titleText, memo = memoText)
                    db.memoDataDao().update(updatedMemoData)
                }
                finish()
            }

        }

    }
}