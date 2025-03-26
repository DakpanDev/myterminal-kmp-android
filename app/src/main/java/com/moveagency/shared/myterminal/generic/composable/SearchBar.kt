package com.moveagency.shared.myterminal.generic.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moveagency.shared.myterminal.R
import com.moveagency.shared.myterminal.ui.theme.MyTerminalTheme
import com.moveagency.shared.myterminal.ui.theme.MyTerminalTheme.colors
import com.moveagency.shared.myterminal.ui.theme.MyTerminalTheme.typography
import com.moveagency.shared.myterminal.ui.theme.Spacing

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
) = BasicTextField(
    value = value,
    onValueChange = onValueChange,
    textStyle = typography.bodySmall.copy(color = colors.primaryGray),
    keyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.Sentences,
        imeAction = ImeAction.Search,
    ),
    singleLine = true,
    cursorBrush = SolidColor(colors.primaryGray),
) { innerTextField ->
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(colors.secondaryGray)
            .padding(vertical = Spacing.x0_5, horizontal = Spacing.x1),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.weight(1F),
        ) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    color = colors.primaryGray,
                    style = typography.bodySmall,
                )
            }
            innerTextField()
        }
        Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = null,
            tint = colors.primaryGray,
        )
    }
}

@Composable
@Preview
private fun PreviewEmptySearchBar() = MyTerminalTheme {
    SearchBar(
        value = "",
        onValueChange = {},
        placeholder = stringResource(R.string.search_placeholder),
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
@Preview
private fun PreviewFilledSearchBar() = MyTerminalTheme {
    SearchBar(
        value = "New York",
        onValueChange = {},
        placeholder = stringResource(R.string.search_placeholder),
        modifier = Modifier.fillMaxWidth(),
    )
}
