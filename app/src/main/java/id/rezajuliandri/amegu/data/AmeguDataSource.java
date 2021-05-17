package id.rezajuliandri.amegu.data;

import id.rezajuliandri.amegu.data.repository.PetRepository;
import id.rezajuliandri.amegu.data.repository.UserRepository;
import id.rezajuliandri.amegu.data.repository.location.LocationRepository;

public interface AmeguDataSource {
    LocationRepository locationRepository();

    UserRepository userRepository();

    PetRepository petRepository();
}
