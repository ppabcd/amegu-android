package id.rezajuliandri.amegu.data;

import id.rezajuliandri.amegu.data.repository.pet.PetRepository;
import id.rezajuliandri.amegu.data.repository.user.UserRepository;
import id.rezajuliandri.amegu.data.repository.location.LocationRepository;

/**
 * Interface AmeguRepository yang dibagi menjadi beberapa repository
 */
public interface AmeguDataSource {
    LocationRepository locationRepository();

    UserRepository userRepository();

    PetRepository petRepository();
}
