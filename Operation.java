/**
 * Operation class to create operations.
 */
public class Operation 
{
    private OpType type;    // type of op
    private String a;       // a if used by op
    private String b;       // b if used by op
    private boolean specialFlag = false; // by default, nothing is a special operation

    /**
     * Constructor for Add or Exchange ops.
     */
    public Operation(OpType type, String a, String b)
    {
        this.type = type;
        this.a = a;
        this.b = b;
    }

    /**
     * Constructor for Del ops.
     */
    public Operation(OpType type, String a)
    {
        this.type = type;
        this.a = a;
    }

    /**
     * Constructor for Print ops.
     */
    public Operation(OpType type)
    {
        this.type = type;
    }

    /**
     * Getter for type.
     */
    public OpType getType()
    {
        return this.type;
    }

    /**
     * Getter for a.
     */
    public String getStringA()
    {
        return this.a;
    }

    /**
     * Getter for b.
     */
    public String getStringB()
    {
        return this.b;
    }

    /**
     * Setter for specialFlag.
     */
    public void setSpecialFlag(boolean specialFlag)
    {
        this.specialFlag = specialFlag;
    }

    /**
     * Getter for specialFlag.
     */
    public boolean getSpecialFlag()
    {
        return this.specialFlag;
    }
}
