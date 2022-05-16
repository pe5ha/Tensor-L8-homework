package com.pe5ha.homework

data class Department(val title: String) {
    companion object{
        fun getMockDepartment() = listOf(
            Department("Space department"),
            Department("Science department"),
            Department("Engineering department")
        )
    }
}