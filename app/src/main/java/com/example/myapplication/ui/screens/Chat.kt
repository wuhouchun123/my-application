package com.example.myapplication.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue
import com.example.myapplication.R
import org.w3c.dom.Text

@Composable
fun Chat(
    modifier: Modifier = Modifier
) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
             Text("瑞青是臭驴12345")
            Message(
                onAuthorClick = {  },
//                msg = "瑞青是臭驴",
                isUserMe = true,
                isFirstMessageByAuthor = true,
                isLastMessageByAuthor = true
            )
        }
        UserInput(
            // let this element handle the padding so that the elevation is shown behind the
            // navigation bar
            modifier = Modifier
                .navigationBarsPadding()
                .imePadding()
        )
    }
}

@Composable
fun Message(
    onAuthorClick: (String) -> Unit,
//    msg: Message,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean
) {
    val borderColor = if (isUserMe) {
        //MaterialTheme.colorScheme.primary
        Color.Blue
    } else {
        //MaterialTheme.colorScheme.tertiary
        Color.Yellow
    }

    val spaceBetweenAuthors = if (isLastMessageByAuthor) Modifier.padding(top = 8.dp) else Modifier
    Row(modifier = spaceBetweenAuthors) {
        if (isLastMessageByAuthor) {
            // Avatar
            Image(
                modifier = Modifier
                    .clickable(onClick = {  })
                    .padding(horizontal = 16.dp)
                    .size(42.dp)
                    .border(1.5.dp, borderColor, CircleShape)
                    .border(3.dp, Color.Blue, CircleShape)
                    .clip(CircleShape)
                    .align(Alignment.Top),
                painter = painterResource(id = R.drawable.music_knob),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        } else {
            // Space under avatar
            Spacer(modifier = Modifier.width(74.dp))
        }
        AuthorAndTextMessage(
//            msg = msg,
            isUserMe = isUserMe,
            isFirstMessageByAuthor = isFirstMessageByAuthor,
            isLastMessageByAuthor = isLastMessageByAuthor,
            authorClicked = onAuthorClick,
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)
        )
    }
}

