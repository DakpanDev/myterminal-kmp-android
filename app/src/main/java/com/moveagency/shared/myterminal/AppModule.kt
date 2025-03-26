package com.moveagency.shared.myterminal

import com.moveagency.shared.myterminal.presentation.PresentationModule
import org.koin.core.annotation.Module

@Module(includes = [PresentationModule::class])
class AppModule
