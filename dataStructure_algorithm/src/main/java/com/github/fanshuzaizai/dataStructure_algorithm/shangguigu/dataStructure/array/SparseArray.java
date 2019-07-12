package com.github.fanshuzaizai.dataStructure_algorithm.shangguigu.dataStructure.array;

/**
 * 稀疏数组
 *
 * @author Jzy.
 * @date 2019/6/28 15:58
 */
public class SparseArray {
    public static void main(String[] args) {

        System.out.println("原始二维数组：");
        int[][] chess = new int[11][11];
        chess[2][1] = 1;
        chess[5][2] = 2;
        chess[0][0] = 4;
        chess[1][1] = 99;

        for (int[] row : chess) {
            for (int i : row) {
                System.out.print(i + "  ");
            }
            System.out.println();
        }

        System.out.println("稀疏数组：");

        //求个数
        int num = 0;
        for (int[] row : chess) {
            for (int i : row) {
                if (i != 0) {
                    num++;
                }
            }
        }

        int[][] sparseArr = new int[num + 1][3];
        //第一行
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = num;

        //放入元素
        boolean flag = false;
        int rowIndex = 1;
        for (int x = 0; x < chess.length; x++) {
            for (int y = 0; y < chess[x].length; y++) {
                int value = chess[x][y];
                if (value != 0) {
                    sparseArr[rowIndex][0] = x;
                    sparseArr[rowIndex][1] = y;
                    sparseArr[rowIndex][2] = value;
                    //元素个数满足后不再遍历了
                    if (rowIndex == num) {
                        flag = true;
                        break;
                    }
                    rowIndex++;
                }

            }
            if (flag) {
                break;
            }
        }

        for (int[] row : sparseArr) {
            for (int i : row) {
                if (i != 0) {
                    System.out.print(i + "  ");
                }
            }
            System.out.println();
        }


        System.out.println("稀疏数组还远二维数组：");

        int[][] restore = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int x = 1; x < sparseArr.length; x++) {
            restore[sparseArr[x][0]][sparseArr[x][1]] = sparseArr[x][2];
        }

        for (int[] row : restore) {
            for (int i : row) {
                System.out.print(i + "  ");
            }
            System.out.println();
        }

    }

}