@Composable
fun AuthorAndTextMessage(
//    msg: Message,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean,
    authorClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
//        if (isLastMessageByAuthor) {
//            AuthorNameTimestamp(msg)
//        }
        ChatItemBubble(isUserMe, authorClicked = authorClicked)
        if (isFirstMessageByAuthor) {
            // Last bubble before next author
            Spacer(modifier = Modifier.height(8.dp))
        } else {
            // Between bubbles
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun ChatItemBubble(
//    message: Message,
    isUserMe: Boolean,
    authorClicked: (String) -> Unit
) {

    val backgroundBubbleColor = if (isUserMe) {
//        MaterialTheme.colorScheme.primary
        Color.Green
    } else {
//        MaterialTheme.colorScheme.surfaceVariant
        Color.Red
    }

    Column {
        Surface(
            color = backgroundBubbleColor,
            shape = ChatBubbleShape
        ) {
            ClickableMessage(
//                message = message,
                isUserMe = isUserMe,
                authorClicked = authorClicked
            )
        }

//        message.image?.let {
//            Spacer(modifier = Modifier.height(4.dp))
//            Surface(
//                color = backgroundBubbleColor,
//                shape = ChatBubbleShape
//            ) {
//                Image(
//                    painter = painterResource(it),
//                    contentScale = ContentScale.Fit,
//                    modifier = Modifier.size(160.dp),
//                    contentDescription = stringResource(id = R.string.attached_image)
//                )
//            }
//        }
    }
}

@Composable
fun ClickableMessage(
//    message: Message,
    isUserMe: Boolean,
    authorClicked: (String) -> Unit
) {
    val uriHandler = LocalUriHandler.current

//    val styledMessage = messageFormatter(
//        text = message.content,
//        primary = isUserMe
//    )

    Text(text = "瑞青是个大笨蛋～~123", modifier = Modifier.padding(16.dp))

//    ClickableText(
//        text = styledMessage,
//        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
//        modifier = Modifier.padding(16.dp),
//        onClick = {
//            styledMessage
//                .getStringAnnotations(start = it, end = it)
//                .firstOrNull()
//                ?.let { annotation ->
//                    when (annotation.tag) {
//                        SymbolAnnotationType.LINK.name -> uriHandler.openUri(annotation.item)
//                        SymbolAnnotationType.PERSON.name -> authorClicked(annotation.item)
//                        else -> Unit
//                    }
//                }
//        }
//    )
}

private val ChatBubbleShape = RoundedCornerShape(5.dp, 20.dp, 20.dp, 20.dp)

enum class InputSelector {
    NONE,
    MAP,
    DM,
    EMOJI,
    PHONE,
    PICTURE
}

@Composable
fun UserInput(
    modifier: Modifier = Modifier
) {
    var currentInputSelector by rememberSaveable { mutableStateOf(InputSelector.NONE) }
    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {  //ok, search: rememberSaveable
        mutableStateOf(TextFieldValue())
    }
    val dismissKeyboard = { currentInputSelector = InputSelector.NONE }
    // 如果 InputSelector 可见，则拦截返回导航
    if (currentInputSelector != InputSelector.NONE) {  //ok
        BackHandler(onBack = dismissKeyboard)  //ok
    }
    // 用于决定是否显示键盘
    var textFieldFocusState by remember { mutableStateOf(false) }

    Surface() {
        Column() {
            userInputText(
                textFieldValue = textState,
                onTextChanged = { textState = it },
                focusState = textFieldFocusState,
                onTextFieldFocused = { focused ->
                    if (focused) {
                        //currentInputSelector = InputSelector.NONE
                    }
                    textFieldFocusState = focused
                },
                keyboardShown = textFieldFocusState,
            )
            UserInputSelector(
                onSelectorChange = { currentInputSelector = it },
                sendMessageEnabled = textState.text.isNotBlank(),
                onMessageSent = {
//                    onMessageSent(textState.text)
                    // Reset text field and close keyboard
                    textState = TextFieldValue()
                    // Move scroll to bottom
                    dismissKeyboard()
                },
                currentInputSelector = currentInputSelector
            )
            SelectorExpanded(
                onCloseRequested = dismissKeyboard,
                onTextAdded = {  },
                currentSelector = currentInputSelector
            )
        }
    }
}

val KeyboardShownKey = SemanticsPropertyKey<Boolean>("KeyboardShownKey")  // ???
var SemanticsPropertyReceiver.keyboardShownProperty by KeyboardShownKey //???

@Composable
fun userInputText(
    onTextChanged: (TextFieldValue) -> Unit,
    textFieldValue: TextFieldValue,
    focusState: Boolean,
    keyboardShown: Boolean,
    onTextFieldFocused: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .semantics {
                contentDescription = "11111111"
                keyboardShownProperty = keyboardShown
            },
        horizontalArrangement = Arrangement.End
    ) {
        Surface {
            Box(
                modifier = Modifier
                    .height(64.dp)
                    .weight(1f)
                    .align(Alignment.Bottom)
            ) {
                var lastFocusState by remember { mutableStateOf(false) }
                BasicTextField(
                    value = textFieldValue,
                    onValueChange = { onTextChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp)
                        .align(Alignment.CenterStart)
                        .onFocusChanged { state ->
                            if (lastFocusState != state.isFocused) {
                                onTextFieldFocused(state.isFocused)
                            }
                            lastFocusState = state.isFocused
                        },
                    maxLines = 1
                )
                val disableContentColor = Color.Gray
                if (textFieldValue.text.isEmpty() && !focusState) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 32.dp),
                        text = stringResource(id = R.string.textfield_hint),
                        color = Color(0x33222222)
                    )
                }
            }
        }
    }
}

