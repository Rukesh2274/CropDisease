package com.example.cropdisease.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter


@Composable
fun DetailScreen(imageText: ImageText) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState))
                {
                    val offset = (scrollState.value / 2)
                    val offsetDp = with(LocalDensity.current) { offset.toDp() }
                    Image(
                        modifier = Modifier
                            .heightIn(max = this@BoxWithConstraints.maxHeight / 2)
                            .fillMaxWidth()
                            .padding(top = offsetDp),
                        painter = rememberAsyncImagePainter(imageText.imageUrl),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )

                    Surface(modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primaryContainer) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(
                                text = imageText.title,
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.W500,
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = imageText.text,
                                maxLines = 2,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.W400,
                                fontSize = 17.sp,
                            )
                        }
                    }
                    //content here
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Solution :",
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 24.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.W500,
                            modifier = Modifier.padding(20.dp,15.dp)
                        )
                        Text(
                            text = imageText.answer,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.W400,
                            modifier = Modifier.padding(20.dp,0.dp)
                        )
                    }
                }
            }
        }
    }
}