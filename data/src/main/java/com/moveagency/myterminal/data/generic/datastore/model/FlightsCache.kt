package com.moveagency.myterminal.data.generic.datastore.model

import com.moveagency.myterminal.domain.generic.model.Flight
import java.time.LocalDate

typealias FlightsCache = Map<LocalDate, List<Flight>>
