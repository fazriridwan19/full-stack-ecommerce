package com.ursklap.ecommerce.services;

import com.ursklap.ecommerce.dto.requests.AddressRequest;
import com.ursklap.ecommerce.dto.requests.ProfileUpdateRequest;
import com.ursklap.ecommerce.dto.responses.AddressResponse;
import com.ursklap.ecommerce.dto.responses.ProfileResponse;
import com.ursklap.ecommerce.models.Address;
import com.ursklap.ecommerce.models.CustomUserDetails;
import com.ursklap.ecommerce.models.User;
import com.ursklap.ecommerce.repositories.AddressRepository;
import com.ursklap.ecommerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfileService {
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private ModelMapper modelMapper;

    public ProfileResponse getProfile(CustomUserDetails userDetails) {
        User user = userDetails.getCredential().getUser();
        List<Address> addresses = user.getAddresses();
        ProfileResponse profileResponse = modelMapper.map(user, ProfileResponse.class);
        List<AddressResponse> addressResponses = new ArrayList<>();
        if (!addresses.isEmpty()) {
            addressResponses = addresses.stream().map((address) -> modelMapper.map(address, AddressResponse.class)).toList();
        }
        profileResponse.setAddresses(addressResponses);
        return  profileResponse;
    }

    public ProfileResponse updateProfile(CustomUserDetails userDetails, ProfileUpdateRequest request) {
        User user = userDetails.getCredential().getUser();
        int numberOfUpdatedField = 0;
        if (request.getName() != null && !request.getName().isBlank()) {
            user.setName(request.getName());
            numberOfUpdatedField++;
        }
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            user.setEmail(request.getEmail());
            numberOfUpdatedField++;
        }
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank()) {
            user.setPhoneNumber(request.getPhoneNumber());
            numberOfUpdatedField++;
        }
        if (numberOfUpdatedField > 0) {
            this.userRepository.save(user);
        }
        return this.getProfile(userDetails);
    }

    @Transactional
    public AddressResponse addAddress(CustomUserDetails userDetails, AddressRequest request) {
        User user = userDetails.getCredential().getUser();
        Address address = this.modelMapper.map(request, Address.class);
        address.setUser(user);
        if (address.getDefaultAddress()) {
            List<Address> addresses = user.getAddresses().stream().peek((addr) -> addr.setDefaultAddress(false)).toList();
            this.addressRepository.saveAll(addresses);
        }
        return modelMapper.map(this.addressRepository.save(address), AddressResponse.class);
    }

    public AddressResponse updateAddress(CustomUserDetails userDetails, AddressRequest request) {
        Address address = this.addressRepository
                .findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found"));
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setProvince(request.getProvince());
        address.setDefaultAddress(request.getDefaultAddress());
        address.setCode(request.getCode());
        User user = userDetails.getCredential().getUser();
        if (address.getDefaultAddress()) {
            List<Address> addresses = user.getAddresses().stream().peek((addr) -> addr.setDefaultAddress(false)).toList();
            this.addressRepository.saveAll(addresses);
        }
        return this.modelMapper.map(this.addressRepository.save(address), AddressResponse.class);
    }
}
