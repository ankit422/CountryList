package com.countrydetails.ui.main.country

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.countrydetails.data.entities.Country
import com.countrydetails.databinding.DataItemsBinding


class Adapter(private val listener: ArticleItemListener) :
    RecyclerView.Adapter<DataViewHolder>() {

    interface ArticleItemListener {
        fun onClickedArticle(articleId: Int, code: String)
    }

    private var list: List<Country> = ArrayList()

    fun setData(data: List<Country>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding: DataItemsBinding =
            DataItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}

class DataViewHolder(
    private val itemBinding: DataItemsBinding,
    private val listener: Adapter.ArticleItemListener
) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private lateinit var article: Country

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(dataItem: Country) {
        try {
            article = dataItem
            itemBinding.title.text = dataItem.Name
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                itemBinding.pinCode.text = Html.fromHtml(
                    "Country Code <b>${dataItem.Code}", Html.FROM_HTML_MODE_LEGACY
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(v: View?) {
        listener.onClickedArticle(article.ID, article.Code!!)
    }
}