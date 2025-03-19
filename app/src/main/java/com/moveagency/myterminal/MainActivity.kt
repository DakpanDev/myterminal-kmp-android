package com.moveagency.myterminal

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.moveagency.myterminal.composable.MainScreen
import com.moveagency.myterminal.shared.KoinHelper
import com.moveagency.myterminal.ui.theme.MyTerminalTheme
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinContext
import org.koin.core.error.KoinApplicationAlreadyStartedException
import org.koin.ksp.generated.module

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupKoin()
        enableEdgeToEdge()

        setContent {
            KoinContext {
                MyTerminalTheme {
                    MainScreen()
                }
            }
        }
    }

    private fun setupKoin() {
        try {
            KoinHelper.initKoin {
                printLogger()
                androidContext(this@MainActivity)
                modules(AppModule().module)
            }
        } catch (exception: KoinApplicationAlreadyStartedException) {
            Log.e(javaClass.simpleName, "Koin application already started")
        }
    }
}
