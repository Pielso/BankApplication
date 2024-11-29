package cloud.aws;

import cloud.aws.service.AccountService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        do {
            AccountService.menuText();
        }while (!AccountService.handleUserChoice(scan.nextInt()));
    }
}