@Composable
private fun UserInputSelector(
    onSelectorChange: (InputSelector) -> Unit,
    sendMessageEnabled: Boolean,
    onMessageSent: () -> Unit,
    currentInputSelector: InputSelector,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(72.dp)
            .wrapContentHeight()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InputSelectorButton(
            onClick = { onSelectorChange(InputSelector.EMOJI) },
            icon = Icons.Outlined.Face,
            selected = currentInputSelector == InputSelector.EMOJI,
            description = stringResource(id = R.string.emoji_selector_bt_desc)
        )
        InputSelectorButton(
            onClick = { onSelectorChange(InputSelector.DM) },
            icon = Icons.Outlined.Build,
            selected = currentInputSelector == InputSelector.DM,
            description = stringResource(id = R.string.dm_desc)
        )
        InputSelectorButton(
            onClick = { onSelectorChange(InputSelector.PICTURE) },
            icon = Icons.Outlined.Clear,
            selected = currentInputSelector == InputSelector.PICTURE,
            description = stringResource(id = R.string.attach_photo_desc)
        )
        InputSelectorButton(
            onClick = { onSelectorChange(InputSelector.MAP) },
            icon = Icons.Outlined.Place,
            selected = currentInputSelector == InputSelector.MAP,
            description = stringResource(id = R.string.map_selector_desc)
        )
        InputSelectorButton(
            onClick = { onSelectorChange(InputSelector.PHONE) },
            icon = Icons.Outlined.AccountBox,
            selected = currentInputSelector == InputSelector.PHONE,
            description = stringResource(id = R.string.videochat_desc)
        )

        val border = if (!sendMessageEnabled) {
            BorderStroke(
                width = 0.dp,
                color = Color(0x00FFFFFF)
            )
        } else {
            null
        }
        Spacer(modifier = Modifier.weight(1f))

        val disabledContentColor = Color(0x33000000)

        val buttonColors = ButtonDefaults.buttonColors(
            disabledContentColor = disabledContentColor
        )

        // Send button
        Button(
            modifier = Modifier.height(24.dp),
            enabled = sendMessageEnabled,
            onClick = onMessageSent,
            colors = buttonColors,
            border = border,
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                stringResource(id = R.string.send),
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colors.background
            )
        }
    }
}
@Composable
private fun InputSelectorButton(
    onClick: () -> Unit,
    icon: ImageVector,
    description: String,
    selected: Boolean
) {
//    val backgroundModifier = if (selected) {
//        Modifier.background(
//            color = Color.Green,
//            shape = RoundedCornerShape(10.dp)
//        )
//    } else {
//        Modifier
//    }
    IconButton(
        onClick = onClick,
        modifier = Modifier  //.then(backgroundModifier)

    ) {
        val tint = if (selected) {
            Color.Blue
        } else {
            Color.Gray
        }
        Icon(
            icon,
            tint = tint,
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp),
            contentDescription = description
        )
    }
}

@Composable
private fun SelectorExpanded(
    currentSelector: InputSelector,
    onCloseRequested: () -> Unit,
    onTextAdded: (String) -> Unit
) {
    if (currentSelector == InputSelector.NONE) return

    // Request focus to force the TextField to lose it
    val focusRequester = FocusRequester()
    // If the selector is shown, always request focus to trigger a TextField.onFocusChange.
    SideEffect {
        if (currentSelector == InputSelector.EMOJI) {
            focusRequester.requestFocus()
        }
    }

    Surface() {
        when (currentSelector) {
            InputSelector.EMOJI -> EmojiTable(focusRequester = focusRequester)
            InputSelector.DM -> Text("这是DM")
            InputSelector.PICTURE -> Text("这是图片")
            InputSelector.MAP -> Text("这是FaceTime")
            InputSelector.PHONE -> Text("这是手机联系人")
            else -> { throw NotImplementedError() }
        }
    }
}

