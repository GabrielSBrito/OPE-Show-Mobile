package br.com.cacaushow.controledeestoquecacaushow

import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import br.com.cacaushow.controledeestoquecacaushow.R.id.parent
import com.squareup.picasso.Picasso

class KitAdapter(val kits: List<Kit>, val onClick: (Kit) -> Unit):RecyclerView.Adapter<KitAdapter.KitsViewHolder>() {
    override fun onBindViewHolder(holder: KitsViewHolder, position: Int) {
        val contexto = holder.itemView.context

        val kit = kits[position]
        holder.cardNome.text = kit.nome
        holder.cardProgress.visibility = View.VISIBLE

        holder.itemView.setOnClickListener { onClick(kit) }

        // download da imagem
        Picasso.with(contexto).load(kit.foto).fit().into(holder.cardImg,object: com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.GONE
                    }

                    override fun onError() {
                        holder.cardProgress.visibility = View.GONE
                    }
                })
    }



    override fun getItemCount() = kits.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_kit,parent,false)
        val holder = KitsViewHolder(view)
        return holder

    }

    class KitsViewHolder(view: View):RecyclerView.ViewHolder(view){
        val cardNome: TextView
        val cardImg:ImageView
        val cardProgress: ProgressBar
        val cardView: CardView

        init{
            cardNome = view.findViewById(R.id.cardNome)
            cardImg = view.findViewById(R.id.cardImg)
            cardProgress = view.findViewById(R.id.cardProgress)
            cardView = view.findViewById(R.id.card_kit)
        }
    }

}