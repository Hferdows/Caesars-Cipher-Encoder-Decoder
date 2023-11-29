import java.io.*;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * PROGRAM 4- CIPHER
 * DATE- 03/06/2023
 * AUTHOR- @HANNAHFERDOWS
 */
public class Cipher {

    public static final int NUM_LETTERS = 26;
    public static final int ENCODE = 1;
    public static final int DECODE = 2;

    public static void main(String[] args) throws Exception {

        // letters
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        // Check args length, if error, print usage message and exit
        if (args.length != 3) {
            System.out.println("Option " + args.length + " is not valid"); //throws exception if command line receives anything other than 3 arguments
            System.exit(1);
        }

            String inputFilename = args[0]; //First argument passed is the file name.
            String key = args[1]; //second argument passed must be the word key user wants to use
            int action = Integer.parseInt(args[2]); //third argument that is passed must be whether to decode(2) or encode(1)

        if(action != 1 && action != 2) { //Throws an exception if the user enters anything other than 1 or 2
            System.out.println("Option " + action + " is not valid");
            System.exit(1);
        }

            String outputFilename = getOutputFilename(inputFilename, action);
            Scanner input = openInput(inputFilename); //creates and opens scanner to read inputfile
            PrintWriter output = openOutput(outputFilename); //creates and opens PrintWriter in order to write output to new file

            int i;
            int charDistance;
            int j = 0;
            int keyLength = (key.length() - 1);

            while (input.hasNext()) { // Read in data and output to file
                    String userWord = input.nextLine().toLowerCase(); //Will read each line, and put it to lowercase
                    for (i = 0; i < userWord.length(); ++i) {
                        if (j != keyLength) { //Checks to make sure j is not out of bounds for the key
                            charDistance = alphabet.indexOf(key.charAt(j));
                            ++j;
                        } else { //if j is out of bounds for key, it is set back to 0 again
                            charDistance = alphabet.indexOf(key.charAt(j));
                            j = 0;
                        }
                        if (action == ENCODE) {
                            char encoded = shiftUpByK(userWord.charAt(i), charDistance);
                            output.print(encoded);
                        }
                        if (action == DECODE) {
                            char decoded = shiftDownByK(userWord.charAt(i), charDistance);
                            output.print(decoded);
                        }
                    }
                }
                input.close();
                output.close();
        }

        //Opens input file for reading
         public static Scanner openInput(String filename) throws FileNotFoundException {
           File fileByteStream = new File(filename);
           Scanner inSS = new Scanner(fileByteStream);

           return inSS;
        }


         //Open output for writing
         public static PrintWriter openOutput(String filename) throws FileNotFoundException {
             File filestream = new File(filename);
             PrintWriter outFS = new PrintWriter(filestream);

             return outFS;
        }

         // Encode letter by some offset d
        public static char shiftUpByK(char c, int distance) {
            if ('a' <= c && c <= 'z')
                return (char) ('a' + (c - 'a' + distance) % NUM_LETTERS);
            if ('A' <= c && c <= 'Z')
                return (char) ('A' + (c - 'A' + distance) % NUM_LETTERS);
            return c; // don't encrypt if not an ic character
        }

        //Decode letter by some offset d
        public static char shiftDownByK(char c, int offset) { //shifts the
            int newOffset = 26 - offset;

            return shiftUpByK(c, newOffset);
        }

        //Changes file extension to ".coded" or ".decoded"
         public static String getOutputFilename(String filename, int action) {
             String finalFile;

             if(action == ENCODE) {  //if the user encoded a message, add .encoded
                 finalFile = filename + ".coded";
                 return finalFile;
             }
             if(action == DECODE) { //if the user decoded a message, add .decoded
                 finalFile = filename + ".decoded";
                 return finalFile;
             }
             else { //any other argument for action will cause a return of a null string.
                 return null;
             }

         }

        public String getInfo() {
            return "Program 3, Hannah Ferdows";
        }

    }
