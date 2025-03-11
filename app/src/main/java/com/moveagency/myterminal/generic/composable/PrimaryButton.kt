package com.moveagency.myterminal.generic.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moveagency.myterminal.R
import com.moveagency.myterminal.ui.theme.MyTerminalTheme
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.colors
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.typography
import com.moveagency.myterminal.ui.theme.Spacing

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .border(width = 1.dp, color = colors.white, shape = RoundedCornerShape(Spacing.x1)),
        shape = RoundedCornerShape(Spacing.x1),
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = colors.white,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = colors.secondaryGray,
        ),
    ) {
        Text(
            text = text,
            style = typography.body,
        )
    }
}

@Composable
@Preview
private fun PreviewPrimaryButton() = MyTerminalTheme {
    PrimaryButton(
        text = stringResource(R.string.generic_retry),
        onClick = {},
    )
}
