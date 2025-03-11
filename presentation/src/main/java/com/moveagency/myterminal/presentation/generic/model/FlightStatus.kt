package com.moveagency.myterminal.presentation.generic.model

enum class FlightStatus(
    val value: String
) {
    Scheduled("Flight Scheduled"),
    Delayed("Delayed"),
    WaitInLounge("Wait in Lounge"),
    GateOpen("Gate Open"),
    Boarding("Boarding"),
    GateClosing("Gate Closing"),
    GateClosed("Gate Closed"),
    Departed("Departed"),
    Cancelled("Cancelled"),
    GateChange("Gate Change"),
    Tomorrow("Tomorrow"),
    Unknown("Unknown"),
}
