package com.rizlee.domain.entities

import com.google.gson.annotations.SerializedName

data class AphorismBody(@field:SerializedName("quoteText")
                        var text: String? = null,
                        @field:SerializedName("quoteAuthor")
                        var author: String? = null)