@Composable
fun EmojiTable(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester
) {
    val a11yLabel = stringResource(id = R.string.emoji_selector_desc)
    Column(
        modifier
            .fillMaxWidth()
            .focusRequester(focusRequester) // Requests focus when the Emoji selector is displayed
            // Make the emoji selector focusable so it can steal focus from TextField
            .focusTarget()
            .semantics { contentDescription = a11yLabel }
    ) {
        repeat(4) { x ->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(EMOJI_COLUMNS) { y ->
                    val emoji = emojis[x * EMOJI_COLUMNS + y]
                    Text(
                        modifier = Modifier
                            .clickable(onClick = { })
                            .sizeIn(minWidth = 20.dp, minHeight = 42.dp)
                            .padding(2.dp),
                        text = emoji,
                        style = LocalTextStyle.current.copy(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

private const val EMOJI_COLUMNS = 10

private val emojis = listOf(
    "\ud83d\ude00", // Grinning Face
    "\ud83d\ude01", // Grinning Face With Smiling Eyes
    "\ud83d\ude02", // Face With Tears of Joy
    "\ud83d\ude03", // Smiling Face With Open Mouth
    "\ud83d\ude04", // Smiling Face With Open Mouth and Smiling Eyes
    "\ud83d\ude05", // Smiling Face With Open Mouth and Cold Sweat
    "\ud83d\ude06", // Smiling Face With Open Mouth and Tightly-Closed Eyes
    "\ud83d\ude09", // Winking Face
    "\ud83d\ude0a", // Smiling Face With Smiling Eyes
    "\ud83d\ude0b", // Face Savouring Delicious Food
    "\ud83d\ude0e", // Smiling Face With Sunglasses
    "\ud83d\ude0d", // Smiling Face With Heart-Shaped Eyes
    "\ud83d\ude18", // Face Throwing a Kiss
    "\ud83d\ude17", // Kissing Face
    "\ud83d\ude19", // Kissing Face With Smiling Eyes
    "\ud83d\ude1a", // Kissing Face With Closed Eyes
    "\u263a", // White Smiling Face
    "\ud83d\ude42", // Slightly Smiling Face
    "\ud83e\udd17", // Hugging Face
    "\ud83d\ude07", // Smiling Face With Halo
    "\ud83e\udd13", // Nerd Face
    "\ud83e\udd14", // Thinking Face
    "\ud83d\ude10", // Neutral Face
    "\ud83d\ude11", // Expressionless Face
    "\ud83d\ude36", // Face Without Mouth
    "\ud83d\ude44", // Face With Rolling Eyes
    "\ud83d\ude0f", // Smirking Face
    "\ud83d\ude23", // Persevering Face
    "\ud83d\ude25", // Disappointed but Relieved Face
    "\ud83d\ude2e", // Face With Open Mouth
    "\ud83e\udd10", // Zipper-Mouth Face
    "\ud83d\ude2f", // Hushed Face
    "\ud83d\ude2a", // Sleepy Face
    "\ud83d\ude2b", // Tired Face
    "\ud83d\ude34", // Sleeping Face
    "\ud83d\ude0c", // Relieved Face
    "\ud83d\ude1b", // Face With Stuck-Out Tongue
    "\ud83d\ude1c", // Face With Stuck-Out Tongue and Winking Eye
    "\ud83d\ude1d", // Face With Stuck-Out Tongue and Tightly-Closed Eyes
    "\ud83d\ude12", // Unamused Face
    "\ud83d\ude13", // Face With Cold Sweat
    "\ud83d\ude14", // Pensive Face
    "\ud83d\ude15", // Confused Face
    "\ud83d\ude43", // Upside-Down Face
    "\ud83e\udd11", // Money-Mouth Face
    "\ud83d\ude32", // Astonished Face
    "\ud83d\ude37", // Face With Medical Mask
    "\ud83e\udd12", // Face With Thermometer
    "\ud83e\udd15", // Face With Head-Bandage
    "\u2639", // White Frowning Face
    "\ud83d\ude41", // Slightly Frowning Face
    "\ud83d\ude16", // Confounded Face
    "\ud83d\ude1e", // Disappointed Face
    "\ud83d\ude1f", // Worried Face
    "\ud83d\ude24", // Face With Look of Triumph
    "\ud83d\ude22", // Crying Face
    "\ud83d\ude2d", // Loudly Crying Face
    "\ud83d\ude26", // Frowning Face With Open Mouth
    "\ud83d\ude27", // Anguished Face
    "\ud83d\ude28", // Fearful Face
    "\ud83d\ude29", // Weary Face
    "\ud83d\ude2c", // Grimacing Face
    "\ud83d\ude30", // Face With Open Mouth and Cold Sweat
    "\ud83d\ude31", // Face Screaming in Fear
    "\ud83d\ude33", // Flushed Face
    "\ud83d\ude35", // Dizzy Face
    "\ud83d\ude21", // Pouting Face
    "\ud83d\ude20", // Angry Face
    "\ud83d\ude08", // Smiling Face With Horns
    "\ud83d\udc7f", // Imp
    "\ud83d\udc79", // Japanese Ogre
    "\ud83d\udc7a", // Japanese Goblin
    "\ud83d\udc80", // Skull
    "\ud83d\udc7b", // Ghost
    "\ud83d\udc7d", // Extraterrestrial Alien
    "\ud83e\udd16", // Robot Face
    "\ud83d\udca9", // Pile of Poo
    "\ud83d\ude3a", // Smiling Cat Face With Open Mouth
    "\ud83d\ude38", // Grinning Cat Face With Smiling Eyes
    "\ud83d\ude39", // Cat Face With Tears of Joy
    "\ud83d\ude3b", // Smiling Cat Face With Heart-Shaped Eyes
    "\ud83d\ude3c", // Cat Face With Wry Smile
    "\ud83d\ude3d", // Kissing Cat Face With Closed Eyes
    "\ud83d\ude40", // Weary Cat Face
    "\ud83d\ude3f", // Crying Cat Face
    "\ud83d\ude3e", // Pouting Cat Face
    "\ud83d\udc66", // Boy
    "\ud83d\udc67", // Girl
    "\ud83d\udc68", // Man
    "\ud83d\udc69", // Woman
    "\ud83d\udc74", // Older Man
    "\ud83d\udc75", // Older Woman
    "\ud83d\udc76", // Baby
    "\ud83d\udc71", // Person With Blond Hair
    "\ud83d\udc6e", // Police Officer
    "\ud83d\udc72", // Man With Gua Pi Mao
    "\ud83d\udc73", // Man With Turban
    "\ud83d\udc77", // Construction Worker
    "\u26d1", // Helmet With White Cross
    "\ud83d\udc78", // Princess
    "\ud83d\udc82", // Guardsman
    "\ud83d\udd75", // Sleuth or Spy
    "\ud83c\udf85", // Father Christmas
    "\ud83d\udc70", // Bride With Veil
    "\ud83d\udc7c", // Baby Angel
    "\ud83d\udc86", // Face Massage
    "\ud83d\udc87", // Haircut
    "\ud83d\ude4d", // Person Frowning
    "\ud83d\ude4e", // Person With Pouting Face
    "\ud83d\ude45", // Face With No Good Gesture
    "\ud83d\ude46", // Face With OK Gesture
    "\ud83d\udc81", // Information Desk Person
    "\ud83d\ude4b", // Happy Person Raising One Hand
    "\ud83d\ude47", // Person Bowing Deeply
    "\ud83d\ude4c", // Person Raising Both Hands in Celebration
    "\ud83d\ude4f", // Person With Folded Hands
    "\ud83d\udde3", // Speaking Head in Silhouette
    "\ud83d\udc64", // Bust in Silhouette
    "\ud83d\udc65", // Busts in Silhouette
    "\ud83d\udeb6", // Pedestrian
    "\ud83c\udfc3", // Runner
    "\ud83d\udc6f", // Woman With Bunny Ears
    "\ud83d\udc83", // Dancer
    "\ud83d\udd74", // Man in Business Suit Levitating
    "\ud83d\udc6b", // Man and Woman Holding Hands
    "\ud83d\udc6c", // Two Men Holding Hands
    "\ud83d\udc6d", // Two Women Holding Hands
    "\ud83d\udc8f" // Kiss
)