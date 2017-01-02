package org.learning.others;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by hluu on 12/27/16.
 */
public class Gairos {
    public static void main(String[] args) throws Exception {
        String fileName = "/Users/hluu/dev/opensource/elastic-search/sample_data/gairos-metrics.json";

        /*String str = "{abdc-sjc1-123,this}";

        if (str.indexOf("-sjc1") != -1) {
            str = str.substring(0, str.length()-1) + ",sjc1}";
        }

        System.out.printf("final %s\n", str);
        */


        addId(fileName);


    }

    private static String createOutputFileName(int count) {
        return "/Users/hluu/tmp/gairos" + count +".json";
    }

    private static void addId(String fileName) throws Exception {
        System.out.printf("**** addId for file: %s\n", fileName);

        int count = 1;
        int linesPerFile = 100000;

        System.out.printf("linesPerFile: %d\n", linesPerFile);
        int fileCount = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            File outputFile = new File(createOutputFileName(fileCount));
            if (outputFile.exists()) {
                outputFile.delete();
            }
            FileWriter fw = new FileWriter(createOutputFileName(fileCount));
            BufferedWriter bw = new BufferedWriter(fw);

            while ((line = br.readLine()) != null) {

                if (line.indexOf("-sjc1") != -1) {
                    line = line.substring(0, line.length()-1) + ", \"dc\":\"sjc1\"}";
                } else if (line.indexOf("-dca1") != -1) {
                    line = line.substring(0, line.length()-1) + ", \"dc\":\"dca1\"}";
                } else {
                    System.out.printf("******* Unable to find data center in %s\n", line);
                    continue;
                }

                /*if (count == 100) {
                    break;
                }*/

                //System.out.printf("count: %d\n", count);
                if (count % linesPerFile == 0) {
                    fileCount++;
                    String tmpOutputFile = createOutputFileName(fileCount);
                    System.out.printf("*** Switching output file to %s\n", outputFile);

                    outputFile = new File(tmpOutputFile);
                    if (outputFile.exists()) {
                        outputFile.delete();
                    }

                    bw.close();

                    fw = new FileWriter(tmpOutputFile);
                    bw = new BufferedWriter(fw);
                }


                //bw.write("{\"index\":{\"_id\":\"" + count + "\"}}");
                bw.write("{\"index\":{\"_id\":\"" + count + "\", \"_type\" : \"metric\"}}");
                bw.newLine();
                bw.write(line);
                bw.newLine();

                count++;
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("There should be %d files, total count: %d\n", fileCount, count);

    }
}
