package school;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        
        Socket sock = null;
        int port = 0;
        String server = "";

        if (args.length > 0) {
            server = args[0];
            port = Integer.parseInt(args[1]);
        }

        // connect to server port
        sock = new Socket(server, port);

        // preparare input data from server
        InputStream is = sock.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);

        // prepare output data from server
        OutputStream os = sock.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);


        try {
            
            String recData = "";

          while (true) {
            recData = ois.readUTF();
        
                System.out.println(recData);
                List<Float> numbers = new ArrayList<Float>();
                
                
                String[] list = recData.split(",");
                System.out.println(list);
                for (String s : list) {
                    numbers.add(Float.parseFloat(s));
                }
                
                System.out.println(numbers);

            
                // get sum
           Float sum = 0.00f;
            for (Float n : numbers) {
                sum += n;
            }

            // get mean
                Float mean =  sum / numbers.size();
               System.out.println(mean); 

               // Get standard deviation of list of numbers
Double standardDeviation = 0.0;
for (double n: numbers) {
    standardDeviation += Math.pow(n - mean, 2);
}

// send response to server
double sd = Math.sqrt(standardDeviation / numbers.size());
oos.writeUTF("Irene Lee");
oos.writeUTF("irene_lee@live.com.sg");
oos.writeFloat(mean);
oos.writeFloat((float) sd);



//close all streams
oos.close();
os.close();
ois.close();
is.close();
sock.close();
         break;   

            }
          
    } catch (IOException e) {
        e.printStackTrace();
    }

    }
}
