/* *****************************************************************************
 *  Name:     Jiatong Yu
 *  NetID:    jiatongy
 *  Precept:  P04
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Hours to complete assignment (optional): 10
 *
 **************************************************************************** */

Programming Assignment 7: Seam Carving


/* *****************************************************************************
 *  Describe concisely your algorithm to find a horizontal (or vertical)
 *  seam.
 **************************************************************************** */
I used the bottom-up dynamic programming approach. The recurrence is:
if row == 0, distTo(v) = energy(v)
else distTo(v) = min{distTo(u) | u pointing to v} + energy(v)

I also maintained an edgeTo array to keep track of the path itself.


/* *****************************************************************************
 *  Describe what makes an image suitable to the seam-carving approach
 *  (in terms of preserving the content and structure of the original
 *  image, without introducing visual artifacts). Describe an image that
 *  would not work well.
 **************************************************************************** */
suitable images:
image with clear distinction between focus and background is suitable for the
seam carving, because then low energy paths would detect the background and produce
less distortion / artifacts

image that won't work well are those that has no clear distinction between
what's the focus and what's the background. For example, a Kaleidoscope would
not work well with seam carving since (1) there's no clear path of low energy, and
(2) any horizontal/ vertical cut will distort the symmetry and thus the shapes in
the Kaleidoscope.


/* *****************************************************************************
 *  Perform computational experiments to estimate the running time to reduce
 *  a W-by-H image by one column and one row (i.e., one call each to
 *  findVerticalSeam(), removeVerticalSeam(), findHorizontalSeam(), and
 *  removeHorizontalSeam()). Use a "doubling" hypothesis, where you
 *  successively increase either W or H by a constant multiplicative
 *  factor (not necessarily 2).
 *
 *  To do so, fill in the two tables below. Each table must have 5-10
 *  data points, ranging in time from around 0.25 seconds for the smallest
 *  data point to around 30 seconds for the largest one.
 **************************************************************************** */

(keep W constant)
 W = 2000
 multiplicative factor (for H) = 2

 H           time (seconds)      ratio       log ratio
------------------------------------------------------
250             0.251             --            --
500             0.349            1.39           0.47
1000            0.678            1.94           0.95
2000            1.279            1.88           0.91
4000            2.529            1.98           0.98
8000            5.498            2.17           1.11
16000           9.777            1.77           0.82
32000           24.533           2.51           1.32


(keep H constant)
 H = 2000
 multiplicative factor (for W) = 2

 W           time (seconds)      ratio       log ratio
------------------------------------------------------
500             0.254           ----            ---
1000            0.454           1.78            0.83
2000            0.930           2.05            1.03
4000            1.971           2.11            1.08
8000            4.092           2.03            1.02
16000           9.306           2.27            1.18
32000           23.864          2.56            1.35



/* *****************************************************************************
 *  Using the empirical data from the above two tables, give a formula
 *  (using tilde notation) for the running time (in seconds) as a function
 *  of both W and H, such as
 *
 *       ~ 5.3*10^-8 * W^5.1 * H^1.5
 *
 *  Briefly explain how you determined the formula for the running time.
 *  Recall that with tilde notation, you include both the coefficient
 *  and exponents of the leading term (but not lower-order terms).
 *  Round each coefficient and exponent to two significant digits.
 **************************************************************************** */


Running time (in seconds) to find and remove one horizontal seam and one
vertical seam, as a function of both W and H:


    ~   ~ 5.8*10^-8 * W^1.16 * H^1.08
       _______________________________________

After filling out the table, I first calculate the exponential b by
giving more weights to larger input sizes. Then I plug in the exponentials
of W and H, and ran 4 new experiments to calculate the coefficient a.


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
every time removing a seam, my program re-calculate the energy table entirely
instead of looking only at the changed parts, which is something to improve

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
no one


/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
When designing the find shortest path algorithm, I first tried to re-implement
Dijkstra's algorithm using MinIndexPQ. It was a pain and didn't run as fast
as ideal. So I changed to dynamic programming.

/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */


/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
