//package domotica.androidapp.drinkmixer;
//
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.List;
//
///**
// * Created by Alberto Flores on 4/23/2016.
// */
//public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{
//
//    public static class PersonViewHolder extends RecyclerView.ViewHolder {
//        CardView cv;
//        TextView recipeName;
//        TextView personAge;
//        ImageView personPhoto;
//
//        PersonViewHolder(View itemView) {
//            super(itemView);
//            cv = (CardView)itemView.findViewById(R.id.cv);
//            recipeName = (TextView)itemView.findViewById(R.id.recipe_card_textView);
//            personAge = (TextView)itemView.findViewById(R.id.person_age);
//            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
//        }
//    }
//
//    List<RecipeDetails> Recipes;
//
//    RVAdapter(List<RecipeDetails> Recipes){
//        this.Recipes = Recipes;
//    }
//
//    @Override
//    public int getItemCount() {
//        return Recipes.size();
//    }
//
////    @Override
////    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
////        //View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_card_main_activity , false);
////        //PersonViewHolder pvh = new PersonViewHolder(v);
////        //return pvh;
////    }
//
//    @Override
//    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
//        personViewHolder.recipeName.setText(Recipes.get(i).getName());
//        personViewHolder.personAge.setText(Recipes.get(i).getDescription());
//        //personViewHolder.personPhoto.setImageResource(Recipes.get(i).photoId);
//    }
//
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//    }
//}