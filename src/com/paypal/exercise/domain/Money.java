package com.paypal.exercise.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

public class Money {

    private static final Currency USD = Currency.getInstance("USD");
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    private BigDecimal amount;
    private Currency currency;   

    public static Money dollars(BigDecimal amount) {
        return new Money(amount, USD);
    }

    Money(BigDecimal amount, Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING);
    }

    Money(BigDecimal amount, Currency currency, RoundingMode rounding) {
        this.amount = amount;
        this.currency = currency;

        this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
    
    public static Money add(Money ... moneys){
    	BigDecimal total = BigDecimal.ZERO;
    	for (Money money : moneys) {
			total = total.add(money.getAmount());
		}
    	return Money.dollars(total);
    }
    
    public static Money sub(Money bigMoney,Money ... moneys){
    	BigDecimal total = bigMoney.getAmount();
    	for (Money money : moneys) {
			total = total.subtract(money.getAmount());
		}
    	return Money.dollars(total);
    }
    
    public static Money mult(Money ... moneys){
    	BigDecimal total = BigDecimal.ONE;
    	for (Money money : moneys) {
			total = total.multiply(money.getAmount());
		}
    	return Money.dollars(total);
    }

    @Override
    public String toString() {
        return getCurrency().getSymbol() + " " + getAmount();
    }

    public String toString(Locale locale) {
        return getCurrency().getSymbol(locale) + " " + getAmount();
    }   
}