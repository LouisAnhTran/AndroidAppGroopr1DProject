package Groopr.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Groopr.R;

import java.util.ArrayList;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.MyViewHolder>{
    Context context;

    private ArrayList<Student> studentList;

    private ArrayList<Integer> listImage;

    private final ApplicationInterface applicationInterface;



    public ApplicationAdapter(Context context, ArrayList<Student> studentList,ApplicationInterface applicationInterface){
        this.context=context;
        this.studentList=studentList;
        this.listImage=new ArrayList<Integer>();
        this.applicationInterface=applicationInterface;
        listImage=new ArrayList<Integer>();
        listImage.add(R.drawable.robot1);
        listImage.add(R.drawable.robot2);
        listImage.add(R.drawable.robot3);
        listImage.add(R.drawable.robot4);
        listImage.add(R.drawable.robot5);
        listImage.add(R.drawable.robot6);
        listImage.add(R.drawable.robot7);
        listImage.add(R.drawable.robot8);

    }

    @NonNull
    @Override
    public ApplicationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /** This is where you can inflate the layout (Giving a look to our rows) **/
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.application_recycle_view,parent,false);
        return new ApplicationAdapter.MyViewHolder(view,this.applicationInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.fullNameApplication.setText(this.studentList.get(position).getFullName());
        holder.userNameApplication.setText(this.studentList.get(position).getUserName());
        holder.skillApplication.setText(this.studentList.get(position).getSkillSet());
        holder.imageApplication.setImageResource(this.listImage.get(position%this.listImage.size()));
    }


    @Override
    public int getItemCount() {
        /** Count how many items you have in total **/
        return this.studentList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView fullNameApplication;
        private TextView userNameApplication;
        private TextView skillApplication;
        private TextView acceptButton;
        private TextView rejectButton;
        private ImageView imageApplication;

        public MyViewHolder(@NonNull View itemView,ApplicationInterface applicationInterface) {
            super(itemView);

            fullNameApplication=itemView.findViewById(R.id.fullNameApplicationRecycle);
            userNameApplication=itemView.findViewById(R.id.userNameApplication);
            skillApplication=itemView.findViewById(R.id.skillApplication);
            acceptButton=itemView.findViewById(R.id.buttonAcceptApplication);
            rejectButton=itemView.findViewById(R.id.buttonRejectApplication);
            imageApplication=itemView.findViewById(R.id.imageApplication);

            acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(applicationInterface!=null){
                        int pos=getAdapterPosition();

                        if(pos!=RecyclerView.NO_POSITION){
                            applicationInterface.clickAcceptButton(pos);
                            acceptButton.setText("ACCEPTED");
                            rejectButton.setVisibility(View.GONE);
                        }

                    }
                }
            });

            rejectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(applicationInterface!=null){
                        int pos=getAdapterPosition();

                        if(pos!=RecyclerView.NO_POSITION){
                            applicationInterface.clickRejectButton(pos);
                            acceptButton.setVisibility(view.GONE);
                            rejectButton.setText("REJECTED");
                        }
                    }
                }
            });



        }
    }




}
