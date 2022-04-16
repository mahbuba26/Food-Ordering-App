package com.mahbuba.foodappnew.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mahbuba.foodappnew.PointValue;
import com.mahbuba.foodappnew.R;

public class PointCartHolder extends FirebaseRecyclerAdapter<PointValue,PointCartHolder.myViewHolder>{

    //private Context context;
    DatabaseReference reference,reference2;
FirebaseAuth fAuth;
    public PointCartHolder(@NonNull FirebaseRecyclerOptions<PointValue> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull PointValue model) {
        holder.Item.setText( "Item    :  "+model.getItem());
        holder.Piece.setText("Piece  :  "+model.getPieces().toString());
        holder.Price.setText("Price  :  "+model.getPrice().toString());
        holder.Date.setText("Date   :  "+model.getDate());

        myViewHolder viewHolder=(myViewHolder) holder;
      /*  holder.txt_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        holder.txt_option.setOnClickListener(v->
        {
            PopupMenu popupMenu =new PopupMenu(v.getContext(), holder.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item->
            {
                switch (item.getItemId())
                {
                    case R.id.menu_remove:

                        fAuth= FirebaseAuth.getInstance();
                        String username =fAuth.getCurrentUser().getUid();
                        if (!username.isEmpty()){

                             FirebaseDatabase.getInstance().getReference()
                                    .child(username)
                                    .child(getRef(position).getKey())
                                    .setValue(null);

                        //    reference2.removeValue();

                        }

                        break;
                }
                return false;
            });
            popupMenu.show();
        });
    }



   // }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout,parent,false);
        return new myViewHolder(view);


    }

    class myViewHolder extends  RecyclerView.ViewHolder{
            TextView Item , Price, Piece,Date;
            ImageView txt_option;

            public myViewHolder(@NonNull final View itemView) {
                super(itemView);
                Date=itemView.findViewById(R.id.data);
                Item = (TextView) itemView.findViewById(R.id.data_id2);
                Piece = (TextView)itemView.findViewById(R.id.data_name2);
                Price=(TextView)itemView.findViewById(R.id.data_age2);
                txt_option = itemView.findViewById(R.id.delete);

            }
    }

}