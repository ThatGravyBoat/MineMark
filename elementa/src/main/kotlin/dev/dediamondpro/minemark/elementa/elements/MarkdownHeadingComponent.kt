package dev.dediamondpro.minemark.elementa.elements

import dev.dediamondpro.minemark.LayoutData
import dev.dediamondpro.minemark.elementa.LayoutConfigImpl
import dev.dediamondpro.minemark.elementa.RenderData
import dev.dediamondpro.minemark.elements.Element
import dev.dediamondpro.minemark.elements.impl.LinkElement
import dev.dediamondpro.minemark.elements.impl.formatting.HeadingElement
import gg.essential.elementa.components.UIBlock
import org.xml.sax.Attributes
import java.awt.Color

class MarkdownHeadingComponent(
    layoutConfig: LayoutConfigImpl,
    parent: Element<LayoutConfigImpl, RenderData>?,
    qName: String, attributes: Attributes?
) : HeadingElement<LayoutConfigImpl, RenderData>(layoutConfig, parent, qName, attributes) {
    private var position: LayoutData.MarkDownLine? = null

    override fun generateLayout(layoutData: LayoutData) {
        super.generateLayout(layoutData)
        if (headingType == HeadingType.H1 || headingType == HeadingType.H2) {
            layoutData.lineHeight = 2f
            position = layoutData.currentLine
            layoutData.addX(layoutData.maxWidth)
            layoutData.nextLine()
        }
    }

    override fun draw(xOffset: Float, yOffset: Float, mouseX: Float, mouseY: Float, renderData: RenderData) {
        super.draw(xOffset, yOffset, mouseX, mouseY, renderData)
        val y = position?.y ?: return
        if (headingType == HeadingType.H1 || headingType == HeadingType.H2) {
            UIBlock.drawBlockSized(
                renderData.matrixStack,
                Color.WHITE,
                xOffset.toDouble(),
                (y + yOffset).toDouble(),
                position!!.width.toDouble(),
                2.0
            )
        }
    }
}