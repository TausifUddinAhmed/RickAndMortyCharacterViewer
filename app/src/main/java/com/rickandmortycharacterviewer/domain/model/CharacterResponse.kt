package com.rickandmortycharacterviewer.domain.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @field:SerializedName("next")
	val next: String? = null,

    @field:SerializedName("previous")
	val previous: String? = null,

    @field:SerializedName("count")
	val count: Int,

    @field:SerializedName("results")
	val results: List<CharacterDetails> = listOf(),
)