package com.paymentrecommendation.recommenders;

import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.models.Cart;
import com.paymentrecommendation.models.PaymentInstrument;
import com.paymentrecommendation.models.User;
import com.paymentrecommendation.rules.*;
import com.paymentrecommendation.service.PaymentRecommender;
import com.paymentrecommendation.service.RecommendationService;
import com.paymentrecommendation.utils.SortByRelevanceScore;
import com.paymentrecommendation.utils.Utils;

import java.util.*;

public class CreditCardBillRecommender extends RecommendationService implements PaymentRecommender {

    public List<PaymentInstrument> recommendPaymentInstrumentsForType(User user, Cart cart) {

        List<PaymentInstrument> paymentInstruments = new ArrayList<>(user.getUserPaymentInstrument().getPaymentInstruments());
        boolean upiEnabled = user.getUserContext()!=null && user.getUserContext().getDeviceContext()!=null &&
                user.getUserContext().getDeviceContext().isUpiEnabled();
        return getPaymentInstruments(paymentInstruments, cart, upiEnabled);
    }

    private List<PaymentInstrument> getPaymentInstruments(List<PaymentInstrument> userPaymentInstruments, Cart cart, boolean upiEnabled){
        List<PaymentInstrument> paymentInstruments = new ArrayList<>();

        List<PaymentInstrumentType> CreditCardBillPaymentInstruments =  CreditCardBillRelevance.creditCardBillPaymentInstrumentRelevance;

        HashMap<PaymentInstrumentType, List<PaymentInstrument>> eligiblePaymentCategories = Utils.getEligiblePaymentCategories
                (userPaymentInstruments, cart.getCartDetail().getCartAmount(), CreditCardBillPaymentInstruments, new CreditCardBillRelevance());

        for(PaymentInstrumentType paymentInstrumentType : CreditCardBillPaymentInstruments){
            if(paymentInstrumentType.equals(PaymentInstrumentType.UPI) && !upiEnabled){
                continue;
            }
            List<PaymentInstrument> instruments = eligiblePaymentCategories.get(paymentInstrumentType);
            if(Objects.nonNull(instruments) && !instruments.isEmpty()) {
                instruments.sort(new SortByRelevanceScore());
                paymentInstruments.addAll(instruments);
            }
        }

        return paymentInstruments;
    }

}
