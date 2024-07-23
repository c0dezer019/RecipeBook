package com.codeinspace.recipebook.views.utility

internal fun lineHandler(lines: List<String>): String {
    var structured = ""
    for ((i, line) in lines.withIndex()) {
        structured = structured.plus("${i + 1}. $line\n\n")
    }

    return structured
}
