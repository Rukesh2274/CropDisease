package com.example.cropdisease.pages
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cropdisease.R

@Composable
fun Allplants(navController: NavController)
{
    Column(modifier = Modifier.padding(15.dp),
    verticalArrangement = Arrangement.spacedBy(0.dp)) {
        Spacer(modifier = Modifier.height(15.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            items(gridViewItems.subList(0, 10)) { gridItem ->
                OutlinedIconButton(
                    onClick = { navController.navigate(gridItem.name) },
                    shape = RoundedCornerShape(25.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier
                        .height(100.dp)
                        .width(90.dp)
                        .fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = gridItem.image),
                        contentDescription = gridItem.name,
                        modifier = Modifier.size(55.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }

    }
}


data class GridItem(val id: Int, val name: String, val image: Int)


var gridViewItems : List<GridItem> = listOf(
    GridItem(1, "apple", R.drawable.apple),
    GridItem(2, "bananna", R.drawable.bananas),
    GridItem(3, "mango", R.drawable.mango),
    GridItem(4, "Item 4", R.drawable.strawberry_fruit_icon),
    GridItem(5, "orange", R.drawable.orange),
    GridItem(6, "corn", R.drawable.corn),
    GridItem(7, "lemon", R.drawable.lemon),
    GridItem(8, "tomato", R.drawable.tomato_icon),
    GridItem(9, "Item 9", R.drawable.chilli_pepper_icon),
    GridItem(10, "wheat", R.drawable.wheat)
)

