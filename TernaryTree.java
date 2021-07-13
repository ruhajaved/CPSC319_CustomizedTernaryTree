/**
 * TernaryTree class.
 */
public class TernaryTree 
{
    private TreeNode root; // root of tree
    
    /**
     * constructor; root payload is set to "root" by default.
     */
    public TernaryTree()
    {
        root = new TreeNode("root", 0);
    }

    /**
     * Getter for root.
     * @return root node.
     */
    public TreeNode getRoot()
    {
        return this.root;
    }

    /**
     * Exchanges a given node's payload from a to b, for all
     * tree nodes with payload a. If a special operation, rather than
     * exchanging a with b, b is appended to a.
     * @param a Tree payloads to replace or append.
     * @param b new payload to exchange or append with.
     * @param specialFlag indicates if operation is a special operation.
     */
    public void exchange(String a, String b, boolean specialFlag)
    {
        while(true)                                             // run until no more nodes to do operation on
        {
            // search for node with payload a, location relative to other duplicates doesn't matter
            TreeNode result = searchTree(this.getRoot(), a);
            if(result == null)                                  // no more matchs found -> no replacements needed, return
            {
                return;
            }

            if(specialFlag)                                     // if special case
            {
                String newPayload = result.getPayload() + b;    //append b to end of a
                result.setPayload(newPayload);
            }
            else
            {
                result.setPayload(b);                           // replace a with b
            }
        }
    }

    /**
     * Finds first occurance of key using preorder traversal and recursion.
     * @param node node to start looking at.
     * @param key payload to search for.
     * @return if key not found, null; otherwise, node with payload key.
     */
    public TreeNode searchTree(TreeNode node, String key)
{
    if(node == null)                                        // if current doesn't exist
    {
        return null;                                        // return null, nothing to check
    }

    String payload = node.getPayload();
    if(payload.equals(key))                                 // if current node matches, return it
    {
        return node;
    }

    TreeNode result = searchTree(node.getLeft(), key);      // check left subtree if we find something
    if(result != null)                                      // if we do, return it
    {
        return result;
    }

    result = searchTree(node.getMiddle(), key);             // if not, check middle subtree if we find something
    if(result != null)                                      // if we do, return it
    {
        return result;
    }

    result = searchTree(node.getRight(), key);              // if not, check right subtree if we find something
    if(result != null)                                      // if we do, return it
    {
        return result;
    }

    return null;                                            // if no match in entire tree, return null
}
    /**
     * If payload a exist in tree and it doesn't have a child at the left,
     * payload b is added as the left child of a; if a doesn't exist in tree, nothing
     * happens, if a occurs more than once in tree, the highest level or most right position
     * (in case of a tie) at which a occurs is used. Note that in case of a special operation,
     * even if the child already exists, the payload of the child is set to b.
     * @param a payload to add child too, if it exists.
     * @param b payload to add as the child.
     * @param overwriteFlag indicates if operation is a special operation.
     * @return -2 for a not found in tree, -1 for found a but could not
     * add child in case of unspecial op, and 0 for success.
     */
    public int addL(String a, String b, boolean overwriteFlag)
    {
        TreeNode result = searchTreeMaxDepthMostRight(this.getRoot(), a); // search for node at which a is
        if(result == null)                                                // match not found, op unsuccessful
        {
            return -2;                                                    // a not found in tree
        }
        else if(result.getLeft() != null && overwriteFlag == false)       // left child exists and not special case
        {
            return -1;
        }
        else if(result.getLeft() != null && overwriteFlag == true)        // left child exists and special case
        {
            result.getLeft().setPayload(b);
            return 0;
        }

        TreeNode temp = new TreeNode(b, result.getLevel() + 1);           // normal add operation
        result.setLeft(temp);                                             // set left child to node with payload b

        return 0;                                                         // op successful

    }

