package com.stu71954.jobroomdb_71954

import java.util.Calendar
import java.util.Date


data class Decorator(
    val id: Int,
    val name: String,
    val location: String,
    val email: String,
    val contact: String,
    val availableFrom: Date,
    val availableTo: Date
)

val mockDecorators = listOf(
    Decorator(1, "John Doe", "Dublin", "john.doe@example.com", "123-456-7890", createDate(2024, 11, 1), createDate(2024, 11, 10)),
    Decorator(2, "Jane Smith", "Dublin", "jane.smith@example.com", "987-654-3210", createDate(2024, 11, 5), createDate(2024, 11, 15)),
    Decorator(3, "Alice Johnson", "Dublin", "alice.johnson@example.com", "555-123-4567", createDate(2024, 11, 3), createDate(2024, 11, 12)),
    Decorator(4, "Bob Brown", "Dublin", "bob.brown@example.com", "555-987-6543", createDate(2024, 11, 7), createDate(2024, 11, 20)),
    Decorator(5, "Sarah O'Connor", "Dublin", "sarah.oconnor@example.com", "222-333-4444", createDate(2024, 11, 2), createDate(2024, 11, 10)),
    Decorator(6, "Michael Murphy", "Dublin", "michael.murphy@example.com", "444-555-6666", createDate(2024, 11, 8), createDate(2024, 11, 18)),
    Decorator(7, "Emma Kelly", "Dublin", "emma.kelly@example.com", "777-888-9999", createDate(2024, 11, 10), createDate(2024, 11, 20)),
    Decorator(5, "Sarah O'Connor", "Cork", "sarah.oconnor@example.com", "222-333-4444", createDate(2024, 11, 25), createDate(2024, 11, 5)),
    Decorator(6, "Michael Murphy", "Galway", "michael.murphy@example.com", "444-555-6666", createDate(2024, 11, 1), createDate(2024, 11, 12)),
    Decorator(7, "Emma Kelly", "Limerick", "emma.kelly@example.com", "777-888-9999", createDate(2024, 11, 15), createDate(2024, 11, 3)),
    Decorator(8, "Noah Walsh", "Belfast", "noah.walsh@example.com", "111-222-3333", createDate(2024, 11, 10), createDate(2024, 11, 20)),
)

fun createDate(year: Int, month: Int, day: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.clear() // Clears all fields, ensuring no time component
    calendar.set(year, month - 1, day) // Month is zero-based
    return calendar.time
}