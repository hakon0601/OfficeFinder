package com.example.officefinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.officefinder.R
import com.example.officefinder.models.OfficeModel
import kotlinx.android.synthetic.main.office_list_row.view.*

class OfficeListAdapter(): RecyclerView.Adapter<OfficeListAdapter.MyViewHolder>() {

    private var officeList: List<OfficeModel>? = null
//    private var stationStatusList: List<StationStatus>? = null

    fun setOfficeList(officeList: List<OfficeModel>?) {
        this.officeList = officeList
    }

//    fun setStationStatusList(stationStatusList: List<StationStatus>?) {
//        this.stationStatusList = stationStatusList
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OfficeListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.office_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfficeListAdapter.MyViewHolder, position: Int) {
        var office = officeList?.get(position)
//        var stationStatus = stationStatusList?.find { it.station_id == stationInformation?.station_id }
        holder.bind(office)
    }

    override fun getItemCount(): Int {
        if(officeList == null) return 0
        else return officeList?.size!!
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val tvOfficeName = view.tvOfficeName

        fun bind(office: OfficeModel?) {
            tvOfficeName.text = office?.name
        }


    }

}