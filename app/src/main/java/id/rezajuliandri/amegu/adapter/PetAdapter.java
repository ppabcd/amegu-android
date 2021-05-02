package id.rezajuliandri.amegu.adapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.databinding.ItemPetRowBinding;
import id.rezajuliandri.amegu.entity.Pet;
import id.rezajuliandri.amegu.helper.StringHelper;
import id.rezajuliandri.amegu.ui.main.abstraction.ItemDetailAbstract;

/**
 * Adapter yang digunakan untuk menampilkan data pet
 */
public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ListViewHolder> {
    private final View viewParent;
    private final Application application;
    private final ItemDetailAbstract itemDetailAbstract;
    ItemPetRowBinding itemPetRowBinding;
    private List<Pet> petList = new ArrayList<>();

    public PetAdapter(Application application, ItemDetailAbstract itemDetailAbstract, View viewParent) {
        this.application = application;
        this.viewParent = viewParent;
        this.itemDetailAbstract = itemDetailAbstract;
    }
    
    public void setData(List<Pet> pets) {
        this.petList = pets;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemPetRowBinding = ItemPetRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListViewHolder(itemPetRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Pet pet = petList.get(position);
        holder.bind(pet);
    }

    private void setRoundedImage(ListViewHolder holder) {
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setBottomLeftCorner(CornerFamily.ROUNDED, application.getResources().getDimension(R.dimen.rounded_image))
                .setBottomRightCorner(CornerFamily.ROUNDED, application.getResources().getDimension(R.dimen.rounded_image))
                .build();
        holder.binding.petImage.setShapeAppearanceModel(shapeAppearanceModel);
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ItemPetRowBinding binding;

        public ListViewHolder(@NonNull ItemPetRowBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(Pet pet) {
            binding.petName.setText(StringHelper.setMaximumText(pet.getNamaHewan(), 15));
            setRoundedImage(this);
            Glide.with(itemView.getContext())
                    .load(pet.getAttachment().getUrl())
                    .apply(new RequestOptions())
                    .into(binding.petImage);
            String price = StringHelper.currency(pet.getHarga());
            binding.petPrice.setText(price);
            itemView.setOnClickListener(v -> itemDetailAbstract.moveToDetailPet(viewParent, pet));
        }
    }
}
