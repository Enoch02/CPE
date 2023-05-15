package com.cpe.ui.screens.auth_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cpe.ui.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(viewModel: AuthViewModel = hiltViewModel(), navController: NavController) {
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val registrationState = viewModel.registrationState.collectAsState(initial = null)
    val loginState = viewModel.loginState.collectAsState(initial = null)

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Text(
                text = "Log In or Create a New Account",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "E-mail icon"
                    )
                },
                placeholder = { Text(text = "E-mail") },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    autoCorrect = true
                ),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                singleLine = true,
                onValueChange = { password = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Password,
                        contentDescription = "E-mail icon"
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                placeholder = { Text(text = "Password") },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { viewModel.loginUser(email, password) })
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.loginUser(email, password) },
                content = {
                    Text(text = "Log In")
                },
                modifier = Modifier.fillMaxWidth()
            )

            FilledTonalButton(
                onClick = { viewModel.registerUser(email, password) },
                content = {
                    Text(text = "Sign Up")
                },
                modifier = Modifier.fillMaxWidth()
            )

            TextButton(
                onClick = { /*TODO*/ },
                content = {
                    Text(text = "reset password")
                },
                modifier = Modifier.align(Alignment.End)
            )
        }
    )

    //TODO: replace the toasts with snackbars
    val context = LocalContext.current
    LaunchedEffect(key1 = registrationState.value?.isSuccess) {
        scope.launch {
            if (registrationState.value?.isSuccess?.isNotEmpty() == true) {
                val success = registrationState.value?.isSuccess
                Toast.makeText(context, "$success", Toast.LENGTH_SHORT).show()

                navController.navigate(Screen.MainScaffold.route) {
                    popUpTo(Screen.AuthScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = registrationState.value?.isError) {
        scope.launch {
            if (registrationState.value?.isError?.isNotEmpty() == true) {
                val error = registrationState.value?.isError
                Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    LaunchedEffect(key1 = loginState.value?.isSuccess) {
        scope.launch {
            if (loginState.value?.isSuccess?.isNotEmpty() == true) {
                val success = loginState.value?.isSuccess
                Toast.makeText(context, "$success", Toast.LENGTH_SHORT).show()

                navController.navigate(Screen.MainScaffold.route) {
                    popUpTo(Screen.AuthScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = loginState.value?.isError) {
        scope.launch {
            if (loginState.value?.isError?.isNotEmpty() == true) {
                val error = loginState.value?.isError
                Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    AuthScreen(navController = rememberNavController())
}