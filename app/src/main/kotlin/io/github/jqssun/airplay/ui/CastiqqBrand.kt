package io.github.jqssun.airplay.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.jqssun.airplay.R
import io.github.jqssun.airplay.ui.theme.Castiqq

// Castiqq's own layout, as in Castiqq TV and the desktop app: a flat dark
// screen, the logo top left, an uppercase grey label over each value, the
// blue status line and outlined buttons that light up blue when focused.

/** The Castiqq logo and name, with the "qq" in the logo's cyan. */
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

/** A button like Castiqq TV's: outlined grey, blue when focused; filled blue when [primary]. */
@Composable
fun CastiqqButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    primary: Boolean = false,
    selected: Boolean = false,
    /** A tab in the header's switch: no outline until focused. */
    tab: Boolean = false,
    focusRequester: FocusRequester? = null,
) {
    val interaction = remember { MutableInteractionSource() }
    val focused by interaction.collectIsFocusedAsState()
    val shape = RoundedCornerShape(8.dp)
    val background = when {
        primary || selected -> Castiqq.Button
        focused -> Castiqq.Surface
        else -> Color.Transparent
    }
    val border = when {
        focused -> Castiqq.Focus
        primary || selected -> Castiqq.Button
        tab -> Color.Transparent
        else -> Castiqq.Border
    }
    Box(
        modifier = modifier
            .then(if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier)
            .clip(shape)
            .background(background)
            .border(2.dp, border, shape)
            .clickable(interactionSource = interaction, indication = null, onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 10.dp)
    ) {
        Text(text, color = Color.White, fontSize = 17.sp, fontWeight = if (primary || selected) FontWeight.SemiBold else FontWeight.Normal)
    }
}

/** Castiqq's top bar: logo, the screens as a segmented switch, and a status pill, like the desktop app. */
@Composable
fun CastiqqHeader(
    tabs: List<String>,
    selected: Int,
    onSelect: (Int) -> Unit,
    status: String,
    statusColor: Color,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CastiqqLogo(32.dp, suffix = "AirPlay")
            Spacer(Modifier.width(28.dp))
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Castiqq.Surface)
                    .border(1.dp, Castiqq.Border, RoundedCornerShape(10.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                tabs.forEachIndexed { i, label ->
                    CastiqqButton(label, onClick = { onSelect(i) }, selected = i == selected, tab = true)
                }
            }
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Castiqq.Surface)
                    .border(1.dp, Castiqq.Border, CircleShape)
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.size(8.dp).clip(CircleShape).background(statusColor))
                Spacer(Modifier.width(8.dp))
                Text(status, color = Castiqq.Muted, fontSize = 15.sp)
            }
        }
        HorizontalDivider(color = Castiqq.Border)
    }
}

/** An uppercase grey label over a value, as on every Castiqq screen. */
@Composable
fun CastiqqLabel(text: String) {
    Text(text.uppercase(), color = Castiqq.Muted, fontSize = 14.sp, letterSpacing = 0.5.sp)
}

/**
 * Waiting for an iPhone, laid out like Castiqq TV's start screen: this TV's
 * name, the status and what to do on the left; the steps in a card on the right.
 */
@Composable
fun CastiqqHome(
    name: String,
    status: String,
    running: Boolean,
    onStartStop: () -> Unit,
    focusRequester: FocusRequester,
) {
    Row(
        modifier = Modifier.fillMaxSize().padding(horizontal = 80.dp, vertical = 28.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.widthIn(max = 520.dp).weight(1f, fill = false)) {
            CastiqqLabel(stringResource(R.string.castiqq_this_tv))
            Text(name, color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(18.dp))
            Text(status, color = Castiqq.Focus, fontSize = 22.sp)
            Spacer(Modifier.height(8.dp))
            Text(stringResource(R.string.castiqq_how_to, name), color = Castiqq.Text, fontSize = 16.sp)
            Spacer(Modifier.height(24.dp))
            CastiqqButton(
                stringResource(if (running) R.string.castiqq_stop else R.string.castiqq_start),
                onClick = onStartStop,
                focusRequester = focusRequester
            )
        }
        Spacer(Modifier.width(40.dp))
        Column(
            modifier = Modifier
                .width(340.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Castiqq.Surface)
                .border(1.dp, Castiqq.Border, RoundedCornerShape(12.dp))
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(stringResource(R.string.castiqq_steps_title), color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            listOf(
                stringResource(R.string.castiqq_step_1),
                stringResource(R.string.castiqq_step_2),
                stringResource(R.string.castiqq_step_3, name),
            ).forEachIndexed { i, step ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier.size(28.dp).clip(CircleShape).background(Castiqq.Button),
                        contentAlignment = Alignment.Center
                    ) { Text("${i + 1}", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold) }
                    Spacer(Modifier.width(12.dp))
                    Text(step, color = Castiqq.Text, fontSize = 16.sp)
                }
            }
        }
    }
}

/** The code an iPhone pairing for the first time types, shown like Castiqq TV's PIN. */
@Composable
fun CastiqqPin(pin: String) {
    Column {
        CastiqqLabel("PIN")
        Text(pin, color = Color.White, fontSize = 64.sp, fontWeight = FontWeight.Bold, letterSpacing = 10.sp)
        Text(stringResource(R.string.castiqq_type_pin), color = Castiqq.Text, fontSize = 16.sp)
    }
}
