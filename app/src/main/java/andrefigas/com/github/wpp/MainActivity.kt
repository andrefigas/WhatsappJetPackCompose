package andrefigas.com.github.wpp

import andrefigas.com.github.wpp.ui.theme.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Scaffold(bottomBar = { ChatBar() },
    topBar = {TopChatBar()}) {
        WppTheme {
            Image(
                painter = painterResource(id = R.drawable.chat_background),
                contentDescription = "background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            LazyColumn(modifier = Modifier.padding(bottom = 55.dp)) {
                items(items = Message.provideData(),
                    itemContent = { msg ->
                        BubbleChat(myMessage = msg.myMessage, message = msg.content)
                    }, key = { it.id })
            }
        }
    }

}

@Composable
fun TopChatBar() {
    ConstraintLayout(modifier = Modifier
        .height(55.dp)
        .background(baseBlack)
        .fillMaxWidth()) {

        val (back, avatar, name, video, call, more) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
            contentDescription = "back",
        modifier = Modifier.size(45.dp).padding(10.dp).constrainAs(back){
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        })

        Image(
            painter = painterResource(id = R.drawable.barney),
            contentDescription = "profile",
            modifier = Modifier.width(40.dp).constrainAs(avatar){
                start.linkTo(back.end, 5.dp)
                top.linkTo(parent.top, 8.dp)
                bottom.linkTo(parent.bottom, 8.dp)
                height = Dimension.fillToConstraints
            }.clip(CircleShape))

        Image(
            painter = painterResource(id = R.drawable.ic_baseline_more_vert_24),
            contentDescription = "more",
            modifier = Modifier.size(45.dp).padding(10.dp).constrainAs(more){
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            })

        Image(
            painter = painterResource(id = R.drawable.ic_baseline_call_24),
            contentDescription = "call",
            modifier = Modifier.size(45.dp).padding(10.dp).constrainAs(call){
                end.linkTo(more.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            })

        Image(
            painter = painterResource(id = R.drawable.ic_baseline_videocam_24),
            contentDescription = "call",
            modifier = Modifier.size(45.dp).padding(10.dp).constrainAs(video){
                end.linkTo(call.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            })


        Text(
            text = "Barney",
            color = Color.White,
            modifier = Modifier.constrainAs(name){
                start.linkTo(avatar.end, 8.dp)
                end.linkTo(video.start, 8.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            },
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

    }
}



@Composable
fun ChatBar() {

    ConstraintLayout(modifier = Modifier
        .height(55.dp)
        .padding(5.dp)
        .fillMaxWidth()) {
        val (fab, optionsContainer, emotion, attachment, camera, message) = createRefs()

        Box(modifier = Modifier
            .constrainAs(fab) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            .size(45.dp)
            .background(fabColor, CircleShape)){
            Image(painter = painterResource(id = R.drawable.ic_baseline_keyboard_voice_24),
                contentDescription = "Record audio", modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.Center))
        }

        Box(modifier = Modifier
            .constrainAs(optionsContainer) {
                start.linkTo(parent.start)
                end.linkTo(fab.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
            .padding(end = 5.dp, top = 1.dp, bottom = 1.dp)
            .width(50.dp)
            .fillMaxHeight()
            .background(baseBlack, RoundedCornerShape(25.dp))
        ) {
        }

        Image(painter = painterResource(id = R.drawable.ic_baseline_camera_alt_24),
            contentDescription = "Open camera",
            modifier = Modifier
                .size(42.dp)
                .padding(10.dp)
                .constrainAs(camera) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(optionsContainer.end, 6.dp)
                }
        )

        Image(painter = painterResource(id = R.drawable.ic_baseline_attach_file_24),
            contentDescription = "Send attachment",
            modifier = Modifier
                .size(42.dp)
                .padding(10.dp)
                .constrainAs(attachment) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(camera.start, 3.dp)
                }
        )

        Image(painter = painterResource(id = R.drawable.ic_baseline_insert_emoticon_24),
            contentDescription = "Send emotion",
            modifier = Modifier
                .size(42.dp)
                .padding(10.dp)
                .constrainAs(emotion) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(optionsContainer.start, 6.dp)
                }
        )

        Text(
            text = "Message",
            color = inputTextColor,
            modifier = Modifier.constrainAs(message){
                start.linkTo(emotion.end, 5.dp)
                end.linkTo(attachment.start, 5.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }
        )
    }
}

@Composable
fun BubbleChat(
    myMessage: Boolean, message: String
) {
    val color = if (myMessage) ownUserBubbleChat else otherUserBubbleChat
    val bubbleShape = bubbleShape(myMessage)

    val defaultPadding = 8.dp
    val extendedPadding = 15.dp

    val anchorOutBubblePadding = 6.dp
    val anchorExtendedOutBubblePadding = 32.dp

    val bubblePaddingEnd: Dp = if (myMessage) extendedPadding else defaultPadding
    val bubblePaddingStart: Dp = if (myMessage) defaultPadding else extendedPadding
    val outBubblePaddingStart =
        if (myMessage) anchorExtendedOutBubblePadding else anchorOutBubblePadding
    val outBubblePaddingEnd =
        if (myMessage) anchorOutBubblePadding else anchorExtendedOutBubblePadding

    Box(
        modifier = Modifier.padding(
            top = 5.dp,
            bottom = 5.dp,
            start = outBubblePaddingStart,
            end = outBubblePaddingEnd
        )
    ) {
        Box(
            Modifier
                .background(bubbleChatShadow, bubbleShape)
                .clip(bubbleShape)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(1.dp)
        ) {

            Box(
                Modifier
                    .align(Alignment.Center)
                    .background(color, bubbleShape)
                    .clip(bubbleShape)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {

                Text(
                    text = message,
                    color = Color.White,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(
                        start = bubblePaddingStart,
                        end = bubblePaddingEnd,
                        top = defaultPadding,
                        bottom = defaultPadding
                    )
                )

            }

        }
    }

}