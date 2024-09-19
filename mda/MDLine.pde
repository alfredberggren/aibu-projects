class MDLine {
  int x1, x2, y1, y2;

  public MDLine(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
  }

  public void draw() {
    line(x1, y1, x2, y2);
  }

  public void mda(int n, int r) {
    int interval = r/n;
    helpMe(this, n, 0, r, interval);
  }

  private void helpMe(MDLine line, int timesToIt, int iterations, int randomness, int decInterval) {
    iterations++;
    if (timesToIt+1 == iterations) {
      line.draw();
      return;
    }
    int rand = int(random(0-randomness, randomness));
    int midx = (line.x1 + line.x2) / 2;
    int midy = (line.y1 + line.y2) / 2;
    helpMe(new MDLine(line.x1, line.y1, midx, midy + rand), timesToIt, iterations, randomness - decInterval, decInterval);
    helpMe(new MDLine(midx, midy + rand, line.x2, line.y2), timesToIt, iterations, randomness - decInterval, decInterval);
  }
}
