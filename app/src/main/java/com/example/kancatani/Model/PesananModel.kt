package com.example.kancatani.Model

data class PesananModel(
    var id: String = "",
    var id_barang: String = "",
    var id_lapak: String = "",
    var fotobarang: String = "",
    var namabarang: String = "",
    var harga: Int = 1000,
    var jasapengiriman: String = "",
    var jumlah: Int = 1,
    var pesan: String = "",
    var metodebayar: String = "",
    var harga_total: Int = 1000,
    var status: String = ""
)