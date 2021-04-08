package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PayPalService;

public class Program {

    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter contract data: ");
        System.out.print("Number: ");
        int number = sc.nextInt();
        System.out.print("Date (dd/MM/yyyy): ");
        Date date = sdf.parse(sc.next());
        System.out.print("Contract Value: ");
        Double totalValue = sc.nextDouble();

        System.out.print("Number of Installments: ");
        int n = sc.nextInt();
        
        Contract contract = new Contract(number, date, totalValue);
        ContractService cs = new ContractService(new PayPalService()); //Injecting the dependency of OnlineServicePayment.
        cs.processContract(contract, n);
        
        System.out.println("Installments:");
        
        for (Installment x : contract.getList()){
        System.out.println(x);
        }
        sc.close();

    }

}
