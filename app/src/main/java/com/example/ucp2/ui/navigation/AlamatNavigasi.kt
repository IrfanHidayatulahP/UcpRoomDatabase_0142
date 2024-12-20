package com.example.ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route = "DashBoard"
}

object DestinasiHomeDsn : AlamatNavigasi {
    override val route: String = "HomeDosen"
}

object DestinasiHomeMK : AlamatNavigasi {
    override val route: String = "HomeMK"
}

object DestinasiDetail : AlamatNavigasi {
    override val route: String = "DetailMK"
}

object DestinasiUpdate: AlamatNavigasi {
    override val route: String = "UpdateMK"
}