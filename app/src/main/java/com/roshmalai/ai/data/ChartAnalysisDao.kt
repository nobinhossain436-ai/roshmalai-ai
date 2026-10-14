package com.roshmalai.ai.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chart_analysis")
data class ChartAnalysisEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val createdAt: Long,
    val summary: String,
    val confidence: Int,
    val requiresManualReview: Boolean,
    val imagePath: String?
)
