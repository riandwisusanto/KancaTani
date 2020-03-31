package com.example.kancatani.Model

data class BarangModel(
    var id: String = "",
    var id_lapak: String = "",
    var fotobarang: String = "",
    var nama: String = "",
    var harga: Int = 1000,
    var kategori: String = "",
    var deskripsi: String = "",
    var jasapengiriman: ArrayList<String> = arrayListOf(),
    var bintang: String = "",
    var stok: Int = 1,
    var kondisi: String = "",
    var provinsi: String = "",
    var kota: String= "",
    var kecamatan: String = "",
    var terjual: Int = 0
)