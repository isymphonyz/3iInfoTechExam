package com.isymphonyz.a3iinfotech.ui.main.products

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.isymphonyz.a3iinfotech.R
import com.isymphonyz.a3iinfotech.data.api.ApiHelper
import com.isymphonyz.a3iinfotech.data.api.ApiServiceImpl
import com.isymphonyz.a3iinfotech.data.model.ProductModel
import com.isymphonyz.a3iinfotech.ui.base.BaseFragment
import com.isymphonyz.a3iinfotech.ui.base.ViewModelFactory
import com.isymphonyz.a3iinfotech.ui.main.adapter.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import isymphonyz.testmvvm.utils.Status
import kotlinx.android.synthetic.main.products_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : BaseFragment() {
    private lateinit var viewModel: ProductsViewModel
    private lateinit var adapter: ProductsAdapter
    override val layoutId: Int = R.layout.products_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        activity?.title = getText(R.string.app_name)

        adapter = ProductsAdapter(arrayListOf())
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(ProductsViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.getProducts().observe(ProductsFragment@this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { products -> renderList(products) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(products: List<ProductModel>) {
        adapter.addData(products)
        adapter.notifyDataSetChanged()
    }

}