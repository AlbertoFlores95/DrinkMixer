package domotica.androidapp.drinkmixer;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;
//
//public class RecipeCardMainActivityAdapter extends RecyclerView.Adapter<RecipeCardMainActivityAdapter.CustomViewHolder> {
//    private List<RecipeDetails> recipesList;
//    private Context mContext;
//
//    public RecipeCardMainActivityAdapter(Context context, List<RecipeDetails> recipesList) {
//        this.recipesList = recipesList;
//        this.mContext = context;
//    }
//
//    @Override
//    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_card_main_activity, null);
//
//        CustomViewHolder viewHolder = new CustomViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
//        RecipeDetails recipe = recipesList.get(i);
//
//        //Setting text view title
//        customViewHolder.title.setText(Html.fromHtml(recipe.getName()));
//    }
//
//    @Override
//    public int getItemCount() {
//        return (null != recipesList ? recipesList.size() : 0);
//    }
//
//    public class FeedListRowHolder extends RecyclerView.ViewHolder {
//        protected ImageView thumbnail;
//        protected TextView title;
//
//        public FeedListRowHolder(View view) {
//            super(view);
//            this.title = (TextView) view.findViewById(R.id.recipe_card_textView);
//        }
//
//    }
//}
//
////public class RecipeCardMainActivityAdapter extends BaseAdapter {
////
////    private List<RecipeDetails> objects = new ArrayList<RecipeDetails>();
////
////    private Context context;
////    private LayoutInflater layoutInflater;
////
////    public RecipeCardMainActivityAdapter(Context context, List<RecipeDetails> objects) {
////        this.layoutInflater = LayoutInflater.from(context);
////        this.objects = objects;
////    }
////
////    @Override
////    public int getCount() {
////        return objects.size();
////    }
////
////    @Override
////    public RecipeDetails getItem(int position) {
////        return objects.get(position);
////    }
////
////    @Override
////    public long getItemId(int position) {
////        return position;
////    }
////
////    @Override
////    public View getView(int position, View convertView, ViewGroup parent) {
////        if (convertView == null) {
////            convertView = layoutInflater.inflate(R.layout.recipe_card_main_activity, null);
////            convertView.setTag(new ViewHolder(convertView));
////        }
////        initializeViews((RecipeDetails)getItem(position), (ViewHolder) convertView.getTag());
////        return convertView;
////    }
////
////    private void initializeViews(RecipeDetails object, ViewHolder holder) {
////        //TODO implement
////
////        holder.recipeCardTextView.setText(object.getName());
////
////
////    }
////
////    protected class ViewHolder {
////        private CardView cv;
////    private ImageView personPhoto;
////    private TextView recipeCardTextView;
////    private TextView personAge;
////
////        public ViewHolder(View view) {
////            cv = (CardView) view.findViewById(R.id.cv);
////            personPhoto = (ImageView) view.findViewById(R.id.person_photo);
////            recipeCardTextView = (TextView) view.findViewById(R.id.recipe_card_textView);
////            personAge = (TextView) view.findViewById(R.id.person_age);
////        }
////    }
////}
