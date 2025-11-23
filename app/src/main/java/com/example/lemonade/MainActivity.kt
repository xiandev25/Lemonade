package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Lemonade(
                        title = "Lemonade",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Lemonade(title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Title(title = title)
        MainContent()
    }
}

@Composable
fun Title(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    var status by remember { mutableStateOf( value = 0) }
    var lemonClickCount = 0
    val maxLemonClickCount = (2..6).random()

    val image = when(status) {
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        3 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    val imageLabel = when(status) {
        1 -> R.string.lemon_prompt_message
        2 -> R.string.glass_of_lemonade_prompt_message
        3 -> R.string.empty_glass_prompt_message
        else -> R.string.lemon_tree_prompt_message
    }

    val imageContentDescription = when(status) {
        1 -> R.string.lemon_image_content_description
        2 -> R.string.glass_of_lemonade_image_content_description
        3 -> R.string.empty_glass_image_content_description
        else -> R.string.lemon_tree_image_content_description
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = imageContentDescription),
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    if (status == 1 && lemonClickCount != maxLemonClickCount) {
                            lemonClickCount++
                    } else {
                        status = when (status) {
                            0 -> 1
                            1 -> 2
                            2 -> 3
                            else -> 0
                        }
                    }
                })
        )
        Spacer(
            modifier = Modifier
                .height(height = 16.dp)
        )
        Text(
            text = stringResource(id = imageLabel),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        Lemonade(
            title = "Lemonade"
        )
    }
}