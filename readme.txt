Programming Assignment 5: K-d Trees


/* *****************************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 **************************************************************************** */
My Node data type consisted of the key of the node(Point2D), the value of it, the
level or depth it is at in the kdtree, and its corresponding bounding box.
I have two methods that return back the x and y coordinates of the Node.

/* *****************************************************************************
 *  Describe your method for range search in a k-d tree.
 **************************************************************************** */

My range search implements a very simple but very effective way of pruning which
see's if the rectHV of a recursed node doesn't interesect with the specified
range, then it won't search that subtree anymore. Otherwise, the recursion is
pretty standard going through the left and right recusively and only adding points
that are contained in the range search.

/* *****************************************************************************
 *  Describe your method for nearest neighbor search in a k-d tree.
 **************************************************************************** */

My nearest neighbor function implements pruning that if the closest point
discovered so far is closer than the distance between the query point and the
rectangle corresponding to a node, there is no need to explore that node. Other
than that it recursively updates near depending on if a nodes distance squaredTo
is closer than the closest one so far. The recursive structure of it is to search
the subtree that is on the same side as the splitting line.

/* *****************************************************************************
 *  How many nearest-neighbor calculations can your PointST implementation
 *  perform per second for input1M.txt (1 million points), where the query
 *  points are random points in the unit square?
 *
 *  Fill in the table below, using one digit after the decimal point
 *  for each entry. Use at least 1 second of CPU time. Do not use -Xint.
 *  (Do not count the time to read the points or to build the 2d-tree.)
 *
 *  Repeat the same question but with your KdTreeST implementation.
 *
 **************************************************************************** */

(Took the average of 10 calls.
                 # calls to         /   CPU time     =   # calls to nearest()
                 client nearest()       (seconds)        per second
                ------------------------------------------------------
PointST:         50                 5.69                   8.7736

KdTreeST:        1000000            4.21                   237551.97

Note: more calls per second indicates better performance.


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
The limitations are that since the structure of the functions are recursive,
putting an additional node to an already large tree can take some time.

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
Got help with Node class from Jack. Debugging from Professor Han. Nearest and
put help from Daruis. Debugging from Daruis. Put help from Jack as well.
Testing help from Nina.


/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */

No serious problems that I encountered. Just a lot of debugging.

/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */

We(Colin and Zach) worked on this assignment together. Whenever we coded, we
would code together. If we went to TA hours seperately we would tell each other
and explained what we solved in it together.


/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on  how helpful the class meeting was and on how much you learned
 * from doing the assignment, and whether you enjoyed doing it.
 **************************************************************************** */
