package com.mahbuba.foodappnew;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.type.LatLng;
import com.mahbuba.foodappnew.Interface.SubCategoryOnClickInterface;
import com.mahbuba.foodappnew.ViewHolder.PointCartHolder;

import java.util.List;
import java.util.Locale;

public class RiderAdapter extends FirebaseRecyclerAdapter<
        RiderModel, RiderAdapter.personsViewholder> {

String Phone;

    DatabaseReference reference,reference2;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
        public RiderAdapter(@NonNull FirebaseRecyclerOptions<RiderModel> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull RiderAdapter.personsViewholder holder, int position, @NonNull RiderModel model) {
        holder.Add.setText(model.getAddress());
        holder.St.setText(model.getDate());
        holder.Phn.setText(model.getPhone());
        holder.Dt.setText(model.getStatus());
holder.del.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        fAuth=FirebaseAuth.getInstance();
        // String fAuth="lvrH8O8rm5eTJy2t6DNdCagJp7T2";



        String username=model.getStatus();
        //  String username =fAuth.getCurrentUser().getUid();



        if (!username.isEmpty()){

            database = FirebaseDatabase.getInstance();
            reference = database.getReference("Address");
            //   reference2 = FirebaseDatabase.getInstance().getReference(username);
            // reference.child(username).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            reference.child(username).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()){

                        Toast.makeText(view.getContext(),"Successfuly Cleared !",Toast.LENGTH_SHORT).show();
                        //    binding.etusername.setText("");


                    }else {

                        Toast.makeText(view.getContext(),"Something went wrong ! Try again .",Toast.LENGTH_SHORT).show();


                    }

                }
            });

        }else{

            //   Toast.makeText(OrderActivity.this,"",Toast.LENGTH_SHORT).show();
        }
    }
});
        holder.Phn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:+88" + model.getPhone()));
                view.getContext().startActivity(i);
            }
        });
        holder.Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sSource = "Shekhertek 12, Adabor, Mohammadpur, Dhaka";
                String sdestination = (model.getAddress());

                //    DisplayTrack(sSource,sdestination);


                try{
                    Uri uri=Uri.parse("https://www.google.co.in/maps/dir/"+sSource+"/"+sdestination);
                    Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                    intent.setPackage("com.google.android.apps.maps");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);
                }
                catch (ActivityNotFoundException e){

                    Uri uri=Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                    Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);



                }
            }
        });
  /*    holder.SubCategoryInterfaceClick(new SubCategoryOnClickInterface() {
          @Override
          public void onClick(View view, boolean isLongPressed) {

              String sSource = "Shekhertek 12, Adabor, Mohammadpur, Dhaka";
              String sdestination = (model.getAddress());

              //    DisplayTrack(sSource,sdestination);


              try{
                  Uri uri=Uri.parse("https://www.google.co.in/maps/dir/"+sSource+"/"+sdestination);
                  Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                  intent.setPackage("com.google.android.apps.maps");
                  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  view.getContext().startActivity(intent);
              }
              catch (ActivityNotFoundException e){

                  Uri uri=Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                  Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  view.getContext().startActivity(intent);



              }
          }
      });


*/

    }



    @NonNull
    @Override
    public personsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rider_latout,parent,false);
        return new personsViewholder(view);
    }

    public class personsViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Add , Dt, Phn, St;
        ImageView del;
  //      public SubCategoryOnClickInterface subCategoryOnClickInterface;


            public personsViewholder(@NonNull View itemView) {
            super(itemView);
                del=itemView.findViewById(R.id.dell);
                Add=itemView.findViewById(R.id.adddd2);
                Dt = (TextView) itemView.findViewById(R.id.phonee2);
                Phn = (TextView)itemView.findViewById(R.id.sttta2);
                St=(TextView)itemView.findViewById(R.id.dateee2);
                itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           // subCategoryOnClickInterface.onClick(view,false);
        }
        public void SubCategoryInterfaceClick(SubCategoryOnClickInterface subCategoryOnClickInterface)
        {
            //this.subCategoryOnClickInterface = subCategoryOnClickInterface;
        }
    }
}