    /**
     * If payload a exist in tree and it doesn't have a child at the middle,
     * payload b is added as the middle child of a; if a doesn't exist in tree, nothing
     * happens, if a occurs more than once in tree, the highest level or most right position
     * (in case of a tie) at which a occurs is used. Note that in case of a special operation,
     * even if the child already exists, the payload of the child is set to b.
     * @param a payload to add child too, if it exists.
     * @param b payload to add as the child.
     * @param overwriteFlag indicates if operation is a special operation.
     * @return -2 for a not found in tree, -1 for found a but could not
     * add child in case of unspecial op, and 0 for success.
     */
    public int addM(String a, String b, boolean overwriteFlag)
    {
        TreeNode result = searchTreeMaxDepthMostRight(this.getRoot(), a); // search for node at which a is the payload
        if(result == null)                                                // match not found, op unsuccessful
        {
            return -2;
        }
        if(result.getMiddle() != null && overwriteFlag == false)          // middle child exists and not special case
        {
            return -1;
        }
        else if(result.getMiddle() != null && overwriteFlag == true)      // left child exists and special case
        {
            result.getMiddle().setPayload(b);
            return 0;
        }

        TreeNode temp = new TreeNode(b, result.getLevel() + 1);           // normal add operation
        result.setMiddle(temp);                                           // set middle child to node with payload b

        return 0;                                                         // op successful

    }

    /**
     * If payload a exist in tree and it doesn't have a child at the right,
     * payload b is added as the right child of a; if a doesn't exist in tree, nothing
     * happens, if a occurs more than once in tree, the highest level or most right position
     * (in case of a tie) at which a occurs is used. Note that in case of a special operation,
     * even if the child already exists, the payload of the child is set to b.
     * @param a payload to add child too, if it exists.
     * @param b payload to add as the child.
     * @param overwriteFlag indicates if operation is a special operation.
     * @return -2 for a not found in tree, -1 for found a but could not
     * add child in case of unspecial op, and 0 for success.
     */
    public int addR(String a, String b, boolean overwriteFlag)
    {
        TreeNode result = searchTreeMaxDepthMostRight(this.getRoot(), a); // search for node at which a is the payload
        if(result == null)                                                // match not found, op unsuccessful
        {
            return -2;
        }
        if(result.getRight() != null && overwriteFlag == false)           // right child exists and not special case
        {
            return -1;
        }
        else if(result.getRight() != null && overwriteFlag == true)       // right child exists and special case
        {
            result.getRight().setPayload(b);
            return 0;
        }

        TreeNode temp = new TreeNode(b, result.getLevel() + 1);           // normal add operation
        result.setRight(temp);                                            // set middle child to node with payload b

        return 0;                                                         // op successful

    }

    /**
     * Deletes the whole left subtree of payload a; if a doesn't exist in tree or a
     * does not have a subtree at the left, nothing happens, and if a occurs more 
     * than once in tree, the highest level or most left position
     * (in case of a tie) at which a occurs is used.
     * @param a payload of tree node to delete subtree from.
     */
    public void delL(String a)
    {
        TreeNode temp = searchTreeMaxDepthMostLeft(this.getRoot(), a);  // search for node of interest

        if(temp == null)                                                // if a doesn't exist in tree
        {
            return;                                                     // do nothing
        }
        
        // note that the below fulfills functionality regardless of whether the subtree exists or not
        temp.setLeft(null);                                             // remove entire left subtree
    }

    /**
     * Deletes the whole middle subtree of payload a; if a doesn't exist in tree or a
     * does not have a subtree at the middle, nothing happens, and if a occurs more 
     * than once in tree, the highest level or most left position
     * (in case of a tie) at which a occurs is used.
     * @param a payload of tree node to delete subtree from.
     */
    public void delM(String a)
    {
        TreeNode temp = searchTreeMaxDepthMostLeft(this.getRoot(), a);  // search for node of interest

        if(temp == null)                                                // if a doesn't exist in tree
        {
            return;                                                     // do nothing
        }
        
        // note that the below fulfills functionality regardless of whether the subtree exists or not
        temp.setMiddle(null);                                          // remove entire middle subtree
    }

