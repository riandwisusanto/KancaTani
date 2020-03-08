package com.example.kancatani.Model

class UserModel(
    var id: String,
    var alamat :String,
    var email: String,
    var jk: String,
    var nama: String,
    var notelp: String,
    var tgllahir: String,
    var foto: String
) {

    constructor() : this("", "", "", "", "", "", "", "")

}