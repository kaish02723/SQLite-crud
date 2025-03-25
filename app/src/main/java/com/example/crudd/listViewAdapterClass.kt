package com.example.crudd

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class listViewAdapterClass (private var context: Context, private var arrayList: ArrayList<userMOdelClass>):
    BaseAdapter() {
        private var SQLiteDabase=SQLiteHelperData(context)
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return arrayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("MissingInflatedId", "ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val getItem=arrayList[p0]
        val layout= LayoutInflater.from(context).inflate(R.layout.custom_list_view_design,null,false)
        val nameText=layout.findViewById<TextView>(R.id.textViewName)
        val emailText=layout.findViewById<TextView>(R.id.textView2Email)

        val deletedUserItem=layout.findViewById<Button>(R.id.buttonDeletedUser)
        deletedUserItem.setOnClickListener {
            SQLiteDabase.deletedItemUser(getItem.id)
            refresh(SQLiteDabase.viewData())

        }
        val updatedUserItem=layout.findViewById<Button>(R.id.buttonUpdateUser)
              updatedUserItem.setOnClickListener {
                  val intent=Intent(context,update_user::class.java).apply {
                      putExtra("id",getItem.id)

                  }
                p2?.context?.startActivity(intent)
              }



        nameText.text= getItem.name
        emailText.text= getItem.email

       

        return layout
    }

    fun refresh(arrayRefreshData: ArrayList<userMOdelClass>){
               arrayList=arrayRefreshData
        notifyDataSetChanged()
    }
}