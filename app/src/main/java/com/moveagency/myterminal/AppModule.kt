package com.moveagency.myterminal

import com.moveagency.myterminal.data.DataModule
import com.moveagency.myterminal.domain.DomainModule
import com.moveagency.myterminal.presentation.PresentationModule
import org.koin.core.annotation.Module

@Module(
    includes = [
        PresentationModule::class,
        DomainModule::class,
        DataModule::class,
    ]
)
class AppModule
