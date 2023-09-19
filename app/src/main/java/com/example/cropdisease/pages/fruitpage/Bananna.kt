package com.example.cropdisease.pages.fruitpage
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dokar.sheets.BottomSheet
import com.dokar.sheets.rememberBottomSheetState
import com.example.cropdisease.R
import kotlinx.coroutines.launch

@Composable
fun Bananna(navController: NavHostController) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(19.dp,5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Bananna",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(0.9f))
                Icon(
                    painter = painterResource(id = R.drawable.bananas),
                    contentDescription = "bannana",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Unspecified
                )
            }
            Field1()
        }
    }
}

@Composable
fun Field1()
{
    Surface(
        shape = RoundedCornerShape(1.dp),
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier
            .height(2600.dp))
    {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = "Monitor your field",
                fontSize = 21.sp,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(10.dp)
            )

            Text(
                text = "We should be careful while applying the pests and fertilizers.",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(10.dp,5.dp)
            )
            OutlinedCard(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(red = 255, green = 219, blue = 170)
                ),
                border = BorderStroke(2.dp, Color.White),
                modifier = Modifier
                    .height(350.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "Field Monitoring",
                        fontSize = 25.sp,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier.padding(7.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Diagnose your crop to control the pests and fertilizers",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    Image(
                        painter = painterResource(R.drawable.exp),
                        contentDescription = null, // decorative
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            SeedStage1()
            VegStage1()
            FlowStage1()
            FruitStage1()
            SamCard1()
        }
    }
}


