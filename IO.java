import java.io.*;
import java.util.Scanner;

/**
 * Used to handle IO in the program.
 */
public class IO 
{
    /**
     * handles dealing with the input file.
     */
    public class InputFile
    {
        private Scanner sc; // link to input file
        
        /**
         * Setter method for sc field.
         * @param sc Scanner object for input file that we want to create a pipe to.
         */
        private void setsc(Scanner sc)
        {
            this.sc = sc;
        }

        /**
         * Getter method for sc field.
         * @return returns Scanner object that links to the input file.
         */
        private Scanner getsc()
        {
            return this.sc;
        }

        /**
         * Opens a file. Throws an exception and quite the program if input file
         * cannot be opened.
         * @param inputFileName Name of file to open as input.
         */
        public void openInputFile(String inputFileName)
        {
            try
            {
                File inputFile = new File(inputFileName);    // create input file object
                Scanner sc = new Scanner(inputFile);         // open pipeline to input file
                this.setsc(sc);                             // set sc field
            }catch(Exception e)
            {
                System.out.println("Input file could not be opened. Terminating.");
                System.exit(1);
            }
        }

        /**
         * Checks of there is a next line in the file being read.
         * @return Returns true if there is more to read. Otherwise false.
         */
        public boolean checkForNextLine()
        {
            Scanner sc = this.getsc();
            return sc.hasNextLine();

        }

        /**
         * Get's next line in the file being read.
         * @return Returns the next line.
         */
        public String getNextLine()
        {
            Scanner sc = this.getsc();
            return sc.nextLine();

        }

        /**
         * closes link to input file.
         */
        public void closeInputFile()
        {
            Scanner sc = this.getsc();
            sc.close();
        }
    }

    /**
     * Handles the output file.
     */
    public class OutputFile
    {
        private BufferedWriter bw;                                  // create pipe to output file
        private boolean outputNewlineFlag = false;                  // used to determine if a new line is added or not

        /**
         * Setter method for bw field.
         * @param bw Object to point bw field to.
         */
        private void setbw(BufferedWriter bw)
        {
            this.bw = bw;
        }

        /**
         * Setter for outputNewlineFlag
         * @param outputNewlineFlag new flag value.
         */
        private void setOutputNewlineFlag(boolean outputNewlineFlag)
        {
            this.outputNewlineFlag = outputNewlineFlag;
        }

        /**
         * Getter method for bw field.
         * @return  Returns bw field.
         */
        private BufferedWriter getbw()
        {
            return this.bw;
        }

        /**
         * Creates a link to the output file; either it creates it or overwrites an
         * exiting file of the same name as ouputFileName.
         * @param outputFileName Name of the file to create or overwrite.
         */
        public void openOutputFile(String outputFileName)
        {
            try
            {
                File outputFile = new File(outputFileName); // create output file object
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile)); // create pipe to output file
                this.setbw(bw); // set bw field
            }catch(Exception e)
            {
                System.out.println("Output file could not be opened. Terminating.");
                System.exit(1);
            }
        }

        /**
         * Writes string to the output file open.
         * @param output string to write in the output file.
         */
        public void writeToOutputFile(String output)
        {
            BufferedWriter bw = this.getbw();
            try
            {
                if(!outputNewlineFlag)                         // check if first time outputting
                {
                    this.setOutputNewlineFlag(true); 
                }
                else                                            // if not, output /n before outputing the rest
                {
                    bw.newLine();                                   
                }
                bw.write(output);
            }catch(Exception e)
            {
                this.closeOuputFile();
                System.out.println("Could not write to output file. Terminating.");
                System.exit(1);
            }
        }

        /**
         * Closes link to the output file.
         */
        public void closeOuputFile()
        {
            BufferedWriter bw = this.getbw();
            while(true)
            {
                try
                {
                    bw.close();
                    break;
                }catch(Exception e){}
            }
        }
    } 
}
