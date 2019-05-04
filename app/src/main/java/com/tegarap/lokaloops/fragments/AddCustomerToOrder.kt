package com.tegarap.lokaloops.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tegarap.lokaloops.R
import kotlinx.android.synthetic.main.fragment_add_customer_to_order.*

class AddCustomerToOrder : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_customer_to_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_back.setOnClickListener {
            kembali()
        }
    }

    private fun kembali() {
        val fragmentCheckout = Checkout()
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragmentCheckout)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    companion object {

    }
}
