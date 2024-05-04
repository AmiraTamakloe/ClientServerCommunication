import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class InputsHandler {
    public String checkIp()
            throws IOException
    {
        System.out.println("Drop the addy");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String ip = input.readLine();

        IpRegexChecker ipChecker = new IpRegexChecker();
        while(!ipChecker.isValid(ip)){
            System.out.println("Drop a good addy");
            ip = input.readLine();
        }
        return ip;
    }

    public int checkPort()
            throws IOException
    {
        System.out.println("Drop the port");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String port = input.readLine();
        while(true)
            try{
                int portInt = Integer.parseInt(port);

                while(portInt < 5000 || portInt > 5050){
                    System.out.println("Drop a good port");
                    port = input.readLine();
                    try {
                        portInt = Integer.parseInt(port);
                    }catch (NumberFormatException ex){
                        ex.printStackTrace();
                    }
                }
                System.out.println(portInt);
                return portInt;
            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
            }
    }


}
