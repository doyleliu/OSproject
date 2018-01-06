/**
 * Created by 栋 on 2017/3/8.
 */

import java.io.*;
import java.net.*;

public class WGet {
    String Website;

    static String myfile;

    WGet(String web){
        Website = web;
    }

    public void Get(){
        try{
            URL U = new URL(Website);
            URLConnection tmp = U.openConnection();
            tmp.connect();
            InputStream is = tmp.getInputStream();
            byte[] data = readInputStream(is);
//            InputStreamReader isr = new InputStreamReader(is);
//            BufferedReader br = new BufferedReader(isr);
            char ch;
//            System.out.println("字节流长度： " + br.toString().length());
//            String  msg = "";
            File file =new File("web.txt");
            if(!file.exists()){
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            if(fos!=null){
                fos.close();
            }
            if(is!=null){
                is.close();
            }
//            FileWriter fileWritter = new FileWriter(file.getName(),true);
//            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

//            while ((msg = br.readLine())!=null)   {
//                bufferWritter.write(msg);
//            }
//            bufferWritter.close();
            //System.out.println(br.read());
//            br.close();

        }catch(Exception e){
            System.out.println(e);

        }
    }

    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int length = 0;
		// String str = ""; 
        ByteArrayOutputStream input = new ByteArrayOutputStream();
        while((length = inputStream.read(buffer)) != -1) {
            input.write(buffer, 0, length);
        }
        input.close();
        return input.toByteArray();
    }

    public static void main(String[] args){
        System.out.print("WGet: ");
        BufferedReader input =new BufferedReader(new InputStreamReader(System.in));

        String web = "";
        try{
            web = input.readLine();
        }catch ( Exception e){
            System.out.println(e);
        }

        WGet website = new WGet(web);
        website.Get();
    }
}
