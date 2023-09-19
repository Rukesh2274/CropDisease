package com.example.cropdisease.pages
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cropdisease.R
import com.example.cropdisease.data.SessionViewModel

@Composable
fun Profile(navController: NavController, sessionViewModel: SessionViewModel) {
    val isLoggedIn = sessionViewModel.isLoggedIn()
    val buttonText = if (isLoggedIn) "Sign Out" else "Sign In"
    val buttonText1 = if (isLoggedIn) "Explore the community and get more information" else "Join with us to explore in the community"
    val buttonText2 = if (isLoggedIn) "WELCOME" else "YOUR ACCOUNT"
    val buttonColor = Color(red = 22, green = 160, blue = 133)
    val buttonBorderColor = Color(red = 22, green = 160, blue = 133)
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(3.dp))
        Surface(
            shape = RoundedCornerShape(1.dp),
            color = Color(0xFFF7F7F7),
            modifier = Modifier
                .height(200.dp)
                .padding(5.dp),
            shadowElevation = 4.dp
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.profile1),
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
                Column(
                    modifier = Modifier.padding(8.dp)
                        .fillMaxSize()
                        .weight(2f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = buttonText2,
                        fontSize = 22.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = buttonText1,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        OutlinedButton(
                            modifier = Modifier
                                .height(50.dp)
                                .width(200.dp),
                            shape = RoundedCornerShape(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = buttonColor,
                                containerColor = Color.White
                            ),
                            border = BorderStroke(1.dp, buttonBorderColor),
                            onClick = {
                                if (isLoggedIn) {
                                    sessionViewModel.logoutUser()
                                } else {
                                    navController.navigate("login")
                                }
                            }
                        ) {
                            Text(
                                text = buttonText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        CardSimple()
        Spacer(modifier = Modifier.height(6.dp))
        CardSimple1()
    }
}

@Composable
fun CardSimple() {
        ElevatedCard(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .height(205.dp)
                .padding(6.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondary,
            ),
        ) {
            Row {
                Column(modifier = Modifier.padding(8.dp, 55.dp)) {
                    Image(
                        modifier = Modifier.size(85.dp), // use CircleShape for circular shape ,
                        painter = painterResource(id = R.drawable.shar),
                        contentScale = ContentScale.Crop,
                        contentDescription = null)
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(5.dp, 30.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Grow Smarter!",
                                fontSize = 21.sp,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.W600,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Share this and help others to solve their problems",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        val context = LocalContext.current
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        OutlinedButton(
                            modifier = Modifier
                                .height(50.dp)
                                .width(155.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color(red = 22, green = 160, blue = 133),
                                containerColor = Color.Transparent
                            ),
                            border = BorderStroke(0.dp, Color.Transparent),
                            onClick = {
                                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_SUBJECT, "Check out this app!")
                                    putExtra(
                                        Intent.EXTRA_TEXT,
                                        "Download this awesome app at: https://myapp.com"
                                    )
                                }
                                context.startActivity(
                                    Intent.createChooser(
                                        shareIntent,
                                        "Share this app"
                                    )
                                )
                            }
                        ) {
                            Text(
                                text = "SHARE APP",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
}

@Composable
fun CardSimple1() {
    ElevatedCard(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(218.dp)
            .padding(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
        ),
    ) {
        Row {
            Column(modifier = Modifier.padding(10.dp, 75.dp)) {
                Image(
                    modifier = Modifier.size(65.dp), // use CircleShape for circular shape ,
                    painter = painterResource(id = R.drawable.forum),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(5.dp, 30.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "How is your experience with this app?",
                            fontSize = 19.sp,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.W600,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "We'd love to know your thoughts and suggestions",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyLarge
                )
                val context = LocalContext.current
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        modifier = Modifier
                            .height(50.dp)
                            .width(179.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color(red = 22, green = 160, blue = 133),
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(0.dp, Color.Transparent),
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW)
                            val url = "https://forms.gle/NEhNFWWxbiRjbQL59"
                            intent.data = Uri.parse(url)
                            context.startActivity(intent)
                        }
                    ) {
                        Text(
                            text = "GIVE FEEDBACK",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

