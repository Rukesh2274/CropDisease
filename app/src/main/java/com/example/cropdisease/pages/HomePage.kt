package com.example.cropdisease.pages
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cropdisease.R

@Composable
fun HomePage(navController: NavHostController) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())) {
        Column(modifier = Modifier) {
            Horz(navController)
            Text(
                text = "Heal your crop1",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(15.dp)
            )
            ElevatedCard(
                //shape = MaterialTheme.shapes.medium,
                shape = RoundedCornerShape(8.dp),

                modifier = Modifier
                    .padding(10.dp,0.dp)
                    .height(230.dp)
                    .fillMaxWidth()
                    .clickable(onClick = { navController.navigate("detectdisease")}),
                //set card elevation of the card
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF7F7F7),
                ),
            ) {
                Column(
                    modifier = Modifier.padding(50.dp,10.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.first),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.weight(2f)
                            .size(300.dp)
                    )
                    OutlinedButton(
                        modifier = Modifier
                            .height(50.dp)
                            .width(250.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color(red = 72, green = 113, blue = 247)
                        ),
                        onClick = { navController.navigate("detectdisease") }
                    ) {
                        Text(
                            text = "Take a picture",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.titleLarge,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            TotalCrop(navController)
        }
    }
}


@Composable
fun Horz(navController: NavHostController)
{
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = "Explore",
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.titleLarge,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(15.dp,10.dp)
        )
        TextButton(onClick = { navController.navigate("allplants") },
            colors = ButtonDefaults. buttonColors (
                containerColor = Color.Transparent,
                contentColor = Color.Gray),
            modifier = Modifier.padding(10.dp,0.dp))
        {
            Text(
                text = "view all",
                textAlign = TextAlign.End,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
    Column(modifier = Modifier
        .horizontalScroll(rememberScrollState())
        .padding(8.dp, 0.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(11.dp)
            ) {

                OutlinedIconButton(
                    onClick = {navController.navigate("bananna")},
                    shape = RoundedCornerShape(50.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier
                        .height(65.dp)
                        .width(65.dp)
                        .fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bananas),
                        contentDescription = "bananna",
                        modifier = Modifier.size(43.dp),
                        tint = Color.Unspecified
                    )
                }
                OutlinedIconButton(
                    onClick = { navController.navigate("apple") },
                    shape = RoundedCornerShape(50.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier
                        .height(65.dp)
                        .width(65.dp)
                        .fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.apple),
                        contentDescription = "apple",
                        modifier = Modifier.size(37.dp),
                        tint = Color.Unspecified
                    )
                }
                OutlinedIconButton(
                    onClick = { navController.navigate("mango") },
                    shape = RoundedCornerShape(50.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier
                        .height(65.dp)
                        .width(65.dp)
                        .fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.mango),
                        contentDescription = "mango",
                        modifier = Modifier.size(37.dp),
                        tint = Color.Unspecified
                    )
                }
                OutlinedIconButton(
                    onClick = { navController.navigate("orange")},
                    shape = RoundedCornerShape(50.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier
                        .height(65.dp)
                        .width(65.dp)
                        .fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.orange),
                        contentDescription = "orange",
                        modifier = Modifier.size(33.dp),
                        tint = Color.Unspecified
                    )
                }
                OutlinedIconButton(
                    onClick = { navController.navigate("lemon") },
                    shape = RoundedCornerShape(50.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier
                        .height(65.dp)
                        .width(65.dp)
                        .fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.lemon),
                        contentDescription = "lemon",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                }

                OutlinedIconButton(
                    onClick = { navController.navigate("tomato") },
                    shape = RoundedCornerShape(50.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier
                        .height(65.dp)
                        .width(65.dp)
                        .fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.tomato_icon),
                        contentDescription = "tomato",
                        modifier = Modifier.size(32.dp),
                        tint = Color.Unspecified
                    )
                }
                OutlinedIconButton(
                    onClick = { navController.navigate("wheat") },
                    shape = RoundedCornerShape(50.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier
                        .height(65.dp)
                        .width(65.dp)
                        .fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.wheat),
                        contentDescription = "wheat",
                        modifier = Modifier.size(33.dp),
                        tint = Color.Unspecified
                    )
                }
            }

        }
    }
}

@Composable
fun TotalCrop(navController: NavHostController)
{
    Column(modifier = Modifier) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Need Help",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.titleLarge,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(15.dp)
        )
        OutlinedCard(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(8.dp),
            // modifier = modifier.size(265.dp, 240.dp)
            modifier = Modifier.padding(10.dp,0.dp),
            //set card elevation of the card
            border = BorderStroke(1.dp, Color(red = 255, green = 255, blue = 255)),
        ) {
            Column(modifier = Modifier.clickable(onClick = { navController.navigate("edit")})) {

                Image(
                    painter = painterResource(R.drawable.exp),
                    contentDescription = null, // decorative
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
        }
    }
}