package me.mitul.aij.college

data class CollegeModel(
    val id: Long = -1,
    val name: String = "",
    var code: String? = "",
    var acronym: String? = "",
    var phone: String? = "",
    var email: String? = "",
    var website: String? = "",
    var address: String? = "",
    var fees: String? = "",
    var hostel: String? = "",
    var established: String? = "",
    var admissionCode: String? = "",
    //
    var type: String? = "",
    var university: String? = "",
    //
    var branches: String? = "",
)
