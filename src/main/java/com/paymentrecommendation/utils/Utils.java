package com.paymentrecommendation.utils;

import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.models.PaymentInstrument;
import com.paymentrecommendation.rules.InvestmentRelevance;
import com.paymentrecommendation.rules.Relevance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {

    public static HashMap<PaymentInstrumentType, List<PaymentInstrument>> getEligiblePaymentCategories
            (List<PaymentInstrument> userPaymentInstruments, double cartAmount,
             List<PaymentInstrumentType> paymentInstrumentRelevance, Relevance relevance){
        HashMap<PaymentInstrumentType, List<PaymentInstrument>> eligiblePaymentCategories = new HashMap<>();

        for (PaymentInstrument paymentInstrument : userPaymentInstruments) {
            for (PaymentInstrumentType paymentInstrumentType : paymentInstrumentRelevance) {
                if (paymentInstrument.getPaymentInstrumentType() != null && paymentInstrument.getPaymentInstrumentType().equals(paymentInstrumentType)) {
                    if(paymentInstrumentType.equals(PaymentInstrumentType.CREDIT_CARD) && cartAmount > relevance.getCcLimit()){
                        continue;
                    }
                    if(paymentInstrumentType.equals(PaymentInstrumentType.DEBIT_CARD) && cartAmount > relevance.getDcLimit()){
                        continue;
                    }
                    if(paymentInstrumentType.equals(PaymentInstrumentType.UPI) && cartAmount > relevance.getUpiLimit()){
                        continue;
                    }
                    eligiblePaymentCategories.computeIfAbsent(paymentInstrumentType, k -> new ArrayList<>());
                    eligiblePaymentCategories.getOrDefault(paymentInstrumentType, new ArrayList<>()).add(paymentInstrument);
                }
            }
        }
        return eligiblePaymentCategories;
    }
}
