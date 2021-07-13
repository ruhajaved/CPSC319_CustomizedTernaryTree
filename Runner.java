/**
 * Class used to run program.
 */
public class Runner
{
    public static void main(String[] args)
    {
        IO pipe = new IO();                                     // create IO object

        String inputFileName = args[0];                         // input file name
        IO.InputFile inputFile = pipe.new InputFile();          // create input file object
        inputFile.openInputFile(inputFileName);                 // open pipeline to input file

        String outputFileName = args[1];                        // output file name
        IO.OutputFile outputFile = pipe.new OutputFile();       // create output file object
        outputFile.openOutputFile(outputFileName);              // create pipe to output file

        TernaryTree tree = new TernaryTree();                   // create root w/ payload "root"

        try                                                     // try to fill myList
        {
            while(inputFile.checkForNextLine())                     // go through the input file, line by line
            {
                String s = inputFile.getNextLine();                 // get command from input file
                Operation op = Utility.getOperation(s);             // get operation, broken down
                int result = 0;
                switch(op.getType())
                {
                    case ADDL:
                    {
                        result = tree.addL(op.getStringA(), op.getStringB(), op.getSpecialFlag()); // execute AddL op
                        break;
                    }
                    case ADDM:
                    {
                        result = tree.addM(op.getStringA(), op.getStringB(), op.getSpecialFlag()); // execute AddM op
                        break;
                    }
                    case ADDR:
                    {
                        result = tree.addR(op.getStringA(), op.getStringB(), op.getSpecialFlag()); // execute AddR op
                        break;
                    }
                    case DELL:
                    {
                        tree.delL(op.getStringA());                                                // execute DelL op   
                        break;        
                    }
                    case DELM:
                    {
                        tree.delM(op.getStringA());                                                // execute DelM op 
                        break;                 
                    }
                    case DELR:
                    {
                        tree.delR(op.getStringA());                                                // execute DelR op 
                        break;                  
                    }
                    case EXCHANGE:
                    {
                        tree.exchange(op.getStringA(), op.getStringB(), op.getSpecialFlag());      // execute Exchange op 
                        break;
                    }
                    case PRINT:
                    {
                        String formattedTreeString = tree.print();                                 // execute Print op 
                        outputFile.writeToOutputFile(formattedTreeString);
                    }

                }

                if(result == -1) // found payload a for any of the UNSPECIAL add operations, but child already exists
                {
                     outputFile.writeToOutputFile("Add operation not possible.");   // write error to output file
                }
            }
        }
        catch(IllegalArgumentException e)                       // an input command was not valid
        {
             outputFile.writeToOutputFile("Input error.");       // write error to output file
        }
        inputFile.closeInputFile();                             // close input file link
        outputFile.closeOuputFile();                            // close output file link
    }  
}