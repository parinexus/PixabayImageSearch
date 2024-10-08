package com.pixabay.challenge.ui.imagelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pixabay.challenge.R
import com.pixabay.challenge.ui.components.CustomButton
import com.pixabay.challenge.ui.components.CustomImageView
import com.pixabay.challenge.ui.model.ImageUiModel

@Composable
fun ImageDetailWarningDialog(
    imageDetail: ImageUiModel,
    onPositive: () -> Unit,
    dismissDialog: () -> Unit
) {
    Dialog(
        properties = DialogProperties(), onDismissRequest = dismissDialog
    ) {
        ImageDetailWarningContent(imageDetail, onPositive, dismissDialog)
    }
}

@Composable
fun ImageDetailWarningContent(
    imageDetail: ImageUiModel,
    onPositive: () -> Unit,
    dismissDialog: () -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_small)))

            CustomImageView(
                context,
                imageDetail.previewURL,
                true,
                Modifier.size(dimensionResource(id = R.dimen.icon_size))
            )

            Text(
                text = context.getString(R.string.detail_screen_warning_dialog, imageDetail.user),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.spacing_medium)),
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_small)))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                CustomButton(
                    modifier = Modifier.wrapContentSize(),
                    text = stringResource(id = R.string.details_warning_dialog_negative_text),
                    textColor = MaterialTheme.colors.error,
                    onClick = { dismissDialog.invoke() }
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacing_tiny)))
                CustomButton(
                    modifier = Modifier.wrapContentSize(),
                    text = stringResource(id = R.string.details_warning_dialog_positive_text),
                    onClick = { onPositive.invoke() }
                )
            }
        }
    }
}