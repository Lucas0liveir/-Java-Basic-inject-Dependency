/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

/**
 *
 * @author olive
 */
public class PayPalService implements OnlinePaymentService {
    private static final double PAY_FEE = 0.02;
    private static final double PAY_INTEREST = 0.01;

    @Override
    public Double paymentFee(Double amount) {
       return amount * PAY_FEE;
    }

    @Override
    public Double interest(Double amount, Integer months) {
       return amount * PAY_INTEREST * months;
    }
    
}
