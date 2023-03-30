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

public class RecruitmentAdapter extends RecyclerView.Adapter<RecruitmentAdapter.MyViewHolder>{
    Context context;
    ArrayList<Project> listProject;

    public RecruitmentAdapter(Context context, ArrayList<Project> projectList){
        this.context=context;
        this.listProject=projectList;
    }

    @NonNull
    @Override
    public RecruitmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /** This is where you can inflate the layout (Giving a look to our rows) **/
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recycle_view_row_for_recruiment_show_group,parent,false);
        return new RecruitmentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecruitmentAdapter.MyViewHolder holder, int position) {
        /** Asssigning values to the views we created in the recycle_view.xml layout file **/
        /** based on the position of the recycle view  **/
        holder.textViewGroupName.setText(this.listProject.get(position).getProjectName());
        holder.textViewShowSkills.setText(this.listProject.get(position).getSkillNeeded());
        holder.textViewgroupDescription.setText(this.listProject.get(position).getMessage());
        holder.textViewNumberMembers.setText(String.valueOf(this.listProject.get(position).getStudentList().size()));

    }

    @Override
    public int getItemCount() {
        /** Count how many items you have in total **/
        return this.listProject.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageGroup;
        TextView textViewGroupName;
        TextView textViewNumberMembers;
        TextView textViewgroupDescription;
        TextView textViewShowSkills;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageGroup=itemView.findViewById(R.id.groupPhoto);
            textViewGroupName=itemView.findViewById(R.id.groupName);
            textViewgroupDescription=itemView.findViewById(R.id.groupDescription);
            textViewNumberMembers=itemView.findViewById(R.id.groupMember);
            textViewShowSkills=itemView.findViewById(R.id.skillNeeded);


        }
    }
}
