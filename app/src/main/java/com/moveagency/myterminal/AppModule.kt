package com.moveagency.myterminal

import com.moveagency.myterminal.presentation.PresentationModule
import org.koin.core.annotation.Module

@Module(includes = [PresentationModule::class])
class AppModule
