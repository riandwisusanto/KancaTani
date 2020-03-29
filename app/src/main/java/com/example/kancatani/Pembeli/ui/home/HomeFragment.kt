package com.example.kancatani.Pembeli.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.kancatani.R

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val isBerita = view.findViewById<ImageSlider>(R.id.berita_imageslider)
        val slideModel  = ArrayList<SlideModel>()
        slideModel.add(SlideModel(R.drawable.liquid,true))
        slideModel.add(SlideModel(R.drawable.jugg,true))
        slideModel.add(SlideModel(R.drawable.liquid,true))
        slideModel.add(SlideModel(R.drawable.jugg,true))
        isBerita.setImageList(slideModel,true)

        return view
    }
}
