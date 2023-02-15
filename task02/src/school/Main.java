package school;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static String dirName = "";
    public static String fileName = "cat_in_the_hat.txt";
    public static String dirFileName = dirName + File.separator + fileName;



    public static void main(String[] args) throws IOException {
        
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("kill")) {
                System.out.println("Ending program...");

                System.exit(0);
            }
        }

        if (args[0].equalsIgnoreCase("seuss")){

            String dirName = args[0];

            Path p = Paths.get(dirName, fileName);
            File f = p.toFile();
            System.out.println();
           

        try {
           
                 FileReader fr = new FileReader(f.getAbsolutePath());
                 System.out.println(fr);
                 BufferedReader br = new BufferedReader(fr);
                 String line;
                 List<String> story = new ArrayList<>();
                 String word = "";
                 String nextWord = "";
                 Map<String, Integer> occurence = new HashMap<>();  


                 while ((line = br.readLine()) != null) {

                   line = line.replace(",", " ");
                   line = line.replace(".", " ");
                   line = line.replace(":", " ");
                   line = line.replace("()", " ");
                   line =  line.replace("{}", " ");
                   line = line.replace("-", " ");
                   line = line.replace("!", " ");
                   line = line.replace("'", " ");
                   line = line.replace("?", " ");
                 

                 
                    String[] list = line.split(" ");
                    for (String s: list) {
                        story.add(s.toLowerCase());
                    }
                 }

                 while (true) {

                    for (int i = 0; i < story.size(); i ++){
                        word = story.get(i);
                        nextWord = story.get(i + 1);

                        if ((story.get(i).contains(word)) && (story.get(i + 1).contains(nextWord))){
                            if (occurence.containsKey(nextWord)){
                                Integer count = occurence.get(nextWord);
                                occurence.put(nextWord, count+1);
                            } else {
                                occurence.put(nextWord, 1);
                            }
                            //System.out.println(occurence.toString());
                            double size = occurence.size();
                            System.out.printf("\n%s\n\t\t %s  %.2f", word, nextWord, occurence.get(nextWord)/size);
                              
                        }
                   }


                  
                   break;
                 }

                 }catch (IOException e) {
                    e.printStackTrace();
                    }   
                }  
                }     
        }
    
    
    

