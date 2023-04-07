package Groopr.Model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Groopr.R;

import java.util.ArrayList;
import java.util.List;

public class MyGroupsAdapter extends RecyclerView.Adapter<MyGroupsAdapter.ViewHolder> {

    Context context;
    private List<ProjectSupport> projectList;
    ArrayList<Integer> listImage;

    private final ShowGroupRecycleViewInterface showGroupRecycleViewInterface;

    int randomNumber;

    public MyGroupsAdapter(Context context, List<ProjectSupport> projectList,ShowGroupRecycleViewInterface showGroupRecycleViewInterface) {
        Log.d("MyGroupsAdapter", "constructor called");
        this.context = context;
        this.projectList = projectList;
        this.showGroupRecycleViewInterface=showGroupRecycleViewInterface;
        listImage=new ArrayList<Integer>();
        listImage.add(R.drawable.robot1);
        listImage.add(R.drawable.robot2);
        listImage.add(R.drawable.robot3);
        listImage.add(R.drawable.robot4);
        listImage.add(R.drawable.robot5);
        listImage.add(R.drawable.robot6);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for the RecyclerView
        /** This is where you can inflate the layout (Giving a look to our rows) **/
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_groups_item,parent,false);
        return new MyGroupsAdapter.ViewHolder(view, showGroupRecycleViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set the project name and other relevant information in the ViewHolder
        holder.textViewModuleID.setText(this.projectList.get(position).getModuleName());
        holder.textViewShowSkills.setText(this.projectList.get(position).getSkillNeeded());
        holder.textViewGroupName.setText(this.projectList.get(position).getProjectName());
        holder.textViewNumberMembers.setText(String.valueOf(this.projectList.get(position).getStudentList().size()));
        holder.imageGroup.setImageResource(listImage.get(position%listImage.size()));
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageGroup;
        TextView textViewModuleID;
        TextView textViewNumberMembers;
        TextView textViewGroupName;
        TextView textViewShowSkills;
        public ViewHolder(@NonNull View itemView, ShowGroupRecycleViewInterface showGroupRecycleViewInterface) {
            super(itemView);
            imageGroup=itemView.findViewById(R.id.groupPhoto);
            textViewModuleID =itemView.findViewById(R.id.moduleID);
            textViewGroupName =itemView.findViewById(R.id.groupName);
            textViewNumberMembers=itemView.findViewById(R.id.groupMember);
            textViewShowSkills=itemView.findViewById(R.id.skillNeeded);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (showGroupRecycleViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            showGroupRecycleViewInterface.OnItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
