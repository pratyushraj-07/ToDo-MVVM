package com.example.todo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieScreen()
{
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp)
            .size(500.dp),
        contentAlignment = Alignment.Center)
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(250.dp)
            )

            Text(
                text = "Nothing To-Do",
                fontSize = 20.sp,
                color = Color.LightGray
            )
        }
    }

}