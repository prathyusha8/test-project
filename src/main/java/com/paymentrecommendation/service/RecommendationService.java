package com.paymentrecommendation.service;

import com.paymentrecommendation.enums.LineOfBusiness;
import com.paymentrecommendation.models.Cart;
import com.paymentrecommendation.models.PaymentInstrument;
import com.paymentrecommendation.models.User;
import com.paymentrecommendation.recommenders.CommerceRecommender;
import com.paymentrecommendation.recommenders.CreditCardBillRecommender;
import com.paymentrecommendation.recommenders.InvestmentRecommender;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecommendationService implements PaymentRecommender {
    @Override
    public List<PaymentInstrument> recommendPaymentInstruments(User user, Cart cart) {
        if(Objects.isNull(cart.getCartDetail().getCartItems()) || cart.getCartDetail().getCartItems().isEmpty() || cart.getCartDetail().getCartAmount() == 0.0){
            return new ArrayList<>();
        }
        if(!isValidUser(user)){
            return new ArrayList<>();
        }
        if(isvalidCart(cart)) {
            if (cart.getLineOfBusiness().equals(LineOfBusiness.COMMERCE)) {
                return new CommerceRecommender().recommendPaymentInstrumentsForType(user, cart);
            }
            if (cart.getLineOfBusiness().equals(LineOfBusiness.INVESTMENT)) {
                return new InvestmentRecommender().recommendPaymentInstrumentsForType(user, cart);
            }
            if (cart.getLineOfBusiness().equals(LineOfBusiness.CREDIT_CARD_BILL_PAYMENT)) {
                return new CreditCardBillRecommender().recommendPaymentInstrumentsForType(user, cart);
            }
        }
        return new ArrayList<>();
    }
    private boolean isvalidCart(Cart cart){
        if(Objects.isNull(cart) || Objects.isNull(cart.getCartDetail()) || Objects.isNull(cart.getCartDetail().getCartItems()) ||
                cart.getCartDetail().getCartItems().isEmpty() || cart.getCartDetail().getCartAmount() == 0.0){
            return false;
        }
        return true;
    }
    private boolean isValidUser(User user){
        if(Objects.isNull(user) || Objects.isNull(user.getUserPaymentInstrument()) || Objects.isNull(user.getUserPaymentInstrument().getPaymentInstruments()) ||
                user.getUserPaymentInstrument().getPaymentInstruments().isEmpty()){
            return false;
        }
        return true;
    }
}
