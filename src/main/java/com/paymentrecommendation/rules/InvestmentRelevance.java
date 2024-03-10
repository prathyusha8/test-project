package com.paymentrecommendation.rules;

import com.paymentrecommendation.enums.PaymentInstrumentType;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class InvestmentRelevance implements Relevance{
    public static List<PaymentInstrumentType> investmentPaymentInstrumentRelevance = Arrays.asList(PaymentInstrumentType.UPI,
            PaymentInstrumentType.NETBANKING, PaymentInstrumentType.DEBIT_CARD);

    private int ccLimit = 0;
    private int dcLimit = 150000;
    private int upiLimit = 100000;
    private int netBankingLimit = 150000;
}
