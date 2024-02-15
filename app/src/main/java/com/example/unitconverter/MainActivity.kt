package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember {
        mutableStateOf("")
    }
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit by remember {
        mutableStateOf("Meters")
    }
    var outputUnit by remember {
        mutableStateOf("Meters")
    }
    var inputExpanded by remember {
        mutableStateOf(false)
    }
    var outputExpanded by remember {
        mutableStateOf(false)
    }
    val conversionFactor= remember {
        mutableStateOf(1.00)
    }
    val oConversionFactor= remember {
        mutableStateOf(1.00)
    }
    fun convertUnits(){
        // ?: elvis operator
        val inputValueDouble=inputValue.toDoubleOrNull() ?: 0.0
        val result=(inputValueDouble * conversionFactor.value*100.0 /oConversionFactor.value).roundToInt()/100.0
        outputValue=result.toString()
    }

    val customTextStyle= TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.Red

    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Here all the UI elements will be stacked over each other
        Text("Unit Converter",
            style=customTextStyle
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value =inputValue ,
            onValueChange ={
                inputValue=it
                convertUnits() },
            label = { Text(text = "Enter a value")},
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box{
                //INPUT BUTTON
                Button(onClick = { inputExpanded=true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down" )
                }
                DropdownMenu(expanded = inputExpanded, onDismissRequest = { inputExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {
                            inputExpanded=false
                            inputUnit="Centimeters"
                            conversionFactor.value=0.01
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            inputExpanded=false
                            inputUnit="Meters"
                            conversionFactor.value=1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            inputExpanded=false
                            inputUnit="Feet"
                            conversionFactor.value=0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            inputExpanded=false
                            inputUnit="Millimeters"
                            conversionFactor.value=0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                //OUTPUT BUTTON
                Button(onClick = { outputExpanded=true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down" )
                }
                DropdownMenu(expanded = outputExpanded, onDismissRequest = { outputExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {
                            outputExpanded=false
                            outputUnit="Centimeters"
                            oConversionFactor.value=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            outputExpanded=false
                            outputUnit="Meters"
                            oConversionFactor.value=1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            outputExpanded=false
                            outputUnit="Feet"
                            oConversionFactor.value=0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            outputExpanded=false
                            outputUnit="Millimeters"
                            oConversionFactor.value=0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue $outputUnit",
            style=MaterialTheme.typography.headlineMedium
        )
    }
}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}