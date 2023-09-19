@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.cropdisease.pages.login

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.cropdisease.data.Constant.ServerClient
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cropdisease.R
import com.example.cropdisease.data.SessionViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@Composable
fun Loginpage(navController: NavHostController, viewModel: SignInViewModel = hiltViewModel(),
              sessionViewModel: SessionViewModel
) {
    val googleSignInState = viewModel.googleState.value
    val isLoggedIn = remember { mutableStateOf(sessionViewModel.isLoggedIn()) }
    if (isLoggedIn.value) {
        navController.navigate("Home")
        return
    }
    // Check if user is logged in based on shared preferences
    LaunchedEffect(key1 = "checkLoggedInState") {
        isLoggedIn.value = sessionViewModel.isLoggedIn()
    }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                GoogleAuthProvider.getCredential(result.idToken, null)
            } catch (it: ApiException) {
                print(it)
            }
        }

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)

    Scaffold {
        Box(modifier = Modifier.padding(it))
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "App Logo",
                modifier = Modifier
                    .weight(1f)
                    .size(200.dp),
                colorFilter = ColorFilter.tint(Color.DarkGray)
            )
            Surface(
                shape = RoundedCornerShape(30.dp),
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .weight(3.5f)
                    .padding(10.dp),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(22.dp)
                ) {
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = " Welcome Back",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        modifier = Modifier
                            .padding()
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    )
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text(text = "E-mail", color = Color.Black) },
                            placeholder = { Text(text = "Enter your mail") },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                        )

                        Spacer(modifier = Modifier.height(9.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password", color = Color.Black) },
                            placeholder = { Text(text = "Enter your password") },
                            singleLine = true,
                            visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                                    Icon(
                                        imageVector = if (passwordVisibility.value) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                        contentDescription = if (passwordVisibility.value) "Hide password" else "Show password"
                                    )
                                }
                            }
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        OutlinedButton(
                            onClick = {
                                scope.launch {
                                    viewModel.loginUser(email, password)


                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .height(50.dp)
                        ) {
                                Text(text = "LOGIN", fontWeight = FontWeight.W600, fontSize = 20.sp, color = Color.Black)
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically) {
                            if (state.value?.isLoading == true) {
                                CircularProgressIndicator()
                            }
                        }
                        Text(text = "New User?", fontSize = 14.sp)
                        TextButton(onClick = { navController.navigate("signup") })
                        {
                            Text(text = "Register", fontWeight = FontWeight.W600, fontSize = 16.sp, color = Color.Black)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            OutlinedIconButton(
                                shape = RoundedCornerShape(6.dp),
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = Color.Transparent),
                                border = BorderStroke(0.dp, Color.Transparent),
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(150.dp)
                                    .fillMaxSize(),
                                onClick = { val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                        .requestEmail()
                                        .requestIdToken(ServerClient)
                                        .build()
                                val googleSingInClient = GoogleSignIn.getClient(context, gso)
                                launcher.launch(googleSingInClient.signInIntent) }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.google),
                                    contentDescription = "Google",
                                    modifier = Modifier.size(50.dp),
                                    tint = Color.Unspecified
                                )
                            }
                            OutlinedIconButton(shape = RoundedCornerShape(6.dp),
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = Color.Transparent),
                                border = BorderStroke(0.dp, Color.Transparent),
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(150.dp)
                                    .fillMaxSize(),
                                onClick = {})
                            {
                                Icon(
                                    painter = painterResource(id = R.drawable.facebook),
                                    contentDescription = "Face",
                                    modifier = Modifier.size(50.dp),
                                    tint = Color.Unspecified)
                            }
                            LaunchedEffect(key1 = state.value?.isSuccess) {
                                scope.launch {
                                    if (state.value?.isSuccess?.isNotEmpty() == true) {

                                        val success = state.value?.isSuccess
                                        Toast.makeText(context, "$success", Toast.LENGTH_LONG).show()
                                        navController.navigate("Home")
                                        sessionViewModel.setLoggedIn(true)
                                    }
                                }
                            }
                            LaunchedEffect(key1 = state.value?.isError) {
                                scope.launch {
                                    if (state.value?.isError?.isNotEmpty() == true) {
                                        val error = state.value?.isError
                                        Toast.makeText(context, "$error", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }

                            LaunchedEffect(key1 = googleSignInState.success) {
                                scope.launch {
                                    if (googleSignInState.success != null) {
                                        Toast.makeText(context, "Sign In Success", Toast.LENGTH_LONG).show()

                                    }
                                }
                            }

                        }
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                if (googleSignInState.loading){
                    CircularProgressIndicator()
                }
            }
        }
    }
}
