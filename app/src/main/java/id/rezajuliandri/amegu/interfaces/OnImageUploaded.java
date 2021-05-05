package id.rezajuliandri.amegu.interfaces;

import id.rezajuliandri.amegu.data.entity.pet.Attachment;

public interface OnImageUploaded {
    void success(Attachment dataImage);

    void error(String message);
}
