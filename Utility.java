import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to aid in execution of program.
 */
public class Utility 
{
    /**
     * checks if the current line read from the input file is valid input,
     * and if so returns an Operation object containing the neccessary details.
     * @param s the current line from the input file to check.
     * @return  returns an Operation object, a parsed version of the input.
     * @throws IllegalArgumentException if input is an invalid operation.
     */
    public static Operation getOperation(String s) throws IllegalArgumentException
    {   
        Operation temp = getAdd(OpType.ADDL, s); // check if AddL op
        if(temp != null)
        {
            return temp;
        }

        temp = getAdd(OpType.ADDM, s);          // check if AddM op
        if(temp != null)
        {
            return temp;
        }

        temp = getAdd(OpType.ADDR, s);          // check if AddR op
        if(temp != null)
        {
            return temp;
        }

        temp = getSpecialAdd(OpType.ADDL, s);   // check if special AddL op
        if(temp != null)
        {
            return temp;
        }

        temp = getSpecialAdd(OpType.ADDM, s);   // check if special AddM op
        if(temp != null)
        {
            return temp;
        }

        temp = getSpecialAdd(OpType.ADDR, s);   // check if special AddR op
        if(temp != null)
        {
            return temp;
        }

        temp = getDel(OpType.DELL, s);          // check if DelL op
        if(temp != null)
        {
            return temp;
        }

        temp = getDel(OpType.DELM, s);          // check if DelM op
        if(temp != null)
        {
            return temp;
        }

        temp = getDel(OpType.DELR, s);          // check if DelR op
        if(temp != null)
        {
            return temp;
        }

        temp = getExchange(s);                  // check if Exchange op
        if(temp != null)
        {
            return temp;
        }

        temp = getSpecialExchange(s);           // check if special Exchange op
        if(temp != null)
        {
            return temp;
        }

        temp = getPrint(s);                     // check if Print op
        if(temp != null)
        {
            return temp;
        }

         throw new IllegalArgumentException();  // invalid input -> throw exception
    }

    /**
     * Checks if string given is a valid special Add operation.
     * @param type either enum constant OpType.ADDL, OpType.ADDM, or OpType.ADDR.
     * @param s string to check.
     * @return returns an Operation object if valid, otherwise null.
     */
    public static Operation getSpecialAdd(OpType type, String s)
    {
        // see if we have a special add op in correct format - ex. addL(a,$b) where a and b have no blanks
        String regex = "\\(([^\\s|^,]+)([,]+?)(?![,])(\\${1})([^\\s]+)\\)$";
        
        if(type == OpType.ADDL)             // looking for AddL
        {
            regex = "^AddL" + regex;
        }
        else if(type == OpType.ADDM)         // looking for AddM
        {
            regex = "^AddM" + regex;
        }
        else                                // looking for AddR
        {
            regex = "^AddR" + regex;
        }

        Pattern myPattern = Pattern.compile(regex);
        Matcher myMatcher = myPattern.matcher(s);
        if(!myMatcher.find())                                                   // if no match
        {
            return null;
        }
        String a = myMatcher.group(1) + myMatcher.group(2);                     // contains an extra comma!
        String b = myMatcher.group(4);
        
        Operation temp = new Operation(type, a.substring(0, a.length()-1), b); // create Operation object
        temp.setSpecialFlag(true);                                             // set specialFlag high
        return temp;
    }

    /**
     * Checks if string given is a valid Exchange operation.
     * @param s string to check.
     * @return returns an Operation object if valid, otherwise null.
     */
    public static Operation getExchange(String s)
    {
        // see if we have an Exchange op in correct format - ex. Exchange(a,b) 
        // where a and b have no spaces and b doesn't start with "$"
        String regex = "^Exchange\\(([^\\s|^,]+)([,]+?)(?![,])([^\\$]{1})([^\\s]*)\\)$";        // updated
        Pattern myPattern = Pattern.compile(regex);
        Matcher myMatcher = myPattern.matcher(s);
        if(!myMatcher.find())                                                   // if no match
        {
            return null;
        }
        String a = myMatcher.group(1) + myMatcher.group(2);                     //contains an extra comma!
        String b = myMatcher.group(3) + myMatcher.group(4);
        return new Operation(OpType.EXCHANGE, a.substring(0, a.length()-1), b); // return Operation object
    }

