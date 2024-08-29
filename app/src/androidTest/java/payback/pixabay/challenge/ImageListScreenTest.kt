package payback.pixabay.challenge

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import payback.pixabay.challenge.ui.model.ImageUiModel
import payback.pixabay.challenge.ui.theme.PaybackChallengeTheme
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import payback.pixabay.challenge.ui.imagelist.ImageCollectionView
import payback.pixabay.challenge.ui.imagelist.ImageThumbnailEntry
import payback.pixabay.challenge.utils.TestTag

@ExperimentalLayoutApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@RunWith(AndroidJUnit4::class)
class ImageListScreenTest {

    private val sampleImage1 = ImageUiModel(
        id = 1,
        pageURL = "https://example.com",
        type = "photo",
        tags = listOf("nature", "landscape"),
        previewURL = "https://example.com/preview",
        webFormatURL = "https://example.com/webformat",
        largeImageURL = "https://example.com/large",
        downloads = "50",
        likes = "10",
        comments = "5",
        userId = 123,
        user = "parisa",
        userImageURL = "https://example.com/user"
    )

    private val sampleImage2 = ImageUiModel(
        id = 2,
        pageURL = "https://example.com",
        type = "photo",
        tags = listOf("nature", "landscape"),
        previewURL = "https://example.com/preview",
        webFormatURL = "https://example.com/webformat",
        largeImageURL = "https://example.com/large",
        downloads = "50",
        likes = "10",
        comments = "5",
        userId = 12322,
        user = "mohammad",
        userImageURL = "https://example.com/user"
    )

    private val sampleImage3 = ImageUiModel(
        id = 3,
        pageURL = "https://example.com",
        type = "photo",
        tags = listOf("nature", "landscape"),
        previewURL = "https://example.com/preview",
        webFormatURL = "https://example.com/webformat",
        largeImageURL = "https://example.com/large",
        downloads = "50",
        likes = "10",
        comments = "5",
        userId = 123,
        user = "reza",
        userImageURL = "https://example.com/user"
    )

    private val sampleImage4 = ImageUiModel(
        id = 4,
        pageURL = "https://example.com",
        type = "photo",
        tags = listOf("nature", "landscape"),
        previewURL = "https://example.com/preview",
        webFormatURL = "https://example.com/webformat",
        largeImageURL = "https://example.com/large",
        downloads = "50",
        likes = "10",
        comments = "5",
        userId = 123,
        user = "ali",
        userImageURL = "https://example.com/user"
    )

    private val imageList = listOf(sampleImage1, sampleImage2, sampleImage3, sampleImage4)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun verifyNoDataMessageWhenImageListIsEmpty() {
        composeTestRule.apply {
            displayImageList(emptyList())
            onNodeWithTag(TestTag.IMAGE_COLLECTION_TAG)
                .onChildren()
                .assertCountEquals(0)
            onAllNodes(hasText(activity.getString(R.string.error_title)) and hasText(activity.getString(R.string.no_data_available)))
        }
    }

    @Test
    fun verifyImageListDisplaysItemsWhenListIsNotEmpty() {
        composeTestRule.apply {
            displayImageList(imageList)
            onNodeWithTag(TestTag.IMAGE_COLLECTION_TAG)
                .onChildren()
                .assertCountEquals(4)
        }
    }

    @Test
    fun verifyImageItemDisplaysUserName() {
        composeTestRule.apply {
            displayImageList(imageList)
            imageList.forEachIndexed { index, image ->
                onNodeWithTag(TestTag.IMAGE_COLLECTION_TAG)
                    .onChildren()[index]
                    .assert(hasText(image.user))
            }
        }
    }

    @Test
    fun verifyImageItemClickAction() {
        val firstImage = imageList.first()
        composeTestRule.apply {
            displayImageItem(firstImage)
            onNodeWithTag(TestTag.IMAGE_ITEM_TAG)
                .assert(hasClickAction())
                .assert(hasText(firstImage.user))
                .performClick()
        }
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.displayImageItem(
        image: ImageUiModel
    ) {
        activity.runOnUiThread {
            activity.setContent { PaybackChallengeTheme { ImageThumbnailEntry(image) {} } }
        }
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.displayImageList(
        images: List<ImageUiModel>
    ) {
        activity.runOnUiThread {
            activity.setContent { PaybackChallengeTheme { ImageCollectionView(Modifier, images) {} } }
        }
    }
}

