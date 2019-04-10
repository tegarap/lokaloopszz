package com.tegarap.lokaloops.viewholder

import android.app.Dialog
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.tegarap.lokaloops.R
import com.tegarap.lokaloops.models.ListItem
import kotlinx.android.synthetic.main.fragment_popup_order.*
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.fragment_popup_order.*
import kotlinx.android.synthetic.main.fragment_popup_order.view.*
import kotlinx.android.synthetic.main.item_list_checkout.view.*

class ListItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: ListItem, callback: Callback) {

        itemView.tv_name?.text = data.name
        itemView.tv_stock?.text = "Stock : " + data.quantity
        itemView.tv_price?.text = "Rp. " + data.price

        itemView.iv_product?.apply {

            Glide.with(this)
                .load("https://saptorenggo-pakis.com/api/poslite/assets/gambar/" + data.foto)
                .into(this)

        }

//        val quan = Integer.parseInt(data.price.toString())

        itemView.bt_order?.setOnClickListener {

            val dialog = Dialog(itemView.context)
            dialog.setContentView(R.layout.fragment_popup_order)
            dialog.tv_namabarang?.text = data.name + "  Rp. " + data.price
            val quan = Integer.parseInt(data.price.toString())
            var number = Integer.parseInt(dialog.tv_stock2.text.toString())

            dialog.bt_min?.setOnClickListener {
                number -= 1
                if (number < 1) {
                    dialog.bt_min.isEnabled = false
                    dialog.btn_keranjang.setVisibility(View.GONE)
                    dialog.btn_backtomenu.setVisibility(View.VISIBLE)
                }
                dialog.btn_keranjang.text = "Add to Box - Rp. " + quan * number
                dialog.tv_stock2.text = number.toString()
            }

            dialog.bt_plus?.setOnClickListener {
                number +=1

                if (number >= 1) {
                    dialog.bt_min.isEnabled = true
                    dialog.btn_backtomenu.setVisibility(View.GONE)
                    dialog.btn_keranjang.setVisibility(View.VISIBLE)
                    dialog.btn_keranjang.text = "Add to Box - Rp. " + quan * number
                }
                dialog.tv_stock2.text = number.toString()
            }

            dialog.btn_keranjang?.setOnClickListener{
                callback.onSubmit(data, number)
            }

            dialog.show()
        }
//        itemView.bt_keranjang

    }


    interface Callback{
        fun onSubmit(data: ListItem, number:Int)
    }


}