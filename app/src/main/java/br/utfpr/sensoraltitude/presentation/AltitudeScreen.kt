package br.utfpr.sensoraltitude.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text

@Composable
fun AltitudeScreen(pressao: Float?, altitude: Float?) {

    Scaffold {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().background(Color(0xFF2196F3))
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = "Barômetro", style = MaterialTheme.typography.title3)
                Spacer(modifier = Modifier.height(12.dp))

                if(pressao != null){
                    Text(text = "Pressão: %.2f hPa".format(pressao) , fontSize = 16.sp)
                }

                if(altitude != null){
                    Text(text = "Altitude: %.2f m".format(altitude), fontSize = 16.sp)
                }

                if(pressao == null || altitude == null){
                    Text(text = "Carregando sensor...", fontSize = 14.sp)
                }


            }
        }

    }

}