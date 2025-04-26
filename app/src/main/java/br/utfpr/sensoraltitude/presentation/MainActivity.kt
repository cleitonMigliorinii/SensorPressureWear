/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package br.utfpr.sensoraltitude.presentation

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.runtime.mutableStateOf
import androidx.wear.compose.material.MaterialTheme
import kotlin.math.pow

class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var pressureSensor: Sensor? = null

    private val valorPressao = mutableStateOf<Float?>(null)
    private val valorAltitude = mutableStateOf<Float?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)

        setContent {
            MaterialTheme {
                AltitudeScreen(
                    pressao = valorPressao.value,
                    altitude = valorAltitude.value
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()

        pressureSensor?.also {
            sensorManager.registerListener(this,it, SensorManager.SENSOR_DELAY_NORMAL)
        }

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {

        val valorPressaoSensor = event.values[0]
        valorPressao.value = valorPressaoSensor

        // Altitude estimada em metros com base na pressão
        val altitude = 44330 * (1 - (valorPressaoSensor / 1013.25).toDouble().pow(1.0 / 5.255))
        valorAltitude.value = altitude.toFloat()

        Log.d("INFO", valorPressaoSensor.toString())

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}