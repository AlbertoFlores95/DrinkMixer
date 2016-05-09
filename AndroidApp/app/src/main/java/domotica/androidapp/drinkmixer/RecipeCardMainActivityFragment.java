package domotica.androidapp.drinkmixer;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeCardMainActivityFragment extends Fragment  {

    private CardView cv;
    private ImageView personPhoto;
    private TextView recipeCardTextView;
    private TextView personAge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_card_main_activity, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cv = (CardView) view.findViewById(R.id.cv);
        personPhoto = (ImageView) view.findViewById(R.id.person_photo);
        recipeCardTextView = (TextView) view.findViewById(R.id.recipe_card_textView);
        personAge = (TextView) view.findViewById(R.id.person_age);
    }

}
