package com.example.cropdisease.pages

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cropdisease.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*

data class ImageText(var documentId: String, val text: String, val imageUrl: Uri, val title: String, val answer : String)

class FirestoreImageTextViewModel : ViewModel() {

    private val firestoreDB = FirebaseFirestore.getInstance()
    val images: MutableList<ImageText> = mutableStateListOf()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            // Listen for changes in the "images" collection in Firestore
            firestoreDB.collection("images")
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        return@addSnapshotListener
                    }
                    val imagesList = snapshot?.documents?.mapNotNull { document ->
                        val title = document.getString("title") ?: return@mapNotNull null
                        val answer = document.getString("answer") ?: return@mapNotNull null
                        val text = document.getString("text") ?: return@mapNotNull null
                        val imageUrl = document.getString("imageUrl")?.toUri() ?: return@mapNotNull null
                        val documentId = document.id

                        ImageText(documentId,text, imageUrl,title,answer)
                    } ?: emptyList()
                    images.clear()
                    images.addAll(imagesList)
                }
        }
    }

    fun uploadImageText(imageUri: Uri, text: String, title: String,answer: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Upload image to Firebase Storage
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("images/${UUID.randomUUID()}")
            val uploadTask = imageRef.putFile(imageUri)
            val downloadUrl = uploadTask.await().storage.downloadUrl.await().toString()

            // Add text and image URL to Firestore database
            val firestoreDB = FirebaseFirestore.getInstance()
            val docRef = firestoreDB.collection("images").document()
            val imageText = ImageText(docRef.id, text, imageUri, title, answer)
            docRef.set(mapOf(
                "text" to text,
                "title" to title,
                "answer" to answer,
                "imageUrl" to downloadUrl
            )).await()
            images.add(imageText)
        }
    }

    fun updateAnswer(imageText: ImageText, answer: String) {
        val docRef = firestoreDB.collection("images").document(imageText.documentId)
        firestoreDB.runTransaction { transaction ->
            val snapshot = transaction.get(docRef)
            if (!snapshot.exists()) {
                throw Exception("ImageText with id ${imageText.documentId} does not exist")
            }
            transaction.update(docRef, "answer", answer)
        }
            .addOnFailureListener { exception ->
                Log.e("FirestoreImageTextViewModel", "Error loading images", exception)
            }
    }
    fun loadImages() {
        firestoreDB.collection("images")
            .get()
            .addOnSuccessListener { querySnapshot ->
                images.clear()
                images.addAll(querySnapshot.documents.mapNotNull { document ->
                    val title = document.getString("title") ?: return@mapNotNull null
                    val answer = document.getString("answer") ?: return@mapNotNull null
                    val text = document.getString("text") ?: return@mapNotNull null
                    val imageUrl = document.getString("imageUrl")?.toUri() ?: return@mapNotNull null
                    val id = document.id
                    ImageText(id, text, imageUrl,title,answer)
                })
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreImageTextViewModel", "Error loading images", exception)
            }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirestoreImageTextInput(viewModel: FirestoreImageTextViewModel,navController: NavController) {
    val context = LocalContext.current
    val imageUriState = remember { mutableStateOf<Uri?>(null) }
    val titleState = remember { mutableStateOf("") }
    val textState = remember { mutableStateOf("") }
    val answerState = remember { mutableStateOf("") }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUriState.value = uri
    }

    Column(modifier = Modifier
        .padding(0.dp)
        .verticalScroll(rememberScrollState())) {
        Text(
            text = stringResource(R.string.title),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            modifier = Modifier.padding(16.dp)
        )
        if (imageUriState.value != null) {
            Image(
                painter = rememberAsyncImagePainter(imageUriState.value),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp),
                contentScale = ContentScale.Crop
            )
        } else {
            OutlinedButton(
                onClick = { launcher.launch("image/*") },
                modifier = Modifier.padding(16.dp,0.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White)
            ){
                Text(
                    text = stringResource(R.string.buttonlab),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W500,
                )
            }
        }
        Spacer(modifier =Modifier.height(15.dp))
        Surface(modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSecondary) {
            Column(modifier = Modifier) {
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = stringResource(R.string.titlelabel),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp,0.dp),
                    fontWeight = FontWeight.W500

                )
                TextField(
                    value = titleState.value,
                    onValueChange = { titleState.value = it },
                    placeholder = { Text("Add a title indicating about the disease that you want to ask",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize =18.sp) },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent))
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        Spacer(modifier =Modifier.height(20.dp))
        Surface(modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSecondary) {
            Column(modifier = Modifier) {
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = stringResource(R.string.textlabel),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp,0.dp),
                    fontWeight = FontWeight.W500

                )
                TextField(
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    placeholder = { Text("Describe the qualities such as spots, colour and bugs...",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize =18.sp) },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent))
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        OutlinedButton(
            onClick = {
                val imageUri = imageUriState.value
                if (imageUri != null) {
                    viewModel.uploadImageText(imageUri, textState.value,titleState.value, answerState.value)
                    imageUriState.value = null
                    textState.value = ""
                    titleState.value = ""
                    Toast.makeText(context, "Image and text uploaded", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(15.dp),
            enabled = imageUriState.value != null && textState.value.isNotBlank() && titleState.value.isNotBlank(),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(red = 22, green = 160, blue = 133),
                containerColor = Color.White
            ),
            border = BorderStroke(1.dp,Color(red = 22, green = 160, blue = 133)),
        ) {
            Text("Upload")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageTextList(
    viewModel: FirestoreImageTextViewModel,
    navController: NavController,
) {
    val scrollState = rememberScrollState()

    Column {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
                LazyColumn(state = LazyListState(0)) {
                    items(viewModel.images) { imageText ->
                        ElevatedCard(
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier.padding(7.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.onSecondary,
                            ),
                            onClick = { navController.navigate(imageText.documentId) },
                            elevation = CardDefaults.elevatedCardElevation(1.dp)// Navigate to detail on click
                        ) {
                            // Card content
                            Image(
                                painter = rememberAsyncImagePainter(imageText.imageUrl),
                                contentDescription = null, // decorative
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(190.dp),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Text(
                                    text = imageText.title,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.W500,
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = imageText.text,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W400,
                                )
                            }
                        }
                    }
                }
            AdoptFab(
                extended = scrollState.value == 0,
                modifier = Modifier.align(Alignment.BottomEnd),
                navController = navController)
        }

    }
    LaunchedEffect(viewModel) {
        viewModel.loadImages()
    }
}




@Composable
fun AdoptFab(extended: Boolean, modifier: Modifier = Modifier,navController: NavController) {
    FloatingActionButton(
        onClick = { navController.navigate("edit") },
        modifier = modifier
            .padding(16.dp)
            .height(54.dp),
        containerColor = Color(0xFF00796A),
        contentColor = Color.White
    ) {
        AnimatingFabContent(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = stringResource(R.string.adopt_me)
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.adopt_me),
                )
            },
            extended = extended
        )
    }
}
