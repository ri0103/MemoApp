package app.ishizaki.dragon.memoapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.ishizaki.dragon.memoapp.databinding.ItemMemoDataCellBinding

class RecyclerViewAdapter(
    private val context: Context,
) : ListAdapter <MemoData, MemoDataViewHolder>(diffCallback) {

//    private val _checkEvent = MutableLiveData(-1L)
//    val checkEvent: LiveData<Long> get() = _checkEvent


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MemoData>() {

            override fun areItemsTheSame(oldItem: MemoData, newItem: MemoData) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MemoData, newItem: MemoData) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoDataViewHolder {
        val view = ItemMemoDataCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemoDataViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.titleTextView.text = item.title
        holder.binding.memoTextView.text = item.memo

        holder.binding.cellConstraintLayout.setOnClickListener {
            val detailIntent = Intent(context, DetailActivity::class.java).apply{
                putExtra("memo_id", item.id)
            }
            context.startActivity(detailIntent)
        }
//        holder.binding.checkBox.setOnClickListener {
//            _checkEvent.value = item.id
//            holder.binding.checkBox.isChecked = false
//            Toast.makeText(context, "削除しました", Toast.LENGTH_SHORT).show()
//        }
    }
}

class MemoDataViewHolder(val binding: ItemMemoDataCellBinding) :
    RecyclerView.ViewHolder(binding.root)