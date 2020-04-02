package com.example.kancatani.Model

object db_alamat {

    val provinsi = arrayOf(
        "x", "Jawa Timur", "Jawa Barat"
    )

    val kota = arrayOf(
        alamatmodel("x", arrayOf("x")),
        alamatmodel(
            "Jawa Timur",
            arrayOf("Ponorogo", "Madiun", "Surabaya")
        ),
        alamatmodel("Jawa Barat", arrayOf("Bandung"))
    )

    val kecamatan = arrayOf(
        alamatmodel("x", arrayOf("x")),
        alamatmodel(
            "Ponorogo",
            arrayOf("Sooko", "Pudak", "Pulung")
        ),
        alamatmodel("Madiun", arrayOf("Balerejo")),
        alamatmodel(
            "Surabaya",
            arrayOf("Jambangan", "Benowo")
        ),
        alamatmodel(
            "Bandung",
            arrayOf("Antapani", "Andir")
        )
    )
}
