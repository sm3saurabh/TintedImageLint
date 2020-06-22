package com.tintedimagelint.lint

import com.android.SdkConstants
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Element

class ImageViewUsageDetector : Detector(), XmlScanner {

    override fun getApplicableElements(): Collection<String>? {
        return listOf(SdkConstants.IMAGE_VIEW)
    }

    override fun visitElement(context: XmlContext, element: Element) {
        // Report this error on every image view visited.
        context.report(
            issue = ISSUE,
            location = context.getElementLocation(element),
            message = REPORT_MESSAGE,
            quickfixData = computeQuickFix()
        )
    }

    /**
     * Create a lint fix that replaces ImageView instance with our Custom TintedImageView instance
     */
    private fun computeQuickFix(): LintFix {
        return LintFix.create()
            .replace().text(SdkConstants.IMAGE_VIEW)
            .with(TINTED_IMAGE_VIEW)
            .build()
    }

    companion object {

        private const val REPORT_MESSAGE = "Do not use image view directly"

        private const val TINTED_IMAGE_VIEW = "com.tintedimagelint.TintedImageView"

        private val IMPLEMENTATION = Implementation(ImageViewUsageDetector::class.java, Scope.RESOURCE_FILE_SCOPE)

        val ISSUE = Issue.create(
            id = "OurImageViewIssueId",
            briefDescription = "Use Tinted image view instead of a normal image view",
            explanation = "We have a business requirement, that we have to have a orange tint over every image, " +
                    "to avoid any code duplication, you are suggested to use our custom image view instead",
            category = Category.CORRECTNESS,
            priority = 10, // Priority is an int between 1 to 10 with 10 being most severe and 1 being least severe
            severity = Severity.FATAL,
            implementation = IMPLEMENTATION
        )
    }

}