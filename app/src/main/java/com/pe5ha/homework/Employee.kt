package com.pe5ha.homework

data class Employee(
    val id: Long,
    val fullName: String,
    val photoUrl: String,
    val department: String
) {
    companion object{
        fun getMockEmloyees() = listOf(
            Employee(
                0,
                "Astronaut",
                "https://cdn.akamai.steamstatic.com/steam/apps/1138850/ss_99361d99514e9745f9fd77ec4b66e1e80a2bec75.600x338.jpg?t=1639634527",
                "Space department"
            ),
            Employee(
                1,
                "Nauticravler",
                "https://thelastgame.ru/wp-content/uploads/2019/09/ss_ef95417a75a981c868362511399c555c14fad0eb.1920x1080.jpg",
                "Bottom department"
            )
        )
    }
}