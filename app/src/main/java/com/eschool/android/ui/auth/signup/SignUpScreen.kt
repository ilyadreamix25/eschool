package com.eschool.android.ui.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.eschool.android.R
import com.eschool.android.ui.auth.navigation.signInScreen
import com.eschool.android.ui.auth.navigation.signUpNextScreen
import com.eschool.android.ui.theme.BackgroundUtility

@Composable
fun SignUpScreen(navController: NavHostController) {
    Surface(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .background(BackgroundUtility.getGradient())
                .systemBarsPadding()
        ) {
            Column(
                Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 160.dp)) {
                ESchoolLogo()
            }
            Column(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    Modifier.padding(bottom = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(R.string.do_you_have_an_account),
                        color = MaterialTheme.colors.onBackground.copy(.5f),
                        fontSize = 12.sp
                    )
                    Text(
                        stringResource(R.string.sign_in),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            navController.navigate(signInScreen)
                        },
                        color = MaterialTheme.colors.onBackground.copy(.75f),
                        fontSize = 12.sp
                    )
                }

                Button(
                    onClick = { navController.navigate(signUpNextScreen) },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth().height(54.dp)
                ) {
                    Text(
                        stringResource(R.string.sign_up),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ESchoolLogo() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Rounded.MenuBook,
            null,
            Modifier.size(128.dp)
        )
        Text(
            stringResource(R.string.app_name),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.e_ukraine_logo))
        )
        Text(
            stringResource(R.string.for_students)
        )
    }
}