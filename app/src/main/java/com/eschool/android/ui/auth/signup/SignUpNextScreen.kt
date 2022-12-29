package com.eschool.android.ui.auth.signup

import android.os.Build
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.eschool.android.R
import com.eschool.android.data.common.getMessageStringResource
import com.eschool.android.data.dto.auth.DeviceInfo
import com.eschool.android.data.dto.auth.SignUpRequest
import com.eschool.android.ui.auth.navigation.signUpScreen

@Composable
fun SignUpNextScreen(navController: NavHostController) {
    var token by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var tokenLengthValid by remember { mutableStateOf(false) }
    var passwordLengthValid by remember { mutableStateOf(false) }

    var buttonEnabled by remember { mutableStateOf(false) }

    tokenLengthValid = token.length == 8
    passwordLengthValid = password.length in (6..36)
    buttonEnabled = tokenLengthValid && passwordLengthValid

    val signUpViewModel = viewModel<SignUpViewModel>()

    ErrorAlert(signUpViewModel)

    Box(
        Modifier
            .fillMaxSize()
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
                    !signUpViewModel.responseState.value.isLoading
                ) { token = it }

                AnimatedVisibility(
                    visible = !tokenLengthValid,
                    modifier = Modifier
                        .alpha(.6f)
                        .padding(top = 8.dp, start = 8.dp)
                ) {
                    Text(text = stringResource(R.string.invalid_token_length))
                }
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
                    !signUpViewModel.responseState.value.isLoading
                ) { password = it }

                AnimatedVisibility(
                    visible = !passwordLengthValid,
                    modifier = Modifier
                        .alpha(.6f)
                        .padding(top = 8.dp, start = 8.dp)
                ) {
                    Text(text = stringResource(R.string.invalid_password_length))
                }
            }
        }

        Column(
            Modifier.align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = buttonEnabled,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Button(
                    onClick = {
                        signUpViewModel.signUp(
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
                    enabled = !signUpViewModel.responseState.value.isLoading
                ) {
                    Text(
                        stringResource(R.string.sign_up),
                        fontWeight = FontWeight.Bold
                    )
                }
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
                    platformStyle =
                        @Suppress("DEPRECATION")
                        PlatformTextStyle(
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
        enabled = enabled,
        maxLines = 1,
        singleLine = true
    )
}

@Composable
fun ErrorAlert(viewModel: SignUpViewModel) {
    if (viewModel.responseState.value.hasError) {
        val statusCode = viewModel.responseState.value.code

        Dialog(
            onDismissRequest = { viewModel.clear() }
        ) {
            Card(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Column {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = stringResource(R.string.error_f, statusCode),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = stringResource(getMessageStringResource(statusCode)),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((0.8).dp)
                            .background(MaterialTheme.colors.onBackground.copy(.1f))
                            .padding(bottom = 16.dp)
                    )

                    Box(
                        Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.clear() }
                    ) {
                        Text(
                            stringResource(R.string.ok),
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.Center),
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}