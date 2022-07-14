package com.gurkanaytekin.jetpackcomposeexample.ui.tasks

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.gurkanaytekin.jetpackcomposeexample.components.TextFieldAlternative
import com.gurkanaytekin.jetpackcomposeexample.jetpacknavigation.Screen

@Composable
fun TaskListScreen(navController: NavController) {
    val viewModel = hiltViewModel<TaskListViewModel>()
    viewModel.setNavigation(navController)
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Tasks List", fontSize = 24.sp)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                TextFieldAlternative(value = viewModel.newTask, onInputChanged = {viewModel.newTask = it})
            }
            Column {
                Button(onClick = { viewModel.addTask() }) {
                    Text(text = "Ekle")
                }
            }
        }
        
        viewModel.tasks.let {
            LazyColumn {
                it.map {
                    item {
                        /*Column(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = rememberImagePainter("https://picsum.photos/200/300"),
                                contentDescription = null,
                                modifier = Modifier.size(128.dp)
                            )
                        }*/
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .height(26.dp), verticalAlignment = CenterVertically) {
                            Checkbox(checked = it.completed, onCheckedChange = { checkedChange ->
                                viewModel.changeCompleted(it.id, !it.completed)
                            })
                            Text(text = it.description)
                        }

                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListPreview() {
    TaskListScreen(rememberNavController())
}