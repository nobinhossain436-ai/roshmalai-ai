package com.roshmalai.ai

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import com.roshmalai.ai.analysis.ChartAnalyzer
import com.roshmalai.ai.analysis.ChartAnalysis
import com.roshmalai.ai.data.AppDatabase
import com.roshmalai.ai.ui.theme.RoshmalaiTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val db by lazy { AppDatabase.getInstance(applicationContext) }
    private val analyzer by lazy { ChartAnalyzer() }

    private var selectedImageUri by mutableStateOf<Uri?>(null)
    private var analysisResult by mutableStateOf<ChartAnalysis?>(null)

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        selectedImageUri = uri
        uri?.let {
            lifecycleScope.launch {
                val result = analyzer.analyzeFromUri(contentResolver, it)
                analysisResult = result
                db.chartAnalysisDao().insert(result)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoshmalaiTheme {
                RoshmalaiApp(
                    imageUri = selectedImageUri,
                    analysis = analysisResult,
                    onPickImage = { pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                    onAnalyze = {
                        selectedImageUri?.let {
                            lifecycleScope.launch {
                                val result = analyzer.analyzeFromUri(contentResolver, it)
                                analysisResult = result
                                db.chartAnalysisDao().insert(result)
                            }
                        }
                    },
                    onClearHistory = {
                        lifecycleScope.launch { db.chartAnalysisDao().clearAll() }
                        analysisResult = null
                    }
                )
            }
        }
    }
}

@Composable
fun RoshmalaiApp(
    imageUri: Uri?,
    analysis: ChartAnalysis?,
    onPickImage: () -> Unit,
    onAnalyze: () -> Unit,
    onClearHistory: () -> Unit,
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Roshmalai AI",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "স্থানীয়ভাবে, অফলাইনে, ক্যান্ডেলস্টিক চার্ট বোঝার জন্য।",
                style = MaterialTheme.typography.bodyLarge
            )
            Button(onClick = onPickImage) {
                Text("ছবি নির্বাচন করুন")
            }
            Button(onClick = onAnalyze) {
                Text("বিশ্লেষণ শুরু করুন")
            }
            Button(onClick = onClearHistory) {
                Text("ইতিহাস মুছুন")
            }

            imageUri?.let {
                AsyncImage(
                    model = it,
                    contentDescription = "চিত্র প্রিভিউ",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                )
            }

            if (analysis != null) {
                Column {
                    Text("ফলাফল", style = MaterialTheme.typography.titleMedium)
                    Text(analysis.summary)
                    Text("বিশ্বাসযোগ্যতা: ${analysis.confidence}%")
                    if (analysis.requiresManualReview) {
                        Text("অপর্যাপ্ত তথ্য: ছবিটি স্পষ্ট নয়।")
                    }
                }
            }
        }
    }
}
