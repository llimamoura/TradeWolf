
package com.example.tradewolfapp.views.navigations

sealed class Screens(val rout: String){
    object Home: Screens("home_screen")
    object Profile: Screens("profile_screen")
    object Markets: Screens("Markets_screen")
    object Wallets: Screens("Wallets_screen")
    object Transaction: Screens("transaction_screen")
}
