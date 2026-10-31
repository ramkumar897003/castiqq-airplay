package io.github.jqssun.airplay.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.jqssun.airplay.R
import io.github.jqssun.airplay.ui.theme.Castiqq

/** The Castiqq logo and name, with the "qq" in the logo's cyan, as in Castiqq TV. */
@Composable
fun CastiqqLogo(size: Dp, suffix: String? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(size * 0.3f)
    ) {
        Image(painterResource(R.drawable.castiqq_logo), contentDescription = null, modifier = Modifier.size(size))
        Text(
            text = buildAnnotatedString {
                append("Casti")
                withStyle(SpanStyle(color = Castiqq.Cyan)) { append("qq") }
                if (suffix != null) append(" $suffix")
            },
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = (size.value * 0.7f).sp
        )
    }
}

/** Waiting for an iPhone: the logo, this TV's name and how to mirror to it, like Castiqq TV's start screen. */
@Composable
fun CastiqqWaiting(name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 32.dp).widthIn(max = 560.dp)
    ) {
        CastiqqLogo(48.dp, suffix = "AirPlay")
        Spacer(Modifier.height(12.dp))
        Text(name, color = Castiqq.Muted, fontSize = 18.sp)
        Spacer(Modifier.height(20.dp))
        Text(
            stringResource(R.string.castiqq_how_to, name),
            color = Castiqq.Text,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

/** The code an iPhone pairing for the first time types, shown like Castiqq TV's PIN. */
@Composable
fun CastiqqPin(pin: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("PIN", color = Castiqq.Muted, fontSize = 14.sp)
        Text(
            pin,
            color = Color.White,
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 10.sp
        )
        Text(stringResource(R.string.castiqq_type_pin), color = Castiqq.Text, fontSize = 16.sp, textAlign = TextAlign.Center)
    }
}
