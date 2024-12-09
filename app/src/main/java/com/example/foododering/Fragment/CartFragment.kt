package com.example.foododering.Fragment

import AdapterCart
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foododering.R
import com.example.foododering.databinding.FragmentCartBinding


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = listOf("Hot Dog", "Sanwich", "Salad", "Spaghetti", "Bánh Mì", "Phở")
        val price = listOf("5", "10", "8", "11", "2", "3")
        val image = listOf(
            R.drawable.hotdog,
            R.drawable.sandwich,
            R.drawable.salad,
            R.drawable.spaghetti,
            R.drawable.banhmi,
            R.drawable.pho
        )
        val tvTotal = binding.tvTotal
        val tvSub = binding.tvSubTotal
        val adapter = AdapterCart(ArrayList(item), ArrayList(image), ArrayList(price),tvSub,tvTotal)
        binding.PopularFoodCart.layoutManager = LinearLayoutManager(requireContext())
        binding.PopularFoodCart.adapter = adapter
        binding.tvSubTotal.text = adapter.calculateTotalSubPrice().toString()
        binding.tvTotal.text = adapter.calculateTotalPrice().toString()
    }
}