package org.wyj.ds.sparsearr;

public class SparseArrTest {
    public static void main(String[] args) {
        final SparseArr sparseArrDemo = new SparseArr();

        // 初始化并返回围棋数组
        sparseArrDemo.initialChessArr();
        final int[][] chessArr = sparseArrDemo.getChessArr();

        // 遍历围棋数组
        System.out.println("遍历围棋数组");
        for(int[] row: chessArr){
            for(int item: row){
                System.out.printf("%s ", item);
            }
            System.out.println();
        }


        // 把围棋数组压缩到稀疏数组中
        sparseArrDemo.initialSparseArr();

        final int[][] sparseArr = sparseArrDemo.getSparseArr();

        System.out.println("有效数据的总数：" + sparseArrDemo.getSum());

        // 遍历稀疏数组
        System.out.println("遍历稀疏数组");
        for (int[] row : sparseArr) {
            for (int item : row) {
                System.out.printf("%s ", item);
            }
            System.out.println();
        }

        // 从稀疏数组中恢复围棋数组
        final int[][] recoveryChessArr = sparseArrDemo.recoveryChessArr();
        // 遍历恢复的稀疏数组
        System.out.println("遍历恢复的稀疏数组");
        for(int [] row: recoveryChessArr){
            for(int item: row){
                System.out.printf("%s ", item);
            }
            System.out.println();
        }

    }
}
