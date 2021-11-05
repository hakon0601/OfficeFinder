package com.example.officefinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.officefinder.R
import com.example.officefinder.models.OfficeModel
import kotlinx.android.synthetic.main.office_list_card.view.*

class OfficeListAdapter(): RecyclerView.Adapter<OfficeListAdapter.MyViewHolder>() {

    private var officeList: List<OfficeModel>? = null

    fun setOfficeList(officeList: List<OfficeModel>?) {
        this.officeList = officeList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OfficeListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.office_list_card, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfficeListAdapter.MyViewHolder, position: Int) {
        var office = officeList?.get(position)
        holder.bind(office)
    }

    override fun getItemCount(): Int {
        if(officeList == null) return 0
        else return officeList?.size!!
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val tvOfficeName2 = view.tvOfficeName2

        fun bind(office: OfficeModel?) {
            tvOfficeName2.text = office?.name
        }
    }

}