package id.rezajuliandri.amegu.interfaces;

import id.rezajuliandri.amegu.api.responses.data.pet.Attachment;

public interface OnImageUploaded {
    void success(Attachment dataImage);
    void error(String message);
}
