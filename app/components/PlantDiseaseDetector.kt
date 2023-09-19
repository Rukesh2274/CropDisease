package com.example.cropdisease.components

import android.app.Activity
import androidx.compose.ui.layout.ContentScale
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Bitmap
import androidx.compose.ui.graphics.asImageBitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.dokar.sheets.BottomSheet
import com.dokar.sheets.rememberBottomSheetState
import kotlinx.coroutines.launch
import java.io.IOException


@Composable
fun PlantDiseaseDetector(assets: AssetManager, context: Context, navController: NavController) {
    val mInputSize = 224
    lateinit var mClassifier: Classifier
    val mBitmap= remember { mutableStateOf(loadImageFromAssets(assets)) }
    val mModelPath = "plant_disease_model.tflite"
    val mLabelPath = "plant_labels.txt"
    mClassifier = Classifier(assets, mModelPath, mLabelPath, mInputSize)


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        loadImageFromAssets(assets)
        val cameraLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                    val imageBitmap = result.data!!.extras?.get("data") as Bitmap
                    // Scale the captured image bitmap to the desired size
                    mBitmap.value = scaleImage(imageBitmap)
                }
            }


        Image(
            bitmap = mBitmap.value.asImageBitmap(),
            contentDescription = "Selected Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop,
        )

        Button(
            onClick = {
                val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                cameraLauncher.launch(callCameraIntent) },
            modifier = Modifier.padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.Gray
            )
        ) {
            Text(
                text = "Open Camera",
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
            )
        }

        val galleryLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                    val uri = result.data!!.data
                    try {
                        val imageBitmap =
                            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                        // Scale the selected image bitmap to the desired size
                        mBitmap.value = scaleImage(imageBitmap)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

        Button(
            onClick = {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryLauncher.launch(intent)
            },
            modifier = Modifier.padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.Gray
            )
        ) {
            Text(
                text = "Open Gallery",
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
            )
        }
        // Define a state variable to hold the text message to display
        //detection button part
        val scope = rememberCoroutineScope()
        val simpleSheetState = rememberBottomSheetState()
        var message by remember { mutableStateOf("") }
        Column(modifier = Modifier) {
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                        scope.launch { simpleSheetState.expand() }
                    val results = mClassifier.recognizeImage(mBitmap.value).firstOrNull()
                    val disease = results?.title ?: "Unknown"
                    val treatment = when (disease) {
                        "apple apple scab" -> "1.Treat with copper-based fungicides.\n" + "2.Rotate crops to reduce the buildup of pathogens in the soil."
                        "apple black rot" -> "Prune infected branches and apply fungicides."
                        "apple cedar apple rust" -> "Remove nearby cedar trees and apply fungicides."
                        "apple healthy" -> "Your plant is good but maintain good orchard hygiene and proper nutrition."
                        "blueberry healthy" -> "Your crop is good but regularly apply pruning and fertilization."
                        "cherry including sour powdery mildew" -> "Apply a fungicide specifically labeled for powdery mildew control on cherries as per the instructions on the label."
                        "cherry including sour healthy" -> "Prune the plant regularly to maintain good air circulation and prevent the buildup of pathogens."
                        "corn maize cercospora leaf spot gray leaf spot" -> "1.Rotate crops to reduce the buildup of pathogens in the soil.\n" +
                                "2.Apply a fungicide specifically labeled for cercospora and gray leaf spot control on maize as per the instructions on the label."
                        "corn maize common rust" -> "1.Rotate crops to reduce the buildup of pathogens in the soil.\n" +
                                "2.Apply a fungicide specifically labeled for cercospora and gray leaf spot control on maize as per the instructions on the label."
                        "Corn maize healthy" -> "1.Keep the plants well-watered and fertilized to promote healthy growth.\n" +
                                "2.Rotate crops to reduce the buildup of pathogens in the soil."
                        "corn maize northern leaf blight" -> "1.Rotate crops to reduce the buildup of pathogens in the soil.\n" +
                                "2.Apply a fungicide specifically labeled for northern leaf blight control on maize as per the instructions on the label."
                        "grape black rot" -> " This can be cure by removing all the infected plant parts, use fungicides, and practice good sanitation."
                        "grape esca black measles " -> "We can control this disease is to prune infected plant parts, avoid over-irrigation, use fungicides, and maintain good vineyard sanitation."
                        "grape leaf blight isariopsis leaf spot " -> "Cure by managing this disease is to prune the infected plant parts, use fungicides, and maintain good vineyard sanitation."
                        "grape healthy" -> "Your crop is healthy.Don't forget to use good vineyard sanitation."
                        "orange haunglongbing citrus greening " -> "Currently, there is no known cure for this disease, and management practices focus on removing infected plants, controlling insect vectors, and using tolerant varieties."
                        "peach bacterial spot" -> "1.Apply copper fungicides before the bud break in the spring.\n" +
                                "2.Apply copper-based fungicides after the petals have fallen.\n" +
                                "3.Apply fixed copper fungicides after harvest."
                        "peach healthy" -> "Your crop is healthy.Don't forget to water it daily."
                        "pepper bell bacterial spot" -> "1.Rotate the crops to prevent the buildup of the bacteria in the soil.\n" +
                                "2.Avoid overhead irrigation and wetting of leaves.\n" +
                                "3.Use copper-based fungicides to control the disease."
                        "pepper bell healthy" -> "Your crop is healthy.Don't forget to water it daily."
                        "potato early blight" -> "To prevent or treat early blight, it is important to maintain good plant hygiene and rotate crops regularly. "
                        "potato late blight" -> "To control late blight, it is important to use resistant potato varieties, maintain good plant hygiene, and apply fungicides regularly."
                        "potato healthy" -> "Your crop is healthy. Don't forget to maintain hygiene."
                        "raspberry healthy" -> "Your crop is healthy. Don't forget to maintain hygiene."
                        "soybean healthy" -> "Your crop is healthy. Don't forget to maintain hygiene."
                        "squash powdery mildew" -> "1.Apply fungicides containing potassium bicarbonate or neem oil.\n" +
                                "2.Remove infected leaves and dispose of them properly.\n" +
                                "3.Apply sulfur dust or wettable sulfur when the disease is in early stage."
                        "strawberry leaf scorch" -> "1.Apply nitrogen-rich fertilizers to promote plant health and immunity.\n" +
                                "2.Mulch around the plants to conserve soil moisture and prevent splashing of spores.\n" + "3.Apply copper fungicides like Bordeaux mixture or copper hydroxide."
                        "strawberry healthy" -> "Your crop is healthy. Don't forget to maintain hygiene."
                        "tomato bacterial spot" -> "1.Affected plants must be removed and destroyed to prevent the spread of the bacteria.\n" +"2.Copper-based fungicides can be used as a preventive measure."
                        "tomato early blight" -> "1.Use chlorothalonil or copper can be used as a preventive measure.\n" +"Infected leaves should be removed, and the plants should be watered at the base to prevent the spread of the disease."
                        "tomato late blight" -> "1.Infected plants should be removed and destroyed immediately.\n" +"2.Fungicides containing copper, chlorothalonil, or mancozeb can be used as a preventive measure."
                        "tomato leaf mold" -> "1.Use the fungicides containing chlorothalonil or copper can be used as a medicine.\n"+ "2.Infected leaves should be removed and destroyed, and the plants should be watered at the base."
                        "tomato septoria leaf spot" -> "1.Remove the infected leaves and destroy them immediately.\n" +
                                "2.Apply a fungicide containing chlorothalonil or copper hydroxide.\n" +
                                "3.Rotate crops, do not plant tomatoes or other susceptible crops in the same location year after year."
                        "tomato target spot" -> "1.Use a copper-based fungicide to help control the disease.\n" +
                                "2.Keep the soil under plants clean and remove any fallen leaves, fruits, or other debris from the area.\n" +
                                "3.Water the tomato plants early in the day, so the leaves have time to dry before nightfall."
                        "tomato tomato yellow leaf curl virus" -> "1.Use yellow sticky traps to control whiteflies, which are the main carrier of the virus.\n" +
                                "2.Remove infected plants and destroy them to prevent the spread of the virus.\n" +
                                "3.Use resistant tomato varieties."
                        "tomato tomato mosaic virus" -> "1.Remove infected plants and destroy them to prevent the spread of the virus.\n" +
                                "2.Use resistant tomato varieties.\n" +
                                "C3.ontrol aphids, which can transmit the virus."
                        "tomato healthy" -> "Your crop is healthy and provide adequate water, sunlight, and nutrients."
                        else -> "No treatment available, post on community"
                    }
                    message = "Disease: ${results?.title}\n"  +
                            "Confidence: ${results?.confidence}\n" +
                            "Treatment: $treatment"

                },
                modifier = Modifier.padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.Gray
                )
            )
            {
                Text(
                    text = "Detect Disease",
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            BottomSheet(
                state = simpleSheetState,
                skipPeeked = true,
                shape = RoundedCornerShape(topStart = 19.dp, topEnd = 19.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 30.dp)
                        .fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        Header(
                            text = "Disease Detected",
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = message,
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState())
                                .align(Alignment.CenterHorizontally),
                            fontSize = 22.sp,
                            lineHeight = 1.5.em
                        )
                    }
                }
            }
        }
    }
}


private fun loadImageFromAssets(assets: AssetManager): Bitmap {
    val mInputSize = 224
    val mSamplePath = "soybean.JPG"

    val inputStream = assets.open(mSamplePath)
    val bitmap = BitmapFactory.decodeStream(inputStream)

    return Bitmap.createScaledBitmap(bitmap, mInputSize, mInputSize, true)
}



private fun scaleImage(imageBitmap: Bitmap): Bitmap {
    val mInputSize = 224
    val width = imageBitmap.width
    val height = imageBitmap.height
    val scaleWidth = mInputSize.toFloat() / width
    val scaleHeight = mInputSize.toFloat() / height
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)
    return Bitmap.createBitmap(imageBitmap, 0,0, width, height, matrix, true)
}

@Composable
fun Header(text: String, modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 22.sp,
        color = Color.DarkGray,
    )
}



