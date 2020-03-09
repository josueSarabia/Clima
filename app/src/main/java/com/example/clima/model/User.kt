package com.example.clima.model

import android.os.Parcelable
import android.widget.HeaderViewListAdapter
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (val name: String?,
            val des: String?,
            val mint: String?,
            val maxt: String?
) : Parcelable {
}