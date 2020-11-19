package io.github.iltotore.spongescala

import io.github.iltotore.scalabuilder
import org.spongepowered.api.text.action.{ClickAction, HoverAction, ShiftClickAction}
import org.spongepowered.api.text.format.{TextColor, TextFormat, TextStyle}
import org.spongepowered.api.text.{LiteralText, Text}

object text {

  class Builder(private var base: Text.Builder) extends scalabuilder.Builder[Text] {

    def children: Seq[Text] = base.getChildren.asScala.toSeq

    def append(values: Text*): Unit = base.append(values: _*)

    def clickAction: Option[ClickAction[_]] = base.getClickAction.toScala

    def clickAction_=(value: ClickAction[_]): Unit = base.onClick(value)

    def color: TextColor = base.getColor

    def color_=(value: TextColor): Unit = base.color(value)

    def format: TextFormat = base.getFormat

    def format_=(value: TextFormat): Unit = base.format(value)

    def hoverAction: Option[HoverAction[_]] = base.getHoverAction.toScala

    def hoverAction_=(value: HoverAction[_]): Unit = base.onHover(value)

    def shiftClickAction: Option[ShiftClickAction[_]] = base.getShiftClickAction.toScala

    def shiftClickAction_=(value: ShiftClickAction[_]): Unit = base.onShiftClick(value)

    def text_=(value: String): Unit = base = Text.builder(value)

    override def build: Text = base.build()
  }

  implicit class StringParser(context: StringContext) {

    def c(args: Any*): Text = {
      val strings = context.parts.iterator
      val expressions = args.iterator
      val builder = Text.builder(strings.next())
      var lastBuilder = Text.builder("")

      while (strings.hasNext) {
        expressions.next() match {
          case color: TextColor =>
            builder.append(lastBuilder.build())
            lastBuilder = Text.builder("").color(color)

          case style: TextStyle => lastBuilder.style(style)

          case other => lastBuilder = lastBuilder.content(lastBuilder.getContent + other.toString)
        }
        lastBuilder.content(lastBuilder.getContent + strings.next())
      }
      builder.append(lastBuilder.build())
      builder.build()
    }
  }

  def unapply(arg: Text): Option[(TextColor, TextStyle)] = Some(arg.getColor, arg.getStyle)

  def unapply(arg: LiteralText): Option[(String, TextColor, TextStyle)] = Some(arg.getContent, arg.getColor, arg.getStyle)
}
