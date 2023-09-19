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
fun Mango(navController: NavHostController) {
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
                    text = "Mango",
                    fontSize = 33.sp,
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(0.9f))
                Icon(
                    painter = painterResource(id = R.drawable.mango),
                    contentDescription = "",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Unspecified
                )
            }
            Field2()
        }
    }
}

@Composable
fun Field2()
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
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(10.dp)
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
            SeedStage2()
            VegStage2()
            FlowStage2()
            FruitStage2()
            SamCard2()
        }
    }
}


@Composable
fun SeedStage2()
{

    Column(
        modifier = Modifier
    ) {
        Text(
            text = "Diseases by Stage",
            fontSize = 21.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "All the pests and diseases that might appear in your crop at different stages.",
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(10.dp)
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
            items(MangoViewItems.subList(0,2)) { MangoItem ->
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
                        painter = painterResource(id = MangoItem.image),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier.padding(18.dp)
                    ) {
                        Text(text = MangoItem.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(Modifier.height(0.dp))
                        Text(
                            text = MangoItem.name,
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
                    shape = RoundedCornerShape(topStart = 19.dp, topEnd = 19.dp)
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
                        Box(Modifier.padding(20.dp)){
                            Divider(color = Color.LightGray, thickness = 1.dp)
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = MangoItem.description,
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState()),
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
fun VegStage2()
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
            items(MangoViewItems.subList(2,4)) { MangoItem ->
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
                        painter = painterResource(id = MangoItem.image),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier.padding(18.dp)
                    ) {
                        Text(text = MangoItem.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(Modifier.height(0.dp))
                        Text(
                            text = MangoItem.name,
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
                    shape = RoundedCornerShape(topStart = 19.dp, topEnd = 19.dp)
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
                        Box(Modifier.padding(20.dp)){
                            Divider(color = Color.LightGray, thickness = 1.dp)
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = MangoItem.description,
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState()),
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
fun FlowStage2()
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
            items(MangoViewItems.subList(4,6)) { MangoItem ->
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
                        painter = painterResource(id = MangoItem.image),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier.padding(18.dp)
                    ) {
                        Text(text = MangoItem.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(Modifier.height(0.dp))
                        Text(
                            text = MangoItem.name,
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
                    shape = RoundedCornerShape(topStart = 19.dp, topEnd = 19.dp)
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
                        Box(Modifier.padding(20.dp)){
                            Divider(color = Color.LightGray, thickness = 1.dp)
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = MangoItem.description,
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState()),
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
fun FruitStage2()
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
            items(MangoViewItems.subList(6,10)) { MangoItem ->
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
                        painter = painterResource(id = MangoItem.image),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier.padding(18.dp)
                    ) {
                        Text(text = MangoItem.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(Modifier.height(0.dp))
                        Text(
                            text = MangoItem.name,
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
                    shape = RoundedCornerShape(topStart = 19.dp, topEnd = 19.dp)
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
                            color = MaterialTheme.colorScheme.primary
                        )
                        Box(Modifier.padding(20.dp)){
                            Divider(color = Color.LightGray, thickness = 1.dp)
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = MangoItem.description,
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState()),
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
fun SamCard2()
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
                        .height(50.dp)
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

data class MangoItem(val id: Int, val title: String, val image: Int,val name: String,val description : String)


var MangoViewItems : List<MangoItem> = listOf(
    MangoItem(1, "Fungus", R.drawable.damp,"Damping-off of seedling","1.Plan a balanced fertilization with split nitrogen applications.\n" +
            "2.Water regularly but superficially.Water early in the morning so that the soil surface is dry by evening.\n" +
            "3.Adopt the ring method of irrigation so that the water does not come in direct contact with the stem.\n" +
            "4.Take care not to inadvertently transport mud from one field to another."),
    MangoItem(2, "Bacteria", R.drawable.m2,"Bacterial black spot of mango","1.Use resistant varieties if available.\n" +
            "2.Ensure good ventilation of the trees.\n" +
            "3.Regularly remove infected twigs, branches and fruits.\n" +
            "4.Avoid mechanical damage to the mango trees during field work.\n" +
            "5.Protect them from strong winds and heavy rains with windbreaks."),
    MangoItem(3, "Fungus", R.drawable.m3,"Mango Malformation","1.Spraying with trace elements of Zinc, . Boron, and copper before bloom and after fruit harvesting has proved to control or minimize the incidence of malformation.\n" +
            "2.Increasing nitrogen doses reduced panicle malformation in a case study.\n" +
            "3.Good hygiene management of the orchard and tools is necessary to avoid the fungus to spread.\n" +
            "4.Clean your pruning equipment thoroughly to reduce the spread of the disease."),
    MangoItem(4, "Insect", R.drawable.m4,"Thrips","1.Use virus- and thrips-free transplants from certified greenhouses and plant nurseries.\n" +
            "2.Avoid planting susceptible plants next to weedy areas and alternative hosts.\n" +
            "3.Add plastic or organic mulch along the rows to reduce the incidence and progression of thrips."),
    MangoItem(5, "Fungus", R.drawable.m5,"Sooty Mold","1.Make sure to provide enough distance between the plants or trees and sufficient sunlight.\n" +
            "2.Build physical barriers around trees or plants to prevent ants and plant sap-sucking insects from reaching them.\n" +
            "3.Fertilize and water the trees sufficiently to ensure an optimal natural resistance of the trees to phloem-feeding parasites."),
    MangoItem(6, "Bacteria", R.drawable.m2,"Bacterial black spot of mango","1.Use resistant varieties if available.\n" +
            "2.Ensure good ventilation of the trees.\n" +
            "3.Regularly remove infected twigs, branches and fruits.\n" +
            "4.Avoid mechanical damage to the mango trees during field work.\n" +
            "5.Protect them from strong winds and heavy rains with windbreaks."),
    MangoItem(7, "Fungus", R.drawable.m7,"Fruit Rot","1.Plant non-host trees such as citrus or coffee in or around the field.\n" +
            "2.Prune trees yearly to enhance ventilation.\n" +
            "3.Remove fallen fruits, branches and leaves from the field.\n" +
            "4.Keep the field clear of weeds and alternative hosts.\n" +
            "5.Store fruits in a well-ventilated environment."),
    MangoItem(8, "Insect", R.drawable.m8,"MealyBug","1.Eradicate weeds in and around the field.\n" +
            "2.Do not grow other susceptible crops in the area.\n" +
            "3.Take great care not to spread the mealybugs during fieldwork.\n" +
            "4.Encourage the population of predators with good field practices, for example with the use of insecticide specific for mealybugs."),
    MangoItem(9, "Insect", R.drawable.m9,"Mango Fruit Fly","1.Plant early-maturing varieties to make sure the fruits ripen when fly populations are low.\n" +
            "2.Pick infested or dropped fruits every day.\n" +
            "3.Install protein bait traps to monitor possible fly invasions.\n" +
            "4.Do not grow alternative host plants such as citrus, guava, papaya, melons, etc nearby.\n" +
            "5.Weed and till carefully around the trees to detect fallen fruits under residues."),
    MangoItem(10, "Fungus", R.drawable.m3,"Mango Malformation","1.Spraying with trace elements of Zinc, . Boron, and copper before bloom and after fruit harvesting has proved to control or minimize the incidence of malformation.\n" +
            "2.Increasing nitrogen doses reduced panicle malformation in a case study.\n" +
            "3.Good hygiene management of the orchard and tools is necessary to avoid the fungus to spread.\n" +
            "4.Clean your pruning equipment thoroughly to reduce the spread of the disease."),
)