    /**
     * Checks if string given is a valid special Exchange operation.
     * @param s string to check.
     * @return returns an Operation object if valid, otherwise null.
     */
    public static Operation getSpecialExchange(String s)
    {
        // see if we have an Exchange op in correct format - ex. Exchange(a,$b) 
        // where a and b have no spaces
        String regex = "^Exchange\\(([^\\s|^,]+)([,]+?)(?![,])(\\${1})([^\\s]+)\\)$";

        Pattern myPattern = Pattern.compile(regex);
        Matcher myMatcher = myPattern.matcher(s);
        if(!myMatcher.find())                               // if no match
        {
            return null;
        }
        String a = myMatcher.group(1) + myMatcher.group(2); // contains an extra comma!
        String b = myMatcher.group(4);
        
        Operation temp = new Operation(OpType.EXCHANGE, a.substring(0, a.length()-1), b); // create new operation
        temp.setSpecialFlag(true);                          // set specialFlag high
        return temp;
    }

    /**
     * Checks if string given is a valid Del operation.
     * @param type either enum constant OpType.DELL, OpType.DELM, or OpType.DELR.
     * @param s string to check.
     * @return returns an Operation object if valid, otherwise null.
     */
    public static Operation getDel(OpType type, String s)
    {
        // see if we have an DelL op in correct format - ex. DelL(a,b)
        String regex = "\\(([^\\s]+)\\)$";

        if(type == OpType.DELL)             // looking for DelL
        {
            regex = "^DelL" + regex;
        }
        else if(type == OpType.DELM)         // looking for DelM
        {
            regex = "^DelM" + regex;
        }
        else                                // looking for DelR
        {
            regex = "^DelR" + regex;
        }

        Pattern myPattern = Pattern.compile(regex);
        Matcher myMatcher = myPattern.matcher(s);
        if(!myMatcher.find())                           // if no match
        {
            return null;
        }
        String a = myMatcher.group(1);
        return new Operation(type, a);                  // return Operation object
    }

    /**
     * Checks if string given is a valid Add operation.
     * @param type either enum constant OpType.ADDL, OpType.ADDM, or OpType.ADDR.
     * @param s string to check.
     * @return returns an Operation object if valid, otherwise null.
     */
    public static Operation getAdd(OpType type, String s)
    {
        // see if we have an Add op in correct format - ex. AddR(a,b)
        String regex = "\\(([^\\s|^,]+)([,]+?)(?![,])([^\\$]{1})([^\\s]*)\\)$";         // updated

        if(type == OpType.ADDL)             // looking for AddL
        {
            regex = "^AddL" + regex;
        }
        else if(type == OpType.ADDM)         // looking for AddM
        {
            regex = "^AddM" + regex;
        }
        else                                // looking for AddR
        {
            regex = "^AddR" + regex;
        }

        Pattern myPattern = Pattern.compile(regex);
        Matcher myMatcher = myPattern.matcher(s);
        if(!myMatcher.find())                               // if no match
        {
            return null;
        }
        String a = myMatcher.group(1) + myMatcher.group(2); // contains an extra comma!
        String b = myMatcher.group(3) + myMatcher.group(4);
        return new Operation(type, a.substring(0, a.length()-1), b); // return Operation object
    }

    /**
     * Checks if string given is a valid Print operation.
     * @param s string to check.
     * @return returns an Operation object if valid, otherwise null.
     */
    public static Operation getPrint(String s)
    {
        // see if we have a Print op in correct format - Print()
        String regex = "^Print\\(\\)$";
        Pattern myPattern = Pattern.compile(regex);
        Matcher myMatcher = myPattern.matcher(s);
        if(!myMatcher.find())                        // if no match
        {
            return null;
        }
        return new Operation(OpType.PRINT);         // return Operation object
    }
}
