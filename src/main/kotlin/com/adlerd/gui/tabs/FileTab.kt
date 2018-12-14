package com.adlerd.gui.tabs

import com.adlerd.gui.CodePane
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import org.fxmisc.flowless.VirtualizedScrollPane
import java.io.File

class FileTab(file: File): CustomTab() {
    val codePane = CodePane()
    lateinit var icon: Image

    init {
        if (file.isFile) {
            this.text = file.name
            icon = when (file.extension) {
                "java" -> Image(
                    FileTab::class.java.getResource("/img/java_file.png").toExternalForm(),
                    ICON_SIZE,
                    ICON_SIZE,
                    true,
                    false
                )
                else -> Image(
                    FileTab::class.java.getResource("/img/folder.png").toExternalForm(),
                    ICON_SIZE,
                    ICON_SIZE,
                    true,
                    false
                )
            }

            this.graphic = ImageView(icon)
            val scrollPane: VirtualizedScrollPane<CodePane> = VirtualizedScrollPane(codePane)

            this.content = scrollPane

            // Ensure CodePane is clear
            codePane.clearText()
            // Read file into CodePane
            codePane.readFile(file)
            // Show the beginning of the file when opening
            codePane.scrollToPixel(0.0, 0.0)
        }
    }

    companion object {
        private const val ICON_SIZE = 14.0
    }
}
