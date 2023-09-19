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
fun Corn(navController: NavHostController) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Corn",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(0.9f))
                Icon(
                    painter = painterResource(id = R.drawable.corn),
                    contentDescription = "corn",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Unspecified
                )
            }
            Field6()
        }
    }
}

@Composable
fun Field6()
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
                        painter = painterResource(R.drawable.pot),
                        contentDescription = null, // decorative
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            SeedStage6()
            VegStage6()
            FlowStage6()
            FruitStage6()
            SamCard6()
        }
    }
}


@Composable
fun SeedStage6()
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
            items(CornViewItems.subList(0,2)) { CornItem ->
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
                        painter = painterResource(id = CornItem.image),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier.padding(18.dp)
                    ) {
                        Text(text = CornItem.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(Modifier.height(0.dp))
                        Text(
                            text = CornItem.name,
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
                            text = CornItem.description,
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
fun VegStage6()
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
            items(CornViewItems.subList(2,4)) { CornItem ->
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
                        painter = painterResource(id = CornItem.image),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier.padding(18.dp)
                    ) {
                        Text(text = CornItem.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(Modifier.height(0.dp))
                        Text(
                            text = CornItem.name,
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
                            text = CornItem.description,
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
fun FlowStage6()
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
            items(CornViewItems.subList(4,6)) { CornItem ->
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
                        painter = painterResource(id = CornItem.image),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier.padding(18.dp)
                    ) {
                        Text(text = CornItem.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(Modifier.height(0.dp))
                        Text(
                            text = CornItem.name,
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
                            text = CornItem.description,
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
fun FruitStage6()
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
            items(CornViewItems.subList(6,10)) { CornItem ->
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
                        painter = painterResource(id = CornItem.image),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier.padding(18.dp)
                    ) {
                        Text(text = CornItem.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(Modifier.height(0.dp))
                        Text(
                            text = CornItem.name,
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
                            text = CornItem.description,
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
fun SamCard6()
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

data class CornItem(val id: Int, val title: String, val image: Int,val name: String,val description : String)


var CornViewItems : List<CornItem> = listOf(
    CornItem(1, "Virus", R.drawable.pot,"Apple Mosiac Virus","1. Use certified virus-free material.\n2.Do not use slips from infected trees for grafting.\n" +
            "3.Use thermotherapy for 24 to 32 days at 38°C to limit the outbreak of the virus.\n4.Remove infected plant parts and weeds on your field."),
    CornItem(2, "Bacteria", R.drawable.pot,"Bacterial Canker","1.Only use seeds from certified nurseries.\n2.Choose loactions exposed to wind to decrease humidity\n" +
            "3.Aviod nitrogen-rich fertilizers but still fertilize accordingly.\n4.Cut off all cankered areas down to the healthy wood.\nPrune soon after the harvest so the wounds can heal better with an appropriate paint.\n" +
            "5.Destroy the affected tree material by burning or throwing it to a landfill."),
    CornItem(3, "Fungus", R.drawable.pot,"Apple Scab","1.Cut the affected leaes,shoots and fruits.\n2.Apply 5% urea to leaves in authum to enhance the decomposition and to obstruct the life cycle of the fungus.\n3.Excessive leaf litter can also be mowed to speed up the breakdown of tissues.\n" +
            "4.Water in evening or early morning hours and avoid overhead irrigation."),
    CornItem(4, "Virus", R.drawable.pot,"Apple Leaf Spot Virus","1.Cultivate only resistant and virus-free certified material and varieties.\n" +
            "2.Avoidence of the infection is the best way to prevent introduction of the virus into new orchads."),
    CornItem(5, "Fungus", R.drawable.pot,"Powdery Mildew","1.Plant crops with sufficient spacing to allow for good ventilation.\n2.Remove the infected leaves when the first spots appears or it damges more.\n" +
            "3.A thick layer of mulch can prevent the disperce of spores from the soil up onto the leaves.\n4.Plow the soil throughly after harvest to dig plant residues deep into soil."),
    CornItem(6, "Fungus", R.drawable.pot,"Marssonina Blotch of Apple", "1.Do not cultivatee Royal,Golden Delicious as they are highly susceptible.\n2.Destroy spores of Diplocarpon mali by destroying fallen leaves in authum.\n" +
            "3.The incidencec of the disease can be controlled through orchad sanitation, and pruning."),
    CornItem(7, "Fungus", R.drawable.pot,"Fruit Rot","1.Avoid any injuries to the fruit during the field work or by pests.\n2.Cut off all dying shoots 20cm to 30cm into healthy wood at first sign of infection.\n" +
            "3.Remove and destroy all infected fruits or branches to prvent the spreading of the infection.\n4.Avoid excessive use of nitrogen fertilizers."),
    CornItem(8, "Fungus", R.drawable.pot,"Sooty Blotch of Apple","1.Remove the alternate hosts such as brambles from orchard and surround hedgerows.\n2.Prune trees in summer to open up tree canopy and facilitate air movement and drying of fruit after rainfall.\n" +
            "3.Thin out trees to seperate fruit clusters and also prevent the disease incidence."),
    CornItem(9, "Fungus", R.drawable.pot,"Apple Scab","1.Cut the affected leaes,shoots and fruits.\n2.Apply 5% urea to leaves in authum to enhance the decomposition and to obstruct the life cycle of the fungus.\n3.Excessive leaf litter can also be mowed to speed up the breakdown of tissues.\n" +
            "4.Water in evening or early morning hours and avoid overhead irrigation."),
    CornItem(10, "Fungus", R.drawable.pot,"Apple Rot and Collar Rot","1.It happens due to soil-borne fungus, avoid excessive weed growth near trunk.\n2.Remove soil from from base to expose the infected trunk and let the area dry and refiil with fresh soil.")
)