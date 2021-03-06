package com.example.maimyou.Adapters;

import android.content.Context;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.maimyou.Activities.SubjectsActivity;
import com.example.maimyou.Classes.SubjectReviews;
import com.example.maimyou.Classes.Trimester;
import com.example.maimyou.R;

import java.util.ArrayList;

public class SubjectReviewsAdapter extends ArrayAdapter<SubjectReviews> {

    private Context mContext;
    private int mResource;
    SubjectsActivity subjectsActivity;

    public void setSubjectsActivity(SubjectsActivity subjectsActivity) {
        this.subjectsActivity = subjectsActivity;
    }

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView SubjectName,SubjectCategory, SubjectCode, SubjectRate, SubjectUsers;
        LinearLayout subjectFinished, subjectFinished2;
        CardView subject;
    }

    /**
     * Default constructor for the PersonListAdapter
     * //     *
     * //     * @param context
     * //     * @param resource
     * //     * @param objects
     * //
     */
    public SubjectReviewsAdapter(Context context, int resource, ArrayList<SubjectReviews> items) {
        super(context, resource, items);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Spannable SubjectName = getItem(position).getSubjectName();
        String SubjectCategory = getItem(position).getCategory();
        Spannable SubjectCode = getItem(position).getSubjectCode();
        String SubjectRate = getItem(position).getSubjectRate();
        String SubjectUsers = getItem(position).getSubjectUsers();
        boolean isFinished = getItem(position).isFinished();

        SubjectReviews subject = new SubjectReviews(SubjectName,SubjectCategory, SubjectCode.toString(), SubjectRate, SubjectUsers, isFinished);
        //ViewHolder object
        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.SubjectName = convertView.findViewById(R.id.SubjectName);
            holder.SubjectCategory = convertView.findViewById(R.id.SubjectCategory);
            holder.SubjectCode = convertView.findViewById(R.id.SubjectCode);
            holder.SubjectRate = convertView.findViewById(R.id.SubjectRate);
            holder.SubjectUsers = convertView.findViewById(R.id.SubjectUsers);
            holder.subjectFinished = convertView.findViewById(R.id.subjectFinished);
            holder.subjectFinished2 = convertView.findViewById(R.id.subjectFinished2);
            holder.subject = convertView.findViewById(R.id.subject);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.SubjectName.setText(SubjectName);
        holder.SubjectCode.setText(SubjectCode);
        holder.SubjectCategory.setText(subject.getCategory());
        holder.subject.setOnClickListener(v -> subjectsActivity.itemClicked(SubjectCode.toString()));

        if (subject.isFinished()) {
            holder.subjectFinished.setVisibility(View.VISIBLE);
            holder.subjectFinished2.setVisibility(View.GONE);
        } else {
            holder.subjectFinished.setVisibility(View.GONE);
            holder.subjectFinished2.setVisibility(View.VISIBLE);
            holder.SubjectRate.setText(subject.getSubjectRate());
            holder.SubjectUsers.setText(subject.getSubjectUsers());
        }

        return convertView;
    }
}