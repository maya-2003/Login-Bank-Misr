package com.maya.loginapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.maya.loginapplication.ui.theme.LoginApplicationTheme
import com.maya.loginapplication.ui.theme.RedBM
import com.maya.loginapplication.ui.theme.RedBMLight
import com.maya.loginapplication.ui.theme.iconTextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginApplicationTheme {
                LoginDesign()
            }
        }
    }
}

@Composable
fun LoginDesign(modifier: Modifier = Modifier) {
    val context = LocalConfiguration.current
    val englishFont = FontFamily.Default
    val arabicFont = FontFamily(Font(R.font.cairo))
    val englishWeight = FontWeight.Medium
    val arabicWeight = FontWeight.Bold
    val localeBasedFont = if (context.locales[0].language == "ar") arabicFont else englishFont
    val localeBasedWeight = if (context.locales[0].language == "ar") arabicWeight else englishWeight


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderDesign()
        var username by remember { mutableStateOf("") }
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = {
                Text(
                    stringResource(R.string.username),
                    color = Color.Gray,
                    fontWeight = localeBasedWeight,
                    fontFamily = localeBasedFont
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp, top = 40.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray
            )
        )

        var password by remember { mutableStateOf("") }
        var isVisible by remember { mutableStateOf(false) }
        val icon = if (isVisible)
            Icons.Outlined.Visibility
        else
            Icons.Outlined.VisibilityOff
        val customFont = FontFamily(Font(R.font.cairo_bold))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    text = stringResource(R.string.password),
                    color = Color.Gray,
                    fontWeight = localeBasedWeight,
                    fontFamily = localeBasedFont
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp, top = 20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = {
                    isVisible = !isVisible
                }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Visibility Icon",
                        tint = Color.Gray
                    )
                }
            },
            visualTransformation = if (isVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
        )
        Text(
            text = stringResource(R.string.forgot_username_password),
            textDecoration = TextDecoration.Underline,
            fontFamily = localeBasedFont,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = Color.DarkGray,
            modifier = modifier
                .align(alignment = Alignment.Start)
                .padding(start = 28.dp, top = 20.dp)
        )

        FilledTonalButton(
            onClick = { /*TODO*/ },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp, top = 40.dp)
                .height(55.dp),
            colors = if (username.isEmpty() || password.isEmpty()) ButtonDefaults.filledTonalButtonColors(
                containerColor = RedBMLight,
                contentColor = Color.White
            )
            else
                ButtonDefaults.filledTonalButtonColors(
                    containerColor = RedBM,
                    contentColor = Color.White
                ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = stringResource(R.string.login),
                fontFamily = if (context.locales[0].language == "ar") customFont else
                    englishFont,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

        }

        Row(
            modifier = modifier
                .align(Alignment.Start)
                .padding(start = 28.dp, top = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.need_help),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                modifier = modifier.padding(end = 4.dp),
                fontFamily = localeBasedFont
            )
            Text(
                text = stringResource(R.string.contact_us),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                fontFamily = localeBasedFont,
                color = RedBM
            )
        }
        HorizontalDivider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 48.dp, start = 28.dp, end = 28.dp)
        )
        IconsDesign()
    }
}

@Composable
fun HeaderDesign(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 80.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bm_icon),
            contentDescription = "Bank Misr Logo",
            modifier = modifier.padding(start = 28.dp)
        )
        TextButton(
            onClick = { /*TODO*/ },
            modifier = modifier.padding(end = 28.dp)
        ) {
            Text(
                text = stringResource(R.string.language),
                fontFamily = FontFamily(Font(R.font.cairo_extra_bold_2)),
                color = RedBM,
                fontSize = 16.sp,
            )
        }
    }
}


@Composable
fun IconsDesign(modifier: Modifier = Modifier) {
    val context = LocalConfiguration.current
    val englishFont = FontFamily.Default
    val arabicFont = FontFamily(Font(R.font.cairo))
    val localeBasedFont = if (context.locales[0].language == "ar") arabicFont else englishFont

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 28.dp, end = 28.dp, top = 28.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.our_products),
                contentDescription = "Products",
                modifier = Modifier
                    .size(64.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.our_products),
                fontFamily = localeBasedFont,
                style = iconTextStyle
            )
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.exchange_rate),
                contentDescription = "Exchange rate",
                modifier = Modifier
                    .size(64.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.excahnge_rate),
                fontFamily = localeBasedFont,
                style = iconTextStyle
            )
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.security_tips),
                contentDescription = "Security tips",
                modifier = Modifier
                    .size(64.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.security_tips),
                fontFamily = localeBasedFont,
                style = iconTextStyle

            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.nearest_branch_or_atm),
                contentDescription = "Nearest branch",
                modifier = Modifier
                    .size(64.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.nearest_branch_or_atm),
                fontFamily = localeBasedFont,
                style = iconTextStyle
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginDesignPreview() {
    LoginApplicationTheme {
        LoginDesign()
    }
}