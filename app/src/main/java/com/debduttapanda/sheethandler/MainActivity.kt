package com.debduttapanda.sheethandler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.debduttapanda.sheethandler.ui.theme.SheetHandlerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SheetHandlerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SheetPage()
                }
            }
        }
    }
}
//////////////////
class SheetViewModel: ViewModel(){
    @OptIn(ExperimentalMaterialApi::class)
    fun onOpenClick() {
        sheetHandler.state {
            show()
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    val sheetHandler = SheetHandler(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
        confirmStateChange = {
            true
        }
    )
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetPage(
    vm: SheetViewModel = viewModel()
){
    val state = vm.sheetHandler.handle()
    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            Text("Sheet Content")
        }
    ) {
        Box(
            contentAlignment = Alignment.Center
        ){
            Button(onClick = {
                vm.onOpenClick()
            }) {
                Text("Open")
            }
        }
    }
}