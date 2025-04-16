package com.example.tradewolfapp.views.components

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SnackBarErrorComponent(
    snackBarHostState: SnackbarHostState,
    message: String,
    duration: SnackbarDuration = SnackbarDuration.Short,
    scope: CoroutineScope = rememberCoroutineScope()

) {
    Scaffold (
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { innerPadding ->
        LaunchedEffect(key1 = message) {
            val result = snackBarHostState
                .showSnackbar(
                    message = message,
                    duration = duration,
                    withDismissAction = true,
                )
            when (result) {
                SnackbarResult.Dismissed -> {}
                SnackbarResult.ActionPerformed -> {}
                else -> {}
            }
        }

    }

}