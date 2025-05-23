// In MainActivity.kt

package com.example.my_sdi_25

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.h2ktheme.Hook2PlayTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hook2PlayTheme { // <--- USE THE CORRECT THEME NAME
                // Assuming AppNavigation is defined elsewhere or below
                AppNavigation()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true) // Preview for Greeting
@Composable
fun GreetingPreview() {
    // It's good practice for previews to also be wrapped in your theme
    // if Hook2PlayTheme is available and working.
    // If Hook2PlayTheme is the source of the error, you might comment this out
    // or use a default MaterialTheme for previewing components in isolation.
    /*
    Hook2PlayTheme {
        Greeting("Preview Android")
    }
    */
    // Fallback for preview if Hook2PlayTheme is broken:
    androidx.compose.material3.MaterialTheme { // Default MaterialTheme for preview
        Greeting("Preview Android")
    }
}


// Assuming AppNavigation is defined something like this:
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "greeting") {
        composable("greeting") { Greeting("Android Navigation") }
        // Add other destinations
    }
}