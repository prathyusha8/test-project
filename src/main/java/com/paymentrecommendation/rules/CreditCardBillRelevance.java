package com.paymentrecommendation.rules;

import com.paymentrecommendation.enums.PaymentInstrumentType;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class CreditCardBillRelevance implements Relevance{
    public static List<PaymentInstrumentType> creditCardBillPaymentInstrumentRelevance = Arrays.asList(PaymentInstrumentType.UPI,
            PaymentInstrumentType.NETBANKING, PaymentInstrumentType.DEBIT_CARD);

    private int ccLimit = 0;
    private int dcLimit = 200000;
    private int upiLimit = 200000;
    private int netBankingLimit = 200000;

}