    /**
     * Deletes the whole right subtree of payload a; if a doesn't exist in tree or a
     * does not have a subtree at the right, nothing happens, and if a occurs more 
     * than once in tree, the highest level or most left position
     * (in case of a tie) at which a occurs is used.
     * @param a payload of tree node to delete subtree from.
     */
    public void delR(String a)
    {
        TreeNode temp = searchTreeMaxDepthMostLeft(this.getRoot(), a);  // search for node of interest

        if(temp == null)                                                // if a doesn't exist in tree
        {
            return;                                                     // do nothing
        }
        
        // note that the below fulfills functionality regardless of whether the subtree exists or not
        temp.setRight(null);                                            // remove entire right subtree
    }

    /**
     * Searches given tree with it's root at the given node for the given payload.
     * @param node tree root to start searching at.
     * @param key payload to search for.
     * @return if the key doesn't exist in tree, null is returned;
     * if duplicates exist, it returns the one at the highest level, and
     * if this is not possible (tie), the most right is returned.
     */
    public TreeNode searchTreeMaxDepthMostRight(TreeNode node, String key)
    {
        if(node == null)                                                        // if current doesn't exist
        {
            return null;                                                        // return null
        }

        int result = -1;
        int resultL = -1;
        int resultM = -1;
        int resultR = -1;

        String payload = node.getPayload();                                     // check current node for a match
        if(payload.equals(key))
        {
            result = node.getLevel();                                           // if found, store level
        }

        TreeNode leftNode = searchTreeMaxDepthMostRight(node.getLeft(), key);   // check left subtree for a match
        if(leftNode != null)
        {
            resultL = leftNode.getLevel();                                      // if found, store level
        }

        TreeNode middleNode = searchTreeMaxDepthMostRight(node.getMiddle(), key); // check middle subtree for a match
        if(middleNode != null)
        {
            resultM = middleNode.getLevel();                                    // if found, store level
        }

        //check right subtree if we find something
        TreeNode rightNode = searchTreeMaxDepthMostRight(node.getRight(), key); // check right subtree for a match
        if(rightNode != null)
        {
            resultR = rightNode.getLevel();                                     // if found, store level
        }

        // decide what to return
        if(result > resultL && result > resultM && result > resultR)        // current is highest level of key
        {
            return node;
        }
        else if(resultL > result && resultL > resultM && resultL > resultR) // left is highest level of key
        {
            return leftNode;
        }
        else if(resultM > result && resultM > resultL && resultM > resultR) // middle is highest level of key
        {
            return middleNode;
        }
        else if(resultR > result && resultR > resultL && resultR > resultM) // right is highest level of key
        {
            return rightNode;
        }
        else if(resultR == resultM || resultR == resultL)                   // right is at highest level and most right
        {
            return rightNode;
        }
        else if(resultM == resultL)                                         // middle is at highest level and most right
        {
            return middleNode;
        }   

        return null;                                                        // key not found in entire tree
    }

    /**
     * Searches given tree with it's root at the given node for the given payload.
     * @param node tree root to start searching at.
     * @param key payload to search for.
     * @return if the key doesn't exist in tree, null is returned;
     * if duplicates exist, it returns the one at the highest level, and
     * if this is not possible (tie), the most left is returned.
     */
    public TreeNode searchTreeMaxDepthMostLeft(TreeNode node, String key)
    {
        if(node == null)                                                         // if current doesn't exist
        {
            return null;                                                         // return null
        }

        int result = -1;
        int resultL = -1;
        int resultM = -1;
        int resultR = -1;

        String payload = node.getPayload();                                     // check current node for a match
        if(payload.equals(key))
        {
            result = node.getLevel();                                           // if found, store level
        }

        TreeNode leftNode = searchTreeMaxDepthMostLeft(node.getLeft(), key);    // check left subtree for a match
        if(leftNode != null)
        {
            resultL = leftNode.getLevel();                                      // if found, store level
        }

        TreeNode middleNode = searchTreeMaxDepthMostLeft(node.getMiddle(), key); // check middle subtree for a match
        if(middleNode != null)
        {
            resultM = middleNode.getLevel();                                    // if found, store level
        }

        TreeNode rightNode = searchTreeMaxDepthMostLeft(node.getRight(), key);  // check right subtree for a match
        if(rightNode != null)
        {
            resultR = rightNode.getLevel();                                     // if found, store level
        }

        if(result > resultL && result > resultM && result > resultR)        // current is highest level of key
        {
            return node;
        }
        else if(resultL > result && resultL > resultM && resultL > resultR) // left is highest level of key
        {
            return leftNode;
        }
        else if(resultM > result && resultM > resultL && resultM > resultR) // middle is highest level of key
        {
            return middleNode;
        }
        else if(resultR > result && resultR > resultL && resultR > resultM) // right is highest level of key
        {
            return rightNode;
        }
        else if(resultL == resultM || resultL == resultR)                   // left is at highest level and most left
        {
            return leftNode;
        }
        else if(resultM == resultR)                                         // middle is at highest level and most left
        {
            return middleNode;
        }   

        return null;                                                        // key not found in entire tree

    }

