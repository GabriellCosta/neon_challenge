package neon.gabrielcosta.gvcneon.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import neon.gabrielcosta.gvcneon.R
import neon.gabrielcosta.gvcneon.entity.vo.PersonVO

class PeopleListAdapter(private val list: List<PersonVO>, private val function: View.OnClickListener) :
    RecyclerView.Adapter<PeopleListAdapter.PeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_people, parent, false)
        inflate.setOnClickListener(function)
        return PeopleViewHolder(inflate)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val personVO = list[position]

        holder.name.text = personVO.name
        holder.phone.text = personVO.phone
        Glide.with(holder.image).load(personVO.photo).into(holder.image)

    }

    class PeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.list_people_name)
        val phone: TextView = itemView.findViewById(R.id.list_people_phone)
        val image: ImageView = itemView.findViewById(R.id.list_people_image)
    }
}