/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.Calendar;
import java.util.Date;
import model.entities.Contract;
import model.entities.Installment;

/**
 *
 * @author olive
 */
public class ContractService {

    private OnlinePaymentService onlinepaymentservice;

    public ContractService(OnlinePaymentService onlinepaymentservice) {
        this.onlinepaymentservice = onlinepaymentservice;
    }

    public void processContract(Contract contract, int months) {
        double basicQuota = contract.getTotalValue() / months;//Get the value of contrancts in referring months
        for (int i = 1; i <= months; i++) {
            Date date = addMonths(contract.getDate(), i); //Add a date for the current month
            double updatedQuota = basicQuota + onlinepaymentservice.interest(basicQuota, i); //Apply the taxService
            double fullQuota = updatedQuota + onlinepaymentservice.paymentFee(updatedQuota); //Apply the taxService

            contract.addList(new Installment(date, fullQuota));//Instantiate a new Installment

        }

    }

    private Date addMonths(Date date, int n) { // Method to add a month based on the contract date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }
}
