/**
 * Created by 栋 on 2017/3/8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Scanner;

public class AddClient {
    static Socket server;

    public static void main(String[] args) throws Exception {
//        System.out.println("Please enter the address of the server");
//        Scanner input = new Scanner(System.in);
//        String address =  input.nextLine();
//        System.out.println("Please enter the port of the server");
//        int port = input.nextInt();
//        server = new Socket(address, port);
        System.out.println("Please enter the port of the server");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        server = new Socket(InetAddress.getLocalHost(), 2222);
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter out = new PrintWriter(server.getOutputStream());
        BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(InetAddress.getLocalHost());
        while (true) {
            System.out.println("Please enter string to send to the server:");
            String str = wt.readLine();
            out.println(str);
            out.flush();
            if (str.equals("exit")) {
                break;
            }
            System.out.println(in.readLine());
        }
        server.close();
    }
}
