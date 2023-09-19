package com.example.cropdisease.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.cropdisease.R

@Composable
fun CustomDialog(
    onDismiss:()->Unit,
    onConfirm:()->Unit
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        OutlinedCard(
            shape = RoundedCornerShape(15.dp),
            border = BorderStroke(2.dp, Color.Transparent),
            modifier = Modifier
                .fillMaxWidth(0.93f)

        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ){

                Text(
                    text = "How is your experience with this app?",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(22.dp, 10.dp),
                    lineHeight = 33.sp
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        OutlinedIconButton(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(13.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = Color(red = 173, green = 216, blue = 230)
                                    ),
                            border = BorderStroke(2.dp, Color.White),
                            modifier = Modifier
                                .height(75.dp)
                                .width(75.dp)
                                .fillMaxSize()
                        ) {
                            Icon(painter = painterResource(id = R.drawable.sad),
                                contentDescription ="emoji",
                            modifier = Modifier.size(60.dp))

                        }
                        OutlinedIconButton(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(13.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color(red = 173, green = 216, blue = 230)
                            ),
                            border = BorderStroke(2.dp, Color.White),
                            modifier = Modifier
                                .height(75.dp)
                                .width(75.dp)
                                .fillMaxSize()
                        ) {
                            Icon(painter = painterResource(id = R.drawable.avg),
                                contentDescription ="paypal",
                                modifier = Modifier.size(60.dp))

                        }
                        OutlinedIconButton(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(13.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color(red = 173, green = 216, blue = 230)
                            ),
                            border = BorderStroke(2.dp, Color.White),
                            modifier = Modifier
                                .height(75.dp)
                                .width(75.dp)
                                .fillMaxSize()
                        ) {
                            Icon(painter = painterResource(id = R.drawable.happy),
                                contentDescription ="paypal",
                                modifier = Modifier.size(60.dp))
                          }
                    }

                }
                Column(
                    modifier = Modifier
                ){
                    OutlinedButton(
                        onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color(red = 255, green = 255, blue = 255),
                            containerColor = Color.LightGray
                        ),
                        border = BorderStroke(0.dp, Color.Transparent),
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(17.dp))
                    OutlinedButton(
                        onClick = {
                            onDismiss()            //change to onConfirm() at last
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Black,
                            containerColor = Color.Cyan
                        ),
                        border = BorderStroke(0.dp, Color.Transparent),
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "Confirm",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                }

            }
        }
    }
}
