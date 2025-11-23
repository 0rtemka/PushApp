package com.example.pushapp.di.annotations

import javax.inject.Qualifier
import kotlin.annotation.Retention
import kotlin.annotation.AnnotationRetention

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope