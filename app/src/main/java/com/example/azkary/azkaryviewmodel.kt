package com.example.azkary

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class azkaryviewmodel: ViewModel() {
    private var _counter = MutableStateFlow(0)
    var counter: StateFlow<Int> = _counter



}