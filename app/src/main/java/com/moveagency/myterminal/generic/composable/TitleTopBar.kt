@file:OptIn(ExperimentalMaterial3Api::class)
package com.moveagency.myterminal.generic.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.moveagency.myterminal.R
import com.moveagency.myterminal.ui.theme.MyTerminalTheme
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.colors
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.typography
import com.moveagency.myterminal.ui.theme.Spacing
import java.time.*

@Composable
fun TitleTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onDateChange: ((LocalDate) -> Unit)? = null,
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .background(colors.primaryGray)
                .statusBarsPadding()
                .padding(top = Spacing.x4, bottom = Spacing.x2)
                .padding(horizontal = Spacing.x3),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1F),
                color = colors.white,
                style = typography.h1,
            )
            onDateChange?.let {
                Icon(
                    painter = painterResource(R.drawable.ic_date_range),
                    contentDescription = null,
                    modifier = Modifier.clickable { showDatePicker = true },
                    tint = colors.white,
                )
            }
        }
    }

    AnimatedVisibility(
        visible = showDatePicker,
    ) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis
                            ?.let(::millisToLocalDate)
                            ?.let { onDateChange?.invoke(it) }
                        showDatePicker = false
                    },
                    content = {
                        Text(
                            text = stringResource(R.string.generic_confirm),
                            color = colors.white,
                        )
                    }
                )
            },
            content = {
                DatePicker(
                    state = datePickerState,
                    colors = DatePickerDefaults.colors(
                        containerColor = colors.primaryGray,
                        titleContentColor = colors.white,
                        headlineContentColor = colors.white,
                        navigationContentColor = colors.white,
                        weekdayContentColor = colors.white,
                        yearContentColor = colors.white,
                        dayContentColor = colors.white,
                        todayContentColor = colors.white,
                        todayDateBorderColor = colors.white,
                        selectedDayContentColor = colors.primaryGray,
                        selectedDayContainerColor = colors.white,
                        selectedYearContentColor = colors.primaryGray,
                        selectedYearContainerColor = colors.white,
                        dividerColor = colors.white,
                    ),
                )
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false },
                    content = {
                        Text(
                            text = stringResource(R.string.generic_cancel),
                            color = colors.white,
                        )
                    },
                )
            },
            colors = DatePickerDefaults.colors(containerColor = colors.primaryGray),
        )
    }
}

private fun millisToLocalDate(millis: Long): LocalDate {
    return Instant.ofEpochMilli(millis).atZone(ZoneOffset.systemDefault()).toLocalDate()
}

@Composable
@Preview
private fun PreviewTitleTopBar() = MyTerminalTheme {
    TitleTopBar(
        title = stringResource(R.string.home_title),
        onDateChange = {},
        modifier = Modifier.fillMaxWidth(),
    )
}
