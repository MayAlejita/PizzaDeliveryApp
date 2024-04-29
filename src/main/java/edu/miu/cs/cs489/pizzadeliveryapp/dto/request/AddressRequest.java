package edu.miu.cs.cs489.pizzadeliveryapp.dto.request;

import jakarta.validation.constraints.NotBlank;

public class AddressRequest
//        (
//        Integer addressId,
//        @NotBlank(message = "street shouldn't be blank")
//        String street,
//        String city,
//        @NotBlank(message = "state shouldn't be blank")
//        String state,
//        String zipCode
//)
{
        Integer addressId;
        @NotBlank(message = "street shouldn't be blank")
        String street;
        String city;
        @NotBlank(message = "state shouldn't be blank")
        String state;
        String zipCode;

        public AddressRequest(Integer addressId, String street, String city, String state, String zipCode) {
                this.addressId = addressId;
                this.street = street;
                this.city = city;
                this.state = state;
                this.zipCode = zipCode;
        }

        public Integer getAddressId() {
                return addressId;
        }

        public void setAddressId(Integer addressId) {
                this.addressId = addressId;
        }

        public String getStreet() {
                return street;
        }

        public void setStreet(String street) {
                this.street = street;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state;
        }

        public String getZipCode() {
                return zipCode;
        }

        public void setZipCode(String zipCode) {
                this.zipCode = zipCode;
        }
}
