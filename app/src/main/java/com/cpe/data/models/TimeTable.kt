package com.cpe.data.models

import com.google.firebase.firestore.DocumentId

data class TimeTable(
    @DocumentId
    val documentId: String? = null,
    val level: String? = null,
    val classes: List<Course>? = null
)