    /**
     * get's all non-null payloads in tree at specified level,
     * seperated by " ; "; note that the level is assumed to 
     * be valid and this traverses in preorder.
     * @param root root of tree to traverse.
     * @param level level of payloads wanted.
     * @return returns a string of all non-null payloads at 
     * specified level and seperated by " ; ".
     */
    private String printLevel(TreeNode root, int level)
    {
        if(root == null)                                            // if root doesn't exist
        {
            return "";                                              // return empty string
        }

        if(root.getLevel() == level)                                // if root in level wanted
        {
            return root.getPayload();                               // return it
        }

        String s = "";

        String tempL, tempM, tempR;

        tempL = printLevel(root.getLeft(), level);                  // otherwise go search in left subtree
        tempM = printLevel(root.getMiddle(), level);                // and search in middle subtree
        tempR = printLevel(root.getRight(), level);                 // and search in left subtree
        
        if(!tempL.isEmpty() && !tempM.isEmpty() && !tempR.isEmpty()) // all not empty
        {
            s = tempL + " ; " + tempM + " ; " + tempR;
        }
        else if(!tempL.isEmpty() && !tempM.isEmpty())               // 2 not empty
        {
            s = tempL + " ; " + tempM;
        }
        else if(!tempL.isEmpty() && !tempR.isEmpty())               // 2 not empty
        {
            s = tempL + " ; " + tempR;
        }
        else if(!tempM.isEmpty() && !tempR.isEmpty())               // 2 not empty
        {
            s = tempM + " ; " + tempR;
        }
        else if(!tempL.isEmpty())                                   // 1 not empty
        {
            s = tempL;
        }
        else if(!tempM.isEmpty())                                   // 1 not empty
        {
            s = tempM;
        }
        else if(!tempR.isEmpty())                                   // 1 not empty
        {
            s = tempR;
        }

        return s;
    }

    /**
     * Calculates the maximum height of a given tree.
     * @param node tree root.
     * @return returns the maximum height of tree with
     *  root at node provided.
     */
    private int maxDepth(TreeNode node)
    {
        if(node == null)                                // if root is null
        {
            return 0;                                   // return height of 0
        }
        
        int leftMax = maxDepth(node.getLeft());         // otherwise get height of left subtree
        int middleMax = maxDepth(node.getMiddle());     // and height of middle subtree
        int rightMax = maxDepth(node.getRight());       // and height of right subtree

        // find max height between subtrees
        int max;
        if(leftMax > middleMax)
        {
            max = leftMax;
        }
        else
        {
            max = middleMax;
        }

        if(rightMax > max)
        {
            max = rightMax;
        }

        return max + 1;                                 // return max height of all subtrees + 1 for current level
    }

    /**
     * Formats tree into a String, which when printed,
     * outputs the tree with each level on it's own line,
     * only non-null payloads described, an all are seperated by
     * " ; ".
     * @return tree as a String.
     */
    public String print()
    {
        int height = maxDepth(this.getRoot());          // get max height of tree
        String s = printLevel(this.getRoot(), 0);
        for(int i = 1; i < height; i++)                 // create string by finding payloads in each level
        {
            s += "\n" + printLevel(this.getRoot(), i);
        }
        return s;                                       // return string
    }
}