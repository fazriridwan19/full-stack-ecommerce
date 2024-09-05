package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.models.Address;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long> {
}
