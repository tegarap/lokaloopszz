package com.tegarap.lokaloops.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.tegarap.lokaloops.R
import com.tegarap.lokaloops.fragments.AccountFragment
import com.tegarap.lokaloops.fragments.HistoryFragment
import com.tegarap.lokaloops.fragments.StoreFragment
import com.tegarap.lokaloops.models.ListItem
import com.tegarap.lokaloops.viewholder.ListItemVH
import kotlinx.android.synthetic.main.item_list_checkout.*

class MainActivity : AppCompatActivity(), ListItemVH.Callback {

    override fun onSubmit(data: ListItem, number: Int) {
        tv_name_check?.text = data.name
        tv_price_check?.text = "Rp. " + data.price
        tv_stock_check?.text = "Stock : " + number

    }

    companion object {

        fun getIntent(ctx : Context?) = Intent(ctx, MainActivity::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //bottom navbar
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val firstFragment = StoreFragment()
        openFragment(firstFragment)

        //bottom sheet


    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {

            R.id.navigation_store -> {
                val accountFragment = StoreFragment()
                openFragment(accountFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_report -> {
                val reportFragment = HistoryFragment()
                openFragment(reportFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_account -> {
                val accountFragment = AccountFragment()
                openFragment(accountFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}