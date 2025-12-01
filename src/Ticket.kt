// This data class just holds the info to print a ticket
data class Ticket(
    val originStation: String,
    val destinationStation: String,
    val price: Double,
    val ticketType: String
)