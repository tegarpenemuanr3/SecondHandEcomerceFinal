package com.tegarpenemuan.secondhandecomerce.ui.notifications.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.tegarpenemuan.secondhandecomerce.data.api.category.GetCategoryResponse
import com.tegarpenemuan.secondhandecomerce.data.api.category.GetCategoryResponseItem
import com.tegarpenemuan.secondhandecomerce.data.api.getNotifications.GetNotifResponseItem
import com.tegarpenemuan.secondhandecomerce.data.api.getProduct.GetProductResponse
import com.tegarpenemuan.secondhandecomerce.databinding.ListItemCategoryHomeBinding
import com.tegarpenemuan.secondhandecomerce.databinding.ListItemNotificationsBinding
import com.tegarpenemuan.secondhandecomerce.databinding.ListItemProductHomeBinding

class NotificationsAdapter(private val listener: EventListener, private var list: List<GetNotifResponseItem>)
    : RecyclerView.Adapter<NotificationsAdapter.ViewHolder>(){
    inner class ViewHolder(val binding: ListItemNotificationsBinding)
        : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<GetNotifResponseItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemNotificationsBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvBarang.text = item.product_id.toString()
        Glide.with(holder.binding.root.context)
            .load(item.image_url)
            .transform(RoundedCorners(20))
            .into(holder.binding.ivImg)
        holder.binding.tvHarga.text = item.bid_price.toString()
        holder.binding.tvJenisNotif.text = item.status
        holder.binding.tvTanggal.text = item.status

        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface EventListener {
        fun onClick(item: GetNotifResponseItem)
    }
}