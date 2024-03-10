package com.paymentrecommendation.rules;

import com.paymentrecommendation.enums.PaymentInstrumentType;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class CommerceRelevance implements Relevance{
    public static List<PaymentInstrumentType> commercePaymentInstrumentRelevance = Arrays.asList(PaymentInstrumentType.CREDIT_CARD,
            PaymentInstrumentType.UPI, PaymentInstrumentType.DEBIT_CARD);

    private int ccLimit = 250000;
    private int dcLimit = 200000;
    private int upiLimit = 100000;
    private int netBankingLimit = 0;
}
