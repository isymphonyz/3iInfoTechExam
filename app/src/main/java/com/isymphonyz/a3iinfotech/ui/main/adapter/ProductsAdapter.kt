package com.isymphonyz.a3iinfotech.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.isymphonyz.a3iinfotech.R
import com.isymphonyz.a3iinfotech.data.model.ProductModel
import com.isymphonyz.a3iinfotech.ui.main.products.ProductsFragmentDirections
import com.isymphonyz.a3iinfotech.util.Utils
import kotlinx.android.synthetic.main.product_view.view.*
import javax.inject.Singleton

@Singleton
class ProductsAdapter(
    private val products: ArrayList<ProductModel>
) : RecyclerView.Adapter<ProductsAdapter.DataViewHolder>() {

    val TAG = ProductsAdapter::class.java.simpleName

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: ProductModel) {
            itemView.txtTitle.text = product.title
            itemView.txtPrice.text = Utils.roundToDecimals(product.price)
            Glide.with(itemView.img.context)
                .load(product.image)
                .apply(
                    RequestOptions()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher))
                .into(itemView.img)

            if(product.isNewProduct) {
                itemView.txtBadge.visibility = View.VISIBLE
            }

            itemView.layout.setOnClickListener(View.OnClickListener {
                ViewCompat.setTransitionName(itemView.img, "transitionImage")
                ViewCompat.setTransitionName(itemView.txtPrice, "transitionPrice")
                ViewCompat.setTransitionName(itemView.txtBadge, "transitionBadge")
                val action = ProductsFragmentDirections.navProductDetailFragment(product)
                val extras = FragmentNavigator.Extras.Builder()
                    .addSharedElement(itemView.img, ViewCompat.getTransitionName(itemView.img)!!)
                    .addSharedElement(itemView.txtPrice, ViewCompat.getTransitionName(itemView.txtPrice)!!)
                    .addSharedElement(itemView.txtBadge, ViewCompat.getTransitionName(itemView.txtBadge)!!)
                    .build()
                it.findNavController().navigate(action, extras)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.product_view, parent,
                false
            )
        )

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(products[position])

    fun addData(list: List<ProductModel>) {
        products.addAll(list)
    }

}