package andrefigas.com.github.wpp.ui.theme

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

fun bubbleShape(
    myMessage: Boolean, round: Dp = 16.dp,
    indicatorSize: Dp = 22.dp, indicatorRound: Dp = 1.dp
): GenericShape {

    return GenericShape { size: Size, layoutDirection: LayoutDirection ->
        val shapeHandler = when (layoutDirection) {
            LayoutDirection.Rtl -> if (myMessage) leftBubbleShape(
                round,
                indicatorSize,
                indicatorRound
            ) else rightBubbleShape(round, indicatorSize, indicatorRound)
            LayoutDirection.Ltr -> if (myMessage) rightBubbleShape(
                round,
                indicatorSize,
                indicatorRound
            ) else leftBubbleShape(round, indicatorSize, indicatorRound)
        }
        shapeHandler(size, layoutDirection)
    }
}

private fun leftBubbleShape(
    round: Dp = 16.dp,
    indicatorSize: Dp = 22.dp, indicatorRound: Dp = 1.dp
): Path.(Size, LayoutDirection) -> Unit = { size, _ ->

    moveTo(indicatorRound.value, 0f)

    lineTo(size.width - round.value, 0f)

    quadraticBezierTo(size.width, 0f, size.width, round.value)

    lineTo(size.width, size.height - round.value)

    quadraticBezierTo(size.width, size.height, size.width - round.value, size.height)

    lineTo(round.value + indicatorSize.value, size.height)

    quadraticBezierTo(
        indicatorSize.value,
        size.height,
        indicatorSize.value,
        size.height - round.value
    )

    lineTo(indicatorSize.value, indicatorSize.value)

    lineTo(0f, indicatorRound.value)

    quadraticBezierTo(0f, 0f, indicatorRound.value, 0f)

}

private fun rightBubbleShape(
    round: Dp = 16.dp,
    indicatorSize: Dp = 22.dp, indicatorRound: Dp = 1.dp
): Path.(Size, LayoutDirection) -> Unit = { size, _ ->
    moveTo(0f, round.value)
    quadraticBezierTo(0f, 0f, round.value, 0f)
    lineTo(size.width - indicatorRound.value, 0f)
    quadraticBezierTo(size.width, 0f, size.width, indicatorRound.value)
    lineTo(size.width - indicatorSize.value, indicatorSize.value)
    lineTo(size.width - indicatorSize.value, size.height - round.value)
    quadraticBezierTo(
        size.width - indicatorSize.value,
        size.height,
        size.width - indicatorSize.value - round.value,
        size.height
    )
    lineTo(round.value, size.height)
    quadraticBezierTo(0f, size.height, 0f, size.height - round.value)
    lineTo(0f, round.value)

}

