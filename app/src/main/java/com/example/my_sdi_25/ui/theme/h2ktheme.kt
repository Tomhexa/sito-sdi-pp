// In My_SDI_25Theme.kt
package com.example.my_sdi_25.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
// ... other imports from your file (BitmapFactory, Palette, Color, etc.)
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.remember // for remember {}
import android.graphics.BitmapFactory // for BitmapFactory
import androidx.palette.graphics.Palette // for Palette
import androidx.compose.ui.graphics.Color // for Compose Color
import androidx.compose.ui.graphics.toArgb // for .toArgb() if you use it, or .rgb for integer
import com.example.my_sdi_25.R // Assuming R.drawable.logo is correct
import androidx.compose.foundation.shape.RoundedCornerShape // For Shapes
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// Your existing DarkColorScheme and LightColorScheme
private val DarkColorSchemeDefault = darkColorScheme(
	primary = Purple80,
	secondary = PurpleGrey80,
	tertiary = Pink80
)

private val LightColorSchemeDefault = lightColorScheme(
	primary = Purple40,
	secondary = PurpleGrey40,
	tertiary = Pink40
	// ... other default colors
)

@Composable
fun My_SDI_25Theme(
	// <--- RENAMED FUNCTION
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit,
) {
	val context = LocalContext.current
	// It's good practice to handle potential errors if R.drawable.logo doesn't exist
	// or if BitmapFactory.decodeResource returns null.
	val logoBitmap = remember {
		try {
			BitmapFactory.decodeResource(context.resources, R.drawable.logo)
		} catch (e: Exception) {
			// Log error or provide a fallback bitmap
			null // Or a default placeholder Bitmap
		}
	}

	val colorScheme = if (logoBitmap != null) {
		if (darkTheme) {
			val palette = Palette.from(logoBitmap).generate()
			darkColorScheme(
				primary = Color(palette.getDarkVibrantColor(DarkColorSchemeDefault.primary.toArgb())),
				secondary = Color(palette.getVibrantColor(DarkColorSchemeDefault.secondary.toArgb())),
				tertiary = Color(palette.getLightMutedColor(DarkColorSchemeDefault.tertiary.toArgb()))
				// Add other colors from palette or defaults
			)
		} else {
			val palette = Palette.from(logoBitmap).generate()
			lightColorScheme(
				primary = Color(palette.getVibrantColor(LightColorSchemeDefault.primary.toArgb())),
				secondary = Color(palette.getMutedColor(LightColorSchemeDefault.secondary.toArgb())),
				tertiary = Color(palette.getLightVibrantColor(LightColorSchemeDefault.tertiary.toArgb()))
				// Add other colors from palette or defaults
			)
		}
	} else {
		// Fallback to default color schemes if logoBitmap is null
		if (darkTheme) DarkColorSchemeDefault else LightColorSchemeDefault
	}

	MaterialTheme(
		colorScheme = colorScheme,
		typography = Typography( // Ensure Typography is defined in Typography.kt
			displayLarge = TextStyle(
				fontFamily = FontFamily.SansSerif,
				fontWeight = FontWeight.Bold,
				fontSize = 36.sp
			)
			// Define other typography styles as needed
		),
		shapes = MaterialTheme.shapes.copy( // Assuming you want to customize default shapes
			medium = RoundedCornerShape(16.dp),
			large = RoundedCornerShape(24.dp)
		),
		content = content
	)
}