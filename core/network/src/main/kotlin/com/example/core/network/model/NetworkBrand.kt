package com.example.core.network.model

/**
 *  Example data:
 *   {
 *         "id": "FRANCEINTER",
 *         "title": "France Inter",
 *         "baseline": "Le direct de France Inter",
 *         "description": "Joyeuse, savante et populaire, France Inter est la radio généraliste de service public ",
 *         "websiteUrl": "https://radiofrance.fr",
 *         "playerUrl": "https://embed.radiofrance.fr/franceinter/player?id_station=1",
 *         "liveStream": "https://icecast.radiofrance.fr/franceinter-midfi.mp3?id=openapi",
 *         "localRadios": null,
 *         "webRadios": [
 *           {
 *             "id": "FRANCEINTER_LA_MUSIQUE_INTER",
 *             "title": "La musique d'Inter",
 *             "description": "Des standards éternels aux découvertes de la playlist, retrouvez 24h/24 les pépites musicales des programmateurs de France Inter",
 *             "liveStream": "https://icecast.radiofrance.fr/franceinterlamusiqueinter-midfi.mp3?id=openapi",
 *             "playerUrl": "https://embed.radiofrance.fr/franceinter/player?id_station=1101"
 *           }
 *         ]
 *       },
 */
data class NetworkBrand (
    val id:String,
    val title: String,
    val baseline: String,
    val description: String,
    val localRadios: List<LocalRadio>
    )

data class LocalRadio (
    val id:String,
val title: String,
val description: String,
)