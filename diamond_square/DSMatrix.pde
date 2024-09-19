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
    
    helpMe(2);
  }
  
  private void helpMe(int n) {
  
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
