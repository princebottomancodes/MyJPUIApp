package dev.hybrid.jetpackcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainView() {
   Scaffold() {
      TopAppBar {
         Text(text = "MyAppBar", textAlign = TextAlign.Center)
      }
      Column() {

      }
   }
}

@Preview
@Composable
fun ScaffoldPreview() {
   MainView()
}