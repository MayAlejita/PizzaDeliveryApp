package edu.miu.cs.cs489.pizzadeliveryapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CustomerRequest
//        (
//        Integer customerId,
//        @NotBlank(message = "firstName shouldn't be blank")
//        String firstName,
//        @NotBlank(message = "lastName shouldn't be blank")
//        String lastName,
//        @NotBlank(message = "phoneNumber shouldn't be blank")
//        String phoneNumber,
//        String email,
//        @NotNull(message = "birthDate shouldn't be null")
//        LocalDate birthDate,
//        @NotNull(message = "address shouldn't be null")
//        AddressRequest address
//)
        {

                Integer customerId;
                @NotBlank(message = "firstName shouldn't be blank")
                String firstName;
                @NotBlank(message = "lastName shouldn't be blank")
                String lastName;
                @NotBlank(message = "phoneNumber shouldn't be blank")
                String phoneNumber;
                String email;
                @NotNull(message = "birthDate shouldn't be null")
                LocalDate birthDate;
                @NotNull(message = "address shouldn't be null")
                AddressRequest address;

                public CustomerRequest(Integer customerId, String firstName, String lastName, String phoneNumber, String email, LocalDate birthDate, AddressRequest address) {
                        this.customerId = customerId;
                        this.firstName = firstName;
                        this.lastName = lastName;
                        this.phoneNumber = phoneNumber;
                        this.email = email;
                        this.birthDate = birthDate;
                        this.address = address;
                }

                public Integer getCustomerId() {
                        return customerId;
                }

                public void setCustomerId(Integer customerId) {
                        this.customerId = customerId;
                }

                public String getFirstName() {
                        return firstName;
                }

                public void setFirstName(String firstName) {
                        this.firstName = firstName;
                }

                public String getLastName() {
                        return lastName;
                }

                public void setLastName(String lastName) {
                        this.lastName = lastName;
                }

                public String getPhoneNumber() {
                        return phoneNumber;
                }

                public void setPhoneNumber(String phoneNumber) {
                        this.phoneNumber = phoneNumber;
                }

                public String getEmail() {
                        return email;
                }

                public void setEmail(String email) {
                        this.email = email;
                }

                public LocalDate getBirthDate() {
                        return birthDate;
                }

                public void setBirthDate(LocalDate birthDate) {
                        this.birthDate = birthDate;
                }

                public AddressRequest getAddress() {
                        return address;
                }

                public void setAddress(AddressRequest address) {
                        this.address = address;
                }
        }
