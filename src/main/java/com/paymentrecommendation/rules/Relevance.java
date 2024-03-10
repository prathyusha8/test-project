package com.paymentrecommendation.rules;

public interface Relevance {
    int getCcLimit();
    int getDcLimit();
    int getUpiLimit();
    int getNetBankingLimit();
}
