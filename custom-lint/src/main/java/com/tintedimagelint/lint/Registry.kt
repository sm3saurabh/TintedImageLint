package com.tintedimagelint.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

class Registry: IssueRegistry() {

    override val issues: List<Issue>
        get() = listOf(ImageViewUsageDetector.ISSUE)

}