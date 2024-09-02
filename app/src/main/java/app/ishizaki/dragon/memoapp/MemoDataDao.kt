package app.ishizaki.dragon.memoapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemoDataDao {

    @Insert
    fun insert(memoData: MemoData)

    @Query("select * from memo_data")
    fun getAll(): List<MemoData>


    @Query("delete from memo_data WHERE id = :id")
    fun deleteDataById(id: Long);
//    @Query("select * from memo_data where id = :id")")
}