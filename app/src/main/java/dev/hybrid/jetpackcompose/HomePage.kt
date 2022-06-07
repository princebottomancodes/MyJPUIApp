package dev.hybrid.jetpackcompose

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

//var selectedIndex: Int = 0;
//var isSelected: Boolean = false;

@Composable
fun HomePage() {
   Card(elevation = 6.dp, shape = RectangleShape) {
      BoxWithConstraints {
         val constraints = if (minWidth < 600.dp) {
            portraitOrientation(margin = 16.dp)
            //For the else cond we can use the landscape Orientation
         } else portraitOrientation(margin = 16.dp)

            ConstraintLayout(constraintSet = constraints) {
               Image(painter = painterResource(
                  id = R.drawable.husky),
                  contentDescription = "husky",
                  modifier = Modifier
                     .border(
                        border = BorderStroke(
                           width = 2.dp,
                           color = Color.Blue
                        ),
                        shape = CircleShape
                     )
                     .clip(shape = CircleShape)
                     .size(width = 100.dp, height = 100.dp)
                     .layoutId("imgDog"))
               Text(text = "Serbian Husky",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.layoutId("txtBreed"))
               Text(text = "Germany",
                  fontSize = 13.sp,
                  modifier = Modifier.layoutId("txtLocation"))
               Row(
                  horizontalArrangement = Arrangement.SpaceEvenly,
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier
                     .fillMaxWidth()
                     .layoutId("rowProfileStats")) {
                  ProfileStats(count = 150, title =  "Followers")
                  ProfileStats(count = 600, title = "Following")
                  ProfileStats(count = 50, title = "Messages")
               }
               Button(onClick = { /*TODO*/ },
                  modifier = Modifier.layoutId("btnFollow")) {
                  Text(text = "Follow")
               }
               Button(onClick = { /*TODO*/ },
                  modifier = Modifier.layoutId("btnMessage")) {
                  Text(text = "Message")
               }
            }
      }
   }
}

@Composable
fun ProfileStats(count: Int, title: String) {
   Column(
      modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Text(text = "$count", fontWeight = FontWeight.Bold)
      Text(text = title)
   }
}

@Composable
fun portraitOrientation(margin: Dp): ConstraintSet {
   return ConstraintSet {
      val imgDog = createRefFor("imgDog")
      val txtBreed = createRefFor("txtBreed")
      val txtLocation = createRefFor("txtLocation")
      val rowProfileStats = createRefFor("rowProfileStats")
      val btnFollow = createRefFor("btnFollow")
      val btnMessage = createRefFor("btnMessage")
      val guideLine = createGuidelineFromTop(0.15f)

      constrain(imgDog) {
         top.linkTo(guideLine)
         start.linkTo(parent.start)
         end.linkTo(parent.end)
      }
      constrain(txtBreed) {
         top.linkTo(imgDog.bottom)
         start.linkTo(parent.start)
         end.linkTo(parent.end)
      }
      constrain(txtLocation) {
         top.linkTo(txtBreed.bottom)
         start.linkTo(parent.start)
         end.linkTo(parent.end)
      }
      constrain(rowProfileStats) {
         /*I will not add the left and right constraints because
         I will make the row to fll the screen width and it's also
         a performance reason.
         */
         top.linkTo(txtLocation.bottom)
      }
      /*
      For the buttons we can either build the using a row that will contain both
      the button or just constrain the buttons singularly. Check out the ff code.
      */
      constrain(btnFollow) {
         top.linkTo(rowProfileStats.bottom)
         start.linkTo(parent.start)
         end.linkTo(btnMessage.start)
      }
      constrain(btnMessage) {
         top.linkTo(rowProfileStats.bottom)
         start.linkTo(btnFollow.end)
         end.linkTo(parent.end)
      }
   }
}

@Preview
@Composable
fun HomePagePreview() {
   HomePage()
}
