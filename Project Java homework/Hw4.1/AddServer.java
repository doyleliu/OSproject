/**
 * Created by 栋 on 2017/3/8.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class AddServer {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
//        System.out.println("Please enter the port of the server");
//        int port = input.nextInt();
        int count = 0;
//        ServerSocket server = new ServerSocket(port);
        ServerSocket server = new ServerSocket(2222);
        Socket client = server.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out1 = new PrintWriter(client.getOutputStream());
        while (true) {
            String str = in.readLine();
//            System.out.println(str);
            try{
                int tmp = Integer.parseInt(str);
                count = count + tmp;
            }catch(Exception e){
                count = count;
            }
            out1.println("Get " + str);
            out1.flush();
            if (str.equals("exit"))
                break;
            System.out.println(count);
        }
        client.close();
    }
}
