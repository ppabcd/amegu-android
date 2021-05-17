package id.rezajuliandri.amegu.ui.home;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.databinding.ItemPetRowBinding;
import id.rezajuliandri.amegu.utils.ItemDetailAbstract;
import id.rezajuliandri.amegu.utils.StringHelper;

public class HomePetAdapter extends RecyclerView.Adapter<HomePetAdapter.ListViewHolder> {
    private final View viewParent;
    private final Application application;
    private final ItemDetailAbstract itemDetailAbstract;
    ItemPetRowBinding itemPetRowBinding;
    private List<PetEntity> petList = new ArrayList<>();

    public HomePetAdapter(Application application, ItemDetailAbstract itemDetailAbstract, View viewParent) {
        this.application = application;
        this.viewParent = viewParent;
        this.itemDetailAbstract = itemDetailAbstract;
    }

    public void setData(List<PetEntity> pets) {
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
        PetEntity pet = petList.get(position);
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

        public void bind(PetEntity pet) {
            binding.petName.setText(StringHelper.setMaximumText(pet.getNamaHewan(), 15));
            setRoundedImage(this);
            Glide.with(itemView.getContext())
                    .load(pet.getAttachmentUrl())
                    .apply(new RequestOptions())
                    .into(binding.petImage);
            String price = StringHelper.currency(pet.getHarga());
            binding.petPrice.setText(price);
            itemView.setOnClickListener(v -> itemDetailAbstract.moveToDetailPet(viewParent, pet));
        }
    }
}