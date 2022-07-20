package com.gurkanaytekin.jetpackcomposeexample.ui.tasks

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gurkanaytekin.jetpackcomposeexample.components.TextFieldAlternative

@Composable
fun TaskListScreen(navController: NavController) {
    val viewModel = hiltViewModel<TaskListViewModel>()
    val context = LocalContext.current
    viewModel.setNavigation(navController)
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        if(viewModel.dialogStatus) {
            AlertDialog(
                onDismissRequest = { /*TODO*/ },
                title = { Text(text = "Delete Task?")},
                text = { Text(text = "Are you sure") },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.deleteTask()
                            viewModel.dialogStatus = false
                            Toast.makeText(
                                context,
                                "Deleted Successfully",
                                Toast.LENGTH_LONG
                            ).show()
                        }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            viewModel.dialogStatus = false
                        }) {
                        Text("No")
                    }
                }
            )
        }

        Text("Tasks List", fontSize = 24.sp)
        Row( modifier = Modifier.height(50.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.weight(4f)) {
                TextFieldAlternative(value = viewModel.newTask, onInputChanged = {viewModel.newTask = it})
            }
            Button(onClick = { viewModel.addTask() }, modifier = Modifier
                .weight(1f).fillMaxHeight(), enabled = !viewModel.serviceCalling) {
                Text(text = "Add")
            }
        }
        
        viewModel.tasks.let {
            LazyColumn {
                items(viewModel.tasks) { item ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .height(26.dp), verticalAlignment = CenterVertically) {
                        Checkbox(checked = item.completed, onCheckedChange = { checkedChange ->
                            viewModel.onChangeCompletedStatus(item.id, !item.completed)
                        })
                        Text(text = item.description)
                        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End) {
                            IconButton(onClick = {
                                viewModel.selectedIdForDeletion = item.id
                                viewModel.dialogStatus = true
                            }) {
                                Icon(Icons.Filled.Delete, contentDescription = "")
                            }
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