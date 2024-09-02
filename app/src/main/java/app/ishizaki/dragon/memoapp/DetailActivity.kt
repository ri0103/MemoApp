package app.ishizaki.dragon.memoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.ishizaki.dragon.memoapp.databinding.ActivityDetailBinding
import androidx.appcompat.app.AlertDialog

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this.applicationContext)


        val memoId = intent.getLongExtra("memo_id", -1L)
        val memoData = db.memoDataDao().getMemoById(memoId)

        binding.titleTextView.text = memoData?.title
        binding.memoTextView.text = memoData?.memo

        binding.deleteMemoFab.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("メモ削除")
                .setMessage("「${memoData?.title}」を削除しますか？")
                .setPositiveButton("はい") { dialog, which ->
                    // 「はい」がクリックされたときの処理
                    if (memoId != -1L) {
                        db.memoDataDao().deleteDataById(memoId)
                        Toast.makeText(this, "メモを削除しました", Toast.LENGTH_SHORT).show()
                        finish() // DetailActivityを終了してMainActivityに戻る
                    }
                }
                .setNegativeButton("キャンセル", null) // 「いいえ」をクリックしたときは何もしない
                .show()
        }

        binding.editMemoFab.setOnClickListener {
            val editIntent = Intent(this, EditMemoActivity::class.java).apply {
                putExtra("memo_id", memoId)
            }
            startActivity(editIntent)

        }

    }

    override fun onResume() {
        super.onResume()
        val memoId = intent.getLongExtra("memo_id", -1L)
        val memoData = db.memoDataDao().getMemoById(memoId)
        binding.titleTextView.text = memoData?.title
        binding.memoTextView.text = memoData?.memo
    }

}