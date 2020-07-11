package com.example.backstack_bottomnavigation.adapters

import android.content.Context
import android.graphics.drawable.Drawable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.com.getin.Callbacks.CallBack
import com.example.backstack_bottomnavigation.R

class Adapter(var ctx:Context, var array:ArrayList<String>, var callBack: CallBack): RecyclerView.Adapter<Adapter.ViewHolderItem>(){
    override fun getItemCount(): Int {
    return array.size;
    }

    override fun onBindViewHolder(item : ViewHolderItem, pos: Int) {
      item.itemView.setOnClickListener {
          callBack.ItemClicked(pos)
      }
       item.user_name_text.setText(array.get(pos))
   /*     Glide.with(ctx)
                .load(Constants.baseurl +array.get(pos)?.qrcode_url)
                .apply(RequestOptions()
                        .diskCacheStrategy( DiskCacheStrategy.ALL )
                        .placeholder(R.color.white)).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        // profile_image_loading_progress.setVisibility(View.GONE)
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {

                        //  profile_image_loading_progress.setVisibility(View.GONE)
                        return false
                    }
                })
                .into(item.qr_code_imgv)

   */

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolderItem {
        val viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, null, false)
        viewHolder.setLayoutParams(RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT))
        return ViewHolderItem(viewHolder)
    }


    class ViewHolderItem(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {

         var user_image:ImageView
         var user_name_text:TextView
        init {

            user_image=itemLayoutView.findViewById(R.id.user_image)
            user_name_text=itemLayoutView.findViewById(R.id.user_name_text)
        }
    }
}