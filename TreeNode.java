/**
 * TreeNode class to create trees from.
 */
public class TreeNode 
{
    private TreeNode left;      // left child of node
    private TreeNode middle;    // middle child of node
    private TreeNode right;     // right child of node
    private String payload;     // payload of node
    private int level;          // level of node in tree

    /**
     * Constructor.
     */
    public TreeNode(String payload, int level)
    {
        this.payload = payload;
        this.level = level;
        this.left = null;
        this.middle = null;
        this.right = null;
    }

    /**
     * Setter for left.
     */
    public void setLeft(TreeNode left)
    {
        this.left = left;
    }

    /**
     * Getter for left.
     */
    public TreeNode getLeft()
    {
        return this.left;
    }

    /**
     * Setter for middle.
     */
    public void setMiddle(TreeNode middle)
    {
        this.middle = middle;
    }

    /**
     * Getter for middle.
     */
    public TreeNode getMiddle()
    {
        return this.middle;
    }

    /**
     * Setter for right.
     */
    public void setRight(TreeNode right)
    {
        this.right = right;
    }

    /**
     * Getter for right.
     */
    public TreeNode getRight()
    {
        return this.right;
    }

    /**
     * Setter for payload.
     */
    public void setPayload(String payload)
    {
        this.payload = payload;
    }

    /**
     * Getter for payload.
     */
    public String getPayload()
    {
        return this.payload;
    }

    /**
     * Getter for level.
     */
    public int getLevel()
    {
        return this.level;
    }
}
