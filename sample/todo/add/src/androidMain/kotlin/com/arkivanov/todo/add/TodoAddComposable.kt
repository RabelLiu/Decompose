package com.arkivanov.todo.add

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.observe

@Composable
operator fun TodoAdd.Model.invoke() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        data.observe { data ->
            OutlinedTextField(
                value = data.text,
                modifier = Modifier.weight(weight = 1F),
                onValueChange = ::onTextChanged,
                label = { Text(text = "Add a todo") }
            )
        }

        IconButton(modifier = Modifier.padding(start = 8.dp), onClick = ::onAddClicked) {
            Icon(Icons.Default.Add)
        }
    }
}
