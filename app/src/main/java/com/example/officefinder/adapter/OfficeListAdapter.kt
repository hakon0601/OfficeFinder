package com.example.officefinder.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.officefinder.R
import com.example.officefinder.models.OfficeModel
import kotlinx.android.synthetic.main.office_list_card.view.*
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL

class OfficeListAdapter(): RecyclerView.Adapter<OfficeListAdapter.MyViewHolder>() {

    private var officeList: List<OfficeModel>? = null

    fun setOfficeList(officeList: List<OfficeModel>?) {
        this.officeList = officeList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OfficeListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.officefinder.R.layout.office_list_card, parent, false)
//        val img = findViewById(R.id.imageView)
//        DownloadImageFromInternet(img).execute("https://images.unsplash.com/photo-1535332371349-a5d229f49cb5?ixlib=rb-1.2.1&w=1000&q=80")

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

        val tvOfficeName = view.tvOfficeName
        val ivOfficeImage = view.ivOfficeImage
        val tvOfficeDescription = view.tvOfficeDescription
//        val tvOfficeCapacity = view.tvOfficeCapacity
        val tvOfficeAvailability = view.tvOfficeAvailability
        val tvOfficeFacilities = view.tvOfficeFacilities


        fun bind(office: OfficeModel?) {
            tvOfficeName.text = office?.name
            tvOfficeDescription.text = office?.description
//            tvOfficeCapacity.text = office?.capacity.toString()
            tvOfficeAvailability.text = office?.available_seats.toString() + "/" + office?.capacity.toString()
            tvOfficeFacilities.text = office?.facilities?.joinToString { it.toString() }



            if (office?.img_url != null && office?.img_url != "") {
                val urlImage: URL = URL(office?.img_url)
                tvOfficeName.text = office?.name

                val result: Deferred<Bitmap?> = GlobalScope.async {
                    urlImage.toBitmap()
                }

                GlobalScope.launch(Dispatchers.Main) {
                    // show bitmap on image view when available
                    ivOfficeImage.setImageBitmap(result.await())
                }
            }

        }

        fun URL.toBitmap(): Bitmap?{
            return try {
                BitmapFactory.decodeStream(openStream())
            }catch (e: IOException){
                null
            }
        }
    }

}