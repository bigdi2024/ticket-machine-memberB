fun main() {
    val machine = TicketMachine()

    // Initial hard-coded destinations
    machine.addDestination("London", 10.0, 18.0)
    machine.addDestination("Manchester", 8.0, 14.0)

    val adminMenu = AdminMenu(machine)
    adminMenu.showMenu()
}
