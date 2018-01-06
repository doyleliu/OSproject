import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SimpleShell
{
    public static void main(String[] args) throws java.io.IOException
    {
        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String MyPath=System.getProperty("user.dir");  //get the current path
        String HomePath=MyPath;
        ArrayList<String> commandHistory=new ArrayList<String>();
        int tem=0;
        // we break out with <control><C>

        while (true)
        {
            // read what the user entered
            System.out.print(MyPath+"@");
            System.out.print("jsh>");
            commandLine = console.readLine();
            // if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;
            /* The steps are:
             (1) parse the input to obtain the command and any parameters
             (2) create a ProcessBuilder object
             (3) start the process
             (4) obtain the output stream
             (5) output the contents returned by the command */
            //public ProcessBuilder (List<String> command);
            if(commandLine.charAt(0)=='!')
            {
                tem=0;
                int tmpnum =0 ;
                if(commandLine.length()==2&&commandLine.charAt(1)=='*')
                {
                    //commandHistory.remove(commandHistory.size()-1);
                    for(int i=0;i<commandHistory.size();i++)
                    {
                        String a=commandHistory.get(i);
                        System.out.print(i+" "+a+"\n");

                    }
                    //commandHistory.add("!*");
                    continue;
                }
                if(commandLine.length()==2&&commandLine.charAt(1)=='!')
                {
                    tmpnum = commandHistory.size()-1;
                }
                else
                {
                	// work out the comand with a certain number
                    try{ 
                        for(int i = 1; i < commandLine.length();i++){
                            tmpnum = tmpnum * 10 + commandLine.charAt(i)-'0';
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        continue;
                    }
//                    for(int i=1;i<commandLine.length();i++)
//                    {
//                        tem=tem*10+commandLine.charAt(i)-'0';
//                    }
                }
                if (tmpnum > commandHistory.size()-1 || tmpnum < 0)
                {
                    System.out.println(tmpnum);
                    System.out.print("error:event not found! \n");
                    System.out.print(commandHistory.size()+"\n");
                    continue;
                }
                else
                {
//                    String exCommand=commandHistory.get(commandHistory.size()-tem);
                    String exCommand=commandHistory.get(tmpnum);
                    System.out.print(exCommand+"\n");
                    commandLine=exCommand;
                }
            }
            String[] UserCommand = commandLine.split(" ");
            if(UserCommand[0].equalsIgnoreCase("cd"))
            {
                commandHistory.add(commandLine);
                if(UserCommand.length==1)
                {
                    MyPath=HomePath;
                }
                else if (UserCommand[1].charAt(0) == '/')
                {
                    String[] CommandSplit = UserCommand[1].split("/");
                    String currentPath = "/";
					//boolean commandflag = true;
					//int count = 0;
                    boolean currentFlag = true;
                    for (int i = 1; i < CommandSplit.length; i++)
                    {
						//comand in process
                        ProcessBuilder pb = new ProcessBuilder("ls");
                        File filedir = new File(currentPath);
                        pb.directory(filedir);
                        Process process = pb.start();
                        InputStream is = process.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);
                        String line;
                        boolean flag = false;
						//int tmpnumber = 0
                        while ((line = br.readLine()) != null)
                        {
                            if (CommandSplit[i].equalsIgnoreCase(line))
                            {
                                flag = true;
                                break;
                            }
                        }
                        br.close();
                        if (flag)
                        {
                            currentPath=currentPath+ "/" +CommandSplit[i];
                            continue;
                        }
                        else
                        {
                            System.out.print("error:Files cannot be found!\n");
                            currentFlag=false;
                            break;
                        }
                    }
                    if(currentFlag)
                    {
                        MyPath = UserCommand[1];
                    }
                }
                else
                {
					// there has a bit more site
                    String[] CommandSplit = UserCommand[1].split("/");
                    String currentPath =MyPath;
                    boolean currentFlag=true;
                    for (int i = 0; i < CommandSplit.length; i++)
                    {	
						//comand in process
                        ProcessBuilder pb = new ProcessBuilder("ls");
                        File filedir = new File(currentPath);
                        pb.directory(filedir);
                        Process process = pb.start();
                        InputStream is = process.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);
                        String line;
                        boolean flag = false;
                        while ((line = br.readLine()) != null)
                        {
                            if (CommandSplit[i].equalsIgnoreCase(line))
                            {
                                flag = true;
                                break;
                            }
                        }
                        br.close();
                        if (flag)
                        {
                            currentPath=currentPath+ "/" + CommandSplit[i];
                            continue;
                        }
                        else
                        {
                            System.out.print("error:Files cannot be found!\n");
                            currentFlag=false;
                            break;
                        }
                    }
                    if(currentFlag)
                    {
                        MyPath = MyPath + "/" + UserCommand[1];
                    }
                }
            }
            else if(UserCommand[0].equalsIgnoreCase("history"))
            {
                for(int i = 0; i < commandHistory.size();i++)
                {
                    String a=commandHistory.get(i);
                    System.out.print(i+" "+a+"\n");
                }
            }
            else if(UserCommand.length==1&&UserCommand[0].equalsIgnoreCase("quit"))
            {
                System.out.println("Bye！");
                commandHistory.clear();
                System.exit(0);
            }
            else
            {
                try
                {
                    commandHistory.add(commandLine);
                    ProcessBuilder pb = new ProcessBuilder(UserCommand);
                    File filedir=new File(MyPath);
                    pb.directory(filedir);
                    Process process = pb.start();
                    // obtain the input stream
                    InputStream is = process.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    // read the output of the process
                    String line;
                    while ( (line = br.readLine()) != null)
                        System.out.println(line);
                    br.close();
                }
                catch(Exception e)
                {
                    commandLine=null;
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}