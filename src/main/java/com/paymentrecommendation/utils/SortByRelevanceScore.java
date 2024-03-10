package com.paymentrecommendation.utils;

import com.paymentrecommendation.models.PaymentInstrument;

import java.util.Comparator;

public class SortByRelevanceScore implements Comparator<PaymentInstrument> {
    public int compare(PaymentInstrument a, PaymentInstrument b) {
        if(b.getRelevanceScore()-a.getRelevanceScore()>0) return 1;
        return -1;
    }
}
