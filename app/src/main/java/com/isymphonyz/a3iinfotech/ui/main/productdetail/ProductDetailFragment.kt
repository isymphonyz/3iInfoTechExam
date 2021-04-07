package com.isymphonyz.a3iinfotech.ui.main.productdetail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.isymphonyz.a3iinfotech.R
import com.isymphonyz.a3iinfotech.data.api.ApiHelper
import com.isymphonyz.a3iinfotech.data.api.ApiServiceImpl
import com.isymphonyz.a3iinfotech.data.model.ProductModel
import com.isymphonyz.a3iinfotech.ui.base.BaseFragment
import com.isymphonyz.a3iinfotech.ui.base.ViewModelFactory
import com.isymphonyz.a3iinfotech.ui.main.adapter.ProductsAdapter
import com.isymphonyz.a3iinfotech.util.Utils
import isymphonyz.testmvvm.utils.Status
import kotlinx.android.synthetic.main.product_detail_fragment.*
import kotlinx.android.synthetic.main.product_view.view.*
import kotlinx.android.synthetic.main.products_fragment.*

class ProductDetailFragment : BaseFragment() {

    private lateinit var product : ProductModel

    override val layoutId = R.layout.product_detail_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        product = arguments?.get("product") as ProductModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        ViewCompat.setTransitionName(img, "transitionImage")
        ViewCompat.setTransitionName(txtPrice, "transitionPrice")
        ViewCompat.setTransitionName(txtBadge, "transitionBadge")

        Glide.with(img.context)
            .load(product.image)
            .apply(
                RequestOptions()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher))
            .into(img)

        txtTitle.text = product.title
        txtPrice.text = Utils.roundToDecimals(product.price)
        txtContent.text = product.content

        if(product.isNewProduct) {
            txtBadge.visibility = View.VISIBLE
        }
    }

}