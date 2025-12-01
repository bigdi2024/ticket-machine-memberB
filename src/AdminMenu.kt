class AdminMenu(private val ticketMachine: TicketMachine) {

    fun viewDestinations() {
        println("----- Destination List -----")
        if (ticketMachine.listDestinations().isEmpty()) {
            println("No destinations available.")
        } else {
            for (dest in ticketMachine.listDestinations()) {
                println("Station: ${dest.stationName} | Single: ${dest.singlePrice} | Return: ${dest.returnPrice} | Sales: ${dest.numberOfSales}")
            }
        }
        println("----------------------------")
    }

    fun addDestination() {
        println("Enter station name:")
        val name = readLine()?.trim()

        println("Enter single ticket price:")
        val singleInput = readLine()

        println("Enter return ticket price:")
        val returnInput = readLine()

        if (name.isNullOrBlank() || singleInput.isNullOrBlank() || returnInput.isNullOrBlank()) {
            println("Invalid input. Destination not added.")
            return
        }

        val singlePrice = singleInput.toDoubleOrNull()
        val returnPrice = returnInput.toDoubleOrNull()

        if (singlePrice == null || returnPrice == null || singlePrice < 0 || returnPrice < 0) {
            println("Invalid prices. Destination not added.")
            return
        }

        ticketMachine.addDestination(name, singlePrice, returnPrice)
        println("Destination added successfully.")
    }

    fun editDestination() {
        println("Enter the name of the station you want to edit:")
        val name = readLine()?.trim()

        if (name.isNullOrBlank()) {
            println("Invalid station name.")
            return
        }

        val destination = ticketMachine.findDestination(name)
        if (destination == null) {
            println("No destination found with that name.")
            return
        }

        println("Current details:")
        println("Station: ${destination.stationName} | Single: ${destination.singlePrice} | Return: ${destination.returnPrice} | Sales: ${destination.numberOfSales}")

        println("Enter new single ticket price:")
        val singleInput = readLine()
        println("Enter new return ticket price:")
        val returnInput = readLine()

        val newSingle = singleInput?.toDoubleOrNull()
        val newReturn = returnInput?.toDoubleOrNull()

        if (newSingle == null || newReturn == null || newSingle < 0 || newReturn < 0) {
            println("Invalid prices. Destination not updated.")
            return
        }

        val updated = ticketMachine.updateDestination(name, newSingle, newReturn)
        if (updated) {
            println("Destination updated successfully.")
        } else {
            println("Failed to update destination.")
        }
    }

    fun changeAllPrices() {
        println("Enter price factor (e.g. 1.1 increases by 10%, 0.9 decreases by 10%):")
        val factorInput = readLine()
        val factor = factorInput?.toDoubleOrNull()

        if (factor == null || factor <= 0) {
            println("Invalid factor. Prices not changed.")
            return
        }

        ticketMachine.applyPriceFactor(factor)
        println("All ticket prices updated by factor $factor.")
    }

    fun showMenu() {
        while (true) {
            println()
            println("=== Admin Menu ===")
            println("1. View destinations")
            println("2. Add destination")
            println("3. Edit destination")
            println("4. Change all prices by factor")
            println("5. Exit")
            print("Choose an option: ")

            when (readLine()?.trim()) {
                "1" -> viewDestinations()
                "2" -> addDestination()
                "3" -> editDestination()
                "4" -> changeAllPrices()
                "5" -> {
                    println("Exiting admin menu...")
                    return
                }
                else -> println("Invalid choice. Please try again.")
            }
        }
    }
}
