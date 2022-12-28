package com.eschool.android.ui.auth.signup

import android.os.Build
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Key
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material.icons.rounded.Password
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.eschool.android.R
import com.eschool.android.data.common.getMessageStringResource
import com.eschool.android.data.dto.auth.DeviceInfo
import com.eschool.android.data.dto.auth.SignUpRequest
import com.eschool.android.ui.auth.navigation.signUpScreen
import com.eschool.android.ui.theme.BackgroundUtility

@Composable
fun SignUpNextScreen(navController: NavHostController) {
    var token by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel: SignUpViewModel = viewModel()

    if (viewModel.responseState.value.hasError) {
        val statusCode = viewModel.responseState.value.code

        AlertDialog(
            onDismissRequest = { viewModel.clear() },
            buttons = {
                TextButton(onClick = { viewModel.clear() }) {
                    Text(stringResource(R.string.ok))
                }
            },
            title = { Text(stringResource(R.string.error, statusCode)) },
            text = {
                Text(
                    text = stringResource(
                        getMessageStringResource(statusCode)
                    )
                )
            },
            modifier = Modifier.clip(RoundedCornerShape(16.dp)),
            properties = DialogProperties()
        )
    }
    
    Surface(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .background(BackgroundUtility.getGradient())
                .systemBarsPadding()
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
        ) {
            Column(Modifier.padding(top = 36.dp)) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val screenInfoText = stringResource(R.string.sign_up) + " " + stringResource(R.string.by_token)

                    ESchoolSmallLogo()
                    Text(
                        text = screenInfoText.uppercase(),
                        color = MaterialTheme.colors.onBackground.copy(.75f),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Column(Modifier.padding(top = 36.dp)) {
                    Text(
                        stringResource(R.string.sign_up_by_teacher_token),
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                    RoundedTextField(
                        token,
                        Icons.Rounded.Key,
                        R.string.token,
                        !viewModel.responseState.value.isLoading
                    ) { token = it }
                }

                Column(Modifier.padding(top = 36.dp)) {
                    Text(
                        stringResource(R.string.create_password),
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                    RoundedTextField(
                        password,
                        Icons.Rounded.Password,
                        R.string.password,
                        !viewModel.responseState.value.isLoading
                    ) { password = it }
                }
            }

            Column(
                Modifier.align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        viewModel.signUp(
                            SignUpRequest(
                                "-",
                                token,
                                password,
                                DeviceInfo(
                                    "Android ${Build.VERSION.RELEASE}",
                                    Build.BRAND,
                                    Build.MODEL
                                )
                            )
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    enabled = !viewModel.responseState.value.isLoading
                ) {
                    Text(
                        stringResource(R.string.sign_up),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    stringResource(R.string.back),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable {
                            navController.navigate(signUpScreen)
                        },
                    color = MaterialTheme.colors.onBackground.copy(.75f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun ESchoolSmallLogo() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Rounded.MenuBook,
            null,
            Modifier.size(48.dp)
        )
        Text(
            stringResource(R.string.app_name),
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(R.font.e_ukraine_logo)),
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 2.5.em,
                    platformStyle = @Suppress("DEPRECATION") PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            ),
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
fun RoundedTextField(
    text: String,
    startIcon: ImageVector,
    @StringRes label: Int,
    enabled: Boolean,
    onChange: (String) -> Unit = {}
) {
    var textInput by remember { mutableStateOf(text) }

    TextField(
        value = textInput,
        onValueChange = {
            textInput = it
            onChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.onBackground,
            textColor = MaterialTheme.colors.onBackground,
            focusedLabelColor = MaterialTheme.colors.onBackground,
        ),
        label = {
            Text(stringResource(label))
        },
        leadingIcon = {
            Icon(
                startIcon,
                null
            )
        },
        enabled = enabled
    )
}