// Developer: Bogdan (Group Member B)
// Class: TicketMachine
// Description: Core system logic responsible for managing destinations,
// users, ticket pricing and admin interactions in the ticket machine system.

/**
 * This class represents the main ticket machine in the system.
 * It maintains a list of destinations, manages pricing updates,
 * handles admin operations, and stores the currently logged-in user.
 */
class TicketMachine {

    /** List of available destinations that the admin can update and users can purchase tickets for */
    val destinations = mutableListOf<Destination>()

    /** List of registered users stored in the system */
    val users = mutableListOf<User>()

    /** List of special offers which can provide specific discounts */
    val specialOffers = mutableListOf<SpecialOffer>()

    /** The starting station where the ticket kiosk is located */
    val originStation: String = "Birmingham"

    /** Tracks how much money has currently been inserted into the machine by a user */
    var moneyInserted: Double = 0.0

    /** Stores the user currently logged into the system (admin or regular) */
    var currentUser: User? = null

    /**
     * Retrieves a list of all destinations currently supported by the system.
     * @return A list containing Destination objects
     */
    fun listDestinations(): List<Destination> {
        return destinations
    }

    /**
     * Adds a new destination to the system with a default number of ticket sales (0).
     *
     * @param stationName The station name of the new destination
     * @param singlePrice Price of a single ticket to this destination
     * @param returnPrice Price of a return ticket to this destination
     */
    fun addDestination(stationName: String, singlePrice: Double, returnPrice: Double) {
        val destination = Destination(stationName, singlePrice, returnPrice, 0)
        destinations.add(destination)
    }

    /**
     * Searches for a destination by name (case-insensitive).
     *
     * @param stationName The name entered by an admin/user
     * @return Destination if found, otherwise null
     */
    fun findDestination(stationName: String): Destination? {
        return destinations.find { it.stationName.equals(stationName, ignoreCase = true) }
    }

    /**
     * Updates both ticket prices for an existing destination.
     *
     * @param stationName The station name selected for price change
     * @param newSinglePrice The updated price for a single ticket
     * @param newReturnPrice The updated price for a return ticket
     * @return True if destination was found and updated successfully, false otherwise
     */
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

    /**
     * Applies a multiplication factor to update all ticket prices at once.
     * For example: factor 1.1 increases prices by 10%, factor 0.9 decreases prices by 10%.
     *
     * @param factor A positive value used to update prices (must be > 0)
     */
    fun applyPriceFactor(factor: Double) {
        destinations.forEach { dest ->
            dest.singlePrice *= factor
            dest.returnPrice *= factor
        }
    }
}
