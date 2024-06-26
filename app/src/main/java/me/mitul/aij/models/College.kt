package me.mitul.aij.models

data class College(
    val id: Long = -1,
    val name: String = "",
    var code: String? = "",
    var acronym: String? = "",
    var phone: String? = "",
    var email: String? = "",
    var website: String? = "",
    var address: String? = "",
    var fees: Int? = -1,
    var hostel: String? = "",
    var established: String? = "",
    var admissionCode: String? = "",
    //
    var type: String? = "",
    var university: String? = "",
    //
    var branches: String? = "",
)
