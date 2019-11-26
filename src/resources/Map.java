package resources;

public class Map {
    public static int sizeX = 30;
    public static int sizeY = 20;

    public static String[] generateSuperMap() {
        String[] map = {"TTTTTTT **  *T  T****T ******T",
                "T** **T  *  T*  *    * T* $ *T",
                "T!    T  *  *T  *    * T* * *T",
                "T* * *T **  T*  T*  *T T*   *T",
                "T     T *              **** *T",
                "T** **T *                     ",
                "T*T TTT *  ****  ***** T* ** *",
                "           *TT*  *TT *  * *   ",
                "** T T **  *TT*  *   * T* *T *",
                "*7 T T  *  ****  *** * T* ** *",
                "** T T **                     ",
                "   T T           ** ^ ** **** ",
                "T*     ***  *T  *TT***** ** * ",
                "T* *** *T*  T*                ",
                "T* *T* *T*  *T  ** ** ** ** **",
                "T* *T* *T*  T*  TT*TT*TT*TT*TT",
                "T* *** *T*  *T  ** ** **#** **",
                "T*     *T*  T*                ",
                "T*@** **T*  *T  T* ** ** ** **",
                "TTTTT *TT*  T*  T**TT*TT*TT*TT"};
        return map;
    }
}
