package com.example.kancatani.Pembeli.ui.keranjang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kancatani.R

class KeranjangFragment : Fragment() {

    private lateinit var keranjangViewModel: KeranjangViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_keranjang, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        return root
    }
}