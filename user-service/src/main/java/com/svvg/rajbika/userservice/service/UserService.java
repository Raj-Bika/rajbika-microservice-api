package com.svvg.rajbika.userservice.service;

import com.svvg.rajbika.userservice.dto.CreateUserAddressRequest;
import com.svvg.rajbika.sharedservice.dto.CreateUserRequest;
import com.svvg.rajbika.userservice.dto.UserAddressListResponse;
import com.svvg.rajbika.userservice.dto.UserResponse;
import com.svvg.rajbika.userservice.model.Address;
import com.svvg.rajbika.userservice.model.User;
import com.svvg.rajbika.userservice.reporsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;
    private static final String CART_SERVICE_URL = "http://localhost:8088/api/v1/users/{user-id}/cart";

    public void createUser(CreateUserRequest userRequest) throws Exception {
        User user = User.builder()
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();
//        Cart cart = Cart.builder().userId(user.getId()).build();
//        cartRepository.save(cart);
//
        this.userRepository.save(user);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(CART_SERVICE_URL, null, String.class, user.getId());
        if(!responseEntity.getStatusCode().isSameCodeAs(HttpStatus.CREATED)) {
            throw new Exception("Cart could not be created for the user! Please try again");
        }


        log.info("User {} saved successfully", user.getId());
    }

    public List<UserResponse> getAllUsers() {
        return this.userRepository.findAll().stream().map(this::mapToUserResponse).toList();
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public Optional<User> getUserById(String userId) {
        return this.userRepository.findById(userId);
    }

    public Boolean createAddressForUser(String userId, CreateUserAddressRequest createUserAddressRequest) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            Address address = Address.builder()
                    .id(UUID.randomUUID().toString())
                    .addressLine1(createUserAddressRequest.getAddressLine1())
                    .addressLine2(createUserAddressRequest.getAddressLine2())
                    .city(createUserAddressRequest.getCity())
                    .state(createUserAddressRequest.getState())
                    .pinCode(createUserAddressRequest.getPinCode())
                    .build();
            if(user.getAddressList() != null && user.getAddressList().stream().toArray().length > 0) {
                user.getAddressList().add(address);
            }else {
                List<Address> newAddressList = new ArrayList<>();
                newAddressList.add(address);
                user.setAddressList(newAddressList);
            }
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public List<UserAddressListResponse> getUserAddresses(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        return optionalUser.map(User::getAddresses).orElse(List.of()).stream().map(this::mapToUserAddressList).toList();
    }

    private UserAddressListResponse mapToUserAddressList(Address addresses) {
        UserAddressListResponse userAddressListResponse = UserAddressListResponse.builder()
                .id(addresses.getId())
                .addressLine1(addresses.getAddressLine1())
                .addressLine2(addresses.getAddressLine2())
                .city(addresses.getCity())
                .state(addresses.getState())
                .pinCode(addresses.getPinCode())
                .build();

        return userAddressListResponse;
    }

    public String createUserWithPhoneNumber(String phoneNumber)  {
        return "";
    }
}
