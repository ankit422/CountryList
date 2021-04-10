package com.countrydetails.ui.main.province

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.countrydetails.data.entities.Province
import com.countrydetails.databinding.DataItemsBinding


class ProvinceAdapter : RecyclerView.Adapter<DataViewHolder>() {

    private var list: List<Province> = ArrayList()

    fun setData(data: List<Province>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding: DataItemsBinding =
            DataItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}

class DataViewHolder(
    private val itemBinding: DataItemsBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var article: Province

    fun bind(dataItem: Province) {
        try {
            article = dataItem
            itemBinding.title.text = dataItem.Name
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                itemBinding.pinCode.text = Html.fromHtml(
                    "Province Code <b>${dataItem.Code}", Html.FROM_HTML_MODE_LEGACY
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}