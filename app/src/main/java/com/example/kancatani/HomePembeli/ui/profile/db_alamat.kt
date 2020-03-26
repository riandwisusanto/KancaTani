package com.example.kancatani.HomePembeli.ui.profile

object db_alamat {

    val provinsi = arrayOf(
        " ", "Jawa Timur", "Jawa Barat"
    )

    val kota = arrayOf(
        alamatmodel(" " , arrayOf(" ")),
        alamatmodel("Jawa Timur" , arrayOf("Ponorogo", "Madiun", "Surabaya")),
        alamatmodel("Jawa Barat" , arrayOf("Bandung"))
    )

    val kecamatan = arrayOf(
        alamatmodel(" " , arrayOf(" ")),
        alamatmodel("Ponorogo", arrayOf("Sooko", "Pudak", "Pulung")),
        alamatmodel("Madiun", arrayOf("Balerejo")),
        alamatmodel("Surabaya", arrayOf("Jambangan", "Benowo")),
        alamatmodel("Bandung", arrayOf("Antapani", "Andir"))
    )
}
