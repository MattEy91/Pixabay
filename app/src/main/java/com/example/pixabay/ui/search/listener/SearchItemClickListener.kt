package com.example.pixabay.ui.search.listener

import com.example.pixabay.domain.model.Hit

interface SearchItemClickListener {
    fun onItemClicked(hit: Hit)
}