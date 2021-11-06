package com.example.officefinder.models

data class OfficeModel(val id: String,
                       val name: String,
                       val organization_id: String,
                       val img_url: String,
                       val description: String,
                       val capacity: Int,
                       val available_seats: Int,
                       val facilities: List<String>)