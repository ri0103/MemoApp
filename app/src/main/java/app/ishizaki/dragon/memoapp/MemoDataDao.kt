package app.ishizaki.dragon.memoapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MemoDataDao {

    @Insert
    fun insert(memoData: MemoData)

    @Query("select * from memo_data")
    fun getAll(): List<MemoData>

    @Query("select * from memo_data WHERE id = :id LIMIT 1")
    fun getMemoById(id: Long): MemoData?

    @Query("delete from memo_data WHERE id = :id")
    fun deleteDataById(id: Long);

    @Update
    fun update(memoData: MemoData)

}