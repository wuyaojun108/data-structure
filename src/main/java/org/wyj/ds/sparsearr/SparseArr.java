package org.wyj.ds.sparsearr;

/*
    使用一个int类型的二维数组，来模拟围棋，0代表空，1代表白子，2代表黑子
    使用一个二维数组充当稀疏数组，对围棋数组进行压缩存储
    提供初始化围棋数组、生成稀疏数组、从稀疏数组中恢复围棋数组的方法
 */
public class SparseArr {
    // 围棋数组
    private final int[][] chessArr = new int[11][12];

    // 稀疏数组
    private int[][] sparseArr;

    // 围棋数组中的有效数据
    private int sum;

    public int[][] getChessArr() {
        return chessArr;
    }

    public int[][] getSparseArr() {
        return sparseArr;
    }

    public int getSum() {
        return sum;
    }

    // 初始化围棋数组的方法
    public void initialChessArr() {
        chessArr[2][3] = 1;
        chessArr[3][4] = 2;
        chessArr[3][7] = 1;
        chessArr[4][8] = 1;
        chessArr[7][1] = 1;
    }

    // 把围棋数组压缩到稀疏数组中
    public void initialSparseArr() {
        int sum = 0;
        for (int[] row : chessArr) {
            for (int item : row) {
                if (item != 0) {
                    sum++;
                }
            }
        }
        this.sum = sum;
        // 创建稀疏数组，它的第一行需要存储围棋数组的行数、列数、有效数据的个数，
        // 下面的每一行都存储一个有效数据，所以需要sum + 1行，每一行包括行坐标、
        // 列坐标、有效数据，所以需要三列
        sparseArr = new int[sum + 1][3];

        // 初始化稀疏数组的第一行，用于存储围棋数组的行数、列数、有效数据的个数
        sparseArr[0][0] = chessArr.length; // 行数
        sparseArr[0][1] = chessArr[0].length; // 列数
        sparseArr[0][2] = sum; // 有效数据的个数

        // 当前行的下标，1代表第二行，因为第一行在前面已经存储数据了
        int current_row_index = 1;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    sparseArr[current_row_index][0] = i;
                    sparseArr[current_row_index][1] = j;
                    sparseArr[current_row_index][2] = chessArr[i][j];
                    current_row_index++;
                }
            }
        }
    }

    // 从稀疏数组中恢复围棋数组
    public int[][] recoveryChessArr() {
        // 初始化需要恢复的围棋数组
        int row_num = sparseArr[0][0];
        int column_num = sparseArr[0][1];
        int[][] recoveryChessArr = new int[row_num][column_num];

        // 把有效值放入围棋数组中
        for (int i = 1; i < sparseArr.length; i++) {
            int row_index = sparseArr[i][0];
            int column_index = sparseArr[i][1];
            int value = sparseArr[i][2];
            recoveryChessArr[row_index][column_index] = value;
        }
        return recoveryChessArr;
    }
}
