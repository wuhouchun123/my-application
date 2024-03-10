package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import java.security.Permission

@Composable
fun PermissionHandlingBox() {
    PermissionHandling()
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionHandling() {
    var permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO
        )
    )
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                permissionState.launchMultiplePermissionRequest()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        permissionState.permissions.forEach() { perm ->
            when(perm.permission) {
                android.Manifest.permission.CAMERA -> {
                    when {
                        perm.status.isGranted -> {
                            // 权限已授予
                            Text("相机权限已授予")
                        }
                        perm.status.shouldShowRationale -> {
                            // 权限被拒绝但应该向用户解释为什么需要它
                            Text("相机权限被拒绝，但是需要相机开启拍照功能")
                        }
                        else -> {
                            // 权限被拒绝且不再询问
                            Text("权限被拒绝且不再询问，请到应用管理授予相机权限")
                        }
                    }
                }
                android.Manifest.permission.RECORD_AUDIO -> {
                    when {
                        perm.status.isGranted -> {
                            // 权限已授予
                            Text("录音权限已授予")
                        }
                        perm.status.shouldShowRationale -> {
                            // 权限被拒绝但应该向用户解释为什么需要它
                            Text("相机权限被拒绝，但是需要相机开启拍照功能")
                        }
                        else -> {
                            // 权限被拒绝且不再询问
                            Text("权限被拒绝且不再询问，请到应用管理授予相机权限")
                        }
                    }
                }
            }
        }
    }
}