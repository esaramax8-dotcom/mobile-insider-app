package com.mobileinsider.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mobileinsider.app.navigation.AppNavigation
import com.mobileinsider.app.ui.theme.MobileInsiderTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        setContent {

            MobileInsiderTheme {

                Surface(
                    modifier =
                        Modifier.fillMaxSize()
                ) {

                    AppNavigation(
                        rememberNavController()
                    )
                }
            }
        }
    }
}
