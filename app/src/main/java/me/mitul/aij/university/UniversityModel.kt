package me.mitul.aij.university

data class UniversityModel(
    var id: Int = -1,
    var name: String = "",
    var acronym: String? = "",
    var type: String? = "",
    var phone: String? = "",
    var address: String? = "",
    var website: String? = "",
    var email: String? = "",
    var established: String? = "",
)
