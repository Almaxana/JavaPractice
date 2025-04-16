package ru.itmo.Presenatation.Scenarios.CustomerIn;

import ru.itmo.Core.Common.AppException;
import ru.itmo.Core.Common.OperationResult;
import ru.itmo.Core.Common.OperationResultVerdict;
import ru.itmo.Core.Services.AccountService;
import ru.itmo.Presenatation.Scenarios.IScenario;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

public class CreateAccountScenario implements IScenario {
    public AccountService accountService;
    public CreateAccountScenario(AccountService _accountService){
        accountService = _accountService;
    }
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nEnter bank name");
        String bankName = in.nextLine();

        UUID accountId = UUID.randomUUID();

        System.out.println("\nEnter start sum");
        BigDecimal currentSum = in.nextBigDecimal();



        String accountType = in.nextLine();
        System.out.println("\nType kind of account: Debet  OR   Credit  OR  Deposit");
        accountType = in.nextLine();

        switch (accountType) {
            case ("Debet"):
                try{
                    OperationResult result = accountService.createDebetAccount(accountId, currentSum, bankName);
                    if (result.getVerdict() == OperationResultVerdict.SUCCESS) System.out.println("\nSUCCESS   Your account is" + accountId);
                    else System.out.println(result.getMessage() + "\n\n\n");
                    break;
                } catch (AppException appException){
                    System.out.println(appException.getMessage());
                }

            case ("Deposit"):
                System.out.println("\nEnter len");
                int length = in.nextInt();
                try{
                    OperationResult result = accountService.createDepositAccount(accountId, currentSum, bankName, length);
                    if (result.getVerdict() == OperationResultVerdict.SUCCESS) System.out.println("\nSUCCESS   Your account is" + accountId);
                    else System.out.println(result.getMessage() + "\n\n\n");
                    break;
                } catch (AppException appException){
                    System.out.println(appException.getMessage());
                }

            case ("Credit"):
                try{
                    OperationResult result = accountService.createCreditAccount(accountId, currentSum, bankName);
                    if (result.getVerdict() == OperationResultVerdict.SUCCESS) System.out.println("\nSUCCESS   Your account is" + accountId);
                    else System.out.println(result.getMessage() + "\n\n\n");
                    break;
                }catch (AppException appException){
                    System.out.println(appException.getMessage());
                }

            default:
                System.out.println("\nNo such kind of account");
                break;
        }

    }

    @Override
    public String getName() {
        return "Create Account";
    }
}
