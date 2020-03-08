package com.example.clima.model

import android.os.Parcelable
import android.widget.HeaderViewListAdapter
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (val title: String?,
            val first: String?,
            val last: String?,
            val gender: String?
) : Parcelable {
}