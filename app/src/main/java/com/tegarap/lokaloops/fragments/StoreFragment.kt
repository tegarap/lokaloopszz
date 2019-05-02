package com.tegarap.lokaloops.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tegarap.lokaloops.R
import com.tegarap.lokaloops.common.VerticalSpaceItem
import com.tegarap.lokaloops.models.Adapter
import com.tegarap.lokaloops.models.CheckoutModel
import com.tegarap.lokaloops.models.ListItem
import com.tegarap.lokaloops.models.ListItemResponse
import com.tegarap.lokaloops.rest.EndPoint
import com.tegarap.lokaloops.rest.InterfacePoint
import com.tegarap.lokaloops.viewholder.ListItemVH
import kotlinx.android.synthetic.main.fragment_store.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list_checkout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response






class StoreFragment : Fragment(), ListItemVH.Callback {

    override fun onSubmit(data: ListItem, number: Int) {
        tv_name?.text = data.name
        tv_stock?.text = number.toString()
    }

    companion object {

        fun getIntent(ctx : Context?) = Intent(ctx, StoreFragment::class.java)

    }

    private lateinit var listAdapter : Adapter<ListItem, ListItemVH>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_store, container, false)

    }

    fun ngaleh(){
        val fragmentCheckout = Checkout()
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
// ngirim data antar fragment
        val args = Bundle()
        args.putParcelable("model", CheckoutModel(tv_name?.text as String, tv_stock?.text as String))
//
        fragmentCheckout.arguments = args
        fragmentTransaction.replace(R.id.container, fragmentCheckout)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_bayar.setOnClickListener{
            ngaleh()
        }

        listAdapter = object : Adapter<ListItem, ListItemVH>(
            R.layout.item_list,
            arrayListOf(),
            ListItemVH::class.java,
            ListItem::class.java
        ){
            override fun bindView(holder: ListItemVH, model: ListItem, position: Int) {
                holder.bind(model, this@StoreFragment)
            }

        }

        rv_list_item?.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(VerticalSpaceItem(context, 5f, 2f))
        }

        EndPoint.client.create(InterfacePoint::class.java).listItem().enqueue(object : Callback<ListItemResponse> {
            override fun onResponse(call: Call<ListItemResponse>, response: Response<ListItemResponse>) {

                if(response.isSuccessful) listAdapter.updateList(response.body()!!.result)

            }

            override fun onFailure(call: Call<ListItemResponse>, t: Throwable) {

            }
        })


    }
}
