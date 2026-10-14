package com.roshmalai.ai.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChartAnalysisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ChartAnalysisEntity)

    @Query("SELECT * FROM chart_analysis ORDER BY createdAt DESC LIMIT 20")
    suspend fun getRecent(): List<ChartAnalysisEntity>

    @Query("DELETE FROM chart_analysis")
    suspend fun clearAll()
}
