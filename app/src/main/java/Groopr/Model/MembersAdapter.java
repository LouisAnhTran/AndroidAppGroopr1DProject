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

import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MembersHolder> {
    Context context; // which activity you're in
    List<String> membersList;
    LayoutInflater mInflater;

    /**
     * Constructor
     */
    public MembersAdapter(Context context, List<String> membersList) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.membersList = membersList;
    }

    @NonNull
    @Override
    // standard code
    public MembersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.manage_grp_recyclerrow,parent,false);
        return new MembersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MembersHolder holder, int position) {
        //TODO 2 get the data point at position from the data source and assign it to the Viewholder
        holder.getMemberName().setText(membersList.get(position));
        holder.getMemberImage().setImageResource(R.drawable.robot1);
    }

    @Override
    public int getItemCount() {
        // TODO 3 return the number of data points
        return membersList.size();
    }

    public class MembersHolder extends RecyclerView.ViewHolder{

        private TextView memberName;
        private ImageView memberImage;

        public MembersHolder(@NonNull View itemView) {
            super(itemView);
            memberName = itemView.findViewById(R.id.MemberText);
            memberImage = itemView.findViewById(R.id.MemberImage);


            // TODO 4 Write code to detect a long click
            /*** detecting a longClick and deleting the list item
             * 1 call setOnLongClickListener on itemView
             * 2 get the position that is being long-clicked
             * by calling getAbsoluteAdapterPosition
             * 3 with the position, remove the data
             * 4 notify the recyclerview by calling notifyDataSetChanged() */
        }

        public TextView getMemberName() {
            return memberName;
        }
        public ImageView getMemberImage() {
            return memberImage;
        }
    }
}