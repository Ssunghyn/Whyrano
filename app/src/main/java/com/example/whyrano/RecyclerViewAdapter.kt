package com.example.whyrano

import android.annotation.SuppressLint
import android.os.Build
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.util.ArrayList

class RecyclerViewAdapter(val recyclerView: RecyclerView) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var dataList = ArrayList<Deliverly>()
    private var sparseArray = SparseArray<Boolean>()
    private var itemView = HashMap<Int,ConstraintLayout>()
    private var imageView = HashMap<Int, ImageView>()

    class ViewHolder (itemView: View,val recyclerView: RecyclerView, val dataList : ArrayList<Deliverly>) : RecyclerView.ViewHolder(itemView){
        private var name = itemView.findViewById<TextView>(R.id.name)
        private var local = itemView.findViewById<TextView>(R.id.local)
        private var over1 = itemView.findViewById<TextView>(R.id.one)
        private var over2 = itemView.findViewById<TextView>(R.id.two)
        private var over3 = itemView.findViewById<TextView>(R.id.three)
        private var date1 = itemView.findViewById<TextView>(R.id.date1)
        private var date2 = itemView.findViewById<TextView>(R.id.date2)
        private var date3 = itemView.findViewById<TextView>(R.id.date3)
        private var now = itemView.findViewById<TextView>(R.id.nowIng)
        private var total = itemView.findViewById<TextView>(R.id.total)
        private var parentView : ConstraintLayout = itemView.findViewById(R.id.itemParentView)
        private var childViewWrap : ConstraintLayout = itemView.findViewById(R.id.childViewWrap)
        private var item_arrow : ImageView  = itemView.findViewById(R.id.item_arrow)

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(sparseArray: SparseArray<Boolean>, itemView: HashMap<Int,ConstraintLayout>, imageView: HashMap<Int, ImageView>){
            if (sparseArray[adapterPosition] == null){
                sparseArray.put(adapterPosition,false)
            }

            name.text = dataList[adapterPosition].name
            local.text = dataList[adapterPosition].local
            over1.text = dataList[adapterPosition].overflows[0].toString()
            over2.text = dataList[adapterPosition].overflows[1].toString()
            over3.text = dataList[adapterPosition].overflows[2].toString()
            date1.text = dataList[adapterPosition].dates[0]
            date2.text = dataList[adapterPosition].dates[1]
            date3.text = dataList[adapterPosition].dates[2]
            now.text = dataList[adapterPosition].now.toString()
            total.text = dataList[adapterPosition].total.toString()
            itemView[adapterPosition] = childViewWrap
            imageView[adapterPosition] = item_arrow

            if (!sparseArray[adapterPosition]){
                collapseItem(itemView[adapterPosition]!!,imageView[adapterPosition]!!)
            }else{
                expandItem(itemView[adapterPosition]!!,imageView[adapterPosition]!!)
            }

            parentView.setOnClickListener {
                TransitionManager.beginDelayedTransition(childViewWrap,AutoTransition())

                when(childViewWrap.visibility){
                    View.VISIBLE -> {
                        sparseArray[adapterPosition] = false
                        childViewWrap.visibility = View.GONE
                        collapseItem(childViewWrap, item_arrow)
                    }

                    View.GONE ->{
                        sparseArray[adapterPosition] = true
                        recyclerView.smoothScrollToPosition(adapterPosition)
                        childViewWrap.visibility = View.VISIBLE
                        expandItem(childViewWrap,item_arrow)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_child_control_item,parent,false),recyclerView,dataList
        )
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sparseArray,itemView,imageView)
    }

    override fun getItemCount(): Int {
        return if (dataList.size>0) dataList.size else 0
    }

    fun addItem(dataList: ArrayList<Deliverly>){
        this.dataList = dataList
    }

}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@SuppressLint("UseCompatLoadingForDrawables")
fun expandItem(view:View, imageView: ImageView){
    view.visibility = View.VISIBLE
    imageView.apply {
        setImageDrawable(this.context.resources.getDrawable(R.drawable.ic_baseline_expand_less_24,null))
    }
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@SuppressLint("UseCompatLoadingForDrawables")
fun collapseItem(view:View, imageView: ImageView){
    view.visibility = View.GONE
    imageView.apply {
        setImageDrawable(this.context.resources.getDrawable(R.drawable.ic_baseline_expand_more_24,null))
    }
}