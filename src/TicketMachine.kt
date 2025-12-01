// This will be our main class that runs the whole application.
// It's a regular class because it will have methods later.
class TicketMachine {

    // We initialize the lists as empty for now.
    // We'll hard-code data into them later.
    val destinations = mutableListOf<Destination>()
    val users = mutableListOf<User>()
    val specialOffers = mutableListOf<SpecialOffer>()

    // The station where this specific ticket machine is located (starting point).
    val originStation: String = "Birmingham"

    // Tracks the money inserted by a user.
    var moneyInserted: Double = 0.0

    // Tracks who is logged in.
    var currentUser: User? = null

    fun listDestinations(): List<Destination> {
        return destinations
    }

    // Adds a new destination with 0 sales
    fun addDestination(stationName: String, singlePrice: Double, returnPrice: Double) {
        val destination = Destination(stationName, singlePrice, returnPrice, 0)
        destinations.add(destination)
    }

    // Finds a destination by station name (case-insensitive)
    fun findDestination(stationName: String): Destination? {
        return destinations.find { it.stationName.equals(stationName, ignoreCase = true) }
    }

    // Updates prices of an existing destination, returns true if successful
    fun updateDestination(stationName: String, newSinglePrice: Double, newReturnPrice: Double): Boolean {
        val destination = findDestination(stationName)
        return if (destination != null) {
            destination.singlePrice = newSinglePrice
            destination.returnPrice = newReturnPrice
            true
        } else {
            false
        }
    }

    // Changes all prices by a given factor (> 0)
    fun applyPriceFactor(factor: Double) {
        destinations.forEach { dest ->
            dest.singlePrice *= factor
            dest.returnPrice *= factor
        }
    }
}
