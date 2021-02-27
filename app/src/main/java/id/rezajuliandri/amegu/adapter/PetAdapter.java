package id.rezajuliandri.amegu.adapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.entity.Pet;
import id.rezajuliandri.amegu.ui.main.abstraction.ItemDetailAbstract;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ListViewHolder> {
    private final View viewParent;
    private final Context context;
    private final Application application;
    private final ItemDetailAbstract itemDetailAbstract;
    private List<Pet> petList = new ArrayList<>();

    public PetAdapter(Context context, Application application, ItemDetailAbstract itemDetailAbstract, View viewParent) {
        this.application = application;
        this.context = context;
        this.viewParent = viewParent;
        this.itemDetailAbstract = itemDetailAbstract;
    }

    public void setData(List<Pet> pets) {
        this.petList = pets;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pet_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Pet pet = petList.get(position);
        holder.petName.setText(pet.getPetName());
        setRoundedImage(holder);
        holder.itemView.setOnClickListener(v -> itemDetailAbstract.moveToDetailPet(viewParent, pet));
    }

    private void setRoundedImage(ListViewHolder holder) {
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setBottomLeftCorner(CornerFamily.ROUNDED, application.getResources().getDimension(R.dimen.rounded_image))
                .setBottomRightCorner(CornerFamily.ROUNDED, application.getResources().getDimension(R.dimen.rounded_image))
                .build();
        holder.petImage.setShapeAppearanceModel(shapeAppearanceModel);
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView petName;
        ShapeableImageView petImage;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            petName = itemView.findViewById(R.id.pet_name);
            petImage = itemView.findViewById(R.id.pet_image);
        }
    }
}
