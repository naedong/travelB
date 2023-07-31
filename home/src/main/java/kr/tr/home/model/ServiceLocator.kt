package com.wasisto.githubuserfinder.android

import androidx.paging.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.reflect.KClass

interface ServiceLocator {


    val ioDispatcher: CoroutineDispatcher

    val mainDispatcher: CoroutineDispatcher

    fun getLogger(cls: KClass<*>): Logger
}