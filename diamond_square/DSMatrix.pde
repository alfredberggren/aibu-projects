class DSMatrix {
  private int[][] matrix;
  private int minRange;
  private int maxRange;

  public DSMatrix(int w, int minRange, int maxRange) {
    matrix = new int[w][w];
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < w; j++) {
        matrix[i][j] = 0;
      }
    }
    this.minRange = minRange;
    this.maxRange = maxRange;
    matrix[0][0] = int(random(minRange, maxRange));
    matrix[w-1][w-1] = int(random(minRange, maxRange));
    matrix[0][w-1] = int(random(minRange, maxRange));
    matrix[w-1][0] = int(random(minRange, maxRange));

    dsa();
  }

  private void dsa() {

    diamond(0, 0, 0, matrix.length-1, matrix.length-1, 0, matrix.length-1, matrix.length-1);
  }

  private void diamond(int x1, int y1,
    int x2, int y2,
    int x3, int y3,
    int x4, int y4) {
    int midX = (x3 + x1) /2;
    int midY = (y2 + y1)/ 2;
    println("" + midX + " " + midY);

    int avg = int((matrix[x1][y1] + matrix[x2][y2] + matrix[x3][y3] + matrix[x4][y4]) / 4);

    matrix[midX][midY] = avg + int(random(minRange, maxRange));
    int s_x1 = midX - midX;
    int s_x2 = midX;
    int s_x3 = midX;
    int s_x4 = midX + midX;
    int s_y1 = midY;
    int s_y2 = midY - midY;
    int s_y3 = midY + midY;
    int s_y4 = midY;
    square(s_x1, s_y1,
      s_x2, s_y2,
      s_x3, s_y3,
      s_x4, s_y4);
  }

  private void square(int x1, int y1,
    int x2, int y2,
    int x3, int y3,
    int x4, int y4) {
  }

  public void printMatrix() {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        print(matrix[i][j] + " ");
      }
      println();
    }
  }
}
