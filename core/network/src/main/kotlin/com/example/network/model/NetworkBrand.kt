package com.example.network.model

/**
 *  Example data:
 *  "id": "FRANCEINTER",
 *         "title": "France Inter",
 *         "baseline": "Le direct de France Inter",
 *         "description": "Joyeuse, savante et populaire, France Inter est la radio généraliste de service public ",
 *         "websiteUrl": "https://radiofrance.fr",
 *         "playerUrl": "https://embed.radiofrance.fr/franceinter/player?id_station=1",
 *         "liveStream": "https://icecast.radiofrance.fr/franceinter-midfi.mp3?id=openapi",
 *         "localRadios": null,
 */
data class NetworkBrand (
    val id:String,
    val title: String,
    val baseline: String,
    val description: String,
    )