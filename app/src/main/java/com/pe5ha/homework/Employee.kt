package com.pe5ha.homework

data class Employee(
    val id: Long,
    val fullName: String,
    val photoUrl: String,
    val department: String
) {
    companion object{
        fun getMockEmployees() = listOf(
            Employee(
                0,
                "My name is",
                "https://catherineasquithgallery.com/uploads/posts/2021-03/1614599232_75-p-foto-cheloveka-na-belom-fone-82.jpg",
                "Some department"
            ),
            Employee(
                1,
                "Giovanni Giorgio",
                "https://www.lendinero.com/wp-content/uploads/2015/11/18.jpg",
                "Another department"
            ),
            Employee(
                2,
                "but everybody",
                "https://mlc5zykssok6.i.optimole.com/wkO2rVQ-0IFPG1ey/w:auto/h:auto/q:auto/https://studyinfocus.ru/wp-content/uploads/2017/03/Product_FullPackage_2_Opt.png",
                "Another one department"
            ),
            Employee(
                3,
                "calls me...",
                "https://mlc5zykssok6.i.optimole.com/wkO2rVQ-0IFPG1ey/w:auto/h:auto/q:auto/https://studyinfocus.ru/wp-content/uploads/2017/03/Product_FullPackage_2_Opt.png",
                "Some department"
            ),
            Employee(
                4,
                "Giorgio",
                "https://mlc5zykssok6.i.optimole.com/wkO2rVQ-0IFPG1ey/w:auto/h:auto/q:auto/https://studyinfocus.ru/wp-content/uploads/2017/03/Product_FullPackage_2_Opt.png",
                "Another department"
            )
        )
    }
}