@Composable
fun SeedStage1()
{

    Column(
        modifier = Modifier
    ) {
        Text(
            text = "Diseases by Stage",
            fontSize = 21.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(10.dp,18.dp)
        )
        Text(
            text = "All the pests and diseases that might appear in your crop at different stages.",
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(10.dp,5.dp)
        )
        Text(
            text = "Seedling Stage",
            fontSize = 23.sp,
            fontWeight = FontWeight.Black,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(10.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(BanannaViewItems.subList(0,2)) { BanannaItem ->
                val scope = rememberCoroutineScope()
                val simpleSheetState = rememberBottomSheetState()
                OutlinedCard(

                    //shape = MaterialTheme.shapes.medium,
                    shape = RoundedCornerShape(15.dp),
                    // modifier = modifier.size(270.dp, 240.dp)
                    modifier = Modifier
                        .padding(2.dp)
                        .height(260.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { scope.launch { simpleSheetState.expand() } }),
                    //set card elevation of the card
                    border = BorderStroke(1.dp, Color(red = 255, green = 255, blue = 255)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                ) {
                    Image(
                        painter = painterResource(id = BanannaItem.image),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier.padding(18.dp)
                    ) {
                        Text(text = BanannaItem.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(Modifier.height(0.dp))
                        Text(
                            text = BanannaItem.name,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleLarge,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                BottomSheet(
                    state = simpleSheetState,
                    skipPeeked = true,
                    shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 30.dp)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "Preventive Measures :",
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState())
                                .align(Alignment.Start),
                            fontSize = 25.sp,
                            lineHeight = 1.5.em,
                            color = Color.DarkGray
                        )
                        Box(Modifier.padding(10.dp,20.dp)){
                            Divider(color = Color.LightGray, thickness = 1.dp)
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = BanannaItem.description,
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState()),
                            textAlign = TextAlign.Justify,
                            fontSize = 17.sp,
                            lineHeight = 1.5.em,

                            )
                    }
                }
            }
        }
    }
}

@Composable
fun VegStage1()
{
    Box(Modifier.padding(20.dp)){
        Divider(color = Color.LightGray, thickness = 1.dp)
    }

    Column(
        modifier = Modifier
    ) {

        Text(
            text = "Vegetative Stage",
            fontSize = 23.sp,
            fontWeight = FontWeight.Black,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(10.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(BanannaViewItems.subList(2,4)) { BanannaItem ->
                val scope = rememberCoroutineScope()
                val simpleSheetState = rememberBottomSheetState()
                OutlinedCard(

                    //shape = MaterialTheme.shapes.medium,
                    shape = RoundedCornerShape(15.dp),
                    // modifier = modifier.size(270.dp, 240.dp)
                    modifier = Modifier
                        .padding(2.dp)
                        .height(260.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { scope.launch { simpleSheetState.expand() } }),
                    //set card elevation of the card
                    border = BorderStroke(1.dp, Color(red = 255, green = 255, blue = 255)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                ) {
                    Image(
                        painter = painterResource(id = BanannaItem.image),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier.padding(18.dp)
                    ) {
                        Text(text = BanannaItem.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(Modifier.height(0.dp))
                        Text(
                            text = BanannaItem.name,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleLarge,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                BottomSheet(
                    state = simpleSheetState,
                    skipPeeked = true,
                    shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 30.dp)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "Preventive Measures :",
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState())
                                .align(Alignment.Start),
                            fontSize = 25.sp,
                            lineHeight = 1.5.em,
                            color = Color.DarkGray
                        )
                        Box(Modifier.padding(10.dp,20.dp)){
                            Divider(color = Color.LightGray, thickness = 1.dp)
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = BanannaItem.description,
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState()),
                            textAlign = TextAlign.Justify,
                            fontSize = 17.sp,
                            lineHeight = 1.5.em,

                            )
                    }
                }
            }
        }
    }
}

@Composable
fun FlowStage1()
{
    Box(Modifier.padding(20.dp)){
        Divider(color = Color.LightGray, thickness = 1.dp)
    }

    Column(
        modifier = Modifier
    ) {
        Text(
            text = "Flowering Stage",
            fontSize = 23.sp,
            fontWeight = FontWeight.Black,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(10.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(BanannaViewItems.subList(4,6)) { BanannaItem ->
                val scope = rememberCoroutineScope()
                val simpleSheetState = rememberBottomSheetState()
                OutlinedCard(

                    //shape = MaterialTheme.shapes.medium,
                    shape = RoundedCornerShape(15.dp),
                    // modifier = modifier.size(270.dp, 240.dp)
                    modifier = Modifier
                        .padding(2.dp)
                        .height(260.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { scope.launch { simpleSheetState.expand() } }),
                    //set card elevation of the card
                    border = BorderStroke(1.dp, Color(red = 255, green = 255, blue = 255)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                ) {
                    Image(
                        painter = painterResource(id = BanannaItem.image),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier.padding(18.dp)
                    ) {
                        Text(text = BanannaItem.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(Modifier.height(0.dp))
                        Text(
                            text = BanannaItem.name,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleLarge,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                BottomSheet(
                    state = simpleSheetState,
                    skipPeeked = true,
                    shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 30.dp)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "Preventive Measures :",
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState())
                                .align(Alignment.Start),
                            fontSize = 25.sp,
                            lineHeight = 1.5.em,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Box(Modifier.padding(10.dp,20.dp)){
                            Divider(color = Color.LightGray, thickness = 1.dp)
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = BanannaItem.description,
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState()),
                            textAlign = TextAlign.Justify,
                            fontSize = 17.sp,
                            lineHeight = 1.5.em,

                            )
                    }
                }
            }
        }
    }
}

@Composable
fun FruitStage1()
{
    Box(Modifier.padding(20.dp)){
        Divider(color = Color.LightGray, thickness = 1.dp)
    }

    Column(
        modifier = Modifier
    ) {
        Text(
            text = "Fruiting Stage",
            fontSize = 23.sp,
            fontWeight = FontWeight.Black,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(10.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(BanannaViewItems.subList(6,10)) { BanannaItem ->
                val scope = rememberCoroutineScope()
                val simpleSheetState = rememberBottomSheetState()
                OutlinedCard(

                    //shape = MaterialTheme.shapes.medium,
                    shape = RoundedCornerShape(15.dp),
                    // modifier = modifier.size(270.dp, 240.dp)
                    modifier = Modifier
                        .padding(2.dp)
                        .height(260.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { scope.launch { simpleSheetState.expand() } }),
                    //set card elevation of the card
                    border = BorderStroke(1.dp, Color(red = 255, green = 255, blue = 255)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                ) {
                    Image(
                        painter = painterResource(id = BanannaItem.image),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier.padding(18.dp)
                    ) {
                        Text(text = BanannaItem.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(Modifier.height(0.dp))
                        Text(
                            text = BanannaItem.name,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleLarge,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                BottomSheet(
                    state = simpleSheetState,
                    skipPeeked = true,
                    shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 30.dp)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "Preventive Measures :",
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState())
                                .align(Alignment.Start),
                            fontSize = 20.sp,
                            lineHeight = 1.5.em,
                            color = Color.DarkGray
                        )
                        Box(Modifier.padding(10.dp,20.dp)){
                            Divider(color = Color.LightGray, thickness = 1.dp)
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = BanannaItem.description,
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState()),
                            textAlign = TextAlign.Justify,
                            fontSize = 17.sp,
                            lineHeight = 1.5.em,

                            )
                    }
                }
            }
        }
    }
}

@Composable
fun SamCard1()
{
    Spacer(Modifier.height(35.dp))
    OutlinedCard(
        //shape = MaterialTheme.shapes.medium,
        shape = RoundedCornerShape(10.dp),
        // modifier = modifier.size(270.dp, 240.dp)
        modifier = Modifier,
        //set card elevation of the card
        border = BorderStroke(1.dp, Color(red = 255, green = 255, blue = 255)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        Column(modifier = Modifier.clickable(onClick = { })) {

            Row(modifier = Modifier.padding(10.dp)) {
                Icon(painter = painterResource(id = R.drawable.leaf), contentDescription = null,modifier = Modifier.padding(10.dp))
                Text(
                    text = "Crop diagnosis",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W500,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Text(
                text = "Identify the crop's issue in a very few seconds",
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = "Quickly identify the problem with your crop.",
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(10.dp)
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .height(60.dp)
                        .width(300.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(red = 72, green = 113, blue = 247)
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Diagnosis",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.titleLarge,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

data class BanannaItem(val id: Int, val title: String, val image: Int,val name: String,val description : String)


var BanannaViewItems : List<BanannaItem> = listOf(
    BanannaItem(1, "Insect", R.drawable.aphids,"Aphids","1.Maintain a high number of different varieties of plants around fields.\n" +
            "2. Use reflective hulches to repel invading populations of aphids.\n" +
            "3. Monitor fields regularly to assess the incidence of a disease or pest and determine their severity.\n" +
            "4. Remove infected plant parts.\n" +
            "5. Check weeds in and around the fields\n" +
            "6. Prune the branches of your trees or remove the bottom leaves of your plants to favor the ventilation of the canopy.\n" +
            "7. If possible, use nets to protect the plants."),
    BanannaItem(2, "Fungus", R.drawable.yellow,"Yellow and Black Sigatoka","1.Avoid hard soils such as heavy clay soils and high soil moisture through good drainage.\n2.Plant in locations exposed to the morning sun or prevailing winds to keep the leaves as dry as possible.\n" +
            "3.Leave sufficient space between plants to ensure good ventilation\n4.Ensure a balanced nutrition of the plants and use fertilizers high in potassium to minimize the infestation." +
            "5.Apply urea as nitrogen source to compromise fungal growth on soil."),
    BanannaItem(3, "Bacteria", R.drawable.bacter,"Bacterial soft rot of bananna","1.Use healthy planting material from reliable sources or from healthy root stocks.\n2.Regularly check your plants or fields for any sign of the disease.\n3.When the foliage is wet do no work in the fields or fertilize.\n" +
            "4. Harvest during dry weather conditions."),
    BanannaItem(4, "Fungus", R.drawable.pananama,"Panama disease","1.Rotate with sugarcane, rice or sunflower to reduce the incidence.\n2.Disinfect tools, equipment and farm machinery using sodium hypochlorite bleach.\n" +
            "3.Beware of inadvertently transporting soil from infected areas to clean areas."),
    BanannaItem(5, "Bacteria", R.drawable.blackleaf,"Black leaf spot of banana","1.Use proper spacing in order to avoid mutual shading and contact of leaves.\n2.Make sure that new plantations are an appropriate distance from diseased plantations.\n" +
            "3.Practice field sanitation by removing old dried hanging leaves.\n4.Removed and destroy affected leaf portions and fruits."),
    BanannaItem(6, "Insect", R.drawable.pseudo,"Pseudostem Weevil", "1.Take care to use clean planting material from certified sources.\n2.Use stems split longitudinally placed on the ground as pseudo-traps.\n" +
            "3.These cut pieces attract adult female weevils to feed and lay their eggs so use fertilizers that kills them."),
    BanannaItem(7, "Fungus", R.drawable.anth,"Anthracnose of Bananna","1.Avoid damage to the banana tissue during harvest,packaging and storage.\n2.Use plastic sleeves after bunch emergence to protect them from contamination.\n" +
            "3.Clean processing stations and storage facilities to prevent post-harvest contamination.\n4.Remove decaying leaves and remaining floral parts."),
    BanannaItem(8, "Insect", R.drawable.aphids,"Aphids","1.Maintain a high number of different varieties of plants around fields.\n" +
            "2. Use reflective hulches to repel invading populations of aphids.\n" +
            "3. Monitor fields regularly to assess the incidence of a disease or pest and determine their severity.\n" +
            "4. Remove infected plant parts.\n" +
            "5. Check weeds in and around the fields\n" +
            "6. Prune the branches of your trees or remove the bottom leaves of your plants to favor the ventilation of the canopy.\n" +
            "7. If possible, use nets to protect the plants."),
    BanannaItem(9, "Insect", R.drawable.moth,"Banana Moth","1.Use healthy seeds and planting material.\n2.Remove and destroy dried out plant material as it can be a source of infestation.\n" +
            "3.Ensure careful handling during cultivation and avoid mechanical injuries to the planks."),
    BanannaItem(10, "Bacteria", R.drawable.blackleaf,"Black leaf spot of banana","1.Use proper spacing in order to avoid mutual shading and contact of leaves.\n2.Make sure that new plantations are an appropriate distance from diseased plantations.\n" +
            "3.Practice field sanitation by removing old dried hanging leaves.\n4.Removed and destroy affected leaf portions and fruits.")
)