package com.pixabay.challenge.contract

import com.pixabay.challenge.ui.model.ImageUiModel

data class ImageUiEvent(
    val onSearchImages: (String) -> (Unit),
    val retry: () -> (Unit),
    val showDialog: (Boolean, ImageUiModel?) -> (Unit)
)