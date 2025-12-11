package com.example.azkary.Presentaion.ViewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class azkaryviewmodel: ViewModel() {
    private var _counter = MutableStateFlow(0)
    var counter: StateFlow<Int> = _counter



}